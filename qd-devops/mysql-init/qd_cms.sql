-- 切换库
USE qd_cms;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 创建本地用户表
CREATE TABLE IF NOT EXISTS local_user
(
    id             BIGINT       NOT NULL PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '昵称',
    openid        VARCHAR(256) NOT NULL COMMENT 'openID',
    create_user_id BIGINT COMMENT '创建用户',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '本地用户' ENGINE = InnoDB
                     CHARACTER SET = utf8mb4
                     COLLATE = utf8mb4_general_ci
                     ROW_FORMAT = DYNAMIC;

-- 创建天平类型表
CREATE TABLE IF NOT EXISTS balance_type
(
    id             BIGINT PRIMARY KEY NOT NULL COMMENT 'id',
    name           VARCHAR(512)       NOT NULL COMMENT '名称',
    description    TEXT COMMENT '描述',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '天平类型' ENGINE = InnoDB
                     CHARACTER SET = utf8mb4
                     COLLATE = utf8mb4_general_ci
                     ROW_FORMAT = DYNAMIC;


-- 创建研究机构表
CREATE TABLE IF NOT EXISTS company
(
    id             BIGINT PRIMARY KEY NOT NULL COMMENT 'id',
    code           VARCHAR(256)       NOT NULL COMMENT '单位编码',
    name           VARCHAR(512) COMMENT '单位名称',
    address        TEXT COMMENT '单位地址',
    telephone      VARCHAR(256) COMMENT '联系电话',
    father_id      BIGINT COMMENT ' 所属机构id',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id),
    FOREIGN KEY (father_id) REFERENCES company (id)
) COMMENT '研究机构' ENGINE = InnoDB
                     CHARACTER SET = utf8mb4
                     COLLATE = utf8mb4_general_ci
                     ROW_FORMAT = DYNAMIC;

-- 创建风洞类型表
CREATE TABLE IF NOT EXISTS wt_type
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '名称',
    description    TEXT COMMENT '描述',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '风洞类型' ENGINE = InnoDB
                     CHARACTER SET = utf8mb4
                     COLLATE = utf8mb4_general_ci
                     ROW_FORMAT = DYNAMIC;

-- 创建模型支撑方式表
CREATE TABLE IF NOT EXISTS support_mode
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '名称',
    description    TEXT COMMENT '描述',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '模型支撑方式' ENGINE = InnoDB
                         CHARACTER SET = utf8mb4
                         COLLATE = utf8mb4_general_ci
                         ROW_FORMAT = DYNAMIC;


-- 创建弹道信息表
CREATE TABLE IF NOT EXISTS trajectory
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    code           VARCHAR(256) NOT NULL COMMENT '编码',
    name           VARCHAR(512) COMMENT '名称',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '弹道信息' ENGINE = InnoDB
                     CHARACTER SET = utf8mb4
                     COLLATE = utf8mb4_general_ci
                     ROW_FORMAT = DYNAMIC;

-- 创建飞行试验方式表
CREATE TABLE IF NOT EXISTS flight_mode
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '名称',
    description    TEXT COMMENT '描述',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '飞行试验方式' ENGINE = InnoDB
                         CHARACTER SET = utf8mb4
                         COLLATE = utf8mb4_general_ci
                         ROW_FORMAT = DYNAMIC;

-- 创建软件信息表
CREATE TABLE IF NOT EXISTS soft
(
    id               BIGINT PRIMARY KEY COMMENT 'id',
    code             VARCHAR(256) NOT NULL COMMENT '编码',
    name             VARCHAR(512) COMMENT '名称',
    version          VARCHAR(256) COMMENT '版本',
    provider         VARCHAR(256) COMMENT '软件供应商',
    func_description TEXT COMMENT '软件功能简介',
    create_user_id   BIGINT COMMENT '创建用户id',
    create_time      DATETIME COMMENT '创建时间',
    update_user_id   BIGINT COMMENT '更新用户id',
    update_time      DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '软件' ENGINE = InnoDB
                 CHARACTER SET = utf8mb4
                 COLLATE = utf8mb4_general_ci
                 ROW_FORMAT = DYNAMIC;

-- 创建天平表
CREATE TABLE IF NOT EXISTS balance
(
    id                  BIGINT       NOT NULL PRIMARY KEY COMMENT 'id',
    company_design_id   BIGINT COMMENT '天平设计单位id',
    company_research_id BIGINT COMMENT '天平机构id',
    balance_type_id     BIGINT COMMENT '天平类型id',
    code                VARCHAR(256) NOT NULL COMMENT '天平编码',
    name                VARCHAR(512) COMMENT '天平名称',
    `load`              VARCHAR(256) COMMENT '天平设计载荷（kn）',
    shape_size          VARCHAR(256) COMMENT '天平外形尺寸（mm）',
    remark              TEXT COMMENT '备注',
    create_user_id      BIGINT COMMENT '创建用户id',
    create_time         DATETIME COMMENT '创建时间',
    update_user_id      BIGINT COMMENT '更新用户id',
    update_time         DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id),
    FOREIGN KEY (company_design_id) REFERENCES company (id),
    FOREIGN KEY (company_research_id) REFERENCES company (id)
) COMMENT '天平' ENGINE = InnoDB
                 CHARACTER SET = utf8mb4
                 COLLATE = utf8mb4_general_ci
                 ROW_FORMAT = DYNAMIC;

-- 创建风洞表
CREATE TABLE IF NOT EXISTS wind_tunnel
(
    id              BIGINT PRIMARY KEY NOT NULL COMMENT 'id',
    code            VARCHAR(256)       NOT NULL COMMENT '编码',
    name            VARCHAR(512)       NOT NULL COMMENT '名称',
    wt_type_id      BIGINT COMMENT '风洞类型',
    company_id      BIGINT COMMENT '所属单位',
    range_test_time VARCHAR(256) COMMENT '风洞有效试验时间范围',
    len_tetssec     VARCHAR(256) COMMENT '风洞试验段长度（m）',
    size_testesc    VARCHAR(256) COMMENT '风洞试验段横截面尺寸（m）',
    size_nozexit    VARCHAR(256) COMMENT '风洞喷管出口尺寸（m）',
    range_v         VARCHAR(256) COMMENT '风洞速度范围（m/s）',
    range_re        VARCHAR(256) COMMENT '风洞雷诺数范围',
    range_nusselt   VARCHAR(256) COMMENT '风洞努森数范围',
    range_pt        VARCHAR(256) COMMENT '风洞总压范围（MPa）',
    range_tt        VARCHAR(256) COMMENT '风洞总温范围（℃）',
    range_alt       VARCHAR(256) COMMENT '模拟高度范围（m）',
    create_user_id  BIGINT COMMENT '创建用户id',
    create_time     DATETIME COMMENT '创建时间',
    update_user_id  BIGINT COMMENT '更新用户id',
    update_time     DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id),
    FOREIGN KEY (wt_type_id) REFERENCES wt_type (id),
    FOREIGN KEY (company_id) REFERENCES company (id)
) COMMENT '风洞' ENGINE = InnoDB
                 CHARACTER SET = utf8mb4
                 COLLATE = utf8mb4_general_ci
                 ROW_FORMAT = DYNAMIC;

