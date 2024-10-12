run-build:
	./build/install/app/bin/app

run-dist-help:
	./build/install/app/bin/app -h

lint:
	./gradlew checkstyleTest
	./gradlew checkstyleMain

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

.PHONY: run-dist
