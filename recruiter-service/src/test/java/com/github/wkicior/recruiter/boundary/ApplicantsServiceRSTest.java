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

import com.github.wkicior.recruiter.boundary.ApplicantsServiceRS;
import com.github.wkicior.recruiter.model.Applicant;
import com.github.wkicior.recruiter.service.ApplicantsService;
import com.google.common.base.Optional;

import junit.framework.TestCase;

public class ApplicantsServiceRSTest extends TestCase {

	@Mock
	ApplicantsService applicantsService;

	@InjectMocks
	ApplicantsServiceRS applicantsServiceRS;

	@Override
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_getAll_returns_all_applicants() {
		Applicant applicant = new Applicant("1l", "Joe");
		Mockito.when(applicantsService.getAll()).thenReturn(Arrays.asList(applicant));
		Response response = applicantsServiceRS.getAll();
		assertTrue(response.hasEntity());
		assertEquals(Arrays.asList(applicant), response.getEntity());
	}

	@Test
	public void test_saveNew_saves_the_applicant_and_returns_redirect_to_it() throws URISyntaxException {
		Applicant applicant = new Applicant("1l", "Joe");
		Mockito.when(applicantsService.save(applicant)).thenReturn(applicant);
		Response response = applicantsServiceRS.saveNew(applicant);
		assertEquals(303, response.getStatus());
	}

	@Test
	public void testGetById_returns_queried_applicant() {
		Applicant applicant = new Applicant("1l", "Joe");
		Mockito.when(applicantsService.getById("1l")).thenReturn(Optional.of(applicant));
		Response response = applicantsServiceRS.get("1l");
		assertTrue(response.hasEntity());
		assertEquals(applicant, response.getEntity());
	}

	@Test
	public void testGetById_returns_404_on_not_found_queried_applicant() {
		Optional<Applicant> applicant = Optional.absent();
		Mockito.when(applicantsService.getById("1l")).thenReturn(applicant);
		Response response = applicantsServiceRS.get("1l");
		assertFalse(response.hasEntity());
		assertEquals(404, response.getStatus());
	}

}
