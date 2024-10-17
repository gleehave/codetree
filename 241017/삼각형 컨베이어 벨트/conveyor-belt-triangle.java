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

        leftSide = new int[3];
        rightSide = new int[3];
        bottomSide = new int[3];

        st = new StringTokenizer(br.readLine());
        leftSide[0] = Integer.parseInt(st.nextToken());
        leftSide[1] = Integer.parseInt(st.nextToken());
        leftSide[2] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        rightSide[0] = Integer.parseInt(st.nextToken());
        rightSide[1] = Integer.parseInt(st.nextToken());
        rightSide[2] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        bottomSide[2] = Integer.parseInt(st.nextToken());
        bottomSide[1] = Integer.parseInt(st.nextToken());
        bottomSide[0] = Integer.parseInt(st.nextToken());

        while(t > 0){
            t--;
            int tempLeft = leftSide[2];
            int tempRight = rightSide[2];
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
            bottomSide[2] = tempRight;
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