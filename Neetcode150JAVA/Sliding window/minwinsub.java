public class minwinsub{

    public static String minWindow(String s, String t) {
        int left = 0;
        int right = 0;
        int minLeft = 0;
        int required = 0;
        int formed = 0;

        int[] need = new int[128];
        int[] win = new int[128];

        // Count frequencies in t
        for (char c : t.toCharArray()) {
            need[c]++;
        }

        // Count distinct required characters
        for (int freq : need) {
            if (freq > 0) {
                required++;
            }
        }

        int minLength = Integer.MAX_VALUE;

        while (right < s.length()) {

            char c = s.charAt(right);
            win[c]++;

            if (need[c] > 0 && need[c] == win[c]) {
                formed++;
            }

            while (formed == required) {

                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }

                char leftChar = s.charAt(left);
                win[leftChar]--;

                if (need[leftChar] > 0 && need[leftChar] > win[leftChar]) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return minLength == Integer.MAX_VALUE
                ? ""
                : s.substring(minLeft, minLeft + minLength);
    }

    public static void main(String[] args) {

        String s = "ADOBECODEBANC";
        String t = "ABC";

        String answer = minWindow(s, t);

        System.out.println("String s : " + s);
        System.out.println("String t : " + t);
        System.out.println("Minimum Window : " + answer);
    }
}