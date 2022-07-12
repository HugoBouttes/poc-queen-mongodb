```shell
helm dependencies update
helm install my-grafana . -f values.yml -n $ns
```

Getting admin password
```shell
kubectl get secret -n gr-queen-perf my-grafana -o jsonpath="{.data.admin-password}" | base64 --decode ; echo
```