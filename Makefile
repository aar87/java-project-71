install:
	./gradlew install

run-help:
	./build/install/java-project-71/bin/java-project-71 -h

run-json:
	./build/install/java-project-71/bin/java-project-71 "src/test/resources/file1.json" "src/test/resources/file2.json"

run-json-plain:
	./build/install/java-project-71/bin/java-project-71 "src/test/resources/file1.json" "src/test/resources/file2.json" -f plain

run-json-nested:
	./build/install/java-project-71/bin/java-project-71 "src/test/resources/json/nested1.json" "src/test/resources/json/nested2.json"

run-json-formatter:
	./build/install/java-project-71/bin/java-project-71 -f json "src/test/resources/file1.json" "src/test/resources/file2.json"

run-json-nested-plain:
	./build/install/java-project-71/bin/java-project-71 "src/test/resources/json/nested1.json" "src/test/resources/json/nested2.json" -f plain

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
