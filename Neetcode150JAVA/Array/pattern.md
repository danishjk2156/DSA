# Array Patterns for DSA Interviews

---

## 1. Hashing (HashSet / HashMap)

### Core Idea
Use a hash-based data structure to achieve **O(1) average lookup, insert, and delete**. This is the most common pattern in array problems.

### When to Use
- Detecting duplicates
- Checking membership (is element present?)
- Counting occurrences
- Grouping elements by some key

### Key Operations
| Operation | HashSet | HashMap |
|-----------|---------|---------|
| Check existence | `set.contains(x)` | `map.containsKey(x)` |
| Insert | `set.add(x)` | `map.put(k, v)` |
| Count | Use `HashMap<T, Integer>` | `map.getOrDefault(k, 0) + 1` |

### Problem Examples
- **Contains Duplicate** — Add elements to HashSet. If already present, duplicate found.
- **Valid Anagram** — Use `int[26]` as frequency map for character counting.

### Template
```java
// Duplicate detection
HashSet<Integer> set = new HashSet<>();
for (int num : nums) {
    if (set.contains(num)) return true;  // duplicate found
    set.add(num);
}
return false;

// Frequency counting
HashMap<Integer, Integer> freq = new HashMap<>();
for (int num : nums) {
    freq.put(num, freq.getOrDefault(num, 0) + 1);
}
```

### Common Pitfalls
- Using HashMap when HashSet suffices (wasted memory)
- Forgetting to handle null keys
- Not considering `O(n)` space complexity in analysis

---

## 2. Frequency Counting

### Core Idea
Count occurrences of each element/character, then use those counts to make decisions. Often uses an `int[]` array instead of HashMap when the range is known and small.

### When to Use
- Comparing two strings/arrays for equivalence
- Finding most/least frequent elements
- Anagram, permutation, and substring problems

### Array vs HashMap for Counting
| Scenario | Use |
|----------|-----|
| Characters (a-z, A-Z) | `int[26]` or `int[52]` |
| ASCII characters | `int[128]` |
| Bounded integer range | `int[max + 1]` |
| Unbounded / large range | `HashMap<T, Integer>` |

### Problem Examples
- **Valid Anagram** — Two strings are anagrams if their character frequency arrays are identical.
- **Top K Frequent Elements** — Count frequencies, then bucket sort by frequency.

### Template
```java
// Character frequency comparison
int[] freq = new int[26];
for (int i = 0; i < s.length(); i++) {
    freq[s.charAt(i) - 'a']++;
    freq[t.charAt(i) - 'a']--;
}
for (int count : freq) {
    if (count != 0) return false;
}
return true;

// General frequency map
HashMap<Integer, Integer> freq = new HashMap<>();
for (int num : nums) {
    freq.merge(num, 1, Integer::sum);
}
```

### Common Pitfalls
- Off-by-one in array index (`char - 'a'` vs `char - '0'`)
- Mutating the frequency array when you shouldn't
- Forgetting to check length equality before frequency comparison

---

## 3. Key Transformation for Grouping

### Core Idea
Transform each element into a **canonical key** such that elements in the same group produce the same key. Use a HashMap to group by this key.

### When to Use
- Grouping anagrams, isomorphic strings
- Any problem where "equivalence" is defined by content, not identity
- Partitioning elements into categories

### Key Transformation Strategies
| Problem Type | Transformation |
|--------------|----------------|
| Anagrams | Sort characters → `"eat"` → `"aet"` |
| Character mapping | Normalize pattern → `"egg"` → `"abb"` |
| Coordinate grouping | Normalize coordinates or use modulo |

### Problem Examples
- **Group Anagrams** — Sort each string to get the key. All anagrams share the same sorted form.

### Template
```java
HashMap<String, List<String>> groups = new HashMap<>();

for (String str : strs) {
    char[] chars = str.toCharArray();
    Arrays.sort(chars);
    String key = new String(chars);

    groups.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
}

return new ArrayList<>(groups.values());
```

### Optimization Tip
Instead of sorting (O(k log k)), you can use a frequency count as the key (O(k)):
```java
int[] freq = new int[26];
for (char c : str.toCharArray()) freq[c - 'a']++;
String key = Arrays.toString(freq);  // e.g., "[1,0,0,...,1,0,1]"
```