-- 创建翼舵类型表
CREATE TABLE IF NOT EXISTS airfoil_type
(
    id             BIGINT PRIMARY KEY NOT NULL COMMENT 'id',
    name           VARCHAR(512) COMMENT '翼型类型名称',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '翼舵布局' ENGINE = InnoDB
                     CHARACTER SET = utf8mb4
                     COLLATE = utf8mb4_general_ci
                     ROW_FORMAT = DYNAMIC;


-- 创建翼型信息表
CREATE TABLE IF NOT EXISTS airfoil
(
    id              BIGINT PRIMARY KEY COMMENT 'id',
    airfoil_type_id BIGINT COMMENT '翼型类型id',
    name            VARCHAR(512) COMMENT '翼型名称',
    remark          TEXT COMMENT '备注',
    create_user_id  BIGINT COMMENT '创建用户id',
    create_time     DATETIME COMMENT '创建时间',
    update_user_id  BIGINT COMMENT '更新用户id',
    update_time     DATETIME COMMENT '更新时间',
    FOREIGN KEY (airfoil_type_id) REFERENCES airfoil_type (id),
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '翼型' ENGINE = InnoDB
                 CHARACTER SET = utf8mb4
                 COLLATE = utf8mb4_general_ci
                 ROW_FORMAT = DYNAMIC;

-- 创建翼型坐标表
CREATE TABLE IF NOT EXISTS airfoil_coordinate
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    surface_ident  INT COMMENT '翼型表面标识',
    along_string_x DOUBLE COMMENT '沿弦向x值',
    airfoil_face_y DOUBLE COMMENT '翼型表面y值',
    airfoil_id     BIGINT COMMENT '翼型id',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (airfoil_id) REFERENCES airfoil (id),
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '翼型坐标' ENGINE = InnoDB
                     CHARACTER SET = utf8mb4
                     COLLATE = utf8mb4_general_ci
                     ROW_FORMAT = DYNAMIC;

-- 创建翼舵构型表
CREATE TABLE IF NOT EXISTS wingflap_config
(
    id                  BIGINT PRIMARY KEY COMMENT 'id',
    name                VARCHAR(512) COMMENT '名称',
    area                DOUBLE COMMENT '翼舵面积（㎡）',
    exposed_length      DOUBLE COMMENT '暴露展长（m）',
    chord_length        DOUBLE COMMENT '翼根弦长（m）',
    twist_chord_angle   DOUBLE COMMENT '跟弦扭转角',
    le_sweep_back_angle DOUBLE COMMENT '前沿后掠角',
    te_sweep_back_angle DOUBLE COMMENT '后沿后掠角',
    dihedral_angle      DOUBLE COMMENT '上反角',
    create_user_id      BIGINT COMMENT '创建用户id',
    create_time         DATETIME COMMENT '创建时间',
    update_user_id      BIGINT COMMENT '更新用户id',
    update_time         DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '翼舵构型' ENGINE = InnoDB
                     CHARACTER SET = utf8mb4
                     COLLATE = utf8mb4_general_ci
                     ROW_FORMAT = DYNAMIC;

-- 创建翼型截面信息表
CREATE TABLE IF NOT EXISTS wing_section
(
    id                 BIGINT PRIMARY KEY NOT NULL COMMENT 'id',
    airfoil_id         BIGINT COMMENT '翼型id',
    section_number     BIGINT COMMENT '翼型截面编号',
    section_location   VARCHAR(256) COMMENT '截面位置',
    wing_proportion    DOUBLE COMMENT '翼型比例',
    wingflap_config_id BIGINT COMMENT '翼舵构型id',
    description        TEXT COMMENT '描述',
    create_user_id     BIGINT COMMENT '创建用户id',
    create_time        DATETIME COMMENT '创建时间',
    update_user_id     BIGINT COMMENT '更新用户id',
    update_time        DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id),
    FOREIGN KEY (airfoil_id) REFERENCES airfoil (id),
    FOREIGN KEY (wingflap_config_id) REFERENCES wingflap_config (id)
) COMMENT '翼型截面信息' ENGINE = InnoDB
                         CHARACTER SET = utf8mb4
                         COLLATE = utf8mb4_general_ci
                         ROW_FORMAT = DYNAMIC;

-- 创建翼舵布局表
CREATE TABLE IF NOT EXISTS wingflap_layout
(
    id                 BIGINT PRIMARY KEY COMMENT 'id',
    name               VARCHAR(512) NOT NULL COMMENT '名称',
    type               VARCHAR(256) NOT NULL COMMENT '类型',
    wingflap_num       INTEGER      NOT NULL COMMENT '翼舵数目',
    fixed_pne_num      INTEGER COMMENT '固定气动面数',
    active_pne_num     INTEGER COMMENT '活动气动面数',
    pitch_wingflap_num INTEGER COMMENT '俯仰翼舵数目',
    pitch_id_set       VARCHAR(512) COMMENT '俯仰翼舵编号集',
    yaw_wingflap_num   INTEGER COMMENT '偏航翼舵数目',
    yaw_wingflap_set   VARCHAR(512) COMMENT '偏航翼舵编号集',
    roll_wingflap_num  INTEGER COMMENT '滚转翼舵数目',
    roll_wingflap_set  VARCHAR(512) COMMENT '滚转翼舵编号集',
    remark             TEXT COMMENT '备注',
    wingflap_img       VARCHAR(1024) COMMENT '翼舵示意图',
    deformula          VARCHAR(512) NOT NULL COMMENT '升降舵公式',
    drformula          VARCHAR(512) NOT NULL COMMENT '方向舵公式',
    daformula          VARCHAR(512) NOT NULL COMMENT '副翼舵公式',
    create_user_id     BIGINT COMMENT '创建用户id',
    create_time        DATETIME COMMENT '创建时间',
    update_user_id     BIGINT COMMENT '更新用户id',
    update_time        DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '翼舵布局' ENGINE = InnoDB
                     CHARACTER SET = utf8mb4
                     COLLATE = utf8mb4_general_ci
                     ROW_FORMAT = DYNAMIC;

-- 创建飞行器子级类型表
CREATE TABLE IF NOT EXISTS aircraft_sub
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '飞行器子级名称',
    description    TEXT COMMENT '飞行器子级描述',
    airfoil_id     BIGINT COMMENT '布局及外形id',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '飞行器子级信息' ENGINE = InnoDB
                           CHARACTER SET = utf8mb4
                           COLLATE = utf8mb4_general_ci
                           ROW_FORMAT = DYNAMIC;

-- 创建FAS细目类别信息表
CREATE TABLE IF NOT EXISTS fas
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '名称',
    code           VARCHAR(256) NOT NULL COMMENT '编码',
    description    TEXT COMMENT '描述',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT 'FAS细目类别信息' ENGINE = InnoDB
                            CHARACTER SET = utf8mb4
                            COLLATE = utf8mb4_general_ci
                            ROW_FORMAT = DYNAMIC;

-- 创建气动布局类型表
CREATE TABLE IF NOT EXISTS vehi_type
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '名称',
    description    TEXT COMMENT '描述',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT '气动布局类型' ENGINE = InnoDB
                         CHARACTER SET = utf8mb4
                         COLLATE = utf8mb4_general_ci
                         ROW_FORMAT = DYNAMIC;

-- 创建原型类型表
CREATE TABLE IF NOT EXISTS prototype_type
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    code           VARCHAR(256) NOT NULL COMMENT '编码',
    name           VARCHAR(512) NOT NULL COMMENT '名称',
    belong         VARCHAR(256) COMMENT '是原型p还是模型m',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT ='原型类型' ENGINE = InnoDB
                      CHARACTER SET = utf8mb4
                      COLLATE = utf8mb4_general_ci
                      ROW_FORMAT = DYNAMIC;


-- 创建翼型类型表
CREATE TABLE IF NOT EXISTS airfoil_type
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '翼型类型名称',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT ='翼型类型' ENGINE = InnoDB
                      CHARACTER SET = utf8mb4
                      COLLATE = utf8mb4_general_ci
                      ROW_FORMAT = DYNAMIC;


-- 创建发动机类型表
CREATE TABLE IF NOT EXISTS engine_type
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '名称',
    description    TEXT COMMENT '描述',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT ='发动机类型' ENGINE = InnoDB
                        CHARACTER SET = utf8mb4
                        COLLATE = utf8mb4_general_ci
                        ROW_FORMAT = DYNAMIC;


-- 创建燃油类型表
CREATE TABLE IF NOT EXISTS fuel_type
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '名称',
    description    TEXT COMMENT '描述',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT ='燃油类型' ENGINE = InnoDB
                      CHARACTER SET = utf8mb4
                      COLLATE = utf8mb4_general_ci
                      ROW_FORMAT = DYNAMIC;

-- 创建注油方式表
CREATE TABLE IF NOT EXISTS inj_mode
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '名称',
    description    TEXT COMMENT '描述',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT ='注油方式' ENGINE = InnoDB
                      CHARACTER SET = utf8mb4
                      COLLATE = utf8mb4_general_ci
                      ROW_FORMAT = DYNAMIC;

-- 创建点火方式表
CREATE TABLE IF NOT EXISTS file_mode
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '名称',
    description    TEXT COMMENT '描述',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT ='点火方式' ENGINE = InnoDB
                      CHARACTER SET = utf8mb4
                      COLLATE = utf8mb4_general_ci
                      ROW_FORMAT = DYNAMIC;


-- 创建样本表
CREATE TABLE IF NOT EXISTS sample
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '名称',
    data_type      VARCHAR(256) COMMENT '数据类别',
    file_format    VARCHAR(256) COMMENT '0-Excel; 1-Dat',
    company_id     BIGINT COMMENT '单位',
    description    TEXT COMMENT '描述',
    download_path  VARCHAR(1024) COMMENT '下载地址',
    locations      VARCHAR(1024) COMMENT '解析文件所在位置',
    parse_id       BIGINT COMMENT '文件解析时的id',
    sheet_index    INT COMMENT '解析内容所在 sheet（从左向右，0开始）',
    title_index    INT COMMENT '标题行所在位置（第几行，从0开始）',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT ='样本' ENGINE = InnoDB
                  CHARACTER SET = utf8mb4
                  COLLATE = utf8mb4_general_ci
                  ROW_FORMAT = DYNAMIC;

-- 创建坐标系类别表
CREATE TABLE IF NOT EXISTS coordinate_type
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '名称',
    description    TEXT COMMENT '描述',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT ='坐标系类别' ENGINE = InnoDB
                        CHARACTER SET = utf8mb4
                        COLLATE = utf8mb4_general_ci
                        ROW_FORMAT = DYNAMIC;

-- 创建坐标系统类别表
CREATE TABLE IF NOT EXISTS coord_system_type
(
    id             BIGINT PRIMARY KEY COMMENT 'id',
    name           VARCHAR(512) NOT NULL COMMENT '名称',
    description    TEXT COMMENT '描述',
    create_user_id BIGINT COMMENT '创建用户id',
    create_time    DATETIME COMMENT '创建时间',
    update_user_id BIGINT COMMENT '更新用户id',
    update_time    DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id)
) COMMENT ='坐标系系统类别' ENGINE = InnoDB
                            CHARACTER SET = utf8mb4
                            COLLATE = utf8mb4_general_ci
                            ROW_FORMAT = DYNAMIC;


-- 创建飞行器主信息表
CREATE TABLE IF NOT EXISTS aircraft_main
(
    id                BIGINT PRIMARY KEY COMMENT 'id',
    vehi_type_id      BIGINT COMMENT '气动布局类型id',
    fuel_type_id      BIGINT COMMENT '燃油类型id',
    vehi_id           BIGINT       NOT NULL COMMENT '飞行器id',
    vehi_name         VARCHAR(512) NOT NULL COMMENT '飞行器名称',
    design_department VARCHAR(512) COMMENT '设计单位',
    design_person     VARCHAR(512) COMMENT '设计负责人',
    design_date       DATETIME COMMENT '设计日期',
    engine_type_id    BIGINT COMMENT '发动机类型',
    moduleNum         BIGINT DEFAULT 0 COMMENT '发动机模块数',
    launch_platform   VARCHAR(512) COMMENT '飞行器发射平台',
    stages_lv         BIGINT DEFAULT 0 COMMENT '飞行器总级数',
    mach_turn         VARCHAR(256) COMMENT '转级马赫数范围',
    alti_turn         VARCHAR(256) COMMENT '转级高度范围(km)',
    mach_cruise       VARCHAR(256) COMMENT '巡航马赫数范围',
    alti_cruise       VARCHAR(256) COMMENT '巡航高度范围（km）',
    mach_dive         VARCHAR(256) COMMENT '俯冲马赫数范围',
    alti_dive         VARCHAR(256) COMMENT '俯冲高度范围（km）',
    `range`           VARCHAR(256) COMMENT '射程（km）',
    create_user_id    BIGINT COMMENT '创建用户id',
    create_time       DATETIME COMMENT '创建时间',
    update_user_id    BIGINT COMMENT '更新用户id',
    update_time       DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id),
    FOREIGN KEY (vehi_type_id) REFERENCES vehi_type (id),
    FOREIGN KEY (fuel_type_id) REFERENCES fuel_type (id),
    FOREIGN KEY (engine_type_id) REFERENCES engine_type (id)
) COMMENT ='飞行器主信息' ENGINE = InnoDB
                          CHARACTER SET = utf8mb4
                          COLLATE = utf8mb4_general_ci
                          ROW_FORMAT = DYNAMIC;

-- 创建飞行器次级信息表
CREATE TABLE IF NOT EXISTS aircraft_sec
(
    id               BIGINT PRIMARY KEY COMMENT 'id',
    aircraft_main_id BIGINT NOT NULL COMMENT '飞行器主信息id',
    aircraft_sub_id  BIGINT NOT NULL COMMENT '飞行器子级信息id',
    length           DOUBLE COMMENT '飞行器子级长度（m）',
    num_face         INT COMMENT '飞行器子级控制面数',
    max_width        DOUBLE COMMENT '飞行器最大宽度（m）',
    max_cross_area   DOUBLE COMMENT '飞行器子级最大横截面积（m²）',
    max_bottom_area  DOUBLE COMMENT '飞行器子级底部面积（m²）',
    create_user_id   BIGINT COMMENT '创建用户id',
    create_time      DATETIME COMMENT '创建时间',
    update_user_id   BIGINT COMMENT '更新用户id',
    update_time      DATETIME COMMENT '更新时间',
    FOREIGN KEY (create_user_id) REFERENCES local_user (id),
    FOREIGN KEY (update_user_id) REFERENCES local_user (id),
    FOREIGN KEY (aircraft_main_id) REFERENCES aircraft_main (id),
    FOREIGN KEY (aircraft_sub_id) REFERENCES aircraft_sub (id)
) COMMENT ='飞行器次级信息' ENGINE = InnoDB
                            CHARACTER SET = utf8mb4
                            COLLATE = utf8mb4_general_ci
                            ROW_FORMAT = DYNAMIC;


SET FOREIGN_KEY_CHECKS = 1;