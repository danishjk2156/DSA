# Java Collection Framework — HashMap

## What is a HashMap?

A `HashMap` is a part of Java's **Collection Framework** that implements the `Map` interface. It stores data in **key-value pairs** and uses a **hash table** internally to provide fast lookups. It **does not allow duplicate keys**, but duplicate values are allowed. It makes **no guarantees** about the order of elements.

```java
import java.util.HashMap;

HashMap<String, Integer> map = new HashMap<>();
```

---

## How It Works Internally

- Each **key** is passed through a **hash function** to compute a hash code.
- This hash code determines the **bucket** (index) where the key-value pair is stored.
- If two keys have the same hash code (collision), they are stored in the same bucket using a **linked list** or **balanced tree** (since Java 8).
- The **value** is associated with its key and can be retrieved later using the key.

---

## Time Complexity

| Operation          | Average | Worst Case |
|--------------------|---------|------------|
| `put()`            | O(1)    | O(n)       |
| `get()`            | O(1)    | O(n)       |
| `remove()`         | O(1)    | O(n)       |
| `containsKey()`    | O(1)    | O(n)       |
| `containsValue()`  | O(n)    | O(n)       |
| `size()`           | O(1)    | O(1)       |
| `isEmpty()`        | O(1)    | O(1)       |

> Worst case O(n) occurs when all keys hash to the same bucket. In practice, average O(1) is expected.

---

## Key Methods

### Creating a HashMap

```java
HashMap<String, Integer> map = new HashMap<>();             // empty map
HashMap<String, Integer> map = new HashMap<>(otherMap);     // from another map
```

### Adding / Updating

```java
map.put("Alice", 90);       // returns null (new entry)
map.put("Alice", 95);       // returns 90 (old value, key overwritten)
map.putIfAbsent("Bob", 85); // only puts if key doesn't exist
```

### Retrieving

```java
map.get("Alice");           // returns 95
map.get("Unknown");         // returns null
map.getOrDefault("Unknown", 0);  // returns 0 if key not found
```

### Removing

```java
map.remove("Alice");                // removes by key
map.remove("Alice", 95);           // removes only if key maps to value
map.removeIf((k, v) -> v < 60);   // remove by condition (Java 8+)
```

### Checking

```java
map.containsKey("Alice");    // returns true or false
map.containsValue(90);       // returns true or false (O(n) scan)
map.isEmpty();               // returns true if empty
map.size();                  // returns number of entries
```

### Iterating

```java
// Iterate over keys
for (String key : map.keySet()) {
    System.out.println(key + " = " + map.get(key));
}

// Iterate over values
for (int val : map.values()) {
    System.out.println(val);
}

// Iterate over entries
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " = " + entry.getValue());
}

// Using forEach (Java 8+)
map.forEach((key, value) -> System.out.println(key + " = " + value));
```

### Merge & Compute

```java
// Merge: combine values for same key
map.merge("Alice", 10, (oldVal, newVal) -> oldVal + newVal); // adds 10 to existing value

// Compute: compute new value based on key
map.compute("Alice", (key, val) -> val == null ? 1 : val + 1);

// Compute if absent
map.computeIfAbsent("Bob", k -> k.length() * 10);  // only computes if key missing
```

### Bulk Operations

```java
map.putAll(otherMap);        // copy all entries from otherMap
map.clear();                 // remove all entries
map.keySet();                // returns Set<String> of all keys
map.values();                // returns Collection<Integer> of all values
map.entrySet();              // returns Set<Map.Entry<String, Integer>>
```

---

## Other Map Implementations

| Class                | Ordered | Sorted | Thread-Safe | Null Keys | Null Values |
|----------------------|---------|--------|-------------|-----------|-------------|
| `HashMap`            | No      | No     | No          | 1 null    | Multiple    |
| `LinkedHashMap`      | Yes     | No     | No          | 1 null    | Multiple    |
| `TreeMap`            | No      | Yes    | No          | No        | Multiple    |
| `Hashtable`          | No      | No     | Yes         | No        | No          |
| `ConcurrentHashMap`  | No      | No     | Yes         | No        | No          |
| `EnumMap`            | No      | No     | No          | No        | Multiple    |

---

## When to Use HashMap

- **Count frequencies** — count occurrences of elements in an array/string
- **Two Sum problems** — store value and index, check for complement
- **Grouping** — group elements by a common property (e.g., anagrams)
- **Caching / Memoization** — store computed results for fast lookup
- **Mapping relationships** — student grades, character counts, word frequency
- **Fast lookup** — replace nested loops with O(1) key-based access

