APP_DIR=/Users/antonraevsky/IdeaProjects/java-project-71
RUN=./build/install/java-project-71/bin/java-project-71
FILE_SRC=$(APP_DIR)/src/test/resources/differ/files

install:
	./gradlew install

run-help:
	$(RUN) -h

run-json:
	cd $(APP_DIR)
	$(RUN) $(FILE_SRC)/flat1.json $(FILE_SRC)/flat2.json

run-json-plain:
	$(RUN) $(FILE_SRC)/flat1.json $(FILE_SRC)/flat2.json -f plain

run-json-nested:
	$(RUN) $(FILE_SRC)/nested1.json $(FILE_SRC)/nested2.json

run-json-formatter:
	$(RUN) -f json $(FILE_SRC)/flat1.json $(FILE_SRC)/flat2.json

run-json-nested-plain:
	$(RUN) $(FILE_SRC)/nested1.json $(FILE_SRC)/nested2.json -f plain

run-yml:
	$(RUN) $(FILE_SRC)/flat1.yaml $(FILE_SRC)/flat2.yaml

lint:
	./gradlew checkstyleTest
	./gradlew checkstyleMain

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

.PHONY: install
