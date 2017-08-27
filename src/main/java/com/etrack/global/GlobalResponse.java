package com.etrack.global;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlobalResponse {

	@JsonProperty("SUCCESS")
	private boolean success;
	@JsonProperty("MSG")
	private String msg;
	
	public GlobalResponse(){
		// no args const
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
}
