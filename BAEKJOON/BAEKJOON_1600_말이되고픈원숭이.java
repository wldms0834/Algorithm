package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON_1600_말이되고픈원숭이 {
	
	static int K;
	static int W;
	static int H;
	static int[][]board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		board = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <W; j++) {
				board[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int result = bfs(0,0);
		System.out.println(result);
	}
	
	private static int bfs(int r, int c) {
		boolean[][][]visited = new boolean[H][W][K+1];
		int[][]moneyDirs= {{0,1},{1,0},{-1,0},{0,-1}};
		int[][]horseDirs= {{2,1},{1,2},{-1,-2},{-2,-1},{2,-1},{1,-2},{-2,1},{-1,2}};
		Queue<position> queue = new LinkedList<>();
		
		queue.offer(new position(r, c, 0,K));
		
		visited[r][c][0]=true;
		
		while(!queue.isEmpty()) {
			position np = queue.poll();
			if(np.r==(H-1)&& np.c==(W-1)) {
				return np.d;
			}else {
				if(np.horseCnt<=0) {
					for (int i = 0; i < moneyDirs.length; i++) {
						int nr=np.r+moneyDirs[i][0];
						int nc=np.c+moneyDirs[i][1];
						if(isIn(nr, nc) &&!visited[nr][nc][0]&& board[nr][nc]!=1) {
							queue.offer(new position(nr, nc, np.d+1,0));
							visited[nr][nc][0]=true;
						}
					}
				}
				else {
					for (int i = 0; i < moneyDirs.length; i++) {
						int nr=np.r+moneyDirs[i][0];
						int nc=np.c+moneyDirs[i][1];
						if(isIn(nr, nc)&& !visited[nr][nc][np.horseCnt]&&board[nr][nc]!=1) {
							queue.offer(new position(nr, nc, np.d+1,np.horseCnt));	
							visited[nr][nc][np.horseCnt]=true;
						}
					}
					for (int i = 0; i < horseDirs.length; i++) {
						int nr=np.r+horseDirs[i][0];
						int nc=np.c+horseDirs[i][1];
						if(isIn(nr, nc)&& !visited[nr][nc][np.horseCnt-1]&& board[nr][nc]!=1) {
							queue.offer(new position(nr, nc, np.d+1,np.horseCnt-1));
							visited[nr][nc][np.horseCnt-1]=true;
						}
					}
				}
			}
		}
		return -1;
		
	}
	
	private static class position{
		int r,c,d,horseCnt;

		public position(int r, int c, int d,int horseCnt) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.horseCnt = horseCnt;
		}
		
	}
	
	private static boolean isIn(int r, int c) {
		return r>=0 && r<H && c>=0 && c<W;
	}
	private static String src="1\n" + 
			"4 4\n" + 
			"0 0 0 0\n" + 
			"1 0 0 0\n" + 
			"0 0 1 0\n" + 
			"0 1 0 0";
}


