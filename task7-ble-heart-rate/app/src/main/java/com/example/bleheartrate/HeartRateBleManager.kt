package com.example.bleheartrate

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothGattService
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.os.ParcelUuid
import java.util.UUID

@SuppressLint("MissingPermission")
class HeartRateBleManager(private val context: Context) {
    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter
    private val scanner: BluetoothLeScanner? get() = bluetoothAdapter.bluetoothLeScanner
    private val seenDevices = linkedMapOf<String, BleDeviceUi>()
    private var gatt: BluetoothGatt? = null

    var onDevicesChanged: (List<BleDeviceUi>) -> Unit = {}
    var onStatusChanged: (String) -> Unit = {}
    var onHeartRateChanged: (Int) -> Unit = {}

    private val scanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            val device = result.device ?: return
            val item = BleDeviceUi(device.name ?: result.scanRecord?.deviceName.orEmpty(), device.address)
            seenDevices[device.address] = item
            onDevicesChanged(seenDevices.values.toList())
        }
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            if (newState == BluetoothGatt.STATE_CONNECTED) {
                onStatusChanged("Подключено. Поиск сервисов...")
                gatt.discoverServices()
            } else if (newState == BluetoothGatt.STATE_DISCONNECTED) {
                onStatusChanged("Отключено")
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            val service = gatt.getService(HEART_RATE_SERVICE_UUID)
            val characteristic = service?.getCharacteristic(HEART_RATE_MEASUREMENT_UUID)
            if (characteristic == null) {
                onStatusChanged("Heart Rate Measurement не найден")
                return
            }
            gatt.setCharacteristicNotification(characteristic, true)
            val descriptor = characteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG_UUID)
            descriptor?.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
            if (descriptor != null) {
                gatt.writeDescriptor(descriptor)
                onStatusChanged("Подписка на уведомления включена")
            }
        }

        override fun onCharacteristicChanged(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic) {
            if (characteristic.uuid == HEART_RATE_MEASUREMENT_UUID) {
                val bpm = parseHeartRate(characteristic.value)
                onHeartRateChanged(bpm)
            }
        }
    }

    fun startScan() {
        val filters = listOf(
            ScanFilter.Builder()
                .setServiceUuid(ParcelUuid(HEART_RATE_SERVICE_UUID))
                .build()
        )
        val settings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .build()
        scanner?.startScan(filters, settings, scanCallback)
        onStatusChanged("Ищем устройства с Heart Rate Service")
    }

    fun stopScan() {
        scanner?.stopScan(scanCallback)
    }

    fun connect(address: String) {
        stopScan()
        val device = bluetoothAdapter.getRemoteDevice(address)
        gatt?.close()
        gatt = device.connectGatt(context, false, gattCallback)
    }

    fun close() {
        stopScan()
        gatt?.close()
    }

    private fun parseHeartRate(data: ByteArray): Int {
        if (data.isEmpty()) return 0
        val flag = data[0].toInt()
        return if (flag and 0x01 == 0) {
            data.getOrNull(1)?.toInt()?.and(0xFF) ?: 0
        } else {
            ((data.getOrNull(2)?.toInt() ?: 0) shl 8) or (data.getOrNull(1)?.toInt()?.and(0xFF) ?: 0)
        }
    }

    companion object {
        val HEART_RATE_SERVICE_UUID: UUID = UUID.fromString("0000180d-0000-1000-8000-00805f9b34fb")
        val HEART_RATE_MEASUREMENT_UUID: UUID = UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb")
        val CLIENT_CHARACTERISTIC_CONFIG_UUID: UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
    }
}
