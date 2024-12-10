Тестовое задание для U-Future. Представляет собой API, для фиксирования сенсоров погоды и привязки к ним полученных измерений.
Инструкция по запуску:
1) Запустить postgres-севрис в docker-compose.yml
2) Запустить app-сервис в docker-compose.yml

Инструкция по использованию:

1. Authentication-API
   1) /auth - POST-запрос, выполняет регистрацию нового пользователя. Принимает JSON body:
      {
      "email":"email",
      "password":"password"
      }
   2) /auth - GET-запрос, выполняет авторизацию зарегистрированного пользователя, возвращая jwt. Принимает JSON body:
      {
      "email":"email",
      "password":"password"
      }

2. Sensor-API
   1) /sensors/registration - POST-запрос, выполняет регистрацию нового, ранее не зарегестрированного сенсора. Принимает JSON body:
      {
      "name":"sensorName"
      }

3. Measurement-API
   1) /measurements/add - POST-запрос, выполняет привязку нового измерения к существующему сенсору. Принмает JSON body:
      {
      "value": 1.0,
      "raining": true,
      "sensor": {
        "name":"sensorName"
        }
      }
   2) /measurements - GET-запрос, возвращает все зарегестрированные измерения.
   3) /measurements/rainyDaysCount - GET-запрос, возвращает количество измерений, в которых зафиксирован дождь.
