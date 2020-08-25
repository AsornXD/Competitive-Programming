import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dpc {
    static int N;
    static int[][] e;
    static long[][][] dparr;
    static long dp(int n, long t, int c){
        if (n == N-1){
            return t;
        }
        if (c == 0){
             return Math.max(dp(n+1, t + e[n][1], 1), dp(n+1, t + e[n][2], 2));
         }
        else if (c == 1){
            return Math.max(dp(n+1, t + e[n][0], 0), dp(n+1, t + e[n][2], 2));
        }
        else if (c == 2){
            return Math.max(dp(n+1, t + e[n][0], 0), dp(n+1, t + e[n][1], 1));
        }
        else{
            return Math.max(Math.max(dp(n+1, t + e[n][0], 0), dp(n+1, t + e[n][1], 1)), dp(n+1, t + e[n][2], 2));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        e = new int[N][3];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            e[i][0] = Integer.parseInt(st.nextToken()); e[i][1] = Integer.parseInt(st.nextToken()); e[i][2] = Integer.parseInt(st.nextToken());
        }
        System.out.println(dp(0, 0, -1));
    }
}
