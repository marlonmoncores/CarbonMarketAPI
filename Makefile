DEBUG_5005=-Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"

FLYWAY_MIGRATE= flyway:migrate
FLYWAY_CLEAN= flyway:clean
FLYWAY_DB_USER= -Dflyway.user=postgres
FLYWAY_DB_PASSWORD= -Dflyway.password=Code1234
FLYWAY_DB_URL=-Dflyway.url=jdbc:postgresql://localhost:5432/carbonmarketdb

docker-up-d:
	docker-compose up -d

run-dev: docker-up-d
	mvn spring-boot:run -Drun.profiles=dev $(DEBUG_5005)

flyway-clean:
	mvn $(FLYWAY_CLEAN) $(FLYWAY_DB_URL) $(FLYWAY_DB_USER) $(FLYWAY_DB_PASSWORD)

flyway-migrate:
	mvn $(FLYWAY_MIGRATE) $(FLYWAY_DB_URL) $(FLYWAY_DB_USER) $(FLYWAY_DB_PASSWORD)

flyway-reset: flyway-clean flyway-migrate
