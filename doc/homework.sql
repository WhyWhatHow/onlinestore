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

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cid` int(11) NOT NULL COMMENT '分类编号',
  `cname` varchar(32) DEFAULT NULL COMMENT '分类名称',
  `parentId` int(11) DEFAULT NULL COMMENT '父类分类id',
  `location` varchar(100) DEFAULT NULL COMMENT '不知道呢..',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `category` */

insert  into `category`(`cid`,`cname`,`parentId`,`location`) values (1,'手机数码',0,''),(2,'热门手机',1,''),(3,'热门电脑',1,''),(4,'数码配件',1,''),(5,'手机特惠',1,''),(6,'图书音像',0,''),(10,'小米手机',2,NULL),(11,'苹果手机',2,NULL);

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

/*Data for the table `item` */

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `uri` varchar(100) DEFAULT NULL COMMENT '权限所在路径',
  `parentId` int(11) DEFAULT '0' COMMENT '父类权限ip',
  `type` int(11) DEFAULT NULL COMMENT 'uri类型本地or 远程',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

/*Data for the table `menu` */

insert  into `menu`(`id`,`name`,`uri`,`parentId`,`type`) values (1,'分类管理',NULL,0,NULL),(2,'添加分类',NULL,1,NULL),(3,'删除分类',NULL,1,NULL),(4,'修改分类',NULL,1,NULL),(5,'商品管理',NULL,0,NULL),(6,'添加商品',NULL,5,NULL),(7,'修改商品',NULL,5,NULL),(8,'删除商品',NULL,5,NULL),(9,'用户管理',NULL,0,NULL),(10,'添加用户',NULL,9,NULL),(11,'修改用户',NULL,9,NULL),(12,'删除用户',NULL,9,NULL),(13,'角色管理',NULL,0,NULL),(14,'添加角色',NULL,13,NULL),(15,'修改角色',NULL,13,NULL),(16,'删除角色',NULL,13,NULL),(17,'权限管理',NULL,0,NULL),(18,'增加权限',NULL,17,NULL),(19,'删除权限',NULL,17,NULL),(20,'修改权限',NULL,17,NULL),(21,'订单管理',NULL,0,NULL),(22,'查看角色',NULL,13,NULL),(23,'查看用户',NULL,9,NULL),(24,'查看商品',NULL,5,NULL),(25,'查询分类',NULL,1,NULL),(26,'查询权限',NULL,17,NULL);

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `oid` varchar(32) NOT NULL,
  `uid` varchar(32) DEFAULT NULL,
  `ordertime` datetime DEFAULT NULL,
  `total` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL COMMENT '收货地址',
  `telephone` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `uid` (`uid`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `order` */

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
  `location` varchar(100) DEFAULT NULL COMMENT '图片或者视频地址',
  `create_time` datetime DEFAULT NULL COMMENT '入库时间',
  `cname` varchar(32) DEFAULT NULL COMMENT '分类编号',
  PRIMARY KEY (`pid`),
  KEY `cid` (`cid`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `product` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `rname` varchar(32) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `role` */

insert  into `role`(`rid`,`rname`) values (1,'admin'),(2,'shopper'),(3,'member');

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `rid` int(11) DEFAULT NULL COMMENT '角色id',
  `mid` int(11) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`),
  KEY `mid` (`mid`),
  CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`),
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

/*Data for the table `role_menu` */

insert  into `role_menu`(`id`,`rid`,`mid`) values (1,1,1),(2,1,2),(3,2,3),(4,1,3),(5,1,4),(6,1,5),(7,1,6),(8,1,7),(9,1,8),(10,1,9),(11,2,6),(12,2,3),(13,2,4),(14,2,5),(15,2,6),(16,2,7),(17,2,8),(18,2,9);

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
  `state` tinyint(1) DEFAULT '0' COMMENT '激活状态',
  `code` varchar(64) DEFAULT NULL COMMENT '激活码',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `rid` int(11) DEFAULT NULL COMMENT '用户角色',
  PRIMARY KEY (`uid`),
  KEY `rid` (`rid`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`,`password`,`email`,`gender`,`login_name`,`level`,`real_name`,`birthday`,`telphone`,`address`,`state`,`code`,`update_time`,`create_time`,`rid`) values ('a1246488712131','lucy','aa12321.',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
