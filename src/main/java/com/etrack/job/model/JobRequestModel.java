package com.etrack.job.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRequestModel extends JobAbstract {

	/** Job unique identifier */
	@JsonProperty("JOB_NO")
	private String jobNo;

	/** The description for JOB */
	@JsonProperty("DESCRIPTION")
	private String jobDescription;

	/** The remarks or feedback */
	@JsonProperty("REMARKS")
	private String remarks;

	/** Username whom this job is requested to. */
	@JsonProperty("REQUEST_TO")
	private String reqTo;

	/** Username whom this job is requested by */
	@JsonProperty("REQUEST_BY")
	private String reqBy;

	/** the current job status */
	@JsonProperty("JOB_STATUS")
	private String jobStatus;

	/** Date when this job is opened */
	@JsonProperty("OPEN_DATE")
	private Date openDate;

	/** Date when this job is closed */
	@JsonProperty("CLOSE_DATE")
	private Date closeDate;

	/** List of image file-path<code>String<code> */
	@JsonProperty("LIST_IMAGES")
	private List<String> listOfImages;

	public JobRequestModel() {
		// no args const
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getReqTo() {
		return reqTo;
	}

	public void setReqTo(String reqTo) {
		this.reqTo = reqTo;
	}

	public String getReqBy() {
		return reqBy;
	}

	public void setReqBy(String reqBy) {
		this.reqBy = reqBy;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public List<String> getListOfImages() {
		return listOfImages;
	}

	public void setListOfImages(List<String> listOfImages) {
		this.listOfImages = listOfImages;
	}

}
