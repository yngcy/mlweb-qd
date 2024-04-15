-- 切换库
USE mlweb_system;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 创建用户表
CREATE TABLE IF NOT EXISTS local_user
(
    id             BIGINT       NOT NULL PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '昵称',
    unionId        VARCHAR(256) NOT NULL COMMENT '认证ID',
    create_user_id BIGINT COMMENT '创建用户',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id),
    PRIMARY KEY (`id`) USING BTREE
) COMMENT '本地用户' ENGINE = InnoDB
                     CHARACTER SET = utf8mb4
                     COLLATE = ut8mb4_general_ci
                     ROW_FORMAT = DYNAMIC;


SET FOREIGN_KEY_CHECKS = 1;