# Task 4 — Nobel Prize API (memory)

## Возможности

- `POST /auth/login` — JWT на 30 минут
- `GET /prizes` — все премии (защищённый)
- `GET /prizes/{year}/{category}` — детальная премия (защищённый)
- `GET /prizes/{year}/{category}/laureates` — лауреаты премии (защищённый)
- `CallLogging`
- Clean Architecture
- данные хранятся в памяти (`SeedData`)

## Тестовый логин

```json
{
  "username": "admin",
  "password": "admin123"
}
```

## Пример запуска

```bash
./gradlew run
```

## Пример получения токена

```bash
curl -X POST http://localhost:8080/auth/login   -H "Content-Type: application/json"   -d '{"username":"admin","password":"admin123"}'
```
