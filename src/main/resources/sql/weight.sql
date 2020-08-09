-- ----------------------------
-- Table structure for weight
-- ----------------------------
DROP TABLE IF EXISTS `t_weight`;
CREATE TABLE `t_weight`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pet_id` bigint(20) NOT NULL COMMENT '宠物ID',
  `weight_value` decimal(10, 1) NOT NULL DEFAULT 0.0 COMMENT '体重值',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;