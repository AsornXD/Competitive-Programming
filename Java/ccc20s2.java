import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ccc20s2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<cell>> cons = new ArrayList<>();
        for (int i = 0; i < 1000001; i++){
            cons.add(new ArrayList<>());
        }
        cell start = new cell(0, 0);
        cell curcell;
        boolean[][] vis = new boolean[1001][1001];
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                curcell = new cell(i+1, j+1);
                cons.get(Integer.parseInt(st.nextToken())).add(curcell);
                if (i+1 == M && j+1 == N){
                    start = curcell;
                }
            }
        }
        vis[M-1][N-1] = true;
        Queue<cell> q = new LinkedList<>();
        q.add(start);
        boolean yes = false;
        cell nowcell;
        ArrayList<cell> check;
        while (!q.isEmpty()){
            nowcell = q.poll();
            check = cons.get(nowcell.m * nowcell.n);
            for (cell cur : check){
                if (cur.n == 1 && cur.m == 1 && !yes){
                    System.out.println("yes");
                    yes = true;
                }
                else{
                    if (!vis[cur.m-1][cur.n-1]){
                        q.add(cur);
                        vis[cur.m-1][cur.n-1] = true;
                    }
                }
            }
        }
        if (!yes){
            System.out.println("no");
        }
    }
}
class cell{
    public int m;
    public int n;
    public cell(int m, int n){
        this.m = m;
        this.n = n;
    }
}

