-- 2014-09-25 weinianjie
create database plt;
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
	registIp varchar(32) comment '注册IP',
	registTime datetime comment '注册时间',
	lastLoginIp varchar(32) comment '最后登录IP',
	lastLoginTime datetime comment '最后登录时间',
	remark varchar(10240) comment '详细介绍',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '用户表';

insert into user values('10000', 'admin', 'admin', md5('admin'), '', 0, 71, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10001', 'teacher1', 'teacher1', md5('admin'), '', 1, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10002', 'teacher2', 'teacher2', md5('admin'), '', 1, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10003', 'teacher3', 'teacher3', md5('admin'), '', 1, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10004', 'teacher4', 'teacher4', md5('admin'), '', 2, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10005', 'teacher5', 'teacher5', md5('admin'), '', 2, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10006', 'student1', 'student1', md5('admin'), '', 2, 1, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10007', 'student2', 'student2', md5('admin'), '', 0, 1, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10008', 'teacher6', 'teacher6', md5('admin'), '', 0, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10009', 'teacher7', 'teacher7', md5('admin'), '', 0, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10010', 'teacher8', 'teacher8', md5('admin'), '', 0, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10011', 'teacher9', 'teacher9', md5('admin'), '', 0, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10012', 'teacher10', 'teacher10', md5('admin'), '', 0, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10013', 'teacher11', 'teacher11', md5('admin'), '', 0, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10014', 'teacher12', 'teacher12', md5('admin'), '', 0, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10015', 'teacher13', 'teacher13', md5('admin'), '', 0, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10016', 'teacher14', 'teacher14', md5('admin'), '', 0, 3, 10000, 10000, null, null, null, null, null, now(), now());
insert into user values('10017', 'teacher15', 'teacher14', md5('admin'), '', 0, 3, 10000, 10000, null, null, null, null, null, now(), now());

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

