SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `hor_request_record`
-- ----------------------------
DROP TABLE IF EXISTS `hor_request_record`;
CREATE TABLE `hor_request_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `uuid` varchar(45) NOT NULL DEFAULT '' COMMENT 'uuid',
  `create_user` int(11) NOT NULL DEFAULT '0' COMMENT '审核人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int(11) NOT NULL DEFAULT '0' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  `platform_id` tinyint(2) NOT NULL DEFAULT '0' COMMENT '平台用户ID',
  `channel_name` varchar(200) NOT NULL DEFAULT '' COMMENT '第三方类型HonorType.name()',
  `is_success` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否成功: 0失败，1成功，2第三方失败',
  `time_spend` int(11) NOT NULL DEFAULT '0',
  `is_new_request` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否为新请求',
  PRIMARY KEY (`id`),
  KEY `index_uuid` (`uuid`),
  KEY `unikey_time_channel` (`create_time`,`channel_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='平台请求记录表';

SET FOREIGN_KEY_CHECKS = 1;
