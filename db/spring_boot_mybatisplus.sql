
/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 5.7.29 : Database - spring_boot_mybatisplus.sql
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`spring_boot_mybatisplus.sql` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `spring_boot_mybatisplus.sql`;

/*Table structure for table `goods` */

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
(1,' Geoffrey',1,'接待员',143.15,'天蓝色','2015-05-14 22:23:27','2012-03-10 04:01:32'),
(2,' Juan',2,'研发工程师',186.16,'天蓝色','2007-11-06 13:06:44','2001-11-20 06:17:28'),
(3,' Jean',3,'行政经理',89.25,'桔色','2002-03-11 07:12:07','2007-06-11 04:59:52'),
(4,' Anallese',4,'网络运维工程师',166.48,'酒红色','2004-09-05 23:44:22','2004-10-20 07:47:18'),
(5,' Roscoe',5,'测试工程师',30.39,'天蓝色','2010-04-27 07:48:08','2008-09-02 21:15:46'),
(6,' Alexandros',6,'话务员',107.38,'深灰色','2012-07-15 05:46:04','2014-08-26 11:10:35'),
(7,' Adair',7,'研发工程师',104.33,'黑色','2009-12-18 17:53:51','2006-04-15 04:17:44'),
(8,' Annnora',8,'话务员',196.45,'紫色','2003-11-07 00:35:14','2013-10-01 16:16:27'),
(9,' Ynez',9,'产品经理',74.24,'深卡其布色','2015-06-20 03:31:47','2014-10-01 23:10:43'),
(10,' Dieter',10,'系统顾问',98.12,'浅灰色','2011-12-15 19:42:42','2019-05-17 07:49:51'),
(11,' Nickie',11,'策划人员',130.47,'黄色','2015-05-28 15:45:40','2018-01-09 08:19:55'),
(12,' Hartley',12,'销售主管',14.51,'深灰色','2005-06-15 02:55:56','2013-07-10 07:28:49'),
(13,' Kaleena',13,'研发工程师',74.58,'黑色','2004-04-29 10:31:16','2001-09-21 05:10:21'),
(14,' Teressa',14,'法律顾问',34,'紫色','2005-05-23 17:02:20','2004-10-25 01:56:29'),
(15,' Sammy',15,'产品经理',68.6,'酒红色','2016-06-26 17:45:03','2003-11-25 08:18:34'),
(16,' Bernhard',16,'秘书',43.65,'绿色','2008-03-31 21:30:19','2013-09-12 21:29:15'),
(17,' Pattie',17,'工程师',147.56,'蓝色','2011-11-05 21:13:03','2007-08-28 05:20:13'),
(18,' Adella',18,'工程师',74.96,'天蓝色','2001-06-04 20:36:37','2012-06-12 00:35:45'),
(19,' Jacob',19,'保安',184.48,'黄色','2003-10-01 03:16:59','2012-09-28 14:07:24'),
(20,' Fons',20,'产品经理',102.69,'紫罗兰','2017-08-26 14:59:19','2013-02-05 14:26:36');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
