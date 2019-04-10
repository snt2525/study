import java.util.*;
class Pair{
	int x, y;
	int dir;
	Pair(int x, int y, int dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}
class Pair2{
	int x, y;
	Pair2(int x,int y){
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int[][] dir = {{0,-1},{-1,0},{0,1},{1,0}};
	static int[][] cctv = new int[65][2]; //x,y,방향
	static int[][] cctv2Dir = {{0,2},{1,3}};
	static int[][] cctv3Dir = {{0,1},{1,2},{2,3},{3,0}};
	static int[][] cctv4Dir = {{0,1,2},{1,2,3},{2,3,0},{3,0,1}};
	static int[][] map;
	static boolean[][] visit;
	static int N, M;
	static int cctvNum = 0;
	static int cnt = 0;
	static int max = 0;
	static void dfs(int cnt, int sum) { //num은 감시하는 칸의 수
		if(cnt == cctvNum) {
			max = Math.max(max, sum);
			return;
		}
		int x = cctv[cnt][0];
		int y = cctv[cnt][1];
		int d = map[x][y];
		//색칠한다.	
		if(d == 1) {
			for(int i = 0;i < 4;i++) {
				Queue<Pair2> remove = new LinkedList<Pair2>();
				Queue<Pair> q = new LinkedList<Pair>();
				q.add(new Pair(x, y, i));
				int tmpCnt = drawMap(q, remove);				
				dfs(cnt + 1, sum + tmpCnt);
				removeVisit(remove);
			}
		}else if(d == 2) {
			for(int i = 0;i < 2;i++) {
				Queue<Pair2> remove = new LinkedList<Pair2>();
				Queue<Pair> q = new LinkedList<Pair>();
				q.add(new Pair(x, y, cctv2Dir[i][0]));
				q.add(new Pair(x, y, cctv2Dir[i][1]));
				int tmpCnt = drawMap(q, remove);	
				dfs(cnt + 1, sum + tmpCnt);
				removeVisit(remove);
			}
		}else if(d == 3) {
			for(int i = 0;i < 4;i++) {
				Queue<Pair2> remove = new LinkedList<Pair2>();
				Queue<Pair> q = new LinkedList<Pair>();
				q.add(new Pair(x, y, cctv3Dir[i][0]));
				q.add(new Pair(x, y, cctv3Dir[i][1]));
				int tmpCnt = drawMap(q, remove);	
				dfs(cnt + 1, sum + tmpCnt);
				removeVisit(remove);
			}
		}else if(d == 4) {
			for(int i = 0;i < 4;i++) {
				Queue<Pair2> remove = new LinkedList<Pair2>();
				Queue<Pair> q = new LinkedList<Pair>();
				q.add(new Pair(x, y, cctv4Dir[i][0]));
				q.add(new Pair(x, y, cctv4Dir[i][1]));
				q.add(new Pair(x, y, cctv4Dir[i][2]));
				int tmpCnt = drawMap(q, remove);	
				dfs(cnt + 1, sum + tmpCnt);
				removeVisit(remove);
			}
		}else if(d == 5){	
			Queue<Pair2> remove = new LinkedList<Pair2>();
			Queue<Pair> q = new LinkedList<Pair>();
			q.add(new Pair(x, y, 0)); q.add(new Pair(x, y, 1)); 
			q.add(new Pair(x, y, 2)); q.add(new Pair(x, y, 3));
			int tmpCnt = drawMap(q, remove);	
			dfs(cnt + 1, sum + tmpCnt);
			removeVisit(remove);
		}
	}
	
	static void removeVisit(Queue<Pair2> remove) {
		int size = remove.size();
		for(int j = 0;j < size;j++) {
			Pair2 tmp = remove.remove();
			visit[tmp.x][tmp.y] = false;
		}
	}
	
	static int drawMap(Queue<Pair> q, Queue<Pair2> remove) {
		int tmpCnt = 0;
		while(!q.isEmpty()) {
			Pair tmp = q.remove();
			int tmpX = tmp.x + dir[tmp.dir][0];
			int tmpY = tmp.y + dir[tmp.dir][1];
			if(tmpX < 0 || tmpY < 0|| tmpX >= N|| tmpY >= M || map[tmpX][tmpY] != 0)continue;
			if(!visit[tmpX][tmpY]) {
				remove.add(new Pair2(tmpX,tmpY));
				tmpCnt++;
			}
			visit[tmpX][tmpY] = true;
			q.add(new Pair(tmpX, tmpY, tmp.dir));
		}
		return tmpCnt;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i = 0;i < N;i++) {
			for(int j = 0;j < M;j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] >= 1 && map[i][j] <= 5) {
					cctv[cctvNum][0] = i;
					cctv[cctvNum++][1] = j;
				}else if(map[i][j] == 0) {
					cnt++;
				}
			}
		}		
		dfs(0, 0);		
		System.out.println(cnt - max);
	}
}
