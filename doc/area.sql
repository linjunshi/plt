-- 2014-09-25 weinianjie
set names utf8;

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