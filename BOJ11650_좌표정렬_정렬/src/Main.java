import java.util.*;
class Pair{
	int x;
	int y;
	Pair(int x,int y){
		this.x = x;
		this.y = y;
	}
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
}

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		String[] a = {"A", "a"," "};
		System.out.print(a[0].compareTo(a[1]));
		int n = sc.nextInt();
		Pair[] p = new Pair[n];
		for(int i = 0;i < n;i++) 
			p[i] = new Pair(sc.nextInt(), sc.nextInt());
		
		
		Arrays.sort(p , new Comparator<Pair>() {
			public int compare(Pair arg0, Pair arg1) {
				if(arg0.x == arg1.x) {
					return Integer.compare(arg0.y, arg1.y);
				}
				return Integer.compare(arg0.x, arg1.x);
			}
			
		});
	
		for(int i = 0;i < n;i++)
			System.out.println(p[i].x + " "+p[i].y);
	}
}
