SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `code_manager`;
CREATE TABLE `code_manager` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `father_id` int(32) DEFAULT NULL COMMENT '父级ID',
  `code` varchar(200) DEFAULT NULL COMMENT '代码程序识别',
  `namespace` varchar(200) DEFAULT NULL COMMENT '命名空间',
  `is_default` int(32) DEFAULT NULL COMMENT '是否默认',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `domain` int(32) DEFAULT NULL COMMENT '域',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='常量表';

INSERT INTO `code_manager` VALUES ('1', '命名空间', '-1', 'Namespace', 'Namespace', null, null, '10086');
INSERT INTO `code_manager` VALUES ('2', '全部', '-1', null, 'All', null, null, '10086');
INSERT INTO `code_manager` VALUES ('3', '无', '-1', '-1', 'None', null, null, '10086');
INSERT INTO `code_manager` VALUES ('4', 'HTTP请求_命名空间', '-1', 'HTTP_Method', 'Namespace', null, null, '10086');
INSERT INTO `code_manager` VALUES ('5', 'GET', '-1', 'GET', 'HTTP_Method', null, null, '10086');
INSERT INTO `code_manager` VALUES ('6', 'POST', '-1', 'POST', 'HTTP_Method', null, null, '10086');
INSERT INTO `code_manager` VALUES ('7', '参数请求响应_命名空间', '-1', 'paramForType', 'Namespace', null, null, '10086');
INSERT INTO `code_manager` VALUES ('8', '请求', '-1', 'request', 'paramForType', null, null, '10086');
INSERT INTO `code_manager` VALUES ('9', '响应', '-1', 'response', 'paramForType', null, null, '10086');

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_name` varchar(200) DEFAULT NULL COMMENT '项目名称',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `domain` int(32) DEFAULT NULL COMMENT '域',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='API表';

DROP TABLE IF EXISTS `project_api`;
CREATE TABLE `project_api` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int(32) DEFAULT NULL COMMENT '隶属项目ID',
  `module_id` int(32) DEFAULT NULL COMMENT '隶属模块ID',
  `api_name` varchar(200) DEFAULT NULL COMMENT 'API名称',
  `api_url` varchar(200) DEFAULT NULL COMMENT 'API地址',
  `api_request_param` text DEFAULT NULL COMMENT 'API请求方式',
  `api_response_param` text DEFAULT NULL COMMENT 'API请求参数',
  `api_type` varchar(200) DEFAULT NULL COMMENT 'API响应参数',
  `api_comment` varchar(200) DEFAULT NULL COMMENT '说明',
  `api_index` int(32) DEFAULT NULL COMMENT '排序',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `domain` int(32) DEFAULT NULL COMMENT '域',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='API表';

DROP TABLE IF EXISTS `project_module`;
CREATE TABLE `project_module` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int(32) DEFAULT NULL COMMENT '隶属项目',
  `module_name` varchar(200) DEFAULT NULL COMMENT '模块名称',
  `module_index` int(32) DEFAULT NULL COMMENT '排序',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `domain` int(32) DEFAULT NULL COMMENT '域',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='API模块管理';

DROP TABLE IF EXISTS `project_table`;
CREATE TABLE `project_table` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int(32) DEFAULT NULL COMMENT '隶属项目ID',
  `table_name` varchar(200) DEFAULT NULL COMMENT '表名',
  `table_index` int(32) DEFAULT NULL COMMENT '排序',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `domain` int(32) DEFAULT NULL COMMENT '域',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COMMENT='API表';

DROP TABLE IF EXISTS `table_column`;
CREATE TABLE `table_column` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `table_id` int(32) DEFAULT NULL COMMENT '隶属表',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名',
  `column_type` varchar(200) DEFAULT NULL COMMENT '列数据类型',
  `is_nullable` varchar(200) DEFAULT NULL COMMENT '是否为空',
  `column_default` varchar(200) DEFAULT NULL COMMENT '默认值',
  `column_comment` varchar(200) DEFAULT NULL COMMENT '说明',
  `column_index` int(32) DEFAULT NULL COMMENT '排序',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `domain` int(32) DEFAULT NULL COMMENT '域',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=714 DEFAULT CHARSET=utf8 COMMENT='列';
