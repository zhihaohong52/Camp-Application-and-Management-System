package model.camp;

import enums.SuggestionStatus;
/**
 * This class {@link Suggestion} is related to camp within the system
 * Suggestions are made by committee member and can be approaved or reject by staff in charge for the camp.
 */
public class Suggestion {


    /**
     * Unique identifier for the suggestion.
     */
    private int suggestionID;

    /**
     * Identifier of the camp associated with the suggestion.
     */
    private int campID;

    /**
     * Identifier of the committee member who made the suggestion.
     */
    private String commiteeID;

    /**
     * Question or description of the suggestion.
     */
    private String question;

    /**
     * Reply or response to the suggestion.
     */
    private String reply;

    /**
     * Identifier of the replier (usually a staff member).
     */
    private String replierID;

    /**
     * Status of the suggestion, indicating whether it's Processing, Approved, or Rejected.
     */
    private SuggestionStatus status;
	
	
    /**
     * Constructor for class {@link Suggestion}
     *
     * @param enquiryID Unique identifier for the suggestion.
     * @param campID Identifier of the camp associated with the suggestion.
     * @param committeeID Identifier of the committee member who made the suggestion.
     * @param question Description of the suggestion.
     */
	public Suggestion(int enquiryID, int campID, String committeeID, String question) {
		this.suggestionID = enquiryID;
		this.campID = campID;
		this.commiteeID = committeeID;
		this.question = question;
		this.setStatus(SuggestionStatus.Processing);
	}
     
	/**
     * Constructor for class {@link Suggestion}
     *
     * @param enquiryID Unique identifier for the suggestion.
     * @param campID Identifier of the camp associated with the suggestion.
     * @param committeeID Identifier of the committee member who made the suggestion.
     * @param question Description of the suggestion.
     * @param reply Response to the suggestion.
     * @param replierID Identifier of the replier (usually a staff member).
     * @param status Status of the suggestion, indicating whether it's Processing, Approved, or Rejected.
     */
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
     * Gets the unique identifier of the suggestion.
     *
     * @return The unique identifier of the suggestion.
     */
	public int getSuggestionID() {
		return suggestionID;
	}

	/**
     * Sets the unique identifier of the suggestion.
     *
     * @param suggestionID The unique identifier to set for the suggestion.
     */
	public void setSuggestionID(int suggestionID) {
		this.suggestionID = suggestionID;
	}

	 /**
     * Gets the identifier of the camp associated with the suggestion.
     *
     * @return The identifier of the camp associated with the suggestion.
     */
	public int getCampID() {
		return campID;
	}
	/**
     * Sets the identifier of the camp associated with the suggestion.
     *
     * @param campID The identifier to set for the camp associated with the suggestion.
     */
	public void setCampID(int campID) {
		this.campID = campID;
	}

	/**
     * Gets the identifier of the committee member who made the suggestion.
     *
     * @return The identifier of the committee member who made the suggestion.
     */
	public String getCommiteeID() {
		return commiteeID;
	}
	/**
     * Sets the identifier of the committee member who made the suggestion.
     *
     * @param commiteeID The identifier to set for the committee member who made the suggestion.
     */
	public void setCommiteeID(String commiteeID) {
		this.commiteeID = commiteeID;
	}

	 /**
     * Gets the description of the suggestion.
     *
     * @return The description of the suggestion.
     */
	public String getQuestion() {
		return question;
	}

	/**
     * Sets the description of the suggestion.
     *
     * @param question The description to set for the suggestion.
     */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
     * Gets the response to the suggestion.
     *
     * @return The response to the suggestion.
     */
	public String getReply() {
		return reply;
	}

	/**
     * Sets the response to the suggestion.
     *
     * @param reply The response to set for the suggestion.
     */
	public void setAnswers(String reply) {
		this.reply = reply;
	}

	/**
     * Gets the identifier of the replier (Staff incharge).
     *
     * @return The identifier of the replier (Staff incharge).
     */
	public String getReplierID() {
		return replierID;
	}
	
	/**
     * Sets the identifier of the replier (usually a staff member).
     *
     * @param replierID The identifier to set for the replier (usually a staff member).
     */
	public void setReplierID(String replierID) {
		this.replierID = replierID;
	}
	
	/**
     * Approves or rejects the suggestion and sets the reply and status accordingly.
     *
     * @param reply The reply or response to the suggestion.
     * @param replierID The identifier of the replier.
     * @param approve True if the suggestion is approved, false if rejected.
     * @return True if the suggestion is successfully approved or rejected, false otherwise.
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
     * Gets the status of the suggestion.
     *
     * @return The status of the suggestion.
     */
	public SuggestionStatus getStatus() {
		return status;
	}

	  /**
     * Sets the status of the suggestion.
     *
     * @param processing The status to set for the suggestion.
     */
	public void setStatus(SuggestionStatus processing) {
		this.status = processing;
	}
	
}
