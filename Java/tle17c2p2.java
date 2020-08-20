import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class tle17c2p2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K, N, cf;
        K = Integer.parseInt(st.nextToken());
        int[] arr = new int[1000001];
        int[] psa = new int[1000001];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++){
            arr[Integer.parseInt(st.nextToken())] = 1;
        }
        psa[0] = 0;
        for (int i = 1; i < 1000001; i++){
            psa[i] = psa[i-1] + arr[i];
        }
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            cf = Integer.parseInt(st.nextToken());
            System.out.println(cf-psa[cf]);
        }
    }
}