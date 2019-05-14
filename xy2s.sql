/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : xy2s

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-07 16:25:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminid` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin', 'admin', 'admin');
INSERT INTO `admin` VALUES ('20180407161027', 'admin123', '123', 'admin123', '13589458652');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `articleid` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `contents` varchar(255) DEFAULT NULL,
  `addtime` varchar(255) DEFAULT NULL,
  `hits` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('20180316102355', 'NBA 勇士骑士火箭马刺 季后赛现场球迷助威 运动T恤', 'upfiles/20180316102351.jpg', '<p>NBA 勇士骑士火箭马刺 季后赛现场球迷助威 运动T恤</p>\r\n', '2018-03-18', '1');
INSERT INTO `article` VALUES ('20180407161857', '测试', 'upfiles/20180407161850.jpg', '<p>测试</p>\r\n', '2018-04-07', '1');

-- ----------------------------
-- Table structure for bbs
-- ----------------------------
DROP TABLE IF EXISTS `bbs`;
CREATE TABLE `bbs` (
  `bbsid` varchar(255) NOT NULL,
  `usersid` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `contents` varchar(255) DEFAULT NULL,
  `addtime` varchar(255) DEFAULT NULL,
  `hits` varchar(255) DEFAULT NULL,
  `repnum` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bbsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bbs
-- ----------------------------
INSERT INTO `bbs` VALUES ('20180306180451', '20180306133920', '24342', '<p>喜欢</p>\r\n', '2018-03-18 18:04:51', '0', '0');
INSERT INTO `bbs` VALUES ('20180306180454', '20180306133920', '345345', '<p>完美</p>\r\n', '2018-03-18 18:04:54', '4', '2');
INSERT INTO `bbs` VALUES ('20180306190905', '20180306190806', '66666666666', '<p>我喜欢</p>\r\n', '2018-03-18 19:09:05', '3', '1');
INSERT INTO `bbs` VALUES ('20180316102727', '20180316102701', '555555', '<p>真的好用</p>\r\n', '2018-03-18 10:27:27', '2', '1');
INSERT INTO `bbs` VALUES ('20180320193100', '20180320192329', '6666666666666666666666', '<p>好看</p>\r\n', '2018-03-20 19:31:00', '2', '1');
INSERT INTO `bbs` VALUES ('20180407162136', '20180320192329', '测试留言', '<p>嘻嘻嘻</p>\r\n', '2018-04-07 16:21:36', '0', '0');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `cartid` varchar(255) NOT NULL,
  `usersid` varchar(255) DEFAULT NULL,
  `goodsid` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `addtime` varchar(255) DEFAULT NULL,
  `num` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cartid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for cate
-- ----------------------------
DROP TABLE IF EXISTS `cate`;
CREATE TABLE `cate` (
  `cateid` varchar(255) NOT NULL,
  `catename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cateid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cate
-- ----------------------------
INSERT INTO `cate` VALUES ('20180306130112', '图书');
INSERT INTO `cate` VALUES ('20180306130116', '电子产品');
INSERT INTO `cate` VALUES ('20180306190600', '服装');
INSERT INTO `cate` VALUES ('20180320192445', '化妆品');
INSERT INTO `cate` VALUES ('20180407162023', '体育用品');

