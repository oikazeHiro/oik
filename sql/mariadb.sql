/*
 Navicat Premium Data Transfer

 Source Server         : mariadb
 Source Server Type    : MariaDB
 Source Server Version : 101100 (10.11.0-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : shiro_test

 Target Server Type    : MariaDB
 Target Server Version : 101100 (10.11.0-MariaDB)
 File Encoding         : 65001

 Date: 18/01/2023 09:25:21
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `DEPT_ID`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门ID',
    `PARENT_ID`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '上级部门ID',
    `DEPT_NAME`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
    `ORDER_NUM`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '排序',
    `UPDATE_USER`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `UPDATE_TIME`     datetime NULL DEFAULT NULL COMMENT '修改时间',
    `STATUS`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NOT NULL DEFAULT '1',
    `CREATE_USER`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `CREATE_TIME`     datetime NULL DEFAULT NULL COMMENT '创建时间',
    `CREATE_USERNAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `UPDATE_USERNAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    PRIMARY KEY (`DEPT_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept`
VALUES ('1', '0', '开发部', '1', NULL, '2019-01-05 21:08:27', '1', NULL, '2018-01-04 15:42:26', NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('2', '1', '开发一部', '1', NULL, '2019-01-18 00:59:37', '1', NULL, '2018-01-04 15:42:34', NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('3', '1', '开发二部', '2', NULL, '2019-01-05 14:09:39', '1', NULL, '2018-01-04 15:42:29', NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('4', '0', '市场部', '2', NULL, '2019-01-23 06:27:56', '1', NULL, '2018-01-04 15:42:36', NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('5', '0', '人事部', '3', NULL, '2019-01-23 06:27:59', '1', NULL, '2018-01-04 15:42:32', NULL, NULL);
INSERT INTO `sys_dept`
VALUES ('6', '0', '测试部', '4', NULL, '2019-01-17 08:15:47', '1', NULL, '2018-01-04 15:42:38', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
    `DICT_ID`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典ID',
    `KEYY`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '键',
    `VALUEE`          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '值',
    `FIELD_NAME`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字段名称',
    `TABLE_NAME`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名',
    `FATHER_ID`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '0 一级',
    `CREATE_TIME`     datetime NULL DEFAULT NULL COMMENT '创建时间',
    `CREATE_USER_ID`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建人ID',
    `CREATE_USERNAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `UPDATE_TIME`     datetime NULL DEFAULT NULL COMMENT '最后一次修改时间',
    `UPDATE_USER_ID`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后一次修改人ID',
    `UPDATE_USERNAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `STATUS`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '逻辑删除：1：生效0：失效',
    `OTHER_KEYY`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '其他判断依据',
    `SORT`            int(11) NOT NULL DEFAULT 0 COMMENT '排序',
    PRIMARY KEY (`DICT_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict`
VALUES ('1', '0', '用户表', '', 'sys_user', '0', '2022-12-19 11:08:37', '0', 'oikaze', '2022-12-19 11:09:00', '0',
        'oikaze', '1', '0', 0);
INSERT INTO `sys_dict`
VALUES ('10', '0', '菜单', 'type', 'sys_menu', '9', NULL, NULL, '', NULL, NULL, '', '1', '2', 0);
INSERT INTO `sys_dict`
VALUES ('11', '1', '按钮', 'type', 'sys_menu', '9', NULL, NULL, '', NULL, NULL, '', '1', '2', 1);
INSERT INTO `sys_dict`
VALUES ('2', '0', '性别', 'ssex', 'sys_user', '1', NULL, NULL, '', NULL, NULL, '', '1', '1', 0);
INSERT INTO `sys_dict`
VALUES ('3', '0', '男', 'ssex', 'sys_user', '2', NULL, NULL, NULL, NULL, NULL, NULL, '1', '2', 0);
INSERT INTO `sys_dict`
VALUES ('4', '1', '女', 'ssex', 'sys_user', '2', NULL, NULL, '', NULL, NULL, '', '1', '2', 1);
INSERT INTO `sys_dict`
VALUES ('5', '1', '状态', 'status', 'sys_user', '1', NULL, NULL, '', NULL, NULL, '', '1', '1', 0);
INSERT INTO `sys_dict`
VALUES ('6', '0', '有效', 'status', 'sys_user', '5', NULL, NULL, '', NULL, NULL, '', '1', '2', 0);
INSERT INTO `sys_dict`
VALUES ('7', '1', '无效', 'status', 'sys_user', '5', NULL, NULL, '', NULL, NULL, '', '1', '2', 1);
INSERT INTO `sys_dict`
VALUES ('8', '0', '菜单表', '', 'sys_menu', '0', '2023-01-10 16:19:17', NULL, '', NULL, NULL, '', '1', '0', 0);
INSERT INTO `sys_dict`
VALUES ('9', '0', '类型', 'type', 'sys_menu', '8', NULL, NULL, '', NULL, NULL, '', '1', '1', 0);

-- ----------------------------
-- Table structure for sys_greet
-- ----------------------------
DROP TABLE IF EXISTS `sys_greet`;
CREATE TABLE `sys_greet`
(
    `id`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `greet`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    `sort`        int(11) NOT NULL DEFAULT 0,
    `CREATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    `CREATE_TIME` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `UPDATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `UPDATE_TIME` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_greet
-- ----------------------------
INSERT INTO `sys_greet`
VALUES ('1', '喝杯咖啡休息下吧☕', 0, '', NULL, NULL, NULL);
INSERT INTO `sys_greet`
VALUES ('10', '准备吃些什么呢', 0, '', NULL, NULL, NULL);
INSERT INTO `sys_greet`
VALUES ('11', '周末要不要去看电影？', 0, '', NULL, NULL, NULL);
INSERT INTO `sys_greet`
VALUES ('2', '要不要和朋友打局LOL', 0, '', NULL, NULL, NULL);
INSERT INTO `sys_greet`
VALUES ('3', '要不要和朋友打局王者荣耀', 0, '', NULL, NULL, NULL);
INSERT INTO `sys_greet`
VALUES ('4', '几天没见又更好看了呢😍', 0, '', NULL, NULL, NULL);
INSERT INTO `sys_greet`
VALUES ('5', '今天又写了几个Bug🐞呢', 0, '', NULL, NULL, NULL);
INSERT INTO `sys_greet`
VALUES ('6', '今天在群里吹水了吗', 0, '', NULL, NULL, NULL);
INSERT INTO `sys_greet`
VALUES ('7', '今天吃了什么好吃的呢', 0, '', NULL, NULL, NULL);
INSERT INTO `sys_greet`
VALUES ('8', '今天您微笑了吗😊', 0, '', NULL, NULL, NULL);
INSERT INTO `sys_greet`
VALUES ('9', '今天帮助别人解决问题了吗', 0, '', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`
(
    `JOB_ID`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务id',
    `BEAN_NAME`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'spring bean名称',
    `METHOD_NAME`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '方法名',
    `PARAMS`          varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数',
    `CRON_EXPRESSION` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'cron表达式',
    `STATUS`          char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NOT NULL COMMENT '任务状态  0：正常  1：暂停',
    `REMARK`          varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
    `UPDATE_USERNAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `UPDATE_USER`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `UPDATE_TIME`     datetime NULL DEFAULT NULL,
    `CREATE_USERNAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `CREATE_USER`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `CREATE_TIME`     datetime NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`JOB_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job`
VALUES ('1', 'testTask', 'test', 'mrbird', '0/1 * * * * ?1', '1', '有参任务调度测试', NULL, NULL, NULL, NULL, NULL,
        '2018-02-24 16:26:14');
INSERT INTO `sys_job`
VALUES ('11', 'testTask', 'test2', NULL, '0/5 * * * * ?', '1', '测试异常', NULL, NULL, NULL, NULL, NULL,
        '2018-02-26 11:15:30');
INSERT INTO `sys_job`
VALUES ('2', 'testTask', 'test1', NULL, '0/10 * * * * ?', '1', '无参任务调度测试', NULL, NULL, NULL, NULL, NULL,
        '2018-02-24 17:06:23');
INSERT INTO `sys_job`
VALUES ('3', 'testTask', 'test', 'hello world', '0/1 * * * * ?', '1', '有参任务调度测试,每隔一秒触发', NULL, NULL, NULL,
        NULL, NULL, '2018-02-26 09:28:26');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`
(
    `LOG_ID`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务日志id',
    `JOB_ID`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务id',
    `BEAN_NAME`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'spring bean名称',
    `METHOD_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '方法名',
    `PARAMS`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数',
    `STATUS`      char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NOT NULL COMMENT '任务状态    0：成功    1：失败',
    `ERROR`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '失败信息',
    `TIMES`       decimal(11, 0) NULL DEFAULT NULL COMMENT '耗时(单位：毫秒)',
    `CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`LOG_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `ID`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
    `USERNAME`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作用户',
    `URL`           varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'url',
    `OPERATION`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作内容',
    `TIME`          decimal(11, 0) NULL DEFAULT 0 COMMENT '耗时',
    `METHOD`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
    `PARAMS`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法参数',
    `IP`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作者IP',
    `CREATE_TIME`   datetime NULL DEFAULT NULL COMMENT '创建时间',
    `location`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
    `RESPONSE_CODE` int(11) NULL DEFAULT 0 COMMENT '应答码',
    `RESPONSE_TEXT` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应答内容',
    `TYPE`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NOT NULL DEFAULT '' COMMENT '0系统内部操作日志, 1系统外部请求日志',
    PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`
(
    `USERNAME`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
    `LOGIN_TIME` datetime                                                      NOT NULL COMMENT '登录时间',
    `LOCATION`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
    `IP`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'IP地址'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-25 16:45:06', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-25 16:46:30', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-25 16:47:45', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-25 16:53:52', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-25 16:57:11', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-25 17:00:16', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-28 14:39:13', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-28 15:00:33', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-28 15:01:49', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('test1', '2022-11-28 15:07:25', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('test1', '2022-11-28 15:22:01', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-28 15:49:22', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-29 10:37:59', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('test1', '2022-11-29 13:53:11', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-29 14:49:29', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-30 13:56:28', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-30 13:56:28', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-30 13:56:28', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-30 13:56:28', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-30 13:56:37', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-30 13:59:06', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-30 14:04:02', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-30 14:07:03', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-11-30 14:11:45', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-01 14:49:14', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-01 14:49:14', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-01 14:49:14', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-09 15:33:22', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-09 15:34:39', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-09 15:36:32', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-12 09:18:32', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-12 09:37:27', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-12 13:18:19', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-12 13:36:15', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-12 13:39:25', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('test1', '2022-12-12 15:25:53', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-12 15:35:39', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-12 15:57:05', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-12 15:58:10', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 08:56:08', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 08:56:08', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 08:59:33', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 09:56:05', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 10:05:09', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 10:11:18', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 10:23:16', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 10:23:27', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 10:25:18', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 10:30:11', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 10:47:02', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 10:51:54', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 10:53:05', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 10:54:38', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 10:55:29', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 10:56:52', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 10:58:43', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 11:13:41', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 13:37:44', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 13:53:50', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 14:23:58', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 14:45:32', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 14:46:34', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 15:23:15', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 15:55:50', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-13 16:14:36', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-15 09:45:40', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-15 10:15:45', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-15 10:39:03', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-15 11:42:35', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-15 14:56:11', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-15 16:59:53', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 08:48:29', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:04:58', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:05:01', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:07:59', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:11:44', NULL, '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:14:31', NULL, '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:20:57', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:24:30', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:25:40', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:26:56', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:28:23', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:32:08', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:34:58', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:36:22', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:39:16', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:42:05', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:43:50', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 09:47:47', '', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 10:06:52', '内网IP', '10.10.254.85');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 10:12:45', '内网IP', '10.10.254.85');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 10:17:51', '内网IP', '10.10.254.85');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-16 15:54:51', '内网IP', '10.10.254.85');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-19 11:58:27', '内网IP', '10.10.254.8');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-19 13:46:37', '内网IP', '10.10.254.8');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-19 15:20:45', '内网IP', '10.10.254.8');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-21 10:40:19', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 10:59:55', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 13:30:24', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 13:35:21', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 13:40:20', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 13:54:58', '内网IP', '172.31.208.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 14:05:22', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 14:14:34', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 14:45:47', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('oikaze', '2022-12-26 14:48:45', '内网IP', '172.31.208.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 14:59:14', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 15:41:43', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 15:43:19', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 15:55:57', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 16:08:24', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 16:12:15', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 16:25:19', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 16:27:44', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 16:32:06', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 16:32:45', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2022-12-26 16:33:11', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-03 15:54:27', '内网IP', '172.31.192.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-04 09:47:31', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-04 09:51:33', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-04 09:51:45', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-04 13:56:20', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-04 14:08:17', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-04 15:43:42', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-04 15:51:28', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-04 16:33:49', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-04 16:35:49', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-05 08:57:22', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-05 09:03:38', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-05 09:10:21', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-05 09:11:05', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-05 10:12:42', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-05 10:15:17', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-05 10:18:55', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-05 15:35:20', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-06 14:42:29', '内网IP', '192.168.56.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-06 14:52:03', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-06 15:00:10', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-06 15:04:12', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-06 15:06:17', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-06 15:08:09', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-06 15:09:00', '内网IP', '192.168.56.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-06 15:11:34', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-06 15:15:16', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-06 15:42:43', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('oikaze', '2023-01-06 15:46:15', '内网IP', '192.168.56.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-09 08:44:20', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-10 09:44:05', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-10 11:08:03', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('oikaze', '2023-01-10 13:55:44', '内网IP', '172.26.48.1');
INSERT INTO `sys_login_log`
VALUES ('oikaze', '2023-01-10 14:01:04', '内网IP', '172.26.48.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-10 14:10:35', '内网IP', '172.26.48.1');
INSERT INTO `sys_login_log`
VALUES ('oikaze', '2023-01-10 14:18:01', '内网IP', '172.26.48.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-10 14:41:06', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-10 16:29:21', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-10 16:33:34', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-11 09:07:40', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-11 10:53:32', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-11 10:55:13', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-11 16:21:02', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-11 16:21:56', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-11 16:23:14', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-12 08:47:58', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('oikaze', '2023-01-12 09:28:56', '内网IP', '172.26.48.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-12 15:24:54', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-12 15:45:50', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-12 15:46:21', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-12 15:47:05', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-12 15:49:06', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-12 15:50:55', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-12 16:22:00', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-13 08:50:40', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-13 14:36:43', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('test2', '2023-01-13 14:46:48', '内网IP', '172.27.224.1');
INSERT INTO `sys_login_log`
VALUES ('oikaze', '2023-01-13 15:22:55', '内网IP', '172.27.224.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-13 15:24:33', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-13 15:24:57', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-13 15:25:50', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-13 15:33:15', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-13 15:34:27', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-13 15:36:47', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-13 15:37:44', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-16 08:59:56', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-16 14:41:18', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-17 08:48:40', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-17 14:17:47', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-17 14:34:38', '内网IP', '127.0.0.1');
INSERT INTO `sys_login_log`
VALUES ('admin', '2023-01-17 14:37:56', '内网IP', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `MENU_ID`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单/按钮ID',
    `PARENT_ID`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '上级菜单ID',
    `MENU_NAME`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '菜单/按钮名称',
    `PATH`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '对应路由path',
    `COMPONENT`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '对应路由组件component',
    `PERMS`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限标识',
    `ICON`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '图标',
    `TYPE`            char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NOT NULL COMMENT '类型 0菜单 1按钮',
    `ORDER_NUM`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `CREATE_TIME`     datetime NULL DEFAULT NULL COMMENT '创建时间',
    `UPDATE_TIME`     datetime NULL DEFAULT NULL COMMENT '修改时间',
    `STATUS`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NOT NULL DEFAULT '1',
    `CREATE_USERNAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `CREATE_USER_ID`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `UPDATE_USERNAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `UPDATE_USER_ID`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    PRIMARY KEY (`MENU_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES ('1', '0', '系统管理', '/system', 'PageView', NULL, 'Setting', '0', '1', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('10', '2', '系统日志', '/monitor/systemlog', 'monitor/SystemLog', '', 'CloseBold', '0', '2',
        '2023-01-11 03:00:01', '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('101', '0', '任务调度', '/job', 'PageView', NULL, 'PieChart', '0', '3', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('102', '101', '定时任务', '/job/job', 'quartz/job/Job', '', 'CloseBold', '0', '1', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('103', '102', '新增任务', '', '', 'job:add', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('104', '102', '修改任务', '', '', 'job:update', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('105', '102', '删除任务', '', '', 'job:delete', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('106', '102', '暂停任务', '', '', 'job:pause', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('107', '102', '恢复任务', '', '', 'job:resume', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('108', '102', '立即执行任务', '', '', 'job:run', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('109', '101', '调度日志', '/job/log', 'quartz/log/JobLog', '', 'CloseBold', '0', '2', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('11', '3', '新增用户', '', '', 'user:add', 'CloseBold', '1', NULL, '2023-01-11 03:00:01', '2023-01-11 03:13:14',
        '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('110', '109', '删除日志', '', '', 'jobLog:delete', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('113', '2', 'Redis监控', '/monitor/redis/info', 'monitor/RedisInfo', '', 'CloseBold', '0', '3',
        '2023-01-11 03:00:01', '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('12', '3', '修改用户', '', '', 'user:update', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('121', '2', '请求追踪', '/monitor/httptrace', 'monitor/Httptrace', NULL, 'CloseBold', '0', '4',
        '2023-01-11 03:00:01', '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('122', '2', '系统信息', '/monitor/system', 'EmptyPageView', NULL, 'CloseBold', '0', '5', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('123', '122', 'Tomcat信息', '/monitor/system/tomcat/info', 'monitor/TomcatInfo', NULL, 'CloseBold', '0', '2',
        '2023-01-11 03:00:01', '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('124', '122', 'JVM信息', '/monitor/system/jvm/info', 'monitor/JvmInfo', NULL, 'CloseBold', '0', '1',
        '2023-01-11 03:00:01', '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('127', '122', '服务器信息', '/monitor/system/system/info', 'monitor/SystemInfo', NULL, 'CloseBold', '0', '3',
        '2023-01-11 03:00:01', '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('128', '0', '其他模块', '/others', 'PageView', NULL, 'coffee', '0', '5', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('129', '128', '导入导出', '/others/excel', 'others/Excel', NULL, 'CloseBold', '0', '1', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('13', '3', '删除用户', '', '', 'user:delete', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('130', '3', '导出Excel', NULL, NULL, 'user:export', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('131', '4', '导出Excel', NULL, NULL, 'role:export', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('132', '5', '导出Excel', NULL, NULL, 'menu:export', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('133', '6', '导出Excel', NULL, NULL, 'dept:export', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('134', '64', '导出Excel', NULL, NULL, 'dict:export', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'admin', '35');
INSERT INTO `sys_menu`
VALUES ('135', '3', '密码重置', NULL, NULL, 'user:reset', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('136', '10', '导出Excel', NULL, NULL, 'log:export', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('137', '102', '导出Excel', NULL, NULL, 'job:export', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('138', '109', '导出Excel', NULL, NULL, 'jobLog:export', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('14', '4', '新增角色', '', '', 'role:add', 'CloseBold', '1', NULL, '2023-01-11 03:00:01', '2023-01-11 03:13:14',
        '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('15', '4', '修改角色', '', '', 'role:update', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('16', '4', '删除角色', '', '', 'role:delete', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('1613819633800974336', '5', '查看菜单', '', '', 'menu:view', 'View', '1', NULL, '2023-01-13 16:45:59',
        '2023-01-13 16:45:59', '', '', '', '', '');
INSERT INTO `sys_menu`
VALUES ('1613819996063010816', '5', '删除菜单', '', '', 'menu:delete', 'Delete', '1', NULL, '2023-01-13 16:47:25',
        '2023-01-13 16:47:25', '', '', '', '', '');
INSERT INTO `sys_menu`
VALUES ('1614826361665490944', '3', '查看用户', '', '', 'user:view', 'View', '1', NULL, '2023-01-16 11:26:21',
        '2023-01-16 11:26:21', '', '', '', '', '');
INSERT INTO `sys_menu`
VALUES ('17', '5', '新增菜单', '', '', 'menu:add', 'CloseBold', '1', NULL, '2023-01-11 03:00:01', '2023-01-11 03:13:14',
        '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('2', '0', '系统监控', '/monitor', 'PageView', NULL, 'Service', '0', '2', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('20', '6', '新增部门', '', '', 'dept:add', 'CloseBold', '1', NULL, '2023-01-11 03:00:01', '2023-01-11 03:13:14',
        '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('200', '2', '请求外部日志', '/monitor/requestlog', 'monitor/RequestLog', '', 'CloseBold', '0', '6',
        '2023-01-11 03:00:01', '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('201', '200', '删除日志', '', '', 'log:delete', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('202', '200', '导出Excel', '', '', 'log:export', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('203', '0', '系统首页', '/system/home', '', 'menu:view', 'House', '0', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('204', '203', '系统首页', '/system/home/index', 'home/DefaultView', NULL, 'House', '0', NULL,
        '2023-01-11 03:00:01', '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('21', '6', '修改部门', '', '', 'dept:update', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('22', '6', '删除部门', '', '', 'dept:delete', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('23', '8', '踢出用户', '', '', 'user:kickout', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('24', '10', '删除日志', '', '', 'log:delete', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('3', '1', '用户管理', '/system/user', 'system/user/User', '', 'CloseBold', '0', '1', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('4', '1', '角色管理', '/system/role', 'system/role/Role', '', 'CloseBold', '0', '2', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('5', '1', '菜单管理', '/system/menu', 'system/menu/Menu', '', 'CloseBold', '0', '3', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('58', '0', '网络资源', '/web', 'PageView', NULL, 'compass', '0', '4', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('59', '58', '天气查询', '/web/weather', 'web/Weather', '', 'CloseBold', '0', '1', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('6', '1', '部门管理', '/system/dept', 'system/dept/Dept', '', 'CloseBold', '0', '4', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('61', '58', '每日一文', '/web/dailyArticle', 'web/DailyArticle', '', 'CloseBold', '0', '2',
        '2023-01-11 03:00:01', '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('64', '1', '字典管理', '/system/dict', 'system/dict/Dict', '', 'CloseBold', '0', '5', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'admin', '35');
INSERT INTO `sys_menu`
VALUES ('65', '64', '新增字典', '', '', 'dict:add', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'admin', '35');
INSERT INTO `sys_menu`
VALUES ('66', '64', '修改字典', '', '', 'dict:update', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'admin', '35');
INSERT INTO `sys_menu`
VALUES ('67', '64', '删除字典', '', '', 'dict:delete', 'CloseBold', '1', NULL, '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'admin', '35');
INSERT INTO `sys_menu`
VALUES ('8', '2', '在线用户', '/monitor/online', 'system/monitor/Online', '', 'CloseBold', '0', '1',
        '2023-01-11 03:00:01', '2023-01-11 03:13:14', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu`
VALUES ('81', '58', '影视资讯', '/web/movie', 'EmptyPageView', NULL, 'CloseBold', '0', '3', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('82', '81', '正在热映', '/web/movie/hot', 'web/MovieHot', '', 'CloseBold', '0', '1', '2023-01-11 03:00:01',
        '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');
INSERT INTO `sys_menu`
VALUES ('83', '81', '即将上映', '/web/movie/coming', 'web/MovieComing', '', 'CloseBold', '0', '2',
        '2023-01-11 03:00:01', '2023-01-11 03:13:14', '0', NULL, NULL, 'mrbird', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `ROLE_ID`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
    `ROLE_NAME`       varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '角色名称',
    `REMARK`          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '角色描述',
    `CREATE_TIME`     datetime                                                      NOT NULL COMMENT '创建时间',
    `UPDATE_TIME`     datetime NULL DEFAULT NULL COMMENT '修改时间',
    `STATUS`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NOT NULL DEFAULT '1',
    `CREATE_USERNAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `CREATE_USER_ID`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `UPDATE_USERNAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `UPDATE_USER_ID`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `DATA_SCOPE`      tinyint(1) UNSIGNED ZEROFILL NOT NULL,
    PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES ('1', '管理员', '管理员', '2017-12-27 16:23:11', '2020-05-02 19:05:56', '1', NULL, NULL, 'admin', '35', 0);
INSERT INTO `sys_role`
VALUES ('2', '注册用户', '可查看，新增，导出', '2019-01-04 14:11:28', '2020-05-02 19:04:34', '0', NULL, NULL, 'mrbird',
        '1', 2);
INSERT INTO `sys_role`
VALUES ('72', '普通用户', '普通用户', '2019-01-23 07:33:20', '2019-12-23 11:13:32', '1', NULL, NULL, 'mrbird', '1', 0);
INSERT INTO `sys_role`
VALUES ('74', '自定义',
        '自定义菜单信息\n自定义菜单信息自定义菜单信息\n自定义菜单信息\n自定义菜单信息\n自定义菜单信息自定义菜',
        '2019-09-09 07:57:33', '2019-12-23 11:12:30', '0', NULL, NULL, 'mrbird', '1', 0);
INSERT INTO `sys_role`
VALUES ('79', 'test', 'test', '2019-12-12 18:12:54', '2019-12-23 11:12:26', '0', NULL, NULL, 'mrbird', '1', 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `ROLE_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `MENU_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`ROLE_ID`, `MENU_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES ('1', '1');
INSERT INTO `sys_role_menu`
VALUES ('1', '10');
INSERT INTO `sys_role_menu`
VALUES ('1', '101');
INSERT INTO `sys_role_menu`
VALUES ('1', '102');
INSERT INTO `sys_role_menu`
VALUES ('1', '103');
INSERT INTO `sys_role_menu`
VALUES ('1', '104');
INSERT INTO `sys_role_menu`
VALUES ('1', '105');
INSERT INTO `sys_role_menu`
VALUES ('1', '106');
INSERT INTO `sys_role_menu`
VALUES ('1', '107');
INSERT INTO `sys_role_menu`
VALUES ('1', '108');
INSERT INTO `sys_role_menu`
VALUES ('1', '109');
INSERT INTO `sys_role_menu`
VALUES ('1', '11');
INSERT INTO `sys_role_menu`
VALUES ('1', '110');
INSERT INTO `sys_role_menu`
VALUES ('1', '113');
INSERT INTO `sys_role_menu`
VALUES ('1', '12');
INSERT INTO `sys_role_menu`
VALUES ('1', '121');
INSERT INTO `sys_role_menu`
VALUES ('1', '122');
INSERT INTO `sys_role_menu`
VALUES ('1', '123');
INSERT INTO `sys_role_menu`
VALUES ('1', '124');
INSERT INTO `sys_role_menu`
VALUES ('1', '127');
INSERT INTO `sys_role_menu`
VALUES ('1', '128');
INSERT INTO `sys_role_menu`
VALUES ('1', '129');
INSERT INTO `sys_role_menu`
VALUES ('1', '13');
INSERT INTO `sys_role_menu`
VALUES ('1', '130');
INSERT INTO `sys_role_menu`
VALUES ('1', '131');
INSERT INTO `sys_role_menu`
VALUES ('1', '132');
INSERT INTO `sys_role_menu`
VALUES ('1', '133');
INSERT INTO `sys_role_menu`
VALUES ('1', '134');
INSERT INTO `sys_role_menu`
VALUES ('1', '135');
INSERT INTO `sys_role_menu`
VALUES ('1', '136');
INSERT INTO `sys_role_menu`
VALUES ('1', '137');
INSERT INTO `sys_role_menu`
VALUES ('1', '138');
INSERT INTO `sys_role_menu`
VALUES ('1', '14');
INSERT INTO `sys_role_menu`
VALUES ('1', '15');
INSERT INTO `sys_role_menu`
VALUES ('1', '16');
INSERT INTO `sys_role_menu`
VALUES ('1', '1613819633800974336');
INSERT INTO `sys_role_menu`
VALUES ('1', '1613819996063010816');
INSERT INTO `sys_role_menu`
VALUES ('1', '1614826361665490944');
INSERT INTO `sys_role_menu`
VALUES ('1', '17');
INSERT INTO `sys_role_menu`
VALUES ('1', '2');
INSERT INTO `sys_role_menu`
VALUES ('1', '20');
INSERT INTO `sys_role_menu`
VALUES ('1', '200');
INSERT INTO `sys_role_menu`
VALUES ('1', '201');
INSERT INTO `sys_role_menu`
VALUES ('1', '202');
INSERT INTO `sys_role_menu`
VALUES ('1', '203');
INSERT INTO `sys_role_menu`
VALUES ('1', '204');
INSERT INTO `sys_role_menu`
VALUES ('1', '21');
INSERT INTO `sys_role_menu`
VALUES ('1', '22');
INSERT INTO `sys_role_menu`
VALUES ('1', '23');
INSERT INTO `sys_role_menu`
VALUES ('1', '24');
INSERT INTO `sys_role_menu`
VALUES ('1', '3');
INSERT INTO `sys_role_menu`
VALUES ('1', '4');
INSERT INTO `sys_role_menu`
VALUES ('1', '5');
INSERT INTO `sys_role_menu`
VALUES ('1', '58');
INSERT INTO `sys_role_menu`
VALUES ('1', '59');
INSERT INTO `sys_role_menu`
VALUES ('1', '6');
INSERT INTO `sys_role_menu`
VALUES ('1', '61');
INSERT INTO `sys_role_menu`
VALUES ('1', '64');
INSERT INTO `sys_role_menu`
VALUES ('1', '65');
INSERT INTO `sys_role_menu`
VALUES ('1', '66');
INSERT INTO `sys_role_menu`
VALUES ('1', '67');
INSERT INTO `sys_role_menu`
VALUES ('1', '8');
INSERT INTO `sys_role_menu`
VALUES ('1', '81');
INSERT INTO `sys_role_menu`
VALUES ('2', '58');
INSERT INTO `sys_role_menu`
VALUES ('72', '10');
INSERT INTO `sys_role_menu`
VALUES ('72', '113');
INSERT INTO `sys_role_menu`
VALUES ('72', '121');
INSERT INTO `sys_role_menu`
VALUES ('72', '122');
INSERT INTO `sys_role_menu`
VALUES ('72', '123');
INSERT INTO `sys_role_menu`
VALUES ('72', '124');
INSERT INTO `sys_role_menu`
VALUES ('72', '127');
INSERT INTO `sys_role_menu`
VALUES ('72', '136');
INSERT INTO `sys_role_menu`
VALUES ('72', '2');
INSERT INTO `sys_role_menu`
VALUES ('72', '200');
INSERT INTO `sys_role_menu`
VALUES ('72', '201');
INSERT INTO `sys_role_menu`
VALUES ('72', '202');
INSERT INTO `sys_role_menu`
VALUES ('72', '203');
INSERT INTO `sys_role_menu`
VALUES ('72', '204');
INSERT INTO `sys_role_menu`
VALUES ('72', '23');
INSERT INTO `sys_role_menu`
VALUES ('72', '24');
INSERT INTO `sys_role_menu`
VALUES ('72', '8');

-- ----------------------------
-- Table structure for sys_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_test`;
CREATE TABLE `sys_test`
(
    `ID`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `FIELD1`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `FIELD2`      int(11) NOT NULL,
    `FIELD3`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `CREATE_TIME` datetime                                                      NOT NULL,
    PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_test
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `USER_ID`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
    `USERNAME`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户名',
    `PASSWORD`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
    `DEPT_ID`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门ID',
    `EMAIL`           varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '邮箱',
    `MOBILE`          varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '联系电话',
    `STATUS`          tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '状态 0无效 1有效',
    `LAST_LOGIN_TIME` datetime NULL DEFAULT NULL COMMENT '最近访问时间',
    `SSEX`            tinyint(3) UNSIGNED NULL DEFAULT 2 COMMENT '性别 0男 1女 2保密',
    `DESCRIPTION`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '描述',
    `AVATAR`          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户头像',
    `CREATE_USERNAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `CREATE_USER_ID`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `CREATE_TIME`     datetime NULL DEFAULT NULL COMMENT '创建时间',
    `UPDATE_USERNAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `UPDATE_TIME`     datetime NULL DEFAULT NULL COMMENT '修改时间',
    `UPDATE_USER_ID`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    PRIMARY KEY (`USER_ID`) USING BTREE,
    UNIQUE INDEX `username_index`(`USERNAME`) USING BTREE COMMENT 'username索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES ('1', 'oikaze', 'AK2sby+LMys3mcTAHXdn7xxYZC0pxqG5WaZfGMSJ6KunXUXYN32p6dcknK+R1oAbtw==', '1',
        'mrbird12111111123@hotmail.com', '13455533233', 1, '2023-01-13 15:22:55', 0, '开发部的小红帽\n',
        'https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7', 'oikaze',
        NULL, '2022-12-15 08:01:11', 'oikaze', '2022-12-15 08:01:11', '0');
INSERT INTO `sys_user`
VALUES ('12', 'jack', 'AK2sby+LMys3mcTAHXdn7xxYZC0pxqG5WaZfGMSJ6KunXUXYN32p6dcknK+R1oAbtw==', '6', 'jack@hotmail.com',
        NULL, 0, '2022-12-15 08:01:13', 0, NULL,
        'https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7', 'oikaze',
        NULL, '2022-12-15 08:01:13', 'oikaze', '2022-12-15 08:01:13', NULL);
INSERT INTO `sys_user`
VALUES ('13', 'xiaoduan', 'AK2sby+LMys3mcTAHXdn7xxYZC0pxqG5WaZfGMSJ6KunXUXYN32p6dcknK+R1oAbtw==', '5', 'test@163.com',
        NULL, 0, '2022-12-15 08:01:14', 2, NULL,
        'https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7', 'oikaze',
        NULL, '2022-12-15 08:01:14', 'oikaze', '2022-12-15 08:01:14', NULL);
INSERT INTO `sys_user`
VALUES ('16', 'test', 'AK2sby+LMys3mcTAHXdn7xxYZC0pxqG5WaZfGMSJ6KunXUXYN32p6dcknK+R1oAbtw==', '6', 'sdfsdf@qq.com',
        '18811445654', 0, '2022-12-15 08:01:15', 2, NULL,
        'https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7', 'oikaze',
        NULL, '2022-12-15 08:01:15', 'oikaze', '2022-12-15 08:01:15', NULL);
INSERT INTO `sys_user`
VALUES ('2', 'scott', '4c2af3a935be9df9AK2sby+LMys3mcTAHXdn7xxYZC0pxqG5WaZfGMSJ6KunXUXYN32p6dcknK+R1oAbtw==', '6',
        'scott@qq.com', '15134627380', 1, '2022-12-15 08:01:12', 0, '我是scott，嗯嗯',
        'https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7', 'oikaze',
        NULL, '2022-12-15 08:01:12', 'oikaze', '2022-12-15 08:01:12', NULL);
INSERT INTO `sys_user`
VALUES ('33', 'ddmtest14', 'AK2sby+LMys3mcTAHXdn7xxYZC0pxqG5WaZfGMSJ6KunXUXYN32p6dcknK+R1oAbtw==', '6',
        '45116365@qq.com', '18856345234', 0, '2022-12-15 08:01:16', 2, NULL,
        'https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7', 'oikaze',
        NULL, '2022-12-15 08:01:16', 'oikaze', '2022-12-15 08:01:16', NULL);
INSERT INTO `sys_user`
VALUES ('34', 'test1', 'AK2sby+LMys3mcTAHXdn7xxYZC0pxqG5WaZfGMSJ6KunXUXYN32p6dcknK+R1oAbtw==', '4', 'test1@qq.com',
        '18811487981', 1, '2022-12-15 08:01:16', 2, NULL,
        'https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7', 'oikaze',
        NULL, '2022-12-15 08:01:16', 'oikaze', '2022-12-15 08:01:16', NULL);
INSERT INTO `sys_user`
VALUES ('35', 'admin', 'AK2sby+LMys3mcTAHXdn7xxYZC0pxqG5WaZfGMSJ6KunXUXYN32p6dcknK+R1oAbtw==', '5', 'admin@admin.com',
        '18246544545', 1, '2023-01-17 14:37:56', 2, NULL,
        'https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7', 'mrbird',
        NULL, '2022-12-15 08:01:05', 'oikaze', '2022-12-15 08:01:05', '0');
INSERT INTO `sys_user`
VALUES ('36', 'test33333', 'AK2sby+LMys3mcTAHXdn7xxYZC0pxqG5WaZfGMSJ6KunXUXYN32p6dcknK+R1oAbtw==', '4',
        'sdasdas@adas.com', '18658273808', 1, '2022-12-15 08:01:17', 2, NULL,
        'https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7', 'oikaze',
        NULL, '2022-12-15 08:01:17', 'oikaze', '2022-12-15 08:01:17', NULL);
INSERT INTO `sys_user`
VALUES ('37', 'test2', 'AK2sby+LMys3mcTAHXdn7xxYZC0pxqG5WaZfGMSJ6KunXUXYN32p6dcknK+R1oAbtw==', NULL, NULL, NULL, 1,
        '2023-01-13 14:46:48', 2, NULL,
        'https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7', 'oikaze',
        NULL, '2022-12-15 08:01:19', 'oikaze', '2022-12-15 08:01:19', '35');

-- ----------------------------
-- Table structure for sys_user_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_config`;
CREATE TABLE `sys_user_config`
(
    `USER_ID`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
    `THEME`        varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '系统主题 dark暗色风格，light明亮风格',
    `LAYOUT`       varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '系统布局 side侧边栏，head顶部栏',
    `MULTI_PAGE`   char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '页面风格 1多标签页 0单页',
    `FIX_SIDERBAR` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '页面滚动是否固定侧边栏 1固定 0不固定',
    `FIX_HEADER`   char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '页面滚动是否固定顶栏 1固定 0不固定',
    `COLOR`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主题颜色 RGB值',
    PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_config
-- ----------------------------
INSERT INTO `sys_user_config`
VALUES ('1', 'dark', 'side', '0', '1', '1', 'rgb(24, 144, 255)');
INSERT INTO `sys_user_config`
VALUES ('2', 'light', 'head', '0', '1', '1', 'rgb(24, 144, 255)');
INSERT INTO `sys_user_config`
VALUES ('35', 'dark', 'side', '0', '1', '1', 'rgb(66, 185, 131)');
INSERT INTO `sys_user_config`
VALUES ('36', 'dark', 'side', '0', '1', '1', 'rgb(66, 185, 131)');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `USER_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
    `ROLE_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`USER_ID`, `ROLE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES ('1', '72');
INSERT INTO `sys_user_role`
VALUES ('12', '2');
INSERT INTO `sys_user_role`
VALUES ('2', '72');
INSERT INTO `sys_user_role`
VALUES ('35', '1');
INSERT INTO `sys_user_role`
VALUES ('36', '2');

SET
FOREIGN_KEY_CHECKS = 1;
