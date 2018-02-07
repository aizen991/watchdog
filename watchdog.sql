/*
Navicat MariaDB Data Transfer

Source Server         : localhost
Source Server Version : 100113
Source Host           : localhost:3306
Source Database       : watchdog

Target Server Type    : MariaDB
Target Server Version : 100113
File Encoding         : 65001

Date: 2018-02-06 19:58:42
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor
-- ----------------------------
INSERT INTO `monitor` VALUES ('83', 'ping 127.0.0.1', 'PING', '5000', '2018-02-06 16:16:18', '2018-02-06 16:16:18');

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
