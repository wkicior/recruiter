# recruiter-service
Prototype recruitment service.
The project exposes some REST api services with the following functionalities:
-listing/saving applicants
-listing/saving job offers
-registering job offer for the aplicant

The applicant and job offer model classes are limited to names only.
Extending them with more values would be the next step forward

requirements to run: docker, dockier-compose

build:
$ cd recruiter-service
$ docker build -t wkicior/recruiter-service .

run (from main directory):
$ docker-compose up

sample usage:
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

