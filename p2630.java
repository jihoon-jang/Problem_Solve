import java.util.Scanner;

public class p2630 {
	static int n, map[][];
	static int blue = 0, white = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		map = new int[n][n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				map[i][j] = in.nextInt();

		part(0, 0, n/2);

		System.out.println(white + "\n" + blue);
	}

	public static void part(int x, int y, int size) {

		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				if(check(map[x+i*size][y+j*size], x+i*size, y+j*size, size)) {
					if(map[x+i*size][y+j*size] == 1)
						blue++;
					else
						white++;
				}
				else 
					part(x+i*size, y+j*size, size/2);
			}
		}
	}

	public static boolean check(int color, int x, int y, int size) {
		for(int i = 0; i < size; i++) 
			for(int j = 0; j < size; j++)
				if(map[x + i][y + j] != color)
					return false;

		return true;
	}

}
