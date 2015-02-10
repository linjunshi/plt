package com.santrong.plt.opt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weinianjie
 * @date 2014年7月18日
 * @time 下午3:04:18
 */
public class PageQuery {
	private int pageNum = 1; // 当前页，1开始计算
	private int count; // 数据总量
	private int pageSize = 16; // 每页大小
	private int showSize = 5; // 显示分页数
	private int prevInsert = 0; //前置插入混淆数据量
	
	private String orderBy;
	private String orderRule;
	
	/*
	 * 获取limit的开头
	 */
	public int getLimitBegin() {
		if(this.pageNum != 1) {// 非第一页
			return (pageNum - 1) * pageSize - prevInsert;
		}
		return (pageNum - 1) * pageSize;
	}
	/*
	 * 获取limit的结尾
	 */
	public int getLimitEnd() {
		if(this.pageNum == 1) {// 第一页
			return pageSize - prevInsert;
		}
		return pageSize;
	}
	
	/*
	 * 获取总页数
	 */
	public int getPageCount() {
		if(count > 0 && pageSize > 0) {
			return count / pageSize + (count % pageSize == 0 ? 0 : 1);
		}
		return 0;
	}
	
	/*
	 * 获取分页序列
	 */
	public List<Integer> getPageSequence() {
		List<Integer> list = new ArrayList<Integer>();
		int tryNum = 0;
		int cal = 0;
		int endNum = this.showSize;
		if (this.pageNum <= this.showSize / 2 + 1) {//前面几位
			if (this.getPageCount() < this.showSize) {
				endNum = this.getPageCount();
			}
			for (int i = 1; i <= endNum; i++) {
				list.add(i);
			}
		} else if (this.getPageCount() - this.pageNum <= this.showSize / 2){//后面几位
			int beginNum = this.getPageCount() - this.showSize + 1;
			for (int i = beginNum; i <= this.getPageCount(); i++) {
				if(i > 0) {
					list.add(i);
				}
			}
		} else {
			endNum = this.showSize / 2;//this.showSize 显示页为基数的时候
			if (this.showSize % 2 == 0) {//this.showSize 显示页为偶数的时候
				endNum =  this.showSize / 2 - 1;
			}
			for(int i = 0 - this.showSize / 2; i <= endNum; i++) {
				tryNum = this.pageNum + i;
				if(tryNum > this.getPageCount() || cal > this.showSize) {
					break;
				}
				if(tryNum > 0) {
					list.add(tryNum);
					cal++;
				}
			}
		}
		return list;
	}
	
	public int getShowSize() {
		return showSize;
	}
	public void setShowSize(int showSize) {
		this.showSize = showSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderRule() {
		return orderRule;
	}

	public void setOrderRule(String orderRule) {
		this.orderRule = orderRule;
	}

	public int getPrevInsert() {
		return prevInsert;
	}

	public void setPrevInsert(int prevInsert) {
		this.prevInsert = prevInsert;
	}
}
