package ada;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class WeekTwo {
	
	enum Pivot {
		First,
		Final,
		MedianOfThree
	}

	public static void main(String[] args) {
		
		int[] arr = getArray("c:\\text\\quicksort.txt", 10000);
		System.out.println(Pivot.First + " : " + quickSortV2(arr, Pivot.First));
		out.println("is sorted : " + isSorted(arr));
		arr = getArray("c:\\text\\quicksort.txt", 10000);
		System.out.println(Pivot.Final + " : " + quickSortV2(arr, Pivot.Final));
		out.println("is sorted : " + isSorted(arr));
		arr = getArray("c:\\text\\quicksort.txt", 10000);
		System.out.println(Pivot.MedianOfThree + " : " + quickSortV2(arr, Pivot.MedianOfThree));
		out.println("is sorted : " + isSorted(arr));
	}
	
	public static int quickSortV2(int[] arr, Pivot piv) {
		return quickSortV2(arr, 0, arr.length - 1, piv);
	}
	
	static int quickSortV2(int[] arr, int from, int end, Pivot piv ) {
		if (from >= end) {
			return 0 ;
		}
		
		if (end - from != 1) {
			switch (piv) {
			case First:
				break;
			case Final:
				swap(arr, from, end);;
				break;
			case MedianOfThree:
				if (arr[from] < arr[(from + end)/2] ) {
					if (arr[(from + end)/2] < arr[end]) {
						swap(arr, from, (from + end)/2);
					}else {
						if (arr[from] < arr[end]) {
							swap(arr, from, end);
						}
					}
				}else {
					if (arr[(from + end)/2] > arr[end]) {
						swap(arr, from, (from + end)/2);
					}else {
						if (arr[from] > arr[end]) {
							swap(arr, from, end);
						}
					}
				}					
				break;
			}
		}
	
		int count = 0;
		int left = from + 1;
	
		for (int i = from + 1 ; i <= end; i++) {
			if (arr[i] < arr[from]) {
				swap(arr, left, i);
				left++;
			}
			count++;
		}
		left--;
		swap(arr, from, left);

		count = count + quickSortV2(arr, from, left-1, piv) + quickSortV2(arr, left+1, end, piv);
		return count;
	}
	
	
	@Deprecated
	public static int quickSort(int[] arr, Pivot piv) {
		return quickSort(arr, 0, arr.length - 1, piv);
	}
	@Deprecated
	static int quickSort(int[] arr, int from, int end, Pivot piv ) {
		if (from >= end) {
			return 0 ;
		}
		
		if (end - from != 1) {
			switch (piv) {
			case First:
				break;
			case Final:
				swap(arr, from, end);;
				break;
			case MedianOfThree:
				if (arr[from] < arr[(from + end)/2] ) {
					if (arr[(from + end)/2] < arr[end]) {
						swap(arr, from, (from + end)/2);
					}else {
						if (arr[from] < arr[end]) {
							swap(arr, from, end);
						}
					}
				}else {
					if (arr[(from + end)/2] > arr[end]) {
						swap(arr, from, (from + end)/2);
					}else {
						if (arr[from] > arr[end]) {
							swap(arr, from, end);
						}
					}
				}					
				break;
			}
		}
	
		int count = 0;
		int left = from + 1;
		int right = end;
		
		while (left < right) {
			while (left < right && arr[left] < arr[from]) {
				left++;
				count++;
			}
			
			while (left < right && arr[right] > arr[from]) {
				right--;
				count++;
			}
			
			if (left < right) {
				swap(arr, left, right);
				count++;
				left++;
				right--;
			}					
		}

		if (arr[from] < arr [left]) {
			left--;
		}
		count++;
		swap(arr, from, left);

		count = count + quickSort(arr, from, left-1, piv) + quickSort(arr, left+1, end, piv);
		return count;
	}
	
	public static void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}

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

	/**
	 * print array
	 * 
	 * @param array
	 *            the array we want to print
	 */
	public static void printArrays(int[] array) {
		StringBuilder stringBuilder = new StringBuilder("{");
		int i = 0;
		for (; i < array.length - 1; i++) {
			stringBuilder.append(array[i]).append(" ,");
		}
		stringBuilder.append(array[i]).append("}");
		out.println(stringBuilder);
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
}
