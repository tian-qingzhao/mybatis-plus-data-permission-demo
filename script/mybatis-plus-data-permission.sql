/*
 Navicat Premium Dump SQL

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : mybatis-plus-data-permission

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 24/01/2025 15:34:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父部门id',
  `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (1, '深圳总公司', 1, 1, '15888888888', '2021-01-05 17:03:47', '2025-01-24 10:02:45', b'0');
INSERT INTO `t_dept` VALUES (2, '长沙分公司', 1, 2, '15888888888', '2021-01-05 17:03:47', '2025-01-24 10:02:45', b'0');
INSERT INTO `t_dept` VALUES (3, '研发部门', 2, 1, '15888888888', '2021-01-05 17:03:47', '2025-01-24 10:02:49', b'0');
INSERT INTO `t_dept` VALUES (4, '市场部门', 2, 2, '15888888888', '2021-01-05 17:03:47', '2025-01-24 10:02:49', b'0');
INSERT INTO `t_dept` VALUES (5, '测试部门', 2, 3, '15888888888', '2021-01-05 17:03:47', '2025-01-24 10:02:49', b'0');
INSERT INTO `t_dept` VALUES (6, '财务部门', 2, 4, '15888888888', '2021-01-05 17:03:47', '2025-01-24 10:02:49', b'0');
INSERT INTO `t_dept` VALUES (7, '运维部门', 2, 5, '15888888888', '2021-01-05 17:03:47', '2025-01-24 10:02:49', b'0');
INSERT INTO `t_dept` VALUES (8, '市场部门', 3, 1, '15888888888', '2021-01-05 17:03:47', '2025-01-24 10:02:53', b'0');
INSERT INTO `t_dept` VALUES (9, '财务部门', 3, 2, '15888888888', '2021-01-05 17:03:47', '2025-01-24 10:02:53', b'0');
INSERT INTO `t_dept` VALUES (10, '新部门', 0, 1, NULL, '2022-02-23 20:46:30', '2025-01-24 10:02:37', b'0');
INSERT INTO `t_dept` VALUES (11, '顶级部门', 0, 1, NULL, '2022-03-07 21:44:50', '2025-01-24 10:02:38', b'0');
INSERT INTO `t_dept` VALUES (12, '产品部门', 1, 100, NULL, '2023-12-02 09:45:13', '2025-01-24 10:03:00', b'0');
INSERT INTO `t_dept` VALUES (13, '支持部门', 2, 3, NULL, '2023-12-02 09:47:38', '2025-01-24 10:03:02', b'0');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `dept_id` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '用户1', 11, '453534', 1, NULL, NULL, 0);
INSERT INTO `t_user` VALUES (2, '用户2', 11, '453534', 2, NULL, NULL, 0);
INSERT INTO `t_user` VALUES (3, '用户3', 11, '453534', 3, NULL, NULL, 0);
INSERT INTO `t_user` VALUES (4, '用户4', 11, '453534', 4, NULL, NULL, 0);
INSERT INTO `t_user` VALUES (5, '用户5', 11, '453534', 5, NULL, NULL, 0);
INSERT INTO `t_user` VALUES (6, '用户6', 11, '453534', 6, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
