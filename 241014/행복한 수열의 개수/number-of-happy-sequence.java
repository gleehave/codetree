import java.io.*;

public class Main {

    static int count;
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);

        map = new int[N][N];

        for(int i=0; i<N; i++){
            String[] mapData = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(mapData[j]);
            }
        }

        count = 0;
        for(int r=0; r < N; r++){
            for(int c=0; c < N-M; c++){
                
                boolean used = false;
                int check = 1;
                for(int k=c; k<=c+M-1; k++){
                    if (map[r][k] == map[r][k+1]){
                        check++;
                    } else {
                        check = 1;
                    }
                    if (check >= M){
                        used = true;
                        count++;
                        break;
                    }
                }

                if (used) break;
            }
        }

        for(int c=0; c < N; c++){
            for(int r=0; r < N-M; r++){

                boolean used = false;
                int check = 1;
                for(int k=r; k<=r+M-1; k++){
                    if (map[k][c] == map[k+1][c]) {
                        check++;
                    } else {
                        check = 1;
                    }
                    if (check >= M){
                        count++;
                        used = true;
                        break;
                    } 
                }
                if (used) break;
            }
        }
        System.out.println(count);
    
    }
}