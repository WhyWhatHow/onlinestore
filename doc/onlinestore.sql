/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.5.27 : Database - onlinestore
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`onlinestore` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `onlinestore`;

/*Table structure for table `cart_item` */

DROP TABLE IF EXISTS `cart_item`;

CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物车项id',
  `uid` varchar(32) DEFAULT NULL COMMENT '用户id',
  `pid` varchar(32) DEFAULT NULL COMMENT '商品id',
  `quantity` int(11) DEFAULT '1' COMMENT '购买数量',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '修改时间',
  `is_finished` tinyint(1) DEFAULT '0' COMMENT '是否结算',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `price` double DEFAULT NULL COMMENT '商品价格',
  `fee` double DEFAULT NULL COMMENT '邮费',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `pid` (`pid`),
  CONSTRAINT `cart_item_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `cart_item_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类编号',
  `cname` varchar(32) DEFAULT NULL COMMENT '分类名称',
  `parentId` int(11) DEFAULT NULL COMMENT '父类分类id',
  `location` varchar(100) DEFAULT NULL COMMENT '不知道呢..',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '分类是否被删除',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `itemid` varchar(32) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `oid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `pid` (`pid`),
  KEY `oid` (`oid`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `item_ibfk_2` FOREIGN KEY (`oid`) REFERENCES `order` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `parentId` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `type` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `oid` varchar(32) NOT NULL,
  `uid` varchar(32) DEFAULT NULL,
  `ordertime` datetime DEFAULT NULL,
  `total` double DEFAULT NULL,
  `state` int(11) DEFAULT '0',
  `address` varchar(100) DEFAULT NULL COMMENT '收货地址',
  `telephone` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `uid` (`uid`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `pid` varchar(32) NOT NULL COMMENT '商品id',
  `pname` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `cid` int(11) DEFAULT NULL COMMENT '分类编号',
  `stock` int(11) DEFAULT NULL COMMENT '库存量',
  `output` int(11) DEFAULT NULL COMMENT '出货量',
  `price` double DEFAULT NULL COMMENT '商品单价',
  `vip_price` double DEFAULT NULL COMMENT '商品会员价',
  `discount` double DEFAULT NULL COMMENT '折扣',
  `info` varchar(300) DEFAULT NULL COMMENT '商品简介(300字以内)',
  `volume` int(11) DEFAULT NULL COMMENT '成交量',
  `view_number` int(11) DEFAULT NULL COMMENT '浏览次数',
  `image_url` varchar(200) DEFAULT NULL COMMENT '图片或者视频地址',
  `create_time` datetime DEFAULT NULL COMMENT '入库时间',
  `cname` varchar(32) DEFAULT NULL COMMENT '分类编号',
  `is_selling` tinyint(1) DEFAULT '0' COMMENT '上下架',
  `position` varchar(200) DEFAULT NULL COMMENT '库存地',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`pid`),
  KEY `cid` (`cid`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `rid` int(11) NOT NULL,
  `rname` varchar(32) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL,
  `mid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` varchar(32) NOT NULL COMMENT '用户编号',
  `username` varchar(32) DEFAULT NULL COMMENT '用户登录名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `email` varchar(32) DEFAULT NULL COMMENT '用户邮箱(可以考虑加一下验证)',
  `gender` varchar(10) DEFAULT NULL COMMENT '用户性别',
  `login_name` varchar(10) DEFAULT NULL COMMENT '用户名',
  `level` int(11) DEFAULT NULL COMMENT '用户级别',
  `real_name` varchar(50) DEFAULT NULL COMMENT '用户真实姓名',
  `birthday` datetime DEFAULT NULL COMMENT '出生年月',
  `telphone` varchar(12) DEFAULT NULL COMMENT '电话',
  `address` varchar(200) DEFAULT NULL COMMENT '收货地址',
  `state` tinyint(1) DEFAULT '0' COMMENT '激活状态 标记用户的激活状态',
  `code` varchar(64) DEFAULT NULL COMMENT '激活码',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `rid` int(11) DEFAULT NULL COMMENT '用户角色',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除该用户',
  `avatar_url` varchar(200) DEFAULT NULL COMMENT '用户头像链接',
  `re_phone` varchar(12) DEFAULT NULL COMMENT '收获电话',
  PRIMARY KEY (`uid`),
  KEY `rid` (`rid`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
