-- 2014-09-25 weinianjie

use plt;
	
--- 用户表 ---
insert into web_user values('10000', 'admin', 'admin', md5('admin'), now(), now());

--- 年级表 ---
insert into web_grade values('10000', '小学', '一年级', 1, 0);
insert into web_grade values('10001', '小学', '二年级', 1, 1);
insert into web_grade values('10002', '小学', '三年级', 1, 2);
insert into web_grade values('10003', '小学', '四年级', 1, 3);
insert into web_grade values('10004', '小学', '五年级', 1, 4);
insert into web_grade values('10005', '小学', '六年级', 1, 5);
insert into web_grade values('10006', '初中', '一年级', 2, 6);
insert into web_grade values('10007', '初中', '二年级', 2, 7);
insert into web_grade values('10008', '初中', '三年级', 2, 8);
insert into web_grade values('10009', '高中', '一年级', 4, 9);
insert into web_grade values('10010', '高中', '二年级', 4, 10);
insert into web_grade values('10011', '高中', '三年级', 4, 11);

--- 科目表 ---
insert into web_subject values('10000', '语文', 0);
insert into web_subject values('10001', '数学', 1);
insert into web_subject values('10002', '英语', 2);
insert into web_subject values('10003', '物理', 3);
insert into web_subject values('10004', '化学', 4);
insert into web_subject values('10005', '生物', 5);
insert into web_subject values('10006', '地理', 6);
insert into web_subject values('10007', '政治', 7);

--- 年级-科目表 ---
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

--- 行政区划表 ---