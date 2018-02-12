/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : watchdog

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-02-13 07:35:32
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
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor
-- ----------------------------
INSERT INTO `monitor` VALUES ('83', 'ping 127.0.0.1', 'PING', '60000', '2018-02-06 16:16:18', '2018-02-06 16:16:18');
INSERT INTO `monitor` VALUES ('89', 'ping 127.0.0.1', 'TELNET', '60000', '2018-02-12 13:44:32', '2018-02-12 13:44:32');
INSERT INTO `monitor` VALUES ('93', 'baidu.com', 'HTTP', '60000', '2018-02-12 21:42:04', '2018-02-12 21:42:04');

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
