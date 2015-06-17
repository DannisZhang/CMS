USE `cms`;
DROP TABLE IF EXISTS `t_cms_mobile_phone_number`;
CREATE TABLE `t_cms_mobile_phone_number` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `number` VARCHAR(16) NOT NULL COMMENT '号码',
  `operator` VARCHAR(64) NOT NULL COMMENT '运营商',
  `attribution` VARCHAR(64) NOT NULL COMMENT '归属地',
  `wholesale_price` DOUBLE(10,2) NOT NULL DEFAULT 0.00 COMMENT '批发价',
  `floor_price` DOUBLE(10,2) NOT NULL DEFAULT 0.00 COMMENT '底价',
  `price` DOUBLE(10,2) NOT NULL DEFAULT 0.00 COMMENT '售价',
  `balance` DOUBLE(10,2) NOT NULL DEFAULT 0.00 COMMENT '话费余额',
  `priority` INT(11) NOT NULL DEFAULT 9999 COMMENT '优先级',
  `remark` VARCHAR(128) NULL COMMENT '备注',
  `status` VARCHAR(16) NOT NULL DEFAULT 'active' COMMENT '状态',
  `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` INT(11) NOT NULL DEFAULT 0 COMMENT '创建人',
  `last_modified_on` TIMESTAMP NULL COMMENT '最近修改时间',
  `last_modified_by` INT(11) NULL COMMENT '最近修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`number`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT '手机号码基本信息表';

-- 汽车品牌表
DROP TABLE IF EXISTS `t_cms_car_brand`;
CREATE TABLE `t_cms_car_brand` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` VARCHAR(128) NOT NULL COMMENT '品牌名称',
  `english_name` VARCHAR(128) COMMENT '英文名称',
  `logo_url` VARCHAR(128) COMMENT 'LOGO URL',
  `country` VARCHAR(128) COMMENT '所属国家',
  `remark` VARCHAR(128) NULL COMMENT '备注',
  `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` INT(11) NOT NULL DEFAULT 0 COMMENT '创建人',
  `last_modified_on` TIMESTAMP NULL COMMENT '最近修改时间',
  `last_modified_by` INT(11) NULL COMMENT '最近修改人',
  PRIMARY KEY (`id`)
)ENGINE = MyISAM DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT '汽车品牌表';

-- 汽车类型表
DROP TABLE IF EXISTS `t_cms_car_type`;
CREATE TABLE `t_cms_car_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` VARCHAR(128) NOT NULL COMMENT '车型名称',
  `english_name` VARCHAR(128) COMMENT '英文名称',
  `remark` VARCHAR(128) NULL COMMENT '备注',
  `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` INT(11) NOT NULL DEFAULT 0 COMMENT '创建人',
  `last_modified_on` TIMESTAMP NULL COMMENT '最近修改时间',
  `last_modified_by` INT(11) NULL COMMENT '最近修改人',
  PRIMARY KEY (`id`)
)ENGINE = MyISAM DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT '汽车类型表';

-- 汽车基本信息表
DROP TABLE IF EXISTS `t_cms_car`;
CREATE TABLE `t_cms_car` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `brand_id` INT(11) NOT NULL COMMENT '品牌ID',
  `type_id` INT(11) NOT NULL COMMENT '车型ID',
  `series` VARCHAR(128) NOT NULL COMMENT '车系',
  `structure` VARCHAR(32) NOT NULL COMMENT '车身结构',
  `displacement` VARCHAR(32) NOT NULL COMMENT '排量',
  `emission_standard` VARCHAR(32) NOT NULL COMMENT '排放标准',
  `gearbox` VARCHAR(32) NOT NULL COMMENT '变速箱',
  `mileage` INT(11) COMMENT '表现里程',
  `registration_time` TIMESTAMP COMMENT '优先级',
  `price` DOUBLE(10,2) NOT NULL DEFAULT 0.00 COMMENT '售价',
  `lowest_price` DOUBLE(10,2) COMMENT '最低售价',
  `remark` VARCHAR(128) NULL COMMENT '备注',
  `priority` INT(11) NULL COMMENT '优先级',
  `status` VARCHAR(16) NOT NULL DEFAULT 'active' COMMENT '状态',
  `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` INT(11) NOT NULL DEFAULT 0 COMMENT '创建人',
  `last_modified_on` TIMESTAMP NULL COMMENT '最近修改时间',
  `last_modified_by` INT(11) NULL COMMENT '最近修改人',
  PRIMARY KEY (`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT '汽车基本信息表';

-- 汽车图片表
DROP TABLE IF EXISTS `t_cms_car_image`;
CREATE TABLE `t_cms_car_image` (
  `car_id` INT(11) NOT NULL COMMENT '汽车ID',
  `image_url` VARCHAR(256) NOT NULL COMMENT '图片URL'
)ENGINE = MyISAM DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT '汽车图片表';