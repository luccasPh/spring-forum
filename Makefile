MVN11="JAVA_HOME=/Users/lucaspinheiro/.sdkman/candidates/java/11.0.15-tem && mvn"

.PHONY: start-dev
start-dev:
	bash -c "${MVN11} spring-boot:run -Dspring-boot.run.profiles=dev"

