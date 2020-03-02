/*
 Navicat Premium Data Transfer

 Source Server         : docker_mysql_5.7.26
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : springboot_aop

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 02/03/2020 09:38:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`springboot_aop` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `type` varchar(255) DEFAULT NULL COMMENT '日志类型',
  `title` varchar(255) DEFAULT NULL COMMENT '日志标题',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(255) DEFAULT NULL COMMENT '操作方式',
  `params` varchar(255) DEFAULT NULL COMMENT '操作提交的数据',
  `time` bigint(20) DEFAULT NULL COMMENT '执行时间',
  `exception` varchar(255) DEFAULT NULL COMMENT '异常信息',
  `service_id` varchar(255) DEFAULT NULL COMMENT '服务ID',
  `del_flag` varchar(255) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` VALUES (1232971632285143041, '0', '查询用户', NULL, NULL, NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '/user/4', 'GET', '', 464, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (1232974159231029250, '0', '查询用户', NULL, NULL, NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '/user/4', 'GET', '', 13, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (1232974325472272385, '0', '查询用户', NULL, NULL, NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', '/user/4', 'GET', '', 486, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (4, 'haha=nger', 20, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
