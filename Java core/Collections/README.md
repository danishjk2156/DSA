# Java Collections Framework

> A comprehensive guide to the most important Java Collection classes for coding interviews and everyday development.

---

## Table of Contents

1. [ArrayList](#1-arraylist)
2. [HashMap](#2-hashmap)
3. [HashSet](#3-hashset)
4. [TreeMap](#4-treemap)
5. [Queue](#5-queue)
6. [Deque](#6-deque)

---

## 1. ArrayList

### A. What is it?

ArrayList is a resizable array implementation of the `List` interface. Unlike a regular array that has a fixed size, ArrayList can grow and shrink dynamically as you add or remove elements. It stores elements in order and allows duplicate values.

### B. Why do we need it?

Regular arrays in Java have a fixed size — once you declare `int[] arr = new int[5]`, you're stuck with 5 elements. In real-world problems (reading user input, processing unknown amounts of data), you rarely know the size in advance. ArrayList solves this by automatically resizing itself, so you never have to worry about running out of space.

### C. Basic Syntax

```java
import java.util.ArrayList;

// Creation
ArrayList<Integer> list = new ArrayList<>();       // empty list
ArrayList<String> names = new ArrayList<>();        // empty list of strings

// Adding elements
list.add(10);              // add at end
list.add(0, 99);           // add at index 0

// Accessing elements
int first = list.get(0);   // get element at index 0

// Updating elements
list.set(0, 50);           // replace element at index 0

// Removing elements
list.remove(0);            // remove element at index 0
list.remove(Integer.valueOf(10));  // remove first occurrence of value 10

// Size
list.size();               // number of elements

// Check if element exists
list.contains(10);         // true or false
```

### D. Small Example

```java
import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {

        // Create a list of student grades
        ArrayList<Integer> grades = new ArrayList<>();

        // Add grades
        grades.add(85);
        grades.add(92);
        grades.add(78);
        grades.add(95);
        grades.add(88);

        System.out.println("All grades: " + grades);

        // Find the highest grade
        int highest = grades.get(0);
        for (int g : grades) {
            if (g > highest) {
                highest = g;
            }
        }
        System.out.println("Highest grade: " + highest);

        // Remove all grades below 85
        grades.removeIf(g -> g < 85);
        System.out.println("Grades >= 85: " + grades);

        // Calculate average
        double sum = 0;
        for (int g : grades) {
            sum += g;
        }
        System.out.println("Average of remaining: " + (sum / grades.size()));
    }
}
```

### E. Internals / How It Works

- Backed by a **regular array** internally (`Object[] elementData`).
- Default initial capacity is **10**.
- When the list exceeds its capacity, a new array of **1.5x size** is created and all elements are copied over — this is an **O(n)** operation.
- `get()` and `set()` are **O(1)** because it uses index-based access.
- `add()` at the end is **amortized O(1)**, but adding at index 0 or the middle is **O(n)** because elements must be shifted.
- `remove()` is **O(n)** because of the shifting.

### F. Common Mistakes / Interview Questions

| Mistake / Question | Answer |
|---|---|
| `ArrayList<int>` | **Won't compile.** ArrayList needs wrapper classes. Use `ArrayList<Integer>`. |
| How to remove elements while iterating? | Use `Iterator` and `it.remove()`, or use `list.removeIf()`. Never use a for-each loop with `list.remove()` — you'll get `ConcurrentModificationException`. |
| `ArrayList` vs `LinkedList`? | ArrayList is faster for random access (O(1) get), LinkedList is faster for insertions/deletions at both ends (O(1)). In practice, ArrayList wins most of the time due to cache locality. |
| What is the time complexity of `contains()`? | **O(n)** — it checks every element. For fast lookups, use a `HashSet`. |

---

## 2. HashMap

### A. What is it?

HashMap is a key-value pair data structure that stores data in buckets based on the hash code of the key. It provides O(1) average-time complexity for insertion, deletion, and lookup operations. Keys must be unique — if you put a duplicate key, the old value is replaced.

### B. Why do we need it?

Imagine you want to store the phone numbers of 1000 people. With an array, you'd need to search through all 1000 entries to find one person. HashMap lets you look up any person's phone number instantly by their name. It's the go-to data structure when you need fast lookups, fast insertions, and want to associate one piece of data with another (like word → definition, student → grade, etc.).

### C. Basic Syntax

```java
import java.util.HashMap;

// Creation
HashMap<String, Integer> map = new HashMap<>();

// Adding key-value pairs
map.put("Alice", 90);
map.put("Bob", 85);
map.put("Charlie", 92);

// Accessing values
int aliceScore = map.get("Alice");          // 90
int unknown = map.getOrDefault("Eve", 0);   // 0 (default if key not found)

// Checking keys / values
map.containsKey("Bob");      // true
map.containsValue(85);       // true

// Removing
map.remove("Bob");           // removes Bob's entry

// Size
map.size();                   // number of entries

// Iterating
for (String key : map.keySet()) {
    System.out.println(key + " = " + map.get(key));
}

for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " = " + entry.getValue());
}
```

### D. Small Example

```java
import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args) {

        // Count frequency of each character in a string
        String word = "mississippi";
        HashMap<Character, Integer> freq = new HashMap<>();

        for (char c : word.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        System.out.println("Character frequencies:");
        for (char key : freq.keySet()) {
            System.out.println(key + " -> " + freq.get(key));
        }

        // Find the most frequent character
        char mostFrequent = ' ';
        int maxCount = 0;
        for (char key : freq.keySet()) {
            if (freq.get(key) > maxCount) {
                maxCount = freq.get(key);
                mostFrequent = key;
            }
        }
        System.out.println("Most frequent: '" + mostFrequent + "' (appears " + maxCount + " times)");
    }
}
```

### E. Internals / How It Works

- Uses an **array of buckets** (default capacity 16, load factor 0.75).
- `hashCode()` of the key determines which bucket the entry goes into.
- If two keys have the same hash (collision), entries are stored in a **linked list** or **balanced tree** within that bucket.
  - Java 8+: When a bucket exceeds **8 entries**, the linked list is converted to a **red-black tree** for O(log n) lookup in the worst case.
- When the number of entries exceeds `capacity × load factor` (e.g., 16 × 0.75 = 12), the map **resizes** (doubles capacity) and rehashes all entries — **O(n)**.
- Average case: **O(1)** for put, get, remove.
- Worst case: **O(n)** or **O(log n)** with tree-ified buckets.

### F. Common Mistakes / Interview Questions

| Mistake / Question | Answer |
|---|---|
| Using a mutable object as a key | If you change a key's fields after putting it in the map, you may never find it again (hash changes). Use immutable keys like `String` or `Integer`. |
| `HashMap` vs `Hashtable`? | Hashtable is synchronized (thread-safe) but slow. HashMap is faster but not thread-safe. For thread safety, use `ConcurrentHashMap`. |
| What happens if two keys have the same hashCode? | They go into the same bucket. The map handles collisions via linked lists (or trees in Java 8+). |
| Can HashMap have null keys? | Yes, one null key is allowed. Multiple null values are allowed. |
| What is the difference between `HashMap` and `LinkedHashMap`? | LinkedHashMap maintains **insertion order** (or access order). HashMap has no guaranteed order. |

---

## 3. HashSet

### A. What is it?

HashSet is a collection that stores **unique elements** with no guaranteed order. It is backed by a HashMap internally — each element is stored as a key with a dummy value. It provides O(1) average-time complexity for add, remove, and contains operations.

### B. Why do we need it?

When you need to check if an element exists in a collection, or when you need to remove duplicates from a list, HashSet is the fastest option. It eliminates duplicate values automatically and provides instant membership checking — something that takes O(n) with a List but only O(1) with a HashSet.

### C. Basic Syntax

```java
import java.util.HashSet;

// Creation
HashSet<Integer> set = new HashSet<>();

// Adding elements
set.add(10);
set.add(20);
set.add(10);     // duplicate — ignored

// Checking existence
boolean exists = set.contains(10);   // true
boolean missing = set.contains(30);  // false

// Removing elements
set.remove(10);

// Size
set.size();

// Iterating
for (int num : set) {
    System.out.println(num);
}

// Set operations
HashSet<Integer> a = new HashSet<>(List.of(1, 2, 3));
HashSet<Integer> b = new HashSet<>(List.of(2, 3, 4));

a.addAll(b);          // Union: {1, 2, 3, 4}
a.retainAll(b);       // Intersection: {2, 3, 4}
a.removeAll(b);       // Difference: removes elements in b from a
```

### D. Small Example

```java
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

public class HashSetDemo {
    public static void main(String[] args) {

        // Find unique elements in a list
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 4, 3, 5, 1));
        System.out.println("Original list: " + numbers);

        HashSet<Integer> unique = new HashSet<>(numbers);
        System.out.println("Unique elements: " + unique);

        // Find duplicates (elements that appear more than once)
        HashSet<Integer> seen = new HashSet<>();
        HashSet<Integer> duplicates = new HashSet<>();

        for (int num : numbers) {
            if (!seen.add(num)) {
                // add returns false if already present
                duplicates.add(num);
            }
        }
        System.out.println("Duplicates: " + duplicates);

        // Check intersection of two groups
        HashSet<String> groupA = new HashSet<>(Arrays.asList("Alice", "Bob", "Charlie"));
        HashSet<String> groupB = new HashSet<>(Arrays.asList("Bob", "David", "Alice"));

        HashSet<String> common = new HashSet<>(groupA);
        common.retainAll(groupB);
        System.out.println("Common members: " + common);
    }
}
```

### E. Internals / How It Works

- Internally uses a **HashMap** where every element is a key and the value is a fixed dummy object (`PRESENT`).
- Same hashing and collision mechanics as HashMap.
- Does **not** allow null elements (one null is technically allowed).
- Unordered — elements may appear in any order because hashing determines placement.
- Average time: **O(1)** for add, remove, contains.

### F. Common Mistakes / Interview Questions

| Mistake / Question | Answer |
|---|---|
| Need sorted unique elements? | Use `TreeSet` instead. HashSet does not maintain order. |
| `HashSet` vs `TreeSet`? | HashSet: O(1) but unordered. TreeSet: O(log n) but sorted. |
| How to remove duplicates from an array? | Put all elements in a HashSet, then convert back. |
| `HashSet` vs `ArrayList` for `contains()`? | HashSet: O(1). ArrayList: O(n). Use HashSet when you only need membership checks. |
| Why is HashSet not thread-safe? | Use `Collections.synchronizedSet()` or `ConcurrentHashMap.newKeySet()` for thread safety. |

---

## 4. TreeMap

### A. What is it?

TreeMap is a `Map` implementation that stores key-value pairs sorted by the **natural ordering** of keys (or by a custom `Comparator`). It uses a **Red-Black tree** internally, guaranteeing O(log n) time for put, get, and remove operations.

### B. Why do we need it?

Sometimes you need your map data to be sorted — like a phone book (alphabetical), a leaderboard (by score), or a dictionary. HashMap gives you O(1) lookups but no ordering. TreeMap gives you O(log n) lookups **plus** guaranteed sorted order of keys. This is essential for problems that require traversal in sorted order or finding the smallest/largest key efficiently.

### C. Basic Syntax

```java
import java.util.TreeMap;

// Creation — keys sorted naturally (ascending)
TreeMap<String, Integer> map = new TreeMap<>();

// Adding
map.put("Banana", 3);
map.put("Apple", 5);
map.put("Cherry", 2);

// Accessing
map.get("Apple");             // 5
map.getOrDefault("Mango", 0); // 0

// Sorted traversal
System.out.println(map);  // {Apple=5, Banana=3, Cherry=2} — sorted by key

// First and last key (bonus: TreeMap-specific)
map.firstKey();    // "Apple"
map.lastKey();     // "Cherry"

// Navigating
map.lowerKey("Banana");    // "Apple"  (key just below)
map.higherKey("Banana");   // "Cherry" (key just above)

// Reverse order
TreeMap<String, Integer> reversed = new TreeMap<>(java.util.Comparator.reverseOrder());
reversed.putAll(map);
System.out.println(reversed);  // {Cherry=2, Banana=3, Apple=5}
```

### D. Small Example

```java
import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {

        // Student leaderboard sorted by score (descending)
        TreeMap<Integer, String> leaderboard = new TreeMap<>(java.util.Comparator.reverseOrder());

        leaderboard.put(95, "Alice");
        leaderboard.put(88, "Bob");
        leaderboard.put(92, "Charlie");
        leaderboard.put(85, "Diana");

        System.out.println("Leaderboard (highest first):");
        int rank = 1;
        for (int score : leaderboard.keySet()) {
            System.out.println(rank + ". " + leaderboard.get(score) + " - " + score);
            rank++;
        }

        // Find scores close to 90
        System.out.println("\nScores around 90:");
        Integer below = leaderboard.lowerKey(90);  // just below 90
        Integer above = leaderboard.higherKey(90);  // just above 90
        System.out.println("Below 90: " + below + " (" + leaderboard.get(below) + ")");
        System.out.println("Above 90: " + above + " (" + leaderboard.get(above) + ")");
    }
}
```

### E. Internals / How It Works

- Implemented as a **Red-Black tree** (a self-balancing binary search tree).
- Each node stores a key, value, left child, right child, parent, and color.
- All operations (get, put, remove) are **O(log n)** guaranteed — no worst-case degradation.
- Keys must be **Comparable** or you must provide a **Comparator** at creation time.
- Supports useful navigation methods: `firstKey()`, `lastKey()`, `lowerKey()`, `higherKey()`, `floorKey()`, `ceilingKey()`.

### F. Common Mistakes / Interview Questions

| Mistake / Question | Answer |
|---|---|
| Can keys be null? | **No.** TreeMap will throw `NullPointerException` if you try to put a null key. |
| `TreeMap` vs `HashMap`? | TreeMap: O(log n), sorted keys, uses more memory. HashMap: O(1), no order, less memory. Use TreeMap only when order matters. |
| `TreeMap` vs `LinkedHashMap`? | TreeMap sorts by key value. LinkedHashMap preserves insertion order. |
| When to use TreeMap in interviews? | When you need sorted keys, range queries, or the smallest/largest element efficiently. |
| What is a Red-Black tree? | A self-balancing BST where nodes are colored red/black to ensure the tree stays roughly balanced, guaranteeing O(log n) operations. |

---

## 5. Queue

### A. What is it?

A Queue is a **FIFO (First-In-First-Out)** collection — the first element added is the first one removed. Think of it like a line at a store: first person in line gets served first. Java provides several Queue implementations like `LinkedList`, `ArrayDeque`, and `PriorityQueue`.

### B. Why do we need it?

Many real-world and computational processes are naturally FIFO:
- **Task scheduling** — processes waiting to run
- **Breadth-First Search (BFS)** — exploring graphs level by level
- **Buffering** — print jobs, messages, requests waiting to be processed
- **Producer-Consumer pattern** — one thread produces data, another consumes it

Without queues, implementing these patterns would be messy and error-prone.

### C. Basic Syntax

```java
import java.util.Queue;
import java.util.LinkedList;

// Creation (LinkedList and ArrayDeque both implement Queue)
Queue<Integer> queue = new LinkedList<>();

// Adding elements
queue.offer(10);    // add to end, returns false if full (preferred over add())
queue.offer(20);
queue.offer(30);

// Removing elements
int front = queue.poll();     // remove and return front, returns null if empty (preferred over remove())
int second = queue.poll();

// Peeking
int next = queue.peek();      // view front without removing, returns null if empty

// Check if empty
queue.isEmpty();

// Size
queue.size();
```

**PriorityQueue (min-heap variant):**

```java
import java.util.PriorityQueue;

// Min-heap (smallest element comes out first)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.offer(30);
minHeap.offer(10);
minHeap.offer(20);
minHeap.poll();  // 10

// Max-heap (largest element comes out first)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(java.util.Comparator.reverseOrder());
maxHeap.offer(30);
maxHeap.offer(10);
maxHeap.offer(20);
maxHeap.poll();  // 30
```

### D. Small Example

```java
import java.util.Queue;
import java.util.LinkedList;

public class QueueDemo {
    public static void main(String[] args) {

        // BFS on a simple graph using a Queue
        // Graph: 0 -> 1, 2    |  1 -> 3    |  2 -> 3, 4    |  3 -> 4
        // Adjacency list
        int[][] graph = {
            {1, 2},
            {3},
            {3, 4},
            {4},
            {}
        };

        int start = 0;
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> bfsQueue = new LinkedList<>();

        bfsQueue.offer(start);
        visited[start] = true;

        System.out.print("BFS traversal: ");
        while (!bfsQueue.isEmpty()) {
            int node = bfsQueue.poll();
            System.out.print(node + " ");

            for (int neighbor : graph[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    bfsQueue.offer(neighbor);
                }
            }
        }
        // Output: 0 1 2 3 4
        System.out.println();

        // Simple task queue example
        Queue<String> taskQueue = new LinkedList<>();
        taskQueue.offer("Process payment");
        taskQueue.offer("Send email");
        taskQueue.offer("Update database");

        System.out.println("\nProcessing tasks:");
        while (!taskQueue.isEmpty()) {
            String task = taskQueue.poll();
            System.out.println("Done: " + task);
        }
    }
}
```

### E. Internals / How It Works

| Implementation | Backend | Time Complexity | Notes |
|---|---|---|---|
| `LinkedList` | Doubly linked list | O(1) add/remove | Also a List |
| `ArrayDeque` | Resizable circular array | O(1) amortized | **Fastest** Queue implementation |
| `PriorityQueue` | Heap (binary heap) | O(log n) insert, O(log n) remove | Not FIFO — sorted by priority |

- **`ArrayDeque`** is preferred over `LinkedList` for queue operations (and for stack operations too). It's faster due to array cache locality.
- **`PriorityQueue`** uses a min-heap internally. The smallest element is always at the root.

### F. Common Mistakes / Interview Questions

| Mistake / Question | Answer |
|---|---|
| `offer()`/`poll()` vs `add()`/`remove()`? | `offer()`/`poll()` return false/null on failure. `add()`/`remove()` throw exceptions. Always prefer `offer()`/`poll()` for safety. |
| When to use PriorityQueue? | When you always need the **minimum** (or maximum) element — like "Kth largest element", "merge K sorted lists", "median of data stream". |
| `Queue` vs `Deque`? | Queue is single-ended (add/remove from opposite ends). Deque is double-ended (add/remove from both ends). |
| Can Queue hold null? | `LinkedList` allows one null. `PriorityQueue` throws `NullPointerException` on null elements. |
| BFS uses which data structure? | Always a **Queue**. DFS uses a **Stack** (or recursion). |

---

## 6. Deque (Double-Ended Queue)

### A. What is it?

Deque (pronounced "deck") is a **double-ended queue** that allows you to add and remove elements from **both the front and the back** in O(1) time. It's more versatile than a Queue — you can use it as a queue (FIFO) or as a stack (LIFO).

### B. Why do we need it?

Java deprecated the `Stack` class because it was poorly designed. `Deque` (specifically `ArrayDeque`) is the recommended replacement for both stacks and queues. Additionally, many algorithms require working with both ends of a collection — like the **sliding window maximum** problem, **LRU cache** implementation, or **palindrome checking**. Deque handles all these efficiently.

### C. Basic Syntax

```java
import java.util.ArrayDeque;

// Creation
ArrayDeque<Integer> deque = new ArrayDeque<>();

// Adding elements
deque.offerFirst(10);    // add to front
deque.offerLast(20);     // add to end
deque.offer(30);         // same as offerLast
deque.addFirst(40);      // same as offerFirst, but throws exception if full
deque.addLast(50);       // same as offerLast, but throws exception if full

// Removing elements
deque.pollFirst();       // remove from front (null if empty)
deque.pollLast();        // remove from back (null if empty)
deque.poll();            // same as pollFirst

// Peeking
deque.peekFirst();       // view front (null if empty)
deque.peekLast();        // view back (null if empty)
deque.peek();            // same as peekFirst

// As a Stack (LIFO)
deque.push(100);         // add to front (same as offerFirst)
deque.pop();             // remove from front (same as pollFirst)

// As a Queue (FIFO)
deque.offer(200);        // add to end
deque.poll();            // remove from front
```

### D. Small Example

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class DequeDemo {
    public static void main(String[] args) {

        // --- Use as a Stack ---
        System.out.println("=== Stack (LIFO) ===");
        Deque<String> stack = new ArrayDeque<>();

        stack.push("First");
        stack.push("Second");
        stack.push("Third");

        System.out.println("Stack: " + stack);
        System.out.println("Pop: " + stack.pop());     // Third
        System.out.println("Pop: " + stack.pop());     // Second
        System.out.println("Top: " + stack.peek());    // First

        // --- Use as a Queue ---
        System.out.println("\n=== Queue (FIFO) ===");
        Deque<String> queue = new ArrayDeque<>();

        queue.offer("Customer 1");
        queue.offer("Customer 2");
        queue.offer("Customer 3");

        while (!queue.isEmpty()) {
            System.out.println("Serving: " + queue.poll());
        }

        // --- Sliding Window Maximum (Interview Classic) ---
        System.out.println("\n=== Sliding Window Maximum ===");
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = slidingWindowMax(nums, k);

        System.out.print("Window maxes: ");
        for (int val : result) {
            System.out.print(val + " ");
        }
        // Output: 3 3 5 5 6 7
        System.out.println();
    }

    // Classic interview problem using Deque
    public static int[] slidingWindowMax(int[] nums, int k) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            // Remove indices outside the window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove smaller elements (they're useless)
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }
}
```

### E. Internals / How It Works

- `ArrayDeque` uses a **circular array** with two pointers (`head` and `tail`).
- When the array fills up, it **doubles in size** and copies elements.
- **No null elements** are allowed — `null` throws `NullPointerException`.
- All operations (`addFirst`, `addLast`, `pollFirst`, `pollLast`) are **amortized O(1)**.
- It is **not thread-safe**. Use `ConcurrentLinkedDeque` for concurrent access.

| Operation | ArrayDeque | LinkedList |
|---|---|---|
| `addFirst`/`addLast` | O(1) amortized | O(1) |
| `removeFirst`/`removeLast` | O(1) amortized | O(1) |
| `contains()` | O(n) | O(n) |
| Memory | Less (array) | More (node pointers) |
| **Performance** | **Faster in practice** | Slower due to cache misses |

### F. Common Mistakes / Interview Questions

| Mistake / Question | Answer |
|---|---|
| `Deque` vs `Stack`? | Always use `Deque` (specifically `ArrayDeque`). Java's `Stack` class extends `Vector` (synchronized) and is considered legacy. |
| `Deque` vs `Queue`? | Deque is a superset of Queue. You can use Deque as a Queue. Prefer Deque when you need both ends. |
| Can Deque hold null? | **No.** Unlike `LinkedList`, `ArrayDeque` does not allow null elements. |
| `ArrayDeque` vs `LinkedList` as a Deque? | `ArrayDeque` is almost always faster due to better cache locality. Use `ArrayDeque` unless you need `LinkedList`-specific features. |
| When to use Deque in interviews? | Stack problems (balanced parentheses, DFS), BFS, sliding window, monotonic stack/deque problems, palindrome checking. |

---

## Quick Reference: When to Use What

| Data Structure | Use When... | Avoid When... |
|---|---|---|
| **ArrayList** | You need index access, dynamic sizing | You do frequent insertions/deletions in the middle |
| **HashMap** | You need key-value lookups in O(1) | You need sorted keys (use TreeMap) |
| **HashSet** | You need unique elements, fast membership checks | You need duplicates or sorted data |
| **TreeMap** | You need keys sorted, range queries | You don't need order (HashMap is faster) |
| **Queue** | You need FIFO processing, BFS | You need random access |
| **Deque** | You need stack + queue flexibility, sliding window | You only need a simple list |

---

## Time Complexity Cheat Sheet

| Operation | ArrayList | HashMap | HashSet | TreeMap | Queue (ArrayDeque) |
|---|---|---|---|---|---|
| Add | O(1)* | O(1)* | O(1)* | O(log n) | O(1)* |
| Remove | O(n) | O(1)* | O(1)* | O(log n) | O(1)* |
| Search/Contains | O(n) | O(1)* | O(1)* | O(log n) | O(n) |
| Get by Index | O(1) | — | — | — | — |

> * = amortized; worst case is higher due to resizing or collisions

---

*Written for Java interview prep and quick reference.*
