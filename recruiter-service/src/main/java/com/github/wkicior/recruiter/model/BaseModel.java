package com.github.wkicior.recruiter.model;

/**
 * Abstract model class for handling entities with key
 * 
 * @author disorder
 *
 */
public abstract class BaseModel {

	protected String id;

	public BaseModel() {

	}

	public BaseModel(String id) {
		super();
		this.id = id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}