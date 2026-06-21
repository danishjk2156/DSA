
import java.util.*;
import java.io.*;

public class topk {

    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length + 1];

        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = bucket.length - 1; i >= 0 && result.size() < k; i--) {
            if (bucket[i] != null) {
                result.addAll(bucket[i]);
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = result.get(i);
        }

        return res;
    }

    // 🔥 Method to save result to file
    public static void saveToFile(int[] arr) {
        try {
            FileWriter writer = new FileWriter("topk_output.txt");

            writer.write(Arrays.toString(arr));

            writer.close();
            System.out.println("✅ File saved as topk_output.txt");
        } catch (IOException e) {
            System.out.println("❌ Error saving file");
            e.printStackTrace();
        }
    }

    // 🚀 Main method to run
    public static void main(String[] args) {

        topk sol = new topk();

        int[] nums = {1,1,1,2,2,3};
        int k = 2;

        int[] result = sol.topKFrequent(nums, k);

        // Print result
        System.out.println("Top K Frequent Elements:");
        System.out.println(Arrays.toString(result));

        // Save to file
       ;
    }
}