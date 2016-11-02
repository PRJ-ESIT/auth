rm -r /opt/tomcat/webapps/auth /opt/tomcat/webapps/auth.war
cd ~/dev/auth/
mvn package
mv ~/dev/auth/target/auth.war /opt/tomcat/webapps/
