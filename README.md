# poc-queen-mongodb

**Objectif du POC :**
- déterminer si une implémentation MongoDb & orientée document de Stromae est possible
- qualifier cette implémentation


**Ce dépôt contient :**
- l'ensemble des helm charts utilisés (répertoire ./apps) pour construire l'environnement de test de performance sur dev.insee.io à savoir :
  - une base [MongoDB](https://github.com/bitnami/charts/tree/master/bitnami/mongodb)
  - un [Prometheus](https://github.com/prometheus-community/helm-charts/tree/main/charts/prometheus) pour collecter les métriques MongoDB, Postgresql et celles des APIs Metallica.
  - une base [InfluxDB](https://github.com/influxdata/helm-charts) pour stocker les métriques k6 
  - un [Grafana](https://github.com/grafana/helm-charts) pour l'observation des métriques (https://grafana-poc-metallica.dev.insee.io) et ses dashboards
- les scénarios métiers à tester (./scenarios)
- les scripts js de test de performance k6 (load-test/k6)


Un node pool de 3 VMs dédiées a été créé pour ce POC . Il vient se rajouter au node pool déjà existant sur le cluster Kubernetes managé dev.insee.io. Pour déployer sur ce node pool, il convient de préciser dans les helm charts le bloc :
```yaml
tolerations:
  - key: "metallica"
    operator: "Equal"
    value: "perf"
    effect: "NoSchedule"
```


**Les instances déployées pour le POC**

* Mongodb (accessible au sein du cluster uniquement)

| parameter  | value  |
|---|---|
| uri  |  ```echo mongodb://demo:$(kubectl get secret -n $ns mongodb-secret -o jsonpath="{.data.mongodb-password}" \| base64 --decode ; echo)@my-mongodb-0.my-mongodb-headless:27017,my-mongodb-1.my-mongodb-headless:27017,my-mongodb-2.my-mongodb-headless:27017``` |
| database  | poc |


* InfluxDB (accessible au sein du cluster uniquement)

| parameter  | value  |
|---|---|
| host  | http://my-influxdb:8086 |
| database  | k6 |

* Prometheus (accessible au sein du cluster uniquement)

| parameter  | value  |
|---|---|
| host  | http://my-prometheus-server |

* Grafana

| parameter  | value  |
|---|---|
| host  | https://grafana-demoqueenmongo.dev.insee.io |
| username  | admin |
| password  | ```kubectl get secret -n $ns my-grafana -o jsonpath="{.data.admin-password}" \| base64 --decode ; echo``` |


* API Queen mode mongodb

| parameter  | value  |
|---|---|
| host  | https://demoqueenmongo.dev.insee.io/ |
