import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static String[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 그룹 당 종이 수
        m = Integer.parseInt(st.nextToken()); // 알파벳 개수 (열 수)

        map = new String[2 * n][m]; // 2N개의 종이
        for (int i = 0; i < 2 * n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = String.valueOf(line.charAt(j));
            }
        }

        int validCombinationCount = 0;

        // 열 조합을 3개 선택
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                for (int k = j + 1; k < m; k++) {
                    // 현재 선택한 열 조합에서 A와 B 그룹의 패턴을 추출
                    Set<String> aSet = new HashSet<>();
                    Set<String> bSet = new HashSet<>();

                    // 그룹 A의 패턴 추가
                    for (int r = 0; r < n; r++) {
                        aSet.add(map[r][i] + map[r][j] + map[r][k]);
                    }

                    // 그룹 B의 패턴 추가
                    for (int r = n; r < 2 * n; r++) {
                        bSet.add(map[r][i] + map[r][j] + map[r][k]);
                    }

                    // A와 B의 패턴에 공통이 없다면 유효한 조합
                    boolean isValid = true;
                    for (String pattern : aSet) {
                        if (bSet.contains(pattern)) {
                            isValid = false;
                            break;
                        }
                    }

                    if (isValid) {
                        validCombinationCount++;
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(validCombinationCount);
    }
}
