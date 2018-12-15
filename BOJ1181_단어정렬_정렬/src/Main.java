import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] word = new String[n];		
		for(int i = 0;i < n;i++)
				word[i] = sc.next();
		
		Arrays.sort(word);
		Arrays.sort(word, new Comparator<String>() {
			public int compare (String s1, String s2) {
				return Integer.compare(s1.length(), s2.length());
			}
		});		
		
		for(int i = 0;i < n;i++)
			System.out.println(word[i]);
	}
}
