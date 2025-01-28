import java.util.*;
public class Trains {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        MyStack<Long> stak = new MyStack<>();
        MyQueue<Long> cum = new MyQueue<>();
        int num = 1;
        for (int i = 0; i < n; i++) {
            int val = scanner.nextInt();
            if (!stak.empty() && val > stak.top()) {
                System.out.println(0);
                return;
            }
            cum.push(1L);
            stak.push((long) val);
            while (!stak.empty() && stak.top() == num) {
                cum.push(2L);
                stak.pop();
                num++;
            }
        }

        ArrayList<Pair<Long, Integer>> actions = new ArrayList<>();
        long val = cum.front();
        cum.pop();
        int count = 1;
        while (!cum.empty()) {
            if (cum.front() == val) {
                count++;
            } else {
                actions.add(new Pair<>(val, count));
                val = cum.front();
                count = 1;
            }
            cum.pop();
        }
        actions.add(new Pair<>(val, count));
        System.out.println(actions.size());
        for (Pair<Long, Integer> action : actions) {
            System.out.println(action.getKey() + " " + action.getValue());
        }
    }
}

class MyStack<T> {
    private ArrayList<T> data = new ArrayList<>();

    public boolean empty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    public void push(T value) {
        data.add(value);
    }

    public void pop() {
        if (!empty()) {
            data.remove(data.size() - 1);
        }
    }

    public T top() {
        return data.get(data.size() - 1);
    }
}

class MyQueue<T> {
    private ArrayList<T> data = new ArrayList<>();

    public boolean empty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    public void push(T value) {
        data.add(value);
    }

    public void pop() {
        if (!empty()) {
            data.remove(0);
        }
    }

    public T front() {
        return data.get(0);
    }

    public T back() {
        return data.get(data.size() - 1);
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
