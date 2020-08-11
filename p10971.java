import java.util.*;
import java.io.*;

public class p10971 {
	static int n, map[][], answer;
	static boolean visit[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer stz;
		map = new int[n+1][n+1];
		visit = new boolean[n+1];
		answer = Integer.MAX_VALUE;

		for(int i = 1; i <= n; i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++)
				map[i][j] = Integer.parseInt(stz.nextToken());
		}

		for(int i = 1; i <= n; i++) {
			visit[i] = true;
			dfs(i, i, 0);
			visit[i] = false;
		}
		
		System.out.println(answer);
	}

	public static void dfs(int start, int line, int sum) {
		boolean finish = true;
		for(int i = 1; i <= n; i++) 
			if(!visit[i]) 
				finish = false;
		
		if(finish && map[line][start] != 0) {
			answer = Math.min(answer, sum+map[line][start]);
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(map[line][i] != 0 && !visit[i]) {
				visit[i] = true;
				dfs(start, i, sum + map[line][i]);
				visit[i] = false;
			}
		}
	}
}
