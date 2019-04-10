import java.util.*;
class Pair{
	int x, y;
	int dir;
	int size;
	Pair(int x, int y, int dir, int size){
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.size = size;
	}
}
public class Solution {
	LinkedList<Pair> atom;	
	Solution(int K){
		this.atom = new LinkedList<Pair>();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int tc = 1;tc <= N;tc++) {
			int K = sc.nextInt();
			Solution s = new Solution(K);
			
			for(int i = 0;i < K;i++) {
				int y = sc.nextInt() + 1000;
				int x = sc.nextInt();
				x = x >= 0 ? 1000 - x : 1000 + Math.abs(x);
				int dir = sc.nextInt();
				int size = sc.nextInt();
				s.atom.add(new Pair(x, y, dir, size));
			}
			
			
			System.out.println("#" + tc + " ");
		}
	}
}
