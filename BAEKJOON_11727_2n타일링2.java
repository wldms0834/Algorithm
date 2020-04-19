package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BAEKJOON_11727_2n타일링2 {
   static int num = 10007;
   public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      int[] memo = new int[1001];
      memo[0] = 0;
      memo[1] = 1;
      memo[2] = 3;
      
      for(int i = 3; i <=N;i++) {
         memo[i] = (memo[i-1] + (2*memo[i-2]))%num;
      }
      System.out.println(memo[N]);
      
   }
}