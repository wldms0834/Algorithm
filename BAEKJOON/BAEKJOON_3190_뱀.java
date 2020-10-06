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

public class BAEKJOON_3190_뱀 {

	static int boardLength;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		boardLength = Integer.parseInt(st.nextToken());
		
		//게임 보드 
		boolean [][]board = new boolean[boardLength+1][boardLength+1];
		boolean [][]snake = new boolean[boardLength+1][boardLength+1];
		st = new StringTokenizer(br.readLine());
		//보드에 있는 사과갯수 
		int appleCnt = Integer.parseInt(st.nextToken());
		//보드 안에 사과 놓기 
		for (int i = 0; i < appleCnt; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]=true;
		}
		//방향 지시 갯
		st = new StringTokenizer(br.readLine());
		int dirCnt = Integer.parseInt(st.nextToken());

		//l은 왼쪽 ,d는 오른
		int [][]changeDir = {{0,1},{1,0},{0,-1},{-1,0}};
		int dirIndex=0;
		int startX=1;
		int startY=1;
		int endX=1;
		int endY=1;
		int currentTime=0;
		int moveCnt=0;	
		snake[startX][startY]=true;
		Queue<position> snakeQueue = new LinkedList<>();
		//첫위치 넣기 
		snakeQueue.add(new position(startX,startY));
		
		for (int i = 0; i <dirCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < time-currentTime; j++) {
				startX+=changeDir[dirIndex][0];
				startY+=changeDir[dirIndex][1];
				position np = new position(startX,startY);
				moveCnt++;
				
				if (!isIn(startX, startY) || snake[startX][startY]==true){
					System.out.println(moveCnt);
					return;
				}
				
				else {
					snakeQueue.add(np);
					snake[startX][startY]=true;
					if (board[startX][startY]==true) {
						board[startX][startY]=false;
					}else {
						position end=snakeQueue.poll();
						snake[end.r][end.c]=false;
					}
				}
			}
			if(st.nextToken().charAt(0)=='D') {
				dirIndex++;
				if(dirIndex==4) dirIndex=0;
			}
			else {
				dirIndex--;
				if(dirIndex==-1) dirIndex=3;
			}
			currentTime=time;
		}
		
		while(true) {
			startX+=changeDir[dirIndex][0];
			startY+=changeDir[dirIndex][1];
			position np = new position(startX,startY);
			moveCnt++;
			
			if(!isIn(startX, startY) || snake[startX][startY]==true) {
				break;
			}
			snakeQueue.add(np);
			snake[startX][startY]=true;
			if (board[startX][startY]==true) {
				board[startX][startY]=false;
			}else {
				position end=snakeQueue.poll();
				snake[end.r][end.c]=false;
			}
			
		}		
		System.out.println(moveCnt);

	}
	
	static class position {
		int r;
		int c;

		public position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		
	}
	
	static boolean isIn(int r, int c) {
		return r >=1 && r <= boardLength && c >=1 && c <= boardLength;
	}
}