package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
class Cli implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private File firstFile;

    @Parameters(index = "0", description = "path to second file")
    private File secondFile;

    @Option(
            names = {"-f", "--format"},
            description = "output format",
            showDefaultValue = CommandLine.Help.Visibility.ALWAYS,
            defaultValue = "stylish"
    )
    private String format = "format";

    @Override
    public Integer call() {
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new Cli()).execute(args);
        System.exit(exitCode);
    }
}
