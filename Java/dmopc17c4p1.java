import java.util.Random;
import java.io.*;
import java.util.*;
public class dmopc17c4p1 {
    public static void main(String[] args) throws IOException {
        int N, Q, a, b, s = 0, blue = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        int[] diff = new int[N + 1];
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            diff[a]++;
            diff[b]--;
        }
        for (int i = 0; i < N; i++) {
            s += diff[i];
            if (s > 0) {
                blue++;
            }
        }
        System.out.println((N - blue) + " " + blue);
    }
}
