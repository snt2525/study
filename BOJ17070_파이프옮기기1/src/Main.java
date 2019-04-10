import java.util.*;
public class Main {
	static int[][] dir = {{0,1},{1,1},{1,0}};
	static int[][] turn = {{0,1,-1},{0,2,1},{2,1,-1},{0,2,-1}};
	static boolean[][] visit;
	static boolean[][] map;
	static int N;
	static int maxMove = 0;
	static void DFS(int x, int y, int d, int move) {
		if(x == N - 1 && y == N - 1) {
			maxMove++;
			return;
		}
		
		int checkCnt = 0;
		for(int i = 0;i < 3;i++) {
			int tmpD = turn[d][i];
			if(tmpD == - 1) continue;
			int tmpX = x + dir[tmpD][0];
			int tmpY = y + dir[tmpD][1];
			if(tmpX >= 0 && tmpY >= 0 && tmpX < N && tmpY < N) {
				if(map[tmpX][tmpY] || visit[tmpX][tmpY]) continue;				
				if(tmpD == 1) {				
					boolean flag = true;
					for(int j = 0;j < 2;j++) {
						int tmpXX = x + dir[turn[3][j]][0];
						int tmpYY = y + dir[turn[3][j]][1];
						if(tmpXX < 0 || tmpYY < 0 || tmpXX >= N || tmpYY >= N 
								|| visit[tmpXX][tmpYY] || map[tmpXX][tmpYY]) {
							flag = false;
							break;
						}
					}
					if(!flag)
						continue;
				}				
				checkCnt++;
				visit[tmpX][tmpY] = true;
				DFS(tmpX, tmpY, tmpD, move + 1);
				visit[tmpX][tmpY] = false;
			}
		}	
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new boolean[N][N];
		visit = new boolean[N][N];
		for(int i = 0;i < N;i++)
			for(int j = 0;j < N;j++)
				map[i][j] = sc.nextInt() == 1 ? true: false;
		
		visit[0][0] = true;
		visit[0][1] = true;
		DFS(0, 1, 0, 0);
	
		System.out.println(maxMove);
	}
}
