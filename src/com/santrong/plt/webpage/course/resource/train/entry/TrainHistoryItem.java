package com.santrong.plt.webpage.course.resource.train.entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.santrong.plt.util.MyUtils;

/**
 * @author huangweihua
 * @date   2014年11月6日 
 * @time   下午3:44:18
 */
public class TrainHistoryItem {

//	resource_train_history 作业历史
	private String id;
	private String userId;
	private String chapterId;
	private String trainId;
	private String questionId;
	private String answer;
	private int result;
	private int del;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getChapterId() {
		return chapterId;
	}
	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
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
	
	/**
	 * 答案中是否包含选项 A
	 * @return boolean
	 */
	public boolean getContainA() {
		return ((getSumAnswer() & TrainQuestionItem.Answers[0]) == TrainQuestionItem.Answers[0]);
	}

	/**
	 * 答案中是否包含选项 B
	 * @return boolean
	 */
	public boolean getContainB() {
		return ((getSumAnswer() & TrainQuestionItem.Answers[1]) == TrainQuestionItem.Answers[1]);
	}

	/**
	 * 答案中是否包含选项 C
	 * @return boolean
	 */
	public boolean getContainC() {
		return ((getSumAnswer() & TrainQuestionItem.Answers[2]) == TrainQuestionItem.Answers[2]);
	}

	/**
	 * 答案中是否包含选项 D
	 * @return boolean
	 */
	public boolean getContainD() {
		return ((getSumAnswer() & TrainQuestionItem.Answers[3]) == TrainQuestionItem.Answers[3]);
	}
	
	/**
	 * 通过算法计算出答案的总和<br>
	 * 适用范围：单选题、多选题、判断题
	 * @author huangweihua
	 * @param answer
	 * @return int
	 */
	public int getSumAnswer() {
		int sum = 0;
		String[] answerArray = getAnswerArray();
		if (answerArray.length > 0) {
			for (String str : answerArray) {
				sum += MyUtils.stringToInt(str);
			}
		}
		return sum;
	}
	
	/**
	 * 把答案转成字符串数组的格式<br>
	 * 目前答案保存格式为 1,2,4,8<br>
	 * 适用范围：单选题、多选题、判断题
	 * @author huangweihua
	 * @param answer
	 * @return String[]
	 */
	public String[] getAnswerArray() {
		String[] answerArray = null;
		answerArray = this.answer.split(","); 
		return answerArray;
	}
	
	/**
	 * 把答案的总和通过位运算后，获取答案选项对应的字母{"A","B","C","D"。。。}<br>
	 * 适用范围：单选题、多选题、判断题
	 * @return List<\String>
	 */
	public List<String> getAnswerString() {
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < TrainQuestionItem.Answers.length; i++) {
			if((getSumAnswer() & TrainQuestionItem.Answers[i]) == TrainQuestionItem.Answers[i]) {
				list.add(TrainQuestionItem.Answers_Options[i]);
			}
		}
		return list;
	}
}
