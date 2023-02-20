IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_dept]') AND type in (N'U')) DROP TABLE [dbo].[sys_dept];
CREATE TABLE [dbo].[sys_dept](
    DEPT_ID VARCHAR(20) NOT NULL,
    PARENT_ID VARCHAR(20) NOT NULL DEFAULT  '0',
    DEPT_NAME VARCHAR(100) NOT NULL,
    ORDER_NUM VARCHAR(255),
    UPDATE_USER VARCHAR(255),
    UPDATE_TIME DATETIME,
    STATUS TINYINT UNSIGNED NOT NULL DEFAULT  1,
    CREATE_USER VARCHAR(255),
    CREATE_TIME DATETIME,
    CREATE_USERNAME VARCHAR(100),
    UPDATE_USERNAME VARCHAR(100),
    PRIMARY KEY (DEPT_ID)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_dept, null, null;
EXEC sp_addextendedproperty 'MS_Description', '部门ID', 'SCHEMA', dbo, 'table', sys_dept, 'column', DEPT_ID;
EXEC sp_addextendedproperty 'MS_Description', '上级部门ID', 'SCHEMA', dbo, 'table', sys_dept, 'column', PARENT_ID;
EXEC sp_addextendedproperty 'MS_Description', '部门名称', 'SCHEMA', dbo, 'table', sys_dept, 'column', DEPT_NAME;
EXEC sp_addextendedproperty 'MS_Description', '排序', 'SCHEMA', dbo, 'table', sys_dept, 'column', ORDER_NUM;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_dept, 'column', UPDATE_USER;
EXEC sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', dbo, 'table', sys_dept, 'column', UPDATE_TIME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_dept, 'column', STATUS;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_dept, 'column', CREATE_USER;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', sys_dept, 'column', CREATE_TIME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_dept, 'column', CREATE_USERNAME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_dept, 'column', UPDATE_USERNAME;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_dict]') AND type in (N'U')) DROP TABLE [dbo].[sys_dict];
CREATE TABLE [dbo].[sys_dict](
    DICT_ID VARCHAR(20) NOT NULL,
    KEYY VARCHAR(20) NOT NULL,
    VALUEE VARCHAR(100) NOT NULL,
    FIELD_NAME VARCHAR(100) NOT NULL,
    TABLE_NAME VARCHAR(100) NOT NULL,
    FATHER_ID VARCHAR(20) NOT NULL,
    CREATE_TIME DATETIME,
    CREATE_USER_ID VARCHAR(20),
    CREATE_USERNAME VARCHAR(100),
    UPDATE_TIME DATETIME,
    UPDATE_USER_ID VARCHAR(20),
    UPDATE_USERNAME VARCHAR(100),
    STATUS CHAR(1) DEFAULT  '1',
    OTHER_KEYY VARCHAR(100),
    SORT INT NOT NULL DEFAULT  0,
    PRIMARY KEY (DICT_ID)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_dict, null, null;
EXEC sp_addextendedproperty 'MS_Description', '字典ID', 'SCHEMA', dbo, 'table', sys_dict, 'column', DICT_ID;
EXEC sp_addextendedproperty 'MS_Description', '键', 'SCHEMA', dbo, 'table', sys_dict, 'column', KEYY;
EXEC sp_addextendedproperty 'MS_Description', '值', 'SCHEMA', dbo, 'table', sys_dict, 'column', VALUEE;
EXEC sp_addextendedproperty 'MS_Description', '字段名称', 'SCHEMA', dbo, 'table', sys_dict, 'column', FIELD_NAME;
EXEC sp_addextendedproperty 'MS_Description', '表名', 'SCHEMA', dbo, 'table', sys_dict, 'column', TABLE_NAME;
EXEC sp_addextendedproperty 'MS_Description', '0 一级', 'SCHEMA', dbo, 'table', sys_dict, 'column', FATHER_ID;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', sys_dict, 'column', CREATE_TIME;
EXEC sp_addextendedproperty 'MS_Description', '创建人ID', 'SCHEMA', dbo, 'table', sys_dict, 'column', CREATE_USER_ID;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_dict, 'column', CREATE_USERNAME;
EXEC sp_addextendedproperty 'MS_Description', '最后一次修改时间', 'SCHEMA', dbo, 'table', sys_dict, 'column', UPDATE_TIME;
EXEC sp_addextendedproperty 'MS_Description', '最后一次修改人ID', 'SCHEMA', dbo, 'table', sys_dict, 'column', UPDATE_USER_ID;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_dict, 'column', UPDATE_USERNAME;
EXEC sp_addextendedproperty 'MS_Description', '逻辑删除：1：生效0：失效', 'SCHEMA', dbo, 'table', sys_dict, 'column', STATUS;
EXEC sp_addextendedproperty 'MS_Description', '其他判断依据', 'SCHEMA', dbo, 'table', sys_dict, 'column', OTHER_KEYY;
EXEC sp_addextendedproperty 'MS_Description', '排序', 'SCHEMA', dbo, 'table', sys_dict, 'column', SORT;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_greet]') AND type in (N'U')) DROP TABLE [dbo].[sys_greet];
CREATE TABLE [dbo].[sys_greet](
    id VARCHAR(20) NOT NULL,
    greet VARCHAR(255) NOT NULL,
    sort INT NOT NULL DEFAULT  0,
    CREATE_USER VARCHAR(255) NOT NULL,
    CREATE_TIME DATETIME,
    UPDATE_USER VARCHAR(255),
    UPDATE_TIME DATETIME,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_greet, null, null;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_greet, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_greet, 'column', greet;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_greet, 'column', sort;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_greet, 'column', CREATE_USER;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_greet, 'column', CREATE_TIME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_greet, 'column', UPDATE_USER;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_greet, 'column', UPDATE_TIME;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_job]') AND type in (N'U')) DROP TABLE [dbo].[sys_job];
