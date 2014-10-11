package com.santrong.plt.opt.grade;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午4:38:05
 */
public class GradeLevelEntry {
	private String levelId;
	private String levelName;
	private String levelEnName;
	
	public String getLevelEnName() {
		return levelEnName;
	}
	public void setLevelEnName(String levelEnName) {
		this.levelEnName = levelEnName;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
}
