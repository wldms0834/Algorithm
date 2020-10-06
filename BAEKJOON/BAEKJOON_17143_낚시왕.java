package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class BAEKJOON_17143_낚시왕 {
	static int R;
	static int C;
	static int M;
	static int[][]board;
	static shark[] sharkInfos;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		sharkInfos = new shark[M+1];
		board = new int[R][C];
		int totalSize=0;
		for (int i = 1; i <=M; i++) {
			st = new StringTokenizer(br.readLine());
			int sharkR =Integer.parseInt(st.nextToken())-1;
			int sharkC =Integer.parseInt(st.nextToken())-1;
			int sharkSpeed =Integer.parseInt(st.nextToken());
			int sharkDir =Integer.parseInt(st.nextToken());
			int sharkSize =Integer.parseInt(st.nextToken());
			
			board[sharkR][sharkC]=i;
			sharkInfos[i]=new shark(sharkSpeed, sharkDir, sharkSize, sharkR, sharkC);
			
			
			
		}

		for (int i = 0; i <C; i++) {
			for (int j = 0; j <R; j++) {
				if(board[j][i]!=0) {
					totalSize+=sharkInfos[board[j][i]].size;
					sharkInfos[board[j][i]].speed=-1;
					board[j][i]=0;
					break;
				}
			}
			moveShark();
			
		}

		System.out.println(totalSize);

	}
	private static void moveShark() {
		//d가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽
		int [][]dirs= {{0,0},{-1,0},{1,0},{0,1},{0,-1}};

		for (int i = 1; i < sharkInfos.length; i++) {
					
			if(sharkInfos[i].speed==-1) continue;
			int d =sharkInfos[i].dir;
			int r =sharkInfos[i].r;
			int c =sharkInfos[i].c;
			int s =sharkInfos[i].speed;
			
			board[r][c]=0;
					
				for (int j = 0; j <s; j++) {
					r+=dirs[d][0];
					c+=dirs[d][1];
										
					if(r<0) {
						d=2;
						r=1;
					}
					else if(r>=R) {
						d=1;
						r=R-2;
						
					}
					else if(c<0) {
						d=3;
						c=1;
					}
					else if(c>=C) {
						d=4;
						c=C-2;
					}
			}
			sharkInfos[i].dir=d;
			sharkInfos[i].r=r;
			sharkInfos[i].c=c;
			
		}

		for (int i = 1; i < sharkInfos.length; i++) {
			if(sharkInfos[i].speed==-1) continue;
			 if(board[sharkInfos[i].r][sharkInfos[i].c]!=0 && board[sharkInfos[i].r][sharkInfos[i].c]<i) {
					int original = board[sharkInfos[i].r][sharkInfos[i].c];
					
					if(sharkInfos[original].size<sharkInfos[i].size) {
						board[sharkInfos[i].r][sharkInfos[i].c]=i;
						sharkInfos[original].speed=-1;
					}else {
						sharkInfos[i].speed=-1;
					}
				}
				else {
					board[sharkInfos[i].r][sharkInfos[i].c]=i;
				}
		}
		
		
	}
	
	static class shark {
		int speed,dir,size,r,c;

		public shark(int speed, int dir, int size,int r,int c) {
			super();
			this.speed = speed;
			this.dir = dir;
			this.size = size;
			this.r=r;
			this.c=c;
		}
		
		
	}
	private static String src="2 2 4\n" + 
			"1 1 1 1 1\n" + 
			"2 2 2 2 2\n" + 
			"1 2 1 2 3\n" + 
			"2 1 2 1 4";
}
