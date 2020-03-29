import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Quicksort {
	
	public static int[] data;
	
	public static void main(String[] args) {
		//ReadData readData = new ReadData(args[0]);
		//int[] data =  {7219, 1777, 8203, 1302, 5732, 6714, 3898, 6939, 4011, 7916, 7106, 4233, 
				//9408, 6179, 3658, 8407, 4786, 4714, 835, 7557, 1220, 2638};
		//int[] data =  {1, 3, 5, 16, 20, 7, 9, 15, 14, 17, 13, 11 ,12, 2, 4, 6, 18, 8, 10, 19}; 
		data = new int[] {3, 10, 2, 8, 9, 5, 1, 6, 4, 7};
		int left = 0;
		int right = data.length - 1;
		String sortedArray = Quicksort3(data, left, right);
		System.out.println(sortedArray);
	}
	
	public static String Quicksort3(int[] data, int left, int right) {
		// handling trivial cases
		if(data.length == 1) {
			//if only one element then return
			String result = IntStream.of(data).mapToObj(Integer::toString).collect(Collectors.joining(", "));
			return result;
		} else if(data.length == 2) {
			//if only two elements then	compare, swap, and return
			if(data[0] > data[1]) {
				int temp = data[0];
				data[0] = data[1];
				data[1] = temp;
				String result = IntStream.of(data).mapToObj(Integer::toString).collect(Collectors.joining(", "));
				return result;
			} else {
				String result = IntStream.of(data).mapToObj(Integer::toString).collect(Collectors.joining(", "));
				return result;
			}
		}
		
		int n = data.length;
		int temp = (int) Math.floor(n / 3);
		int temp2 = temp + temp;
		
		// order pivots such that pivot_1 < pivot_2
		int pivot_1 = data[temp - 1]; //data[2] for first test array
		int pivot_2 = data[temp2 - 1]; //data[5] for first test array
		
		swap(left, pivot_1);
        int p1 = partition_1(data, left, right);
        System.out.println(left);
        System.out.println(p1);
        //swap(left, p1);
	
		String result = IntStream.of(data).mapToObj(Integer::toString).collect(Collectors.joining(", "));
		
		return result;
		
		
		/*
		
		swap(right, pivot_2);
		int p2 = partition_2(data, left, right);
		swap(right, p2);
		
		Quicksort3(data, left, p1 - 1);
		Quicksort3(data, p1 + 1, p2 - 1);
		Quicksort3(data, p2 + 1, right);
		*/
	}
	
	public static void swap(int left, int pivot) {
		int tempSwap = data[0]; //sets the left value temporarily
		data[0] = data[pivot];
		data[pivot] = tempSwap;
	}
	
	public static int partition_1(int[] data, int left, int right) {
		int pivot = data[left];
		int leftValue = 0;
		int rightValue = 0;
		for(int i = left; i <= right; i++) {
			if(data[i] > pivot) {
				leftValue = data[i];
				break;
			}
		}
		
		for(int j = right; j >= 0; j--) {
			if(data[j] < pivot) {
				rightValue = data[j];
				break;
			}
		}
		
		int temp = leftValue;
		leftValue = rightValue;
		rightValue = temp;
		
		return leftValue;
	}
	
	public static int partition_2(int[] data, int left, int right) {
		return left;
	}
}

class ReadData {
	public String text;
	public ReadData(String filename) {
		File file = new File(filename);
		BufferedReader buffer;
		try {
			buffer = new BufferedReader(new FileReader(file));
			text = buffer.readLine();
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
