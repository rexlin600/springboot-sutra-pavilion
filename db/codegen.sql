/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 5.7.29 : Database - codegen
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`codegen` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `codegen`;

/*Table structure for table `data_source` */

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

/*Data for the table `data_source` */

insert  into `data_source`(`id`,`host`,`port`,`username`,`password`,`db_name`,`create_time`,`update_time`) values 
(1,'localhost','3306','root','123456','tb_flyway','2020-01-14 15:03:59',NULL),
(2,'10.197.236.152','3306','root','123456','edl_public','2020-01-14 15:50:51',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