-- 年级表 --
drop table if exists grade;
create table grade(
	id varchar(32) not null comment 'UUID',
	gradeName varchar(64) not null comment '年级名称',
	levelName varchar(64) not null comment '年级级别',
	gradeGroup int(10) comment '年级类型',
	gradeEnName varchar(16) not null comment '年级英文名',
	levelEnName varchar(16) not null comment '级别英文名',
	priority int(10) comment '优先级',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '年级表';

insert into grade values('10000', '小学', '一年级', 1, 'xiaoxue', 'level1', 0);
insert into grade values('10001', '小学', '二年级', 1, 'xiaoxue', 'level2', 1);
insert into grade values('10002', '小学', '三年级', 1, 'xiaoxue', 'level3', 2);
insert into grade values('10003', '小学', '四年级', 1, 'xiaoxue', 'level4', 3);
insert into grade values('10004', '小学', '五年级', 1, 'xiaoxue', 'level5', 4);
insert into grade values('10005', '小学', '六年级', 1, 'xiaoxue', 'level6', 5);
insert into grade values('10006', '初中', '一年级', 2, 'chuzhong', 'level1', 6);
insert into grade values('10007', '初中', '二年级', 2, 'chuzhong', 'level2', 7);
insert into grade values('10008', '初中', '三年级', 2, 'chuzhong', 'level3', 8);
insert into grade values('10009', '高中', '一年级', 4, 'gaozhong', 'level1', 9);
insert into grade values('10010', '高中', '二年级', 4, 'gaozhong', 'level2', 10);
insert into grade values('10011', '高中', '三年级', 4, 'gaozhong', 'level3', 11);

-- 科目表 --
drop table if exists subject;
create table subject(
	id varchar(32) not null comment 'UUID',
	subjectName varchar(64) not null comment '科目名称',
	subjectEnName varchar(16) not null comment '科目英文名',
	priority int(10) comment '优先级',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '科目表';

insert into subject values('10000', '语文', 'yuwen', 0);
insert into subject values('10001', '数学', 'shuxue', 1);
insert into subject values('10002', '英语', 'yingyu', 2);
insert into subject values('10003', '物理', 'wuli', 3);
insert into subject values('10004', '化学', 'huaxue', 4);
insert into subject values('10005', '生物', 'shengwu', 5);
insert into subject values('10006', '地理', 'dili', 6);
insert into subject values('10007', '政治', 'zhengzhi', 7);

-- 年级-科目表 --
drop table if exists grade_to_subject;
create table grade_to_subject(
	gradeId varchar(32) comment '年级ID',
	subjectId varchar(32) comment '科目ID',
	primary key (gradeId, subjectId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '年级-科目表';

insert into grade_to_subject values('10000', '10000');
insert into grade_to_subject values('10000', '10001');
insert into grade_to_subject values('10001', '10000');
insert into grade_to_subject values('10001', '10001');
insert into grade_to_subject values('10002', '10000');
insert into grade_to_subject values('10002', '10001');
insert into grade_to_subject values('10003', '10000');
insert into grade_to_subject values('10003', '10001');
insert into grade_to_subject values('10004', '10000');
insert into grade_to_subject values('10004', '10001');
insert into grade_to_subject values('10004', '10002');
insert into grade_to_subject values('10005', '10000');
insert into grade_to_subject values('10005', '10001');
insert into grade_to_subject values('10005', '10002');
insert into grade_to_subject values('10006', '10000');
insert into grade_to_subject values('10006', '10001');
insert into grade_to_subject values('10006', '10002');
insert into grade_to_subject values('10006', '10007');
insert into grade_to_subject values('10007', '10000');
insert into grade_to_subject values('10007', '10001');
insert into grade_to_subject values('10007', '10002');
insert into grade_to_subject values('10007', '10007');
insert into grade_to_subject values('10008', '10000');
insert into grade_to_subject values('10008', '10001');
insert into grade_to_subject values('10008', '10002');
insert into grade_to_subject values('10008', '10003');
insert into grade_to_subject values('10008', '10004');
insert into grade_to_subject values('10008', '10005');
insert into grade_to_subject values('10008', '10006');
insert into grade_to_subject values('10008', '10007');
insert into grade_to_subject values('10009', '10000');
insert into grade_to_subject values('10009', '10001');
insert into grade_to_subject values('10009', '10002');
insert into grade_to_subject values('10009', '10003');
insert into grade_to_subject values('10009', '10004');
insert into grade_to_subject values('10009', '10005');
insert into grade_to_subject values('10009', '10006');
insert into grade_to_subject values('10010', '10000');
insert into grade_to_subject values('10010', '10001');
insert into grade_to_subject values('10010', '10002');
insert into grade_to_subject values('10010', '10003');
insert into grade_to_subject values('10010', '10004');
insert into grade_to_subject values('10010', '10005');
insert into grade_to_subject values('10010', '10006');
insert into grade_to_subject values('10011', '10000');
insert into grade_to_subject values('10011', '10001');
insert into grade_to_subject values('10011', '10002');
insert into grade_to_subject values('10011', '10003');
insert into grade_to_subject values('10011', '10004');
insert into grade_to_subject values('10011', '10005');
insert into grade_to_subject values('10011', '10006');

-- 学校表 --
drop table if exists school;
create table school(
	id varchar(32) not null comment 'UUID',
	schoolName varchar(128) not null comment '学校名称',
	schoolEnName varchar(128) comment '学校拼音',
	areaCode varchar(32) not null comment '地区编码',
	schoolGrade int(10) unsigned not null default 1 comment '学校级别',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '学校表';

insert into school values('10000', '实验1小', null, '440300', 1);
insert into school values('10001', '实验2小', null, '440300', 1);
insert into school values('10002', '实验3小', null, '440300', 1);
insert into school values('10003', '实验4小', null, '440300', 1);
insert into school values('10006', '深圳1中', null, '440300', 2);
insert into school values('10007', '深圳2中', null, '440300', 2);
insert into school values('10008', '深圳3中', null, '440300', 2);
insert into school values('10009', '深圳4中', null, '440300', 2);
insert into school values('10010', '深圳5中', null, '440300', 6);
insert into school values('10011', '高级1中', null, '440300', 4);
insert into school values('10012', '高级2中', null, '440300', 4);
insert into school values('10013', '高级3中', null, '440300', 4);
insert into school values('10014', '高级4中', null, '440300', 4);
insert into school values('10015', '高级5中', null, '440300', 4);
insert into school values('10016', '高级6中', null, '440300', 4);
insert into school values('10017', '高级7中', null, '440300', 4);
insert into school values('10018', '高级8中', null, '440300', 4);

-- 行政区划表 --
drop table if exists web_area;
create table web_area(
	id varchar(32) not null comment 'UUID',
	areaName varchar(64) not null comment '区域名称',
	areaCode varchar(32) not null comment '地区编码',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '行政区划表';

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
	live int(10) default 0 not null comment '是否直播',
	endTime datetime comment '结束时间',
	gradeId varchar(32) not null comment '所属年级',
	subjectId varchar(32) not null comment '所属科目',
	remark varchar(10240) comment '具体描述',
	saleCount int(10) default 0 not null comment '销售量',
	collectCount int(10) default 0 not null comment '收藏量',
	commentCount int(10) default 0 not null comment '评论数量',
	chapterCount int(10) default 0 not null comment '课时数量',
	ownerId varchar(32) not null comment '所有者',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '课程主表';

insert into course values('10000', '马克思主义', '王老师', 10000, 0, '2014-12-12', '10000', '10000', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10001', '相对论', '王老师', 10000, 0, '2014-12-12', '10000', '10001', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10002', '微积分', '王老师', 10000, 0, '2014-12-12', '10000', '10001', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10003', '高等数学', '王老师', 10000, 1, '2014-12-12', '10000', '10001', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10004', '数字逻辑', '王老师', 10000, 1, '2014-12-12', '10006', '10003', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10005', '线性代数', '王老师', 10000, 0, '2014-12-12', '10006', '10003', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10006', '蛋白质', '王老师', 10000, 1, '2014-12-12', '10006', '10003', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10007', '细胞核', '王老师', 10000, 1, '2014-12-12', '10006', '10003', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10008', '世界历史', '王老师', 10000, 0, '2014-12-12', '10009', '10003', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10009', '唯物主义', '王老师', 10000, 1, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10010', '经济学入门', '王老师', 10000, 1, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10011', '货币战争', '王老师', 10000, 1, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10012', '论持久战', '王老师', 10000, 1, '2014-12-12', '10000', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10013', '大头儿子小头爸爸', '王老师', 10000, 1, '2014-12-12', '10006', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values('10014', '西游记', '王老师', 10000, 0, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '西游记2', '王老师', 10000, 0, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '西游记3', '王老师', 10000, 0, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '西游记4', '王老师', 10000, 0, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '西游记5', '王老师', 10000, 0, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '西游记6', '王老师', 10000, 0, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '大头儿子小头爸爸2', '王老师', 10000, 0, '2014-12-12', '10006', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '大头儿子小头爸爸3', '王老师', 10000, 0, '2014-12-12', '10006', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '大头儿子小头爸爸4', '王老师', 10000, 0, '2014-12-12', '10006', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '大头儿子小头爸爸5', '王老师', 10000, 0, '2014-12-12', '10006', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '大头儿子小头爸爸6', '王老师', 10000, 0, '2014-12-12', '10006', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '经济学入门2', '王老师', 10000, 0, '2014-12-12', '10000', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '经济学入门3', '王老师', 10000, 0, '2014-12-12', '10000', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '经济学入门4', '王老师', 10000, 0, '2014-12-12', '10000', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '经济学入门5', '王老师', 10000, 0, '2014-12-12', '10000', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());
insert into course values(replace(uuid(),'-',''), '经济学入门6', '王老师', 10000, 0, '2014-12-12', '10000', '10004', 'xxx', 1, 2, 3, 4, '10001', now(), now());

-- 订单表 --
drop table if exists web_order;
create table web_order(
	id varchar(32) not null comment 'UUID',
	userId varchar(32) not null comment '用户ID',
	courseId varchar(32) not null comment '课程ID',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '订单表';

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

insert into course_comment values('10000', '10000', '10000', '课程还可以', now(), now());
insert into course_comment values('10001', '10001', '10000', '非常喜欢', now(), now());
insert into course_comment values('10002', '10002', '10000', '还行', now(), now());

-- 课程收藏表 --
drop table if exists course_collection;
create table course_collection(
	userId varchar(32) not null comment '用户ID',
	courseId varchar(32) not null comment '课程ID',
	cts datetime comment '创建时间',	
	primary key (userId, courseId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '课程收藏表';

-- 章节表 --
drop table if exists course_chapter;
create table course_chapter(
	id varchar(32) not null comment 'UUID',
	courseId varchar(32) not null comment '课程ID',
	remark varchar(1024) comment '描述',
	priority int(10) comment '优先级',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '章节表';

insert into course_chapter values('10000', '10000', 'remark1', 0, now(), now());
insert into course_chapter values('10001', '10000', 'remark2', 1, now(), now());
insert into course_chapter values('10002', '10000', 'remark3', 2, now(), now());
insert into course_chapter values('10003', '10000', 'remark4', 3, now(), now());

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

insert into course_chapter_to_resource values('10000', '马克思主义0', '10000', '10000', 1, 1);
insert into course_chapter_to_resource values('10001', '张老师直播1', '10000', '10000', 2, 2);
insert into course_chapter_to_resource values('10002', '文档1', '10000', '10000', 3, 3);
insert into course_chapter_to_resource values('10003', '测验1', '10000', '10000', 4, 4);

-- 点播文件表 --
drop table if exists resource_file;
create table resource_file(
	id varchar(32) not null comment 'UUID',
	title varchar(128) not null comment '文件名称',
	url varchar(255) comment '资源路径',
	size bigint default 0 not null comment '资源大小',
	duration int(10) default 0 not null comment '视频时长',
	groupId varchar(32) comment '所属组ID',
	ownerId varchar(32) not null comment '所有者ID',
	remark varchar(1024) comment '备注',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '点播文件表';

insert into resource_file values('10000', '马克思主义0', '', 1024, 66, '10000', '10000', '', now(), now());
insert into resource_file values('10001', '马克思主义1', '', 1024, 66, '10000', '10008', '', now(), now());
insert into resource_file values('10002', '马克思主义2', '', 1024, 66, '10000', '10009', '', now(), now());
insert into resource_file values('10003', '马克思主义3', '', 1024, 66, '10000', '10010', '', now(), now());
insert into resource_file values('10004', '马克思主义4', '', 1024, 66, '10000', '10010', '', now(), now());
insert into resource_file values('10005', '马克思主义5', '', 1024, 66, '10000', '10010', '', now(), now());
insert into resource_file values('10006', '马克思主义6', '', 1024, 66, '10000', '10000', '', now(), now());
insert into resource_file values('10007', '马克思主义7', '', 1024, 66, '10000', '10000', '', now(), now());
insert into resource_file values('10008', '马克思主义8', '', 1024, 66, '10000', '10000', '', now(), now());
insert into resource_file values('10009', '马克思主义9', '', 1024, 66, '10000', '10007', '', now(), now());
insert into resource_file values('10010', '马克思主义10', '', 1024, 66, '10000', '10007', '', now(), now());
insert into resource_file values('10011', '马克思主义11', '', 1024, 66, '10000', '10007', '', now(), now());

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

insert into resource_live values('10000', '张老师直播1', null, '2014-12-12 13:00', '2014-12-12 15:00', 7200, null, '10000', now(), now());


-- 直播资源组表 --
drop table if exists resource_live_group;
create table resource_live_group(
	id varchar(32) not null comment 'UUID',
	groupName varchar(128) not null comment '组名',
	ownerId varchar(32) not null comment '所有者ID',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '直播资源组表';


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

insert into resource_doc values('10000', '文档1', '', 1, '10000', '10000', '', now(), now());


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
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '测验表';

insert into resource_train values('10000', '模拟测验1', now(), now());

-- 测验记录表 --
drop table if exists resource_train_history;
create table resource_train_history(
	id varchar(32) not null comment 'UUID',
	userId varchar(32) not null comment '用户ID',
	chapterId varchar(32) not null comment '章节ID',
	trainId varchar(32) not null comment '测验ID',
	answer varchar(1024) not null comment '答案',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',		
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '测验记录表';

-- 习题表 --
drop table if exists resource_train_question;
create table resource_train_question(
	id varchar(32) not null comment 'UUID',
	title varchar(128) not null comment '题目名',
	remark varchar(512) comment '题目描述',
	questionType int(10) default 0 not null comment '题目类型',
	answer varchar(1024) comment '标准答案',
	score int(10) not null comment '题目分值',
	extends varchar(1024) comment '扩展存储',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '习题表';

-- 测验-习题关联表 --
drop table if exists resource_train_to_question;
create table resource_train_to_question(
	trainId varchar(32) not null comment '测验ID',
	questionId varchar(32) not null comment '习题ID',
	primary key (trainId, questionId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '测验-习题关联表';

-- 表 --
-- drop table if exists course;
-- create table course(
--	id varchar(32) not null comment 'UUID',
--	primary key (id)
-- ) engine=InnoDB default charset=utf8 collate=utf8_bin comment '表';