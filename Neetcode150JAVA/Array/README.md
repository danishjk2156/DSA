# Array Problems

## Concept

Array problems cover fundamental techniques for processing sequential data in memory. These problems test your ability to manipulate, search, and transform arrays efficiently.

**Key techniques used:**
- **Hashing** (HashMap/HashSet) for O(1) lookups
- **Prefix/Suffix products** for cumulative computations
- **Bucket sort** for frequency-based ordering
- **Constraint propagation** for validation problems

---

## Problems

### 1. Group Anagrams (`GroupAnagram.java`)
**LeetCode 49** | Group strings that are anagrams of each other.

**Approach:** For each string, sort its characters to create a canonical key. Use a HashMap mapping key → list of original strings.

**Dry Run:** `strs = ["eat", "tea", "tan", "ate", "nat", "bat"]`
```
"eat" → sorted "aet" → map: {"aet": ["eat"]}
"tea" → sorted "aet" → map: {"aet": ["eat", "tea"]}
"tan" → sorted "ant" → map: {"aet": ["eat","tea"], "ant": ["tan"]}
"ate" → sorted "aet" → map: {"aet": ["eat","tea","ate"], "ant": ["tan"]}
"nat" → sorted "ant" → map: {"aet": ["eat","tea","ate"], "ant": ["tan","nat"]}
"bat" → sorted "abt" → map: {"aet": [...], "ant": [...], "abt": ["bat"]}

Result: [["eat","tea","ate"], ["tan","nat"], ["bat"]]
```

---

### 2. Longest Consecutive Sequence (`LongestConsecutive.java`)
**LeetCode 128** | Find the length of the longest consecutive elements sequence in O(n).

**Approach:** Add all numbers to a HashSet. For each number, only start counting if `num-1` is NOT in the set (i.e., it's the start of a sequence). Then count consecutive numbers.

**Dry Run:** `nums = [100, 4, 200, 1, 3, 2]`
```
Set = {100, 4, 200, 1, 3, 2}

100: 99 not in set → start. 101 not in set → streak=1
4: 3 IS in set → skip (not start)
200: 199 not in set → start. 201 not in set → streak=1
1: 0 not in set → start. 2 in set → streak=2. 3 in set → streak=3. 4 in set → streak=4. 5 not in set → stop. max=4
3: 2 IS in set → skip
2: 1 IS in set → skip

Result: 4
```

---

### 3. Product of Array Except Self (`productExceptSelf.java`)
**LeetCode 238** | Return an array where `res[i]` = product of all elements except `nums[i]` (without division, O(n)).

**Approach:** Two passes. First pass: prefix products stored in result array. Second pass: multiply by suffix product while tracking it.

**Dry Run:** `nums = [1, 2, 4, 6]`
```
Pass 1 (prefix):
  res[0] = 1
  res[1] = 1 × 1 = 1
  res[2] = 1 × 2 = 2
  res[3] = 2 × 4 = 8
  res = [1, 1, 2, 8]

Pass 2 (suffix):
  suffix = 1
  i=3: res[3] = 8 × 1 = 8, suffix = 1 × 6 = 6
  i=2: res[2] = 2 × 6 = 12, suffix = 6 × 4 = 24
  i=1: res[1] = 1 × 24 = 24, suffix = 24 × 2 = 48
  i=0: res[0] = 1 × 48 = 48, suffix = 48 × 1 = 48

Result: [48, 24, 12, 8]
```

---

### 4. Top K Frequent Elements (`topk.java`)
**LeetCode 347** | Return the k most frequent elements.

**Approach:** Count frequencies using HashMap. Use **bucket sort** — create an array of lists where index = frequency. Iterate from highest frequency to lowest.

**Dry Run:** `nums = [1,1,1,2,2,3], k = 2`
```
Frequency map: {1:3, 2:2, 3:1}
Bucket array (size = 7):
  bucket[3] = [1]
  bucket[2] = [2]
  bucket[1] = [3]

Iterate from end:
  i=6: null → skip
  i=5: null → skip
  i=4: null → skip
  i=3: [1] → result = [1], size=1
  i=2: [2] → result = [1, 2], size=2 = k → stop

Result: [1, 2]
```

---

### 5. Valid Sudoku (`ValidSudoku.java`)
**LeetCode 36** | Determine if a 9×9 Sudoku board is valid (no duplicate in row/col/3×3 box).

**Approach:** Use 3 arrays of HashSets (rows, cols, boxes). For each cell, compute boxIndex = `(i/3)*3 + j/3`. Check and add value to corresponding sets.

**Dry Run:** `board = [["1","2",".",".","3",...]]`
```
i=0,j=0 val="1" boxIndex=0
  rows[0]: add "1"  cols[0]: add "1"  boxes[0]: add "1"

i=0,j=1 val="2" boxIndex=0
  rows[0]: add "2"  cols[1]: add "2"  boxes[0]: add "2"

i=0,j=4 val="3" boxIndex=1
  rows[0]: add "3"  cols[4]: add "3"  boxes[1]: add "3"

...continues for all 81 cells. If any duplicate found, return false.
If all cells validated without conflict → return true
```
