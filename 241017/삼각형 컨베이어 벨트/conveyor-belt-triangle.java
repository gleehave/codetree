import java.io.*;
import java.util.*;

public class Main {

    static int n, t;
    static int[] leftSide;
    static int[] rightSide;
    static int[] bottomSide;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        leftSide = new int[n];
        rightSide = new int[n];
        bottomSide = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            leftSide[i] = Integer.parseInt(st.nextToken()); 
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            rightSide[i] = Integer.parseInt(st.nextToken()); 
        }

        st = new StringTokenizer(br.readLine());
        for(int i=n-1; i>=0; i--){
            bottomSide[i] = Integer.parseInt(st.nextToken()); 
        }

        while(t > 0){
            t--;
            int tempLeft = leftSide[n-1];
            int tempRight = rightSide[n-1];
            int tempBottom = bottomSide[0];

            for(int i=n-1; i>0; i--){
                leftSide[i] = leftSide[i-1];
            }
            leftSide[0] = tempBottom;

            for(int i=n-1; i>0; i--){
                rightSide[i] = rightSide[i-1];
            }
            rightSide[0] = tempLeft;

            for(int i=0; i<n-1; i++){
                bottomSide[i] = bottomSide[i+1];
            }
            bottomSide[n-1] = tempRight;
        }

        StringBuilder builder = new StringBuilder();
        for(int i=0; i<n; i++){
            builder.append(leftSide[i]).append(" ");
        }
        System.out.println(builder.toString());

        builder = new StringBuilder();
        for(int i=0; i<n; i++){
            builder.append(rightSide[i]).append(" ");
        }
        System.out.println(builder.toString());

        builder = new StringBuilder();
        for(int i=0; i<n; i++){
            builder.append(bottomSide[i]).append(" ");
        }
        System.out.println(builder.reverse().toString().trim());
    }
}