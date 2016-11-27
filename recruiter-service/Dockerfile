FROM fedora:23
MAINTAINER https://github.com/wkicior
RUN dnf update -y
RUN dnf install -y wget && wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u5-b13/jdk-8u5-linux-x64.rpm
RUN dnf install -y tar curl unzip zip expect findutils
RUN rpm -ivh jdk-8u5-linux-x64.rpm && rm jdk-8u5-linux-x64.rpm

RUN curl -L -o /tmp/glassfish-4.1.zip http://download.java.net/glassfish/4.1/release/glassfish-4.1.zip && \
unzip /tmp/glassfish-4.1.zip -d /usr/local && \
rm -f /tmp/glassfish-4.1.zip

RUN cd /opt && wget http://mirror.symnds.com/software/Apache/maven/maven-3/3.0.5/binaries/apache-maven-3.0.5-bin.tar.gz
RUN cd /opt && tar -zxvf apache-maven*
ENV JAVA_HOME /usr/java/jdk1.8.0_05

# Build our apps from source code
RUN mkdir -p /opt/recruiter-service

EXPOSE 8080 4848 8181
WORKDIR /opt/recruiter-service
ADD change_admin_password_func.sh /usr/local/glassfish4/bin/
RUN chmod +x /usr/local/glassfish4/bin/change_admin_password_func.sh
RUN /usr/local/glassfish4/bin/asadmin start-domain  
RUN /usr/local/glassfish4/bin/change_admin_password_func.sh admin123
ADD pom.xml /opt/recruiter-service
ADD src /opt/recruiter-service/src
CMD /opt/apache-maven-3.0.5/bin/mvn -fn glassfish:undeploy &&  /opt/apache-maven-3.0.5/bin/mvn clean package glassfish:deploy && tail -f /usr/local/glassfish4/domains/domain1/logs/server.log


