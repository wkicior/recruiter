package com.github.wkicior.recruiter.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.github.wkicior.recruiter.exceptions.RecruiterException;
import com.github.wkicior.recruiter.model.Applicant;
import com.github.wkicior.recruiter.model.JobOffer;
import com.google.common.base.Optional;

/**
 * Service bean for handling business logic specific for applicants
 * 
 * @author disorder
 *
 */
@Stateless
public class ApplicantsService {

	@Inject
	private JobOfferService jobOfferService;

	@Inject
	ApplicantsRepository applicantsRepository;

	/**
	 * Attaches job offer to selected applicant
	 * 
	 * @param applicantId
	 * @param offerId
	 * @throws RecruiterException
	 */
	public void apply(String applicantId, String offerId) throws RecruiterException {
		Optional<Applicant> applicant = applicantsRepository.getById(applicantId);
		Optional<JobOffer> offer = jobOfferService.getById(offerId);
		if (!applicant.isPresent() || !offer.isPresent()) {
			throw new RecruiterException("Not found");
		}
		applicant.get().getApplications().add(offer.get());
		applicantsRepository.update(applicant.get());
	}

	public Applicant save(Applicant applicant) {
		return applicantsRepository.save(applicant);
	}

	public List<Applicant> getAll() {
		return applicantsRepository.getAll();
	}

	public Optional<Applicant> getById(String id) {
		return applicantsRepository.getById(id);
	}

}
