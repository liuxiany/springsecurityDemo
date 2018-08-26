-- ----------------------------
-- Table structure for admin_authority
-- ----------------------------
DROP TABLE IF EXISTS `admin_authority`;
CREATE TABLE `admin_authority` (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `authority_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限名称',
  `authotiry_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限的url',
  `description` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限描述',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_authority
-- ----------------------------
INSERT INTO `admin_authority` VALUES ('1', 'user', '/user', '用户主页', '2018-08-27 00:00:41');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `role_value` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色值',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_value` (`role_value`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', '管理员', 'ROLE_ADMIN', '2018-08-26 15:24:37', '管理员角色');
INSERT INTO `admin_role` VALUES ('2', '用户', 'ROLE_USER', '2018-08-26 15:24:43', '用户角色');

-- ----------------------------
-- Table structure for admin_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_authority`;
CREATE TABLE `admin_role_authority` (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `role_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `authority_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `authority_id` (`authority_id`),
  CONSTRAINT `admin_role_authority_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `admin_role` (`id`),
  CONSTRAINT `admin_role_authority_ibfk_2` FOREIGN KEY (`authority_id`) REFERENCES `admin_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role_authority
-- ----------------------------
INSERT INTO `admin_role_authority` VALUES ('1', '2', '1', '2018-08-26 23:41:01');

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `role_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `admin_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `admin_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', 'liuxiany', '{bcrypt}$2a$10$CZllN4b7vXQTsqCSWnrNiOgcuP6o8Q.H7nfYTkXUdRxe3JTMMJGL.', 'jack', '2018-08-26 17:17:38', '2');
