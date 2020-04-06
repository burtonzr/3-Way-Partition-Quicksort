  
import java.io.File;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QSort3 {
	
	public static int[] data; 
	
	public static void main(String[] args) {
		data = readFiles(args[0]);
		String sortedArray = Quicksort3(data, 0,  data.length - 1);
		System.out.println(sortedArray);
	}
	
	public static int[] readFiles(String file) {
		try {
			File f          = new File(file);
			Scanner scanner = new Scanner(f);
			int counter     = 0;
			while(scanner.hasNextInt()) {
				counter++;
				scanner.nextInt();
			}
			int arr[] = new int[counter];
			scanner.close();
			Scanner s1 = new Scanner(f);
			
			for(int i = 0; i < arr.length; i++) {
				arr[i] = s1.nextInt();
			}
			
			s1.close();
			
			return arr;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String Quicksort3(int[] data, int left, int right) {
	
		// handling trivial cases
		if(left == right) {
			//if only one element then return
			String result = IntStream.of(data).mapToObj(Integer::toString).collect(Collectors.joining(", "));
			return result;
		} 
		
		if((left + 1) == right) {
			//if only two elements then	compare, swap, and return
			if(data[left] > data[right]) {
				int temp = data[left];
				data[left] = data[right];
				data[right] = temp;
				String result = IntStream.of(data).mapToObj(Integer::toString).collect(Collectors.joining(", "));
				return result;
			} else {
				String result = IntStream.of(data).mapToObj(Integer::toString).collect(Collectors.joining(", "));
				return result;
			}
		}
		
		int pivot_1 = (int) (Math.floor((right - left) / 3) + left) - 1;
		int pivot_2 = (int) ((Math.floor((right - left) / 3) + left) * 2) - 1;
		
		if(pivot_2 > right) {
			pivot_2 = right;
		}
		
		swap(left, pivot_1);
		int p1 = partition_1(data, left, right, pivot_1);
        swap(left, p1);
        
        swap(right, pivot_2);
        int p2 = partition_2(data, left, right, pivot_2);
        swap(right, p2);
        
        Quicksort3(data, left, p1 - 1);
        Quicksort3(data, p1 + 1, p2 - 1);
        Quicksort3(data, p2 + 1, right);
        
        String result = IntStream.of(data).mapToObj(Integer::toString).collect(Collectors.joining(", "));
		
		return result;
	}
	
	public static void swap(int position, int pivot) {
		try {
			int tempSwap = data[position]; //sets the position value temporarily
			data[position] = data[pivot];
			data[pivot] = tempSwap;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int partition_1(int[] data, int left, int right, int pivot) {
		pivot = left;
		while(left <= right) {
			while(!(data[left] > data[pivot])) {
				left++;
			}
			while(data[right] > data[pivot]) {
				right--;
			}
			
			if(left <= right) {
				swap(left, right);
				left++;
				right--;
			}
		}
		
		
		return right;
	}
	
	public static int partition_2(int[] data, int left, int right, int pivot) {
		pivot = right;
		while(left <= right) {
			while(data[left] < data[pivot]) {
				left++;
			}
			
			while(!(data[right] < data[pivot])) {
				right--;
			}
			
			if(left <= right) {
				swap(left, right);
				left++;
				right--;
			}
		}
		
		return left;
	}
}
