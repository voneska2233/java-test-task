package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class SearchElements {
    public static void searchElements(String fileInputName, List<String> listOfInteger, List<String> listOfFloat, List<String> listOfString) {
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(fileInputName))){
                String line;
                while ((line = br.readLine()) != null){
                    if (isValidInteger(line)){
                        listOfInteger.add(line);
                        continue;
                    }
                    if (isValidFloat(line)){
                        listOfFloat.add(line);
                        continue;
                    }
                    if (!line.isEmpty()){
                        listOfString.add(line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл " + fileInputName + " не найден");
            System.exit(1);
        }
        catch (IOException e){
            System.out.println("Ошибка при фильтрации файлов");
            System.exit(1);
        }
    }

    private static boolean isValidInteger(String element) {
        try {
            new BigInteger(new BigDecimal(element).toPlainString());
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private static boolean isValidFloat(String element){
        try {
            new BigDecimal(element);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
