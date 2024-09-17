package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProcessingArguments {
    private String prefix = "";
    private String path = "";
    private boolean boolUpdate = false;
    private final Set<String> setOfCommands = new HashSet<>();
    private final String[] args;
    private final List<String> listOfFile = new ArrayList<>();

    public ProcessingArguments(String[] args) {
        this.args = args;
    }

    public void processArguments() {
        try {
            boolean beforeFiles = true;
            for (int i = 0; i < args.length; i++) {
                if (beforeFiles) {
                    switch (args[i]) {
                        case "-p" -> {
                            validateCommand(setOfCommands, args[i]);
                            if (i + 1 == args.length) {
                                throw new IllegalArgumentException("Не существует префикс, а также входные файлы");
                            }
                            prefix = args[++i];
                        }
                        case "-o" -> {
                            validateCommand(setOfCommands, args[i]);
                            setOfCommands.add(args[i]);
                            if (i + 1 == args.length) {
                                throw new IllegalArgumentException("Не существует путь, а также входные файлы");
                            }
                            path = args[++i] + "/";
                        }
                        case "-a" -> {
                            validateCommand(setOfCommands, args[i]);
                            setOfCommands.add(args[i]);
                            boolUpdate = true;
                        }
                        case "-s", "-f" -> {
                            if (!setOfCommands.contains("-s") && !setOfCommands.contains("-f")) {
                                setOfCommands.add(args[i]);
                            } else {
                                throw new IllegalArgumentException("Команды (-s, -f) должна встречаться только один раз");
                            }
                        }
                        default -> {
                            if (!args[i].endsWith(".txt")) {
                                throw new IllegalArgumentException("Команды " + args[i] + " не существует");
                            } else {
                                beforeFiles = false;
                            }
                        }
                    }
                }
                if (!beforeFiles) {
                    listOfFile.add(args[i]);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public String getPath() {
        return path;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isBoolUpdate() {
        return boolUpdate;
    }

    public Set<String> getSetOfCommands() {
        return setOfCommands;
    }

    public List<String> getListOfFileName() {
        return listOfFile;
    }

    private static void validateCommand(Set<String> set, String command) {
        if (set.contains(command)) {
            throw new IllegalArgumentException("Команда " + command + " должна встречаться только один раз");
        }
        set.add(command);
    }
}
