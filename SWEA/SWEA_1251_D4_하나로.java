package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



/**
 * 
 * @author herjieun
 * cycle 없이 모든 정점 연 ->tree (최소 비용으로 연결되는 tree)
 *  우리의 무기 - 완탐 !
 *  -완탐 하다가 안되면 : dp, 그리디
 *  -그리디 : 검증된 녀석들만쓰자..
 *  	-활동 선택 문제 : 회의실 배정, 냉장고
 *  	-그래프와 관련된 그리디 :prim(mst), kruskal(mst), dijkstra( 어떤 정점에서 다른 정점까지의 최단 거리 (비용 ))
 *  
 *  -mst(minimum spanning tree)
 *  -spanning tree : 모든 정점을 연결하고, 간선의 부분들로 이루어진 집합
 *   , tree이므로 모든 정점이 연결되고, cycle은 없다.root개념, 조상, 자식 개념 없음
 *   
 *   
 *   최소비용을 연결한 간선 ? priority queue 를 이용하여 구
 *   
 *   
 *
 */


public class SWEA_1251_D4_하나로 {

	static int landCnt;
	static long[][] lands;
	static double E;
	static long [][]graph;
	
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
			
			//입력된 자료를 기반으로 섬 간의 가중치 그래프를 그려봅시당
			graph = new long[landCnt][landCnt];
			long[]from,to;
			for (int r = 0; r <landCnt; r++) {
				from=lands[r];
				for (int c = r+1; c < landCnt; c++) {
					to= lands[c];
					graph[c][r]=graph[r][c]=(from[0]-to[0]) *(from[0]-to[0])+(from[1]-to[1])*(from[1]-to[1]);
				}
			}
			
			double cost = prim(0) *E;
			
			sb.append(Math.round(cost)).append("\n");
			}System.out.println(sb);
			
		}
	
	
	private static long prim(int start) {
		//long cost =0;
		//mst에 들어가지 않는 녀석들 
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		//모든 정점들을 다 관리
		Edge[] dynamicGraph = new Edge[landCnt];
		
		for (int i = 0; i < dynamicGraph.length; i++) {
			dynamicGraph[i] = new Edge(i, Long.MAX_VALUE);
			if(i==start) {
				//넣기전에 결정
				dynamicGraph[i].cost =0;
			}
			pq.add(dynamicGraph[i]);
		}
		
		long cost = 0;
		while(!pq.isEmpty()) {
			Edge front = pq.poll();
			cost+=front.cost;
			
			for (int i = 0; i < dynamicGraph.length; i++) {
				Edge child = dynamicGraph[i];
				//pq : 비 mst
				if(pq.contains(child)) {
					long tempCost = graph[front.idx][child.idx];
					if(tempCost<child.cost) {
						child.cost = tempCost;
						pq.remove(child);
						pq.add(child);
					}
				}
			}
		}
		
		return cost;
	}

	static class Edge implements Comparable<Edge> {
		int idx;
		long cost;
				
		public Edge(int idx, long cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}
	
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Long.compare(this.cost, o.cost);
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
