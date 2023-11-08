package model.camp;

import java.util.List;

import enums.EnquiryStatus;

public class Enquiry {

	private int enquiryID;
	private int campID;
	private String studentID;
	private String question;
	private List<String> reply;
	private List<String> replierID;
	private EnquiryStatus status;
	
	/**
	 * @param campID
	 * @param studentID
	 */
	public Enquiry(int enquiryID, int campID, String studentID, String question) {
		this.enquiryID = enquiryID;
		this.campID = campID;
		this.studentID = studentID;
		this.question = question;
		this.setStatus(EnquiryStatus.Processing);
	}
	
	public Enquiry(int enquiryID, int campID, String studentID, String question, List<String> reply, List<String> replierID, EnquiryStatus status) {
		this.enquiryID = enquiryID;
		this.campID = campID;
		this.studentID = studentID;
		this.question = question;
		this.reply = reply;
		this.replierID = replierID;
		this.status = status;
	}
	
	/**
	 * @return the inquiryID
	 */
	public int getEnquiryID() {
		return enquiryID;
	}

	/**
	 * @param inquiryID the inquiryID to set
	 */
	public void setEnquiryID(int enquiryID) {
		this.enquiryID = enquiryID;
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
	 * @return the studentID
	 */
	public String getStudentID() {
		return studentID;
	}

	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(String studentID) {
		this.studentID = studentID;
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
	public List<String> getReply() {
		return reply;
	}

	/**
	 * @param reply the reply to set
	 */
	public void setAnswers(List<String> reply) {
		this.reply = reply;
	}

	/**
	 * @return the replierID
	 */
	public List<String> getReplierID() {
		return replierID;
	}
	
	/**
	 * @param replierID
	 */
	public void setReplierID(List<String> replierID) {
		this.replierID = replierID;
	}
	
	/**
	 * @param reply
	 * @return
	 */
	public boolean replyToEnquiry(String reply, String replierID) {
		this.reply.add(reply);
		this.replierID.add(replierID);
		this.status = EnquiryStatus.Processed;
		return true;
	}

	/**
	 * @return the status
	 */
	public EnquiryStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EnquiryStatus status) {
		this.status = status;
	}
	
}
