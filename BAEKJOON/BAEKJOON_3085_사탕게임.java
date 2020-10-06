package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON_3085_사탕게임 {

	static int N;
	static char[][] board;
	static boolean[][] visited;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		board = new char[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				eatCandyColUp(i, j, 0, 1,i,j);
				eatCandyColDown(i, j, 0, 1,i,j);
				eatCandyRowUp(i, j, 0, 1,i,j);
				eatCandyRowDown(i, j, 0, 1,i,j);
			}
		}

		System.out.println(max);

	}

	private static void eatCandyRowUp(int r, int c, int changeCnt, int candyCnt,int xr,int xc) {

		if(r+1>=N) {
			max = Math.max(max, candyCnt);
			return;
		}
		else if (changeCnt == 1) {
			if (board[r + 1][c] != board[xr][xc]) {
				max = Math.max(max, candyCnt);
				return;
			} else if(board[r + 1][c] == board[xr][xc]){				
				eatCandyRowUp(r + 1, c, changeCnt, candyCnt + 1,r+1,c);
			}
		}

		else if (changeCnt == 0 &&r+1<N) {
			if (board[xr][xc] == board[r + 1][c]) {
				eatCandyRowUp(r + 1, c, changeCnt, candyCnt + 1, r+1,c);
			}

			else {
				if (c+1<N &&board[r + 1][c + 1] == board[xr][xc]) {
					eatCandyRowUp(r + 1, c, changeCnt + 1, candyCnt + 1,r+1,c+1);
				}
				else if (c-1>=0 && board[r+1][c-1] == board[xr][xc]) {
					eatCandyRowUp(r + 1, c, changeCnt + 1, candyCnt + 1,r+1,c-1);
					
				}
				else {
					max = Math.max(max, candyCnt);
					return;
				}
			}
		}

	}
	
	private static void eatCandyRowDown(int r, int c, int changeCnt, int candyCnt,int xr,int xc) {
		if(r-1<0) {
			max = Math.max(max, candyCnt);
			return;
		}
		else if (changeCnt == 1) {
			if (board[r -1][c] != board[xr][xc]) {
				max = Math.max(max, candyCnt);
				return;
			} else if(board[r - 1][c] == board[xr][xc]){				
				eatCandyRowDown(r - 1, c, changeCnt, candyCnt + 1,r-1,c);
			}
		}

		else if (changeCnt == 0) {
			if (board[xr][xc] == board[r-1][c]) {
				eatCandyRowDown(r - 1, c, changeCnt, candyCnt + 1, r-1,c);
			}

			else {
				if (c+1<N &&board[r -1][c + 1] == board[xr][xc]) {
					eatCandyRowDown(r - 1, c, changeCnt + 1, candyCnt + 1,r-1,c+1);
				}
				else if (c-1>=0 && board[r-1][c-1] == board[xr][xc]) {
					eatCandyRowDown(r - 1, c, changeCnt + 1, candyCnt + 1,r-1,c-1);	
				}	
				else {
					max = Math.max(max, candyCnt);
					return;
				}
			}
		}

	}

private static void eatCandyColUp(int r, int c, int changeCnt, int candyCnt,int xr,int xc) {
		if(c+1>=N) {
			max = Math.max(max, candyCnt);
			return;
		}
		else if (changeCnt == 1) {
			if (board[r][c+1] != board[xr][xc]) {
				max = Math.max(max, candyCnt);
				return;
			} else if(board[r][c+1] == board[xr][xc]){
				eatCandyColUp(r, c+1, changeCnt, candyCnt + 1,r,c+1);
			}
		}

		else if (changeCnt == 0 && c+1<N) {
			if (board[xr][xc] == board[r][c+1]) {
				eatCandyColUp(r, c+1, changeCnt, candyCnt + 1,r,c+1);
			}

			else {
				if (r+1<N &&board[r + 1][c + 1] == board[xr][xc]) {
					eatCandyColUp(r, c+1, changeCnt + 1, candyCnt + 1,r+1,c+1);
				}
				else if (r-1>=0 && board[r-1][c+1] == board[xr][xc]) {
					eatCandyColUp(r, c+1, changeCnt + 1, candyCnt + 1,r-1,c+1);
				}
				else {
					max = Math.max(max, candyCnt);
					return;
				}
			}
		}

	}

private static void eatCandyColDown(int r, int c, int changeCnt, int candyCnt,int xr,int xc) {
	if(c-1<0) {
		max = Math.max(max, candyCnt);
		return;
	}
	else if (changeCnt == 1) {
		if (board[r][c-1] != board[xr][xc]) {
			max = Math.max(max, candyCnt);
			return;
		} else if(board[r][c-1] == board[xr][xc]){
			eatCandyColDown(r, c-1, changeCnt, candyCnt + 1,r,c-1);
		}
	}

	else if (changeCnt == 0) {
		if (board[xr][xc] == board[r][c-1]) {
			eatCandyColDown(r, c-1, changeCnt, candyCnt + 1,r,c-1);
		}

		else {
			if (r+1<N && board[r + 1][c - 1] == board[xr][xc]) {
				eatCandyColDown(r, c-1, changeCnt + 1, candyCnt + 1,r+1,c-1);
			}
			else if (r-1>=0 && board[r-1][c-1] == board[xr][xc]) {
				eatCandyColDown(r, c-1, changeCnt + 1, candyCnt + 1,r-1,c-1);
			}
			else {
				max = Math.max(max, candyCnt);
				return;
			}
		}
	}

}
	
private static String src = "3\n" + 
			"CPZ\n" + 
			"ZYC\n" + 
			"CPZ\n";
}
