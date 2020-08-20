import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class wac2p1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String day = br.readLine();
        int cur = 0;
        if (day.equals("Sunday")){
            cur = 1;
        }
        else if (day.equals("Saturday")){
            cur = 7;
        }
        else if (day.equals("Monday")){
            cur = 2;
        }
        else if (day.equals("Tuesday")){
            cur = 3;
        }
        else if (day.equals("Wednesday")){
            cur = 4;
        }
        else if (day.equals("Thursday")){
            cur = 5;
        }
        else if (day.equals("Friday")){
            cur = 6;
        }
        for (int i = 0; i < 27; i++){
            cur--;
            if (cur == 0){
                cur = 7;
            }
        }
        if (cur == 1){
            System.out.println("Sunday");
        }
        if (cur == 2){
            System.out.println("Monday");
        }
        if (cur == 3){
            System.out.println("Tuesday");
        }
        if (cur == 4){
            System.out.println("Wednesday");
        }
        if (cur == 5){
            System.out.println("Thursday");
        }
        if (cur == 6){
            System.out.println("Friday");
        }
        if (cur == 7){
            System.out.println("Saturday");
        }
    }
}
