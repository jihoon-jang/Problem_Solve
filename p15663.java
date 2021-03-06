import java.util.*;

public class p15663 {
	static int n, m, array[];
	static boolean[] visit;
	static LinkedHashSet<String> set = new LinkedHashSet<String>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		visit = new boolean[n];
		array = new int[n];

		for(int i = 0; i < n; i++)
			array[i] = in.nextInt();

		Arrays.sort(array);
		dfs(0, "");

		Iterator it = set.iterator();

		while(it.hasNext())
			System.out.println(it.next());
	}

	public static void dfs(int length, String s) {
		if(length == m) {
			set.add(s.trim());
			return;
		}
		else {
			for(int i = 0; i < n; i++) {
				if(!visit[i]) {
					visit[i] = true;
					dfs(length+1, s + array[i] + " ");
					visit[i] = false;
				}
			}
		}
	}

}
