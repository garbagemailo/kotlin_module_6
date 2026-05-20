# Task 3 — Auth + Users Client

## Что реализовано

- авторизация через `POST /auth/login`
- сохранение токена в `Preferences DataStore`
- Bearer token добавляется через `OkHttp Interceptor`
- список пользователей
- детальная информация о пользователе
- выход из аккаунта с очисткой токена
- состояния `Loading / Success / Error`

## Тестовый вход

- username: `emilys`
- password: `emilyspass`
