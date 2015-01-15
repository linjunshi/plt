-- 2014-09-25 weinianjie
set names utf8;

-- 用户 --
insert into user values('10000', 'admin', 'admin', md5('admin'), '', 0, 71, 10000, 10000, 'weinianjie@163.com', '18665955410', null, null, null, null, null, now(), now());
insert into user values('10001', 'teacher1', 'teacher1', md5('admin'), '', 1, 3, 10000, 10000, '304468211@qq.com', '18665955411', null, null, null, null, null, now(), now());
insert into user values('10002', 'teacher2', 'teacher2', md5('admin'), '', 1, 3, 10000, 10000, '', '18665955412', null, null, null, null, null, now(), now());
insert into user values('10003', 'teacher3', 'teacher3', md5('admin'), '', 1, 3, 10000, 10000, '', '18665955413', null, null, null, null, null, now(), now());
insert into user values('10004', 'teacher4', 'teacher4', md5('admin'), '', 2, 3, 10000, 10000, '', '18665955414', null, null, null, null, null, now(), now());
insert into user values('10005', 'teacher5', 'teacher5', md5('admin'), '', 2, 3, 10000, 10000, '', '18665955415', null, null, null, null, null, now(), now());
insert into user values('10006', 'student1', 'student1', md5('admin'), '', 2, 1, 10000, 10000, '418941485@qq.com', '18665955416', null, null, null, null, null, now(), now());
insert into user values('10007', 'student2', 'student2', md5('admin'), '', 0, 1, 10000, 10000, '', '18665955417', null, null, null, null, null, now(), now());
insert into user values('10008', 'teacher6', 'teacher6', md5('admin'), '', 0, 3, 10000, 10000, '', '18665955418', null, null, null, null, null, now(), now());
insert into user values('10009', 'teacher7', 'teacher7', md5('admin'), '', 0, 3, 10000, 10000, '', '18665955419', null, null, null, null, null, now(), now());
insert into user values('10010', 'teacher8', 'teacher8', md5('admin'), '', 0, 3, 10000, 10000, '', '18665955420', null, null, null, null, null, now(), now());
insert into user values('10011', 'teacher9', 'teacher9', md5('admin'), '', 0, 3, 10000, 10000, '', '18665955421', null, null, null, null, null, now(), now());
insert into user values('10012', 'teacher10', 'teacher10', md5('admin'), '', 0, 3, 10000, 10000, '', '18665955422', null, null, null, null, null, now(), now());
insert into user values('10013', 'teacher11', 'teacher11', md5('admin'), '', 0, 3, 10000, 10000, '', '18665955423', null, null, null, null, null, now(), now());
insert into user values('10014', 'teacher12', 'teacher12', md5('admin'), '', 0, 3, 10000, 10000, '', '18665955424', null, null, null, null, null, now(), now());
insert into user values('10015', 'teacher13', 'teacher13', md5('admin'), '', 0, 3, 10000, 10000, '', '18665955425', null, null, null, null, null, now(), now());
insert into user values('10016', 'teacher14', 'teacher14', md5('admin'), '', 0, 3, 10000, 10000, '', '18665955426', null, null, null, null, null, now(), now());
insert into user values('10017', 'teacher15', 'teacher14', md5('admin'), '', 0, 3, 10000, 10000, '', '18665955427', null, null, null, null, null, now(), now());

-- 年级 --
insert into grade values('10000', '小学', '一年级', 1, 'xiaoxue', 'level1', 0);
insert into grade values('10001', '小学', '二年级', 1, 'xiaoxue', 'level2', 1);
insert into grade values('10002', '小学', '三年级', 1, 'xiaoxue', 'level3', 2);
insert into grade values('10003', '小学', '四年级', 1, 'xiaoxue', 'level4', 3);
insert into grade values('10004', '小学', '五年级', 1, 'xiaoxue', 'level5', 4);
insert into grade values('10005', '小学', '六年级', 1, 'xiaoxue', 'level6', 5);
insert into grade values('10006', '初中', '一年级', 2, 'chuzhong', 'level1', 6);
insert into grade values('10007', '初中', '二年级', 2, 'chuzhong', 'level2', 7);
insert into grade values('10008', '初中', '三年级', 2, 'chuzhong', 'level3', 8);
insert into grade values('10009', '高中', '一年级', 4, 'gaozhong', 'level1', 9);
insert into grade values('10010', '高中', '二年级', 4, 'gaozhong', 'level2', 10);
insert into grade values('10011', '高中', '三年级', 4, 'gaozhong', 'level3', 11);

