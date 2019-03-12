# Тестовое задание на написание веб-приложения helloworld

Исходные данные:
```
Реализовать тривиальное HTTP "Hello, world!" web-приложение на любом удобном Вам языке программирования и завернуть его в clound native окружение.

Требования:
- Dockerfile, который докеризует приложение.
- Приложение должно иметь health-check и ready-check.
- Приложение должно предоставлять metrics endpoint для Prometheus (метрики - на Ваше усмотрение).
- Grafana dashboard с визуализацией метрик.
- docker-compose.yml, который запускает приложение со всем необходимым окружением (Prometheus и Grafana).

Временем и инструментом для выполнение тестового задания Вы не ограничены. Любые другие аспекты реализации, которые не указаны в требованиях, могут быть выполнены на Ваше усмотрение.

Следующее будет плюсом:
- Kubernetes спеки приложения, либо Helm-чарт, для запуска его в Minikube (в дополнение к docker-compose.yaml).
- E2E-тесты, которые проверяют корректность докеризации приложения.
```

Решение:
## Web-приложение 
Реализовано на языке Java (Spring Boot)
Добавлена одна кастомная метрика requests_count_total - количество запросов главной страницы
Собранный jar файл специально не клал рядом с кодом - много места ест.


## Docker (шаги 0 и 1 можно пропустить, так как собранный образ с данным сервисом внутри уже лежит на dockerhub)
0) Необходимо собрать проект и получить helloworld-0.0.1
```
IntelliJ IDEA > Maven > package
```
кладем jar-файл в папку docker
1) Собираем образ
```
cd docker
docker built -t helloworld .
```
2) Стартуем docker-compose
```
docker-compose up -d
```
3) Заходим и смотрим стартанувшие компоненты:
  * helloworld : localhost:8080 (metrics - localhost:8080/actuator/prometheus)
  * prometheus : localhost:9090
  * grafana : localhost:3000 (admin/admin)
  
## Kubernetes
В папке k8s/helloworld лежит helm chart для нашего сервиса.  
Установка чарта описана в файлике README.md
Там реализован deployment c сервисом helloworld + readiness\liveness probes.  
Так же там есть ingress, но хост надо будет добавлять себе в /etc/hosts

PS: docker-image предварительно загружен в dockerhub