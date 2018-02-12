/*
Navicat MariaDB Data Transfer

Source Server         : localhost
Source Server Version : 100113
Source Host           : localhost:3306
Source Database       : watchdog

Target Server Type    : MariaDB
Target Server Version : 100113
File Encoding         : 65001

Date: 2018-02-12 18:08:00
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor
-- ----------------------------
INSERT INTO `monitor` VALUES ('83', 'ping 127.0.0.1', 'PING', '60000', '2018-02-06 16:16:18', '2018-02-06 16:16:18');
INSERT INTO `monitor` VALUES ('89', 'ping 127.0.0.1', 'TELNET', '60000', '2018-02-12 13:44:32', '2018-02-12 13:44:32');

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
