install:
	./gradlew install

run-help:
	./build/install/java-project-71/bin/java-project-71 -h

run-json:
	./build/install/java-project-71/bin/java-project-71 "src/test/resources/file1.json" "src/test/resources/file2.json"

run-json-nested:
	./build/install/java-project-71/bin/java-project-71 "src/test/resources/json/nested1.json" "src/test/resources/json/nested2.json"

run-yml:
	./build/install/java-project-71/bin/java-project-71 "src/test/resources/yaml/file1.yaml" "src/test/resources/yaml/file2.yaml"

lint:
	./gradlew checkstyleTest
	./gradlew checkstyleMain

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

.PHONY: run-dist
