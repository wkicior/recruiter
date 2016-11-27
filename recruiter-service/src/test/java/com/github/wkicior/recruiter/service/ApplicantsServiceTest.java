package com.github.wkicior.recruiter.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.wkicior.recruiter.exceptions.RecruiterException;
import com.github.wkicior.recruiter.model.Applicant;
import com.github.wkicior.recruiter.model.JobOffer;
import com.google.common.base.Optional;

public class ApplicantsServiceTest {

	private static final String APPLICANT_ID = "A1";
	private static final String OFFER_ID = "O1";

	@Mock
	ApplicantsRepository applicantsRepository;

	@Mock
	JobOfferService joService;

	@InjectMocks
	ApplicantsService service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testApply() throws RecruiterException {
		Optional<Applicant> applicant = Optional.of(new Applicant());
		Mockito.when(applicantsRepository.getById(APPLICANT_ID)).thenReturn(applicant);
		Optional<JobOffer> offer = Optional.of(new JobOffer());
		Mockito.when(joService.getById(OFFER_ID)).thenReturn(offer);
		service.apply(APPLICANT_ID, OFFER_ID);
		Mockito.verify(applicantsRepository).update(applicant.get());
	}

	@Test(expected = RecruiterException.class)
	public void testApply_throws_exception() throws RecruiterException {
		Optional<Applicant> applicant = Optional.absent();
		Mockito.when(applicantsRepository.getById(APPLICANT_ID)).thenReturn(applicant);
		service.apply(APPLICANT_ID, OFFER_ID);
	}
}
