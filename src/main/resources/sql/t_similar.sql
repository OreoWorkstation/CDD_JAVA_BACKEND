/*
 Navicat Premium Data Transfer

 Source Server         : CentOS7
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : scotty.ink:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 03/06/2020 15:47:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_similar
-- ----------------------------
DROP TABLE IF EXISTS `t_similar`;
CREATE TABLE `t_similar`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article` bigint(0) NOT NULL,
  `type` bigint(0) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
