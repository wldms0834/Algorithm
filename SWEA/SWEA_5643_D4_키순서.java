package SWEA;

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

public class SWEA_5643_D4_키순서 {

	static int studentcCnt;
	static int compareCnt;
	static int[] inDegree;
	static List<Integer>[] graph;
	static Queue<position> queue;
	static StringBuilder sb;
	static boolean[]visited;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			studentcCnt = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			compareCnt = Integer.parseInt(st.nextToken());
			
			inDegree = new int[studentcCnt+1];
			queue = new LinkedList<>();
			visited = new boolean[studentcCnt+1];
			graph = new ArrayList[studentcCnt+1];
			
			for (int i = 1; i < graph.length; i++) {
				graph[i] = new ArrayList<Integer>();
			}
			
			for (int i = 0; i <compareCnt; i++) {
				st = new StringTokenizer(br.readLine());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				
				graph[num1].add(num2);
				inDegree[num2]++;
			}
			
			for (int i = 1; i <inDegree.length; i++) {
				if (inDegree[i]==0) {
					queue.add(new position(i, 0));
					visited[i]=true;
				}
			}
			int [] result=bfs();
			System.out.println(sb);
			System.out.println(Arrays.toString(result));
		}
		
	}
	private static int[] bfs() {
		int []check = new int[studentcCnt+1];
		
		while(!queue.isEmpty()) {
			position np = queue.poll();
			sb.append(np.idx).append(" ");
			check[np.depth]++;
			
			for (int i = 0; i < graph[np.idx].size(); i++) {
				int temp = graph[np.idx].get(i);
				if (!visited[temp]) {
					if (inDegree[temp]!=1) {
						inDegree[temp]--;
					}else {
						queue.offer(new position(temp, np.depth+1));
						visited[temp]=true;
					}
				}
				
			}
		}
		return check;
	}
	
	private static class position{
		int idx, depth;

		public position(int idx, int depth) {
			super();
			this.idx = idx;
			this.depth = depth;
		}
		
	}
	private static String src = "1\n" + 
			"6\n" + 
			"6\n" + 
			"1 5\n" + 
			"3 4\n" + 
			"5 4\n" + 
			"4 2\n" + 
			"4 6\n" + 
			"5 2";
}
