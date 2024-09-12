package org.example;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class WriteStatistics {
    public static void shortStatistic(File fileName) throws IOException {
       try{
           int count = 0;
           try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
               while (br.readLine() != null) {
                   count++;
               }
           }
           System.out.println(" ");
           System.out.println("Количество элементов в " + fileName.getName() + ": " + count);
       } catch (NullPointerException _) {}
    }
    public static void longStatistic(File file) throws IOException {
        shortStatistic(file);
        try {
            String fileName = file.getName();
            if (fileName.endsWith("strings.txt")){
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    int max = Integer.MIN_VALUE;
                    int min = Integer.MAX_VALUE;
                    while ((line = br.readLine()) != null) {
                        if (line.length() > max) {
                            max = line.length();
                        }
                        if (line.length() < min) {
                            min = line.length();
                        }
                    }
                    System.out.println("Размер самой короткой строки в " + fileName + ": "+ min);
                    System.out.println("Размер самой длинной строки в " + fileName + ": " + max);
                }
            }
            if (fileName.endsWith("integers.txt") || fileName.endsWith("floats.txt")){
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line = br.readLine();
                    BigDecimal max = new BigDecimal(line);
                    BigDecimal min = new BigDecimal(line);
                    BigDecimal sum = BigDecimal.ZERO;
                    BigDecimal average;
                    BigDecimal count = BigDecimal.ZERO;
                    while (line != null) {
                        BigDecimal tmp = new BigDecimal(line);
                        if (tmp.compareTo(max) > 0) {
                            max = tmp;
                        }
                        if (tmp.compareTo(min) < 0) {
                            min = tmp;
                        }
                        sum = sum.add(tmp);
                        count = count.add(BigDecimal.valueOf(1));
                        line = br.readLine();
                    }
                    average = sum.divide(count, RoundingMode.HALF_UP);
                    System.out.println("Минимальное значение в " + fileName + ": " + min);
                    System.out.println("Максимальное значение в " + fileName + ": " + max);
                    System.out.println("Сумма элементов в " + fileName + ": " + formatOutputNumbers(sum));
                    System.out.println("Среднее значение в " + fileName + ": " + formatOutputNumbers(average));
                }
            }
        } catch (NullPointerException _) {}
    }
    private static String formatOutputNumbers (BigDecimal number) {
        if (number.abs().compareTo(new BigDecimal("1E10")) >= 0 || number.abs().compareTo(new BigDecimal("1E-10")) < 0) {
            DecimalFormat df = new DecimalFormat("#.#####E0");
            return df.format(number);
        }
        return number.setScale(5, RoundingMode.HALF_UP).toPlainString();
    }
}
