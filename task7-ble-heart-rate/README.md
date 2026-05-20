# Task 7 — BLE Heart Rate Monitor

## Что реализовано

- BLE scanner
- фильтр по `Heart Rate Service` (`0x180D`)
- подключение к устройству
- подписка на `Heart Rate Measurement` (`0x2A37`) через notifications
- отображение `Heart Rate: 72 bpm` / `—`

## UUID

- Service: `0000180d-0000-1000-8000-00805f9b34fb`
- Characteristic: `00002a37-0000-1000-8000-00805f9b34fb`
- Descriptor: `00002902-0000-1000-8000-00805f9b34fb`

## Важные замечания

- Для Android 12+ нужны разрешения `BLUETOOTH_SCAN` и `BLUETOOTH_CONNECT`.
- Для старых версий Android нужен `ACCESS_FINE_LOCATION`.
- Тестировать лучше на реальном устройстве.
