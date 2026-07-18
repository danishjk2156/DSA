# Java Collection Framework — HashSet

## What is a HashSet?

A `HashSet` is a part of Java's **Collection Framework** that implements the `Set` interface. It uses a **hash table** internally (backed by a `HashMap`) to store elements. It **does not allow duplicate values** and makes **no guarantees** about the order of elements.

```java
import java.util.HashSet;

HashSet<Integer> set = new HashSet<>();
```

---

## How It Works Internally

- Each element is passed through a **hash function** to compute a hash code.
- This hash code determines the **bucket** (index) where the element is stored.
- If two elements have the same hash code (collision), they are stored in the same bucket using a **linked list** or **balanced tree** (since Java 8).

---

## Time Complexity

| Operation          | Average | Worst Case |
|--------------------|---------|------------|
| `add()`            | O(1)    | O(n)       |
| `remove()`         | O(1)    | O(n)       |
| `contains()`       | O(1)    | O(n)       |
| `size()`           | O(1)    | O(1)       |
| `isEmpty()`        | O(1)    | O(1)       |

> Worst case O(n) occurs when all elements hash to the same bucket. In practice, average O(1) is expected.

---

## Key Methods

### Creating a HashSet

```java
HashSet<String> set = new HashSet<>();          // empty set
HashSet<String> set = new HashSet<>(list);      // from a collection
```

### Adding Elements

```java
set.add("Apple");       // returns true if added
set.add("Apple");       // returns false (duplicate, not added)
```

### Removing Elements

```java
set.remove("Apple");            // removes by value
set.removeIf(s -> s.startsWith("A"));  // remove by condition
```

### Checking Elements

```java
set.contains("Apple");   // returns true or false
set.isEmpty();           // returns true if empty
set.size();              // returns number of elements
```

### Iterating

```java
for (String s : set) {
    System.out.println(s);
}

set.forEach(s -> System.out.println(s));
```

### Conversion

```java
// Set to Array
String[] arr = set.toArray(new String[0]);

// Array to Set
Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));

// Set to List
List<String> list = new ArrayList<>(set);
```

### Set Operations

```java
// Union
Set<Integer> a = new HashSet<>(Set.of(1, 2, 3));
Set<Integer> b = new HashSet<>(Set.of(3, 4, 5));
a.addAll(b);              // a = {1, 2, 3, 4, 5}

// Intersection
Set<Integer> a = new HashSet<>(Set.of(1, 2, 3));
Set<Integer> b = new HashSet<>(Set.of(3, 4, 5));
a.retainAll(b);           // a = {3}

// Difference
Set<Integer> a = new HashSet<>(Set.of(1, 2, 3));
Set<Integer> b = new HashSet<>(Set.of(3, 4, 5));
a.removeAll(b);           // a = {1, 2}
```

---

## Other Set Implementations

| Class              | Ordered | Sorted | Thread-Safe | Null Elements |
|--------------------|---------|--------|-------------|---------------|
| `HashSet`          | No      | No     | No          | 1 null        |
| `LinkedHashSet`    | Yes     | No     | No          | 1 null        |
| `TreeSet`          | No      | Yes    | No          | No            |
| `CopyOnWriteArraySet` | No   | No     | Yes         | 1 null        |

---

## When to Use HashSet

- **Remove duplicates** from an array or list
- **Check membership** — does this element exist? O(1) lookup
- **Set operations** — union, intersection, difference
- **Track visited elements** in graph/tree traversals
- **Two Sum / Anagram problems** — fast element presence checks

### vs ArrayList

| Operation           | HashSet | ArrayList |
|---------------------|---------|-----------|
| `contains(x)`       | O(1)    | O(n)      |
| `add(x)`            | O(1)    | O(1)*     |
| `remove(x)`         | O(1)    | O(n)      |
| Maintains order     | No      | Yes       |
| Allows duplicates   | No      | Yes       |

> *amortized

---

## LeetCode Questions Using HashSet

| #   | Title                                         | Difficulty | Link                                                                 |
|-----|-----------------------------------------------|------------|----------------------------------------------------------------------|
| 217 | Contains Duplicate                            | Easy       | [Link](https://leetcode.com/problems/contains-duplicate/)            |
| 219 | Contains Duplicate II                         | Easy       | [Link](https://leetcode.com/problems/contains-duplicate-ii/)         |
| 128 | Longest Consecutive Sequence                  | Medium     | [Link](https://leetcode.com/problems/longest-consecutive-sequence/)  |
| 49  | Group Anagrams                                | Medium     | [Link](https://leetcode.com/problems/group-anagrams/)                |
| 242 | Valid Anagram                                 | Easy       | [Link](https://leetcode.com/problems/valid-anagram/)                 |
| 1   | Two Sum                                       | Easy       | [Link](https://leetcode.com/problems/two-sum/)                       |
| 202 | Happy Number                                  | Easy       | [Link](https://leetcode.com/problems/happy-number/)                  |
| 349 | Intersection of Two Arrays                    | Easy       | [Link](https://leetcode.com/problems/intersection-of-two-arrays/)    |
| 350 | Intersection of Two Arrays II                 | Easy       | [Link](https://leetcode.com/problems/intersection-of-two-arrays-ii/) |
| 205 | Isomorphic Strings                            | Easy       | [Link](https://leetcode.com/problems/isomorphic-strings/)            |
| 290 | Word Pattern                                  | Easy       | [Link](https://leetcode.com/problems/word-pattern/)                  |
| 383 | Ransom Note                                   | Easy       | [Link](https://leetcode.com/problems/ransom-note/)                   |
| 141 | Linked List Cycle                             | Easy       | [Link](https://leetcode.com/problems/linked-list-cycle/)             |
| 160 | Intersection of Two Linked Lists              | Easy       | [Link](https://leetcode.com/problems/intersection-of-two-linked-lists/) |
| 268 | Missing Number                                | Easy       | [Link](https://leetcode.com/problems/missing-number/)                |
| 448 | Find All Numbers Disappeared in an Array      | Easy       | [Link](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) |
| 653 | Two Sum IV - Input BST                        | Easy       | [Link](https://leetcode.com/problems/two-sum-iv-input-is-a-bst/)    |
| 220 | Contains Duplicate III                        | Hard       | [Link](https://leetcode.com/problems/contains-duplicate-iii/)        |
| 30 | Substring with Concatenation of All Words     | Hard       | [Link](https://leetcode.com/problems/substring-with-concatenation-of-all-words/) |
| 36 | Valid Sudoku                                  | Medium     | [Link](https://leetcode.com/problems/valid-sudoku/)                  |

---

## Tips for Using HashSet in LeetCode

1. **Duplicate check**: `if (set.contains(num)) return true; set.add(num);`
2. **Fast lookup instead of nested loops**: reduces O(n²) to O(n)
3. **Use `HashSet<Integer>` for arrays**, `HashSet<String>` for string problems
4. **Combine with other structures**: HashSet + HashMap, HashSet + Two Pointers
5. **Initialization from array**: `new HashSet<>(Arrays.asList(arr))` or loop and add
