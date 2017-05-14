#!/bin/bash
set -x

# get local routable ip
ipaddr=$(ipconfig getifaddr en0)

# export env
export http_proxy=http://$ipaddr:8118
export https_proxy=http://$ipaddr:8118

# replace minikube existing config file
sed -i "" "s#\"HTTP_PROXY=http:.*,\$#\"HTTP_PROXY=$http_proxy\",#" /Users/dongyan/.minikube/machines/minikube/config.json
sed -i "" "s#\"HTTPS_PROXY=http:.*,\$#\"HTTPS_PROXY=$http_proxy\",#" /Users/dongyan/.minikube/machines/minikube/config.json

# start minikube with env
minikube start \
         --docker-env HTTP_PROXY=$http_proxy \
         --docker-env HTTPS_PROXY=$http_proxy \
         --docker-env NO_PROXY=192.168.99.0/24 \
         --extra-config=apiserver.ServiceNodePortRange=1000-30000

echo "CALL following to use docker:"
echo "     eval $(minikube docker-env)"
