package com.github.wkicior.recruiter.boundary;

import java.net.URISyntaxException;
import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.wkicior.recruiter.boundary.JobOfferServiceRS;
import com.github.wkicior.recruiter.model.JobOffer;
import com.github.wkicior.recruiter.service.JobOfferService;
import com.google.common.base.Optional;

import junit.framework.TestCase;

public class JobOfferServiceRSTest extends TestCase {

	@Mock
	JobOfferService jobOfferService;

	@InjectMocks
	JobOfferServiceRS jobOfferServiceRS;

	@Override
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_getAll_returns_all_jobOffers() {
		JobOffer jobOffer = new JobOffer("1l", "Developer");
		Mockito.when(jobOfferService.getAll()).thenReturn(Arrays.asList(jobOffer));
		Response response = jobOfferServiceRS.getAll();
		assertTrue(response.hasEntity());
		assertEquals(Arrays.asList(jobOffer), response.getEntity());
	}

	@Test
	public void test_saveNew_saves_the_jobOffer_and_returns_redirect_to_it() throws URISyntaxException {
		JobOffer offer = new JobOffer("1l", "Joe");
		Mockito.when(jobOfferService.save(offer)).thenReturn(offer);
		Response response = jobOfferServiceRS.saveNew(offer);
		assertEquals(303, response.getStatus());
	}

	@Test
	public void testGetById_returns_queried_jobOffer() {
		JobOffer offer = new JobOffer("1l", "Joe");
		Mockito.when(jobOfferService.getById("1l")).thenReturn(Optional.of(offer));
		Response response = jobOfferServiceRS.get("1l");
		assertTrue(response.hasEntity());
		assertEquals(offer, response.getEntity());
	}

	@Test
	public void testGetById_returns_404_on_not_found_queried_applicant() {
		Optional<JobOffer> jobOffer = Optional.absent();
		Mockito.when(jobOfferService.getById("1l")).thenReturn(jobOffer);
		Response response = jobOfferServiceRS.get("1l");
		assertFalse(response.hasEntity());
		assertEquals(404, response.getStatus());
	}
}
