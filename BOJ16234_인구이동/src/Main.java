import java.util.*;
class Pair{
	int x, y;
	Pair(int x,int y){
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int[][]dir = {{1,0},{0,1},{-1,0},{0,-1}};
	static int[][] map;
	static boolean[][] visit;
	static int N;
	static int A,B;
	static boolean bfs(int a, int b) {
		Queue<Pair> q = new LinkedList<Pair>();
		Queue<Pair> open = new LinkedList<Pair>();
		boolean[][] log = new boolean[N][N];
		log[a][b] = true;
		int sum = 0;
		q.add(new Pair(a, b));
		
		while(!q.isEmpty()) {
			Pair tmp = q.remove();
			open.add(tmp);
			sum += map[tmp.x][tmp.y];
			for(int i = 0;i < 4;i++) {
				int x = tmp.x + dir[i][0];
				int y = tmp.y + dir[i][1];
				if(x < 0||y < 0||x >= N||y >= N||visit[x][y]||log[x][y]) continue;
				if(Math.abs(map[x][y] - map[tmp.x][tmp.y]) >= A && Math.abs(map[x][y] - map[tmp.x][tmp.y]) <= B) {
					log[x][y] = true;
					q.add(new Pair(x, y));
				}
			}
		}
		
		if(sum == map[a][b])
			return false;
		
		int size = open.size();
		int num = sum / size;
		for(int i = 0;i < size;i++) {
			Pair tmp = open.remove();
			map[tmp.x][tmp.y] = num;
			visit[tmp.x][tmp.y] = true;
		}		
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = sc.nextInt();
		B = sc.nextInt();
		map = new int[N][N];
		for(int i = 0;i < N;i++)
			for(int j = 0;j < N;j++)
				map[i][j] = sc.nextInt();
		
		int cnt = 0;
		while(true) {
			boolean flag = false;
			visit = new boolean[N][N];
			for(int i = 0;i < N;i++) {
				for(int j = 0;j < N;j++) {
					if(!visit[i][j] && bfs(i,j)) {
							flag = true;
					}
				}
			}
			if(!flag) break;
			cnt++;
		}
		System.out.print(cnt);
	}
}
