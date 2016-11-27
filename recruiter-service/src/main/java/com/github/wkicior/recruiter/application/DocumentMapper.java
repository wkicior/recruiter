package com.github.wkicior.recruiter.application;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.github.wkicior.recruiter.model.BaseModel;
import com.google.common.base.Optional;
import com.google.gson.Gson;

/**
 * Document mapper used for mapping model objects into/from org.bson.Document
 * 
 * @author disorder
 *
 * @param <T>
 */
public class DocumentMapper<T extends BaseModel> {
	final Class<T> typeParameterClass;

	public DocumentMapper(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
	}

	public Optional<T> getModel(Optional<Document> document) {
		if (document.isPresent()) {
			T model = new Gson().fromJson(document.get().toJson(), typeParameterClass);
			ObjectId id = (ObjectId) document.get().get("_id");
			model.setId(id.toString());
			return Optional.of(model);
		}
		return Optional.absent();
	}

	public Document getDocument(T model) {
		String json = new Gson().toJson(model);
		return Document.parse(json);
	}
}
