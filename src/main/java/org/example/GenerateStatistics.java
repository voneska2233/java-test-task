package org.example;

import java.io.File;

public class GenerateStatistics {
    private final ProcessingArguments arguments;
    private final ProcessingFiles files;

    public GenerateStatistics(ProcessingArguments arguments, ProcessingFiles files) {
        this.arguments = arguments;
        this.files = files;
    }

    public void generateStatistics() {
        if (arguments.getSetOfCommands().contains("-s")) {
            for (File file : files.getListOfFile()) {
                WriteStatistics.shortStatistic(file);
            }
        }
        if (arguments.getSetOfCommands().contains("-f")) {
            for (File file : files.getListOfFile()) {
                WriteStatistics.longStatistic(file);
            }
        }
    }
}
