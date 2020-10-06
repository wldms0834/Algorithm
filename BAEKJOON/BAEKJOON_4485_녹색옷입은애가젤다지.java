package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BAEKJOON_4485_녹색옷입은애가젤다지 {

	static int size;
	
	private static class edge implements Comparable<edge>{
		int r, c,w;

		public edge(int r, int c,int w) {
			super();
			this.r = r;
			this.c = c;
			this.w =w;
		}
		

		@Override
		public int compareTo(edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.w, o.w);
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st;
		int index=1;
		while(true) {
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			int totalMoney=0;
			if(size==0) {
				break;
			}
			else {
				int[][]cave = new int[size][size];
				for (int i = 0; i <size; i++) {
					st = new StringTokenizer(br.readLine());
					for (int j = 0; j <size; j++) {
						cave[i][j]=Integer.parseInt(st.nextToken());
					}
				}
				edge[][] D = new edge[size][size];
				int [][]dirs = {{1,0},{-1,0},{0,-1},{0,1}};
								
				PriorityQueue<edge> queue = new PriorityQueue<>();
				for (int i = 0; i <size; i++) {
					for (int j = 0; j < size; j++) {
						if (i == 0 && j==0)
							D[i][j] = new edge(i,j,cave[i][j]);
						else {
							D[i][j] = new edge(i,j,Integer.MAX_VALUE);
						}
						queue.add(D[i][j]);						
					}				
				}
				
				while(!queue.isEmpty()) {
					edge now = queue.poll();
					if(now.r==size-1 && now.c==size-1) {
						totalMoney=now.w;
						break;
					}
					for (int i = 0; i < dirs.length; i++) {						
						int nr = now.r+dirs[i][0];
						int nc = now.c+dirs[i][1];
						if(isIn(nr, nc) && D[nr][nc].w > D[now.r][now.c].w + cave[nr][nc]) {
							D[nr][nc].w =D[now.r][now.c].w + cave[nr][nc];
							queue.remove(D[nr][nc]);
							queue.add(D[nr][nc]);
						}
					}
				}
				
				
				System.out.println("Problem "+(index++)+": "+totalMoney);
				
			}
		}

	}
	private static boolean isIn(int r, int c) {
		return r>=0 && r<size && c>=0 && c<size;
	}
	private static String src="3\n" + 
			"5 5 4\n" + 
			"3 9 1\n" + 
			"3 2 7\n" + 
			"5\n" + 
			"3 7 2 0 1\n" + 
			"2 8 0 9 1\n" + 
			"1 2 1 8 1\n" + 
			"9 8 9 2 0\n" + 
			"3 6 5 1 5\n" + 
			"7\n" + 
			"9 0 5 1 1 5 3\n" + 
			"4 1 2 1 6 5 3\n" + 
			"0 7 6 1 6 8 5\n" + 
			"1 1 7 8 3 2 3\n" + 
			"9 4 0 7 6 4 1\n" + 
			"5 8 3 2 4 8 3\n" + 
			"7 4 8 4 8 3 4\n" + 
			"0";
}
