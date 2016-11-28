package com.github.wkicior.recruiter.application;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;

import com.github.wkicior.recruiter.model.BaseModel;

/**
 * Produces DocumentMapper with correct type
 * 
 * @author disorder
 *
 */
public class DocumentMapperFactory {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@MapperClass
	@Produces
	public static DocumentMapper<BaseModel> createDocumentMapper(InjectionPoint ip) {
		Annotated annotated = ip.getAnnotated();
		MapperClass annotation = annotated.getAnnotation(MapperClass.class);
		return new DocumentMapper(annotation.value());
	}

}
