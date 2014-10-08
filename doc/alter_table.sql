-- 2014-09-25 weinianjie
create database plt;

use plt;
	
-- 用户表 ---
drop table if exists web_user;
create table web_user(
	id varchar(32) not null comment 'UUID',
	showName varchar(128) not null comment '显示名',
	username varchar(64) not null comment '用户名',
	password varchar(64) not null comment '密码',
	role int(11) not null default 1 comment '角色',
	cts datetime comment '创建时间',
	uts datetime comment '修改时间',
	primary key (id)
) engine=InnoDB default charset=utf8 collate=utf8_bin;

insert into web_user values('10000', 'admin', 'admin', md5('admin'), now(), now());