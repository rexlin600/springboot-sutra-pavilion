DROP database if exists `custom_test_starter`;
create database custom_test_starter default character set utf8mb4 collate utf8mb4_general_ci;

USE custom_test_starter;

DROP TABLE IF EXISTS `data_source`;
CREATE TABLE `data_source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `host` varchar(500) NOT NULL COMMENT '数据库地址',
  `port` varchar(20) NOT NULL COMMENT '数据库端口',
  `username` varchar(500) NOT NULL COMMENT '用户名',
  `password` varchar(500) NOT NULL COMMENT '密码',
  `db_name` varchar(100) NOT NULL COMMENT '数据库名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

INSERT INTO data_source (id, host, port, username, password, db_name, create_time, update_time) VALUES (1, 'localhost', '3306', '830c5eabaa5bf6720e06040d49e02aea', 'aed08c462c0f70208d0770804383e464', 'custom_test_starter', '2020-01-18 13:49:09', null);
INSERT INTO data_source (id, host, port, username, password, db_name, create_time, update_time) VALUES (2, 'localhost', '3306', '830c5eabaa5bf6720e06040d49e02aea', 'aed08c462c0f70208d0770804383e464', 'spring-boot-mybatisplus', '2020-01-18 13:48:58', null);
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `position` varchar(100) DEFAULT NULL COMMENT '岗位',
  `weight` double DEFAULT NULL COMMENT '重量',
  `color` varchar(32) DEFAULT NULL COMMENT '颜色',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`id`,`name`,`sort`,`position`,`weight`,`color`,`create_date`,`update_date`) values
(0,' Padraig',0,'接待员',192.78,'透明','2011-03-25 16:37:44','2014-10-05 15:16:39'),
(1,' Geoffrey',1,'接待员',143.15,'天蓝色','2015-05-14 22:23:27','2012-03-10 04:01:32');