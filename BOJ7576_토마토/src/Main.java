import java.util.*;
class Pair{
	int x, y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static Queue<Pair> q = new LinkedList<Pair>();
	static int[][] dir = {{1,0},{0,-1},{-1,0},{0,1}};
	static boolean[][] visit; 
	static int[][] map;
	static int N, M;
	static int tomato;
	static void BFS() {
		int day = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			day++;
			for(int j = 0;j < size;j++) {
				Pair tmp = q.remove();
				for(int i = 0;i < 4;i++) {
					int x = tmp.x + dir[i][0];
					int y = tmp.y + dir[i][1];
					if(x < 0||y < 0||x >= N||y >= M)continue;
					if(!visit[x][y] && map[x][y] == 0) {
						visit[x][y] = true;
						q.add(new Pair(x, y));
						tomato--;
					}
				}
			}
		}
		if(tomato == 0) {
			System.out.println(day - 1);
		}else {
			System.out.println(-1);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i = 0;i < N;i++) {
			for(int j = 0;j < M;j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) {
					q.add(new Pair(i, j));
					visit[i][j] = true;
				}else if(map[i][j] == 0) {
					tomato++;
				}
			}
		}
		BFS();
	}
}
