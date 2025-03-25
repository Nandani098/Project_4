FROM tomcat:9

# Copy the WAR file into the Tomcat webapps directory
COPY target/ORS_Project4.war /usr/local/tomcat/webapps/

# Expose the port Tomcat is running on
EXPOSE 8080

# Start Tomcat when the container starts
CMD ["catalina.sh", "run"]
