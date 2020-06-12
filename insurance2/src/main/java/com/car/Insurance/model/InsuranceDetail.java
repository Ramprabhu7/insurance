package com.car.Insurance.model;

public class InsuranceDetail {
	private String companyName;
	private long premium;
	private long IDV;
	public InsuranceDetail() {
		
	}
	public InsuranceDetail(String companyName, long premium, long iDV) {
		super();
		this.companyName = companyName;
		this.premium = premium;
		IDV = iDV;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public long getPremium() {
		return premium;
	}
	public void setPremium(long premium) {
		this.premium = premium;
	}
	public long getIDV() {
		return IDV;
	}
	public void setIDV(long iDV) {
		IDV = iDV;
	}
}
