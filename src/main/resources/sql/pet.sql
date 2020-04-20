-- ----------------------------
-- Table structure for pet
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `gender` tinyint(1) NOT NULL DEFAULT 0 COMMENT '性别（0：公，1：母）',
  `species` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '品种',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '宠物头像',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '介绍',
  `birthday` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '生日',
  `weight` decimal(10, 1) NOT NULL DEFAULT 0.0 COMMENT '当前体重',
  `total_cost` decimal(10, 1) NOT NULL DEFAULT 0.0 COMMENT '总消费',
  `photo_number` int(11) NOT NULL DEFAULT 0 COMMENT '照片数量',
  `diary_number` int(11) NOT NULL DEFAULT 0 COMMENT '日记数量',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES (1, 1, 'pet001', 0, 'cat', 'https://images.unsplash.com/photo-1548681528-6a5c45b66b42?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80', 'a cat', '2020-04-20 13:35:02', 0.0, 0.0, 0, 0, '2020-04-20 13:35:02', '2020-04-20 13:36:11');
INSERT INTO `pet` VALUES (2, 1, 'pet002', 1, 'dog', 'https://images.unsplash.com/photo-1548439739-0cf616cef1cd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=675&q=80', 'a dog', '2020-04-20 13:35:09', 0.0, 0.0, 0, 0, '2020-04-20 13:35:09', '2020-04-20 13:36:48');
INSERT INTO `pet` VALUES (3, 2, 'pet001', 0, 'cat', 'https://images.unsplash.com/photo-1548681528-6a5c45b66b42?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80', 'a cat', '2020-04-20 13:36:46', 0.0, 0.0, 0, 0, '2020-04-20 13:36:46', '2020-04-20 13:36:57');
INSERT INTO `pet` VALUES (4, 2, 'pet002', 1, 'dog', 'https://images.unsplash.com/photo-1548439739-0cf616cef1cd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=675&q=80', 'a dog', '2020-04-20 13:37:08', 0.0, 0.0, 0, 0, '2020-04-20 13:37:08', '2020-04-20 13:37:14');
