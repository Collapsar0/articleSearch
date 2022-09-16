/*
Navicat MySQL Data Transfer

Source Server         : erhuoduoduo_platform
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : erhuoduoduo_platform

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2021-12-27 03:27:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comments_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价ID',
  `comment_order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `comments_date` datetime DEFAULT NULL COMMENT '评价时间',
  `comment_users_account` varchar(255) DEFAULT NULL COMMENT '评价的用户账号',
  `commented_users_account` varchar(255) DEFAULT NULL COMMENT '被评价的用户账号',
  `Comments_star` double(16,1) DEFAULT NULL COMMENT '评价星级',
  PRIMARY KEY (`comments_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '2021-12-08 00:46:33', 'aaa', 'bbb', '3.0');
INSERT INTO `comment` VALUES ('2', '2', '2021-12-08 00:46:51', 'aaa', 'bbb', '4.0');
INSERT INTO `comment` VALUES ('3', '3', '2021-12-08 00:47:07', 'bbb', 'aaa', '1.0');
INSERT INTO `comment` VALUES ('4', '26', '2021-12-14 23:36:17', 'testuser', 'aaaaaaa', '3.0');
INSERT INTO `comment` VALUES ('5', '24', '2021-12-14 16:10:04', 'testuser', 'aaaaaaa', '3.5');
INSERT INTO `comment` VALUES ('6', '24', '2021-12-14 16:14:33', 'testuser', 'aaaaaaa', '3.5');

-- ----------------------------
-- Table structure for communication
-- ----------------------------
DROP TABLE IF EXISTS `communication`;
CREATE TABLE `communication` (
  `communication_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '聊天室id',
  `user_to_account` varchar(255) DEFAULT NULL COMMENT '接收方账号',
  `user_from_account` varchar(255) DEFAULT NULL COMMENT '发送方账号',
  `communication_sendtime` datetime DEFAULT NULL COMMENT '发送时间',
  `communication_text` varchar(1024) DEFAULT NULL COMMENT '发送的内容',
  PRIMARY KEY (`communication_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of communication
-- ----------------------------
INSERT INTO `communication` VALUES ('1', 'testuser', 'aaa', '2021-12-22 02:09:58', 'text1');
INSERT INTO `communication` VALUES ('2', 'aaa', 'testuser', '2021-12-07 02:17:49', 'test2');
INSERT INTO `communication` VALUES ('3', 'aaa', 'testuser', '2021-12-30 02:18:16', 'test3');
INSERT INTO `communication` VALUES ('4', 'bbbbb', 'testuser', '2021-12-31 03:04:42', 'work');

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '申诉id',
  `complaint_cause` varchar(255) DEFAULT NULL COMMENT '申诉原因',
  `complaint_time` datetime DEFAULT NULL COMMENT '申诉时间',
  `complaint_type` varchar(255) DEFAULT NULL COMMENT '申诉类型',
  `complaint_state` varchar(255) DEFAULT NULL COMMENT '申诉状态',
  `complaint_user_account` varchar(255) DEFAULT NULL COMMENT '申诉人账号',
  `complainted_user_account` varchar(255) DEFAULT NULL COMMENT '被申诉人账号',
  `complaint_reply` varchar(1024) DEFAULT NULL COMMENT '申诉回复',
  `complaint_evidence_URL` varchar(1024) DEFAULT NULL COMMENT '申诉证据URL',
  PRIMARY KEY (`complaint_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of complaint
-- ----------------------------
INSERT INTO `complaint` VALUES ('1', 'test', '2021-12-14 15:34:48', '恶意下单', '申诉驳回', 'testuser', 'admin123', '不行', 'test');
INSERT INTO `complaint` VALUES ('2', 'test', '2021-12-15 00:43:22', '恶意下单', '申诉中', 'testuser', 'admin123', null, 'test');
INSERT INTO `complaint` VALUES ('3', 'test', '2021-12-15 12:36:03', '恶意下单', '申诉中', 'testuser', 'admin123', null, 'test');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `Goods_id` int(255) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `goods_description` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `goods_price` double(16,2) DEFAULT NULL COMMENT '商品价格',
  `goods_date` datetime DEFAULT NULL COMMENT '上架时间',
  `goods_owner_ID` int(255) DEFAULT NULL COMMENT '商品卖家ID',
  `goods_picture` varchar(255) DEFAULT NULL COMMENT '商品图片URL',
  `goods_status` varchar(255) DEFAULT NULL COMMENT '商品状态',
  `goods_browse` int(255) DEFAULT NULL COMMENT '商品浏览量',
  `goods_type` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '商品类别',
  `goods_owner_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_icelandic_ci DEFAULT NULL COMMENT '商品发布者用户名',
  PRIMARY KEY (`Goods_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '手机', '一个手机', '1000.00', '2021-12-01 10:50:26', '1', '1.png', '上架中', '60', '闲置数码', '张三');
INSERT INTO `goods` VALUES ('2', '相机', '一个相机', '800.00', '2021-12-01 10:52:37', '2', '2.png', '交易中', '50', '闲置数码', '李磊');
INSERT INTO `goods` VALUES ('3', '自行车', '123', '500.00', '2021-12-01 11:11:11', '3', '3.png', '上架中', '20', '校园代步', 'collapsar');
INSERT INTO `goods` VALUES ('4', '电瓶车', '艾玛', '1688.00', '2021-12-01 11:11:11', '3', '6.png', '上架中', '30', '校园代步', 'collapsar');
INSERT INTO `goods` VALUES ('5', '飞机模型', '123', '500.00', '2021-12-01 11:11:11', '3', '3.png', '交易中', '70', '运动棋牌', 'collapsar');
INSERT INTO `goods` VALUES ('6', '口红', 'Dior', '300.00', '2021-12-07 16:36:32', '2', '2.png', '交易中', '68', '美妆衣物', '李磊');
INSERT INTO `goods` VALUES ('7', '高等数学', 'math', '8.00', '2021-12-07 16:37:24', '1', '1.png', '上架中', '10', '图书教材', '张三');
INSERT INTO `goods` VALUES ('8', '演唱会门票', '周杰伦', '1300.00', '2021-12-06 16:38:35', '2', '1.png', '上架中', '95', '票券小物', '李磊');
INSERT INTO `goods` VALUES ('9', '充电宝', '小米', '60.00', '2021-12-03 16:39:35', '6', '2.png', '交易中', '50', '电器日用', 'testa');
INSERT INTO `goods` VALUES ('10', '餐巾纸', 'aaa', '30.00', '2021-12-14 00:02:16', '8', 'dzd', '上架中', '0', '校园代步', '测试者01');
INSERT INTO `goods` VALUES ('11', '游戏手柄', 'aaa', '10.00', '2021-12-14 00:19:27', '6', 'testURL', '上架中', '0', '电器日用', 'testa');
INSERT INTO `goods` VALUES ('12', '蓝牙耳机', 'aaa', '100.00', '2021-12-14 00:20:21', '6', 'testURL', '上架中', '0', '电器日用', 'testa');
INSERT INTO `goods` VALUES ('13', '大学英语四', 'aaa', '5.00', '2021-12-14 00:21:05', '6', 'testURL', '上架中', '0', '图书教材', 'testa');
INSERT INTO `goods` VALUES ('14', '柯南手办', 'aaa', '5.00', '2021-12-14 00:21:49', '6', 'testURL', '上架中', '0', '票券小物', 'testa');
INSERT INTO `goods` VALUES ('15', '衣服1', 'aaa', '5.00', '2021-12-14 00:22:50', '6', 'testURL', '上架中', '0', '美妆衣物', 'testa');
INSERT INTO `goods` VALUES ('16', '衣服2', 'aaa', '5.00', '2021-12-14 00:22:56', '6', 'testURL', '上架中', '0', '美妆衣物', 'testa');
INSERT INTO `goods` VALUES ('17', '衣服3', 'aaa', '5.00', '2021-12-14 00:23:02', '6', 'testURL', '上架中', '0', '美妆衣物', 'testa');
INSERT INTO `goods` VALUES ('18', '衣服4', 'aaa', '5.00', '2021-12-14 00:23:07', '6', 'testURL', '上架中', '0', '美妆衣物', 'testa');
INSERT INTO `goods` VALUES ('19', '衣服5', 'aaa', '5.00', '2021-12-14 00:23:13', '6', 'testURL', '上架中', '0', '美妆衣物', 'testa');
INSERT INTO `goods` VALUES ('20', '衣服6', 'aaa', '5.00', '2021-12-14 00:23:18', '6', 'testURL', '上架中', '0', '美妆衣物', 'testa');
INSERT INTO `goods` VALUES ('21', '衣服7', 'aaa', '5.00', '2021-12-14 00:23:22', '6', 'testURL', '上架中', '0', '美妆衣物', 'testa');
INSERT INTO `goods` VALUES ('22', '衣服8', 'aaa', '5.00', '2021-12-14 00:23:27', '6', 'testURL', '上架中', '0', '美妆衣物', 'testa');
INSERT INTO `goods` VALUES ('23', '衣服9', 'aaa', '5.00', '2021-12-14 00:23:32', '6', 'testURL', '上架中', '0', '美妆衣物', 'testa');
INSERT INTO `goods` VALUES ('24', '衣服10', 'aaa', '5.00', '2021-12-14 00:23:36', '6', 'testURL', '上架中', '0', '美妆衣物', 'testa');
INSERT INTO `goods` VALUES ('25', '足球1', 'aaa', '5.00', '2021-12-14 00:31:30', '2', 'testURL', '上架中', '0', '运动棋牌', '李磊');
INSERT INTO `goods` VALUES ('26', '足球2', 'aaa', '5.00', '2021-12-14 00:31:36', '2', 'testURL', '上架中', '0', '运动棋牌', '李磊');
INSERT INTO `goods` VALUES ('27', '足球3', 'aaa', '5.00', '2021-12-14 00:31:41', '2', 'testURL', '上架中', '0', '运动棋牌', '李磊');
INSERT INTO `goods` VALUES ('28', '足球4', 'aaa', '5.00', '2021-12-14 00:31:46', '2', 'testURL', '上架中', '0', '运动棋牌', '李磊');
INSERT INTO `goods` VALUES ('29', '足球5', 'aaa', '5.00', '2021-12-14 00:31:50', '2', 'testURL', '上架中', '0', '运动棋牌', '李磊');
INSERT INTO `goods` VALUES ('30', '足球6', 'aaa', '5.00', '2021-12-14 00:31:54', '2', 'testURL', '上架中', '0', '运动棋牌', '李磊');
INSERT INTO `goods` VALUES ('31', '足球7', 'aaa', '5.00', '2021-12-14 00:31:58', '2', 'testURL', '上架中', '0', '运动棋牌', '李磊');
INSERT INTO `goods` VALUES ('32', '足球8', 'aaa', '5.00', '2021-12-14 00:32:02', '2', 'testURL', '上架中', '0', '运动棋牌', '李磊');
INSERT INTO `goods` VALUES ('33', '足球9', 'aaa', '5.00', '2021-12-14 00:32:06', '2', 'testURL', '交易中', '0', '运动棋牌', '李磊');
INSERT INTO `goods` VALUES ('34', '足球10', 'aaa', '5.00', '2021-12-14 00:32:10', '2', 'testURL', '上架中', '0', '运动棋牌', '李磊');
INSERT INTO `goods` VALUES ('35', '排球1', 'aaa', '5.00', '2021-12-14 00:32:19', '2', 'testURL', '上架中', '0', '运动棋牌', '李磊');
INSERT INTO `goods` VALUES ('36', '排球2', 'aaa', '5.00', '2021-12-14 00:32:24', '2', 'testURL', '上架中', '0', '运动棋牌', '李磊');
INSERT INTO `goods` VALUES ('37', '排球3', 'aaa', '50.00', '2021-12-14 00:32:32', '2', 'testURL', '上架中', '0', '运动棋牌', '李磊');
INSERT INTO `goods` VALUES ('38', '排球3', 'aaa', '50.00', '2021-12-15 12:25:08', '8', 'testURL', '上架中', '0', '运动棋牌', 'test');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_date` datetime DEFAULT NULL COMMENT '下单时间',
  `sell_user_account1` varchar(255) DEFAULT NULL COMMENT '卖家账号',
  `sell_user_account2` varchar(255) DEFAULT NULL COMMENT '买家账号',
  `order_state` varchar(255) DEFAULT NULL COMMENT '订单状态',
  `good_id` int(11) DEFAULT NULL COMMENT '相关商品编号',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '2021-12-06 20:19:11', 'aaa', 'testuser', '已完成', '1');
INSERT INTO `orders` VALUES ('2', '2021-12-07 16:24:20', 'bbb', 'testuser', '已完成', '2');
INSERT INTO `orders` VALUES ('3', '2021-12-07 20:37:58', '31901224@zucc.edu.cn', 'testuser', '卖方已完成', '3');
INSERT INTO `orders` VALUES ('19', '2021-12-14 00:28:04', 'aaaaaaa', 'testuser', '未完成', '11');
INSERT INTO `orders` VALUES ('20', '2021-12-14 00:28:08', 'aaaaaaa', 'testuser', '买方已完成', '12');
INSERT INTO `orders` VALUES ('21', '2021-12-14 00:28:12', 'aaaaaaa', 'testuser', '未完成', '13');
INSERT INTO `orders` VALUES ('22', '2021-12-14 00:28:15', 'aaaaaaa', 'testuser', '已完成', '14');
INSERT INTO `orders` VALUES ('23', '2021-12-14 00:28:19', 'aaaaaaa', 'testuser', '已完成', '15');
INSERT INTO `orders` VALUES ('24', '2021-12-14 00:28:24', 'aaaaaaa', 'testuser', '卖方已完成', '16');
INSERT INTO `orders` VALUES ('25', '2021-12-14 00:28:26', 'aaaaaaa', 'testuser', '卖方已完成', '17');
INSERT INTO `orders` VALUES ('26', '2021-12-14 00:28:29', 'aaaaaaa', 'testuser', '已完成', '18');
INSERT INTO `orders` VALUES ('27', '2021-12-14 00:28:32', 'aaaaaaa', 'testuser', '未完成', '19');
INSERT INTO `orders` VALUES ('28', '2021-12-14 00:28:35', 'aaaaaaa', 'testuser', '卖方已完成', '20');
INSERT INTO `orders` VALUES ('29', '2021-12-14 00:28:39', 'aaaaaaa', 'testuser', '未完成', '21');
INSERT INTO `orders` VALUES ('30', '2021-12-14 00:28:47', 'aaaaaaa', 'testuser', '交易关闭', '22');
INSERT INTO `orders` VALUES ('31', '2021-12-14 00:33:33', 'aaaaaaa', 'bbb', '未完成', '23');
INSERT INTO `orders` VALUES ('32', '2021-12-14 00:33:36', 'aaaaaaa', 'bbb', '未完成', '24');
INSERT INTO `orders` VALUES ('33', '2021-12-14 00:34:34', 'bbb', 'testuser', '已完成', '26');
INSERT INTO `orders` VALUES ('34', '2021-12-14 00:34:38', 'bbb', 'testuser', '未完成', '27');
INSERT INTO `orders` VALUES ('35', '2021-12-14 00:34:41', 'bbb', 'testuser', '买方已完成', '28');
INSERT INTO `orders` VALUES ('36', '2021-12-14 00:34:43', 'bbb', 'testuser', '已完成', '29');
INSERT INTO `orders` VALUES ('37', '2021-12-14 00:34:47', 'bbb', 'testuser', '已完成', '30');
INSERT INTO `orders` VALUES ('38', '2021-12-14 00:34:49', 'bbb', 'testuser', '已完成', '31');
INSERT INTO `orders` VALUES ('39', '2021-12-14 00:34:51', 'bbb', 'testuser', '已完成', '32');
INSERT INTO `orders` VALUES ('40', '2021-12-14 00:34:53', 'bbb', 'testuser', '买方已完成', '33');
INSERT INTO `orders` VALUES ('41', '2021-12-14 00:34:55', 'bbb', 'testuser', '已完成', '34');
INSERT INTO `orders` VALUES ('42', '2021-12-14 00:34:58', 'bbb', 'testuser', '买方已完成', '35');
INSERT INTO `orders` VALUES ('43', '2021-12-14 00:35:00', 'bbb', 'testuser', '买方已完成', '36');
INSERT INTO `orders` VALUES ('45', '2021-12-14 01:06:11', '31901224@zucc.edu.cn', 'testuser', '未完成', '5');
INSERT INTO `orders` VALUES ('46', '2021-12-15 12:38:05', 'bbb', 'testuser', '未完成', '33');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_account` varchar(255) DEFAULT NULL COMMENT '用户账号',
  `user_password` varchar(255) DEFAULT NULL COMMENT '密码',
  `user_nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `user_picture` varchar(255) DEFAULT NULL COMMENT '头像',
  `user_phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `user_state` varchar(255) DEFAULT NULL COMMENT '状态',
  `report_cnt` int(255) DEFAULT NULL COMMENT '被举报次数',
  `user_category` varchar(255) DEFAULT NULL COMMENT '用户类别',
  `user_sex` int(255) DEFAULT NULL COMMENT '性别（0-女,1-男）',
  `user_admission_time` int(255) DEFAULT NULL COMMENT '入学时间',
  `user_major` varchar(255) DEFAULT NULL COMMENT '专业',
  `user_comment` double(16,1) DEFAULT NULL COMMENT '用户评价',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'aaa', '$2a$10$UqeQ1630uJE8JAr30ie5l.Gf8paEL9v6Nf1UuSuUf/.GNkgiDEcV6', '张三', '1.png', null, null, null, null, '0', '2018', '软件工程', null);
INSERT INTO `users` VALUES ('2', 'bbb', 'ccc', '李磊', '1.png', null, null, null, null, '0', '2019', '计算机', null);
INSERT INTO `users` VALUES ('3', '31901224@zucc.edu.cn', '$2a$10$9v6TbMYJeCFmTQ4RsWzoOu.cw/K8LNwbEQ5QZG38m2JPqnTmY7gJ6', 'collapsar', '2.png', '123123123', 'OK', '0', '学生', '1', '2019', '资产评估', null);
INSERT INTO `users` VALUES ('4', 'admin123', '123', '11', '1', '1', '1', '11', '11', '1', '2019', '英语', null);
INSERT INTO `users` VALUES ('5', '123', '123', 'adadada', '', '', '', '0', '', '0', '2020', '德语', null);
INSERT INTO `users` VALUES ('6', 'aaaaaaa', '123', 'testa', '', '123321', '', '0', '', '1', '2018', '资产评估', '3.5');
INSERT INTO `users` VALUES ('7', 'bbbbb', '123', 'test', '', '123321', '', '0', '', '1', '2019', '软件工程', null);
INSERT INTO `users` VALUES ('8', 'testuser', '$2a$10$UKTbTsUfVRshNlLPNuHdl.UYk.7a1Fn4gm40lJ8O3EO3lBwwup/Fm', 'test', '156', '123321', 'good', '0', '测试者', '0', '2019', 'rjgc', null);
INSERT INTO `users` VALUES ('9', 'admin', '$2a$10$jP62hA8D6HJu04StuFFxJ.iyo6Mye3k6fUZv4oUdp/2iGez7YNoEC', '测试者02', '312.png', '123321', 'good', '0', '测试者', '0', '2021', '临床医学', null);
INSERT INTO `users` VALUES ('10', 'admin321', '$2a$10$yc8L2bmPcDJfcJsdnX8JcOB4BOExV1Nxq46J07/aEdtipczgbEhiu', '测试者02', '312.png', '123321', 'good', '0', '测试者', '0', '2020', '英语', null);
INSERT INTO `users` VALUES ('12', '836667853@qq.com', '$2a$10$Py6YpvSZGfqAXRgk6YVMf.GvINfII.KJPeEmhM0dXlpaJCCzgauFe', '测试者02', '312.png', '123321', 'good', '0', '测试者', '1', '2020', '中文', null);
