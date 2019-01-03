import java.util.*;
public class Main {
	static long[] tree;
	static int N;
	static long M; 
	static void quickSort(int left, int right) {
		int r = right;
		int l = left;
		int pivot = (r + l) / 2;
		while(l <= r) {
			while(tree[r] > tree[pivot]) r--;
			while(tree[l] < tree[pivot]) l++;
			if(l <= r) {
				long tmp = tree[l];
				tree[l] = tree[r];
				tree[r] = tmp;
				l++; r--;
			}
		}
		if(l < right) quickSort(left, r);
		if(r > left) quickSort(l, right);
	}
	
	static long binarySearch(long high) {
		long low = 0;
		long now = 0;
		long result = -1;
		while(low <= high) {
			now = (high + low) / 2;
			long cut = 0;
			for(int i = 0 ;i < N ;i++) 
				cut += tree[i] >= now ? (tree[i] - now) : 0;
			if(cut >= M) {
				low = now + 1;
			}else
				high = now - 1;
		}		
		return high; 
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		tree = new long[N];
		long high = 0;
		for(int i = 0; i < N;i++) {
			tree[i] = sc.nextInt();
			high = tree[i] > high ? tree[i] : high;
		}
		
		quickSort(0, N - 1);
		System.out.println(binarySearch(high));
	}
}
