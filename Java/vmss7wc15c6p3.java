import java.util.*;
import java.io.*;
public class vmss7wc15c6p3 {
    public static void main(String[] args) throws IOException{
        ArrayList<ArrayList<Integer>> bfs = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++){
            bfs.add(new ArrayList<>());
        }
        for (int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            bfs.get(Integer.parseInt(st.nextToken())-1).add(Integer.parseInt(st.nextToken())-1);
        }
        st = new StringTokenizer(br.readLine());
        int[] w = new int[N];
        for (int i = 0; i < N; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Queue<Integer> q;
        int cur, max = Integer.MIN_VALUE, v1, v2, cur2;
        ArrayList<Integer> curarr;
        for (int i = N-1; i >= 0; i--){
            cur = w[i];
            q = new LinkedList<>();
            q.add(i);
            while (!q.isEmpty()) {
                v1 = q.poll();
                curarr = bfs.get(v1);
                for (int j = 0; j < curarr.size(); j++) {
                    v2 = curarr.get(j);
                    if (dp[v2] != Integer.MAX_VALUE){
                        cur+=dp[v2];
                    }
                    else {
                        q.add(v2);
                        cur += w[v2];
                    }
                }
            }
            dp[i] = cur;
            if (cur > max){
                max = cur;
            }
        }
        System.out.println(max);
    }
}

