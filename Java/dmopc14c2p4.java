
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class dmopc14c2p4 {
    public static void main(String[] args) throws IOException {
        int N, Q,a,b;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        int[] psa = new int[N+1];
        for (int i = 1; i < N+1; i++)
            arr[i] = Integer.parseInt(br.readLine());
        psa[0] = 0;
        for (int i = 1; i < N+1;i++)
            psa[i] = psa[i-1] + arr[i];
        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            System.out.println(psa[b+1] - psa[a]);
        }
    }
}