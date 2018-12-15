import java.util.*;
public class Main {
	static int[] a;
	static int[] b;
	static int[] result;
	static int aCnt;
	static int bCnt;
	
	static int binarySearch(int num) {
		int left = 0;
		int right = aCnt - 1;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			if(num < a[mid]) {
				right = mid - 1;
			}else if(num > a[mid]) {
				left = mid + 1;
			}else if(num == a[mid]) {
				//찾았을 때
				return 1;
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		aCnt = sc.nextInt();
		a = new int[aCnt];
		for(int i = 0 ;i < aCnt;i++) 
			a[i] = sc.nextInt();
		
		bCnt = sc.nextInt();
		b = new int[bCnt];
		result = new int[bCnt];
		
		for(int j = 0;j < bCnt;j++) 
			b[j] = sc.nextInt();
		
		Arrays.sort(a);
		
		for(int i = 0;i < bCnt; i++)
			result[i] = binarySearch(b[i]);
		
		for(int i = 0;i < bCnt; i++)
			System.out.print(result[i] + " ");
	}
}

