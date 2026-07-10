# Binary Search

Binary search is a divide-and-conquer algorithm that finds the position of a target value within a **sorted** array. It repeatedly halves the search interval by comparing the target to the middle element.

**Core Idea:** Eliminate half the remaining elements in each step.

**Time Complexity:** O(log n) | **Space Complexity:** O(1) for iterative, O(log n) for recursive.

---

## 1. Search a 2D Matrix (`Solution.java`)

**LeetCode:** [74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/)

**Problem:** You are given an `m x n` integer matrix with the following two properties:
- Each row is sorted in non-decreasing order.
- The first integer of each row is greater than the last integer of the previous row.

Given an integer `target`, return `true` if `target` is in the matrix, otherwise return `false`. You must solve it in `O(log(m * n))` time complexity.

**Concept:** Treat the 2D matrix as a flattened 1D sorted array. Use binary search on the virtual 1D index and convert it back to 2D coordinates using:
- `row = mid / cols`
- `col = mid % cols`

**File:** `Solution.java`

**Example:**
```
matrix = [
  [ 1,  3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 60]
]
target = 13
```

### Dry Run: `searchMatrix(matrix, 13)`

```
r = 3, c = 4, left = 0, right = 11

Step 1: mid = 0 + (11 - 0) / 2 = 5
        row = 5 / 4 = 1, col = 5 % 4 = 1
        matrix[1][1] = 11 < 13 → left = 6

Step 2: mid = 6 + (11 - 6) / 2 = 8
        row = 8 / 4 = 2, col = 8 % 4 = 0
        matrix[2][0] = 23 > 13 → right = 7

Step 3: mid = 6 + (7 - 6) / 2 = 6
        row = 6 / 4 = 1, col = 6 % 4 = 2
        matrix[1][2] = 16 > 13 → right = 5

Step 4: left = 6, right = 5 → loop ends
        return false
```

**Output:** `false`

---

## 2. Search in Rotated Sorted Array (`rotated.java`)

**LeetCode:** [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)

**Problem:** You are given an integer array `nums` sorted in ascending order (with distinct values) and an integer `target`. Before being passed to your function, `nums` is possibly rotated at an unknown pivot index `k` (1 <= k < nums.length) such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]`. Given the array `nums` after the possible rotation and an integer `target`, return the index of `target` if it is in `nums`, or `-1` if it is not. You must write an algorithm with `O(log n)` runtime complexity.

**Concept:** At any `mid`, one half (left or right) is always sorted. Determine which half is sorted and check if the target lies in that sorted half to decide which direction to go.

**File:** `rotated.java`

**Example:**
```
nums = [4, 5, 6, 7, 0, 1, 2]
target = 0
```

### Dry Run: `search(nums, 0)`

```
left = 0, right = 6

Step 1: mid = 3
        nums[3] = 7 != 0
        nums[left] = 4 <= nums[mid] = 7 → Left half [4,5,6,7] is sorted
        nums[left] = 4 <= 0 < nums[mid] = 7? → NO
        left = mid + 1 = 4

Step 2: mid = 4 + (6 - 4) / 2 = 5
        nums[5] = 1 != 0
        nums[left] = 0 <= nums[mid] = 1 → Left half [0,1] is sorted
        nums[left] = 0 <= 0 < nums[mid] = 1? → YES
        right = mid - 1 = 4

Step 3: mid = 4 + (4 - 4) / 2 = 4
        nums[4] = 0 == target → return 4
```

**Output:** `4`

### Dry Run: `search(nums, 3)` (target not found)

```
nums = [4, 5, 6, 7, 0, 1, 2], target = 3

Step 1: left = 0, right = 6, mid = 3
        nums[3] = 7 != 3
        Left half [4,5,6,7] is sorted
        4 <= 3 < 7? → NO → left = 4

Step 2: left = 4, right = 6, mid = 5
        nums[5] = 1 != 3
        nums[left] = 0 <= nums[mid] = 1 → Left half is sorted
        0 <= 3 < 1? → NO → left = 6

Step 3: left = 6, right = 6, mid = 6
        nums[6] = 2 != 3
        nums[left] = 2 <= nums[mid] = 2 → Left half is sorted
        2 <= 3 < 2? → NO → left = 7

