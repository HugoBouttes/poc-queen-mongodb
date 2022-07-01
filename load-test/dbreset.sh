#!/bin/bash
ns=gr-queen-perf

#### MongoDB #####

kubectl delete job k6-performance-test  -n $ns

# Uninstall helm releases my-mongodb and data
helm uninstall my-mongodb -n $ns
kubectl delete pvc datadir-my-mongodb-0 -n $ns
kubectl delete pvc datadir-my-mongodb-1 -n $ns
kubectl delete pvc datadir-my-mongodb-2 -n $ns

sleep 10

# # Install helm release
helm install my-mongodb ../apps/mongodb -f ../apps/mongodb/values.yaml -n $ns


