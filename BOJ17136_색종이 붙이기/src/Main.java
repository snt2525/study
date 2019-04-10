import java.util.*;
class Pair{
	int x, y;
	Pair(int x,int y){
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static LinkedList<Pair> q = new LinkedList<Pair>();
	static boolean[][]map = new boolean[10][10];
	static boolean[][] visit = new boolean[10][10];
	static int[] paper = new int[6]; 
	static int min = Integer.MAX_VALUE;
	static void dfs(int cnt, int remain, int paint) {
		if(min < paint)
			return;
		if(remain == 0) {
			min = Math.min(min, paint);
			return;
		}
		
		if(q.size() <= cnt) return;
		
		Pair tmp = q.get(cnt);
		if(!visit[tmp.x][tmp.y]) {
			int size = findMax(tmp.x, tmp.y);
			for(int j = size;j >= 1;j--) {
				if(paper[j] + 1 <= 5) {
					visitMap(tmp.x, tmp.y, j, true); //방문
					paper[j]++;			
					dfs(cnt + 1,remain - (j * j), paint + 1);
					visitMap(tmp.x, tmp.y, j, false);
					paper[j]--;
				}
			}
		}
		else 
			dfs(cnt + 1,remain, paint);						
	}
	static int findMax(int x, int y) {
		for(int i = 1;i <= 5;i++) {
			boolean flag = true;
			for(int j = x;j < x + i;j++) {
				for(int k = y;k < y + i;k++) { 
					if(j >= 10||k >= 10||!map[j][k]||visit[j][k])
						flag = false;
				}
				if(!flag)
					return i - 1;
			}
		}	
		return 5;
	}
	
	static void visitMap(int x, int y, int size, boolean flag) {
		for(int i = x;i < x + size;i++) 
			for(int j = y;j < y + size;j++) 
				visit[i][j] = flag;				
	}
	
	static void removeVisitMap(int x, int y,int size, int maxSize) {
			
		for(int i = x;i < x + maxSize;i++) {
			for(int j = y;j < y + maxSize;j++) {
				if(i >= x + size || j >= y + size)
					visit[i][j] = false;	
			}
		}		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i = 0;i < 10;i++) {
			for(int j = 0;j < 10;j++) {
				map[i][j] = sc.nextInt() == 1 ? true : false;
				if(map[i][j])
					q.add(new Pair(i, j));
			}
		}
		dfs(0, q.size(), 0);
		if(min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}
}
