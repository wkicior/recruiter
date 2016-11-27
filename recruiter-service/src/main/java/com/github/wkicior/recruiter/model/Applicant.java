package com.github.wkicior.recruiter.model;

import java.util.ArrayList;
import java.util.List;

public final class Applicant extends BaseModel {
	private String name;
	private List<JobOffer> applications = new ArrayList<JobOffer>();

	public Applicant() {
	}

	public Applicant(String id, String name) {
		super(id);
		this.name = name;
	}

	public List<JobOffer> getApplications() {
		return applications;
	}

	public void setApplications(List<JobOffer> applications) {
		this.applications = applications;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
