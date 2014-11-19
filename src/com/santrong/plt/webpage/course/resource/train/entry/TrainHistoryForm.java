package com.santrong.plt.webpage.course.resource.train.entry;


/**
 * @author weinianjie
 * @date   2014年11月6日 
 * @time   下午3:44:18
 */
public class TrainHistoryForm {

	private String resourceId;
	private String questionId;
	private String chapterId;
	private String trainId;
	private Integer total;
	private Integer index;
	private Integer[] answer;
	
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
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
	public Integer[] getAnswer() {
		return answer;
	}
	public void setAnswer(Integer[] answer) {
		this.answer = answer;
	}
	
	public int answerTotal() {
		int total = 0;
		for(Integer i:this.answer) {
			total += i;
		}
		return total;
	}
}
