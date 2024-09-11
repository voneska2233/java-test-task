package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> listOfInteger = new ArrayList<>();
        List<String> listOfString = new ArrayList<>();
        List<String> listOfFloat = new ArrayList<>();
        String path = "";
        String prefix = "";
        boolean boolUpdate = false;
        Set<String> setOfCommands = new HashSet<>();
        boolean beforeFiles = true;
        try {
            for (int i = 0; i < args.length; i++) {
                if (beforeFiles){
                    switch (args[i]) {
                        case "-p" -> {
                            if (!setOfCommands.contains(args[i])) {
                                setOfCommands.add(args[i]);
                                if (i + 1 == args.length) {
                                    throw new IllegalArgumentException("Не существует префикс, а также входные файлы");
                                }
                                prefix = args[++i];
                            } else {
                                throw new IllegalArgumentException("Команда -p должна встречаться только один раз");
                            }
                        }
                        case "-o" -> {
                            if (!setOfCommands.contains(args[i])) {
                                setOfCommands.add(args[i]);
                                if (i + 1 == args.length) {
                                    throw new IllegalArgumentException("Не существует путь, а также входные файлы");
                                }
                                path = args[++i] + "/";
                            } else {
                                throw new IllegalArgumentException("Команда -o должна встречаться только один раз");
                            }
                        }
                        case "-a" -> {
                            if (!setOfCommands.contains(args[i])) {
                                setOfCommands.add(args[i]);
                                boolUpdate = true;
                            } else {
                                throw new IllegalArgumentException("Команда -a должна встречаться только один раз");
                            }
                        }
                        case "-s", "-f" -> {
                            if (!setOfCommands.contains("-s") && !setOfCommands.contains("-f")) {
                                setOfCommands.add(args[i]);
                            } else {
                                throw new IllegalArgumentException("Команды (-s, -f) должна встречаться только один раз");
                            }
                        }
                        default -> {
                            if (!args[i].endsWith(".txt")){
                                throw new IllegalArgumentException("Команды " + args[i] + " не существует");
                            } else {
                                beforeFiles = false;
                            }
                        }
                    }
                }
                if (!beforeFiles) {
                    if (args[i].endsWith(".txt")) {
                        SearchElements.searchElements(args[i], listOfInteger, listOfFloat, listOfString);
                    } else {
                        throw new IllegalArgumentException("Файл неправильного формата (правильный формат .txt): " + args[i]);
                    }
                }
            }
            if (beforeFiles){
                throw new IllegalArgumentException("Входных файлов не существует");
            }
            File fileOfIntegers = WriteToOutputFile.writeToOutputFile(listOfInteger, path + prefix + "integers.txt", boolUpdate);
            File fileOfFloats = WriteToOutputFile.writeToOutputFile(listOfFloat, path + prefix + "floats.txt", boolUpdate);
            File fileOfStrings = WriteToOutputFile.writeToOutputFile(listOfString, path + prefix + "strings.txt", boolUpdate);
            if (setOfCommands.contains("-s")) {
                WriteStatistics.shortStatistic(fileOfIntegers);
                WriteStatistics.shortStatistic(fileOfFloats);
                WriteStatistics.shortStatistic(fileOfStrings);
            }
            if (setOfCommands.contains("-f")) {
                WriteStatistics.longStatistic(fileOfIntegers);
                WriteStatistics.longStatistic(fileOfFloats);
                WriteStatistics.longStatistic(fileOfStrings);
            }
        }
        catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}