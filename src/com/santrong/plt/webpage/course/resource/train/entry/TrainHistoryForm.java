package com.santrong.plt.webpage.course.resource.train.entry;


/**
 * @author weinianjie
 * @date   2014年11月6日 
 * @time   下午3:44:18
 */
public class TrainHistoryForm {

	private String chapterId;
	private String trainId;
	private String[] answer;
	
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
	public String[] getAnswer() {
		return answer;
	}
	public void setAnswer(String[] answer) {
		this.answer = answer;
	}
}
