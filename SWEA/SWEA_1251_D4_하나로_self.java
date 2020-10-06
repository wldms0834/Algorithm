package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1251_D4_하나로_self {
	static int landCnt;
	static long [][]lands;
	static double E;
	static long[][]graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			landCnt = Integer.parseInt(st.nextToken());
			lands = new long [landCnt][2];
			
			st = new StringTokenizer(br.readLine()); //x좌표 받기 
			for (int i = 0; i <lands.length; i++) {
				lands[i][0]=Long.parseLong(st.nextToken());
			}
			st = new StringTokenizer(br.readLine()); //y좌표 받기 
			for (int i = 0; i <lands.length; i++) {
				lands[i][1]=Long.parseLong(st.nextToken());
			}
				
			st = new StringTokenizer(br.readLine());
			E = Double.parseDouble(st.nextToken());
			
			graph = new long[landCnt][landCnt];
			for (int i = 0; i <landCnt; i++) {
				for (int j = 0; j <landCnt; j++) {
					graph[i][j] = (lands[j][0]-lands[i][0])*(lands[j][0]-lands[i][0])+(lands[j][1]-lands[i][1])*(lands[j][1]-lands[i][1]);
				}
			}
			
			double cost = prim(0) *E;
			sb.append(Math.round(cost)).append("\n");
		}
		System.out.print(sb);
	}
	private static double prim(int i) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		Edge [] notIncluded = new Edge[landCnt];
		for (int j = 0; j < notIncluded.length; j++) {
			notIncluded[j] = new Edge(j, Long.MAX_VALUE);
			
			if(j==i) {
				notIncluded[j].cost=0;
			}
			pq.offer(notIncluded[j]);
		}
		
		long cost=0;
		while(!pq.isEmpty()) {
			Edge ne = pq.poll();
			cost +=ne.cost;
			for (int j = 0; j < notIncluded.length; j++) {
				Edge child = notIncluded[j];
				if (pq.contains(child)) {
					long temp = graph[ne.index][child.index];
					if(temp<child.cost) {
						child.cost=temp;
						pq.remove(child);
						pq.offer(child);
					}
				}
				
			}
		}
		return cost;
	}
	private static class Edge implements Comparable<Edge>{
		int index;
		long cost;
		public Edge(int index, long cost) {
			super();
			this.index = index;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			long c1= this.cost;
			long c2= o.cost;
			return Long.compare(c1, c2);
		}
		
		
	}
	private static String src ="4\n" + 
				"2\n" + 
				"0 0\n" + 
				"0 100\n" + 
				"1.0\n" + 
				"4\n" + 
				"0 0 400 400\n" + 
				"0 100 0 100\n" + 
				"1.0\n" + 
				"6\n" + 
				"0 0 400 400 1000 2000\n" + 
				"0 100 0 100 600 2000\n" + 
				"0.3\n" + 
				"9\n" + 
				"567 5 45674 24 797 29 0 0 0\n" + 
				"345352 5464 145346 54764 5875 0 3453 4545 123\n" + 
				"0.0005";
}
