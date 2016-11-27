# recruiter-service
The prototype of the recruitment service.  
The project exposes some REST api services with the following functionalities:  
-listing/saving applicants  
-listing/saving job offers  
-registering job offer for the aplicant  

The applicant and job offer model classes are limited to names only.  
Extending them with more values would be the next step forward  

requirements to run: docker, dockier-compose  

# BUILD:
$ cd recruiter-service
$ docker build -t wkicior/recruiter-service .

# RUN (from main directory):
$ docker-compose up

#EXAMPLE USAGE:
listing applicants
$ curl http://localhost.localdomain:8090/recruiter-service/resources/applicants

listing job offers
$ curl http://localhost.localdomain:8090/recruiter-service/resources/offers

adding applicant
$ curl -H "Content-Type: application/json" -X POST -d '{"name":"Test User"}' http://localhost.localdomain:8090/recruiter-service/resources/applicants

adding offer
$ curl -H "Content-Type: application/json" -X POST -d '{"positionName":"Java architect"}' http://localhost.localdomain:8090/recruiter-service/resources/offers

applying for the offer
curl -X POST http://localhost.localdomain:8090/recruiter-service/resources/applicants/[applicant_id]/applications/[offer_id]

#EXAMPLE SESSION
Adds new job offer, registers new applicant and applies job offer to the applicant  
$ curl -H "Content-Type: application/json" -X POST -d '{"positionName":"Lead Java Engineer"}' http://localhost.localdomain:8090/recruiter-service/resources/offers

$ curl http://localhost.localdomain:8090/recruiter-service/resources/offers
[{"type":"jobOffer","id":"583b6427c9e77c0137e45b10","positionName":"Lead Java Engineer"},{"type":"jobOffer","id":"583b6456c9e77c0137e45b11","positionName":"Senior Developer"}]

$ curl -H "Content-Type: application/json" -X POST -d '{"name":"Wojciech Kicior"}' http://localhost.localdomain:8090/recruiter-service/resources/applicants

$ curl http://localhost.localdomain:8090/recruiter-service/resources/applicants
[{"type":"applicant","id":"583b64b2c9e77c0137e45b12","applications":[],"name":"Wojciech Kicior"}]

curl -X POST  http://localhost.localdomain:8090/recruiter-service/resources/applicants/583b64b2c9e77c0137e45b12/applications/583b6427c9e77c0137e45b10

$ curl http://localhost.localdomain:8090/recruiter-service/resources/applicants
[{"type":"applicant","id":"583b64b2c9e77c0137e45b12","applications":[{"id":"583b6427c9e77c0137e45b10","positionName":"Lead Java Engineer"}],"name":"Wojciech Kicior"}]



