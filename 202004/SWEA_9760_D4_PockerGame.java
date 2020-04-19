package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SWEA_9760_D4_PockerGame {
	static String[] result = { "Straight Flush", "Four of a Kind", "Full House", "Flush", "Straight", "Three of a kind",
			"Two pair", "One pair", "High card" };
	static info[] cards;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");
			cards = new info[5];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 5; i++) {
				String info = st.nextToken();
				int value = 0;
				switch (info.charAt(1)) {
				case 'A':
					value = 1;
					break;
				case 'T':
					value = 10;
					break;
				case 'J':
					value = 11;
					break;
				case 'Q':
					value = 12;
					break;
				case 'K':
					value = 13;
					
					break;
				default:
					value = info.charAt(1) - '0';
					break;
				}
				cards[i] = new info(info.charAt(0), value);
			}
			Arrays.sort(cards, new Comparator<info>() {

				@Override
				public int compare(info o1, info o2) {
					Integer val1 = o1.value;
					Integer val2 = o2.value;
					return val1.compareTo(val2);
				}
			});

			sb.append(result[findScore()]).append("\n");

		}
		System.out.println(sb);

	}

	private static int findScore() {

		// 스트레이트 플러쉬랑 스트레이트 판별
		boolean straightValueFlag = true;
		boolean allSameFlag = true;
		
		int[] check = new int[13+1];
		
		for (int i = 0; i < cards.length; i++) {
			check[cards[i].value]++;
			if (allSameFlag&& straightValueFlag&& i != cards.length - 1) {
				if (cards[i].figure != cards[i + 1].figure)
					allSameFlag = false;
				if (cards[i].value + 1 != cards[i + 1].value) {
					straightValueFlag = false;
				}
			}

		}
		if(check[1]>0 &&check[10]>0 &&check[11]>0 &&check[12]>0 &&check[13]>0) return 0;

		if (straightValueFlag) {
			if (allSameFlag)
				return 0;
			if(!allSameFlag)
				return 4;
		}
		boolean fourFlag = false;
		boolean threeFlag = false;
		boolean twoFlag1 = false;
		boolean twoFlag2 = false;
		for (int i = 1; i < check.length; i++) {
			if (check[i] == 4) {
				return 1;
			} else if (check[i] == 3) {
				threeFlag = true;
			} else if (check[i] == 2) {
				if (twoFlag1)
					twoFlag2 = true;
				else
					twoFlag1 = true;
			}
		}
		if (threeFlag && twoFlag1)
			return 2;
		else if (allSameFlag)
			return 3;
		else if (threeFlag && !twoFlag1)
			return 5;
		else if (twoFlag1 && twoFlag2)
			return 6;
		else if (twoFlag1)
			return 7;

		return 8;
	}

	private static class info {
		char figure;
		int value;

		public info(char figure, int value) {
			super();
			this.figure = figure;
			this.value = value;
		}

		
		
	}

	private static String src = "1\n" +  
			"CQ CJ CT C9 C8";

}
