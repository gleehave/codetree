import java.io.*;
import java.util.*;

public class Main {

    static String direction;
    static int[][] grid;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        grid = new int[4][4];
        for(int i=0; i<4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        } // grid 입력

        direction = br.readLine();
        
        switch(direction){
            case "L":
                leftShift();
                leftSum();
                PRINT();
                break;
            case "R":
                rightShift();
                rightSum();
                PRINT();
                break;
            case "U":
                upShift();
                upShift();
                PRINT();
                break;
            case "D":
                downShift();
                downSum();
                PRINT();                
                break;                       
        }
    }

    public static void PRINT(){
        for(int r=0; r<4; r++){
            for(int c=0; c<4; c++){
                System.out.print(grid[r][c]+" ");
            }
            System.out.println();
        }
    }

    public static void downSum(){
        for(int c=0; c<4; c++){
            for(int r=3; r>0; r--){
                if (grid[r][c] == grid[r-1][c]){
                    grid[r][c] += grid[r][c];
                    grid[r-1][c] = 0;
                    c = c - 2;
                }
            }
        }
        upShift();
        return;
    }

    public static void upSum(){
        for(int c=0; c<4; c++){
            for(int r=0; r<3; r++){
                if (grid[r][c] == grid[r+1][c]){
                    grid[r][c] += grid[r][c];
                    grid[r+1][c] = 0;
                    c = c + 2;
                }
            }
        }
        upShift();
        return;
    }

    public static void leftSum(){
        for(int r=0; r<4; r++){
            for(int c=0; c<3; c++){
                if (grid[r][c] == grid[r][c+1]){
                    grid[r][c] += grid[r][c];
                    grid[r][c+1] = 0;
                    c = c + 2;
                }
            }
        }
        leftShift();
        return;
    }

    public static void rightSum(){
        
        for(int r=0; r<4; r++){
            for(int c=3; c>0; c--){
                if (grid[r][c] == grid[r][c-1]){
                    grid[r][c] += grid[r][c];
                    grid[r][c-1] = 0;
                    c = c-2;
                }
            }
        }
        rightShift();
        return;
    }

    public static void leftShift(){
        int[] temp;
        for(int r=0; r<4; r++){
            int index = 0;
            temp = new int[4];
            for(int c=0; c<4; c++){
                if (grid[r][c] == 0) continue;
                temp[index++] = grid[r][c];
            }

            for(int c=0; c<4; c++){
                grid[r][c] = temp[c];
            }
        }
    }
    public static void rightShift(){
        int[] temp;
        for(int r=0; r<4; r++){
            int index = 0;
            temp = new int[4];
            for(int c=3; c>=0; c--){
                if (grid[r][c] == 0) continue;
                temp[index++] = grid[r][c];
            }

            for(int c=3; c>=0; c--){
                grid[r][c] = temp[3-c];
            }
        }
    }
    public static void upShift(){
        int[] temp;
        for(int c=0; c<4; c++){
            int index = 0;
            temp = new int[4];
            for(int r=0; r<4; r++){
                if (grid[r][c] == 0) continue;
                temp[index++] = grid[r][c];
            }
            for(int r=0; r<4; r++){
                grid[r][c] = temp[r];
            }
        }
        
    }
    public static void downShift(){
        int[] temp;
        for(int c=0; c<4; c++){
            int index = 0;
            temp = new int[4];
            for(int r=3; r>=0; r--){
                if (grid[r][c] == 0) continue;
                temp[index++] = grid[r][c];
            }
            for(int r=3; r>=0; r--){
                grid[3-r][c] = temp[r];
            }
        }
    }    

}