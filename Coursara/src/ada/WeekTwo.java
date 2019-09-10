package ada;

import util.Util;

import static java.lang.System.out;
import static util.Util.*;

public class WeekTwo {
	
	enum Pivot {
		First,
		Final,
		MedianOfThree
	}

	public static void main(String[] args) {

		String path = "/home/cenxui/IdeaProjects/Coursara/Coursara/src/QuickSort.txt";
		
		int[] arr = getArray(path, 10000);
		System.out.println(Pivot.First + " : " + quickSortV2(arr, Pivot.First));
		out.println("is sorted : " + isSorted(arr));
		arr = getArray(path, 10000);
		System.out.println(Pivot.Final + " : " + quickSortV2(arr, Pivot.Final));
		out.println("is sorted : " + isSorted(arr));
		arr = getArray(path, 10000);
		System.out.println(Pivot.MedianOfThree + " : " + quickSortV2(arr, Pivot.MedianOfThree));
		out.println("is sorted : " + isSorted(arr));
	}
	
	private static int quickSortV2(int[] arr, Pivot piv) {
		return quickSortV2(arr, 0, arr.length - 1, piv);
	}
	
	private static int quickSortV2(int[] arr, int from, int end, Pivot piv ) {
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
	
	private static void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
}
