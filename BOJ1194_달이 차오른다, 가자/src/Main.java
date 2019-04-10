import java.util.*;
class Pair{
	int x, y;
	int key;
	int time;
	Pair(int x, int y, int key, int time){
		this.x = x;
		this.y = y;
		this.key = key;
		this.time = time;
	}
}
public class Main {
	static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	static Queue<Pair> q = new LinkedList<Pair>();
	static boolean[][][] visit;
	static char[][] map;
	static int N, M;
	static int result = 0;
	static void BFS(int a, int b) {
		visit = new boolean[N][M][130];
		
		q.add(new Pair(a, b, 0 ,0));
		visit[a][b][0] = true;
		while(!q.isEmpty()) {
			Pair tmp = q.remove();
			for(int i = 0;i < 4;i++) {
				int x = tmp.x + dir[i][0];
				int y = tmp.y + dir[i][1];
				if(x < 0||y < 0||x >= N||y >= M||map[x][y] == '#'||visit[x][y][tmp.key]) continue;
				if(map[x][y] == '1') {
					result = tmp.time + 1;
					return;
				}else if(map[x][y] == '.') { 
					visit[x][y][tmp.key] = true;
					q.add(new Pair(x, y, tmp.key, tmp.time + 1));
				}else if(map[x][y] >= 'a' && map[x][y] <= 'f') {  //열쇠일 때,
					int tmpKey = (1 << (map[x][y] - 'a') + 1) | tmp.key;
					visit[x][y][tmpKey] = true;
					q.add(new Pair(x, y, tmpKey, tmp.time + 1));
				}else if(map[x][y] >= 'A' && map[x][y] <= 'F') {  //문일 때,
					int tmpDoor = (1 << (map[x][y] - 'A') + 1);
					if((tmp.key & tmpDoor) == 0) continue;					
					visit[x][y][tmp.key] = true;
					q.add(new Pair(x, y, tmp.key, tmp.time + 1));		
				}								
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		int x = 0, y = 0;
		for(int i = 0;i < N;i++) {
			char[] tmp = sc.next().toCharArray();
			for(int j = 0;j < M;j++) {
				map[i][j] = tmp[j];
				if(map[i][j] == '0') {
					x = i; y = j;
					map[i][j] = '.';
				}
			}
		}
		BFS(x, y);
		if(result == 0)
			System.out.println(-1);
		else
			System.out.println(result);
	}	
}
