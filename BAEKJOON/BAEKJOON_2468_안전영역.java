package BAEKJOON;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BAEKJOON_2468_안전영역 {

	static boolean[][] visited;
	static int arrayLength;
	static int[][] array;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		arrayLength = scanner.nextInt();
		array = new int[arrayLength][arrayLength];
		int limit = 0;
		int max =Integer.MIN_VALUE;

		for (int row = 0; row < array.length; row++) {
			for (int col = 0; col < array.length; col++) {
				array[row][col] = scanner.nextInt();
				if (array[row][col] > limit) {
					limit = array[row][col];
				}
			}
		}

		// 최대 안전영역 구하기
		for (int i = 0; i < limit; i++) {
			visited = new boolean[arrayLength][arrayLength];
			int areaCnt = 0;
			for (int row = 0; row < array.length; row++) {
				for (int col = 0; col < array.length; col++) {
					if (array[row][col] > i && !visited[row][col]) {
						areaCnt+= bfs(row, col, i);
					}
				}
			}
			if(areaCnt>max) max = areaCnt;
		}
		System.out.println(max);

	}

	public static int bfs(int r, int c, int limit) {
		int dir[][] = { { -1, 0 },  { 0, 1 }, { 1, 0 }, { 0, -1 }};
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(r, c));
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			Point point = queue.poll();

			for (int i = 0; i < dir.length; i++) {
				int newR = point.r + dir[i][0];
				int newC = point.c + dir[i][1];
				if (isIn(newR, newC) && array[newR][newC] > limit && !visited[newR][newC]) {
					queue.offer(new Point(newR, newC));
					visited[newR][newC] = true;
					
				}

			}
		}
		return 1;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < arrayLength && c >= 0 && c < arrayLength;
	}

}

class Point {
	int r;
	int c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}