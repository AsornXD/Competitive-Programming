import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ccc17s4 {
    static int counter = 0, tn = 0;
    static long maxw;
    static int[] ds;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), D = Integer.parseInt(st.nextToken());
        ds = new int[N+1];
        for (int i = 0; i < N+1; i++){
            ds[i] = i;
        }
        Edge[] edges = new Edge[M];
        for (int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (i < N){
                edges[i-1] = new Edge(a,b,c,true);
            }
            else{
                edges[i-1] = new Edge(a,b,c,false);
            }
        }
        Arrays.sort(edges, new edgesort());
        for (Edge e : edges) {
            union(e);
        }
        long nowmax = maxw;
        int days = Math.max(counter, (N-1)-(tn - counter));
        for (int i = 0; i < N+1; i++){
            ds[i] = i;
        }
        for (Edge e : edges){
            if (!(find(e.u) == find(e.v))){
                if (e.c < nowmax || e.ac && e.c == nowmax){
                    union(e);
                }
                else if(e.ac && e.c <= D){
                    days-=1;
                    break;
                }
            }
        }
        System.out.println(days);
    }

    public static int find(int v){
        if(ds[v] == v){
            return v;
        }
        return ds[v] = find(ds[v]);
    }

    public static void union(Edge e){
        int up = find(e.u), vp = find(e.v);
        if (up!=vp){
            ds[up] = vp;
            tn++;
            maxw = Math.max(maxw, e.c);
            if(!e.ac){
                counter++;
            }
        }
    }
    static class Edge{
        int u, v;
        long c;
        boolean ac;
        Edge(int u, int v, long c, boolean ac) {
            this.u = u;
            this.v = v;
            this.c = c;
            this.ac = ac;
        }
    }
    public static class edgesort implements Comparator<Edge> {
        public int compare(Edge A, Edge B){
            return Long.compare(A.c, B.c);
        }

    }
}
