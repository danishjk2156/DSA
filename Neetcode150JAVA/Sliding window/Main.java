import java.util.HashSet;

public class Main {

    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(c);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String s1 = "zxyzxyz";
        String s2 = "xxxx";

        System.out.println(lengthOfLongestSubstring(s1)); // 3
        System.out.println(lengthOfLongestSubstring(s2)); // 1
    }
}