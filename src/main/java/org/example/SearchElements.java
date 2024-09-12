package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class SearchElements {
    public static void searchElements(String fileInputName, List<String> listOfInteger, List<String> listOfFloat, List<String> listOfString) throws IOException {
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
    }
        private static boolean isValidInteger(String s){
          try {
              new BigInteger(new BigDecimal(s).toPlainString());
              return true;
          }catch (NumberFormatException e){
              return false;
          }
        }
        private static boolean isValidFloat(String s){
            try {
                new BigDecimal(s);
                return true;
            }catch (NumberFormatException e){
                return false;
            }
        }
}
