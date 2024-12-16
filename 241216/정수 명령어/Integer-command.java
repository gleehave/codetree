import java.io.*;
import java.util.*;
import java.util.TreeSet;

public class Main {
    static int T, TIME;
    static TreeSet<Integer> treeSet = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            TIME = Integer.parseInt(br.readLine());
            for(int i=0; i<TIME; i++){

                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                if (command.equals("I")){
                    treeSet.add(value);
                } else{
                    if (!treeSet.isEmpty()){
                        if (value == 1){
                            treeSet.remove(treeSet.last());
                        } else {
                            treeSet.remove(treeSet.first());
                        }
                    }
                }
            }
            
            if (!treeSet.isEmpty()){
                System.out.println(treeSet.last() +" "+treeSet.first());

                if (treeSet.size() == 1) treeSet.remove(treeSet.first());
                else {
                    treeSet.remove(treeSet.first());
                    treeSet.remove(treeSet.last());                    
                }
            } else {
                System.out.println("EMPTY");
            }
        }
    }
}