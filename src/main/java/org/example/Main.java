package org.example;

public class Main {
    public static void main(String[] args) {
        ProcessingArguments processingArguments= new ProcessingArguments(args);
        processingArguments.processArguments();
        ProcessingFiles processingFiles= new ProcessingFiles(processingArguments);
        processingFiles.processingFiles();
        GenerateStatistics generateStatistics = new GenerateStatistics(processingArguments, processingFiles);
        generateStatistics.generateStatistics();
    }
}