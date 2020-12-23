/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2020-12-23 08:44:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'test', '$2a$10$E6BcwDLSdVmeH93m/fPte.6UAI1L3nNowKYXCYw6PbyamMAkIxsQG', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 'test@qq.com', '测试账号', null, '2018-09-29 13:55:30', '2018-09-29 13:55:39', '1');
INSERT INTO `admin` VALUES ('3', 'admin', '$2a$10$E6BcwDLSdVmeH93m/fPte.6UAI1L3nNowKYXCYw6PbyamMAkIxsQG', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 'admin@163.com', '系统管理员', '系统管理员', '2018-10-08 13:32:47', '2019-04-20 12:45:16', '1');
INSERT INTO `admin` VALUES ('4', 'macro', '$2a$10$E6BcwDLSdVmeH93m/fPte.6UAI1L3nNowKYXCYw6PbyamMAkIxsQG', 'string', 'macro@qq.com', 'macro', 'macro专用', '2019-10-06 15:53:51', '2020-02-03 14:55:55', '1');
INSERT INTO `admin` VALUES ('6', 'productAdmin', '$2a$10$E6BcwDLSdVmeH93m/fPte.6UAI1L3nNowKYXCYw6PbyamMAkIxsQG', null, 'product@qq.com', '商品管理员', '只有商品权限', '2020-02-07 16:15:08', null, '1');
INSERT INTO `admin` VALUES ('7', 'orderAdmin', '$2a$10$E6BcwDLSdVmeH93m/fPte.6UAI1L3nNowKYXCYw6PbyamMAkIxsQG', null, 'order@qq.com', '订单管理员', '只有订单管理权限', '2020-02-07 16:15:50', null, '1');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `user_id` bigint(20) DEFAULT NULL COMMENT '资源分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='后台资源表';

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '2020-02-04 17:04:55', '测试', '/api/**', null, '3');
INSERT INTO `resource` VALUES ('2', '2020-02-04 17:05:35', '商品属性分类管理', '/productAttribute/**', null, '3');
INSERT INTO `resource` VALUES ('3', '2020-02-04 17:06:13', '商品属性管理', '/productAttribute/**', null, '3');
INSERT INTO `resource` VALUES ('4', '2020-02-04 17:07:15', '商品分类管理', '/productCategory/**', null, '3');
INSERT INTO `resource` VALUES ('5', '2020-02-04 17:09:16', '商品管理', '/product/**', null, '1');
