package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON_16236_아기상어 {
	/*더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
	먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
	먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
	거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
	거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
	아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다. 즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면,
 	이동과 동시에 물고기를 먹는다. 물고기를 먹으면, 그 칸은 빈 칸이 된다.
 	
 	0: 빈 칸
	1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
	9: 아기 상어의 위치
	 * */
	
	static int N;
	static int[][]map;
	static Position babyShark;
	static int sharkSize=2;
	static int sizeIdx=0;
	static int totalTime=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //공간의 크기
		map = new int[N][N]; //공간 생성 
		
		for (int i = 0; i <N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
					if (map[i][j]==9) {
						babyShark = new Position(i,j,0); //아기상어 위치 입력
					}			
			}
		} //맵에 입력 완료
		
		while(true) {	
			if(!findFish(babyShark)) {
				System.out.println(totalTime);
				return;
			}
		}
		
		

	}
	private static boolean findFish(Position babyShark) {
		int [][]dirs= {{0,1},{1,0},{0,-1},{-1,0}};
		boolean [][]visited = new boolean[N][N];
		PriorityQueue<Position> fishQueue = new PriorityQueue<>();
		Queue<Position> disQueue = new LinkedList<>();
		disQueue.offer(babyShark);
		visited[babyShark.r][babyShark.c]=true;
		
		int distanceIdx=0;
		while(!disQueue.isEmpty()) {
			if (disQueue.peek().distance>distanceIdx && !fishQueue.isEmpty()) {
				break;
			}
			Position np = disQueue.poll();
			distanceIdx = np.distance;
			
			for (int i = 0; i < dirs.length; i++) {
				int nr=np.r+dirs[i][0];
				int nc=np.c+dirs[i][1];
				if (isIn(nr, nc) && map[nr][nc]<=sharkSize && !visited[nr][nc]) {
					visited[nr][nc]=true;
					if (map[nr][nc]!=0 && map[nr][nc]<sharkSize) {
						fishQueue.offer(new Position(nr, nc, np.distance+1));
					}
					disQueue.offer(new Position(nr, nc, np.distance+1));
				}
			}			
		}
		if (!fishQueue.isEmpty()) {
			Position eat = fishQueue.poll();
			
			map[eat.r][eat.c]=9;
			map[babyShark.r][babyShark.c]=0;
			sizeIdx++;
			
			if (sizeIdx==sharkSize) {
				sharkSize++;
				sizeIdx=0;
			}
			totalTime+=eat.distance;
			babyShark.r = eat.r;
			babyShark.c = eat.c;
			return true;
		}
		
		return false;
		
	}
	private static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	
	private static class Position implements Comparable<Position>{
		int r, c, distance;

		public Position(int r, int c, int distance) {
			super();
			this.r = r;
			this.c = c;
			this.distance = distance;
		}

		@Override
		public int compareTo(Position o) {
			Integer d1 =this.distance;
			Integer d2 =o.distance;
			
			if (d1.equals(d2)) {
				Integer r1 = this.r;
				Integer r2 = o.r;
				if(r1.equals(r2)) {
					Integer c1 = this.c;
					Integer c2 = o.c;
					return c1.compareTo(c2);
				}
				
				else {
					return  r1.compareTo(r2);
				}
			}else {
				return d1.compareTo(d2);
			}
		}
		
	}
	private static String src="6\n" + 
			"1 1 1 1 1 1\n" + 
			"2 2 6 2 2 3\n" + 
			"2 2 5 2 2 3\n" + 
			"2 2 2 4 6 3\n" + 
			"0 0 0 0 0 6\n" + 
			"0 0 0 0 0 9";
}
