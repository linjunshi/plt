-- 2014-09-25 weinianjie
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