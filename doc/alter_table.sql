-- 2014-09-25 weinianjie
create database plt;

use plt;
	
--- 用户表 ---
drop table if exists web_user;
create table web_user(
	id varchar(32) not null comment 'UUID',
	showName varchar(128) not null comment '显示名',
	username varchar(64) not null comment '用户名',
	password varchar(64) not null comment '密码',
	role int(10) not null default 1 comment '角色',
	schoolId varchar(32) comment '所属学校',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '用户表';

insert into web_user values('10000', 'admin', 'admin', md5('admin'), 71, 10000, now(), now());

--- 年级表 ---
drop table if exists web_grade;
create table web_grade(
	id varchar(32) not null comment 'UUID',
	gradeName varchar(64) not null comment '年级名称',
	levelName varchar(64) not null comment '年级级别',
	gradeGroup int(10) comment '年级类型',
	gradeEnName varchar(16) not null comment '年级英文名',
	levelEnName varchar(16) not null comment '级别英文名',
	priority int(10) comment '优先级',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '年级表';

insert into web_grade values('10000', '小学', '一年级', 1, 'xiaoxue', 'level1', 0);
insert into web_grade values('10001', '小学', '二年级', 1, 'xiaoxue', 'level2', 1);
insert into web_grade values('10002', '小学', '三年级', 1, 'xiaoxue', 'level3', 2);
insert into web_grade values('10003', '小学', '四年级', 1, 'xiaoxue', 'level4', 3);
insert into web_grade values('10004', '小学', '五年级', 1, 'xiaoxue', 'level5', 4);
insert into web_grade values('10005', '小学', '六年级', 1, 'xiaoxue', 'level6', 5);
insert into web_grade values('10006', '初中', '一年级', 2, 'chuzhong', 'level1', 6);
insert into web_grade values('10007', '初中', '二年级', 2, 'chuzhong', 'level2', 7);
insert into web_grade values('10008', '初中', '三年级', 2, 'chuzhong', 'level3', 8);
insert into web_grade values('10009', '高中', '一年级', 4, 'gaozhong', 'level1', 9);
insert into web_grade values('10010', '高中', '二年级', 4, 'gaozhong', 'level2', 10);
insert into web_grade values('10011', '高中', '三年级', 4, 'gaozhong', 'level3', 11);

--- 科目表 ---
drop table if exists web_subject;
create table web_subject(
	id varchar(32) not null comment 'UUID',
	subjectName varchar(64) not null comment '科目名称',
	subjectEnName varchar(16) not null comment '科目英文名',
	priority int(10) comment '优先级',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '科目表';

insert into web_subject values('10000', '语文', 'yuwen', 0);
insert into web_subject values('10001', '数学', 'shuxue', 1);
insert into web_subject values('10002', '英语', 'yingyu', 2);
insert into web_subject values('10003', '物理', 'wuli', 3);
insert into web_subject values('10004', '化学', 'huaxue', 4);
insert into web_subject values('10005', '生物', 'shengwu', 5);
insert into web_subject values('10006', '地理', 'dili', 6);
insert into web_subject values('10007', '政治', 'zhengzhi', 7);

--- 年级-科目表 ---
drop table if exists web_grade_subject;
create table web_grade_subject(
	gradeId varchar(32) comment '年级ID',
	subjectId varchar(32) comment '科目ID',
	primary key (gradeId, subjectId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '年级-科目表';

insert into web_grade_subject values('10000', '10000');
insert into web_grade_subject values('10000', '10001');
insert into web_grade_subject values('10001', '10000');
insert into web_grade_subject values('10001', '10001');
insert into web_grade_subject values('10002', '10000');
insert into web_grade_subject values('10002', '10001');
insert into web_grade_subject values('10003', '10000');
insert into web_grade_subject values('10003', '10001');
insert into web_grade_subject values('10004', '10000');
insert into web_grade_subject values('10004', '10001');
insert into web_grade_subject values('10004', '10002');
insert into web_grade_subject values('10005', '10000');
insert into web_grade_subject values('10005', '10001');
insert into web_grade_subject values('10005', '10002');
insert into web_grade_subject values('10006', '10000');
insert into web_grade_subject values('10006', '10001');
insert into web_grade_subject values('10006', '10002');
insert into web_grade_subject values('10006', '10007');
insert into web_grade_subject values('10007', '10000');
insert into web_grade_subject values('10007', '10001');
insert into web_grade_subject values('10007', '10002');
insert into web_grade_subject values('10007', '10007');
insert into web_grade_subject values('10008', '10000');
insert into web_grade_subject values('10008', '10001');
insert into web_grade_subject values('10008', '10002');
insert into web_grade_subject values('10008', '10003');
insert into web_grade_subject values('10008', '10004');
insert into web_grade_subject values('10008', '10005');
insert into web_grade_subject values('10008', '10006');
insert into web_grade_subject values('10008', '10007');
insert into web_grade_subject values('10009', '10000');
insert into web_grade_subject values('10009', '10001');
insert into web_grade_subject values('10009', '10002');
insert into web_grade_subject values('10009', '10003');
insert into web_grade_subject values('10009', '10004');
insert into web_grade_subject values('10009', '10005');
insert into web_grade_subject values('10009', '10006');
insert into web_grade_subject values('10009', '10007');
insert into web_grade_subject values('10010', '10000');
insert into web_grade_subject values('10010', '10001');
insert into web_grade_subject values('10010', '10002');
insert into web_grade_subject values('10010', '10003');
insert into web_grade_subject values('10010', '10004');
insert into web_grade_subject values('10010', '10005');
insert into web_grade_subject values('10010', '10006');
insert into web_grade_subject values('10010', '10007');
insert into web_grade_subject values('10011', '10000');
insert into web_grade_subject values('10011', '10001');
insert into web_grade_subject values('10011', '10002');
insert into web_grade_subject values('10011', '10003');
insert into web_grade_subject values('10011', '10004');
insert into web_grade_subject values('10011', '10005');
insert into web_grade_subject values('10011', '10006');
insert into web_grade_subject values('10011', '10007');

--- 学校表 ---
drop table if exists web_school;
create table web_school(
	id varchar(32) not null comment 'UUID',
	schoolName varchar(32) not null comment '学校名称',
	areaCode varchar(32) not null comment '地区编码',
	schoolType int(10) unsigned not null default 1 comment '学校类型',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '学校表';

insert into web_school values('10000', '实验1小', '440300', 1);
insert into web_school values('10001', '实验2小', '440300', 1);
insert into web_school values('10002', '实验3小', '440300', 1);
insert into web_school values('10003', '实验4小', '440300', 1);
insert into web_school values('10004', '实验5小', '440300', 1);
insert into web_school values('10005', '实验6小', '440300', 1);
insert into web_school values('10006', '深圳1中', '440300', 2);
insert into web_school values('10007', '深圳2中', '440300', 2);
insert into web_school values('10008', '深圳3中', '440300', 2);
insert into web_school values('10009', '深圳4中', '440300', 2);
insert into web_school values('10010', '深圳5中', '440300', 6);
insert into web_school values('10011', '高级1中', '440300', 4);
insert into web_school values('10012', '高级2中', '440300', 4);
insert into web_school values('10013', '高级3中', '440300', 4);

--- 行政区划表 ---
drop table if exists web_area;
create table web_area(
	id varchar(32) not null comment 'UUID',
	areaName varchar(32) not null comment '区域名称',
	areaCode varchar(32) not null comment '地区编码',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '行政区划表';

--- 点播课表 ---
drop table if exists web_course_vod;
create table web_course_vod(
	id varchar(32) not null comment 'UUID',
	courseName varchar(128) not null comment '课程名称',
	ownerId varchar(32) not null comment '所有者ID',
	gradeId varchar(32) not null comment '所属年级',
	subjectId varchar(32) not null comment '所属科目',
	price int(10) not null comment '价格',
	preview varchar(256) comment '预览图',
	collectCount int(10) not null default 0 comment '收藏次数',
	saleCount int(10) not null default 0 comment '购买次数',
	playCount int(10) not null default 0 comment '播放次数',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '点播课表';

insert into web_course_vod values('10000', '马克思主义1', '10000', '10000', '10000', 1111, null, 1, 2, 3, now(), now());
insert into web_course_vod values('10001', '马克思主义2', '10000', '10008', '10000', 1111, null, 1, 2, 3, now(), now());
insert into web_course_vod values('10002', '马克思主义3', '10000', '10009', '10000', 1111, null, 1, 2, 3, now(), now());
insert into web_course_vod values('10003', '马克思主义4', '10000', '10010', '10000', 1111, null, 1, 2, 3, now(), now());

--- 直播课表 ---
drop table if exists web_course_live;
create table web_course_live(
	id varchar(32) not null comment 'UUID',
	courseName varchar(128) not null comment '课程名称',
	ownerId varchar(32) not null comment '所有者ID',
	gradeId varchar(32) not null comment '所属年级',
	subjectId varchar(32) not null comment '所属科目',
	price int(10) not null comment '价格',
	preview varchar(256) comment '预览图',
	beginTime datetime not null comment '开始时间',
	collectCount int(10) not null default 0 comment '收藏次数',
	saleCount int(10) not null default 0 comment '购买次数',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '直播课表';