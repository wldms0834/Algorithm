package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_3124_D4_최소스패닝트리 {

	static int V;
	static int E;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			parents = new int[V + 1];
			int result = 0;

			PriorityQueue<edge> queue = new PriorityQueue<>();
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				queue.offer(new edge(a, b, w));
			}
			for (int i = 1; i <= V; i++) {
				makeSet(i);
			}
			long total = 0;
			int cnt = 0;
			// 정점의 갯수 -번 반복하면서
			for (int i = 0; i < E; i++) {
				edge temp = queue.poll();
				int a = findSet(temp.from);
				int b = findSet(temp.to);
				if (a == b)
					continue;
				union(a, b);
				total += temp.w;
				cnt++;
				if (cnt == V - 1)
					break;

			}

			sb.append(total).append("\n");
		}
		System.out.println(sb);
	}

	// 최초의 집합만들기
	static void makeSet(int x) {
		parents[x] = x;
	}

	// 우리 집합의 대표자 찾기
	static int findSet(int x) {
		if (x == parents[x])
			return x;
		else {
			return parents[x] = findSet(parents[x]);
		}
	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		parents[y] = x;
	}

	private static class edge implements Comparable<edge> {

		int from, to, w;

		public edge(int from, int to, int w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public String toString() {
			return "edge [from=" + from + ", to=" + to + ", w=" + w + "]";
		}

		@Override
		public int compareTo(edge o) {
			return Integer.compare(this.w, o.w);
		}

	}

	private static String src = "1\n" + 
			"5 7\n" + 
			"1 2 1\n" + 
			"1 3 1\n" + 
			"1 4 1\n" + 
			"2 3 1\n" + 
			"2 4 1\n" + 
			"3 4 1\n" + 
			"4 5 2";
}
