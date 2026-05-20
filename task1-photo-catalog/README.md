# Task 1 — Photo Catalog

## Что реализовано

- Retrofit + Clean Architecture
- список фото с `https://picsum.photos/v2/list`
- `LazyVerticalGrid` в 2 столбца
- детализация с большой картинкой через Coil
- состояния `Loading / Success / Error`
- скачивание через **SAF**: выбирается папка в системном диалоге, затем фото сохраняется в выбранную директорию (рекомендуется Downloads)

## Основные пакеты

- `data` — Retrofit API + repository
- `domain` — model/use cases/repository contract
- `presentation` — Compose UI + ViewModel + navigation

## Запуск

1. Открыть папку в Android Studio.
2. Sync Gradle.
3. Запустить модуль `app`.