Step 4: left = 7 > right = 6 → loop ends, return -1
```

**Output:** `-1`

---

## 3. Koko Eating Bananas (`banana.java`)

**LeetCode:** [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/)

**Problem:** Koko loves to eat bananas. There are `n` piles of bananas, the `i`-th pile has `piles[i]` bananas. The guards have gone and will come back in `h` hours. Koko can decide her bananas-per-hour eating speed of `k`. Each hour, she chooses some pile of bananas and eats from it. If the pile has less than `k` bananas, she eats all of them instead and will not eat any more bananas during this hour. Koko likes to eat slowly but still wants to finish before the guards return. Return the **minimum** integer `k` such that she can eat all the bananas within `h` hours.

**Concept:** This is a classic "minimize the maximum" problem solved with binary search on the answer.
- **Search space:** `left = 1` (min speed), `right = max(pile)` (max speed — eats largest pile in 1 hour)
- For each `mid` speed, calculate total hours. If `hours <= h`, try smaller speed; otherwise increase.
- Ceiling division: `(pile + mid - 1) / mid` avoids floating point math.

**File:** `banana.java`

**Example:**
```
piles = [3, 6, 7, 11]
h = 8
```

### Dry Run: `minEatingSpeed(piles, 8)`

```
left = 1, right = 11 (max pile)

Step 1: mid = 1 + (11 - 1) / 2 = 6
        hours = ceil(3/6) + ceil(6/6) + ceil(7/6) + ceil(11/6)
              = 1 + 1 + 2 + 2 = 6
        6 <= 8 → right = 6 (try smaller speed)

Step 2: mid = 1 + (6 - 1) / 2 = 3
        hours = ceil(3/3) + ceil(6/3) + ceil(7/3) + ceil(11/3)
              = 1 + 2 + 3 + 4 = 10
        10 > 8 → left = mid + 1 = 4

Step 3: mid = 4 + (6 - 4) / 2 = 5
        hours = ceil(3/5) + ceil(6/5) + ceil(7/5) + ceil(11/5)
              = 1 + 2 + 2 + 3 = 8
        8 <= 8 → right = 5

Step 4: mid = 4 + (5 - 4) / 2 = 4
        hours = ceil(3/4) + ceil(6/4) + ceil(7/4) + ceil(11/4)
              = 1 + 2 + 2 + 3 = 8
        8 <= 8 → right = 4

Step 5: left = 4, right = 4 → loop ends, return 4
```

**Output:** `4` (minimum speed = 4 bananas/hour)

---

## 4. Median of Two Sorted Arrays (`mediansort.java`)

**LeetCode:** [4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)

**Problem:** Given two sorted integer arrays `nums1` and `nums2`, return the median of the two sorted arrays. The overall run time complexity should be `O(log (m + n))`. You must solve the problem without merging the two arrays into one.

**Concept:** The median splits a combined sorted array into two equal halves. The key insight is to **partition** both arrays such that the left half contains exactly `(m + n + 1) / 2` elements. We binary search on the smaller array to find the correct partition point:
- `cut1` = elements taken from `nums1` into the left half
- `cut2 = (m + n + 1) / 2 - cut1` = elements taken from `nums2` into the left half

After partitioning, we check if the partition is valid:
- `left1 <= right2` (max of left half of nums1 <= min of right half of nums2)
- `left2 <= right1` (max of left half of nums2 <= min of right half of nums1)

If valid:
- **Odd total:** median = `max(left1, left2)` (the extra element is on the left side)
- **Even total:** median = `(max(left1, left2) + min(right1, right2)) / 2.0`

We always binary search on the **smaller** array to minimize the search space.

**File:** `mediansort.java`

**Example:**
```
nums1 = [1, 3]
nums2 = [2]
```

### Dry Run: `findMedianSortedArrays([1,3], [2])`

```
nums1 = [1, 3] (m=2), nums2 = [2] (n=1)
left = 0, right = 2

Step 1: cut1 = 0 + (2 - 0) / 2 = 1
        cut2 = (2 + 1 + 1) / 2 - 1 = 1

        left1 = nums1[0] = 1    (cut1 != 0)
        right1 = nums1[1] = 3   (cut1 != m)
        left2 = MIN_VALUE        (cut2 == 0? NO → cut2=1)
        left2 = nums2[0] = 2    (cut2-1 = 0)
        right2 = MAX_VALUE       (cut2 == n)

        Check: left1=1 <= right2=MAX → YES
               left2=2 <= right1=3  → YES  ✓ Valid partition

        Total length = 3 (odd)
        median = max(left1, left2) = max(1, 2) = 2.0
