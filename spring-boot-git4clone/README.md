# 简介

这个应用利用 `多线程技术` 帮助用户实现快速批量下载 `gitlab` 上的项目，用户只需要进行如下修改即可使用

```yaml
# 修改为自己的相关配置即可
# max-wait-time 表示最多等待多久时间返回下载结果（单位：秒~~~~）
gitlab:
  host: http://git.xxx.com
  token: xxx
  username: xxx
  password: xxx
  max-wait-time: 60
```