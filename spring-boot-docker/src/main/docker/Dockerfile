FROM anapsix/alpine-java:8_server-jre_unlimited

LABEL MAINTAINER=rexlin600@gmail.com

# ENV
ENV TZ=Asia/Shanghai
ENV APP_PATH="/app"
ENV LOG_PATH="/app/logs"
ENV JAVA_ARGUMENTS="-Xms256m -Xmx256m \
    -Djava.security.egd=file:/dev/./urandom"

# 要构建的程序
ENV APP_NAME="spring-boot-docker.jar"

# 设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 创建工作目录
RUN mkdir -p $APP_PATH \
    && mkdir -p $LOG_PATH

# 导向到工作目录
WORKDIR $APP_PATH
# 注意，一定要提前构建好jar包执行此命令才会成功！
COPY $APP_NAME/ $APP_PATH

EXPOSE 10002

# 启动项目
CMD java -jar $JAVA_ARGUMENTS $APP_NAME
