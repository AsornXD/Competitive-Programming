import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ccc18s3 {
    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<ArrayList<int[]>>> bfs = new ArrayList<>();
        int N, M, i, j, count = 0, k, x, y;
        int[] start = new int[]{};
        String[] curstr;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] dis = new int[N][M];
        boolean[][] cameracheck = new boolean[N][M];
        String[][] grid = new String[N][M];
        for (i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            curstr = st.nextToken().split("");
            bfs.add(new ArrayList<>());
            for (j = 0; j < M; j++) {
                bfs.get(i).add(new ArrayList<>());
                dis[i][j] = -1;
                cameracheck[i][j] = false;
                grid[i][j] = curstr[j];
                if (grid[i][j].equals(".")) {
                    count++;
                }
                else if (grid[i][j].equals("S")) {
                    start = new int[]{i, j};
                }
            }
        }
        for (i = 0; i < N; i++) {
            for (j = 0; j < M; j++) {
                if (grid[i][j].equals(".") || grid[i][j].equals("S")) {
                    if (grid[i + 1][j].equals(".")) {
                        bfs.get(i).get(j).add(new int[]{i + 1, j});
                    }
                    if (grid[i - 1][j].equals(".")) {
                        bfs.get(i).get(j).add(new int[]{i - 1, j});
                    }
                    if (grid[i][j + 1].equals(".")) {
                        bfs.get(i).get(j).add(new int[]{i, j + 1});
                    }
                    if (grid[i][j - 1].equals(".")) {
                        bfs.get(i).get(j).add(new int[]{i, j - 1});
                    }
                } else if (grid[i][j].equals("C")) {
                    cameracheck[i][j] = true;
                    for (k = j; !grid[i][k].equals("W"); k++) {
                        cameracheck[i][k] = true;
                    }
                    for (k = i; !grid[k][j].equals("W"); k++) {
                        cameracheck[k][j] = true;
                    }
                    for (k = i; !grid[k][j].equals("W"); k--) {
                        cameracheck[k][j] = true;
                    }
                    for (k = j; !grid[i][k].equals("W"); k--) {
                        cameracheck[i][k] = true;
                    }
                }
                else if (grid[i][j].equals("U") && !grid[i-1][j].equals("D")){
                    if (grid[i-1][j].equals("U")){
                        bfs.get(i+1).get(j).add(new int[] {i-2,j});
                        bfs.get(i).get(j+1).add(new int[] {i-2,j});
                        bfs.get(i).get(j-1).add(new int[] {i-2,j});
                    }
                    bfs.get(i+1).get(j).add(new int[] {i-1,j});
                    bfs.get(i).get(j+1).add(new int[] {i-1,j});
                    bfs.get(i).get(j-1).add(new int[] {i-1,j});
                }
                else if (grid[i][j].equals("D") && !grid[i+1][j].equals("U")){
                    if (grid[i+1][j].equals("D")){
                        bfs.get(i-1).get(j).add(new int[] {i+2,j});
                        bfs.get(i).get(j+1).add(new int[] {i+2,j});
                        bfs.get(i).get(j-1).add(new int[] {i+2,j});
                    }
                    bfs.get(i-1).get(j).add(new int[] {i+1,j});
                    bfs.get(i).get(j+1).add(new int[] {i+1,j});
                    bfs.get(i).get(j-1).add(new int[] {i+1,j});
                }
                else if (grid[i][j].equals("L") && !grid[i][j-1].equals("R")){
                    if (grid[i][j-1].equals("L")){
                        bfs.get(i-1).get(j).add(new int[] {i,j-2});
                        bfs.get(i+1).get(j).add(new int[] {i,j-2});
                        bfs.get(i).get(j+1).add(new int[] {i,j-2});
                    }
                    bfs.get(i-1).get(j).add(new int[] {i,j-1});
                    bfs.get(i+1).get(j).add(new int[] {i,j-1});
                    bfs.get(i).get(j+1).add(new int[] {i,j-1});
                }
                else if (grid[i][j].equals("R") && !grid[i][j+1].equals("L")){
                    if (grid[i][j+1].equals("R")){
                        bfs.get(i-1).get(j).add(new int[] {i,j+2});
                        bfs.get(i+1).get(j).add(new int[] {i,j+2});
                        bfs.get(i).get(j-1).add(new int[] {i,j+2});
                    }
                    bfs.get(i-1).get(j).add(new int[] {i,j+1});
                    bfs.get(i+1).get(j).add(new int[] {i,j+1});
                    bfs.get(i).get(j-1).add(new int[] {i,j+1});
                }
            }
        }
        if (cameracheck[start[0]][start[1]]) {
            for (i = 0; i < count; i++) {
                System.out.println(-1);
            }
        }
        else {
            ArrayList<int[]> q = new ArrayList<>(), curarr;
            q.add(start);
            dis[start[0]][start[1]] = 0;
            while (!q.isEmpty()) {
                curarr = bfs.get(q.get(0)[0]).get(q.get(0)[1]);
                for (i = 0; i < curarr.size(); i++) {
                    if (!cameracheck[curarr.get(i)[0]][curarr.get(i)[1]]) {
                        if (dis[curarr.get(i)[0]][curarr.get(i)[1]] == -1 || dis[q.get(0)[0]][q.get(0)[1]] + 1 < dis[curarr.get(i)[0]][curarr.get(i)[1]]) {
                            dis[curarr.get(i)[0]][curarr.get(i)[1]] = dis[q.get(0)[0]][q.get(0)[1]] + 1;
                            q.add(curarr.get(i));
                        }
                    }
                }
                q.remove(0);
            }
            for (i = 0; i < N; i++){
                for (j = 0; j < M; j++){
                    if (grid[i][j].equals(".")){
                        System.out.println(dis[i][j]);
                    }
                }
            }
        }
    }
}