### Common Pitfalls
- Using the original string as the key (defeats the purpose)
- Expensive key transformation (prefer O(k) over O(k log k) when possible)
- Not handling edge cases (empty strings, single characters)

---

## 4. Prefix and Suffix Arrays

### Core Idea
Precompute cumulative information from **left to right (prefix)** and **right to left (suffix)**. Each element's answer depends on all elements before it and all elements after it.

### When to Use
- Product/sum of all elements except current
- Next greater/smaller element
- Range queries (sum, product, min, max)
- When division is not allowed but you need "all others" computation

### Prefix vs Suffix
| Direction | What it stores | Index meaning |
|-----------|---------------|---------------|
| Prefix | Cumulative from left | `prefix[i]` = result of elements `0..i-1` |
| Suffix | Cumulative from right | `suffix[i]` = result of elements `i+1..n-1` |

### Problem Examples
- **Product Except Self** — `prefix[i]` = product of all elements before `i`. `suffix` = product of all elements after `i`. Answer = `prefix[i] * suffix[i]`.

### Template
```java
int n = nums.length;
int[] result = new int[n];

// Prefix pass
result[0] = 1;
for (int i = 1; i < n; i++) {
    result[i] = result[i - 1] * nums[i - 1];
}

// Suffix pass (use single variable to save space)
int suffix = 1;
for (int i = n - 1; i >= 0; i--) {
    result[i] *= suffix;
    suffix *= nums[i];
}

return result;
```

### Common Pitfalls
- Confusing `nums[i-1]` vs `nums[i]` in prefix computation
- Off-by-one errors at array boundaries (index 0 and n-1)
- Not handling the case where input has 0s (for product problems)

---

## 5. Bucket Sort

### Core Idea
Instead of comparison-based sorting (O(n log n)), distribute elements into **buckets** based on some property (frequency, range, etc.), then collect in order. Achieves **O(n)** time when range is bounded.

### When to Use
- "Top K frequent/important" elements
- Frequency-based sorting
- Integer sorting with known range
- When you need better than O(n log n)

### How It Works
1. **Count** — Compute the property (e.g., frequency) for each element
2. **Bucket** — Place elements into buckets indexed by that property
3. **Collect** — Iterate buckets in desired order, collect until K elements

### Problem Examples
- **Top K Frequent Elements** — Bucket index = frequency. Bucket at index `f` contains all elements that appear `f` times. Iterate from highest bucket to lowest.

### Template
```java
// Step 1: Count frequencies
HashMap<Integer, Integer> freq = new HashMap<>();
for (int num : nums) {
    freq.put(num, freq.getOrDefault(num, 0) + 1);
}

// Step 2: Create buckets (index = frequency)
List<Integer>[] buckets = new List[nums.length + 1];
for (int key : freq.keySet()) {
    int f = freq.get(key);
    if (buckets[f] == null) buckets[f] = new ArrayList<>();
    buckets[f].add(key);
}

// Step 3: Collect top K from highest bucket
List<Integer> result = new ArrayList<>();
for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
    if (buckets[i] != null) {
        result.addAll(buckets[i]);
    }
}
```

### Comparison: Bucket Sort vs Priority Queue
| Approach | Time | Space | Best For |
|----------|------|-------|----------|
| Bucket Sort | O(n) | O(n) | Bounded frequency range |
| Min-Heap (Priority Queue) | O(n log k) | O(n) | General top-K |
| TreeMap | O(n log n) | O(n) | Sorted order needed |

### Common Pitfalls
- Bucket array size must be `max_frequency + 1`, not `n`
- Forgetting that multiple elements can share the same frequency
- Not handling the case where K > number of distinct elements

---

## 6. Hash Set for Sequence Detection

### Core Idea
Use a HashSet to detect **consecutive sequences** or **connected components** in unsorted data. The key trick: only start counting from the **beginning** of a sequence (where `num - 1` is not in the set).

### When to Use
- Finding longest consecutive sequence
- Detecting arithmetic progressions
- Graph-like connectivity in arrays
- Any problem where you need to follow chains

