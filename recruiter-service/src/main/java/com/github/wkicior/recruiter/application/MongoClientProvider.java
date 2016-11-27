package com.github.wkicior.recruiter.application;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * MongoDB connection provider
 * 
 * @author disorder
 *
 */
@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class MongoClientProvider {

	private MongoClient mongoClient = null;

	@Lock(LockType.READ)
	public MongoClient getMongoClient() {
		return mongoClient;
	}

	@PostConstruct
	public void init() {
		String mongoIpAddress = "recruiter-mongo";
		Integer mongoPort = 27017;
		mongoClient = new MongoClient(mongoIpAddress, mongoPort);
	}

	@Produces
	public MongoDatabase getDB() {
		MongoDatabase db = getMongoClient().getDatabase("recruiterDB");
		return db;
	}

	@Produces
	@RecruiterMongoCollection
	public <Document> MongoCollection<org.bson.Document> expose(InjectionPoint ip) {
		Annotated annotated = ip.getAnnotated();
		RecruiterMongoCollection annotation = annotated.getAnnotation(RecruiterMongoCollection.class);
		return getDB().getCollection(annotation.value());
	}

}