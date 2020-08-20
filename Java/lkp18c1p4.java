import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class lkp18c1p4{
    static long[] dis;
    static long C;
    static int A, B;
    static ArrayList<e>[] adj;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N + 1];
        for (int i = 0; i < N+1; i++){
            adj[i] = new ArrayList<>();
        }
        int a,b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            adj[a].add(new e(b, c, i+1));
            adj[b].add(new e(a, c,i+1));
        }
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        if (A==B){
            System.out.println(0);
        }
        else {
            C = Long.parseLong(st.nextToken());
            long lo = 1;
            long hi = M;
            long mid = 0;
            long ans = Long.MAX_VALUE;
            while (lo<=hi) {
                mid = (lo+hi)/2;
                if (run(A, mid) < C){
                    ans = mid;
                    hi = mid-1;
                }
                else{
                    lo = mid+1;
                }
            }
            if (ans == Long.MAX_VALUE){
                System.out.println(-1);
            }
            else{
                System.out.println(ans);
            }
        }
    }
    public static long run(int A, long mid){
        dis = new long[N + 1];
        boolean[] vis = new boolean[N+1];
        Arrays.fill(dis, Long.MAX_VALUE);
        dis[A] = 0;
        vis[A] = true;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(A);
        while (!q.isEmpty()){
            int cur = q.poll();
            vis[cur] = false;
            for (e cur2 : adj[cur]){
                if (cur2.t <= mid && dis[cur2.v] > dis[cur] + cur2.c){
                    dis[cur2.v] = dis[cur] + cur2.c;
                    if (!vis[cur2.v]){
                        vis[cur2.v] = true;
                        q.add(cur2.v);
                    }
                    else if (cur2.t > mid){
                        break;
                    }
                }
            }
        }
        return dis[B];
    }
}
class e implements Comparable<e>{
    int v, t;
    long c;
    e(int v1, long c1, int t1){
        v = v1;
        c = c1;
        t = t1;
    }
    public int compareTo(e i){
        return (int) (c - i.c);
    }
}