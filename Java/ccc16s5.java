import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ccc16s5 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); long T = Long.parseLong(st.nextToken());
        String gen = br.readLine();
        int[] num = new int[N];
        for (int i =0; i < N; i++){
            num[i] = gen.charAt(i);
        }
        while (T >= 1){
            long a = T&-T; T-=a;
            int[] numtemp = new int[N];
            for (int j = 0; j < N; j++){
                int x1 = num[(int)( (a+j) % N ) ]; int x2 = num[(int)((j+N-a%N)%N)];
                numtemp[j] = x1^x2;
            }
            for (int k = 0; k < N; k++){
                num[k] = numtemp[k];
            }
        }
        for (int item : num){
            System.out.print(item);
        }
    }
}
