import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class cco11p2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
        ArrayList<node>[] adj = new ArrayList[N];
        for (int i = 0; i < N; i++){
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken()), u = Integer.parseInt(st.nextToken());
            if (u == 1){
                adj[s].add(new node(t, d, d));
                adj[t].add(new node(s, d, d));
            }
            else if (u == 0){
                adj[s].add(new node(t, d, 0));
                adj[t].add(new node(s, d, 0));
            }
        }
        int[][] dis = new int[N+1][S+1];
        for (int i = 0; i < N; i++){
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        PriorityQueue<node> pq = new PriorityQueue<>();
        boolean[] vis = new boolean[N];
        dis[0][0] = 0;
        vis[0] = true;
        pq.add(new node(0, 0, 0));
        boolean check = false;
        while (!pq.isEmpty()) {
            node cur = pq.poll();
            vis[cur.v] = false;
            if (cur.v == N - 1){
                System.out.println(cur.w);
                check = true;
                break;
            }
            if (dis[cur.v][cur.c] < cur.w){
                continue;
            }
            for (node cur2 : adj[cur.v]){
                int i = cur.c + cur2.c;
                if (i <= S && dis[cur2.v][i] > cur.w + cur2.w){
                    dis[cur2.v][i] = cur.w + cur2.w;
                    pq.add(new node (cur2.v, dis[cur2.v][i], i));
                }
            }
        }
        if (!check) {
            System.out.println(-1);
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