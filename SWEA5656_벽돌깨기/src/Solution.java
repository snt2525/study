import java.util.*;
class Pair{
	int x, y;
	int num;
	Pair(int x, int y,int num){
		this.x = x;
		this.y = y;
		this.num = num;
	}
}
public class Solution {
	int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	int N, M, K;
	int[][] map;
	int min = Integer.MAX_VALUE;
	void DFS(int cnt, int remain) {
		if(remain == 0 || min == 0) {
			min = 0;
			return;
		}
		if(cnt == K) {
			min = Math.min(min, remain);
			return;
		}
		
		for(int y = 0;y < M;y++) {
			int[][] saveMap  = new int[N][M];
			int tmpX = findTopNum(y);
			if(tmpX >= N) continue;
			copyMap(saveMap);
			int remainTmp = visitCheck(tmpX, y);
			pullMap();
			DFS(cnt + 1, remain - remainTmp);
			returnMap(saveMap);
			
		}
	}
	
	int visitCheck(int tmpX, int tmpY) {
		int result = 1;
		if(map[tmpX][tmpY] == 1) {
			map[tmpX][tmpY] = 0;
			return 1;
		}
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(tmpX, tmpY,map[tmpX][tmpY]));
		while(!q.isEmpty()) {
			Pair tmp = q.remove();	
			int saveCnt = tmp.num;
			map[tmp.x][tmp.y] = 0;
			for(int i = 0;i < 4;i++) {
				int x = tmp.x + dir[i][0];
				int y = tmp.y + dir[i][1];
				int cnt = saveCnt - 1;
				while(x >= 0&&y >= 0&&x < N&&y < M&&cnt > 0) {
					if(map[x][y] != 0)result++;
					if(map[x][y] > 1) 
						q.add(new Pair(x, y, map[x][y]));
					map[x][y] = 0;
					x += dir[i][0];
					y += dir[i][1];
					cnt--;
				}
			}
		}
		return result;
	}
	
	int findTopNum(int y) {
		int cnt = 0;
		while(cnt < N && map[cnt][y] == 0) 
			cnt++;	
		return cnt;
	}
	
	void pullMap() {
		for(int i = 0;i < M;i++) {
			for(int j = N - 1;j >= 0;j--) {
				if(map[j][i] == 0) {
					int saveJ = j;
					while(saveJ > 0 && map[saveJ][i] == 0) 
						saveJ--;	
					if(saveJ >= 0)
						swap(i, saveJ, j);
				}
			}
		}
	}
	
	void swap(int i, int j, int saveJ) {
		int tmp = map[j][i];
		map[j][i] = map[saveJ][i];
		map[saveJ][i] = tmp;
	}
	
	void copyMap(int[][] saveMap) {
		for(int i = 0;i < N;i++) 
			for(int j = 0;j < M;j++) 
				saveMap[i][j] = map[i][j];				
	}
	
	void returnMap(int[][] saveMap) {
		for(int i = 0;i < N;i++) 
			for(int j = 0;j < M;j++) 
				map[i][j] = saveMap[i][j];		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int loop = sc.nextInt();
		for(int tc = 1;tc <= loop;tc++) {
			Solution s = new Solution();
			s.K = sc.nextInt();
			s.M = sc.nextInt();
			s.N = sc.nextInt();
			s.map = new int[s.N][s.M];
			int count = 0;
			for(int i = 0;i < s.N;i++) {
				for(int j = 0;j < s.M;j++) {
					s.map[i][j] = sc.nextInt();
					if(s.map[i][j] > 0) 
						count++;					
				}
			
			s.DFS(0, count);		
			System.out.println("#" + tc + " " + s.min);
		}
	}
}
