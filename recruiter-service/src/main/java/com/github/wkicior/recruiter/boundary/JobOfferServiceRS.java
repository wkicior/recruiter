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

import com.github.wkicior.recruiter.model.JobOffer;
import com.github.wkicior.recruiter.service.JobOfferService;
import com.google.common.base.Optional;

/**
 * The REST service for managing job offers
 * 
 * @author disorder
 *
 */
@Path("/offers")
public class JobOfferServiceRS {

	@Inject
	JobOfferService service;

	/**
	 * Returns all stored job offers
	 * 
	 * @return
	 */
	@GET
	@Produces({ "application/json" })
	public Response getAll() {
		List<JobOffer> applicants = service.getAll();
		GenericEntity<List<JobOffer>> entities = new GenericEntity<List<JobOffer>>(applicants) {
		};
		return Response.ok(entities).build();
	}

	/**
	 * Gets specific job offer by id
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ "application/json" })
	@Path("/{id}")
	public Response get(@PathParam("id") String id) {
		Optional<JobOffer> offer = service.getById(id);
		if (!offer.isPresent()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		GenericEntity<JobOffer> entity = new GenericEntity<JobOffer>(offer.get()) {
		};
		return Response.ok(entity).build();

	}

	/**
	 * Saves new job offer
	 * 
	 * @param applicant
	 * @return
	 * @throws URISyntaxException
	 */
	@POST
	@Consumes({ "application/json" })
	public Response saveNew(JobOffer offer) throws URISyntaxException {
		offer = service.save(offer);
		URI location = new URI("/recruiter-service/resources/offers/" + offer.getId());
		return Response.seeOther(location).build();
	}

}
