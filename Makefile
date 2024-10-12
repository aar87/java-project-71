run-build:
	./build/install/app/bin/app

run-dist:
	./build/install/app/bin/app "src/test/resources/emptyFile.json" "src/test/resources/file2.json"

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
