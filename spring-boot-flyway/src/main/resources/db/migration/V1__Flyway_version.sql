DROP TABLE IF EXISTS `tb_flyway`;
CREATE TABLE `tb_flyway`
(
  `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`        varchar(255)  DEFAULT NULL,
  `length`      double(16, 2) DEFAULT NULL,
  `create_time` datetime      DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;