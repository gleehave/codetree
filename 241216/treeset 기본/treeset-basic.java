import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    static int n;
    static TreeSet<Integer> treeSet = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "add":
                    treeSet.add(Integer.parseInt(st.nextToken()));
                    break;
                case "remove":
                    treeSet.remove(Integer.parseInt(st.nextToken()));
                    break;
                case "find":
                    if (treeSet.contains(Integer.parseInt(st.nextToken()))) {
                        System.out.println("true");
                    } else {
                        System.out.println("false");
                    }
                    break;
                case "lower_bound":
                    int key = Integer.parseInt(st.nextToken());
                    Integer lowerBound = treeSet.ceiling(key);
                    if (lowerBound != null) {
                        System.out.println(lowerBound);
                    } else {
                        System.out.println("None");
                    }
                    break;
                case "upper_bound":
                    int key2 = Integer.parseInt(st.nextToken());
                    Integer upperBound = treeSet.higher(key2);
                    if (upperBound != null) {
                        System.out.println(upperBound);
                    } else {
                        System.out.println("None");
                    }
                    break;
                case "largest":
                    if (!treeSet.isEmpty()) {
                        System.out.println(treeSet.last());
                    } else {
                        System.out.println("None");
                    }
                    break;
                case "smallest":
                    if (!treeSet.isEmpty()) {
                        System.out.println(treeSet.first());
                    } else {
                        System.out.println("None");
                    }
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }
}