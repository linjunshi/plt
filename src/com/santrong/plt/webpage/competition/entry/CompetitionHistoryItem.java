package com.santrong.plt.webpage.competition.entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;

/**
 * @author weinianjie
 * @date   2014年11月6日 
 * @time   下午3:44:18
 */
public class CompetitionHistoryItem {

	private String id;
	private String attendId;
	private String questionId;
	private int answer;
	private int result;
	private Date cts;
	private Date uts;
	
	/**
	 * 答题正确，结果为1
	 */
	public static final int ANSWER_IS_RIGHT		=	1;
	/**
	 * 答题错误，结果为0
	 */
	public static final int ANSWER_IS_WRONG		=	0;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAttendId() {
		return attendId;
	}
	public void setAttendId(String attendId) {
		this.attendId = attendId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public Date getCts() {
		return cts;
	}
	public void setCts(Date cts) {
		this.cts = cts;
	}
	public Date getUts() {
		return uts;
	}
	public void setUts(Date uts) {
		this.uts = uts;
	}
	
	
	public List<String> getAnswerString() {
		List<String> list = new ArrayList<String>();
		for(int i=0;i<TrainQuestionItem.Answers.length;i++) {
			if((answer & TrainQuestionItem.Answers[i]) == TrainQuestionItem.Answers[i]) {
				list.add(TrainQuestionItem.Answers_Options[i]);
			}
		}
		return list;
	}
}
