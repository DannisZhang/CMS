USE `cms`;
DROP TABLE IF EXISTS `t_cms_mobile_phone_number`;
CREATE TABLE `t_cms_mobile_phone_number` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `number` VARCHAR(16) NOT NULL COMMENT '号码',
  `operator` VARCHAR(64) NOT NULL COMMENT '运营商',
  `attribution` VARCHAR(64) NOT NULL COMMENT '归属地',
  `wholesale_price` DOUBLE(10,2) NOT NULL DEFAULT 0.00 COMMENT '批发价',
  `floor_price` DOUBLE(10,2) NOT NULL DEFAULT 0.00 COMMENT '底价',
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