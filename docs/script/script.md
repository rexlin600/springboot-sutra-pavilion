# 脚本

> 这里给出需要某些中间件的参考 `docker` 指令，方便大家做测试！

## MySQL

> 配置文件请参考 `docs/script/config/my.cnf`

```
# 创建文件夹
$ mkdir /docker/data/mysql_5.7.26
$ mkdir /docker/logs/mysql_5.7.26
$ mkdir /docker/database/config

# 然后将进入到 my.cnf 所在目录并将其放入 /docker/database/config 中
$ mv my.cnf /docker/database/config/

# 分配权限
chmod -R 777 /docker

# 启动 mysql
$ docker run \
 --name mysql_5.7.27 \
 -p 3306:3306 \
 -v /docker/data/mysql_5.7.26:/var/lib/mysql \
 -v /docker/logs/mysql_5.7.26:/var/log/mysql \
 -v /docker/database/config/my.cnf:/etc/mysql/my.cnf:rw \
 -e MYSQL_ROOT_PASSWORD=123456 \
 --restart=always \
 -d mysql:5.7.26
```

## MongoDB

```
# 创建文件夹
$ mkdir /docker/data/mongodb

# 分配权限
chmod -R 777 /docker

# 启动 MongoDB
$ docker run \
-p 27017:27017 \
-v /docker/data/mongodb:/data/db \
--name mongodb:3.3.8 \
-d mongo:3.3.8
```

## Redis

> 不指定配置文件

```
# 创建文件夹
$ mkdir /docker/data/redis

# 分配权限
chmod -R 777 /docker

# 启动 Redis
$ docker run \
-p 6379:6379 \
-v /docker/data/redis:/data \
--name redis \
-d redis
```

## Elasticsearch

```

```
 