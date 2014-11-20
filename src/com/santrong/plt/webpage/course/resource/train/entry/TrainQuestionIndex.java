package com.santrong.plt.webpage.course.resource.train.entry;

/**
 * @author weinianjie
 * @date 2014年11月20日
 * @time 下午4:06:42
 */
public class TrainQuestionIndex {
	private int answer;
	private int result;

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
	
	// 是否已做题，是否做对题
	public String getClassString() {
		if(answer != 0) {
			if(result == 0) {
				return "wrong";
			}
			return "done";
		}
		return "";
	}
}
