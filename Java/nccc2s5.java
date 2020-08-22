import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class nccc2s5 {
    static ArrayList<Pair>[] g;
    static Edge[] edges;
    static int[] ds;
    static int N, M, bb;
    static long w;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        edges = new Edge[M+1];
        ds = new int[N+1];
        g = new ArrayList[N+1];
        edges[0] = new Edge(0,0,Long.MIN_VALUE);
        for (int i = 1; i < M+1; i++){
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
        }
        kruskal();
        st = new StringTokenizer(br.readLine());
        int Q = Integer.parseInt(st.nextToken());
        for (int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            if (t == 1){
                int m = Integer.parseInt(st.nextToken()); long x = Long.parseLong(st.nextToken());
                Edge temp = edges[m];
                edges[m] = new Edge(temp.u, temp.v, x);
                kruskal();
            }
            else{
                int a = Integer.parseInt(st.nextToken());
                bb = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());
                if (find(a) == find(bb)){
                    System.out.println(dfs(a));
                }
                else{
                    System.out.println(0);
                }
            }
        }
    }

    static int dfs(int a){
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        boolean[] canvis = new boolean[N+1];
        canvis[a] = true;
        pq.add(new Pair(a, -1));
        while (!pq.isEmpty()){
            Pair cur = pq.poll();
            if (cur.a == bb){
                return 1;
            }
            for (Pair cur2 : g[cur.a]){
                if (cur2.b >= w && !canvis[cur2.a]){
                    pq.add(cur2);
                    canvis[cur2.a] = true;
                }
            }
        }
        return 0;
    }

    static void kruskal(){
        for (int i = 1; i < N+1; i++){
            ds[i] = i;
            g[i] = new ArrayList<>();
        }
        Edge[] edgesclone = new Edge[M+1];
        System.arraycopy(edges, 0, edgesclone, 0, M+1);
        Arrays.sort(edgesclone, new edgesort());
        for (int i = 0; i < M; i++){
            Edge cur = edgesclone[i];
            int a = find(cur.u), b = find(cur.v);
            if (a != b){
                g[cur.u].add(new Pair(cur.v, cur.w));
                g[cur.v].add(new Pair(cur.u, cur.w));
                ds[a] = b;
            }
        }
    }

    static int find(int n){
        if (ds[n] == n){
            return n;
        }
        return ds[n] = find(ds[n]);
    }

    static class Edge{
        int u, v;
        long w;
        Edge(int u, int v, long w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    public static class edgesort implements Comparator<Edge>{
        public int compare(Edge A, Edge B){
            return Long.compare(B.w, A.w);
        }
    }
    static class Pair implements Comparable<Pair>{
        int a;
        long b;
        Pair(int a, long b){
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair o) {
            return Long.compare(this.b, o.b);
        }
    }
}
