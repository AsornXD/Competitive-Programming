import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ncco4d1p3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<e>[] g = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++){
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            g[A].add(new e(B, L));
            g[B].add(new e(A, L));
        }
        int[] dis = new int[N+1];
        int[] discheck = new int[N+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        Arrays.fill(discheck, Integer.MAX_VALUE);
        PriorityQueue<e> q = new PriorityQueue<e>();
        dis[1] = 0;
        q.add(new e(1, 0));
        while (!q.isEmpty()){
            e cur = q.poll();
            for (e cur2 : g[cur.v]){
                if (dis[cur2.v] > cur.c + cur2.c){
                    discheck[cur2.v] = dis[cur2.v];
                    dis[cur2.v] = cur.c + cur2.c;
                    q.add(new e(cur2.v, dis[cur2.v]));
                }
                else if (cur.c + cur2.c < discheck[cur2.v] && dis[cur2.v] != cur.c + cur2.c){ //
                    discheck[cur2.v] = cur.c + cur2.c;
                    q.add(new e(cur2.v, discheck[cur2.v]));
                }
            }
        }
        System.out.println(discheck[N]);

    }
}
class e implements Comparable<e>{
    int v, c;
    e(int v1, int c1){
        v = v1;
        c = c1;
    }
    public int compareTo(e i){
        return Integer.compare(c, i.c);
    }
}