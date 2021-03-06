-- 2015-01-04 weinianjie
alter table knowledge add column level int(16) not null comment '层级' after id;
alter table knowledge add column week int(10) default 1 comment '周' after gradeId;
insert into knowledge values('10000', 100000000000000, '知识点', '0', '0', 0);

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