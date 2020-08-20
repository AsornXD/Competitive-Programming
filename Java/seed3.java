import java.util.Random;
import java.io.*;
import java.util.*;
public class seed3 {
    public static void main(String[] args) throws IOException {
        int I, N, J, K, XI, XF, s = 0, l = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        I = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        J = Integer.parseInt(st.nextToken());
        int[] diff = new int[I+1];
        for (int i = 0; i < J; i++){
            st = new StringTokenizer(br.readLine());
            XI = Integer.parseInt(st.nextToken());
            XF = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            diff[XI-1]+=K;
            diff[XF]-=K;
        }
        for (int i=0; i < I; i++){
            s+=diff[i];
            if (s < N){
                l++;
            }
        }
        System.out.println(l);
    }
}