-- 科目 --
insert into subject values('10000', '语文', 'yuwen', 0);
insert into subject values('10001', '数学', 'shuxue', 1);
insert into subject values('10002', '英语', 'yingyu', 2);
insert into subject values('10003', '物理', 'wuli', 3);
insert into subject values('10004', '化学', 'huaxue', 4);
insert into subject values('10005', '生物', 'shengwu', 5);
insert into subject values('10006', '地理', 'dili', 6);
insert into subject values('10007', '政治', 'zhengzhi', 7);

-- 年级-科目 --
insert into grade_to_subject values('10000', '10000');
insert into grade_to_subject values('10000', '10001');
insert into grade_to_subject values('10000', '10002');
insert into grade_to_subject values('10001', '10000');
insert into grade_to_subject values('10001', '10001');
insert into grade_to_subject values('10001', '10002');
insert into grade_to_subject values('10002', '10000');
insert into grade_to_subject values('10002', '10001');
insert into grade_to_subject values('10002', '10002');
insert into grade_to_subject values('10003', '10000');
insert into grade_to_subject values('10003', '10001');
insert into grade_to_subject values('10003', '10002');
insert into grade_to_subject values('10004', '10000');
insert into grade_to_subject values('10004', '10001');
insert into grade_to_subject values('10004', '10002');
insert into grade_to_subject values('10005', '10000');
insert into grade_to_subject values('10005', '10001');
insert into grade_to_subject values('10005', '10002');
insert into grade_to_subject values('10006', '10000');
insert into grade_to_subject values('10006', '10001');
insert into grade_to_subject values('10006', '10002');
insert into grade_to_subject values('10006', '10007');
insert into grade_to_subject values('10007', '10000');
insert into grade_to_subject values('10007', '10001');
insert into grade_to_subject values('10007', '10002');
insert into grade_to_subject values('10007', '10007');
insert into grade_to_subject values('10008', '10000');
insert into grade_to_subject values('10008', '10001');
insert into grade_to_subject values('10008', '10002');
insert into grade_to_subject values('10008', '10003');
insert into grade_to_subject values('10008', '10004');
insert into grade_to_subject values('10008', '10005');
insert into grade_to_subject values('10008', '10006');
insert into grade_to_subject values('10008', '10007');
insert into grade_to_subject values('10009', '10000');
insert into grade_to_subject values('10009', '10001');
insert into grade_to_subject values('10009', '10002');
insert into grade_to_subject values('10009', '10003');
insert into grade_to_subject values('10009', '10004');
insert into grade_to_subject values('10009', '10005');
insert into grade_to_subject values('10009', '10006');
insert into grade_to_subject values('10010', '10000');
insert into grade_to_subject values('10010', '10001');
insert into grade_to_subject values('10010', '10002');
insert into grade_to_subject values('10010', '10003');
insert into grade_to_subject values('10010', '10004');
insert into grade_to_subject values('10010', '10005');
insert into grade_to_subject values('10010', '10006');
insert into grade_to_subject values('10011', '10000');
insert into grade_to_subject values('10011', '10001');
insert into grade_to_subject values('10011', '10002');
insert into grade_to_subject values('10011', '10003');
insert into grade_to_subject values('10011', '10004');
insert into grade_to_subject values('10011', '10005');
insert into grade_to_subject values('10011', '10006');

