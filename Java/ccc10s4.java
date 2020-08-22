import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ccc10s4 {
    static ArrayList<Pair>[] adj;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        adj = new ArrayList[500];
        Corner[][] corners = new Corner[500][500];
        for (int i = 0; i < 500; i++){
            adj[i] = new ArrayList<>();
            for (int j = 0; j < 500; j++){
                corners[i][j] = new Corner(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int[] c = new int[e], w = new int[e];
            for (int j = 0; j < e; j++){
                c[j] = Integer.parseInt(st.nextToken());
            }
            for(int j  =0; j < e; j++){
                w[j] = Integer.parseInt(st.nextToken());
            }
            for (int j = 0; j < e; j++){
                int x = c[j], y = c[(j+1)%e];
                int a = Math.max(x, y), b = Math.min(x, y);
                if (corners[a][b].p1 == Integer.MAX_VALUE){
                    corners[a][b].p1 = i+1;
                }
                else{
                    corners[a][b].p2 = i+1;
                }
                corners[a][b].w = w[j];
            }
        }
        for (int i = 0; i < 500; i++){
            for (int j = 0;j < 500; j++){
                if (corners[i][j].p1 != Integer.MAX_VALUE && corners[i][j].p2 != Integer.MAX_VALUE){
                    adj[corners[i][j].p1].add(new Pair(corners[i][j].p2, corners[i][j].w));
                    adj[corners[i][j].p2].add(new Pair(corners[i][j].p1, corners[i][j].w));
                }
                else if (corners[i][j].p1 != Integer.MAX_VALUE && corners[i][j].p2 == Integer.MAX_VALUE){
                    adj[corners[i][j].p1].add(new Pair(N+1, corners[i][j].w));
                    adj[N+1].add(new Pair(corners[i][j].p1, corners[i][j].w));
                }
            }
        }
        System.out.println(Math.min(prims(N), prims(N+1)));
    }
    static int prims(int n){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] vis = new boolean[500];
        Arrays.fill(vis, false);
        int tn = 1, tw = 0;
        for (int i = 0; i < adj[1].size(); i++){
            if (adj[1].get(i).a <= n) {
                pq.add(new Edge(adj[1].get(i).a, adj[1].get(i).w, 1));
            }
        }
        vis[1] = true;
        System.out.println("a");
        while (tn < n && !pq.isEmpty()){
            System.out.println(tn);
            Edge cur = pq.poll();
            if (!vis[cur.to]){
                System.out.println(cur.from + " " + cur.to + " " + cur.w);
                tn++;
                tw+=cur.w;
                vis[cur.to] = true;
                for (Pair cur2 : adj[cur.to]){
                    if (cur2.a <= n){
                        pq.add(new Edge(cur2.a, cur2.w, cur.to));
                    }
                }
            }
        }
        if (tn == n){
            return tw;
        }
        else{
            return Integer.MAX_VALUE;
        }
    }
    private static class Corner{
        int p1, p2, w; Corner(int p1,int p2, int w){ this.p1 = p1; this.p2 = p2; this.w = w; }
    }
    private static class Pair{
        int a, w; Pair(int a, int w){ this.a = a; this.w = w; }
    }
    private static class Edge implements Comparable<Edge>{
        int to, w, from; Edge(int to, int w, int from){ this.to = to; this.from = from; this.w = w;}

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
}
