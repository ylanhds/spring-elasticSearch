FROM java:8
EXPOSE 8080
MAINTAINER ylanhds@gmail.com
VOLUME /tmp
# 将主机环境的jar包，以文件名.jar添加到docker镜像中。
ADD ./target/spring-elasticsearch-1.0.0.jar spring-elasticsearch.jar
RUN sh -c 'touch /spring-elasticsearch.jar'
ENV JAVA_OPTS=""
#启动容器之后，默认的运行命令
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /spring-elasticsearch.jar" ]
