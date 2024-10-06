package hexlet.code;


import com.fasterxml.jackson.core.type.TypeReference;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.concurrent.Callable;


@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @CommandLine.Parameters(index = "0", description = "path to first file")
    private File firstFile;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private File secondFile;

    @CommandLine.Option(
            names = {"-f", "--format"},
            description = "output format",
            showDefaultValue = CommandLine.Help.Visibility.ALWAYS,
            defaultValue = "stylish"
    )
    private String format = "format";

    public static Map<String, Object> getData(byte[] data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data, new TypeReference<>() {});
    }

    public Integer call() {
        try {
            var firstFileData = getData(Files.readAllBytes(firstFile.toPath()));
            var secondFileData = getData(Files.readAllBytes(secondFile.toPath()));

            System.out.println(firstFileData);
            System.out.println(secondFileData);

        } catch (IOException e) {
            System.err.println("Failed to read files");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Application crashed");
            System.exit(2);
        }

        return 0;
    }

    public static void main(String[] args) {
        var exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
