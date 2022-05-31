#!/bin/bash
export VUS="250"
export PROTOCOL="http"
export HOSTNAME="demoqueenmongo:8080"
ns=gr-queen-perf

# Uri to k6 script file
export K6_FILE_URI='https://raw.githubusercontent.com/BouttesINSEE/poc-queen-mongodb/main/load-test/k6/script.js'

kubectl delete job k6-performance-test  -n $ns

cat k6/job.yaml | envsubst | kubectl apply -f -  -n $ns