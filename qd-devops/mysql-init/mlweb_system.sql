-- 切换库
USE mlweb_system;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 创建部门表
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `id`          BIGINT                                                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        VARCHAR(64) CHARSET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '部门名称',
    `parent_id`   BIGINT                                                  NULL DEFAULT 0 COMMENT '父节点id',
    `tree_path`   VARCHAR(256) CHARSET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '父节点id路径',
    `sort`        INT                                                     NULL DEFAULT 0 COMMENT '显示顺序',
    `status`      TINYINT                                                 NULL DEFAULT 1 COMMENT '状态(0:禁用;1:正常)',
    `deleted`     TINYINT                                                 NULL DEFAULT 0 COMMENT '是否删除(0:未删除;1:已删除)',
    `create_time` DATETIME                                                NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME                                                NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1677
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '部门表'
  ROW_FORMAT = DYNAMIC;

-- 创建菜单表
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          BIGINT                                                        NOT NULL AUTO_INCREMENT,
    `parent_id`   BIGINT                                                        NULL DEFAULT NULL COMMENT '父菜单ID',
    `type`        TINYINT                                                       NULL DEFAULT NULL COMMENT '菜单类型(1:菜单;2:目录;3:外链;4:按钮)',
    `name`        VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '菜单名称',
    `path`        VARCHAR(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由路径',
    `component`   VARCHAR(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'react组件完整路径，省略后缀',
    `perm`        VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '按钮权限标识',
    `icon`        VARCHAR(64) CHARACTER SET uft8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '菜单图标',
    `sort`        INT                                                           NULL DEFAULT 0 COMMENT '排序',
    `visible`     TINYINT(1)                                                    NULL DEFAULT 1 COMMENT '状态(0:禁用;1:开启)',
    `redirect`    VARCHAR(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '跳转路径',
    `tree_path`   VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '树路径',
    `always_show` TINYINT                                                       NULL DEFAULT NULL COMMENT '【目录】只有一个子路由是否始终显示(1:是;0:否)',
    `keep_alive`  TINYINT                                                       NULL DEFAULT NULL COMMENT '【菜单】是否开启页面缓存(1:是;0:否)',
    `create_time` DATETIME                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME                                                      NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 140
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '菜单管理'
  ROW_FORMAT = DYNAMIC;

-- 创建角色表
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          BIGINT                                                       NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
    `code`        VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '角色编码',
    `sort`        INT                                                          NULL     DEFAULT NULL COMMENT '显示顺序',
    `status`      TINYINT                                                      NULL     DEFAULT 1 COMMENT '角色状态(0:停用;1:正常)',
    `deleted`     TINYINT                                                      NOT NULL DEFAULT 0 COMMENT '是否删除(0:未删除;1:已删除)',
    `create_time` DATETIME                                                     NULL     DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME                                                     NULL     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name` (`name` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 78
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色表'
  ROW_FORMAT = DYNAMIC;


-- 创建角色菜单表
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `role_id` BIGINT NOT NULL COMMENT '角色ID',
    `menu_id` BIGINT NOT NULL COMMENT '菜单ID'
) ENGINE = InnoDB
  AUTO_INCREMENT = 78
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色菜单表'
  ROW_FORMAT = DYNAMIC;

-- 创建用户表
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          BIGINT                                                        NULL AUTO_INCREMENT,
    `username`    VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '用户名',
    `nickname`    VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '昵称',
    `gender`      TINYINT(1)                                                    NULL     DEFAULT 1 COMMENT '性别(1:男;2:女)',
    `password`    VARCHAR(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '密码',
    `dept_id`     BIGINT                                                        NULL     DEFAULT NULL COMMENT '部门ID',
    `avatar`      VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT '' COMMENT '用户头像',
    `mobile`      VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '' COMMENT '联系方式',
    `status`      TINYINT(1)                                                    NULL     DEFAULT 1 COMMENT '状态(1:正常;0:禁用)',
    `email`       VARCHAR(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '用户邮箱',
    `deleted`     TINYINT                                                       NOT NULL DEFAULT 0 COMMENT '是否删除(0:未删除;1:已删除)',
    `create_time` DATETIME                                                      NULL     DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME                                                      NULL     DEFAULT NULL COMMENT '更新时间',
    `create_user` BIGINT                                                        NULL     DEFAULT NULL COMMENT '创建者ID',
    `update_user` BIGINT                                                        NULL     DEFAULT NULL COMMENT '更新者ID',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `login_name` (`username` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1001
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户表'
  ROW_FORMAT = DYNAMIC;

-- 创建用户角色表
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `role_id` BIGINT NOT NULL COMMENT '角色ID'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户角色表'
  ROW_FORMAT = DYNAMIC;

-- 创建人员密级表
DROP TABLE IF EXISTS `sys_secret_level`;
CREATE TABLE `sys_secret_level`
(
    `id`          BIGINT                                                       NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '人员密级名称',
    `code`        VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '人员密级编码',
    `description` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '描述',
    `sort`        INT                                                          NULL     DEFAULT NULL COMMENT '显示顺序',
    `status`      TINYINT                                                      NULL     DEFAULT 1 COMMENT '状态(0:停用;1:正常)',
    `deleted`     TINYINT                                                      NOT NULL DEFAULT 0 COMMENT '是否删除(0:未删除;1:已删除)',
    `create_time` DATETIME                                                     NULL     DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME                                                     NULL     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `code` (`code` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 230
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '人员密级表'
  ROW_FORMAT = DYNAMIC;

-- 创建用户密级表
DROP TABLE IF EXISTS `sys_user_sl`;
CREATE TABLE `sys_user_sl`
(
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `sl_id`   BIGINT NOT NULL COMMENT '人员密级ID'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户密级表'
  ROW_FORMAT = DYNAMIC;

-- 创建数据密级表
DROP TABLE IF EXISTS `sys_confidentiality_level`;
CREATE TABLE `sys_confidentiality_level`
(
    `id`          BIGINT                                                       NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '数据密级名称',
    `code`        VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '数据密级编码',
    `description` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '描述',
    `sort`        INT                                                          NULL     DEFAULT NULL COMMENT '显示顺序',
    `status`      TINYINT                                                      NULL     DEFAULT 1 COMMENT '状态(0:停用;1:正常)',
    `deleted`     TINYINT                                                      NOT NULL DEFAULT 0 COMMENT '是否删除(0:未删除;1:已删除)',
    `create_time` DATETIME                                                     NULL     DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME                                                     NULL     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `code` (`code` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 308
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '数据密级表'
  ROW_FORMAT = DYNAMIC;

-- 创建资源表
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`
(
    `id`          BIGINT                                                       NOT NULL AUTO_INCREMENT,
    `resource_id` BIGINT                                                       NULL DEFAULT NULL COMMENT '资源ID(如果为记录必填)',
    `type`        TINYINT                                                      NULL DEFAULT NULL COMMENT '资源类型(1:库表;2:记录;3:字段)',
    `name`        VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '资源名称',
    `sort`        INT                                                          NULL DEFAULT 0 COMMENT '排序',
    `visible`     TINYINT(1)                                                   NULL DEFAULT 1 COMMENT '状态(0:禁用;1:开启)',
    `create_time` DATETIME                                                     NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME                                                     NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 595
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '资源表'
  ROW_FORMAT = DYNAMIC;

-- 创建资源密级表
DROP TABLE IF EXISTS `sys_resource_cl`;
CREATE TABLE `sys_resource_cl`
(
    `resource_id` BIGINT NOT NULL COMMENT '资源ID',
    `cl_id`       BIGINT NOT NULL COMMENT '数据密级ID'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '资源密级表'
  ROW_FORMAT = DYNAMIC;


SET FOREIGN_KEY_CHECKS = 1;