### vs HashSet

| Feature             | HashMap                  | HashSet         |
|---------------------|--------------------------|-----------------|
| Stores              | Key-Value pairs          | Single elements |
| Add                 | `put(key, value)`        | `add(element)`  |
| Lookup              | `get(key)`               | `contains(val)` |
| Duplicate keys      | Not allowed              | N/A             |
| Duplicate values    | Allowed                  | Not allowed     |
| Use case            | Need to associate data   | Just need membership check |

---

## LeetCode Questions Using HashMap

| #   | Title                                         | Difficulty | Link                                                                 |
|-----|-----------------------------------------------|------------|----------------------------------------------------------------------|
| 1   | Two Sum                                       | Easy       | [Link](https://leetcode.com/problems/two-sum/)                       |
| 242 | Valid Anagram                                 | Easy       | [Link](https://leetcode.com/problems/valid-anagram/)                 |
| 49  | Group Anagrams                                | Medium     | [Link](https://leetcode.com/problems/group-anagrams/)                |
| 128 | Longest Consecutive Sequence                  | Medium     | [Link](https://leetcode.com/problems/longest-consecutive-sequence/)  |
| 383 | Ransom Note                                   | Easy       | [Link](https://leetcode.com/problems/ransom-note/)                   |
| 205 | Isomorphic Strings                            | Easy       | [Link](https://leetcode.com/problems/isomorphic-strings/)            |
| 290 | Word Pattern                                  | Easy       | [Link](https://leetcode.com/problems/word-pattern/)                  |
| 202 | Happy Number                                  | Easy       | [Link](https://leetcode.com/problems/happy-number/)                  |
| 349 | Intersection of Two Arrays                    | Easy       | [Link](https://leetcode.com/problems/intersection-of-two-arrays/)    |
| 350 | Intersection of Two Arrays II                 | Easy       | [Link](https://leetcode.com/problems/intersection-of-two-arrays-ii/) |
| 138 | Copy List with Random Pointer                 | Medium     | [Link](https://leetcode.com/problems/copy-list-with-random-pointer/) |
| 347 | Top K Frequent Elements                       | Medium     | [Link](https://leetcode.com/problems/top-k-frequent-elements/)       |
| 219 | Contains Duplicate II                         | Easy       | [Link](https://leetcode.com/problems/contains-duplicate-ii/)         |
| 268 | Missing Number                                | Easy       | [Link](https://leetcode.com/problems/missing-number/)                |
| 169 | Majority Element                              | Easy       | [Link](https://leetcode.com/problems/majority-element/)              |
| 451 | Sort Characters By Frequency                  | Medium     | [Link](https://leetcode.com/problems/sort-characters-by-frequency/)  |
| 3   | Longest Substring Without Repeating Characters| Medium     | [Link](https://leetcode.com/problems/longest-substring-without-repeating-characters/) |
| 76  | Minimum Window Substring                      | Hard       | [Link](https://leetcode.com/problems/minimum-window-substring/)      |
| 23  | Merge k Sorted Lists                          | Hard       | [Link](https://leetcode.com/problems/merge-k-sorted-lists/)          |
| 146 | LRU Cache                                     | Medium     | [Link](https://leetcode.com/problems/lru-cache/)                     |
| 220 | Contains Duplicate III                        | Hard       | [Link](https://leetcode.com/problems/contains-duplicate-iii/)        |
| 403 | Frog Jump                                     | Hard       | [Link](https://leetcode.com/problems/frog-jump/)                     |
| 895 | Maximum Frequency Stack                       | Hard       | [Link](https://leetcode.com/problems/maximum-frequency-stack/)       |
| 560 | Subarray Sum Equals K                         | Medium     | [Link](https://leetcode.com/problems/subarray-sum-equals-k/)         |
| 287 | Find the Duplicate Number                     | Medium     | [Link](https://leetcode.com/problems/find-the-duplicate-number/)     |

---

## Tips for Using HashMap in LeetCode

1. **Frequency counting**: `map.put(num, map.getOrDefault(num, 0) + 1);`
2. **Two Sum pattern**: `if (map.containsKey(complement)) return new int[]{map.get(complement), i}; map.put(num, i);`
3. **Grouping pattern**: `map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);`
4. **Check duplicates**: `if (map.containsKey(key)) { /* found duplicate */ }`
5. **Use `getOrDefault()`** to avoid null checks
6. **Use `merge()`** to combine values for the same key
7. **Iterate `entrySet()`** when you need both key and value — avoids redundant `get()` calls
