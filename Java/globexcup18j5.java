
import java.io.*;
import java.util.*;
public class globexcup18j5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken()) - 1, i, j, a, b;
        ArrayList<ArrayList<Integer>> bfs = new ArrayList<>();
        for (i = 0; i < N; i++) {
            bfs.add(new ArrayList<>());
        }
        for (i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            bfs.get(a).add(b);
            bfs.get(b).add(a);
        }
        int[] cdis;
        int v, v2;
        Queue<Integer> q = new LinkedList<>();
        cdis = new int[N];
        Arrays.fill(cdis, Integer.MAX_VALUE);
        cdis[C] = 0;
        q.add(C);
        ArrayList<Integer> curarr;
        while (!q.isEmpty()) {
            v = q.peek();
            curarr = bfs.get(v);
            for (j = 0; j < curarr.size(); j++) {
                v2 = curarr.get(j);
                if (cdis[v] + 1 < cdis[v2]){
                    cdis[v2] = cdis[v] + 1;
                    q.add(v2);
                }
            }
            q.remove();
        }
        for (i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            a = cdis[Integer.parseInt(st.nextToken())-1] + cdis[Integer.parseInt(st.nextToken())-1];
            if (a < 0 || a == Integer.MAX_VALUE){
                System.out.println("This is a scam!");
            }
            else{
                System.out.println(a);
            }
        }
    }
}
