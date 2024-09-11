package org.example;

import java.io.*;
import java.util.List;

public class WriteToOutputFile {
    public static File writeToOutputFile(List<String> listOfElements, String fileName, boolean boolUpdate) throws IOException {
        if (!listOfElements.isEmpty()) {
            try {
                File file = new File(fileName);
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, boolUpdate))) {
                    for (String content : listOfElements) {
                        bw.write(content);
                        bw.newLine();
                    }
                }
                return file;
            } catch (FileNotFoundException e) {
                System.err.println("Неправильно указан путь для записи файлов");
                System.exit(1);
            }
        }
        return null;
    }
}
