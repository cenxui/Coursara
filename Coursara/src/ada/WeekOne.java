package ada;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * this class is for Coursara ada lesson week one 
 * compute number of inversions.
 * @author xenxui
 *
 */

public class WeekOne {

	public static void main(String[] args) {
		int[] arr = getArray("/home/cenxui/IdeaProjects/Coursara/Coursara/src/IntegerArray.txt");
		printArrays(arr);
		System.out.println(sort(arr));
		printArrays(arr);
	}
	
	/**
	 * sort array with merge sort method and return to number of inversions
	 * @param arr the random array
	 * @return the number of inversions 
	 */
	
	public static long sort(int[] arr) {
		int len = arr.length;
		int[] result = new int[len];
		return sort(arr, result, 0,len-1);
	}
	
	static long sort(int[] arr, int[] result, int start, int end) {
		long count = 0;
		
		if (start >= end) return count;
		
		int len = end - start, mid = (len >> 1) + start;
		int leftStart = start, leftEnd = mid;
		int rightStart = mid + 1, rightEnd = end;
		count = sort(arr, result, leftStart, leftEnd) + sort(arr, result, rightStart, rightEnd);
		
		int index = start;
		int leftIndex = leftStart, rightIndex = rightStart;
		while (leftIndex <= leftEnd && rightIndex <= rightEnd) {
			if (arr[leftIndex] > arr[rightIndex]) {
				result[index++] = arr[rightIndex++];
				count = count + rightIndex - index;
			}else {
				result[index++] = arr[leftIndex++];	
			}			
		}
		while (leftIndex <= leftEnd) {
			result[index++] = arr[leftIndex++];
		}
		while (rightIndex <= rightEnd) {
			result[index++] =  arr[rightIndex++];
		}
		System.arraycopy(result, start, arr, start,len+1);

		return count;
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
	
	/**
	 * open the array.txt file 
	 * @param fileName 
	 * @return the array from array.txt. file
	 */
	
	public static int[] getArray(String fileName) {
		File file = new File(fileName);

		BufferedReader bufferedReader = null;
		int[] result = null;
		try {
			FileInputStream  fileInputStream = new FileInputStream(file);
			bufferedReader =
					new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));	
			String text = null;
			result = new int[100000];
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
}
