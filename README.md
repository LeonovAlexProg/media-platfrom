# Media-project!

Данный проект представляет собой реализацию ТЗ от **Effective Mobile**. Реализовал функции API в полном объёме. Тесты отсутствуют за нехваткой времени на изучение Mock и интеграционного тестирования.

За неделю работы изучул такие технологии как:
- Spring Security
- JWT-authentication
- Swagger/OpenAPI документация
- Освоил теорию Mock-тестирования


# Swagger

Для получения документации по эндпоинтам следует:
- запустить проект
- перейти в браузере по http://localhost:8080/swagger-ui/index.html#/
- в поисковой строке Swagger UI вбить /v3/api-docs

# Эндпоинты

Все эндпоинты по пути localhost:8080/api кроме **api/auth/**** являются **защищёнными** и требуют JWT-токен для доступа.

Для получения **JWT-токена** существует 2 эндпоинта:
1. **POST localhost:8080/api/auth/register** - регистрация нового пользователя
2. **POST localhost:8080/api/auth/authenticate** - аутентификация зарегестрированного пользователя

## Доступ к эндпоинтам

Для доступа к защищённым эндпоинтам следует передать передать полученный при регистрации JWT-токен вместе с запросом в заголовке **Authorization**

Токен выступает так же в роли идентификатора пользователя для его получения из БД.