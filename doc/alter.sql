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
	primary key (gradeId, subjectId)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '单元表';

alter table resource_train_question add column unitId varchar(32) comment '所属单元' after gradeId;
alter table knowledge add column unitId varchar(32) comment '所属单元' after gradeId;

-- 2015-01-14 weinianjie  (2015-01-15 hwcloud)
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