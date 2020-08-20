import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class ccc15s4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        ArrayList<node>[] adj = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++){
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken());
            adj[a].add(new node(b, t, h));
            adj[b].add(new node(a, t, h));
        }
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
        PriorityQueue<node> pq = new PriorityQueue<>();
        int[][] dis = new int[N+1][K];
        for (int i = 0; i < N+1; i++){
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        boolean[] vis = new boolean[N+1];
        pq.add(new node(A, 0, 0));
        vis[A] = true;
        dis[A][0] = 0;
        while (!pq.isEmpty()){
            node cur = pq.poll();
            vis[cur.v] = false;
            for (node cur2 : adj[cur.v]){
                for (int i = 0; i < K; i++){
                    if (dis[cur.v][i] != Integer.MAX_VALUE && cur2.c + i < K && dis[cur2.v][cur2.c + i] > dis[cur.v][i] + cur2.w){
                        dis[cur2.v][cur2.c + i] = dis[cur.v][i] + cur2.w;
                        if (!vis[cur2.v]){
                            vis[cur2.v] = true;
                            pq.add(cur2);
                        }
                    }
                }
            }
        }
        int min = dis[B][0];
        for (int i = 1; i < K; i++){
            if (dis[B][i] < min){
                min = dis[B][i];
            }
        }
        if (min == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else {
            System.out.println(min);
        }
    }
}
class node implements Comparable<node>{
    int v, w, c;
    node (int v, int w, int c){
        this.v = v;
        this.w = w;
        this.c = c;
    }

    @Override
    public int compareTo(node o) {
        return w - o.w;
    }
}