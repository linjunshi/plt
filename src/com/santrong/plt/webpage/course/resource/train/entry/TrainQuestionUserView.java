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
public class TrainQuestionUserView {

//	resource_train_question 作业习题
	private String id;
	private String topic;
	private int questionType;
	private String answer;
	private String remark;
	private String subjectId;
	private String gradeId;
	private String unitId;
	private int timeLimit;
	private String ownerId;
	private int level;
	private int status;
	private Date cts;
	private Date uts;
	
	private int[] pageAnswer;//用来保存表单多选题的答案值
	
	// 是否已选择
	private boolean assemble;
	
	// user 用户表
	public String showName;//所属用户的名称
	
	public boolean isAssemble() {
		return assemble;
	}
	public void setAssemble(boolean assemble) {
		this.assemble = assemble;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public static int[] getAnswers() {
		return TrainQuestionItem.Answers;
	}
	public int[] getPageAnswer() {
		return pageAnswer;
	}
	public void setPageAnswer(int[] pageAnswer) {
		this.pageAnswer = pageAnswer;
	}
	
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	/**
	 * 是否是单选题
	 * @return boolean
	 */
	public boolean isSingleSelection() {
		return (questionType == TrainQuestionItem.QUESTION_TYPE_SINGLE_SELECTION);
	}
	/**
	 * 是否是多选题
	 * @return boolean
	 */
	public boolean isMulChoice() {
		return (questionType == TrainQuestionItem.QUESTION_TYPE_MULTIPLE_CHOICE);
	}
	/**
	 * 是否是判断题
	 * @return boolean
	 */
	public boolean isTrueOrFlase() {
		return (questionType == TrainQuestionItem.QUESTION_TYPE_JUDGE_TRUE_OR_FLASE);
	}
	/**
	 * 是否是填空题
	 * @return boolean
	 */
	public boolean isBlankFilling() {
		return (questionType == TrainQuestionItem.QUESTION_TYPE_BLANK_FILLING);
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
		if (answerArray != null && answerArray.length > 0) {
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
		if (MyUtils.isNotNull(this.answer)) {
			if (this.questionType == TrainQuestionItem.QUESTION_TYPE_SINGLE_SELECTION 
				|| this.questionType == TrainQuestionItem.QUESTION_TYPE_MULTIPLE_CHOICE
				|| this.questionType == TrainQuestionItem.QUESTION_TYPE_JUDGE_TRUE_OR_FLASE) {
				answerArray = this.answer.split(","); 
			}
		}
		return answerArray;
	}
	
	/**
	 * 返回试题的类型
	 * @return String
	 */
	public String getTypeString() {
		switch(this.questionType) {
		case TrainQuestionItem.QUESTION_TYPE_SINGLE_SELECTION :
			return "单选题";
		case TrainQuestionItem.QUESTION_TYPE_MULTIPLE_CHOICE :
			return "多选题";
		case TrainQuestionItem.QUESTION_TYPE_JUDGE_TRUE_OR_FLASE:
			return "判断题";
		case TrainQuestionItem.QUESTION_TYPE_BLANK_FILLING:
			return "填空题";			
		}
		return "";
	}
	
	/**
	 * 返回试题的状态
	 * @return String
	 */
	public String getStatusString() {
		switch(this.status) {
		case TrainQuestionItem.Status_New :
			return "待审核";
		case TrainQuestionItem.Status_Approve :
			return "已审核";
		case TrainQuestionItem.Status_Disapprove:
			return "审核不通过";
		case TrainQuestionItem.Status_Del:
			return "已删除";			
		}
		return "";
	}
	
	/**
	 * 把答案的总和通过位运算后，获取答案选项对应的字母{"A","B","C","D"。。。}<br>
	 * 适用范围：单选题、多选题、判断题
	 * @return List<\String>
	 */
	public List<String> getAnswerString() {
		List<String> list = new ArrayList<String>();
		if (this.questionType == TrainQuestionItem.QUESTION_TYPE_MULTIPLE_CHOICE 
				|| this.questionType == TrainQuestionItem.QUESTION_TYPE_SINGLE_SELECTION) {//选择题
			for(int i = 0; i < TrainQuestionItem.Answers.length; i++) {
				if(((this.questionType == TrainQuestionItem.QUESTION_TYPE_SINGLE_SELECTION 
						? MyUtils.stringToInt(this.answer) 
								: getSumAnswer()) & TrainQuestionItem.Answers[i]) == TrainQuestionItem.Answers[i]) {
					list.add(TrainQuestionItem.Answers_Options[i]);
				}
			}
		} else if(this.questionType == TrainQuestionItem.QUESTION_TYPE_JUDGE_TRUE_OR_FLASE){//判断题
			for (int i = 0; i < TrainQuestionItem.Answers_TrueOrFalse.length; i++) {
				if ((MyUtils.stringToInt(this.answer) & TrainQuestionItem.Answers[i]) == TrainQuestionItem.Answers[i]) {
					list.add(TrainQuestionItem.Answers_TrueOrFalse[i]);
				}
			}
		}
		return list;
	}
	
}
