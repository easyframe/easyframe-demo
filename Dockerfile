FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
LABEL Vendor="EasyFrame" easyframe-serv="ef-demo"
ADD target/easyframe-demo-master-SNAPSHOT-exec.jar demo.jar
RUN sh -c 'touch /demo.jar'

ENV JAVA_OPTS="-server -Xmx512m -Xms512m -XX:NewRatio=1 -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 \
-XX:+UseCMSInitiatingOccupancyOnly -XX:+ParallelRefProcEnabled -XX:+AlwaysPreTouch -XX:ReservedCodeCacheSize=128M \
-XX:MaxTenuringThreshold=6 -XX:+ExplicitGCInvokesConcurrent -Duser.timezone=Asia/Shanghai \
-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.port=8061 \
-Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=192.168.31.140"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /demo.jar" ]