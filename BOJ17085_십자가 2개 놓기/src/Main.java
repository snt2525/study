import java.util.*;
class Pair{
	int x, y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}
class Pair2{
	int x, y;
	int dir;
	Pair2(int x, int y, int dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}
public class Main {
	static boolean[][] visit;
	static int[] sizeCross = {1, 5, 9, 13, 17, 21, 25, 29};
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	static LinkedList<Pair> q = new LinkedList<Pair>();
	static int N, M;
	static char[][] map;
	static int BFS(int sx, int sy , boolean check) {
		ArrayList<Pair2> tmpQ = new ArrayList<Pair2>();
		tmpQ.add(new Pair2(sx, sy, 0));
		tmpQ.add(new Pair2(sx, sy, 1));
		tmpQ.add(new Pair2(sx, sy, 2));
		tmpQ.add(new Pair2(sx, sy, 3));

		int cnt = 0;
		while(!q.isEmpty()) {
			int size = tmpQ.size();
			boolean flag = false;
			for(int i = 0;i < size;i++) {
				Pair2 tmp = tmpQ.get(i);
				if(check) visit[tmp.x][tmp.y] = true; 
				int x = tmp.x + dir[tmp.dir][0];
				int y = tmp.y + dir[tmp.dir][1];
				if(x < 0||y < 0||x >= N||y >= M||map[x][y] != '#'||visit[x][y]) {
					flag = true;
					break;
				}	
				tmpQ.add(new Pair2(x, y, tmp.dir));
			}
			for(int i = 0;i < 4;i++) {
				if(tmpQ.isEmpty()) break;
				Pair2 tmp = tmpQ.remove();
				if(check) 
					visit[tmp.x][tmp.y] = true;
			}
			if(flag)
				break;			
			cnt++;
		}	
		
		return sizeCross[cnt];
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];

		for(int i = 0;i < N;i++) {
			char[] tmp = sc.next().toCharArray();
			for(int j = 0;j < M;j++) {
				map[i][j] = tmp[j];
				if(tmp[j] == '#') {
					q.add(new Pair(i, j));
				}
			}
		}
		
		if(q.isEmpty()) {
			System.out.println(0);
			return;
		}
		
		int qSize = q.size();
		int result = 0;
		for(int i = 0;i < qSize - 1;i++) {
			visit = new boolean[N][M];
			Pair tmp = q.get(i);
			int a = BFS(tmp.x, tmp.y, true);
			int max = 0;
			for(int j = i + 1;j < qSize;j++) {
				Pair tmp2 = q.get(j);
				if(visit[tmp2.x][tmp2.y]) continue;
				max = Math.max(max, BFS(tmp2.x, tmp2.y, false));			
			}
			result = Math.max(result, a * max);
		}	
		System.out.println(result);
	}
}
