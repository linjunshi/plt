package com.santrong.plt.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.logicalcobwebs.proxool.configuration.PropertyConfigurator;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.opt.grade.GradeDefine;
import com.santrong.plt.opt.grade.GradeDefineEntry;
import com.santrong.plt.opt.grade.GradeLevelEntry;
import com.santrong.plt.opt.grade.GradeSubjectEntry;
import com.santrong.plt.schedule.LogClearJob;
import com.santrong.plt.schedule.ScheduleManager;
import com.santrong.plt.webpage.home.dao.GradeDao;
import com.santrong.plt.webpage.home.dao.SubjectDao;
import com.santrong.plt.webpage.home.entry.GradeItem;
import com.santrong.plt.webpage.home.entry.GradeView;
import com.santrong.plt.webpage.home.entry.SubjectItem;

/**
 * @Author weinianjie
 * @Date 2014-7-6
 * @Time 下午6:37:09
 */
public class StartUpListener implements ServletContextListener {
	
	ScheduleManager scheManager = new ScheduleManager();
	
	// 启动执行
	@Override
	public void contextInitialized(ServletContextEvent sct) {
		try{
			// 把proxool配置载入环境
			try{
				Properties dbProps = new Properties();
				dbProps.load(StartUpListener.class.getResourceAsStream("/datasource.properties"));
				PropertyConfigurator.configure(dbProps);
			}catch(Exception e) {
				Log.printStackTrace(e);
			}
			
			ServletContext application = sct.getServletContext();		
			
			// 把年级班级科目装载一份到静态数据里
			try{
				GradeDefine.gradeList = new ArrayList<GradeDefineEntry>();
				GradeDao gradeDao = new GradeDao();
				SubjectDao subjectDao = new SubjectDao();
				List<GradeView> gradeList = gradeDao.selectGrade();
				for(GradeView g : gradeList) {
					GradeDefineEntry entry = new GradeDefineEntry();
					entry.setGradeName(g.getGradeName());
					entry.setGradeGroup(g.getGradeGroup());
					entry.setGradeEnName(g.getGradeEnName());
					
					List<GradeLevelEntry> levelList = new ArrayList<GradeLevelEntry>();
					List<GradeItem> levelList2 = gradeDao.selectByGradeGroup(g.getGradeGroup());
					for(GradeItem item : levelList2) {
						GradeLevelEntry e = new GradeLevelEntry();
						e.setLevelId(item.getId());
						e.setLevelName(item.getLevelName());
						e.setLevelEnName(item.getLevelEnName());
						levelList.add(e);
					}
					entry.setGradeLevelList(levelList);
					
					List<GradeSubjectEntry> subjectList = new ArrayList<GradeSubjectEntry>();
					List<SubjectItem> subjectList2 = subjectDao.selectByGradeGroup(g.getGradeGroup());
					for(SubjectItem item : subjectList2) {
						GradeSubjectEntry e = new GradeSubjectEntry();
						e.setSubjectId(item.getId());
						e.setSubjectName(item.getSubjectName());
						e.setSubjectEnName(item.getSubjectEnName());
						subjectList.add(e);
					}
					entry.setGradeSubjectList(subjectList);
					
					GradeDefine.gradeList.add(entry);
					application.setAttribute("gradeList", GradeDefine.gradeList);
				}
			}catch(Exception e) {
				Log.printStackTrace(e);
			}
			
			
			// 给shell目录添加权限
//			try {
//				String[] cmd = new String[] { "/bin/sh", "-c", " chmod 777 " + DirDefine.ShellDir + "/* " };
//				Process ps = Runtime.getRuntime().exec(cmd);
//				ps.waitFor();
//			} catch (Exception e) {
//				Log.printStackTrace(e);
//			}			
			
			// 启动日志清理线程
			scheManager.startCron(new LogClearJob());
			
			// 启动TCP服务监听线程
	//		new Thread(new TcpServer(), "---TcpServer").start();
		}catch(Exception e) {
			Log.printStackTrace(e);
		}finally{
			ThreadUtils.closeAll();
		}
	}
	
	// 销毁执行
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			
			// 手工销毁quartz框架防止tomcat关不住
			Log.info("------:destroy quartz framework----");
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler = factory.getScheduler();
            scheduler.shutdown(true);
            Thread.sleep(100);// Sleep for a bit so that we don't get any errors
        } catch (Exception e){
            Log.printStackTrace(e);
        }
	}

}
