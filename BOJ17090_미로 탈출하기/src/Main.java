import java.util.*;
public class Main {
	static int[][] dir = new int[30][2];
	static boolean[][] possible;
	static boolean[][] visit;
	static char[][] map;
	static int N,M;
	
	static boolean DFS(int x, int y) {	
		visit[x][y] = true;
		
		int nextX = x + dir[map[x][y] - 'A'][0];
		int nextY = y + dir[map[x][y] - 'A'][1];
		
		if(nextX >= N ||nextY >= M||nextY < 0||nextX < 0||possible[nextX][nextY])
			return true;
		
		if(!visit[nextX][nextY]) {
			visit[nextX][nextY] = true;
			possible[nextX][nextY] = DFS(nextX, nextY);
			visit[nextX][nextY] = false;
			
			if(possible[nextX][nextY]) 
				return true;			
		}
		return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		possible = new boolean[N][M];
		visit = new boolean[N][M];
		
		dir[(int)('U' - 'A')][0] = -1; 
		dir[(int)('L' - 'A')][1] = -1;
		dir[(int)('R' - 'A')][1] = 1;  
		dir[(int)('D' - 'A')][0] = 1;
		
		for(int i = 0;i < N;i++) {
			char[] tmp = sc.next().toCharArray();
			for(int j = 0;j < M;j++) {
				map[i][j] = tmp[j];
			}
		}
		
		for(int i = 0;i < N;i++) 
			for(int j = 0;j < M;j++) 
				if(!possible[i][j]) 
					possible[i][j] = DFS(i,j);	
		
		int cnt = 0;
		for(int i = 0;i < N;i++)
			for(int j = 0;j < M;j++)
				if(possible[i][j])
					cnt++;
				
		System.out.println(cnt);
	}

}
