package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1868_D4_파핑파핑지뢰찾기 {

	static int notBombCnt;
	static int N;
	static int[][]game;
	static int min;
	static boolean [][]visited;
	static Queue<Point> queue;
	static int [][]dirs = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <=TC; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			game = new int[N][N];
			min = 0;
			visited = new boolean[N][N];
			queue = new LinkedList<>();
			for (int i = 0; i <N; i++) {
				String temp = br.readLine();
				for (int j = 0; j <N; j++) {
					game[i][j]=temp.charAt(j);
				}
			}
			
			for (int i = 0; i <N; i++) {
				for (int j = 0; j <N; j++) {
						if(game[i][j]=='.' && !visited[i][j]) {
							int BombCnt=0;
							for (int k = 0; k < dirs.length; k++) {
								int nr = i+dirs[k][0];
								int nc = j+dirs[k][1];
								if(isIn(nr, nc)) {
									if(game[nr][nc]=='*') BombCnt++;
								}
							}
							if(BombCnt==0) {
								visited[i][j]=true;
								queue.offer(new Point(i, j));
								min+=findBomb();
							}
					}
				}
			}
			
			for (int i = 0; i <N; i++) {
				for (int j = 0; j <N; j++) {
					if(!visited[i][j] && game[i][j]=='.') min++;
				}
			}
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}
		
	private static int findBomb() {		 
		while(!queue.isEmpty()) {
			Point np = queue.poll();
			int bombCnt=0;
			
			for (int i = 0; i < dirs.length; i++) {
				int nr = np.r+dirs[i][0];
				int nc = np.c+dirs[i][1];
				if(isIn(nr, nc)&& game[nr][nc]=='*') bombCnt++;
			}
			if(bombCnt==0) {
				for (int i = 0; i < dirs.length; i++) {
					int nr = np.r+dirs[i][0];
					int nc = np.c+dirs[i][1];
					if(isIn(nr, nc)&& game[nr][nc]=='.'&&!visited[nr][nc]) {
					queue.offer(new Point(nr, nc));
					visited[nr][nc]=true;					
					}
				}						
			}
		}
		return 1;
	}
	
	private static boolean isIn(int r,int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	
	private static class Point{
		int r,c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	private static String src ="2\n" + 
			"3\n" + 
			"..*\n" + 
			"..*\n" + 
			"**.\n" + 
			"5\n" + 
			"..*..\n" + 
			"..*..\n" + 
			".*..*\n" + 
			".*...\n" + 
			".*...";
}
