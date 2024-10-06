package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "Differ 1.0",
        description = "Compares two configuration files and shows a difference.")
class Cli implements Callable<Integer> {

    @Override
    public Integer call() {
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new Cli()).execute(args);
        System.exit(exitCode);
    }
}
