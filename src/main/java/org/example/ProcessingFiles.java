package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProcessingFiles {
    private final List<String> listOfInteger = new ArrayList<>();
    private final List<String> listOfString = new ArrayList<>();
    private final List<String> listOfFloat = new ArrayList<>();
    private final List<File> listOfFile = new ArrayList<>();
    private final ProcessingArguments arguments;

    public ProcessingFiles(ProcessingArguments arguments) {
        this.arguments = arguments;
    }

    public void processingFiles() {
        try {
            if (arguments.getListOfFileName().isEmpty()) {
                throw new IllegalArgumentException("Входных файлов не существует");
            }
            for (String fileName: arguments.getListOfFileName()) {
                if (fileName.endsWith(".txt")) {
                    SearchElements.searchElements(fileName, listOfInteger, listOfFloat, listOfString);
                } else {
                    throw new IllegalArgumentException("Файл неправильного формата (правильный формат .txt): " + fileName);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        File fileOfInteger = WriteToOutputFile.writeToOutputFile(listOfInteger, arguments.getPath() + arguments.getPrefix() + "integers.txt", arguments.isBoolUpdate());
        listOfFile.add(fileOfInteger);
        File fileOfFloat = WriteToOutputFile.writeToOutputFile(listOfFloat, arguments.getPath() + arguments.getPrefix() + "floats.txt", arguments.isBoolUpdate());
        listOfFile.add(fileOfFloat);
        File fileOfString = WriteToOutputFile.writeToOutputFile(listOfString, arguments.getPath() + arguments.getPrefix() + "strings.txt", arguments.isBoolUpdate());
        listOfFile.add(fileOfString);
    }

    public List<File> getListOfFile() {
        return listOfFile;
    }
}
