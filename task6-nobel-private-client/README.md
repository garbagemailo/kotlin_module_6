# Task 6 — Nobel Private Client

Клиент переписан на работу с собственным сервером из задания 5.


## Зависимость от task5

Это приложение не работает само по себе: оно является Android-клиентом для сервера из `task5-nobel-server-postgres`. Перед запуском клиента нужно запустить сервер.

Порядок запуска для Android Emulator:

```bash
cd ../task5-nobel-server-postgres
./gradlew run
```

Проверить на компьютере:

```text
http://localhost:8081/docs
http://localhost:8081/prizes
```

После этого запустить `task6-nobel-private-client` в Android Studio. Для эмулятора адрес сервера должен быть:

```text
http://10.0.2.2:8081
```

`10.0.2.2` — специальный адрес Android Emulator для доступа к `localhost` компьютера.

Для физического телефона нужен IP компьютера в локальной сети, например:

```properties
nobelPrivateBaseUrl=http://192.168.1.10:8081
```

Эту строку можно добавить в `task6-nobel-private-client/gradle.properties`, затем выполнить Gradle Sync и переустановить приложение.

## База URL

По умолчанию выставлен адрес Android Emulator:

```properties
nobelPrivateBaseUrl=http://10.0.2.2:8081
```

Для физического устройства нужно заменить на IP вашей машины в локальной сети.

## Что есть

- список лауреатов
- фильтр по году и категории
- экран деталей
- состояния `Loading / Success / Error`