### Key Insight
Without the `num - 1` check, the solution would be O(n²). With it, each element is visited at most twice (once in the outer loop, once in the while chain), making it **O(n)**.

### Problem Examples
- **Longest Consecutive Sequence** — For each number, if `num - 1` is not in the set, it's the start of a sequence. Count forward until the chain breaks.

### Template
```java
HashSet<Integer> set = new HashSet<>();
for (int num : nums) set.add(num);

int maxStreak = 0;
for (int num : set) {
    // Only start from the beginning of a sequence
    if (!set.contains(num - 1)) {
        int current = num;
        int streak = 1;
        while (set.contains(current + 1)) {
            current++;
            streak++;
        }
        maxStreak = Math.max(maxStreak, streak);
    }
}
return maxStreak;
```

### Common Pitfalls
- Iterating over the original array instead of the set (causes duplicate work)
- Forgetting the `num - 1` check (solution becomes O(n²))
- Not using a HashSet (using array or sorting would be slower)

---

## 7. Hash Set for Grid/Matrix Validation

### Core Idea
Use **multiple HashSets** to simultaneously validate constraints across rows, columns, and sub-regions. Each dimension gets its own set.

### When to Use
- Sudoku validation
- Matrix uniqueness checks
- Grid-based constraint satisfaction
- Any 2D validation with region-based rules

### Key Formula
For 3x3 sub-boxes in a 9x9 grid:
```
boxIndex = (row / 3) * 3 + (col / 3)
```
This maps each cell to one of 9 boxes:
```
Box 0: rows 0-2, cols 0-2    Box 1: rows 0-2, cols 3-5    Box 2: rows 0-2, cols 6-8
Box 3: rows 3-5, cols 0-2    Box 4: rows 3-5, cols 3-5    Box 5: rows 3-5, cols 6-8
Box 6: rows 6-8, cols 0-2    Box 7: rows 6-8, cols 3-5    Box 8: rows 6-8, cols 6-8
```

### Problem Examples
- **Valid Sudoku** — 3 sets of HashSets: one for each row, one for each column, one for each 3x3 box. Check all three before placing a value.

### Template
```java
HashSet<Character>[] rows = new HashSet[9];
HashSet<Character>[] cols = new HashSet[9];
HashSet<Character>[] boxes = new HashSet[9];

for (int i = 0; i < 9; i++) {
    rows[i] = new HashSet<>();
    cols[i] = new HashSet<>();
    boxes[i] = new HashSet<>();
}

for (int i = 0; i < 9; i++) {
    for (int j = 0; j < 9; j++) {
        char val = board[i][j];
        if (val == '.') continue;

        int boxIndex = (i / 3) * 3 + (j / 3);

        if (rows[i].contains(val) ||
            cols[j].contains(val) ||
            boxes[boxIndex].contains(val)) {
            return false;  // duplicate found
        }

        rows[i].add(val);
        cols[j].add(val);
        boxes[boxIndex].add(val);
    }
}
return true;
```

### Common Pitfalls
- Incorrect box index formula
- Not initializing all HashSets before use
- Forgetting to skip empty cells (`.`)

---

## Pattern Selection Cheat Sheet

| If the problem asks... | Pattern |
|------------------------|---------|
| "Is there a duplicate?" | Hash Set |
| "Count occurrences" | Frequency Counting |
| "Group by equivalence" | Key Transformation |
| "Product/sum except self" | Prefix & Suffix |
| "Top K most frequent" | Bucket Sort |
| "Longest consecutive sequence" | Hash Set for Sequence |
| "Validate grid constraints" | Grid Validation with Sets |

---

## Complexity Quick Reference

| Pattern | Time | Space |
|---------|------|-------|
| Hashing | O(n) | O(n) |
| Frequency Counting | O(n) | O(1)* or O(n) |
| Key Transformation | O(n × k log k) | O(n × k) |
| Prefix/Suffix | O(n) | O(1) to O(n) |
| Bucket Sort | O(n) | O(n) |
| Sequence Detection | O(n) | O(n) |
| Grid Validation | O(n²) | O(n²) |

*O(1) space when using fixed-size array (e.g., `int[26]` for lowercase letters)
