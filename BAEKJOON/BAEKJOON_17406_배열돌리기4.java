package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.transform.Templates;

public class BAEKJOON_17406_배열돌리기4 {

	static int N;
	static int M;
	static int K;
	static int[][]numbers;
	static int[][]rotationOrder;
	static boolean []checked;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //행 개수  
		M = Integer.parseInt(st.nextToken()); //열 개수 
		K = Integer.parseInt(st.nextToken()); //회전 연산 개수 
		max = Integer.MIN_VALUE;
		
		numbers = new int[N+1][M+1];
		for (int i = 1; i < numbers.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <=numbers.length; j++) {
				numbers[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//회전연산 개수 입력받기 (가장위칸 = (r-s, c-s), 가장 아랫칸 =(r+s, c+s))
		rotationOrder = new int[K][3];
		checked = new boolean[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			rotationOrder[i][0]=Integer.parseInt(st.nextToken()); //r 
			rotationOrder[i][1]=Integer.parseInt(st.nextToken()); //c 
			rotationOrder[i][2]=Integer.parseInt(st.nextToken()); //c 			
		}
		//for (int i = 0; i < rotationOrder.length; i++) {
			//checked[i]=true;
			rotate(0, new int[K]);
			//checked[i]=false;
		//}
		System.out.println(max);		
	}
	private static void rotate(int cnt, int[]temp) {
		if(cnt==K) {
			for (int i = 0; i < temp.length; i++) {
				System.out.println(Arrays.toString(temp));
			}
			/*for (int i = 0; i < temp.length; i++) {
				int sum=0;
				for (int j = 0; j < temp.length; j++) {
					sum+=temp[i][j];
				}
				max = Math.max(max, sum);
			}*/
		}else {
		for (int i = 0; i < rotationOrder.length; i++) {
				if(!checked[i]) {
					checked[i]=true;
					temp[cnt]=i;
					rotate(cnt+1,temp);
					checked[i]=false;
				}
			}
		}
	}
	private static int[][] swap(int [][]temp, int index) {
		int r = rotationOrder[index][0];
		int c = rotationOrder[index][1];
		int s = rotationOrder[index][2];
        int value=1;
        int firstStart=c-s;
        int firstEnd=c+s;
        int secondStart=r-s;
        int secondEnd=r+s;
        int tempNum = numbers[r+s][c+s];
        int row = r-s;
        int col=c+s;
        
        while(value<((r+s+1)-(r-s))*((c+s+1)-(c-s))) {
        	if(secondEnd==secondStart && firstEnd==firstStart) break;
        	tempNum=numbers[secondEnd][firstEnd];
        	for (int i = secondEnd; i >secondStart; i--) {
				temp[i][col] = temp[i-1][col];	
				value++;
				row=i-1;
			}
        	for (int i =firstEnd; i >firstStart; i--) {
            	temp[row][i] = temp[row][i-1];	
                value++;
                col =i-1;
            }
        	
            for (int i = secondStart; i <secondEnd; i++) {
            	temp[i][col] = temp[i+1][col];	
				value++;
				row=i+1;
            }
            for (int i =firstStart; i <firstEnd; i++) {
             	temp[row][i] = temp[row][i+1];	
                 value++;
                 col =i+1;
             }
            
            temp[row][col-1]=tempNum;
            firstEnd--;
            firstStart++;
            secondEnd--;
            secondStart++;
    
}
        for (int i = 0; i < temp.length; i++) {
			System.out.println(Arrays.toString(temp[i]));
		}
		return temp;
	}
	private static String src = "5 6 2\n" + 
			"1 2 3 2 5 6\n" + 
			"3 8 7 2 1 3\n" + 
			"8 2 3 1 4 5\n" + 
			"3 4 5 1 1 1\n" + 
			"9 3 2 1 4 3\n" + 
			"3 4 2\n" + 
			"4 2 1";
}
