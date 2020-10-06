package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_6109_추억의2048게임 {

	static int N;
	static List<Integer>[] result;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append("\n");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 배열크기
			String dir = st.nextToken(); // 이동 방향

			int[][] board = new int[N][N];
			
			result = new ArrayList[N];
			for (int i = 0; i < result.length; i++) {
				result[i] = new ArrayList<Integer>();
			}
			for (int i = 0; i < board.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < board.length; j++) {
					int num =Integer.parseInt(st.nextToken());
					//board[i][j] = Integer.parseInt(st.nextToken());
					if (num!=0) {
						if (dir.equals("up") || dir.equals("down")) {
								result[j].add(num);
						} else {							
								result[i].add(num);
						}
					}					
				}
			}
		
			if (dir.equals("down") || dir.equals("right")) {
				for (int i = 0; i < result.length; i++) {
					int end=result[i].size()-2;
					int size = result[i].size();
					for (int j = 0; j <=end; j++) {
						if (result[i].get(result[i].size()-1).equals(result[i].get(result[i].size()-2))) {
							int temp = result[i].get(result[i].size()-1) * 2;
							result[i].remove(result[i].size()-1);
							result[i].remove(result[i].size()-1);
							result[i].add(0,temp);
							j++;
							
						} else {	
							result[i].add(0,result[i].get(result[i].size()-1));
							result[i].remove(result[i].size()-1);
						}
						if(j==end) {
							result[i].add(0,result[i].get(result[i].size()-1));
							result[i].remove(result[i].size()-1);
							break;

						}
					}
					size = result[i].size();
					for (int j = 0; j < N-(size); j++) {
						result[i].add(0,0);
					}
				}
				
			} else {
				for (int i = 0; i < result.length; i++) {
					int end=result[i].size()-2;
					for (int j = 0; j <=end; j++) {
						if (result[i].get(0).equals(result[i].get(1))) {
							int temp = result[i].get(0) * 2;
							result[i].remove(0);
							result[i].remove(0);
							result[i].add(temp);
							j++;
							
						} else {	
							result[i].add(result[i].get(0));
							result[i].remove(0);
						}
						if(j==end) {
							result[i].add(result[i].get(0));
							result[i].remove(0);
							break;

						}
					}	
					int size = result[i].size();
					for (int j = 0; j < N-(size); j++) {
						result[i].add(0);
					}
				}
				
			}
			
					
			print(dir);

		}
		System.out.println(sb);
	}
	private static void print(String dir) {
		if(dir.equals("up")) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(result[j].get(i)).append(" ");
				}sb.append("\n");
			}
		}else if(dir.equals("down")) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(result[j].get(i)).append(" ");
				}sb.append("\n");
			}			
		}else if(dir.equals("right")) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(result[i].get(j)).append(" ");
				}sb.append("\n");
		}
			
		}else {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(result[i].get(j)).append(" ");
				}sb.append("\n");
			}
		}
		
	}

	private static String src = "1\n" + 
			"5 right\n" + 
			"1024 1024 2 4 0\n" + 
			"128 4 2 0 8\n" + 
			"8 0 2 4 4\n" + 
			"2 2 2 2 8\n" + 
			"0 2 2 0 0";
}