CREATE TABLE [dbo].[sys_job](
    JOB_ID VARCHAR(20) NOT NULL,
    BEAN_NAME VARCHAR(100) NOT NULL,
    METHOD_NAME VARCHAR(100) NOT NULL,
    PARAMS VARCHAR(200),
    CRON_EXPRESSION VARCHAR(100) NOT NULL,
    STATUS CHAR(2) NOT NULL,
    REMARK VARCHAR(200),
    UPDATE_USERNAME VARCHAR(100),
    UPDATE_USER VARCHAR(20),
    UPDATE_TIME DATETIME,
    CREATE_USERNAME VARCHAR(100),
    CREATE_USER VARCHAR(20),
    CREATE_TIME DATETIME,
    PRIMARY KEY (JOB_ID)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_job, null, null;
EXEC sp_addextendedproperty 'MS_Description', '任务id', 'SCHEMA', dbo, 'table', sys_job, 'column', JOB_ID;
EXEC sp_addextendedproperty 'MS_Description', 'spring bean名称', 'SCHEMA', dbo, 'table', sys_job, 'column', BEAN_NAME;
EXEC sp_addextendedproperty 'MS_Description', '方法名', 'SCHEMA', dbo, 'table', sys_job, 'column', METHOD_NAME;
EXEC sp_addextendedproperty 'MS_Description', '参数', 'SCHEMA', dbo, 'table', sys_job, 'column', PARAMS;
EXEC sp_addextendedproperty 'MS_Description', 'cron表达式', 'SCHEMA', dbo, 'table', sys_job, 'column', CRON_EXPRESSION;
EXEC sp_addextendedproperty 'MS_Description', '任务状态  0：正常  1：暂停', 'SCHEMA', dbo, 'table', sys_job, 'column', STATUS;
EXEC sp_addextendedproperty 'MS_Description', '备注', 'SCHEMA', dbo, 'table', sys_job, 'column', REMARK;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_job, 'column', UPDATE_USERNAME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_job, 'column', UPDATE_USER;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_job, 'column', UPDATE_TIME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_job, 'column', CREATE_USERNAME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_job, 'column', CREATE_USER;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', sys_job, 'column', CREATE_TIME;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_job_log]') AND type in (N'U')) DROP TABLE [dbo].[sys_job_log];
CREATE TABLE [dbo].[sys_job_log](
    LOG_ID VARCHAR(20) NOT NULL,
    JOB_ID VARCHAR(255) NOT NULL,
    BEAN_NAME VARCHAR(100) NOT NULL,
    METHOD_NAME VARCHAR(100) NOT NULL,
    PARAMS VARCHAR(200),
    STATUS CHAR(2) NOT NULL,
    ERROR TEXT,
    TIMES DECIMAL(11),
    CREATE_TIME DATETIME,
    PRIMARY KEY (LOG_ID)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_job_log, null, null;
EXEC sp_addextendedproperty 'MS_Description', '任务日志id', 'SCHEMA', dbo, 'table', sys_job_log, 'column', LOG_ID;
EXEC sp_addextendedproperty 'MS_Description', '任务id', 'SCHEMA', dbo, 'table', sys_job_log, 'column', JOB_ID;
EXEC sp_addextendedproperty 'MS_Description', 'spring bean名称', 'SCHEMA', dbo, 'table', sys_job_log, 'column', BEAN_NAME;
EXEC sp_addextendedproperty 'MS_Description', '方法名', 'SCHEMA', dbo, 'table', sys_job_log, 'column', METHOD_NAME;
EXEC sp_addextendedproperty 'MS_Description', '参数', 'SCHEMA', dbo, 'table', sys_job_log, 'column', PARAMS;
EXEC sp_addextendedproperty 'MS_Description', '任务状态    0：成功    1：失败', 'SCHEMA', dbo, 'table', sys_job_log, 'column', STATUS;
EXEC sp_addextendedproperty 'MS_Description', '失败信息', 'SCHEMA', dbo, 'table', sys_job_log, 'column', ERROR;
EXEC sp_addextendedproperty 'MS_Description', '耗时(单位：毫秒)', 'SCHEMA', dbo, 'table', sys_job_log, 'column', TIMES;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', sys_job_log, 'column', CREATE_TIME;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_log]') AND type in (N'U')) DROP TABLE [dbo].[sys_log];
CREATE TABLE [dbo].[sys_log](
    ID VARCHAR(20) NOT NULL,
    USERNAME VARCHAR(50),
    URL VARCHAR(500),
    OPERATION TEXT,
    TIME DECIMAL(11) DEFAULT  0,
    METHOD TEXT,
    PARAMS TEXT,
    IP VARCHAR(64),
    CREATE_TIME DATETIME,
    location VARCHAR(50),
    RESPONSE_CODE INT DEFAULT  0,
    RESPONSE_TEXT TEXT,
    TYPE CHAR(1) NOT NULL,
    PRIMARY KEY (ID)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_log, null, null;
EXEC sp_addextendedproperty 'MS_Description', '日志ID', 'SCHEMA', dbo, 'table', sys_log, 'column', ID;
EXEC sp_addextendedproperty 'MS_Description', '操作用户', 'SCHEMA', dbo, 'table', sys_log, 'column', USERNAME;
EXEC sp_addextendedproperty 'MS_Description', 'url', 'SCHEMA', dbo, 'table', sys_log, 'column', URL;
EXEC sp_addextendedproperty 'MS_Description', '操作内容', 'SCHEMA', dbo, 'table', sys_log, 'column', OPERATION;
EXEC sp_addextendedproperty 'MS_Description', '耗时', 'SCHEMA', dbo, 'table', sys_log, 'column', TIME;
EXEC sp_addextendedproperty 'MS_Description', '请求方式', 'SCHEMA', dbo, 'table', sys_log, 'column', METHOD;
EXEC sp_addextendedproperty 'MS_Description', '方法参数', 'SCHEMA', dbo, 'table', sys_log, 'column', PARAMS;
EXEC sp_addextendedproperty 'MS_Description', '操作者IP', 'SCHEMA', dbo, 'table', sys_log, 'column', IP;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', sys_log, 'column', CREATE_TIME;
EXEC sp_addextendedproperty 'MS_Description', '操作地点', 'SCHEMA', dbo, 'table', sys_log, 'column', location;
EXEC sp_addextendedproperty 'MS_Description', '应答码', 'SCHEMA', dbo, 'table', sys_log, 'column', RESPONSE_CODE;
EXEC sp_addextendedproperty 'MS_Description', '应答内容', 'SCHEMA', dbo, 'table', sys_log, 'column', RESPONSE_TEXT;
EXEC sp_addextendedproperty 'MS_Description', '0系统内部操作日志, 1系统外部请求日志', 'SCHEMA', dbo, 'table', sys_log, 'column', TYPE;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_login_log]') AND type in (N'U')) DROP TABLE [dbo].[sys_login_log];
CREATE TABLE [dbo].[sys_login_log](
    USERNAME VARCHAR(100) NOT NULL,
    LOGIN_TIME DATETIME NOT NULL,
    LOCATION VARCHAR(255),
    IP VARCHAR(100)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_login_log, null, null;
EXEC sp_addextendedproperty 'MS_Description', '用户名', 'SCHEMA', dbo, 'table', sys_login_log, 'column', USERNAME;
EXEC sp_addextendedproperty 'MS_Description', '登录时间', 'SCHEMA', dbo, 'table', sys_login_log, 'column', LOGIN_TIME;
EXEC sp_addextendedproperty 'MS_Description', '登录地点', 'SCHEMA', dbo, 'table', sys_login_log, 'column', LOCATION;
EXEC sp_addextendedproperty 'MS_Description', 'IP地址', 'SCHEMA', dbo, 'table', sys_login_log, 'column', IP;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_menu]') AND type in (N'U')) DROP TABLE [dbo].[sys_menu];
CREATE TABLE [dbo].[sys_menu](
    MENU_ID VARCHAR(20) NOT NULL,
    PARENT_ID VARCHAR(20) NOT NULL DEFAULT  '0',
    MENU_NAME VARCHAR(50) NOT NULL,
    PATH VARCHAR(255),
    COMPONENT VARCHAR(255),
    PERMS VARCHAR(50),
    ICON VARCHAR(50),
    TYPE CHAR(2) NOT NULL,
    ORDER_NUM VARCHAR(255),
    CREATE_TIME DATETIME,
    UPDATE_TIME DATETIME,
    STATUS CHAR(1) NOT NULL DEFAULT  '1',
    CREATE_USERNAME VARCHAR(100),
    CREATE_USER_ID VARCHAR(255),
    UPDATE_USERNAME VARCHAR(100),
    UPDATE_USER_ID VARCHAR(255),
    PRIMARY KEY (MENU_ID)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_menu, null, null;
EXEC sp_addextendedproperty 'MS_Description', '菜单/按钮ID', 'SCHEMA', dbo, 'table', sys_menu, 'column', MENU_ID;
EXEC sp_addextendedproperty 'MS_Description', '上级菜单ID', 'SCHEMA', dbo, 'table', sys_menu, 'column', PARENT_ID;
EXEC sp_addextendedproperty 'MS_Description', '菜单/按钮名称', 'SCHEMA', dbo, 'table', sys_menu, 'column', MENU_NAME;
EXEC sp_addextendedproperty 'MS_Description', '对应路由path', 'SCHEMA', dbo, 'table', sys_menu, 'column', PATH;
EXEC sp_addextendedproperty 'MS_Description', '对应路由组件component', 'SCHEMA', dbo, 'table', sys_menu, 'column', COMPONENT;
EXEC sp_addextendedproperty 'MS_Description', '权限标识', 'SCHEMA', dbo, 'table', sys_menu, 'column', PERMS;
EXEC sp_addextendedproperty 'MS_Description', '图标', 'SCHEMA', dbo, 'table', sys_menu, 'column', ICON;
EXEC sp_addextendedproperty 'MS_Description', '类型 0菜单 1按钮', 'SCHEMA', dbo, 'table', sys_menu, 'column', TYPE;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_menu, 'column', ORDER_NUM;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', sys_menu, 'column', CREATE_TIME;
EXEC sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', dbo, 'table', sys_menu, 'column', UPDATE_TIME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_menu, 'column', STATUS;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_menu, 'column', CREATE_USERNAME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_menu, 'column', CREATE_USER_ID;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_menu, 'column', UPDATE_USERNAME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_menu, 'column', UPDATE_USER_ID;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_role]') AND type in (N'U')) DROP TABLE [dbo].[sys_role];
CREATE TABLE [dbo].[sys_role](
    ROLE_ID VARCHAR(20) NOT NULL,
    ROLE_NAME VARCHAR(10) NOT NULL,
    REMARK VARCHAR(100),
    CREATE_TIME DATETIME NOT NULL,
    UPDATE_TIME DATETIME,
    STATUS TINYINT UNSIGNED NOT NULL DEFAULT  1,
    CREATE_USERNAME VARCHAR(100),
    CREATE_USER_ID VARCHAR(20),
    UPDATE_USERNAME VARCHAR(100),
    UPDATE_USER_ID VARCHAR(20),
    DATA_SCOPE TINYINT UNSIGNED NOT NULL DEFAULT  0,
    PRIMARY KEY (ROLE_ID)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_role, null, null;
EXEC sp_addextendedproperty 'MS_Description', '角色ID', 'SCHEMA', dbo, 'table', sys_role, 'column', ROLE_ID;
EXEC sp_addextendedproperty 'MS_Description', '角色名称', 'SCHEMA', dbo, 'table', sys_role, 'column', ROLE_NAME;
EXEC sp_addextendedproperty 'MS_Description', '角色描述', 'SCHEMA', dbo, 'table', sys_role, 'column', REMARK;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', sys_role, 'column', CREATE_TIME;
EXEC sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', dbo, 'table', sys_role, 'column', UPDATE_TIME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_role, 'column', STATUS;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_role, 'column', CREATE_USERNAME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_role, 'column', CREATE_USER_ID;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_role, 'column', UPDATE_USERNAME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_role, 'column', UPDATE_USER_ID;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_role, 'column', DATA_SCOPE;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_role_menu]') AND type in (N'U')) DROP TABLE [dbo].[sys_role_menu];
CREATE TABLE [dbo].[sys_role_menu](
    ROLE_ID VARCHAR(20) NOT NULL,
    MENU_ID VARCHAR(20) NOT NULL,
    PRIMARY KEY (ROLE_ID,MENU_ID)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_role_menu, null, null;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_role_menu, 'column', ROLE_ID;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_role_menu, 'column', MENU_ID;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_test]') AND type in (N'U')) DROP TABLE [dbo].[sys_test];
