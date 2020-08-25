import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class dpa {
    static int[] dp;
    static int[] h;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        dp = new int[111010];
        h = new int[111010];
        for (int i = 1; i <= N; i++){
            h[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= K; j++){
                dp[i+j] = Math.min(dp[i+j], dp[i] + Math.abs(h[i] - h[i+j]));
            }
        }
        System.out.println(dp[N]);
    }
}
