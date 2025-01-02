import java.util.*;

public class Main {

    public static List<List<Integer>> processCommands(int n, int m, int q, List<List<Integer>> initialLines, List<int[]> commands) {
        // Initialize the lines with LinkedList
        List<LinkedList<Integer>> lines = new ArrayList<>();
        for (List<Integer> line : initialLines) {
            if (line.get(0) == -1) {
                lines.add(new LinkedList<>());
            } else {
                LinkedList<Integer> queue = new LinkedList<>(line.subList(1, line.size()));
                lines.add(queue);
            }
        }

        for (int[] command : commands) {
            int cmd = command[0];

            if (cmd == 1) {
                // 1 a b: a cuts in front of b
                int a = command[1];
                int b = command[2];
                for (LinkedList<Integer> line : lines) {
                    if (line.contains(b)) {
                        int idx = line.indexOf(b);
                        line.add(idx, a);
                        break;
                    }
                }

            } else if (cmd == 2) {
                // 2 a: a goes home
                int a = command[1];
                for (LinkedList<Integer> line : lines) {
                    line.remove((Integer) a);
                }

            } else if (cmd == 3) {
                // 3 a b c: a to b group cuts in front of c
                int a = command[1];
                int b = command[2];
                int c = command[3];
                LinkedList<Integer> group = new LinkedList<>();

                // Extract the group [a, ..., b] from the lines
                for (LinkedList<Integer> line : lines) {
                    if (line.contains(a)) {
                        int startIdx = line.indexOf(a);
                        int endIdx = line.indexOf(b);
                        group.addAll(new LinkedList<>(line.subList(startIdx, endIdx + 1)));
                        line.subList(startIdx, endIdx + 1).clear();
                        break;
                    }
                }

                // Find where c is and insert the group before it
                for (LinkedList<Integer> line : lines) {
                    if (line.contains(c)) {
                        int idx = line.indexOf(c);
                        line.addAll(idx, group);
                        break;
                    }
                }
            }
        }

        // Convert LinkedLists back to Lists for output
        List<List<Integer>> result = new ArrayList<>();
        for (LinkedList<Integer> line : lines) {
            if (line.isEmpty()) {
                result.add(Collections.singletonList(-1));
            } else {
                result.add(new ArrayList<>(line));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int q = scanner.nextInt();

        List<List<Integer>> initialLines = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int size = scanner.nextInt();
            if (size == -1) {
                initialLines.add(Collections.singletonList(-1));
            } else {
                List<Integer> line = new ArrayList<>();
                line.add(size);
                for (int j = 0; j < size; j++) {
                    line.add(scanner.nextInt());
                }
                initialLines.add(line);
            }
        }

        List<int[]> commands = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            int cmdType = scanner.nextInt();
            if (cmdType == 1) {
                commands.add(new int[]{cmdType, scanner.nextInt(), scanner.nextInt()});
            } else if (cmdType == 2) {
                commands.add(new int[]{cmdType, scanner.nextInt()});
            } else if (cmdType == 3) {
                commands.add(new int[]{cmdType, scanner.nextInt(), scanner.nextInt(), scanner.nextInt()});
            }
        }

        List<List<Integer>> result = processCommands(n, m, q, initialLines, commands);
        for (List<Integer> line : result) {
            if (line.size() == 1 && line.get(0) == -1) {
                System.out.println(-1);
            } else {
                System.out.println(String.join(" ", line.stream().map(String::valueOf).toArray(String[]::new)));
            }
        }
    }
}
