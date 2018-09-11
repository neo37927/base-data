SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `hor_data`
-- ----------------------------
DROP TABLE IF EXISTS `hor_data`;
CREATE TABLE `hor_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `uuid` varchar(45) NOT NULL DEFAULT '' COMMENT 'uuid',
  `create_user` int(11) NOT NULL DEFAULT '0' COMMENT '审核人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int(11) NOT NULL DEFAULT '0' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  `channel_name` varchar(100) NOT NULL DEFAULT '',
  `honor_data` varchar(20000) NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户调用唯一标识',
  `honor_url` varchar(1000) NOT NULL DEFAULT '',
  `file_path` varchar(200) NOT NULL DEFAULT '' COMMENT '文件路径',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_uuid` (`uuid`),
  KEY `index_channel_time` (`channel_name`,`create_time`) USING BTREE,
  KEY `index_time_channel` (`create_time`,`channel_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='信用数据表';

SET FOREIGN_KEY_CHECKS = 1;
