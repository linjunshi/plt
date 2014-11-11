-- 2014-11-11 weinianjie
set names utf8;

-- 行政区划表 --
drop table if exists spider_school_vko;
create table spider_school_vko(
	itCode varchar(32),
	areaCode varchar(32),
	schoolName varchar(64)
) engine=InnoDB default charset=utf8 collate=utf8_bin comment '微课网的学校';