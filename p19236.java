import java.io.*;
import java.util.*;

public class p19236 {
    public static Fish map[][];
    public static int N = 4;
    public static int answer = 0; /*↑, ↖, ←, ↙, ↓, ↘, →, ↗*/
    public static int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int dy[] = {0, -1, -1, -1, 0, 1, 1, 1};
    public static LinkedList<Fish> list = new LinkedList<Fish>();
    public static Fish shark;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;
        map = new Fish[N][N];
        for(int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int n = Integer.parseInt(stz.nextToken());
                int d = Integer.parseInt(stz.nextToken());
                map[i][j] = new Fish(n, d, i, j);
                list.add(map[i][j]);
            }
        }
        Collections.sort(list, new Comparator<Fish>() {
            public int compare(Fish f1, Fish f2) {
                return f1.number - f2.number;
            }
        });
        shark = new Fish(map[0][0].number, map[0][0].direction, 0, 0);
        for(int i = 0; i < list.size(); i++)
            if(list.get(i).number == shark.number) {
                list.remove(i);
                break;
            }
        map[0][0].number = 0;
        answer = shark.number;
        move();
        start();
        System.out.println(answer);
    }
    
    public static void start() {
        print();
        System.out.println();
        LinkedList<Fish> temp = new LinkedList<>();
        for(int i = 0; i < list.size(); i++)
            temp.addLast(copy(list.get(i)));
        Fish tr[][] = new Fish[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                tr[i][j] = copy(map[i][j]);
        int sN = shark.number;
        int sD = shark.direction;
        int sX = shark.x;
        int sY = shark.y;
        for(int i = 1; i <= 3; i++) {
            int x = shark.x + dx[shark.direction - 1] * i;
            int y = shark.y + dy[shark.direction - 1] * i;
            if(x >= 0 && y >= 0 && x < N && y < N && map[x][y].number != 0) {
                shark.number += map[x][y].number;
                shark.direction = map[x][y].direction;
                for(int a = 0; a < list.size(); a++)
                    if(map[x][y].number == list.get(a).number)
                        list.remove(a);
                System.out.println(x + " " + y);
                map[x][y].number = 0;
                shark.x = x;
                shark.y = y;
                System.out.println(shark.number);
                answer = Math.max(shark.number, answer);
                move();
                start();
                for(int q = 0; q < N; q++)
                    for(int z = 0; z < N; z++)
                        map[q][z] = copy(tr[q][z]);
                shark.number = sN;
                shark.direction = sD;
                shark.x = sX;
                shark.y = sY;
                list.clear();
                for(int w = 0; w < temp.size(); w++)
                    list.addLast(copy(temp.get(w)));
            }
        }
    }
    
    public static Fish copy(Fish f) {
        return new Fish(f.number, f.direction, f.x, f.y);
    }
    
    public static void print() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++)
                System.out.printf("%-2d ", map[i][j].number);
            System.out.println();
        }
    }
    
    public static void move() {
        Iterator<Fish> it = list.iterator();
        while(it.hasNext()) {
            Fish now = it.next();
            for(int i = 0; i < 8; i++) {
                int x = now.x + dx[(now.direction + i - 1) % 8];
                int y = now.y + dy[(now.direction + i - 1) % 8];
                
                if(check(x, y)) {
                    int swap = map[x][y].number;
                    if(swap == 0) {
                        map[x][y] = copy(now);
                        map[now.x][now.y].number = 0;
                        now.x = x;
                        now.y = y;
                    }
                    else
                        for(int j = 0; j < list.size(); j++) {
                            Fish temp = list.get(j);
                            if(temp.number == swap) {
                                map[now.x][now.y] = copy(temp);
                                map[x][y] = copy(now);
                                map[now.x][now.y].x = now.x;
                                map[now.x][now.y].y = now.y;
                                map[x][y].x = x;
                                map[x][y].y = y;
                                temp.x = now.x;
                                temp.y = now.y;
                                now.x = x;
                                now.y = y;
                            }
                        }
                    break;
                }
            }
        }
    }
    
    public static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N && !(shark.x == x && shark.y == y);
    }
    
    static class Fish {
        int x, y;
        int number, direction;
        
        Fish(int number, int direction, int x, int y) {
            this.number = number;
            this.direction = direction;
            this.x = x;
            this.y = y;
        }
    }
}