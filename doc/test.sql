create table tmp as select a.id,d.title from course a left join course_chapter b on a.id=b.courseId left join course_chapter_to_resource c on b.id=c.chapterId left join resource_video d on c.resourceId=d.id where a.courseType=1 and d.url like '%.flv';
update course,tmp set course.url=concat_ws('','/upload/wk/',tmp.title,'.jpg') where course.id=tmp.id;


select a.id,b.id,c.id,d.id from course a left join course_chapter b on a.id=b.courseId left join course_chapter_to_resource c on b.id=c.chapterId left join resource_video d on c.resourceId=d.id where a.courseType=1 and d.title='20130513389';


select a.id,d.title from course a left join course_chapter b on a.id=b.courseId left join course_chapter_to_resource c on b.id=c.chapterId left join resource_video d on c.resourceId=d.id where a.courseType=1 and d.url like '%.flv';