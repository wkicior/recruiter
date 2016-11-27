package com.github.wkicior.recruiter.boundary;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.wkicior.recruiter.exceptions.RecruiterException;
import com.github.wkicior.recruiter.model.Applicant;
import com.github.wkicior.recruiter.service.ApplicantsService;
import com.google.common.base.Optional;

/**
 * REST service for Applicants
 * 
 * @author disorder
 *
 */
@Path("/applicants")
public class ApplicantsServiceRS {

	@Inject
	ApplicantsService service;

	/**
	 * Returns all saved applicants
	 * 
	 * @return
	 */
	@GET
	@Produces({ "application/json" })
	public Response getAll() {
		List<Applicant> applicants = service.getAll();
		GenericEntity<List<Applicant>> entities = new GenericEntity<List<Applicant>>(applicants) {
		};
		return Response.ok(entities).build();
	}

	/**
	 * Gets specific applicant by id
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ "application/json" })
	@Path("/{id}")
	public Response get(@PathParam("id") String id) {
		Optional<Applicant> applicant = service.getById(id);
		if (!applicant.isPresent()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		GenericEntity<Applicant> entity = new GenericEntity<Applicant>(applicant.get()) {
		};
		return Response.ok(entity).build();
	}

	/**
	 * Saves new applicant
	 * 
	 * @param applicant
	 * @return
	 * @throws URISyntaxException
	 */
	@POST
	@Consumes({ "application/json" })
	public Response saveNew(Applicant applicant) throws URISyntaxException {
		applicant = service.save(applicant);
		URI location = new URI("/recruiter-service/resources/applicants/" + applicant.getId());
		return Response.seeOther(location).build();
	}

	/**
	 * adds new job offer application for the applicant
	 * 
	 * @param applicant
	 * @return
	 * @throws URISyntaxException
	 */
	@POST
	@Path("/{applicantId}/applications/{offerId}")
	public Response createApplication(@PathParam("applicantId") String applicantId,
			@PathParam("offerId") String offerId) throws URISyntaxException {
		try {
			service.apply(applicantId, offerId);
			URI location = new URI("/recruiter-service/resources/applicants/" + applicantId);
			return Response.seeOther(location).build();
		} catch (RecruiterException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
