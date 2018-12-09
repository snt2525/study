import java.util.*;
public class Main {
	/*  원래는 HashMap에 key와 실제 데이터가 저장된다. 
		실제 데이터가 없으므로 배열사용    */
	boolean[] check; 
	LinkedList<String> memory;
	int memorySize;
	
	Main(int n){
		this.memorySize = n;
		this.check = new boolean[150];
		this.memory = new LinkedList<String>();
	}
	
	//현재 메모리 상태 출력
	public void print() {   
		int mSize = memory.size();
		for(int i = 0; i < mSize; i++)
			System.out.print(memory.get(i));
		System.out.println();
	}
	
	//LRU 캐싱 알고리즘
	public void LRU(String[] cache) {
		int size = cache.length;
		for(int i = 0;i < size; i++) {
			if(cache[i].equals("!")) {
				print();
			}else {
				int s2i = cache[i].charAt(0);
				//이미 메모리에 있는 캐시인가?
				if(check[s2i]){
					for(int j = 0; j < memory.size(); j++) {
						//메모리 안의 캐시가 있는 위치를 찾음
						if(memory.get(j).equals(cache[i])) {
							memory.remove(j);    	//이 전 위치에 있던 캐시를 꺼내서
							memory.add(cache[i]);  	//다시 삽입
							break;
						}
					}
				//메모리 안에 존재하지 않는 캐시인가?
				}else{
					//메모리 여유 공간이 없으면
					if(memory.size() == memorySize) {
						check[memory.getFirst().charAt(0)] = false;
						memory.removeFirst();   //최근 가장 오래 사용하지 않은 캐시 삭제
					}
					check[s2i] = true;
					memory.add(cache[i]);       //메모리에 새로운 캐시 삽입
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();	
		int cnt = 1;
		
		while(n != 0) {           
			Main m = new Main(n);
			String[] cache = sc.next().trim().split("");
			System.out.println("Simulation " + (cnt++));
			m.LRU(cache);
			n = sc.nextInt();  
		}
	}
}
