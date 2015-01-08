package com.santrong.plt.webpage.course.resource.train.entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huangweihua
 * @date   2014年11月6日 
 * @time   下午3:44:18
 */
public class TrainQuestionItem {

//	resource_train_question 作业习题
	private String id;
	private String topic;
	private int questionType;
	private String opt1;
	private String opt2;
	private String opt3;
	private String opt4;
	private int answer;
	private String remark;
	private String subjectId;
	private String gradeId;
	private int timeLimit;
	private String ownerId;
	private int del;
	private Date cts;
	private Date uts;
	
	private int[] pageAnswer;
	
	// 是否已选择
	private boolean assemble;

	// 题目的默认类型值
	/**
	 * 单选题 (默认值为1)
	 */
	public static final int QUESTION_TYPE_SINGLE_SELECTION			=	1; //单选题 (默认值为1)
	/**
	 * 多选题 (默认值为2)
	 */
	public static final int QUESTION_TYPE_MULTIPLE_CHOICE			=	2; //多选题 (默认值为2)
	/**
	 * 判断题 (默认值为3)
	 */
	public static final int QUESTION_TYPE_JUDGE_TRUE_OR_FLASE		=	3; //判断题 (默认值为3)
	/**
	 * 填空题  (默认值为4)
	 */
	public static final int QUESTION_TYPE_BLANK_FILLING				=	4; //填空题 (默认值为4)
	
	// 是否已删除
	/**
	 * 默认为 IS_NOT_DELETE = 0，标识为未删除
	 */
	public static final int IS_NOT_DELETE 							=	0; //默认0
	/**
	 * IS_DELETE = 1，标识为已删除
	 */
	public static final int IS_DELETE								=	1; //1 标识成伪删除
	
	/**
	 * 选项的{"A","B","C","D"}分别对应的数值{1,2,4,8}
	 * 0001,0010,0100,1000
	 */
	public static final int[] Answers								= {1,2,4,8};
	/**
	 * 选项的{"A","B","C","D"}分别对应的数值{1,2,4,8}
	 * 0001,0010,0100,1000
	 */
	public static final String[] Answers_Options					= {"A","B","C","D"};
	
	
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
	public String getOpt1() {
		return opt1;
	}
	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}
	public String getOpt2() {
		return opt2;
	}
	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}
	public String getOpt3() {
		return opt3;
	}
	public void setOpt3(String opt3) {
		this.opt3 = opt3;
	}
	public String getOpt4() {
		return opt4;
	}
	public void setOpt4(String opt4) {
		this.opt4 = opt4;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
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
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public int[] getPageAnswer() {
		return pageAnswer;
	}
	public void setPageAnswer(int[] pageAnswer) {
		this.pageAnswer = pageAnswer;
	}
	
	/**
	 * 是否是单选题
	 * @return boolean
	 */
	public boolean isSingleSelection() {
		return (questionType == QUESTION_TYPE_SINGLE_SELECTION);
	}
	/**
	 * 是否是多选题
	 * @return boolean
	 */
	public boolean isMulChoice() {
		return (questionType == QUESTION_TYPE_MULTIPLE_CHOICE);
	}
	/**
	 * 是否是判断题
	 * @return boolean
	 */
	public boolean isTrueOrFlase() {
		return (questionType == QUESTION_TYPE_JUDGE_TRUE_OR_FLASE);
	}
	/**
	 * 是否是填空题
	 * @return boolean
	 */
	public boolean isBlankFilling() {
		return (questionType == QUESTION_TYPE_BLANK_FILLING);
	}
	
	/**
	 * 答案中是否包含选项 A
	 * @return boolean
	 */
	public boolean getContainA() {
		return ((answer & Answers[0]) == Answers[0]);
	}

	/**
	 * 答案中是否包含选项 B
	 * @return boolean
	 */
	public boolean getContainB() {
		return ((answer & Answers[1]) == Answers[1]);
	}

	/**
	 * 答案中是否包含选项 C
	 * @return boolean
	 */
	public boolean getContainC() {
		return ((answer & Answers[2]) == Answers[2]);
	}

	/**
	 * 答案中是否包含选项 D
	 * @return boolean
	 */
	public boolean getContainD() {
		return ((answer & Answers[3]) == Answers[3]);
	}
	
	public String getTypeString() {
		switch(this.questionType) {
		case QUESTION_TYPE_SINGLE_SELECTION :
			return "单选题";
		case QUESTION_TYPE_MULTIPLE_CHOICE :
			return "多选题";
		case QUESTION_TYPE_JUDGE_TRUE_OR_FLASE:
			return "判断题";
		case QUESTION_TYPE_BLANK_FILLING:
			return "填空题";			
		}
		return "";
	}
	
	public List<String> getAnswerString() {
		List<String> list = new ArrayList<String>();
		for(int i=0;i<Answers.length;i++) {
			if((answer & Answers[i]) == Answers[i]) {
				list.add(Answers_Options[i]);
			}
		}
		return list;
	}
}
