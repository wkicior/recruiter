package com.github.wkicior.recruiter.service;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.github.wkicior.recruiter.application.DocumentMapper;
import com.github.wkicior.recruiter.application.RecruiterMongoCollection;
import com.github.wkicior.recruiter.model.JobOffer;
import com.google.common.base.Optional;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

@Stateless
public class JobOfferRepository {
	@Inject
	@RecruiterMongoCollection("offers")
	private MongoCollection<Document> offersCollection;

	private DocumentMapper<JobOffer> documentMapper = new DocumentMapper<JobOffer>(JobOffer.class);

	public List<JobOffer> getAll() {
		MongoCursor<Document> cursor = offersCollection.find().iterator();
		List<JobOffer> applicants = new ArrayList<JobOffer>();
		while (cursor.hasNext()) {
			JobOffer applicant = documentMapper.getModel(Optional.of(cursor.next())).get();
			applicants.add(applicant);
		}
		return applicants;
	}

	public JobOffer save(JobOffer offer) {
		Document doc = documentMapper.getDocument(offer);
		offersCollection.insertOne(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		return getById(id.toString()).get();
	}

	public Optional<JobOffer> getById(String id) {
		Optional<Document> applicantDoc = Optional
				.fromNullable(offersCollection.find(eq("_id", new ObjectId(id))).first());
		return documentMapper.getModel(applicantDoc);
	}
}
