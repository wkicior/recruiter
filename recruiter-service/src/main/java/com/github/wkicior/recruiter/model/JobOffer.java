package com.github.wkicior.recruiter.model;

public class JobOffer extends BaseModel {
	private String positionName;

	public JobOffer() {
		super();
	}

	public JobOffer(String id, String positionName) {
		super(id);
		this.positionName = positionName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
