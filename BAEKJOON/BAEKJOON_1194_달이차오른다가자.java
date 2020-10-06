package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON_1194_달이차오른다가자 {

	static int R, C;
	static Character[][] map;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken()); // 세로
		C = Integer.parseInt(st.nextToken()); // 가로

		map = new Character[R][C];
		Position start = null;
		for (int i = 0; i < R; i++) {
			String temp=br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp.charAt(j);
				if (map[i][j] == '0') {
					start = new Position(i, j, 0,null,null);
				}
			}
		} // 입력완료
		for (int i = 0; i < R; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		boolean [][]visited = new boolean[R][C];
		List<Character> keys = new ArrayList<Character>();

		bfs(start.r, start.c, visited, keys, 0);
		if (min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else
			System.out.println(min);

	}

	static int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static void bfs(int r, int c,boolean[][]visited, List<Character>keys,int length) {
		/*
		 * 		빈 곳 : 언제나 이동할 수 있다. ('.‘로 표시됨) 
		 * 		벽 : 절대 이동할 수 없다. (‘#’) 
		 * 열쇠 : 언제나 이동할 수 있다. 이 곳에
		 * 처음 들어가면 열쇠를 집는다. (a - f)
		 *  문 : 대응하는 열쇠가 있을 때만 이동할 수 있다. (A - F) 
		 *  	민식이의 현재 위치 : 빈곳이고, 민식이가 현재 서 있는 곳이다. (숫자 0) 
		 *  	출구 : 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를
		 * 		탈출한다. (숫자 1)
		 */
		if (map[r][c]=='1') {
			 min=Math.min(min, length);
			 return;
		}else {
			for (int i = 0; i < dirs.length; i++) {
				int nr = r+dirs[i][0];
				int nc = c+dirs[i][1];
				if (isIn(nr, nc) && !visited[nr][nc]) {
					if (map[nr][nc]=='#') {
						continue;
					}else if(map[nr][nc]=='a'||map[nr][nc]=='b'||map[nr][nc]=='c'||map[nr][nc]=='d'||map[nr][nc]=='e'||map[nr][nc]=='f') {
						keys.add(map[nr][nc]);
						visited[nr][nc]=true;
						bfs(nr, nc, visited, keys, length+1);
						visited[nr][nc]=false;
						keys.remove(map[nr][nc]);
						
					}else if(map[nr][nc]=='A'||map[nr][nc]=='B'||map[nr][nc]=='C'||map[nr][nc]=='D'||map[nr][nc]=='E'||map[nr][nc]=='F') {
						char temp =map[nr][nc].toString().toLowerCase().charAt(0);
						if (keys.contains(temp)) {
							keys.remove(keys.indexOf(temp));
							visited[nr][nc]=true;
							bfs(nr, nc, visited, keys, length+1);
							visited[nr][nc]=false;
							keys.add(temp);
						}
					}
					else {
						visited[nr][nc]=true;
						bfs(nr, nc, visited, keys, length+1);
						visited[nr][nc]=false;
					}
				}
			}
		}
		
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	private static class Position {
		int r, c, l;
		List<Character> keys;
		boolean [][]visited;
		
		public Position(int r, int c, int l, List<Character> keys, boolean[][]visited) {
			super();
			this.r = r;
			this.c = c;
			this.l = l;
			this.keys = keys;
			this.visited = visited;
		}		

	}

	private static String src = "7 8\n" + 
			"a#c#eF.1\n" + 
			".#.#.#..\n" + 
			".#B#D###\n" + 
			"0....F.1\n" + 
			"C#E#A###\n" + 
			".#.#.#..\n" + 
			"d#f#bF.1";
}
