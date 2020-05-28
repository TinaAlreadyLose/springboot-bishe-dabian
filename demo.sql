/*
 Navicat Premium Data Transfer

 Source Server         : mysql_guli
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 192.168.50.107:3306
 Source Schema         : cloud2020

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 27/05/2020 21:46:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes`  (
  `id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名称',
  `parent_id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父id,学院的父id是0,班级的父id是学院',
  `flag` tinyint(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('1', 'school1', '0', 0, 0, NULL, NULL);
INSERT INTO `classes` VALUES ('2', 'school2', '0', 0, 0, NULL, NULL);
INSERT INTO `classes` VALUES ('3', 'class1', '1', 0, 0, NULL, NULL);
INSERT INTO `classes` VALUES ('4', 'class2', '2', 0, 0, NULL, NULL);

-- ----------------------------
-- Table structure for GradeQuery
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `mark` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '成绩',
  `student_id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生id',
  `teacher_id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师id',
  `flag` tinyint(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of GradeQuery
-- ----------------------------
INSERT INTO `grade` VALUES ('1', 80.00, '1', '1', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('10', 86.00, '4', '3', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('11', 85.00, '4', '4', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('12', 88.00, '5', '3', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('13', 94.00, '5', '4', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('14', 91.00, '6', '3', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('15', 89.00, '6', '4', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('16', 95.00, '7', '6', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('17', 89.00, '8', '3', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('18', 84.00, '8', '4', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('19', 86.00, '9', '1', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('2', 92.00, '1', '2', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('20', 89.00, '9', '2', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('21', 98.00, '9', '5', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('3', 89.00, '1', '5', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('4', 76.00, '2', '1', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('5', 82.00, '2', '2', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('6', 89.00, '2', '5', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('7', 76.00, '3', '1', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('8', 76.00, '3', '2', 0, 0, NULL, NULL);
INSERT INTO `grade` VALUES ('9', 81.00, '3', '5', 0, 0, NULL, NULL);

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `sort` int(0) NOT NULL COMMENT '优先级',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分组名',
  `teacher_id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组长id',
  `flag` tinyint(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('1', 1, 'group1', '1', 0, 0, NULL, NULL);
INSERT INTO `group` VALUES ('2', 2, 'group2', '3', 0, 0, NULL, NULL);
INSERT INTO `group` VALUES ('3', 3, 'group3', '6', 0, 0, NULL, NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生id',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生姓名',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '毕设题目',
  `class_id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生班级id',
  `group_id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分组id',
  `final_grade` decimal(10, 2) NULL DEFAULT NULL COMMENT '最终成绩',
  `update` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否可以修改,0可以,1不可以',
  `flag` tinyint(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除,0未删除,1已删除',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'amumu', 'my name is amumu,I\'m a student', '3', '1', NULL, 0, 0, 0, NULL, NULL);
INSERT INTO `student` VALUES ('2', 'ashe', 'my name is ashe,I\'m a stundent', '3', '1', NULL, 0, 0, 0, NULL, NULL);
INSERT INTO `student` VALUES ('3', 'annie', 'my name is annie,I\'m a Student', '4', '1', NULL, 0, 0, 0, NULL, NULL);
INSERT INTO `student` VALUES ('4', 'yorick ', 'my name is yorick,I\'m a student', '4', '2', NULL, 0, 0, 0, NULL, NULL);
INSERT INTO `student` VALUES ('5', 'jax', 'my name is jax,I\'m a student', '3', '2', NULL, 0, 0, 0, NULL, NULL);
INSERT INTO `student` VALUES ('6', 'master yi', 'my name is master yi ,I\'m a student', '4', '2', NULL, 0, 0, 0, NULL, NULL);
INSERT INTO `student` VALUES ('7', 'ahri', 'my name is ahri,I\'m a student', '4', '3', NULL, 0, 0, 0, NULL, NULL);
INSERT INTO `student` VALUES ('8', 'akali', 'my name is akali,I\'m a student', '3', '2', NULL, 0, 0, 0, NULL, NULL);
INSERT INTO `student` VALUES ('9', 'anivia', 'my name is aniva,I\'m a student', '5', '1', NULL, 0, 0, 0, NULL, NULL);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师id',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师手机号',
  `parent_id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父id,,组长的父id是0,组员的父id是组长的id',
  `flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', 'dan', '11489464', '0', 0, 0, NULL, NULL);
INSERT INTO `teacher` VALUES ('2', 'tina', '4648966', '1', 0, 0, NULL, NULL);
INSERT INTO `teacher` VALUES ('3', 'lucy', '456651', '0', 0, 0, NULL, NULL);
INSERT INTO `teacher` VALUES ('4', 'mike', '789487498', '3', 0, 0, NULL, NULL);
INSERT INTO `teacher` VALUES ('5', 'bill', '645685', '1', 0, 0, NULL, NULL);
INSERT INTO `teacher` VALUES ('6', 'mark', '461565', '0', 0, 0, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
