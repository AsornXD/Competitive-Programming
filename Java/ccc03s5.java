import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class ccc03s5{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<int[]>> bfs =  new ArrayList<>();
        for (int i = 0; i < c; i++){
            bfs.add(new ArrayList<>());
        }
        int x, y, w;
        for (int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken())-1;
            y = Integer.parseInt(st.nextToken())-1;
            w = Integer.parseInt(st.nextToken());
            bfs.get(x).add(new int[] {y,w});
            bfs.get(y).add(new int[] {x,w});
        }
        int[] maxweight = new int[c];
        Arrays.fill(maxweight, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        ArrayList<int []> curarr;
        int v1, v2, w2;
        while (!q.isEmpty()){
            v1 = q.poll();
            curarr = bfs.get(v1);
            for (int i = 0; i < curarr.size();i++){
                v2 = curarr.get(i)[0];
                w2 = curarr.get(i)[1];
                if (w2 > maxweight[v2]){
                    maxweight[v2] = w2;
                    q.add(v2);
                }
            }
        }
        int min = Integer.MAX_VALUE, cur;
        for (int i = 0; i < d; i++){
            st = new StringTokenizer(br.readLine());
            cur = Integer.parseInt(st.nextToken())-1;
            if (maxweight[cur] < min){
                min = maxweight[cur];
            }
        }
        System.out.println(min);
    }
}

