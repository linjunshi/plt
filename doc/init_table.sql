-- 2014-09-25 weinianjie
set names utf8;
create database if not exists plt;
use plt;

-- 用户表 --
drop table if exists user;
create table user(
	id varchar(32) not null comment 'UUID',
	showName varchar(128) not null comment '显示名',
	username varchar(64) not null comment '用户名',
	password varchar(64) not null comment '密码',
	url varchar(256) comment '头像',
	gender int(10) default 0 not null comment '性别',
	role int(10) not null default 1 comment '角色',
	schoolId varchar(32) comment '所属学校',
	subjectId varchar(32) comment '所属学科',
	email varchar(64) not null comment '邮箱',
	phone varchar(16) comment '电话',
	idCard varchar(32) comment '身份证',
	registIp varchar(32) comment '注册IP',
	registTime datetime comment '注册时间',
	lastLoginIp varchar(32) comment '最后登录IP',
	lastLoginTime datetime comment '最后登录时间',
	remark varchar(10240) comment '详细介绍',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '用户表';

-- 用户教育信息 --
drop table if exists user_education;
create table user_education(
	userId varchar(32) not null comment '用户ID',
	education int(10) default 0 not null comment '学历',
	positional int(10) default 0 not null comment '职称',
	graduateSchool varchar(128) comment '毕业院校',
	primary key (userId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '用户教育信息表';

-- 用户扩展信息 --
drop table if exists user_extends;
create table user_extends(
	userId varchar(32) not null comment '用户ID',
	birthday date comment '出生日期',
	nativePlace varchar(128) comment '籍贯信息',
	primary key (userId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '用户扩展信息表';

-- 用户临时信息 --
drop table if exists user_tmp;
create table user_tmp(
	userId varchar(32) not null comment '用户ID',
	activeCode varchar(128) comment '激活码',
	cts datetime comment '创建时间',
	primary key (userId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '用户临时信息表';

-- 用户关系信息 --
drop table if exists user_relation;
create table user_relation(
	userId1 varchar(32) not null comment '发起用户',
	userId2 varchar(32) not null comment '接收用户',
	applyMsg varchar(256) comment '申请消息',
	returnMsg varchar(256) comment '反馈消息',
	result int(10) not null comment '结果',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (userId1, userId2)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '用户关系表';

-- 学校表 --
drop table if exists school;
create table school(
	id varchar(32) not null comment 'UUID',
	schoolName varchar(128) not null comment '学校名称',
	schoolEnName varchar(128) comment '学校拼音',
	areaCode varchar(32) not null comment '地区编码',
	schoolGrade int(10) unsigned not null default 1 comment '学校级别',
	primary key (id),
	key (areaCode)
) engine=Myisam default charset=utf8 collate=utf8_bin comment '学校表';

-- 行政区划表 --
drop table if exists web_area;
create table web_area(
	areaCode varchar(6) not null comment '区域编码',
	areaName varchar(64) not null comment '区域名称',
	areaEnName varchar(128) comment '区域拼音',
	areaType varchar(8) comment '区域类型',
	primary key (areaCode),
	key (areaName),
	key(areaEName)
) engine=Myisam default charset=utf8 collate=utf8_bin comment '行政区划表';

-- 年级表 --
drop table if exists grade;
create table grade(
	id varchar(32) not null comment 'UUID',
	gradeName varchar(64) not null comment '年级名称',
	levelName varchar(64) not null comment '年级级别',
	gradeGroup int(10) comment '年级类型',
	gradeEnName varchar(16) not null comment '年级英文名',
	levelEnName varchar(16) not null comment '级别英文名',
	priority int(10) default 0 not null comment '优先级',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '年级表';

-- 科目表 --
drop table if exists subject;
create table subject(
	id varchar(32) not null comment 'UUID',
	subjectName varchar(64) not null comment '科目名称',
	subjectEnName varchar(16) not null comment '科目英文名',
	priority int(10) default 0 not null comment '优先级',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '科目表';

-- 年级-科目表 --
drop table if exists grade_to_subject;
create table grade_to_subject(
	gradeId varchar(32) comment '年级ID',
	subjectId varchar(32) comment '科目ID',
	primary key (gradeId, subjectId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '年级-科目表';

-- 单元表 --
drop table if exists lesson_unit;
create table lesson_unit(
	id varchar(32) not null comment 'UUID',
	unitName varchar(128) not null comment '单元名称',
	gradeId varchar(32) comment '年级ID',
	subjectId varchar(32) comment '科目ID',
	term int(10) comment '上下学期',
	priority int(10) default 0 not null comment '优先级', 
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '单元表';

-- 一体机表 --
drop table if exists machine;
create table machine(
	id varchar(32) not null comment 'UUID',
	machineName varchar(128) not null comment '一体机名称',
	uuidFlag varchar(32) not null comment '一体机唯一标识',
	ownerId varchar(32) not null comment '所有者ID',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '一体机表';

-- 课程主表 --
drop table if exists course;
create table course(
	id varchar(32) not null comment 'UUID',
	courseName varchar(128) not null comment '课程名称',
	teacher varchar(32) comment '主讲老师',
	price int(10) not null comment '价格',
	url varchar(256) comment '封面',
	live int(10) default 0 not null comment '是否直播',
	endTime datetime comment '结束时间',
	gradeId varchar(32) not null comment '所属年级',
	subjectId varchar(32) not null comment '所属科目',
	unitId varchar(32) comment '所属单元',
	remark varchar(10240) comment '具体描述',
	limitCount int(10) default 0 not null comment '限购数量',
	saleCount int(10) default 0 not null comment '销售量',
	collectCount int(10) default 0 not null comment '收藏量',
	commentCount int(10) default 0 not null comment '评论数量',
	chapterCount int(10) default 0 not null comment '课时数量',
	playCount int(10) default 0 not null comment '播放次数',
	courseType int(10) default 0 not null comment '课程类型',
	ownerId varchar(32) not null comment '所有者',
	status int(10) not null default 0 comment '状态',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '课程主表';

-- 订单表 --
drop table if exists web_order;
create table web_order(
	id varchar(32) not null comment 'UUID',
	userId varchar(32) not null comment '用户ID',
	courseId varchar(32) not null comment '课程ID',
	price int(10) not null default 0 comment '价格',
	status int(10) not null default 0 comment '状态',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '订单表';

insert into web_order values('10000', '10000', '10000', now(), now());

-- 课程评论表 --
drop table if exists course_comment;
create table course_comment(
	id varchar(32) not null comment 'UUID',
	userId varchar(32) not null comment '用户ID',
	courseId varchar(32) not null comment '课程ID',
	remark varchar(1024) not null comment '评论内容',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '课程评论表';

-- 课程收藏表 --
drop table if exists course_collection;
create table course_collection(
	userId varchar(32) not null comment '用户ID',
	courseId varchar(32) not null comment '课程ID',
	cts datetime comment '创建时间',	
	primary key (userId, courseId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '课程收藏表';

-- 课程参与历史表 --
drop table if exists course_attend_history;
create table course_attend_history(
	id varchar(32) not null comment 'UUID',
	userId varchar(32) not null comment '用户ID',
	courseId varchar(32) not null comment '课程ID',
	attendType int(10) not null comment '参与类型',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '课程参与历史表';

-- 章节表 --
drop table if exists course_chapter;
create table course_chapter(
	id varchar(32) not null comment 'UUID',
	courseId varchar(32) not null comment '课程ID',
	remark varchar(1024) comment '描述',
	priority int(10) default 0 not null comment '优先级',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '章节表';

-- 章节关联资源表 --
drop table if exists course_chapter_to_resource;
create table course_chapter_to_resource(
	id varchar(32) not null comment 'UUID',
	title varchar(1024) not null comment '标题',
	chapterId varchar(32) not null comment '章节ID',
	resourceId varchar(32) not null comment '资源ID',
	resourceType int(10) not null comment '资源类型',
	priority int(10) comment '排序',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '章节关联资源表';

-- 视频文件表 --
drop table if exists resource_video;
create table resource_video(
	id varchar(32) not null comment 'UUID',
	title varchar(128) not null comment '文件名称',
	url varchar(255) comment '资源路径',
	size bigint default 0 not null comment '资源大小',
	duration varchar(32) default '' comment '录制时长',
	ownerId varchar(32) not null comment '所有者ID',
	remark varchar(1024) comment '备注',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '视频文件表';

-- 点播文件表 --
drop table if exists resource_file;
create table resource_file(
	id varchar(32) not null comment 'UUID',
	title varchar(128) not null comment '文件名称',
	url varchar(255) comment '资源路径',
	size bigint default 0 not null comment '资源大小',
	duration varchar(32) default '' comment '录制时长',
	groupId varchar(32) comment '所属组ID',
	ownerId varchar(32) not null comment '所有者ID',
	remark varchar(1024) comment '备注',
	status int(10) default 0 not null comment '推送状态，0待推送，1推送中，2推送异常，3推送完成',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '点播文件表';

-- 点播文件组表 --
drop table if exists resource_file_group;
create table resource_file_group(
	id varchar(32) not null comment 'UUID',
	groupName varchar(128) not null comment '组名',
	ownerId varchar(32) not null comment '所有者ID',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '点播文件组表';


-- 直播资源表 --
drop table if exists resource_live;
create table resource_live(
	id varchar(32) not null comment 'UUID',
	title varchar(128) not null comment '直播名称',
	url varchar(255) comment '资源路径',
	beginTime datetime comment '开始时间',
	endTime datetime comment '结束时间',
	duration int(10) comment '时长',
	groupId varchar(32) comment '所属组ID',
	ownerId varchar(32) not null comment '所有者ID',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '直播资源表';

-- 直播评分表 --
drop table if exists resource_live_score;
create table resource_live_score(
	id varchar(32) not null comment 'UUID',
	liveId varchar(32) not null comment '直播ID',
	userId varchar(32) not null comment '用户ID',
	score int(10) not null comment '分数',
	cts datetime comment '创建时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '直播评分表';

-- 直播点名表 --
drop table if exists resource_live_call;
create table resource_live_call(
	id varchar(32) not null comment 'UUID',
	liveId varchar(32) not null comment '直播ID',
	callName varchar(32) not null comment '点名标识',
	cts datetime comment '创建时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '直播点名表';

-- 直播答到表 --
drop table if exists resource_live_reply;
create table resource_live_reply(
	id varchar(32) not null comment 'UUID',
	callId varchar(32) not null comment '点名ID',
	userId varchar(32) not null comment '用户ID',
	cts datetime comment '创建时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '直播答到表';


-- 文档资源表 --
drop table if exists resource_doc;
create table resource_doc(
	id varchar(32) not null comment 'UUID',
	title varchar(128) not null comment '文档名',
	url varchar(255) not null comment '资源路径',
	docType int(10) not null comment '资源类型',
	groupId varchar(32) comment '所属组ID',
	ownerId varchar(32) not null comment '所有者ID',
	remark varchar(1024) comment '备注',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '文档资源表';

-- 文档资源组表 --
drop table if exists resource_doc_group;
create table resource_doc_group(
	id varchar(32) not null comment 'UUID',
	groupName varchar(128) not null comment '组名',
	ownerId varchar(32) not null comment '所有者ID',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '文档资源组表';


-- 测验表 --
drop table if exists resource_train;
create table resource_train(
	id varchar(32) not null comment 'UUID',
	title varchar(128) not null comment '测验名',
	ownerId varchar(32) not null comment '所有者',
	del int(10) comment '是否删除',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '测验表';

-- 测验记录表 --
drop table if exists resource_train_history;
create table resource_train_history(
	id varchar(32) not null comment 'UUID',
	userId varchar(32) not null comment '用户ID',
	chapterId varchar(32) not null comment '章节ID',
	trainId varchar(32) not null comment '测验ID',
	questionId varchar(32) not null comment '习题ID',
	answer varchar(256) comment '答案',
	result int(10) not null comment '正确与否1yes2no',
	del int(10) comment '是否删除',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',		
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '测验记录表';

-- 习题表 --
drop table if exists resource_train_question;
create table resource_train_question(
	id varchar(32) not null comment 'UUID',
	topic text not null comment '题目',
	questionType int(10) default 0 not null comment '题目类型',
	answer varchar(256) comment '答案',
	remark varchar(256) comment '详解',
	subjectId varchar(32) comment '所属科目',
	gradeId varchar(32) comment '所属年级',
	unitId varchar(32) comment '所属单元',
	timeLimit int(10) default 0 not null comment '限制时间（s）',
	ownerId varchar(32) not null comment '所有者',
	level int(10) comment '难中易',
	status int(10) comment '状态',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '习题表';

-- 测验-习题关联表 --
drop table if exists resource_train_to_question;
create table resource_train_to_question(
	trainId varchar(32) not null comment '测验ID',
	questionId varchar(32) not null comment '习题ID',
	priority int(10) default 0 not null comment '优先级',
	primary key (trainId, questionId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '测验-习题关联表';

-- 竞赛表 --
drop table if exists competition;
create table competition(
	id varchar(32) not null comment 'UUID',
	title varchar(128) not null comment '竞赛名',
	remark varchar(1024) comment '描述',
	beginTime date comment '竞赛开始时间',
	flag int(10) default 0 not null comment '竞赛类型',
	ownerId varchar(32) not null comment '所有者',
	del int(10) comment '是否删除',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '竞赛表';

-- 竞赛关联习题表 --
drop table if exists competition_to_question;
create table competition_to_question(
	competitionId varchar(32) not null comment '竞赛ID',
	questionId varchar(32) not null comment '习题ID',
	priority int(10) default 0 not null comment '优先级',
	primary key (competitionId, questionId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '竞赛关联习题表';

-- 竞赛报名表 --
drop table if exists competition_attend;
create table competition_attend(
	id varchar(32) not null comment 'UUID',
	userId varchar(32) not null comment '用户ID',
	competitionId varchar(32) comment '竞赛ID',
	cts datetime comment '创建时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '竞赛报名表';

-- 竞赛历史回答表 --
drop table if exists competition_history;
create table competition_history(
	id varchar(32) not null comment 'UUID',
	attendId varchar(32) not null comment '报名表ID',
	questionId varchar(32) not null comment '习题ID',
	answer varchar(256) comment '答案',
	result int(10) not null comment '正确与否1yes2no',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '竞赛历史回答表';

-- 知识点表 --
drop table if exists knowledge;
create table knowledge(
	id varchar(32) not null comment 'UUID',
	code int(32) not null comment '编码',
	level int(16) not null comment '层级',
	knowledgeName varchar(64) not null comment '知识点名称',
	subjectId varchar(32) comment '所属科目',
	gradeId varchar(32) comment '所属年级',	
	unitId varchar(32) comment '所属单元',
	priority int(10) comment '排序',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '知识点表';

-- 习题关联知识点表 --
drop table if exists question_to_knowledge;
create table question_to_knowledge(
	questionId varchar(32) not null comment '习题ID',
	knowledgeId varchar(32) not null comment '知识点ID',
	primary key (questionId, knowledgeId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '习题关联知识点表';

-- 课程关联知识点表 --
drop table if exists course_to_knowledge;
create table course_to_knowledge(
	courseId varchar(32) not null comment '课程ID',
	knowledgeId varchar(32) not null comment '知识点ID',
	primary key (courseId, knowledgeId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '课程关联知识点表';

-- 剧本表 --
drop table if exists story;
create table story(
	id varchar(32) not null comment 'UUID',
	storyName varchar(128) not null comment '剧本名称',
	storyEname varchar(128) not null comment '剧本英文名称',
	url varchar(256) comment '封面',
	duration varchar(32) comment '时长',
	subjectId varchar(32) not null comment '所属科目',
	remark varchar(10240) comment '具体描述',
	limitCount int(10) default 0 not null comment '角色数量',
	storyType int(10) default 0 not null comment '课程类型',
	ownerId varchar(32) not null comment '所有者',
	status int(10) not null default 0 comment '状态',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '剧本表';

-- 剧本参与历史表 --
drop table if exists story_attend_history;
create table story_attend_history(
	id varchar(32) not null comment 'UUID',
	userId varchar(32) not null comment '用户ID',
	storyId varchar(32) not null comment '剧本ID',
	attendType int(10) not null comment '参与类型',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '剧本参与历史表';

-- 表 --
-- drop table if exists course;
-- create table course(
--	id varchar(32) not null comment 'UUID',
--	primary key (id)
-- ) engine=InnoDB default charset=utf8 collate=utf8_bin comment '表';
