package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {

	static int N;
	static int C;
	static int R;
	static int[][] map;
	static int brickCnt;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			brickCnt = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());

			map = new int[R][C];
			for (int i = 0; i <R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j <C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]!=0) brickCnt++;
				}
			}

			answer = brickCnt;			
			//구슬 하나씩 떨어뜨리자
			//좌표가 달라져야 한다, 중복순열로 좌표 고르자
			//벽돌의 개수도 전달, map전달
			dropMarble(N,brickCnt,map);
			System.out.println("#"+t+" "+(brickCnt-answer));			
		}

	}
	private static void dropMarble(int N, int brickCnt, int[][] map) {
		if(N==0) {
			//최소값 찾기
			answer = Math.min(answer, brickCnt);
			return;
		}
		for (int c = 0; c <C; c++) {
			//1. 맵복제 
			int [][] cloned = cloneMap(map);
			//2.해당 컬럼의 맨 처음 벽돌 가져오기
			Position first = null;
			for (int r = 0; r <R; r++) {
				if(cloned[r][c]!=0) {
					first = new Position(r, c, cloned[r][c]);
					break;
				}
			}
			//2.1 null --> continue;
			if(first==null)continue;
			//3. 구슬을 떨어뜨려서 벽돌을 깬다. --> 깨진 벽돌갯수?
			int broken =crash(first, cloned);
			//3-1.남은 벽돌 0? 게임종료 정답은 0; , 종료
			if(broken >=brickCnt) {
				answer=0;
				return;
			}
			//4. 화면 정리 
			cleanMap(cloned);
			
			//5. 다음 샷 발사 
			dropMarble(N-1, brickCnt-broken, map);
		}
		
	}



	private static void cleanMap(int[][] map) {
		for (int i = 0; i <C; i++) {
			for (int j = R-1, nr=R-1; j>=0; j--) {
				if(map[i][j]!=0) {
					int temp = map[i][j];
					map[i][j]=0;
					map[nr--][j]=temp;
				}
			}
		}
		
	}
	private static int crash(Position first, int[][] map) {
		int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		int broken=0;
		map[first.r][first.c]=0;
		broken++;
		
		for (int i = 1; i<first.d; i++) {
			for (int j = 0; j < dirs.length; j++) {
				int nr = first.r + (dirs[j][0]) * i;
				int nc = first.c + (dirs[j][1]) * i;
				
				if (isIn(nr, nc) && map[nr][nc]!=0) {
					broken+=crash(new Position(nr, nc, map[nr][nc]), map);
				}
			}
		}
		return broken;
		
		
		
	}
	private static int[][] cloneMap(int[][] map) {
		int [][]temp = new int[R][C];
		for (int i = 0; i <R; i++) {
			temp[i]=map[i].clone(); //deepCopy ->내용 복사 
		}
		return temp;
	}
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	private static class Position {
		int r, c, d;

		public Position(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	private static String src = "2\n" + 
						"3 10 10\n" + 
						"0 0 0 0 0 0 0 0 0 0\n" + 
						"1 0 1 0 1 0 0 0 0 0\n" + 
						"1 0 3 0 1 1 0 0 0 1\n" + 
						"1 1 1 0 1 2 0 0 0 9\n" + 
						"1 1 4 0 1 1 0 0 1 1\n" + 
						"1 1 4 1 1 1 2 1 1 1\n" + 
						"1 1 5 1 1 1 1 2 1 1\n" + 
						"1 1 6 1 1 1 1 1 2 1\n" + 
						"1 1 1 1 1 1 1 1 1 5\n" + 
						"1 1 7 1 1 1 1 1 1 1\n" + 
						"2 9 10\n" + 
						"0 0 0 0 0 0 0 0 0\n" + 
						"0 0 0 0 0 0 0 0 0\n" + 
						"0 1 0 0 0 0 0 0 0\n" + 
						"0 1 0 0 0 0 0 0 0\n" + 
						"1 1 0 0 1 0 0 0 0\n" + 
						"1 1 0 1 1 1 0 1 0\n" + 
						"1 1 0 1 1 1 0 1 0\n" + 
						"1 1 1 1 1 1 1 1 0\n" + 
						"1 1 3 1 6 1 1 1 1\n" + 
						"1 1 1 1 1 1 1 1 1";
}
