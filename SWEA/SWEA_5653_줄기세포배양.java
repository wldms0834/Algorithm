package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5653_줄기세포배양 {
	static int [][]area;
	static int N; //세로크기
	static int M; //가로크기
	static int T; //배양시간
	static Queue<Cell>q;
	static int [][]visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <=TC; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			N =Integer.parseInt(st.nextToken());
			M =Integer.parseInt(st.nextToken());
			T =Integer.parseInt(st.nextToken());
			q= new LinkedList<>();
			visited = new int[N+T][M+T];
			area = new int[N+T][M+T];
			for (int i = T/2; i < T/2+N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = T/2; j < T/2+M; j++) {
					area[i][j]=Integer.parseInt(st.nextToken());
					q.offer(new Cell(i, j, area[i][j], false));
					visited[i][j]=area[i][j];
				}
			}
			sb.append(spread()).append("\n");
		}
		System.out.println(sb);
	}
	
	private static int spread() {
		List<Cell> list = new ArrayList<>();
		int total=0;
		int before=0;
		int ans =0;
		int[][]dirs = {{0,1},{1,0},{0,-1},{-1,0}};
		while(!q.isEmpty()) {
			Cell c = q.poll();
			list.add(c);
			
			if(before!=c.t) {
				total+=c.t-before;
				before = c.t;
			}			
			ans++;
			
			if(total>=T) {
				break;
			}
			
			for (int i = 0; i < dirs.length; i++) {
				int nr =c.r+dirs[i][0];
				int nc =c.c+dirs[i][1];
				if(isIn(nr, nc)&& visited[nr][nc]<c.t) {
					q.offer(new Cell(nr, nc, c.t, false));
				}
			}
		}
		return list.size();
		
	}
	private static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
		}
	
	private static class Cell implements Comparable<Cell>{
		int r,c,t;
		boolean isLived;
		public Cell(int r, int c, int t, boolean isLived) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
			this.isLived = isLived;
		}
		@Override
		public int compareTo(Cell o) {
			Integer t1 = this.t;
			Integer t2 = o.t;
			return t1.compareTo(t2);
		}
		
		
		
	}
	private static String src="4\n" + 
			"2 2 10\n" + 
			"1 1\n" + 
			"0 2\n" + 
			"5 5 19\n" + 
			"3 2 0 3 0 \n" + 
			"0 3 0 0 0 \n" + 
			"0 0 0 0 0 \n" + 
			"0 0 1 0 0 \n" + 
			"0 0 0 0 2\n" + 
			"9 10 37\n" + 
			"0 0 0 0 0 0 0 0 3 0 \n" + 
			"0 0 0 0 0 0 0 0 5 3 \n" + 
			"0 0 2 0 0 0 0 4 0 0 \n" + 
			"3 0 0 0 0 0 4 0 0 0 \n" + 
			"0 0 0 0 0 3 5 0 0 2 \n" + 
			"0 0 0 0 0 0 0 0 0 5 \n" + 
			"0 0 0 0 0 0 0 0 2 3 \n" + 
			"0 0 0 0 0 0 0 0 0 0 \n" + 
			"0 0 2 2 0 0 0 0 0 0 \n" + 
			"20 18 83\n" + 
			"0 0 0 0 0 0 0 0 0 0 0 2 0 0 6 0 0 0 \n" + 
			"0 0 0 0 0 0 0 3 0 0 0 0 0 0 0 0 0 0 \n" + 
			"0 0 0 6 0 0 0 0 0 0 0 0 0 0 2 0 3 0 \n" + 
			"4 0 2 0 0 0 0 0 0 0 0 0 5 0 0 0 0 3 \n" + 
			"0 0 0 0 0 5 4 4 6 0 0 0 0 0 0 0 0 0 \n" + 
			"5 0 0 0 0 0 2 0 2 6 0 0 0 0 0 4 0 0 \n" + 
			"4 0 0 3 0 0 0 0 0 0 0 3 0 0 0 5 0 0 \n" + 
			"0 0 0 0 0 0 0 2 2 0 0 0 0 3 0 0 0 0 \n" + 
			"0 0 0 0 5 0 0 0 3 0 3 0 0 4 0 0 0 0 \n" + 
			"0 0 0 0 6 0 0 0 0 0 0 0 0 0 0 0 0 0 \n" + 
			"0 0 0 0 6 0 2 0 0 0 0 0 3 0 0 0 3 0 \n" + 
			"0 5 2 0 0 0 0 4 0 0 0 0 0 0 0 0 0 0 \n" + 
			"3 0 0 0 0 0 0 0 6 0 2 0 5 0 0 0 0 0 \n" + 
			"5 0 0 0 4 0 0 0 0 0 0 0 0 0 0 0 0 0 \n" + 
			"0 6 0 5 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n" + 
			"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n" + 
			"0 0 0 4 0 0 0 0 0 0 0 0 0 0 2 0 0 0 \n" + 
			"0 0 3 4 5 0 0 0 0 0 0 0 0 0 0 6 0 0 \n" + 
			"2 0 0 0 0 3 0 0 0 0 0 0 0 0 0 5 0 0 \n" + 
			"0 0 0 0 0 0 0 0 0 3 6 2 0 0 0 0 0 0 ";
}
