import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ccc13s5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int t = 0;
        int M;
        while (N > 1){
            int a = 2;
            int b = (int) Math.pow(N, 0.5) + 1;
            while (N % a != 0 && a <= b){ a++; }
            if (N % a == 0 && a < N){
                M = N/a;
                N-=M;
                t+=N/M;
            }
            else{
                N--;
                t+=N;
            }
        }
        System.out.println(t);
    }
}
