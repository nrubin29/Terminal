package me.nrubin29.terminal.level;

public class Checkpoint {

	private String hint, answer;
	
	public Checkpoint(String hint, String answer) {
		this.hint = hint;
		this.answer = answer;
	}

	public String getHint() {
		return hint;
	}

	public String getAnswer() {
		return answer;
	}
}