CREATE TABLE [dbo].[sys_test](
    ID VARCHAR(20) NOT NULL,
    FIELD1 VARCHAR(20) NOT NULL,
    FIELD2 INT NOT NULL,
    FIELD3 VARCHAR(100) NOT NULL,
    CREATE_TIME DATETIME NOT NULL,
    PRIMARY KEY (ID)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_test, null, null;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_test, 'column', ID;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_test, 'column', FIELD1;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_test, 'column', FIELD2;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_test, 'column', FIELD3;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_test, 'column', CREATE_TIME;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_user]') AND type in (N'U')) DROP TABLE [dbo].[sys_user];
CREATE TABLE [dbo].[sys_user](
    USER_ID VARCHAR(20) NOT NULL,
    USERNAME VARCHAR(50),
    PASSWORD VARCHAR(128),
    DEPT_ID VARCHAR(20),
    EMAIL VARCHAR(128),
    MOBILE VARCHAR(20),
    STATUS TINYINT UNSIGNED DEFAULT  1,
    LAST_LOGIN_TIME DATETIME,
    SSEX TINYINT UNSIGNED DEFAULT  2,
    DESCRIPTION VARCHAR(100),
    AVATAR VARCHAR(500),
    CREATE_USERNAME VARCHAR(100),
    CREATE_USER_ID VARCHAR(20),
    CREATE_TIME DATETIME,
    UPDATE_USERNAME VARCHAR(100),
    UPDATE_TIME DATETIME,
    UPDATE_USER_ID VARCHAR(20),
    PRIMARY KEY (USER_ID)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_user, null, null;
EXEC sp_addextendedproperty 'MS_Description', '用户ID', 'SCHEMA', dbo, 'table', sys_user, 'column', USER_ID;
EXEC sp_addextendedproperty 'MS_Description', '用户名', 'SCHEMA', dbo, 'table', sys_user, 'column', USERNAME;
EXEC sp_addextendedproperty 'MS_Description', '密码', 'SCHEMA', dbo, 'table', sys_user, 'column', PASSWORD;
EXEC sp_addextendedproperty 'MS_Description', '部门ID', 'SCHEMA', dbo, 'table', sys_user, 'column', DEPT_ID;
EXEC sp_addextendedproperty 'MS_Description', '邮箱', 'SCHEMA', dbo, 'table', sys_user, 'column', EMAIL;
EXEC sp_addextendedproperty 'MS_Description', '联系电话', 'SCHEMA', dbo, 'table', sys_user, 'column', MOBILE;
EXEC sp_addextendedproperty 'MS_Description', '状态 0无效 1有效', 'SCHEMA', dbo, 'table', sys_user, 'column', STATUS;
EXEC sp_addextendedproperty 'MS_Description', '最近访问时间', 'SCHEMA', dbo, 'table', sys_user, 'column', LAST_LOGIN_TIME;
EXEC sp_addextendedproperty 'MS_Description', '性别 0男 1女 2保密', 'SCHEMA', dbo, 'table', sys_user, 'column', SSEX;
EXEC sp_addextendedproperty 'MS_Description', '描述', 'SCHEMA', dbo, 'table', sys_user, 'column', DESCRIPTION;
EXEC sp_addextendedproperty 'MS_Description', '用户头像', 'SCHEMA', dbo, 'table', sys_user, 'column', AVATAR;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_user, 'column', CREATE_USERNAME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_user, 'column', CREATE_USER_ID;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', sys_user, 'column', CREATE_TIME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_user, 'column', UPDATE_USERNAME;
EXEC sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', dbo, 'table', sys_user, 'column', UPDATE_TIME;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_user, 'column', UPDATE_USER_ID;


CREATE UNIQUE INDEX username_index ON sys_user(USERNAME);

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_user_config]') AND type in (N'U')) DROP TABLE [dbo].[sys_user_config];
CREATE TABLE [dbo].[sys_user_config](
    USER_ID VARCHAR(20) NOT NULL,
    THEME VARCHAR(10),
    LAYOUT VARCHAR(10),
    MULTI_PAGE CHAR(1),
    FIX_SIDERBAR CHAR(1),
    FIX_HEADER CHAR(1),
    COLOR VARCHAR(20),
    PRIMARY KEY (USER_ID)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_user_config, null, null;
EXEC sp_addextendedproperty 'MS_Description', '用户ID', 'SCHEMA', dbo, 'table', sys_user_config, 'column', USER_ID;
EXEC sp_addextendedproperty 'MS_Description', '系统主题 dark暗色风格，light明亮风格', 'SCHEMA', dbo, 'table', sys_user_config, 'column', THEME;
EXEC sp_addextendedproperty 'MS_Description', '系统布局 side侧边栏，head顶部栏', 'SCHEMA', dbo, 'table', sys_user_config, 'column', LAYOUT;
EXEC sp_addextendedproperty 'MS_Description', '页面风格 1多标签页 0单页', 'SCHEMA', dbo, 'table', sys_user_config, 'column', MULTI_PAGE;
EXEC sp_addextendedproperty 'MS_Description', '页面滚动是否固定侧边栏 1固定 0不固定', 'SCHEMA', dbo, 'table', sys_user_config, 'column', FIX_SIDERBAR;
EXEC sp_addextendedproperty 'MS_Description', '页面滚动是否固定顶栏 1固定 0不固定', 'SCHEMA', dbo, 'table', sys_user_config, 'column', FIX_HEADER;
EXEC sp_addextendedproperty 'MS_Description', '主题颜色 RGB值', 'SCHEMA', dbo, 'table', sys_user_config, 'column', COLOR;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sys_user_role]') AND type in (N'U')) DROP TABLE [dbo].[sys_user_role];
CREATE TABLE [dbo].[sys_user_role](
    USER_ID VARCHAR(20) NOT NULL,
    ROLE_ID VARCHAR(20) NOT NULL,
    PRIMARY KEY (USER_ID,ROLE_ID)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', sys_user_role, null, null;
EXEC sp_addextendedproperty 'MS_Description', '用户ID', 'SCHEMA', dbo, 'table', sys_user_role, 'column', USER_ID;
EXEC sp_addextendedproperty 'MS_Description', '角色ID', 'SCHEMA', dbo, 'table', sys_user_role, 'column', ROLE_ID;

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[t_chat_msg]') AND type in (N'U')) DROP TABLE [dbo].[t_chat_msg];
CREATE TABLE [dbo].[t_chat_msg](
    id VARCHAR(20) NOT NULL,
    code TINYINT UNSIGNED DEFAULT  0,
    send_id VARCHAR(20) NOT NULL,
    accept_id VARCHAR(20),
    msg VARCHAR(500) NOT NULL,
    msg_type TINYINT UNSIGNED NOT NULL DEFAULT  0,
    expand_msg TEXT,
    accept_group VARCHAR(20),
    sign_flag TINYINT UNSIGNED NOT NULL DEFAULT  0,
    create_time DATETIME NOT NULL,
    PRIMARY KEY (id)
);

EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', t_chat_msg, null, null;
EXEC sp_addextendedproperty 'MS_Description', 'id', 'SCHEMA', dbo, 'table', t_chat_msg, 'column', id;
EXEC sp_addextendedproperty 'MS_Description', '', 'SCHEMA', dbo, 'table', t_chat_msg, 'column', code;
EXEC sp_addextendedproperty 'MS_Description', '发送者的id', 'SCHEMA', dbo, 'table', t_chat_msg, 'column', send_id;
EXEC sp_addextendedproperty 'MS_Description', '接收者的id', 'SCHEMA', dbo, 'table', t_chat_msg, 'column', accept_id;
EXEC sp_addextendedproperty 'MS_Description', '消息', 'SCHEMA', dbo, 'table', t_chat_msg, 'column', msg;
EXEC sp_addextendedproperty 'MS_Description', '消息类型', 'SCHEMA', dbo, 'table', t_chat_msg, 'column', msg_type;
EXEC sp_addextendedproperty 'MS_Description', '消息扩展内容', 'SCHEMA', dbo, 'table', t_chat_msg, 'column', expand_msg;
EXEC sp_addextendedproperty 'MS_Description', '接收组', 'SCHEMA', dbo, 'table', t_chat_msg, 'column', accept_group;
EXEC sp_addextendedproperty 'MS_Description', '接收状态', 'SCHEMA', dbo, 'table', t_chat_msg, 'column', sign_flag;
EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', dbo, 'table', t_chat_msg, 'column', create_time;

