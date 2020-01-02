#!/bin/bash

echo '============== start spring-boot-docker appliation ================='

docker run \
-itd --name "spring-boot-docker" \
-p 10002:10002 \
registry.cn-shanghai.aliyuncs.com/rexlin600/spring-boot-docker:2.0.0
