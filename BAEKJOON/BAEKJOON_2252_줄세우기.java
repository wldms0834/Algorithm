package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON_2252_줄세우기 {
	
	static int people;
	static int heightCheck;
	static int[] inDegree;
	static List<Integer>[] graph;
	static Queue<Integer> queue;
	static StringBuilder sb;
	static boolean[]visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		people = Integer.parseInt(st.nextToken());
		heightCheck = Integer.parseInt(st.nextToken());
		inDegree = new int[people+1];
		queue = new LinkedList<>();
		visited = new boolean[people+1];
		graph = new ArrayList[people+1];
		
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i <heightCheck; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			graph[num1].add(num2);
			inDegree[num2]++;
		}
		//System.out.println(Arrays.toString(inDegree));
		for (int i = 1; i < inDegree.length; i++) {
			if (inDegree[i]==0) {
				queue.offer(i);
				visited[i]=true;
			}
		}
		
		bfs();
		System.out.println(sb);

	}
	
	private static void bfs() {
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			sb.append(now).append(" ");
			for (int i = 0; i < graph[now].size(); i++) {
				if (!visited[graph[now].get(i)]) {
					if (inDegree[graph[now].get(i)]==1) {
						queue.offer(graph[now].get(i));
						visited[graph[now].get(i)]=true;
					}else {
						inDegree[graph[now].get(i)]--;;
					}
				}
			}
			
		}
		
	}

	private static String src="4 2\n" + 
			"4 2\n" + 
			"3 1";
}
