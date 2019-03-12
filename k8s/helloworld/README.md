# helm chart
Проект описывает helm-чарт для разворачивания сервиса в кластер kubernetes.

---

## Установка чарта

Чтобы установить чарт, необходимо:
````
helm template path/to/chart/ --name helloworld > $HOME/helloworld-chart.yaml
kubectl apply -f $HOME/helloworld-chart.yaml
````
---

## Конфиг values.yaml
| Параметр                                   | Описание                                         | Значение по-умолчанию 
|--------------------------------------------|--------------------------------------------------|-------------------------------------------|
| `image.repository`                         | адрес репы, откуда берется образ                 | `ppcololo/helloworld`
| `image.tag`                                | версия образа                                    | `latest`
| `image.pullPolicy`                         | политика скачивания образа                       | `Always`
| `service.port`                             | порт сервиса для балансировки подов              |  8080
| `checks.enabled`                           | флаг включения проверки пода на готовность       | `true`
| `checks.livenessProbe.scheme`              | тип проверки: http/https                         | `http`
| `checks.livenessProbe.path`                | путь для проверки доступности сервиса            | `/libeness`
| `checks.livenessProbe.initialDelaySeconds` | задержка проверки на доступность                 | 30
| `checks.livenessProbe.periodSeconds`       | частота проверки на доступность                  | 10
| `checks.readiness.scheme`                  | тип проверки: http/https                         | `http`
| `checks.readiness.path`                    | путь для проверки доступности сервиса            | `/actuator/health`
| `checks.readiness.initialDelaySeconds`     | задержка проверки на готовность                  | 30
| `checks.readiness.periodSeconds`           | частота проверки на готовность                   | 10
| `checks.readiness.failureThreshold`        | количество неуспешных проверок                   | 3
| `ingress.enabled`                          | флаг включения доступности через http            | `true`
| `ingress.hostname`                         | хост по которому будет доступен сепрвис          | `"helloworld.server.com"`
| `replicaCount`                             | количество реплик подов                          | 1
| `resources.requests.memory`                | лимит по памяти при создании пода                | 512Mi
| `resources.requests.cpu`                   | лимит по CPU при создании пода                   | 100m
| `resources.limit.memory`                   | лимит по памяти при работе пода                  | 1Gi
| `resources.limit.cpu`                      | лимит по CPU при работе пода                     | 1

---