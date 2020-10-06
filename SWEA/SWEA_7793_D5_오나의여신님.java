package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7793_D5_오나의여신님 {

	static char [][]map;
	static int N;
	static int M;
	static Position suyeon;
	static Queue<Position> queueS = new LinkedList<>();
	static Queue<Position> queueD;
	static int finalResult;
	static boolean [][]visited;
	static boolean [][]Dvisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		//StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		//수연이의 위치는 ‘S’, 여신의 공간은 ‘D’, 돌의 위치는 ‘X’, 악마는 ‘*’,‘.’는 평범한 지역 
		//int TC = Integer.parseInt(st.nextToken());
		//for (int t = 1; t <=TC; t++) {
			queueD = new LinkedList<>();
			//sb.append("#").append(t).append(" ");
			//st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char [N][M];
			visited = new boolean[N][M];
			Dvisited = new boolean[N][M];
			for (int i = 0; i <N; i++) {
				String s = br.readLine();
				for (int j = 0; j <M; j++) {
					map[i][j]=s.charAt(j);
					if(map[i][j]=='*') {
						queueD.offer(new Position('d',i, j, 0));
						Dvisited[i][j]=true;
					}
					else if(map[i][j]=='S') {
					suyeon = new Position('s',i, j, 0);
					visited[i][j]=true;
					}
				}
			}
			queueD.offer(suyeon);
			spreadDevil();	
			if(finalResult==-1)System.out.println("KAKTUS");//sb.append("KAKTUS").append("\n");
			else System.out.println(finalResult);//sb.append(finalResult).append("\n");
			
		}//System.out.println(sb);

	//}
		
	private static void spreadDevil() {
		int [][]dirs = {{1,0},{-1,0},{0,1},{0,-1}};
		while(!queueD.isEmpty()) {
			Position np = queueD.poll();
			if(np.index=='d'){
			for (int i = 0; i < dirs.length; i++) {
				int nr = np.r+dirs[i][0];
				int nc = np.c+dirs[i][1];
				if(isIn(nr, nc) && !visited[nr][nc] &&(map[nr][nc]=='.'||visited[nr][nc]==true)) {
					queueD.offer(new Position('d',nr, nc,np.d+1));
					map[nr][nc] ='*';
					Dvisited[nr][nc]=true;
					}
				}
			}
			else if(np.index=='s') {
				for (int i = 0; i < dirs.length; i++) {
					int nr = np.r+dirs[i][0];
					int nc = np.c+dirs[i][1];
					if(isIn(nr, nc) && !visited[nr][nc]) {
						if(map[nr][nc]=='D') {
							finalResult=np.d+1;
							return;
					}else if(map[nr][nc]=='.') {
						queueD.offer(new Position('s',nr, nc, np.d+1));
						visited[nr][nc]=true;
						}
					}
				}	
			}
		}
		finalResult =-1;
		return;
	}
	
	private static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
	
	private static class Position{
		int r,c,d;
		char index;

		public Position(char index, int r, int c, int d) {
			super();
			this.index=index;
			this.r = r;
			this.c = c;
			this.d = d;
		}
		
	}
	private static String src =
			"5 4\n" + 
			".D.*\n" + 
			"....\n" + 
			"..X.\n" + 
			"S.*.\n" + 
			"....";
}