```

**Output:** `2.0`

### Dry Run: `findMedianSortedArrays([1,2], [3,4])`

```
nums1 = [1,2] (m=2), nums2 = [3,4] (n=2)
left = 0, right = 2

Step 1: cut1 = 0 + (2 - 0) / 2 = 1
        cut2 = (2 + 2 + 1) / 2 - 1 = 1

        left1 = nums1[0] = 1
        right1 = nums1[1] = 2
        left2 = nums2[0] = 3
        right2 = nums2[1] = 4

        Check: left1=1 <= right2=4 → YES
               left2=3 <= right1=2 → NO  ✗ Invalid

        left2 > right1 → left = cut1 + 1 = 2

Step 2: cut1 = 2 + (2 - 2) / 2 = 2
        cut2 = 5 / 2 - 2 = 0

        left1 = nums1[1] = 2
        right1 = MAX_VALUE        (cut1 == m)
        left2 = MIN_VALUE         (cut2 == 0)
        right2 = nums2[0] = 3

        Check: left1=2 <= right2=3 → YES
               left2=MIN <= right1=MAX → YES  ✓ Valid

        Total length = 4 (even)
        median = (max(1,3) + min(MAX,3)) / 2.0
               Hmm — let's use exact values:

        max(left1, left2) = max(2, MIN) = 2
        min(right1, right2) = min(MAX, 3) = 3
        median = (2 + 3) / 2.0 = 2.5
```

**Output:** `2.5`

---

## 5. Time Based Key-Value Store (`TimeMap.java`)

**LeetCode:** [981. Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store/)

**Problem:** Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a particular timestamp. Implement the `TimeMap` class:
- `TimeMap()` Initializes the object of the data structure.
- `void set(String key, String value, int timestamp)` Stores the key `key` with the value `value` at the given time `timestamp`.
- `String get(String key, int timestamp)` Returns a value such that `set` was called previously with `timestamp_prev <= timestamp`. If there are multiple such values, it returns the one associated with the largest `timestamp_prev`. If no values are found, it returns `""`.

**Concept:** Use a `HashMap<String, List<Pair>>` where each key maps to a list of (value, timestamp) pairs sorted by timestamp (since `set` is called in increasing timestamp order). For `get`, perform binary search on the list to find the largest timestamp `<=` the given timestamp.

**File:** `TimeMap.java`

**Example:**
```
set("foo", "bar", 1)
set("foo", "bar2", 4)
get("foo", 5)
```

### Dry Run: `get("foo", 5)`

```
map = { "foo": [(value="bar", ts=1), (value="bar2", ts=4)] }

list = [(bar, 1), (bar2, 4)]
left = 0, right = 1, ans = ""

Step 1: mid = 0
        list[0].timestamp = 1 <= 5 → ans = "bar", left = 1

Step 2: mid = 1
        list[1].timestamp = 4 <= 5 → ans = "bar2", left = 2

Step 3: left = 2 > right = 1 → loop ends
        return "bar2"
```

**Output:** `"bar2"`

### Dry Run: `get("foo", 0)` (timestamp before all entries)

```
list = [(bar, 1), (bar2, 4)]
left = 0, right = 1, ans = ""

Step 1: mid = 0
        list[0].timestamp = 1 <= 0? → NO → right = -1

Step 2: left = 0 > right = -1 → loop ends
        return ""
```

**Output:** `""` (no entry at or before timestamp 0)

---

## Summary

| File | Problem | Technique | Time | Space |
|------|---------|-----------|------|-------|
| `Solution.java` | Search a 2D Matrix | 2D to 1D mapping | O(log(m*n)) | O(1) |
| `rotated.java` | Search in Rotated Sorted Array | Identify sorted half | O(log n) | O(1) |
| `banana.java` | Koko Eating Bananas | Binary search on answer | O(n * log(max)) | O(1) |
| `mediansort.java` | Median of Two Sorted Arrays | Partition-based binary search | O(log(min(m,n))) | O(1) |
| `TimeMap.java` | Time Based Key-Value Store | Binary search on timestamps | O(log n) get, O(1) set | O(n) |
