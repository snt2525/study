import java.util.*;
class Pair{
	int x, y;
	Pair(int x,int y){
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int[][] dir = {{0,-1},{-1,0},{0,1}};
	static int[][] map;
	static int N, M, D;
	static int max = 0;
	
	static void gameMain(int a, int b, int c) {
		int kill = 0;
		Pair[] killCheck = new Pair[N * M];
		for(int i = N - 1;i >= 0;i--) {
			Pair[] tmp = new Pair[3];
			tmp[0] = BFS(i, a);
			tmp[1] = BFS(i, b);
			tmp[2] = BFS(i, c);
			
			//쏜 적은 0으로 만든다 -> kill 높힌다.
			for(int j = 0;j < 3;j++) {
				if(tmp[j].x == -1) continue;
				if(map[tmp[j].x][tmp[j].y] == 1) {
					killCheck[kill] = new Pair(tmp[j].x, tmp[j].y);
					map[tmp[j].x][tmp[j].y] = 0;
					kill++;
				}
			}
		}
		
		//map을 다시 복구
		returnMap(killCheck, kill);		
		max = Math.max(kill, max);
	}
	//가장 먼저 찾은 애를 return 해준다
	static Pair BFS(int sx, int sy) {
		if(map[sx][sy] == 1)
			return new Pair(sx, sy);
		Queue<Pair> q = new LinkedList<Pair>();
		boolean[][] visit = new boolean[N][M];
		
		q.add(new Pair(sx, sy));
		visit[sx][sy] = true;
		int cnt = 1;
		while(!q.isEmpty()) {
			int size = q.size();
			if(cnt == D) break;
			for(int i = 0;i < size;i++) {
				Pair tmp = q.remove();
				for(int j = 0;j < 3;j++) {
					int x = tmp.x + dir[j][0];
					int y = tmp.y + dir[j][1];
					if(x < 0||y < 0||x >= N||y >= M)continue;
					if(!visit[x][y] && map[x][y] == 1) {
						return new Pair(x, y);
					}else {
						visit[x][y] = true;
						q.add(new Pair(x, y));
					}
				}
			}
			cnt++; 
		}			
		return new Pair(-1, -1);
	}
	
	static void returnMap(Pair[] killCheck , int kill) {
		for(int i = 0;i < kill;i++) {
			map[killCheck[i].x][killCheck[i].y] = 1;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		
		map = new int[N][M];
		for(int i = 0;i < N;i++)
			for(int j = 0;j < M;j++)
				map[i][j] = sc.nextInt();
		
		//궁수의 위치 조합 
		for(int i = 0;i < M - 2;i++) {
			 for(int j = i + 1;j < M - 1;j++) {
				 for(int k = j + 1;k < M;k++) {
					gameMain(i, j, k);
				 }
			 }
		 }		 
		 System.out.println(max);
	}
}
