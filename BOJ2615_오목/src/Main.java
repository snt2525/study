import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair{
	int x, y;
	int color;
	Pair(int x, int y, int color){
		this.x = x;
		this.y = y;
		this.color = color;
	}
}
public class Main {
	static int[][] dir = {{0,1},{1,-1},{1,1},{1,0}};
	static Queue<Pair> q = new LinkedList<Pair>();
	static int[][] map = new int[20][20];
	static boolean[][][] visit = new boolean[20][20][4];
	static void whoIsTheWinner() {
		while(!q.isEmpty()) {
			Pair tmp = q.remove();
			for(int i = 0;i < 4;i++) {
				int x = tmp.x;
				int y = tmp.y;		
				int cnt = 1;
				while(true) {
					x = x + dir[i][0];
					y = y + dir[i][1];
					if(x < 0||y < 0||x >= 19||y >= 19) break;
					if(!(map[x][y] == tmp.color && !visit[x][y][i])) break;
					visit[x][y][i] = true;
					cnt++;				
				}
				if(cnt == 5) {
					System.out.println(tmp.color);
					if(i == 1) {
						System.out.println((tmp.x + 5)+ " " + (tmp.y - 3));
					}else {						
						System.out.println((tmp.x + 1) + " " + (tmp.y + 1));
					}
					return;
				}
			}
		}
		System.out.print(0);
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0;i < 19;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 0;j < 19;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) {
					q.add(new Pair(i, j, map[i][j]));
				}
			}
		}
		whoIsTheWinner();
	}
}
