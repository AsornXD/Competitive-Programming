import java.util.*;
import java.io.*;
public class ccc10j5 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<ArrayList<ArrayList<int[]>>> bfs = new ArrayList<>();

        int[][] dis = new int[8][8];
        for (int x = 0; x < 8; x++){
            bfs.add(new ArrayList<>());
            for (int y = 0; y < 8; y++){
                bfs.get(x).add(new ArrayList<>());
                dis[x][y] = -1;
                if (y+2 < 8 && x+1 < 8){
                    bfs.get(x).get(y).add(new int[] {x+1,y+2});
                }
                if (y+2 < 8 && x-1 > -1){
                    bfs.get(x).get(y).add(new int[] {x-1,y+2});
                }
                if (y + 1 < 8 && x+2 < 8){
                    bfs.get(x).get(y).add(new int[] {x+2, y+1});
                }
                if (y - 1 > -1 && x+2 < 8){
                    bfs.get(x).get(y).add(new int[] {x+2, y-1});
                }
                if (y - 2 > -1 && x+1 < 8){
                    bfs.get(x).get(y).add(new int[] {x+1, y-2});
                }
                if (y - 2 > -1 && x-1 > -1){
                    bfs.get(x).get(y).add(new int[] {x-1,  y-2});
                }
                if (y+1 < 8 && x-2 > -1){
                    bfs.get(x).get(y).add(new int[] {x-2, y+1});
                }
                if (y-1 > -1 && x-2 > -1){
                    bfs.get(x).get(y).add(new int[] {x-2, y-1});
                }
            }
        }
        int[] Start = new int[] {Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1};
        ArrayList<int[]> q = new ArrayList<>();
        q.add(Start);
        dis[Start[0]][Start[1]] = 0;
        while (!q.isEmpty()){
            ArrayList<int[]> curarr = bfs.get(q.get(0)[0]).get(q.get(0)[1]);
            for (int i = 0; i < curarr.size(); i++){
                if (dis[curarr.get(i)[0]][curarr.get(i)[1]] == -1 || dis[q.get(0)[0]][q.get(0)[1]] + 1 < dis[curarr.get(i)[0]][curarr.get(i)[1]]){
                    dis[curarr.get(i)[0]][curarr.get(i)[1]] = dis[q.get(0)[0]][q.get(0)[1]] + 1;
                    q.add(curarr.get(i));
                }
            }
            q.remove(0);
        }
        st = new StringTokenizer(br.readLine());
        System.out.println(dis[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]);
    }
}
