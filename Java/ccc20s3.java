import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ccc20s3 {
    // prime moduli 5381
    static String needle;
    static ArrayList<Integer> hashes = new ArrayList<>();
    static void perms(String str, int ans, int n){
        if (str.length() == 0){
            if (!hashes.contains(ans)) {
                hashes.add(ans);
            }
            return;
        }
        for (int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            String newstr = str.substring(0,i) + str.substring(i+1);
            perms(newstr, (int)(ans + ((ch-96) * Math.pow(26, n))) % 5381, n-1);
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        needle = br.readLine();
        String haystack = br.readLine();
        int windowsize = needle.length();
        int c = 0;
        perms(needle, 0, windowsize-1);
        int checkans = 0;
        for(int i = 0; i < windowsize; i++){
            checkans+=(int) (((haystack.charAt(i)-96) * Math.pow(26, windowsize-1-i)) % 5381);
        }
        for (int i = 0; i < haystack.length()-(windowsize); i++){
            if (i !=0) {
                checkans*=26;
                checkans = (checkans + (int) ((haystack.charAt(i+windowsize-1) - 96) * Math.pow(26, 0)))%5381;
            }
            if(hashes.contains(checkans)){
                c++;
                hashes.remove(hashes.indexOf(checkans));
            }
            checkans = (checkans - (int) ((haystack.charAt(i) - 96) * Math.pow(26, windowsize-1)))%5381;
        }
        System.out.println(c);
    }
}
