/*
Navicat MariaDB Data Transfer

Source Server         : localhost
Source Server Version : 100113
Source Host           : localhost:3306
Source Database       : watchdog

Target Server Type    : MariaDB
Target Server Version : 100113
File Encoding         : 65001

Date: 2018-02-22 16:54:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for http_monitor
-- ----------------------------
DROP TABLE IF EXISTS `http_monitor`;
CREATE TABLE `http_monitor` (
  `id` bigint(20) NOT NULL,
  `url` varchar(500) NOT NULL,
  `timeout` int(10) NOT NULL,
  `method` varchar(6) NOT NULL,
  `request_params` varchar(500) DEFAULT NULL,
  `userid` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `user_agent` varchar(500) DEFAULT NULL,
  `http_header` varchar(500) DEFAULT NULL,
  `should_contain` varchar(20) DEFAULT NULL,
  `should_not_contain` varchar(20) DEFAULT NULL,
  `case_sensitive` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of http_monitor
-- ----------------------------
INSERT INTO `http_monitor` VALUES ('93', 'https://www.baidu.com/', '3000', 'GET', null, null, null, null, null, '使得法国', null, 'NO');

-- ----------------------------
-- Table structure for http_url_monitor
-- ----------------------------
DROP TABLE IF EXISTS `http_url_monitor`;
CREATE TABLE `http_url_monitor` (
  `id` bigint(20) NOT NULL,
  `url` varchar(500) NOT NULL,
  `timeout` int(10) NOT NULL,
  `method` varchar(6) NOT NULL,
  `request_params` varchar(500) DEFAULT NULL,
  `userid` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `http_condition` varchar(6) NOT NULL,
  `http_value` int(6) NOT NULL,
  `user_agent` varchar(500) DEFAULT NULL,
  `http_header` varchar(500) DEFAULT NULL,
  `should_contain` varchar(20) DEFAULT NULL,
  `should_not_contain` varchar(20) DEFAULT NULL,
  `case_sensitive` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of http_url_monitor
-- ----------------------------

-- ----------------------------
-- Table structure for memcached_monitor
-- ----------------------------
DROP TABLE IF EXISTS `memcached_monitor`;
CREATE TABLE `memcached_monitor` (
  `id` bigint(20) NOT NULL,
  `host` varchar(20) NOT NULL,
  `port` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of memcached_monitor
-- ----------------------------
INSERT INTO `memcached_monitor` VALUES ('94', '127.0.0.1', '11211');

-- ----------------------------
-- Table structure for monitor
-- ----------------------------
DROP TABLE IF EXISTS `monitor`;
CREATE TABLE `monitor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `type` varchar(20) NOT NULL COMMENT '监控器类型，可选值HTTP,PING,',
  `polling_interval` int(10) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor
-- ----------------------------
INSERT INTO `monitor` VALUES ('82', 'mysql localhost', 'MYSQL', '60000', '2018-02-22 11:25:39', '2018-02-22 11:25:42');
INSERT INTO `monitor` VALUES ('83', 'ping 127.0.0.1', 'PING', '60000', '2018-02-06 16:16:18', '2018-02-06 16:16:18');
INSERT INTO `monitor` VALUES ('89', 'ping 127.0.0.1', 'TELNET', '60000', '2018-02-12 13:44:32', '2018-02-12 13:44:32');
INSERT INTO `monitor` VALUES ('93', 'baidu.com', 'HTTP', '60000', '2018-02-12 21:42:04', '2018-02-12 21:42:04');
INSERT INTO `monitor` VALUES ('94', 'memcache localhost', 'MEMCACHED', '60000', '2018-02-13 14:56:13', '2018-02-13 14:56:16');
INSERT INTO `monitor` VALUES ('95', 'redis localhost', 'REDIS', '60000', '2018-02-14 15:55:34', '2018-02-14 15:55:37');

-- ----------------------------
-- Table structure for mysql_monitor
-- ----------------------------
DROP TABLE IF EXISTS `mysql_monitor`;
CREATE TABLE `mysql_monitor` (
  `id` bigint(20) NOT NULL,
  `host` varchar(20) NOT NULL COMMENT 'mysql主机地址',
  `port` int(10) NOT NULL COMMENT '访问端口号',
  `username` varchar(20) NOT NULL COMMENT '数据库用户名',
  `password` varchar(50) NOT NULL COMMENT '数据库密码',
  `base_dir` varchar(50) DEFAULT NULL COMMENT 'mysql主目录地址',
  `data_dir` varchar(50) DEFAULT NULL COMMENT '数据目录路径',
  `server` varchar(10) NOT NULL COMMENT '类型，MASTER,SLAVE,STANDALONE',
  `host_name` varchar(20) DEFAULT NULL COMMENT 'mysql主机名称',
  `os` varchar(20) DEFAULT NULL COMMENT 'mysql主机操作系统',
  `version` varchar(60) NOT NULL COMMENT 'mysql服务器版本',
  `encoding` varchar(10) NOT NULL COMMENT 'mysql服务器版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mysql_monitor
-- ----------------------------
INSERT INTO `mysql_monitor` VALUES ('82', '127.0.0.1', '3306', 'root', '111111', 'C:\\Program Files\\MariaDB 10.1', 'C:\\Program Files\\MariaDB 10.1\\data', 'STANDALONE', '01AD58697812703', 'windows', '10.1.13-MariaDB mariadb.org binary distribution', 'utf-8');

-- ----------------------------
-- Table structure for ping_monitor
-- ----------------------------
DROP TABLE IF EXISTS `ping_monitor`;
CREATE TABLE `ping_monitor` (
  `id` bigint(20) NOT NULL,
  `host` varchar(20) NOT NULL,
  `timeout` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ping_monitor
-- ----------------------------
INSERT INTO `ping_monitor` VALUES ('83', '127.0.0.1', '50');

-- ----------------------------
-- Table structure for redis_monitor
-- ----------------------------
DROP TABLE IF EXISTS `redis_monitor`;
CREATE TABLE `redis_monitor` (
  `id` bigint(20) NOT NULL,
  `host` varchar(20) NOT NULL,
  `port` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of redis_monitor
-- ----------------------------
INSERT INTO `redis_monitor` VALUES ('95', '127.0.0.1', '6379');

-- ----------------------------
-- Table structure for telnet_monitor
-- ----------------------------
DROP TABLE IF EXISTS `telnet_monitor`;
CREATE TABLE `telnet_monitor` (
  `id` bigint(20) NOT NULL,
  `host` varchar(20) NOT NULL,
  `port` int(10) NOT NULL,
  `timeout` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of telnet_monitor
-- ----------------------------
INSERT INTO `telnet_monitor` VALUES ('89', '127.0.0.1', '3306', '5000');
