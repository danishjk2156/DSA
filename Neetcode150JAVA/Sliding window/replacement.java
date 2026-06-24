public class replacement {

    public static int characterReplacement(String s, int k) {
        int left = 0;
        int maxFreq = 0;
        int[] freq = new int[26];
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            freq[c - 'A']++;
            maxFreq = Math.max(maxFreq, freq[c - 'A']);

            while ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;

        int result = characterReplacement(s, k);

        System.out.println("String: " + s);
        System.out.println("k = " + k);
        System.out.println("Longest Length = " + result);
    }
}