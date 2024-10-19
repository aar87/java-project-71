package hexlet.code;

import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;


@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @CommandLine.Parameters(index = "0", description = "path to first file")
    public File firstFile;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    public File secondFile;

    @CommandLine.Option(
            names = {"-f", "--format"},
            description = "output format",
            showDefaultValue = CommandLine.Help.Visibility.ALWAYS,
            defaultValue = "stylish"
    )
    public String format;


    public Integer call() {
        try {
            var diff = Differ.generate(firstFile, secondFile, format);

            System.out.println(diff);

        } catch (IOException e) {
            System.err.println("Failed to read files " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Application crashed: " + e.getMessage());
            System.exit(2);
        }

        return 0;
    }

    public static void main(String[] args) {
        var exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
