package com.etrack.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminTestModel {

	@JsonProperty("VAR_ONE")
	private String var1;
	@JsonProperty("VAR_TWO")
	private String var2;
	@JsonProperty("VAR_THREE")
	private String var3;

	public AdminTestModel() {
		// no args conts
	}

	public AdminTestModel(String var1, String var2, String var3) {
		super();
		this.var1 = var1;
		this.var2 = var2;
		this.var3 = var3;
	}

	public String getVar1() {
		return var1;
	}

	public void setVar1(String var1) {
		this.var1 = var1;
	}

	public String getVar2() {
		return var2;
	}

	public void setVar2(String var2) {
		this.var2 = var2;
	}

	public String getVar3() {
		return var3;
	}

	public void setVar3(String var3) {
		this.var3 = var3;
	}

}
