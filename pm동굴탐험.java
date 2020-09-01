import java.util.*;

class Solution {
	
	int[] prior, ret;
	boolean[] visit;
	ArrayList<Integer>[] list;

	public boolean solution(int n, int[][] path, int[][] order) {
		prior = new int[n + 1];
		ret = new int[n + 1];
		list = new ArrayList[n + 1];
		visit = new boolean[n + 1];
		
		for (int i = 0; i < n + 1; ++i)
			list[i] = new ArrayList<>();
		
		for (int i = 0; i < path.length; ++i) {
			list[path[i][0]].add(path[i][1]);
			list[path[i][1]].add(path[i][0]);
		}
		// prior[1] = 4, prior[7] = 8
		for (int i = 0; i < order.length; ++i) {
			prior[order[i][1]] = order[i][0];
		}
		
		if (prior[0] != 0)
			return false;
		
		visit[0] = true;
		Stack<Integer> st = new Stack<>();
		
		for (int i = 0; i < list[0].size(); ++i) {
			st.push(list[0].get(i));
		}
		
		while (!st.isEmpty()) {
			int v = st.pop();
			if (visit[v])
				continue;
			if (!visit[prior[v]]) {
				ret[prior[v]] = v;
				continue;
			}
			visit[v] = true;
			st.push(ret[v]);
			for (int i = 0; i < list[v].size(); ++i) {
				st.push(list[v].get(i));
			}
		}

		for (int i = 0; i < n; ++i) {
			if (!visit[i]) {
				return false;
			}
		}
		return true;
	}
}
