package com.santrong.plt.webpage.course.resource.train.entry;

/**
 * @author weinianjie
 * @date 2014年11月20日
 * @time 下午4:06:42
 */
public class TrainQuestionIndex {
	private String answer;
	private int result;

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
	
	// 是否已做题，是否做对题
	public String getClassString() {
		if(answer != null) {
			if(result == TrainHistoryItem.ANSWER_IS_WRONG) {
				return "wrong";
			} else if (result == TrainHistoryItem.ANSWER_IS_RIGHT) {
				return "right";
			}
			return "done";
		}
		return "";
	}
}
