package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/*
 * @author herjieun
 * 알고리즘 잘하려면 ?,,
 * -프로그램 언어 문법
 * -IDE 이클립스 (자동완성, 디버깅, 템플릿) 
 * -독해 능력 (문제분석) 
 * -코드 분석 
 * 
 * -최적화 : 입출력, 변수, 메서드
 * -java : 가독성, 주석, 재활용 , 구조화 
 * <--> 알고리즘 : 시간 / 공s
 * 
 */



public class SWEA_3378_스타일리쉬들여쓰기 {

	static int p;
	static int q;
	static int[][]resultMaster;
	static int[][]resultMy;
	
	static int dotCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		
		//br= new BufferedReader(new StringReader(src));
		
		StringTokenizer st = new StringTokenizer(br.readLine());	
		
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <=TC; t++) {
			sb.append("#").append(t).append(" ").append("0 "
					+ "");
			st = new StringTokenizer(br.readLine()," ");
			p = Integer.parseInt(st.nextToken()); //마스터의 코드 줄수 
			q = Integer.parseInt(st.nextToken()); //나의 코드 줄수 
			resultMaster = new int[p][4];
			resultMy = new int[q][4];
			
			for (int i = 0; i <p; i++) {
				String temp =br.readLine();
				int index=0;
				while(temp.charAt(index++)=='.') {
					resultMaster[i][0]++;
				}
				if(i>0) {
					resultMaster[i][1]=resultMaster[i-1][1];
					resultMaster[i][2]=resultMaster[i-1][2];
					resultMaster[i][3]=resultMaster[i-1][3];
				}
	
							
				for (int j = 0; j < temp.length(); j++) {
					switch (temp.charAt(j)) {
					case '(': resultMaster[i][1]++;break;
					case ')': resultMaster[i][1]--;break;
					case '{': resultMaster[i][2]++;break;
					case '}': resultMaster[i][2]--;break;
					case '[': resultMaster[i][3]++;break;
					case ']': resultMaster[i][3]--;break;
					}
				}

			}//마스터의 스타일리쉬코드 분석 for문 

			
			
			for (int i = 0; i <q; i++) {
				String temp =br.readLine();	
				resultMy[i][0]=-100;
				if(i>0) {
					resultMy[i][1]=resultMy[i-1][1];
					resultMy[i][2]=resultMy[i-1][2];
					resultMy[i][3]=resultMy[i-1][3];
				}
				
				for (int j = 0; j < temp.length(); j++) {
					switch (temp.charAt(j)) {
					case '(': resultMy[i][1]++;break;
					case ')': resultMy[i][1]--;break;
					case '{': resultMy[i][2]++;break;
					case '}': resultMy[i][2]--;break;
					case '[': resultMy[i][3]++;break;
					case ']': resultMy[i][3]--;break;
					}
				}
			}//내코드 분석 for문
			
			//answer
			for (int R = 1; R <=20; R++) {
				for (int C = 1; C <=20; C++) {
					for (int S = 1; S <=20; S++) {
						if(check(R,C,S)) {
							cal(R,C,S);
						}
						
					}
				}
			}
			for (int i = 1; i < resultMy.length; i++) {
				sb.append(resultMy[i][0]+" ");
			}
			sb.append("\n");
		}//end of testCase
		System.out.print(sb);
	}//end of main
	
	private static void cal(int R, int C, int S) {
		for (int i = 1; i < resultMy.length; i++) {
			if (resultMy[i][0]==-100) {
				resultMy[i][0]= (resultMy[i-1][1]*R)+(resultMy[i-1][2]*C)+(resultMy[i-1][3]*S);
				}
			else if(resultMy[i][0]!=resultMy[i-1][1]*R+resultMy[i-1][2]*C+resultMy[i-1][3]*S) {
				resultMy[i][0]=-1;
			}
		}	
	}

	private static boolean check(int R, int C, int S) {
		for (int i = 1; i < resultMaster.length; i++) {
			if(resultMaster[i][0]!=resultMaster[i-1][1]*R+resultMaster[i-1][2]*C+resultMaster[i-1][3]*S) return false;
		}
		return true;
	}

}//end of class
