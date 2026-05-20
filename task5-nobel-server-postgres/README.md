# Task 5 — Nobel Prize API + PostgreSQL / Neon

## Что реализовано

- PostgreSQL/Neon-хранилище
- JWT авторизация
- локальные стартовые данные Nobel Prize, чтобы `/prizes` работал без внешнего API
- ручная синхронизация с публичным Nobel API через `POST /sync`
- таблицы `users`, `prizes`, `laureates`, `user_prizes`
- избранные премии пользователя
- `CallLogging`
- Swagger UI на `/docs`
- OpenAPI YAML на `/openapi.yaml`

## Открытые endpoints

- `POST /login`
- `GET /prizes`
- `GET /prizes/{year}/{category}`
- `GET /prizes/{year}/{category}/laureates`
- `POST /sync` — попытка обновить локальную БД из публичного Nobel API

## Защищённые endpoints

- `GET /users/me`
- `GET /users/me/prizes`
- `POST /users/me/prizes/{prizeId}`
- `DELETE /users/me/prizes/{prizeId}`

## Demo users

- `demo / demo123`
- `admin / admin123`

## Быстрый локальный запуск

Для обычной проверки достаточно Docker. Команда ниже автоматически поднимет локальный PostgreSQL из `docker-compose.yml`, дождётся готовности БД и запустит сервер:

```bash
./gradlew run
```

После запуска открыть:

```text
http://localhost:8081/docs
http://localhost:8081/prizes
```

`/prizes` больше не зависит от доступности внешнего Nobel API на каждом запросе. При первом запуске БД заполняется локальными демонстрационными данными. Если нужен refresh из публичного Nobel API, вызови:

```bash
curl -X POST http://localhost:8081/sync
```

Если внешний API недоступен или отвечает слишком долго, сервер сохранит текущие локальные данные и не должен отдавать `500` на обычный `GET /prizes`.

Локальные параметры по умолчанию:

```text
DATABASE_URL=jdbc:postgresql://localhost:5432/nobel
DATABASE_USER=postgres
DATABASE_PASSWORD=postgres
```

## Запуск для клиента task6

`task6-nobel-private-client` обращается к этому серверу. Поэтому сначала должен быть запущен `task5`.

Для Android Emulator ничего менять не нужно: клиент использует `http://10.0.2.2:8081`, а сервер слушает порт `8081`.

Для физического Android-устройства компьютер и телефон должны быть в одной сети. Сервер уже настроен на `host: 0.0.0.0`, поэтому он доступен по IP компьютера, например `http://192.168.1.10:8081`. Этот адрес нужно прописать в `task6-nobel-private-client/gradle.properties` как `nobelPrivateBaseUrl`.

Если `/prizes` открывается на компьютере, но не открывается в Android-приложении, чаще всего причина одна из трёх: сервер не запущен, выбран неправильный адрес (`localhost` внутри Android указывает на само Android-устройство), или firewall блокирует порт `8081`.

## Ручной запуск PostgreSQL

```bash
docker compose up -d postgres
./gradlew run
```

Проверка контейнера:

```bash
docker compose ps
docker compose logs postgres
```

Остановка контейнера:

```bash
docker compose down
```

Полная очистка данных:

```bash
docker compose down -v
```

## Запуск с Neon или внешним PostgreSQL

Если задан `DATABASE_URL`, Gradle не будет запускать локальный Docker PostgreSQL. Нужно выставить переменные окружения:

```bash
export DATABASE_URL='jdbc:postgresql://host:5432/dbname?sslmode=require'
export DATABASE_USER='username'
export DATABASE_PASSWORD='password'
export JWT_SECRET='super_secret_key_for_nobel_postgres_server_123456'
export JWT_ISSUER='nobel-postgres-server'
export JWT_AUDIENCE='nobel-postgres-client'
./gradlew run
```
