import java.util.*;
public class Main {
	static String[] num;
	static int k;
	static void dfs(int cnt, int choice, String word) {
		if(choice == 6) {
			System.out.println(word);
			return;
		}
		if(cnt == k) return;
		if(word == "")
			dfs(cnt + 1, choice + 1, num[cnt] + " ");
		else
			dfs(cnt + 1, choice + 1, word + num[cnt] +" ");
		dfs(cnt + 1, choice, word);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		k = sc.nextInt();
		while(k != 0) {
			num = new String[k];
			for(int i = 0;i < k;i++)
				num[i] = sc.next();
			dfs(0, 0, "");
			k = sc.nextInt();
			if(k != 0)
				System.out.println();
		}
	}
}
