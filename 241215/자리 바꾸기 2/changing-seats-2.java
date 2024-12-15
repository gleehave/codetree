import java.util.Scanner;
import java.util.HashSet;

public class Main {
    public static int n, k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); 
        k = sc.nextInt();

        // 각 사람이 갈 수 있는 자리들을 저장하는 HashSet 배열
        HashSet<Integer>[] sets = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) {
            sets[i] = new HashSet<>();
            sets[i].add(i); // 초기에는 자기 자신만 가능
        }

        // 자리 교환 정보 입력
        int[] a = new int[k];
        int[] b = new int[k];
        for (int i = 0; i < k; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }

        // 현재 사람의 위치를 추적하는 배열
        int[] people = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            people[i] = i; // 초기에는 각 사람이 자기 자리에 앉아있음
        }

        // 3번의 자리 교환 반복
        for (int t = 0; t < 3; t++) {
            for (int j = 0; j < k; j++) {
                int posA = people[a[j]]; // a[j] 자리의 현재 사람
                int posB = people[b[j]]; // b[j] 자리의 현재 사람

                // posA 사람은 b[j] 자리에 갈 수 있음
                sets[posA].add(b[j]);
                // posB 사람은 a[j] 자리에 갈 수 있음
                sets[posB].add(a[j]);

                // 두 사람의 자리를 실제로 교환
                people[a[j]] = posB;
                people[b[j]] = posA;
            }
        }

        // 결과 출력
        for (int i = 1; i <= n; i++) {
            System.out.println(sets[i].size());
        }
    }
}
