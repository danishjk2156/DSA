import java.util.*;

public class TimeMap {

    class Pair {
        String value;
        int timestamp;

        public Pair(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    private Map<String, List<Pair>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Pair(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }

        List<Pair> list = map.get(key);

        int left = 0;
        int right = list.size() - 1;
        String ans = "";

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid).timestamp <= timestamp) {
                ans = list.get(mid).value;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        TimeMap obj = new TimeMap();

        obj.set("foo", "bar", 1);

        System.out.println(obj.get("foo", 1)); // bar
        System.out.println(obj.get("foo", 3)); // bar

        obj.set("foo", "bar2", 4);

        System.out.println(obj.get("foo", 4)); // bar2
        System.out.println(obj.get("foo", 5)); // bar2

        // Additional Tests
        System.out.println(obj.get("foo", 0)); // ""
        System.out.println(obj.get("hello", 5)); // ""
    }
}