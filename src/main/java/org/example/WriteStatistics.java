package org.example;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class WriteStatistics {
    public static void shortStatistic(File file) {
       try{
           int count = 0;
           try (BufferedReader br = new BufferedReader(new FileReader(file))) {
               while (br.readLine() != null) {
                   count++;
               }
           }
           System.out.println(" ");
           System.out.println("Количество элементов в " + file.getName() + ": " + count);
       } catch (IOException _) {}
    }

    public static void longStatistic(File file)  {
        shortStatistic(file);
        String fileName = file.getName();
        if (fileName.endsWith("strings.txt")){
            printStatisticsOfString(file);
        } else {
            printStatisticsOfNumber(file);
        }
    }

    private static void printStatisticsOfString(File file) {
        try {
            String line;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while ((line = br.readLine()) != null) {
                    if (line.length() > max) {
                        max = line.length();
                    }
                    if (line.length() < min) {
                        min = line.length();
                    }
                }
            }
            System.out.println("Размер самой короткой строки в " + file.getName() + ": "+ min);
            System.out.println("Размер самой длинной строки в " + file.getName() + ": " + max);
        } catch (IOException _) {}
    }

    private static void printStatisticsOfNumber(File file) {
        try {
            String line;
            BigDecimal max;
            BigDecimal min;
            BigDecimal sum = BigDecimal.ZERO;
            BigDecimal count = BigDecimal.ZERO;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                line = br.readLine();
                max = new BigDecimal(line);
                min = new BigDecimal(line);
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
            }
            BigDecimal average = sum.divide(count, RoundingMode.HALF_UP);
            System.out.println("Минимальное значение в " + file.getName() + ": " + min);
            System.out.println("Максимальное значение в " + file.getName() + ": " + max);
            System.out.println("Сумма элементов в " + file.getName() + ": " + formatOutputNumbers(sum));
            System.out.println("Среднее значение в " + file.getName() + ": " + formatOutputNumbers(average));
        } catch (IOException _){}
    }

    private static String formatOutputNumbers (BigDecimal number) {
        if (number.abs().compareTo(new BigDecimal("1E10")) >= 0 || number.abs().compareTo(new BigDecimal("1E-10")) < 0) {
            DecimalFormat df = new DecimalFormat("#.#####E0");
            return df.format(number);
        }
        return number.setScale(5, RoundingMode.HALF_UP).toPlainString();
    }
}
