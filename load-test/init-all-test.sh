ns=gr-queen-perf


helm uninstall my-grafana
helm install --dependency-update my-grafana ../apps/grafana -f ../apps/grafana/values.yaml -n $ns

helm uninstall my-influxdb
helm install --dependency-update my-influxdb ../apps/influxdb -f ../apps/influxdb/values.yaml -n $ns

helm uninstall my-prometheus
helm install --dependency-update my-prometheus ../apps/prometheus -f ../apps/prometheus/values.yaml -n $ns