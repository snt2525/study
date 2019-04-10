import java.util.*;
public class Main {
	static String[] word;
	static int N, K;
	static void DFS(int cnt, int num, String sum, boolean countA, int countB) {
		if(cnt == K && countA && countB >= 2) {
			System.out.println(sum);
			return; 
		}
		if(num >= N) return;
		if(word[num].equals("a")||word[num].equals("e")||
				word[num].equals("i")||word[num].equals("o")||
				word[num].equals("u")) {
			DFS(cnt + 1, num + 1, sum + word[num], true, countB);
		}else {
			DFS(cnt + 1, num + 1, sum + word[num], countA , countB + 1);
		}
		DFS(cnt, num + 1, sum, countA, countB);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		N = sc.nextInt();
		word = new String[N];
		for(int i = 0;i < N;i++)
			word[i] = sc.next();
		Arrays.sort(word);
		DFS(0, 0, "", false, 0);
	}
}
