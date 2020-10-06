package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BAEKJOON_1753_최단경로 {

	private static final int INF = 	Integer.MAX_VALUE/3;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());

		List<Edge>[] graph = new ArrayList[V + 1];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[from].add(new Edge(to, w));
		}

		PriorityQueue<Edge> queue = new PriorityQueue<>();
		Edge[] D = new Edge[V + 1];
		boolean[] check = new boolean[V + 1];
		for (int i = 1; i <V+1; i++) {
			if (i == start)
				D[i] = new Edge(i, 0);
			else {
				D[i] = new Edge(i, INF);
			}
			queue.add(D[i]);
		}
		
		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			if(edge.weight==INF)
				break;
			for (Edge next : graph[edge.v]) {
				if (!check[next.v] && D[next.v].weight > D[edge.v].weight + next.weight) { 
					D[next.v].weight = D[edge.v].weight + next.weight;
					queue.remove(D[next.v]);
					queue.add(D[next.v]);			
				}
			}
				check[edge.v] = true;

		}
		
		for (int i = 1; i <D.length; i++) {
			if(D[i].weight==INF)
				System.out.println("INF");
			else 
				System.out.println(D[i].weight);
		}
	}

	private static class Edge implements Comparable<Edge> {
		int v, weight;

		public Edge(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}

	}

	private static String src = "5 6\n" + 
			"1\n" + 
			"5 1 1\n" + 
			"1 2 2\n" + 
			"1 3 3\n" + 
			"2 3 4\n" + 
			"2 4 5\n" + 
			"3 4 6";
}
