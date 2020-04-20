-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '学生姓名',
  `gender` tinyint(1) NOT NULL DEFAULT 0 COMMENT '学生性别（0：男，1：女）',
  `major` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '专业',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'scott', 0, 'software', '2020-04-20 19:19:34', '2020-04-20 19:19:34');
INSERT INTO `student` VALUES (2, 'kai', 0, 'cs', '2020-04-20 19:19:34', '2020-04-20 19:19:34');
INSERT INTO `student` VALUES (3, 'alan', 1, 'cs', '2020-04-20 19:20:15', '2020-04-20 19:20:15');
INSERT INTO `student` VALUES (4, 'david', 0, 'cs', '2020-04-20 19:20:25', '2020-04-20 19:20:25');
INSERT INTO `student` VALUES (5, 'tom', 0, 'cs', '2020-04-20 19:20:35', '2020-04-20 19:20:41');