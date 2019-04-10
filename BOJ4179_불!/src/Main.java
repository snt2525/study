import java.util.*;
class Pair{
	int x, y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	static String[][] map;
	static int N, M;
	static int sX = 0, sY = 0;
	static Queue<Pair> qF = new LinkedList<Pair>();
	static int result = 0;
	static void bfs() {
		Queue<Pair> q = new LinkedList<Pair>();
		Queue<Pair> qF = new LinkedList<Pair>();
		boolean [][] log = new boolean[N][M];
		log[sX][sY] = true;
		q.add(new Pair(sX, sY));
		int time = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			time++;
			fireBFS();
			for(int i = 0;i < size;i++) {
				Pair tmp = q.remove();
				for(int j = 0;j < 4;j++) {
					int x = tmp.x + dir[j][0];
					int y = tmp.y + dir[j][1];
					if(x < 0||y < 0||x >= N||y >= M) {
						result = time;
						return;
					}
					if(map[x][y].equals(".") && !log[x][y]) {
						log[x][y] = true;
						q.add(new Pair(x, y));
					}
				}
			}
		}
	}
	static void fireBFS() {
		int size = qF.size();
		for(int i = 0;i < size;i++) {
			Pair tmp = qF.remove();
			for(int j = 0;j < 4;j++) {
				int x = tmp.x + dir[j][0];
				int y = tmp.y + dir[j][1];
				if(x < 0||y < 0||x >= N||y >= M) continue;
				if(map[x][y].equals(".")) {
					qF.add(new Pair(x, y));
					map[x][y] = "*";
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new String[N][M];
		
		for(int i = 0;i < N;i++) {
			String[] tmp = sc.next().trim().split("");
			for(int j = 0;j < M;j++) {
				map[i][j] = tmp[j];
				if(tmp[j].equals("J")) {
					sX = i; sY = j;
					map[i][j] = ".";
				}else if(tmp[j].equals("F")) {
					qF.add(new Pair(i, j));
					map[i][j] = "*";
				}
			}
		}
		
		bfs();
		if(result == 0)
			System.out.println("IMPOSSIBLE");
		else	
			System.out.println(result);
	}
}
