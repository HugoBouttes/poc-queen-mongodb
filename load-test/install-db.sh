#!/bin/bash
ns=gr-queen-perf
helm install --dependency-update my-mongodb ../apps/mongodb -f ../apps/mongodb/values.yaml -n $ns
