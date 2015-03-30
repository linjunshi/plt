-- 2015-03-27 weinianjie (2015-03-27 plttest)  (2015-03-27 hwcloud)
insert into story values('10001', '功夫片段A', 'KongFuA', '', '10:10', '10000', '', 3, 0, '10000', 0, now(), now());
insert into story values('10002', '功夫片段B', 'KongFuB', '', '10:10', '10000', '', 3, 0, '10000', 0, now(), now());
insert into story values('10003', '功夫片段C', 'KongFuC', '', '10:10', '10000', '', 3, 0, '10000', 0, now(), now());
insert into story values('10004', '让子弹飞片段A', 'RangZiDanFeiA', '', '10:10', '10000', '', 3, 0, '10000', 0, now(), now());
insert into story values('10005', '让子弹飞片段B', 'RangZiDanFeiB', '', '10:10', '10000', '', 3, 0, '10000', 0, now(), now());

-- 2015-03-09
update story set storyName='骄傲的公鸡',storyEname='ProudCock' where id='10000';

create table story_comment(
	id varchar(32) not null comment 'UUID',
	userId varchar(32) not null comment '用户ID',
	storyId varchar(32) not null comment '剧本ID',
	remark varchar(1024) not null comment '评论内容',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '剧本评论表';

-- 2015-02-26 weinianjie
alter table story add column storyEname varchar(128) not null comment '剧本英文名称' after storyName;
insert into story values('10000', '美丽的公鸡', 'meilidegongji', '', '10:10', '10000', '', 3, 0, '10000', 0, now(), now());

-- 2015-02-06 weinianjie
update user set role=127 where username='admin';

-- 2015-02-03 weinianjie
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

-- 2015-02-02 weinianjie
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

drop table if exists story;
create table story(
	id varchar(32) not null comment 'UUID',
	storyName varchar(128) not null comment '剧本名称',
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

-- 2015-01-31 weinianjie
alter table user add column gradeId varchar(32) comment '年级ID' after subjectId;

-- 2015-01-21 weinianjie
alter table resource_train_question change topic topic text not null comment '题目';

alter table course add column unitId varchar(32) comment '所属单元' after subjectId;
drop table if exists course_to_knowledge;
create table course_to_knowledge(
	courseId varchar(32) not null comment '课程ID',
	knowledgeId varchar(32) not null comment '知识点ID',
	primary key (courseId, knowledgeId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '课程关联知识点表';


-- 2015-01-20 
alter table competition_attend change competitionId competitionId varchar(32) comment '竞赛ID';

-- 2015-01-19 weinianjie
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

alter table course add column courseType int(10) default 0 not null comment '课程类型' after chapterCount;
alter table course add column playCount int(10) default 0 not null comment '播放次数' after chapterCount;

-- 2015-01-15 weinianjie
alter table knowledge drop column week;

-- 2015-01-15 weinianjie
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

alter table resource_train_question add column unitId varchar(32) comment '所属单元' after gradeId;
alter table knowledge add column unitId varchar(32) comment '所属单元' after gradeId;

-- 2015-01-14 weinianjie
alter table resource_train_question add column answer2 varchar(256) comment '答案' after answer;
update resource_train_question set answer2='1';
alter table resource_train_question drop column answer;
alter table resource_train_question change answer2 answer varchar(256) comment '答案';

alter table resource_train_history add column answer2 varchar(256) comment '答案' after answer;
update resource_train_history set answer2='1';
alter table resource_train_history drop column answer;
alter table resource_train_history change answer2 answer varchar(256) comment '答案';

alter table competition_history add column answer2 varchar(256) comment '答案' after answer;
update competition_history set answer2='1';
alter table competition_history drop column answer;
alter table competition_history change answer2 answer varchar(256) comment '答案';

-- 2015-01-13 weinianjie
alter table resource_train_question change topic topic varchar(2048) not null comment '题目';

update resource_train_question set topic=concat_ws('',topic,'<br/>','A:',opt1,'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;','B:',opt2,'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;','C:',opt3,'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;','D:',opt4);
alter table resource_train_question drop column opt1;
alter table resource_train_question drop column opt2;
alter table resource_train_question drop column opt3;
alter table resource_train_question drop column opt4;

-- 2015-01-08 weinianjie
alter table resource_train_question add column level int(10) comment '难中易' after ownerId; 
alter table resource_train_question change del status int(10) comment '状态' after level; 

-- 2015-01-05 weinianjie
alter table knowledge add column code int(32) not null comment '编码' after id;
alter table knowledge add column priority int(10) comment '排序' after week;
delete from knowledge where id='10000';
insert into knowledge values('10000', 1000000000, 1, '知识点', '0', '0', 0, 0);

-- 2015-01-04 weinianjie
alter table knowledge add column level int(16) not null comment '层级' after id;
alter table knowledge add column week int(10) default 1 comment '周' after gradeId;
insert into knowledge values('10000', 1000000000000000, '知识点', '0', '0', 0);

alter table competition add column flag int(10) default 0 not null comment '竞赛类型' after beginTime; -- 0公共竞赛，1个人竞赛（个人练习）

-- 2014-12-29 weinianjie 
insert into grade_to_subject values('10000', '10002');
insert into grade_to_subject values('10001', '10002');
insert into grade_to_subject values('10002', '10002');
insert into grade_to_subject values('10003', '10002');

-- 2014-12-25 weinianjie
alter table resource_train_question add column subjectId varchar(32) comment '所属科目' after remark;
alter table resource_train_question add column gradeId varchar(32) comment '所属年级' after subjectId;
alter table resource_train_question add column timeLimit int(10) default 0 not null comment '限制时间（s）' after gradeId;

-- 竞赛表 --
drop table if exists competition;
create table competition(
	id varchar(32) not null comment 'UUID',
	title varchar(128) not null comment '竞赛名',
	remark varchar(1024) comment '描述',
	beginTime datetime not null comment '竞赛开始时间',
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
	competitionId varchar(32) not null comment '竞赛ID',
	cts datetime comment '创建时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '竞赛报名表';

-- 竞赛历史回答表 --
drop table if exists competition_history;
create table competition_history(
	id varchar(32) not null comment 'UUID',
	attendId varchar(32) not null comment '报名表ID',
	questionId varchar(32) not null comment '习题ID',
	answer int(10) not null comment '答案',
	result int(10) not null comment '正确与否1yes2no',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',	
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '竞赛历史回答表';

-- 知识点表 --
drop table if exists knowledge;
create table knowledge(
	id varchar(32) not null comment 'UUID',
	knowledgeName varchar(64) not null comment '知识点名称',
	subjectId varchar(32) comment '所属科目',
	gradeId varchar(32) comment '所属年级',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '知识点表';

-- 习题关联知识点表 --
drop table if exists question_to_knowledge;
create table question_to_knowledge(
	questionId varchar(32) not null comment '习题ID',
	knowledgeId varchar(32) not null comment '知识点ID',
	primary key (questionId, knowledgeId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '习题关联知识点表';
