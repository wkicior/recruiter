package com.github.wkicior.recruiter.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.github.wkicior.recruiter.model.JobOffer;
import com.google.common.base.Optional;

/**
 * Service for handling job offers. Currently it only delegates to the
 * repository service, however logic would rise here on implementing remaining
 * features
 * 
 * @author disorder
 *
 */
@Stateless
public class JobOfferService {

	@Inject
	JobOfferRepository jobOfferRepository;

	public List<JobOffer> getAll() {
		return jobOfferRepository.getAll();
	}

	public JobOffer save(JobOffer offer) {
		return jobOfferRepository.save(offer);
	}

	public Optional<JobOffer> getById(String id) {
		return jobOfferRepository.getById(id);
	}
}
