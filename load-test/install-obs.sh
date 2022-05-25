#!/bin/bash
ns=gr-queen-perf
helm install --dependency-update my-influxdb ../apps/influxdb -f ../apps/influxdb/values.yaml -n $ns
helm install --dependency-update my-prometheus ../apps/prometheus -f ../apps/prometheus/values.yaml -n $ns
helm install --dependency-update my-grafana ../apps/grafana -f ../apps/grafana/values.yaml -n $ns