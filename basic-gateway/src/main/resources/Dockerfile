FROM jdk1.8 
ADD basic-gateway-1.0.0.jar /opt/
ENTRYPOINT java -jar /opt/basic-gateway-1.0.0.jar >> /data/logs/success.log $$ /bin/bash
