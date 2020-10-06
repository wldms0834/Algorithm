package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON_2178_미로탐색 {

	static int R,C;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for (int i = 0; i <R; i++) {
			String temp =br.readLine();			
			for (int j = 0; j <C; j++) {
				map[i][j]=temp.charAt(j)-'0';
			}
		}//입력완료
		//System.out.println(map);
		
		int temp = bfs(0,0);
		System.out.println(temp);
		//System.out.println(bfs(0,0));

	}
	private static int bfs(int i, int j) {
		int [][]dirs= {{1,0},
						{0,1},
						{-1,0},
						{0,-1}}; //상하좌우
		
		Queue<Position> queue = new LinkedList<>(); //큐만들
		
		boolean [][]visited = new boolean[R][C];
		visited[i][j]=true; //방문 표
		
		queue.offer(new Position(i, j, 1)); //큐에넣어주기
		
		while(!queue.isEmpty()) {
			Position np = queue.poll();
			if (np.r==R-1 && np.c==C-1) {
				return np.d;
			}
			for (int k = 0; k < dirs.length; k++) {
				int nr = np.r+dirs[k][0];
				int nc = np.c+dirs[k][1];
				if (isIn(nr, nc)&& !visited[nr][nc] && map[nr][nc]==1) {
					queue.offer(new Position(nr, nc, np.d+1));
					visited[nr][nc]=true;
				}
			}
		}
		return -1;
		
	}
	private static boolean isIn(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
	private static class Position{
		int r,c,d;

		public Position(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
		
	}
	private static String src ="4 6\n" + 
			"101111\n" + 
			"101010\n" + 
			"101011\n" + 
			"111011";
}
