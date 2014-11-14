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