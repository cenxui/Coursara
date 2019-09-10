package util;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static java.lang.System.out;

public class Util {
    /**
     * open the array.txt file
     *
     * @param fileName
     * @return the array from array.txt. file
     */
    public static int[] getArray(String fileName, int length) {
        File file = new File(fileName);

        BufferedReader bufferedReader = null;
        int[] result = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
            String text = null;
            result = new int[length];
            int index = 0;
            for (;;) {
                text = bufferedReader.readLine();
                if (text == null) {
                    break;
                }
                result[index++] = Integer.valueOf(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i+1) {
                out.print(i+ " is not sorted");
                return false;
            }
        }
        return true;
    }

    /**
     * print array
     * @param array the array we want to print
     */
    public static void printArrays(int[] array) {
        StringBuilder stringBuilder = new StringBuilder("{");
        int i = 0;
        for ( ; i< array.length-1; i++) {
            stringBuilder.append(array[i]).append(" ,");
        }
        stringBuilder.append(array[i]).append("}");
        out.println(stringBuilder);
    }
}
