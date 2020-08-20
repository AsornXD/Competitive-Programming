import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class nccc2s5 {
    public static int[] ds;
    public static ArrayList<pair>[] gclone;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        ArrayList<pair>[] g = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++){
            g[i] = new ArrayList<>();
        }
        int[] dsclone = new int[N+1];
        Edge[] edges = new Edge[M+1];
        for (int i = 0; i < N+1; i++){
            dsclone[i] = i;
        }
        edges[0] = new Edge(0, 0, Integer.MIN_VALUE);
        for (int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(a, b, c);
        }
        ds = dsclone.clone();
            gclone = g.clone();
            for (Edge e : edges){
            // System.out.println(e.w);
            if (e.w != Integer.MIN_VALUE) {
                union(e);
            }
        }
        int Q = Integer.parseInt((st = new StringTokenizer(br.readLine())).nextToken());
        for (int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int check = Integer.parseInt(st.nextToken());
            if (check == 1){
                edges[Integer.parseInt(st.nextToken())].changew(Integer.parseInt(st.nextToken()));
                ds = dsclone.clone();
                gclone = g.clone();
                for (Edge e : edges){
                    System.out.println(e.w);
                    if (e.w != Integer.MIN_VALUE) {
                        union(e);
                    }
                }
            }
            else{
                System.out.println("abc: " + dfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), -1));
            }
        }
    }
    public static int dfs(int a, int b, int r, int p){
        if (a == b){
            return 1;
        }
        for (pair i : gclone[a]){
            System.out.println(a + " " + i.a + " " + i.w);
            if (i.w >= r && i.a != p && dfs(i.a, b, r, a) == 1){
                return 1;
            }
        }
        return 0;
    }

    public static int find(int v){
        if (ds[v] == v){
            return v;
        }
        return ds[v] = find(ds[v]);
    }
    public static void union(Edge e){
        int ap = find(e.a), bp = find(e.b);
        if (ap != bp){
            gclone[e.a].add(new pair(e.b, e.w));
            gclone[e.b].add(new pair(e.a, e.w));
            ds[ap] = bp;
        }
    }
    private static class Edge{
        int a,b,w;
        public Edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
        public void changew(int w){
            this.w = w;
        }
    }
    private static class pair{
        int a, w;
        public pair(int a, int w){
            this.a = a;
            this.w = w;
        }
    }
    private static class edgesort implements Comparator<Edge>{
        public int compare(Edge a, Edge b){
            if (a.w == Integer.MIN_VALUE || b.w == Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
            return b.w - a.w;
        }
    }
}



