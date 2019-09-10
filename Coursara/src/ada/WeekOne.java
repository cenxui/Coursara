package ada;

import static java.lang.System.out;
import static util.Util.*;

/**
 * this class is for Coursara ada lesson week one 
 * compute number of inversions.
 * @author xenxui
 *
 */

public class WeekOne {

	public static void main(String[] args) {
		int[] arr = getArray("/home/cenxui/IdeaProjects/Coursara/Coursara/src/IntegerArray.txt",100000);
		printArrays(arr);
		System.out.println(sort(arr));
		printArrays(arr);
	}
	
	/**
	 * sort array with merge sort method and return to number of inversions
	 * @param arr the random array
	 * @return the number of inversions 
	 */
	
	private static long sort(int[] arr) {
		int len = arr.length;
		int[] result = new int[len];
		return sort(arr, result, 0,len-1);
	}
	
	private static long sort(int[] arr, int[] result, int start, int end) {
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
}
