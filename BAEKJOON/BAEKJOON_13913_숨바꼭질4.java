package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BAEKJOON_13913_숨바꼭질4 {
	static int N;
	static int K;
	static boolean []visited = new boolean[100001];
	static int []parent = new int[100001];
	static int []depth = new int[100001];
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N =Integer.parseInt(st.nextToken());//수빈이의 위치 
		K =Integer.parseInt(st.nextToken());//동생의 위치 
		
		if(N==K) {
			System.out.println(0);
			System.out.println(N);
		}
		else 
			findSister();
	}
	
	private static void findSister() {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(N);
		int cnt=0;
		parent[N]=-1;
		visited[N]=true;
		depth[N]=0;
		
		while(!queue.isEmpty()) {
			int num =queue.poll();
			if(num==K) {
				System.out.println(depth[num]);
				int index=K;
				List<Integer> temp = new ArrayList<Integer>();
				while(true) {
					if(parent[index]==-1) {
						temp.add(0, N);
						for (Integer integer : temp) {
							System.out.print(integer+" ");
						}
						return;
					}else {
						temp.add(0, index);
						index=parent[index];
					}
				}
			}
			else {		
				if(num-1>=0&&!visited[num-1]) {
					queue.offer(num-1);				
					visited[num-1]=true;
					depth[num-1]=depth[num]+1;
					parent[num-1]=num;
				}
				if(num+1<100001&&!visited[num+1]) {
					queue.offer(num+1);
					visited[num+1]=true;
					depth[num+1]=depth[num]+1;
					parent[num+1]=num;
				}
				if(num*2<100001&&!visited[num*2]) {
					queue.offer(num*2);
					visited[num*2]=true;
					depth[num*2]=depth[num]+1;
					parent[num*2]=num;
				}
				
			}
		}
	}
	
}
