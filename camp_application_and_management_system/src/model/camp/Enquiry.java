package model.camp;

import java.util.ArrayList;
import java.util.List;

import enums.EnquiryStatus;

/**
 * This {@link Enquiry} Class represent inquiry related to a camp. 
 * An inquiry is sent in by a student and the student may then receive replies from staff or committee members of the camp.
 */
public class Enquiry {

	/**
     * Unique identifier for the inquiry.
     */
    private int enquiryID;

    /**
     * Identifier of the camp associated with the inquiry.
     */
    private int campID;

    /**
     * Identifier of the student who initiated the inquiry.
     */
    private String studentID;

    /**
     * Question or description of the inquiry.
     */
    private String question;

    /**
     * List of replies to the inquiry.
     */
    private List<String> reply = new ArrayList<String>();

    /**
     * List of replier identifiers corresponding to each reply.
     */
    private List<String> replierID = new ArrayList<String>();

    /**
     * Status of the inquiry, indicating whether it's Processing or Processed.
     */
    private EnquiryStatus status;
	
	 /**
     * Constuctor of class {@link Enquiry}
     *
     * @param enquiryID Unique identifier for the inquiry.
     * @param campID Identifier of the camp associated with the inquiry.
     * @param studentID Identifier of the student who initiated the inquiry.
     * @param question Question or description of the inquiry.
     */
	public Enquiry(int enquiryID, int campID, String studentID, String question) {
		this.enquiryID = enquiryID;
		this.campID = campID;
		this.studentID = studentID;
		this.question = question;
		this.setStatus(EnquiryStatus.Processing);
	}
	
	 /**
     * Constuctor of class {@link Enquiry}
	 * 
     * @param enquiryID Unique identifier for the inquiry.
     * @param campID Identifier of the camp associated with the inquiry.
     * @param studentID Identifier of the student who initiated the inquiry.
     * @param question Question or description of the inquiry.
     * @param reply List of replies to the inquiry.
     * @param replierID List of replier identifiers corresponding to each reply.
     * @param status Status of the inquiry, indicating whether it's Processing or Processed.
     */
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
     * Gets the unique identifier of the inquiry.
     *
     * @return The unique identifier of the inquiry.
     */
	public int getEnquiryID() {
		return enquiryID;
	}

	/**
     * Sets the unique identifier of the inquiry.
     *
     * @param enquiryID The unique identifier to set for the inquiry.
     */
	public void setEnquiryID(int enquiryID) {
		this.enquiryID = enquiryID;
	}

	/**
     * Gets the identifier of the camp associated with the inquiry.
     *
     * @return The identifier of the camp associated with the inquiry.
     */
	public int getCampID() {
		return campID;
	}

	/**
     * Sets the identifier of the camp associated with the inquiry.
     *
     * @param campID The identifier to set for the camp associated with the inquiry.
     */
	public void setCampID(int campID) {
		this.campID = campID;
	}

	/**
     * Gets the identifier of the student who initiated the inquiry.
     *
     * @return The identifier of the student who initiated the inquiry.
     */
	public String getStudentID() {
		return studentID;
	}

	/**
     * Sets the identifier of the student who initiated the inquiry.
     *
     * @param studentID The identifier to set for the student who initiated the inquiry.
     */
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	/**
     * Gets the question of the inquiry.
     *
     * @return The question or description of the inquiry.
     */
	public String getQuestion() {
		return question;
	}

	/**
     * Sets the question or description of the inquiry.
     *
     * @param question The question or description to set for the inquiry.
     */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
     * Gets the list of replies to the inquiry.
     *
     * @return The list of replies to the inquiry.
     */
	public List<String> getReply() {
		return reply;
	}

	/**
     * Sets the list of replies to the inquiry.
     *
     * @param reply The list of replies to set for the inquiry.
     */
	public void setAnswers(List<String> reply) {
		this.reply = reply;
	}

	/**
     * Gets the list of replier identifiers.
	 * 
     * @return The list of replier identifiers.
	 */
	public List<String> getReplierID() {
		return replierID;
	}
	
	/**
     * Sets the list of replier identifiers.
     *
     * @param replierID The list of replier identifiers.
     */
	public void setReplierID(List<String> replierID) {
		this.replierID = replierID;
	}
	
	/**
     * Adds a reply to the inquiry and updates the status to Processed.
     *
     * @param reply The reply to add to the inquiry.
     * @param replierID The identifier of the replier.
     * @return {@code True} if the reply is successfully added, {@code False} otherwise.
     */

	public boolean replyToEnquiry(String reply, String replierID) {
		this.reply.add(reply);
		this.replierID.add(replierID);
		this.status = EnquiryStatus.Processed;
		return true;
	}

	/**
     * Gets the status of the inquiry.
     *
     * @return The status of the inquiry.
     */
	public EnquiryStatus getStatus() {
		return status;
	}

	/**
     * Sets the status of the inquiry.
     *
     * @param status The status to set for the inquiry.
     */
	public void setStatus(EnquiryStatus status) {
		this.status = status;
	}
	
}