-- 课程 --
insert into course values('10000', '马克思主义', '王老师', 10000, 0, '2014-12-12', '10000', '10000', '课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介', 1, 2, 3, 4, '10000', 1, now(), now());
insert into course values('10001', '相对论', '王老师', 10000, 0, '2014-12-12', '10000', '10001', '课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values('10002', '微积分', '王老师', 10000, 0, '2014-12-12', '10000', '10001', '课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values('10003', '高等数学', '王老师', 10000, 1, '2014-12-12', '10000', '10001', '课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values('10004', '数字逻辑', '王老师', 10000, 1, '2014-12-12', '10006', '10003', '课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values('10005', '线性代数', '王老师', 10000, 0, '2014-12-12', '10006', '10003', '课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values('10006', '蛋白质', '王老师', 10000, 1, '2014-12-12', '10006', '10003', '课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values('10007', '细胞核', '王老师', 10000, 1, '2014-12-12', '10006', '10003', '课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values('10008', '世界历史', '王老师', 10000, 0, '2014-12-12', '10009', '10003', '课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values('10009', '唯物主义', '王老师', 10000, 1, '2014-12-12', '10009', '10004', '课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values('10010', '经济学入门', '王老师', 10000, 1, '2014-12-12', '10009', '10004', '课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values('10011', '货币战争', '王老师', 10000, 1, '2014-12-12', '10009', '10004', '课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values('10012', '论持久战', '王老师', 10000, 1, '2014-12-12', '10000', '10004', '课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介课程的简介', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values('10013', '大头儿子小头爸爸', '王老师', 10000, 1, '2014-12-12', '10006', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values('10014', '西游记', '王老师', 10000, 0, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '西游记2', '王老师', 10000, 0, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '西游记3', '王老师', 10000, 0, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '西游记4', '王老师', 10000, 0, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '西游记5', '王老师', 10000, 0, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '西游记6', '王老师', 10000, 0, '2014-12-12', '10009', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '大头儿子小头爸爸2', '王老师', 10000, 0, '2014-12-12', '10006', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '大头儿子小头爸爸3', '王老师', 10000, 0, '2014-12-12', '10006', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '大头儿子小头爸爸4', '王老师', 10000, 0, '2014-12-12', '10006', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '大头儿子小头爸爸5', '王老师', 10000, 0, '2014-12-12', '10006', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '大头儿子小头爸爸6', '王老师', 10000, 0, '2014-12-12', '10006', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '经济学入门2', '王老师', 10000, 0, '2014-12-12', '10000', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '经济学入门3', '王老师', 10000, 0, '2014-12-12', '10000', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '经济学入门4', '王老师', 10000, 0, '2014-12-12', '10000', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '经济学入门5', '王老师', 10000, 0, '2014-12-12', '10000', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());
insert into course values(replace(uuid(),'-',''), '经济学入门6', '王老师', 10000, 0, '2014-12-12', '10000', '10004', 'xxx', 1, 2, 3, 4, '10001', 1, now(), now());

-- 课程评论 --
insert into course_comment values('10000', '10000', '10000', '课程还可以', now(), now());
insert into course_comment values('10001', '10001', '10000', '非常喜欢', now(), now());
insert into course_comment values('10002', '10002', '10000', '还行', now(), now());

-- 章节 --
insert into course_chapter values('10000', '10000', 'remark1', 0, now(), now());
insert into course_chapter values('10001', '10000', 'remark2', 1, now(), now());
insert into course_chapter values('10002', '10000', 'remark3', 2, now(), now());
insert into course_chapter values('10003', '10000', 'remark4', 3, now(), now());

-- 章节关联资源 --
insert into course_chapter_to_resource values('10000', '随堂测验', '10000', '10000', 4, 1);
insert into course_chapter_to_resource values('10001', '张老师直播1', '10000', '10001', 2, 2);
insert into course_chapter_to_resource values('10002', '张老师点播1', '10000', '10002', 1, 2);
insert into course_chapter_to_resource values('10003', '文档1', '10000', '10003', 3, 3);

-- 点播文件 --
insert into resource_file values('10012', '马克思主义0', '', 1024, 66, '10000', '10000', '', now(), now());
insert into resource_file values('10001', '马克思主义1', '', 1024, 66, '10000', '10008', '', now(), now());
insert into resource_file values('10002', '马克思主义2', '', 1024, 66, '10000', '10009', '', now(), now());
insert into resource_file values('10003', '马克思主义3', '', 1024, 66, '10000', '10010', '', now(), now());
insert into resource_file values('10004', '马克思主义4', '', 1024, 66, '10000', '10010', '', now(), now());
insert into resource_file values('10005', '马克思主义5', '', 1024, 66, '10000', '10010', '', now(), now());
insert into resource_file values('10006', '马克思主义6', '', 1024, 66, '10000', '10000', '', now(), now());
insert into resource_file values('10007', '马克思主义7', '', 1024, 66, '10000', '10000', '', now(), now());
insert into resource_file values('10008', '马克思主义8', '', 1024, 66, '10000', '10000', '', now(), now());
insert into resource_file values('10009', '马克思主义9', '', 1024, 66, '10000', '10007', '', now(), now());
insert into resource_file values('10010', '马克思主义10', '', 1024, 66, '10000', '10007', '', now(), now());
insert into resource_file values('10011', '马克思主义11', '', 1024, 66, '10000', '10007', '', now(), now());

-- 直播资源 --
insert into resource_live values('10001', '张老师直播1', null, '2014-12-12 13:00', '2014-12-12 15:00', 7200, null, '10000', now(), now());

-- 文档资源 --
insert into resource_doc values('10003', '文档1', '', 1, '10000', '10000', '', now(), now());