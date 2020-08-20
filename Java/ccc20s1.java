import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ccc20s1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<timepos> array = new ArrayList<timepos>();
        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            timepos curnode = new timepos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            array.add(curnode);
        }
        Collections.sort(array);
        double prev = 0;
        double curspeed;
        timepos curnode;
        timepos nextnode;
        for (int j = 0; j < array.size()-1; j++){
            curnode = array.get(j);
            nextnode = array.get(j+1);
            curspeed = Math.abs((nextnode.pos-curnode.pos)/(nextnode.time-curnode.time + 0.0));
            if (curspeed > prev){
                prev = curspeed;
            }
        }
        System.out.println(prev);
    }
}
class timepos implements Comparable<timepos>{
    public int time;
    public int pos;
    public timepos(int time1, int pos1){
        time = time1;
        pos = pos1;
    }
    @Override
    public int compareTo(timepos cur){
        return time-cur.time;
    }

}
