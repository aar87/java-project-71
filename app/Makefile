APP_DIR=$(PWD)
RUN=./build/install/app/bin/app
FILE_SRC=$(APP_DIR)/src/test/resources/differ/files

build:
	@./gradlew clean build

lint:
	@./gradlew checkstyleTest
	@./gradlew checkstyleMain

test:
	@./gradlew test

report:
	@./gradlew jacocoTestReport

clean:
	@./gradlew clean

install:
	@./gradlew clean install

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
