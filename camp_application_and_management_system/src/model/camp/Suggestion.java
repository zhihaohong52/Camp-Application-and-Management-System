package model.camp;

import enums.SuggestionStatus;

public class Suggestion {

	private int suggestionID;
	private int campID;
	private String commiteeID;
	private String question;
	private String reply;
	private String replierID;
	private SuggestionStatus status;
	
	/**
	 * @param campID
	 * @param commiteeID
	 */
	public Suggestion(int enquiryID, int campID, String committeeID, String question) {
		this.suggestionID = enquiryID;
		this.campID = campID;
		this.commiteeID = committeeID;
		this.question = question;
		this.setStatus(SuggestionStatus.Processing);
	}
	
	public Suggestion(int enquiryID, int campID, String committeeID, String question, String reply, String replierID, SuggestionStatus status) {
		this.suggestionID = enquiryID;
		this.campID = campID;
		this.commiteeID = committeeID;
		this.question = question;
		this.reply = reply;
		this.replierID = replierID;
		this.status = status;
	}
	
	/**
	 * @return the suggestionID
	 */
	public int getSuggestionID() {
		return suggestionID;
	}

	/**
	 * @param suggestionID the suggestionID to set
	 */
	public void setSuggestionID(int suggestionID) {
		this.suggestionID = suggestionID;
	}

	/**
	 * @return the campID
	 */
	public int getCampID() {
		return campID;
	}

	/**
	 * @param campID the campID to set
	 */
	public void setCampID(int campID) {
		this.campID = campID;
	}

	/**
	 * @return the commiteeID
	 */
	public String getCommiteeID() {
		return commiteeID;
	}

	/**
	 * @param commiteeID the commiteeID to set
	 */
	public void setCommiteeID(String commiteeID) {
		this.commiteeID = commiteeID;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the reply
	 */
	public String getReply() {
		return reply;
	}

	/**
	 * @param reply the reply to set
	 */
	public void setAnswers(String reply) {
		this.reply = reply;
	}

	/**
	 * @return the replierID
	 */
	public String getReplierID() {
		return replierID;
	}
	
	/**
	 * @param replierID
	 */
	public void setReplierID(String replierID) {
		this.replierID = replierID;
	}
	
	/**
	 * @param reply
	 * @param approve 
	 * @return
	 */
	public boolean approveSuggestion(String reply, String replierID, boolean approve) {
		this.reply = reply;
		this.replierID = replierID;
		if (approve) {
			this.status = SuggestionStatus.Approved;
		}
		else {
			this.status = SuggestionStatus.Rejected;
		}
		return true;
	}

	/**
	 * @return the status
	 */
	public SuggestionStatus getStatus() {
		return status;
	}

	/**
	 * @param processing the status to set
	 */
	public void setStatus(SuggestionStatus processing) {
		this.status = processing;
	}
	
}
