package com.github.wkicior.recruiter.service;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.github.wkicior.recruiter.application.DocumentMapper;
import com.github.wkicior.recruiter.application.MapperClass;
import com.github.wkicior.recruiter.application.RecruiterMongoCollection;
import com.github.wkicior.recruiter.model.Applicant;
import com.github.wkicior.recruiter.model.BaseModel;
import com.google.common.base.Optional;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

/**
 * Class extracts mongoDB repository of applicants
 * 
 * @author disorder
 *
 */
@Stateless
public class ApplicantsRepository {

	@Inject
	@RecruiterMongoCollection("applicants")
	private MongoCollection<Document> applicantsCollection;

	@Inject
	@MapperClass(Applicant.class)
	private DocumentMapper<BaseModel> documentMapper;

	public List<Applicant> getAll() {
		MongoCursor<Document> cursor = applicantsCollection.find().iterator();
		List<Applicant> applicants = new ArrayList<Applicant>();
		while (cursor.hasNext()) {
			Applicant applicant = (Applicant) documentMapper.getModel(Optional.of(cursor.next())).get();
			applicants.add(applicant);
		}
		return applicants;
	}

	public Applicant save(Applicant applicant) {
		Document doc = documentMapper.getDocument(applicant);
		applicantsCollection.insertOne(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		return getById(id.toString()).get();
	}

	public void update(Applicant applicant) {
		Document doc = documentMapper.getDocument(applicant);
		applicantsCollection.findOneAndReplace(eq("_id", new ObjectId(applicant.getId())), doc);
	}

	public Optional<Applicant> getById(String i) {
		Optional<Document> applicantDoc = Optional
				.fromNullable(applicantsCollection.find(eq("_id", new ObjectId(i))).first());

		Optional<BaseModel> entity = documentMapper.getModel(applicantDoc);
		return Optional.fromNullable((Applicant) entity.orNull());
	}

}