-- ----------------------------
-- Table structure for fav
-- ----------------------------
DROP TABLE IF EXISTS `fav`;
CREATE TABLE `fav` (
  `favid` varchar(255) NOT NULL,
  `usersid` varchar(255) DEFAULT NULL,
  `goodsid` varchar(255) DEFAULT NULL,
  `addtime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`favid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fav
-- ----------------------------
INSERT INTO `fav` VALUES ('20180306151438', '20180306133920', '20180306150533', '2018-03-18');
INSERT INTO `fav` VALUES ('20180306164853', '20180306133920', '20180306150335', '2018-03-18');
INSERT INTO `fav` VALUES ('20180306190824', '20180306190806', '20180306150554', '2018-03-18');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodsid` varchar(255) NOT NULL,
  `usersid` varchar(255) DEFAULT NULL,
  `goodsname` varchar(255) DEFAULT NULL,
  `cateid` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `marketprice` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `addtime` varchar(255) DEFAULT NULL,
  `hits` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `contents` varchar(255) DEFAULT NULL,
  `storage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`goodsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('20180306150335', '20180306133920', '昕薇杂志', '20180306130112', 'upfiles/20180306150317.jpg', '25', '15', '2018-03-18', '6', '上架', '<p>昕薇杂志</p>\r\n', '10');
INSERT INTO `goods` VALUES ('20180306150407', '20180306133920', '世界时装之苑 ELLE 杂志', '20180306130112', 'upfiles/20180306150342.jpg', '25', '15', '2018-03-18', '1', '上架', '<p>世界时装之苑 ELLE 杂志</p>\r\n', '20');
INSERT INTO `goods` VALUES ('20180306150422', '20180306133920', '时尚伊人cosmo杂志', '20180306130112', 'upfiles/20180306150413.jpg', '25', '15', '2018-03-18', '2', '上架', '<p>时尚伊人cosmo杂志</p>\r\n', '30');
INSERT INTO `goods` VALUES ('20180306150437', '20180306133920', '时尚芭莎BAZAA上半月杂志', '20180306130112', 'upfiles/20180306150427.jpg', '25', '15', '2018-03-18', '0', '上架', '<p>时尚芭莎BAZAA上半月杂志</p>\r\n', '40');
INSERT INTO `goods` VALUES ('20180306150533', '20180306133920', 'Apple iPad Pro 平板电脑', '20180306130116', 'upfiles/20180306150513.png', '5399', '4599', '2018-03-18', '22', '上架', '<p>Apple iPad Pro 平板电脑 10.5 英寸（256G WLAN版 A10X芯片 Retina屏 Multi-Touch技术 MPDY2CH）深空灰色</p>\r\n', '15');
INSERT INTO `goods` VALUES ('20180306150554', '20180306133920', 'Apple iPad 平板电脑', '20180306130116', 'upfiles/20180306150538.jpg', '5699', '4999', '2018-03-18', '4', '上架', '<p>Apple iPad 平板电脑 9.7英寸 128G WLAN版 A9 芯片 Retina显示屏</p>\r\n', '17');
INSERT INTO `goods` VALUES ('20180306164727', '20180306133920', '悦己SELF 双刊双封面随机发送', '20180306130116', 'upfiles/20180306145951.jpg', '79', '29', '2018-03-18', '3', '上架', '<p>悦己SELF 双刊双封面随机发送</p>\r\n', '35');
INSERT INTO `goods` VALUES ('20180306190712', '20180306190635', 'Gap纯棉百搭小格纹单袋长袖衬衫', '20180306190600', 'upfiles/20180306190703.jpg', '155', '80', '2018-03-18', '4', '上架', '<p>Gap纯棉百搭小格纹单袋长袖衬衫</p>\r\n', '40');
INSERT INTO `goods` VALUES ('20180306190730', '20180306190635', 'Seno商务休闲长袖衬衫', '20180306190600', 'upfiles/20180306190718.jpg', '299', '100', '2018-03-18', '0', '下架', '<p>Seno商务休闲长袖衬衫</p>\r\n', '55');
INSERT INTO `goods` VALUES ('20180316015933', '20180306133920', '名流都会短袖衬衫', '20180306190600', 'upfiles/20180316015922.jpg', '179', '59', '2018-03-18', '2', '上架', '<p>名流都会短袖衬衫</p>\r\n', '30');
INSERT INTO `goods` VALUES ('20180316023757', '20180306133920', '夏装新款女士衬衫', '20180306190600', 'upfiles/20180316023738.jpg', '99', '69', '2018-03-18', '0', '上架', '<p>森马短袖衬衫2016夏装新款女士衬衫</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180316023819', '20180306133920', '方领绣花长袖衬衣', '20180306190600', 'upfiles/20180316023803.jpg', '99', '69', '2018-03-18', '0', '上架', '<p>茵曼2016新款方领绣花长袖衬衣</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180316023844', '20180306133920', '红豆 春款衬衫', '20180306190600', 'upfiles/20180316023830.jpg', '99', '79', '2018-03-18', '0', '上架', '<p>红豆（Hodo）男装 2016春款衬衫</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180316025217', '20180306133920', 'NBA 季后赛slogan系列', '20180306190600', 'upfiles/20180316025200.png', '799', '599', '2018-03-18', '1', '上架', '<p>NBA 季后赛slogan系列 勇士骑士火箭公牛快船 运动短袖T恤</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180316025244', '20180306133920', 'NBA 金州勇士队运动休闲T恤', '20180306190600', 'upfiles/20180316025223.jpg', '799', '699', '2018-03-18', '0', '上架', '<p>NBA 金州勇士队 2017总冠军里程碑系列 运动休闲T恤</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180316025308', '20180306133920', '秋冬连帽休闲卫衣外套', '20180306190600', 'upfiles/20180316025250.jpg', '799', '699', '2018-03-18', '0', '上架', '<p>NBA 库里杜兰特罗斯秋冬连帽休闲卫衣外套</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180316025328', '20180306133920', 'NBA 库里秋冬连帽休闲卫衣外套男', '20180306190600', 'upfiles/20180316025314.jpg', '799', '699', '2018-03-18', '0', '上架', '<p>NBA 库里秋冬连帽休闲卫衣外套男</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180316025448', '20180306133920', 'NBA 骑士 迷彩系列', '20180306190600', 'upfiles/20180316025436.jpg', '799', '699', '2018-03-18', '0', '上架', '<p>NBA 骑士 迷彩系列 长袖圆领篮球T恤男 运动休闲</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180316025506', '20180306133920', 'NBA 骑士队 詹姆斯', '20180306190600', 'upfiles/20180316025455.jpg', '799', '699', '2018-03-18', '1', '上架', '<p>NBA 骑士队 詹姆斯 夏季篮球运动T恤 休闲短袖GT藏青</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180316025524', '20180306133920', 'NBA 球队款', '20180306190600', 'upfiles/20180316025512.jpg', '799', '699', '2018-03-18', '1', '上架', '<p>NBA 球队款 NOT IN MY HOUSE T恤 运动休闲短袖</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180316025544', '20180306133920', '2017 总冠军新纪录系列', '20180306190600', 'upfiles/20180316025531.jpg', '799', '699', '2018-03-18', '0', '上架', '<p>NBA 勇士队 2017 总冠军新纪录系列 运动休闲纪念T恤</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180316025602', '20180306133920', '2017总冠军 荣誉系列', '20180306190600', 'upfiles/20180316025550.jpg', '799', '699', '2018-03-18', '0', '上架', '<p>NBA 勇士队 2017总冠军 荣誉系列 运动休闲纪念T恤</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180316102848', '20180316102701', 'lol游戏键盘鼠标套装', '20180306130116', 'upfiles/20180316102824.jpg', '199', '99', '2018-03-18', '5', '上架', '<p>lol游戏键盘鼠标套装</p>\r\n', '30');
INSERT INTO `goods` VALUES ('20180316103002', '20180316102701', '87键机械合金版游戏背光键盘', '20180306130116', 'upfiles/20180316102942.jpg', '399', '199', '2018-03-18', '5', '上架', '<p>达尔优（dare-u）87键机械合金版游戏背光键盘</p>\r\n', '94');
INSERT INTO `goods` VALUES ('20180320192519', '20180320192329', '香奈儿邂逅清新淡香水', '20180320192445', 'upfiles/20180320192504.jpg', '260', '190', '2018-03-20', '2', '上架', '<p>香奈儿邂逅清新淡香水50ml新老包装随机发放</p>\r\n', '99');
INSERT INTO `goods` VALUES ('20180320192547', '20180320192329', '香奈儿可可小姐淡香水 50m', '20180320192445', 'upfiles/20180320192535.jpg', '799', '499', '2018-03-20', '0', '上架', '<p>香奈儿可可小姐淡香水 50m</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180320192608', '20180320192329', '范思哲（VERSACE）幻影金钻', '20180320192445', 'upfiles/20180320192556.jpg', '599', '299', '2018-03-20', '1', '上架', '<p>范思哲（VERSACE）幻影金钻</p>\r\n', '100');
INSERT INTO `goods` VALUES ('20180320192639', '20180320192329', '香奈儿香水女士淡香氛', '20180320192445', 'upfiles/20180320192622.jpg', '899', '699', '2018-03-20', '0', '上架', '<p>Chanel香奈儿香水女士淡香氛 粉色邂逅</p>\r\n', '100');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `ordersid` varchar(255) NOT NULL,
  `ordercode` varchar(255) DEFAULT NULL,
  `usersid` varchar(255) DEFAULT NULL,
  `sellerid` varchar(255) DEFAULT NULL,
  `goodsid` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `addtime` varchar(255) DEFAULT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `num` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ordersid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1e7cb27dd7c243d0ac0e820b90bf1f31', 'PD20180320192916812', '20180320192329', '20180316102701', '20180316103002', '199', '已收货', '2018-03-20', '666', '666', '666', '1');
INSERT INTO `orders` VALUES ('264bd6844d9f470294ceb6cb4a668a33', 'PD20180316103046545', '20180316102547', '20180316102701', '20180316103002', '199', '已收货', '2018-03-18', '555', '555', '555', '5');
INSERT INTO `orders` VALUES ('a611e7c2f74847719df1b86b53208e81', 'PD20180306172304864', '20180306172040', '20180306133920', '20180306150533', '4599', '已发货', '2018-03-18', '234234', '234234', '234234', '1');
INSERT INTO `orders` VALUES ('adb603e3df9644baac9d65198f70ca86', 'PD20180306190839150', '20180306190806', '20180306133920', '20180306150554', '4999', '已发货', '2018-03-18', '333', '333', '333', '1');
INSERT INTO `orders` VALUES ('b5346a28492346f39bfb8aeb42dff9f8', 'PD20180306190839150', '20180306190806', '20180306190635', '20180306190712', '80', '已收货', '2018-03-18', '333', '333', '333', '1');
INSERT INTO `orders` VALUES ('de1e44c823494d40a9491bc2bdcfaead', 'PD20180320192916812', '20180320192329', '20180316102701', '20180316102848', '99', '未付款', '2018-03-20', '666', '666', '666', '1');
INSERT INTO `orders` VALUES ('e263652ea93542098bd5ac9dad97d8a6', 'PD20180407160638195', '20180407160524', '20180320192329', '20180320192519', '190', '已收货', '2018-04-07', '用户', '13589458652', '湖北武汉', '1');
INSERT INTO `orders` VALUES ('efbbc394ccad4efbb577a8a0bf4bcf3e', 'PD20180316095058171', '20180306133920', '20180306133920', '20180306150533', '4599', '未付款', '2018-03-18', '123123', '123123', '123123', '1');
INSERT INTO `orders` VALUES ('f2d51ab8b77548cf9ccda1a3f7c803c1', 'PD20180306172304864', '20180306172040', '20180306133920', '20180306164727', '29', '未付款', '2018-03-18', '234234', '234234', '234234', '1');
INSERT INTO `orders` VALUES ('f894b38db11842e39761eb101b1c4275', 'PD20180316103046545', '20180316102547', '20180316102701', '20180316102848', '99', '未付款', '2018-03-18', '555', '555', '555', '2');

-- ----------------------------
-- Table structure for rebbs
-- ----------------------------
DROP TABLE IF EXISTS `rebbs`;
CREATE TABLE `rebbs` (
  `rebbsid` varchar(255) NOT NULL,
  `usersid` varchar(255) DEFAULT NULL,
  `bbsid` varchar(255) DEFAULT NULL,
  `contents` varchar(255) DEFAULT NULL,
  `addtime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rebbsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rebbs
-- ----------------------------
INSERT INTO `rebbs` VALUES ('20180306172803', '20180306172040', '20180306172758', '<p>漂亮</p>\r\n', '2018-11-18 17:28:03');
INSERT INTO `rebbs` VALUES ('20180306180459', '20180306133920', '20180306180454', '<p>我喜欢</p>\r\n', '2018-11-18 17:28:03');
INSERT INTO `rebbs` VALUES ('20180306190911', '20180306190806', '20180306190905', '<p>下次还来买</p>\r\n', '2018-11-18 17:28:03');
INSERT INTO `rebbs` VALUES ('20180306190921', '20180306190806', '20180306180454', '<p>我喜欢</p>\r\n', '2018-12-18 17:28:03');
INSERT INTO `rebbs` VALUES ('20180316102733', '20180316102701', '20180316102727', '<p>真的好用</p>\r\n', '2018-11-18 17:28:03');
INSERT INTO `rebbs` VALUES ('20180320193105', '20180320192329', '20180320193100', '<p>好用</p>\r\n', '2018-12-20 19:31:05');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `topicid` varchar(255) NOT NULL DEFAULT '',
  `usersid` varchar(255) DEFAULT NULL,
  `goodsid` varchar(255) DEFAULT NULL,
  `num` varchar(255) DEFAULT NULL,
  `contents` text,
  `addtime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`topicid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('20180320130135', '20180306133920', '20180306150335', '5', '123123', '2018-11-20');
INSERT INTO `topic` VALUES ('20180320192726', '20180320192329', '20180316102848', '5', '123123', '2018-11-20');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `usersid` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `regdate` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`usersid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('20180306133920', '云天明', '123123', '123123', '男', '2018-03-16', '123123', '123123', 'upfiles/20180306133918.jpg', '2018-03-18', '解锁', '会员');
INSERT INTO `users` VALUES ('20180306190635', '222', '222', '222', '男', '2018-03-16', '222', '222', 'upfiles/20180306190633.jpg', '2018-03-18', '解锁', '会员');
INSERT INTO `users` VALUES ('20180306190806', '333', '333', '333', '男', '2018-03-16', '333', '333', 'upfiles/20180306190804.jpg', '2018-03-18', '解锁', '会员');
INSERT INTO `users` VALUES ('20180316102547', '程欣', '444', '444', '女', '2018-03-16', '444', '444', 'upfiles/20180316102543.jpg', '2018-03-18', '解锁', '会员');
INSERT INTO `users` VALUES ('20180316102701', '罗辑', '555', '6666', '男', '2018-03-16', '555', '555', 'upfiles/20180316102700.jpg', '2018-03-18', '解锁', '会员');
INSERT INTO `users` VALUES ('20180320192329', '韦德', '666', '666', '男', '2018-03-20', '666', '666', 'upfiles/20180320192327.jpg', '2018-03-20', '解锁', '商家');
INSERT INTO `users` VALUES ('20180407154515', 'shangjia', '123', 'shangjia', '男', '2018-04-02', '13589458652', '武汉', 'upfiles/20180407154459.jpg', '2018-04-07', '解锁', '商家');
INSERT INTO `users` VALUES ('20180407160524', 'yonghu', '123', '用户', '男', '2018-04-02', '13654261589', '湖北武汉', 'upfiles/20180407160521.jpg', '2018-04-07', '解锁', '会员');
INSERT INTO `users` VALUES ('20180407162231', 'shangjia123', '123', 'shangjia123', '男', '2018-04-02', '15471651248', '北京', 'upfiles/20180407162229.jpg', '2018-04-07', '解锁', '商家');
