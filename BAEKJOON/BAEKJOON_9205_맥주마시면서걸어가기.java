package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON_9205_맥주마시면서걸어가기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			String result="sad";
			st = new StringTokenizer(br.readLine());
			Queue<Position> q = new LinkedList<>();
			// 편의점의 갯
			int storeCnt = Integer.parseInt(st.nextToken());
			Position[] position = new Position [storeCnt+2];
			boolean visited[] = new boolean[storeCnt+2];
			st = new StringTokenizer(br.readLine());
			// 상근이네 좌표
			position[0] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			// 편의점 좌표
			for (int i = 1; i <=storeCnt; i++) {
				st = new StringTokenizer(br.readLine());
				position[i] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			// 락페스티벌 좌표
			st = new StringTokenizer(br.readLine());
			position[position.length-1] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	
			
			Position start = position[0];    //시작 위치
			Position end = position[position.length-1];    //도착 위치
	        q.add(start);    //시작 위치로 부터 출발
	        visited[0]=true;
	        
	        while(!q.isEmpty()) {
	        	Position current = q.poll();
	        	
	        	if(current.equals(end)) {
	        		result ="happy";
	        		break;
	        	}
	        	else {
	        		for (int i = 1; i < position.length; i++) {
						if(!visited[i] &&(Math.abs(current.x-position[i].x)+Math.abs(current.y-position[i].y))<=1000) {
							q.offer(position[i]);
							visited[i]=true;
						}
					}
	        	}
	        }
			
			System.out.println(result);			
		}

	}
	
	static class Position{
		int x,y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
		
	}


	private static String src = "2\n" + "2\n" + "0 0\n" + "1000 0\n" + "1000 1000\n" + "2000 1000\n" + "2\n" + "0 0\n"
			+ "1000 0\n" + "2000 1000\n" + "2000 2000";
}
