SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `hor_third_channel`
-- ----------------------------
DROP TABLE IF EXISTS `hor_third_channel`;
CREATE TABLE `hor_third_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `uuid` varchar(45) NOT NULL DEFAULT '' COMMENT 'uuid',
  `create_user` int(11) NOT NULL DEFAULT '0' COMMENT '审核人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` int(11) NOT NULL DEFAULT '0' COMMENT '更新用户',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  `description` varchar(500) NOT NULL DEFAULT '' COMMENT '第三方征信渠道说明,描述',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用状态: 0:未启用, 1: 启用',
  `code` varchar(50) NOT NULL DEFAULT '' COMMENT '渠道编号HonorType.name()',
  `channel_name` varchar(100) NOT NULL DEFAULT '' COMMENT '第三方征信渠道名称',
  `weight` int(10) NOT NULL DEFAULT '0' COMMENT '权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='第三方渠道表';

SET FOREIGN_KEY_CHECKS = 1;
