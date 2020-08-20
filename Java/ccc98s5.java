import java.util.*;
import java.io.*;
public class ccc98s5 {
    public static void main(String[] args) throws IOException {
        int n, minox, j, k, oxused;
        int[][] grid;
        ArrayList<ArrayList<ArrayList<int[]>>> bfs;
        ArrayList<int[]> q, curarr;
        ArrayList<int[]> dis;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int runs = Integer.parseInt(st.nextToken());
        for (int i = 0; i < runs; i++) {
            dis = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0){
                System.out.println(0);
            }
            else {
                grid = new int[n][n];
                oxused = 0;
                for (j = 0; j < n; j++) {
                    for (k = 0; k < n; k++) {
                        st = new StringTokenizer(br.readLine());
                        grid[j][k] = Integer.parseInt(st.nextToken());
                    }
                }
                minox = grid[0][0];
                bfs = new ArrayList<>();
                for (j = 0; j < n; j++) {
                    dis.add(new int[n]);
                    bfs.add(new ArrayList<>());
                    for (k = 0; k < n; k++) {
                        if (j == 0 && k == 0) {
                            dis.get(j)[k] = 0;
                        } else {
                            dis.get(j)[k] = -1;
                        }
                        bfs.get(j).add(new ArrayList<>());
                        if (j != 0 && Math.abs(grid[j][k] - grid[j - 1][k]) < 3) {
                            bfs.get(j).get(k).add(new int[]{j - 1, k});
                        }
                        if (k != 0 && Math.abs(grid[j][k] - grid[j][k - 1]) < 3) {
                            bfs.get(j).get(k).add(new int[]{j, k - 1});
                        }
                        if (j < n - 1 && Math.abs(grid[j][k] - grid[j + 1][k]) < 3) {
                            bfs.get(j).get(k).add(new int[]{j + 1, k});
                        }
                        if (k < n - 1 && Math.abs(grid[j][k] - grid[j][k + 1]) < 3) {
                            bfs.get(j).get(k).add(new int[]{j, k + 1});
                        }
                    }
                }
                q = new ArrayList<>();
                q.add(new int[]{0, 0});
                while (!q.isEmpty()) {
                    if (!bfs.get(q.get(0)[0]).isEmpty()) {
                        curarr = bfs.get(q.get(0)[0]).get(q.get(0)[1]);
                        for (j = 0; j < curarr.size(); j++) {
                            if (grid[curarr.get(j)[0]][curarr.get(j)[1]] > minox || grid[q.get(0)[0]][q.get(0)[1]] > minox) {
                                if (dis.get(curarr.get(j)[0])[curarr.get(j)[1]] == -1 || dis.get(q.get(0)[0])[q.get(0)[1]] + 1 < dis.get(curarr.get(j)[0])[curarr.get(j)[1]]) {
                                    dis.get(curarr.get(j)[0])[curarr.get(j)[1]] = dis.get(q.get(0)[0])[q.get(0)[1]] + 1;
                                    q.add(curarr.get(j));
                                }
                            } else if (dis.get(curarr.get(j)[0])[curarr.get(j)[1]] == -1 || dis.get(q.get(0)[0])[q.get(0)[1]] < dis.get(curarr.get(j)[0])[curarr.get(j)[1]]) {
                                dis.get(curarr.get(j)[0])[curarr.get(j)[1]] = dis.get(q.get(0)[0])[q.get(0)[1]];
                                q.add(curarr.get(j));
                            }
                        }
                    }
                    q.remove(0);
                }
                if (dis.get(n - 1)[n - 1] == -1) {
                    System.out.println("CANNOT MAKE THE TRIP");
                } else {
                    System.out.println(dis.get(n - 1)[n - 1]);
                }
                System.out.println("");
            }
        }
    }
}
