  
import java.io.File;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QSort3 {
	
	public static int[] data; 
	public static int pivot_1;
	public static int pivot_2;
	
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
		if(right - left == 0 || left > right) {
			String result = IntStream.of(data).mapToObj(Integer::toString).collect(Collectors.joining(", "));
			return result;
		}
		
		if(right - left == 1) {
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
		
		pivot_1 = (int) (Math.floor((right - left) / 3) + left);
		pivot_2 = (int) (Math.floor((right - left) * 2/ 3) + left);
		
		if(pivot_1 > pivot_2) {
			swap(pivot_1, pivot_2);
		}
		
		swap(left, pivot_1);
		int p1 = partition_1(data, left, right);
        swap(left, p1);
       
        swap(right, pivot_2);
        int p2 = partition_2(data, left, right);
        swap(right, p2);        
		
        
        Quicksort3(data, left, p1 - 1);
        Quicksort3(data, p1 + 1, p2 - 1);
        Quicksort3(data, p2 + 1, right);
        
        String result = IntStream.of(data).mapToObj(Integer::toString).collect(Collectors.joining(", "));
		
		return result;
	}
	
	public static void swap(int position, int pivot) {
		int tempSwap = data[position]; //sets the position value temporarily
		data[position] = data[pivot];
		data[pivot] = tempSwap;
	}
	
	public static int partition_1(int[] data, int left, int right) {
		int pivot = left;
		while(left <= right) {
			while(data[left] < data[pivot]) {
				left++;
			}
			
			while((right != 0) && data[right] > data[pivot]) {
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
	
	public static int partition_2(int[] data, int left, int right) {
		int pivot = right;
		while(left <= right) {
			while(data[left] < data[pivot]) {
				left++;
			}
			
			while((right != 0) && !(data[right] < data[pivot])) {
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
