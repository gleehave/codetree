import java.util.Scanner;

// 연결 리스트의 노드
class Node {
	// 책의 번호
	int data;

	// 이전 노드와 다음 노드
	Node prev, next;

	// 번호가 data인 단일 노드를 만드는 생성자
	public Node(int data) {
		this.data = data;
		this.prev = this.next = null;
	}
}

public class Main {
	public static final int MAX_N = 250000;
	public static final int MAX_K = 100;

	// 노드를 번호순으로 배열로 관리
	public static Node[] nodes = new Node[MAX_N + 1];

	// 각 연결 리스트의 head와 tail을 배열로 관리
	public static Node[] heads = new Node[MAX_K + 1];
	public static Node[] tails = new Node[MAX_K + 1];

	// i번 연결 리스트가 비어있는지 여부
	public static boolean empty(int i) {
		return null == heads[i];
	}

	// 노드 u와 v를 서로 연결
	public static void connect(Node u, Node v) {
		u.next = v;
		v.prev = u;
	}

	// i번 연결 리스트의 head를 삭제한 후 반환
	public static Node pop_front(int i) {
		Node ret = heads[i];

		if (null != ret) {
			// head를 뒤로 하나 이동
			heads[i] = ret.next;

			// 노드 ret를 단일 노드로 만듦
			ret.next = null;

			if (null != heads[i])
				// 연결 리스트가 비어있지 않다면, head의 이전 노드를 Null로 설정
				heads[i].prev = null;
			else
				// 연결 리스트가 비어있다면, tail도 Null로 설정
				tails[i] = null;
		}

		return ret;
	}

	// i번 연결 리스트의 tail을 삭제한 후 반환
	public static Node pop_back(int i) {
		Node ret = tails[i];

		if (null != ret) {
			// tail을 앞으로 하나 이동
			tails[i] = ret.prev;

			// 노드 ret를 단일 노드로 만듦
			ret.prev = null;

			if (null != tails[i])
				// 연결 리스트가 비어있지 않다면, tail의 다음 노드를 Null로 설정
				tails[i].next = null;
			else
				// 연결 리스트가 비어있다면, head도 Null로 설정
				heads[i] = null;
		}

		return ret;
	}

	// i번 연결 리스트의 맨 앞에 단일 노드 singleton을 삽입
	public static void push_front(int i, Node singleton) {
		if (null == heads[i]) {
			// 연결 리스트가 비어있다면, head와 tail은 모두 singleton
			heads[i] = tails[i] = singleton;
		} else {
			// 연결 리스트가 비어있지 않다면, 기존의 head 앞에 singleton을 삽입
			connect(singleton, heads[i]);
			heads[i] = singleton;
		}
	}

	// i번 연결 리스트의 맨 뒤에 단일 노드 singleton을 삽입
	public static void push_back(int i, Node singleton) {
		if (null == tails[i]) {
			// 연결 리스트가 비어있다면, head와 tail은 모두 singleton
			heads[i] = tails[i] = singleton;
		} else {
			// 연결 리스트가 비어있지 않다면, 기존의 tail 뒤에 singleton을 삽입
			connect(tails[i], singleton);
			tails[i] = singleton;
		}
	}

	// i번 연결 리스트를 j번 연결 리스트 앞에 삽입
	public static void move_all_front(int i, int j) {
		// 무의미한 연산에 대한 예외처리
		if (i == j || empty(i))
			return;

		if (empty(j)) {
			// j번 연결 리스트가 비어있다면, head와 tail은 i번의 것과 동일
			heads[j] = heads[i];
			tails[j] = tails[i];
		} else {
			// j번 연결 리스트가 비어있지 않다면
			// i번의 tail과 j번의 head를 연결하고
			// j번의 head는 i번의 head가 됨
			connect(tails[i], heads[j]);
			heads[j] = heads[i];
		}

		// 이제, i번 연결 리스트는 비어있음
		heads[i] = tails[i] = null;
	}

	// i번 연결 리스트를 j번 연결 리스트 뒤에 삽입
	public static void move_all_back(int i, int j) {
		// 무의미한 연산에 대한 예외처리
		if (i == j || empty(i))
			return;

		if (empty(j)) {
			// j번 연결 리스트가 비어있다면, head와 tail은 i번의 것과 동일
			heads[j] = heads[i];
			tails[j] = tails[i];
		} else {
			// j번 연결 리스트가 비어있지 않다면
			// j번의 tail과 i번의 head를 연결하고
			// j번의 tail는 i번의 tail이 됨
			connect(tails[j], heads[i]);
			tails[j] = tails[i];
		}

		// 이제, i번 연결 리스트는 비어있음
		heads[i] = tails[i] = null;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();

		// n 개의 단일 노드 생성
		for (int i = 1; i <= n; i++)
			nodes[i] = new Node(i);

		// n 개의 노드를 일렬로 연결
		for (int i = 1; i < n; i++)
			connect(nodes[i], nodes[i+1]);

		// k 개의 연결 리스트 초기화
		for (int i = 1; i <= k; i++)
			heads[i] = tails[i] = null;

		// 초기에 1번 연결 리스트는 n 개의 노드를 모두 가짐
		heads[1] = nodes[1];
		tails[1] = nodes[n];

		int q = sc.nextInt();

		for (int t = 0; t < q; t++) {
			int type = sc.nextInt();
			int i = sc.nextInt();
			int j = sc.nextInt();

			if (1 == type) {
				// i번 연결 리스트에서 맨 앞 노드를 가져옴
				Node node = pop_front(i);

				// 그 노드를 j번 연결 리스트 맨 뒤에 삽입
				if (null != node)
					push_back(j, node);
			} else if (2 == type) {
				// i번 연결 리스트에서 맨 뒤 노드를 가져옴
				Node node = pop_back(i);

				// 그 노드를 j번 연결 리스트 맨 앞에 삽입
				if (null != node)
					push_front(j, node);
			} else if (3 == type) {
				// i번 연결 리스트를 j번 연결 리스트의 맨 앞에 삽입
				move_all_front(i, j);
			} else if (4 == type) {
				// i번 연결 리스트를 j번 연결 리스트의 맨 뒤에 삽입
				move_all_back(i, j);
			}
		}

		// 각 연결 리스트를 순회
		for (int i = 1; i <= k; i++) {
			int cnt = 0;

			// i번 연결 리스트를 순회하여 노드의 수를 셈
			Node cur = heads[i];
			while (null != cur) {
				cnt++;
				cur = cur.next;
			}

			System.out.printf("%d", cnt);

			// i번 연결 리스트를 순회하면서 각 노드의 값을 출력
			cur = heads[i];
			while (null != cur) {
				System.out.printf(" %d", cur.data);
				cur = cur.next;
			}

			System.out.println();
		}
	}
}