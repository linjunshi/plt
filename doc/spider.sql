-- 2014-11-11 weinianjie
set names utf8;

-- 微课网的学校 --
drop table if exists spider_school_vko;
create table spider_school_vko(
	itCode varchar(32) comment '未知编码',
	areaCode varchar(32) comment '行政区划编码',
	schoolName varchar(64) comment '学校名称'
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '微课网的学校';

-- 人人网的学校 --
drop table if exists spider_school_rr;
create table spider_school_rr(
	itCode varchar(32) comment '未知编码',
	areaCode varchar(32) comment '行政区划编码',
	schoolName varchar(64) comment '学校名称',
	flag int(10) comment '类型，1初中2高中'
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '人人网的学校';