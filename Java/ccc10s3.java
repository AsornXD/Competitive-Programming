import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ccc10s3 {
    static int H, k;
    static int[] housesarr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        housesarr = new int[H];
        for (int i = 0; i < H; i++){
            st = new StringTokenizer(br.readLine());
            housesarr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        Arrays.sort(housesarr);
        int start = -1, end = 1000000, cur, check2;
        while (start < end-1){
            cur = (start+end)/2;
            check2 = run(cur*2);
            if (check2 > k) {
                start = cur;
            }
            else{
                end = cur;
            }
        }
        System.out.println(end);
    }
    public static int run(int check){
        int hc = H, i = 0, cur2, ch, j;
        while (i < H && housesarr[i] <= (housesarr[0] + check)){
            cur2 = 1;
            ch = housesarr[i];
            j = i + 1;
            while (j < H && housesarr[j] < housesarr[i] - check + 1000000) {
                if (housesarr[j] > ch) {
                    cur2+=1;
                    ch = housesarr[j] + check;
                }
                j++;
            }
            i++;
            if (cur2 < hc){
                hc = cur2;
            }
        }
        return hc;
    }
}

