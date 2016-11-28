package com.github.wkicior.recruiter.application;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

import com.github.wkicior.recruiter.model.Applicant;
import com.github.wkicior.recruiter.model.BaseModel;

@Qualifier
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MapperClass {
	@Nonbinding
	Class<? extends BaseModel> value() default Applicant.class;
}
