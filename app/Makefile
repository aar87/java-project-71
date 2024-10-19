APP_DIR=$(PWD)
RUN=./build/install/java-project-71/bin/java-project-71
FILE_SRC=$(APP_DIR)/src/test/resources/differ/files

install:
	@./gradlew install

build:
	@./gradlew clean build

lint:
	@./gradlew checkstyleTest
	@./gradlew checkstyleMain

test:
	@./gradlew test

report:
	@./gradlew jacocoTestReport

run-help:
	$(RUN) -h

run-json:
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

.PHONY: build
