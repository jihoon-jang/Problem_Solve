import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class p14425 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stz.nextToken());
        int m = Integer.parseInt(stz.nextToken());
        Set<String> set = new HashSet<>();
        for(int i = 0; i < n; i++)
            set.add(br.readLine());
        int count = 0;
        for(int i = 0; i < m; i++)
            if(set.contains(br.readLine()))
                count++;
    
        System.out.println(count);
    }
}
