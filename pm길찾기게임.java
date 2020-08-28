import java.util.*;

public class pm��ã����� {

	public int pre[], post[], index, jndex;

	public int[][] solution(int[][] nodeinfo) {
		Tree tree = new Tree();
		LinkedList<Node> list = new LinkedList<Node>();

		for(int i = 0; i < nodeinfo.length; i++) 
			list.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));

		pre = new int[list.size()];
		post = new int[list.size()];
		index = 0;
		jndex = 0;

		Collections.sort(list, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return n2.y-n1.y;
			}
		});

		for(int i = 0; i < list.size(); i++) 
			tree.insert(list.get(i));

		tree.preOrder(tree.root);
		tree.postOrder(tree.root);  	

		int[][] answer = {pre, post};
		return answer;
	}
	
	class Tree {
		Node root;

		Tree() {
			root = null;
		}

		void insert(Node newNode) {
			Node node = root;
			if(root == null)
				root = newNode;

			else {
				while(true) {
					boolean finish = true;
					while(node.left != null && node.x > newNode.x && node.y > newNode.y) {
						node = node.left;
						finish = false;
					}

					while(node.right != null && node.x < newNode.x && node.y > newNode.y) {
						node = node.right;
						finish = false;
					}
					if(finish)
						break;
				}

				if(node.x > newNode.x)
					node.left = newNode;
				else
					node.right = newNode;
			}
		}

		void preOrder(Node node) {
			if(node == null)
				return;
			pre[index++] = node.number;
			preOrder(node.left);
			preOrder(node.right);
		}

		void postOrder(Node node) {
			if(node == null)
				return;
			postOrder(node.left);
			postOrder(node.right);
			post[jndex++] = node.number;
		}
	}

	class Node {
		int number;
		int x, y;
		Node left, right;

		Node(int number, int x, int y) {
			this.number = number;
			this.x = x;
			this.y = y;
			left = null;
			right = null;
		}
	}

}