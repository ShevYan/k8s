#!/bin/bash
#set -x

# check if shadowsocks-client is running
if ! ps -ef | grep shadowsocks-local > /dev/null 2>&1 ; then 
	echo "Shadowsocks-client is not running!"
	exit -1
fi

# start proxy
echo "Starting http proxy(Privoxy)..."
sudo /Applications/Privoxy/stopPrivoxy.sh 
sudo /Applications/Privoxy/startPrivoxy.sh 

# stop minikube
minikube stop

ipaddr=""
# get local routable ip
for ((i=0; i<=10; i++));
do
	ipaddr=`ipconfig getifaddr en$i | grep 192.168 `
	if [ X"$ipaddr" != X"" ]; then
		echo "en$i -> $ipaddr"
		break;
	fi
done

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
