# Two Pointer Technique

## Concept

The **two-pointer** technique uses two pointers (indices) to traverse an array or string, usually from opposite ends or at different speeds. This reduces time complexity from O(n²) to O(n) for problems involving pairs or subarrays.

**Common patterns:**
- **Opposite ends**: One pointer at start, one at end, moving toward each other
- **Same direction**: Both pointers start at the same end, one moving faster

---

## Problems

### 1. Valid Palindrome (`ValidPalindrome.java`)
**LeetCode 125** | Check if a string is a palindrome considering only alphanumeric characters and ignoring case.

**Approach:** Clean the string (keep only letters/digits, lowercase), then use two pointers from both ends comparing characters.

**Dry Run:** `s = "A man, a plan, a canal: Panama"`
```
Cleaned: "amanaplanacanalpanama"
i=0(a) → ← j=29(a) ✓
i=1(m) → ← j=28(m) ✓
i=2(a) → ← j=27(a) ✓
...all match → return true
```

---

### 2. Two Sum II - Sorted (`TwoSumSorted.java`)
**LeetCode 167** | Find two numbers in a sorted array that sum to target. Return 1-indexed positions.

**Approach:** Two pointers at opposite ends. If sum > target, move right pointer left. If sum < target, move left pointer right.

**Dry Run:** `numbers = [2, 7, 11, 15], target = 9`
```
i=0(2)  j=3(15)  sum=17 > 9 → j--
i=0(2)  j=2(11)  sum=13 > 9 → j--
i=0(2)  j=1(7)   sum=9 == 9 → return [1, 2]
```

---

### 3. 3Sum (`ThreeSum.java`)
**LeetCode 15** | Find all unique triplets that sum to zero.

**Approach:** Sort array. Fix one element, use two pointers for the remaining two. Skip duplicates. Early break/continue optimizations.

**Dry Run:** `nums = [-1, 0, 1, 2, -1, -4]`
```
Sorted: [-4, -1, -1, 0, 1, 2]
i=0(-4)  left=1(-1) right=5(2)  sum=-3 < 0 → left++
i=0(-4)  left=2(-1) right=5(2)  sum=-3 < 0 → left++
i=0(-4)  left=3(0)  right=5(2)  sum=-2 < 0 → left++
i=0(-4)  left=4(1)  right=5(2)  sum=-1 < 0 → left++  (left>right, stop)

i=1(-1)  left=2(-1) right=5(2)  sum=0 → add [-1,-1,2]
         left=3(0)  right=4(1)  sum=0 → add [-1,0,1]
         (skip duplicate -1 at i=2)

Result: [[-1,-1,2],[-1,0,1]]
```

---

### 4. Container With Most Water (`MA.java`)
**LeetCode 11** | Find two lines that together with the x-axis form a container holding the most water.

**Approach:** Two pointers at ends. Calculate area = min(height[left], height[right]) * width. Move the shorter line inward.

**Dry Run:** `height = [1, 8, 6, 2, 5, 4, 8, 3, 7]`
```
l=0(1) r=8(7) area=min(1,7)×8=8   max=8  1<7 → l++
l=1(8) r=8(7) area=min(8,7)×7=49  max=49 8>7 → r--
l=1(8) r=7(3) area=min(8,3)×6=18  max=49 8>3 → r--
l=1(8) r=6(8) area=min(8,8)×5=40  max=49 8=8 → r--
l=1(8) r=5(4) area=min(8,4)×4=16  max=49 8>4 → r--
l=1(8) r=4(5) area=min(8,5)×3=15  max=49 8>5 → r--
l=1(8) r=3(2) area=min(8,2)×2=4   max=49 8>2 → r--
l=1(8) r=2(6) area=min(8,6)×1=6   max=49
Result: 49
```

---

### 5. Trapping Rain Water (`TrapWater.java`)
**LeetCode 42** | Calculate how much water can be trapped between bars.

**Approach:** Two pointers from ends. Track leftMax and rightMax. At each step, process the smaller side — if current height < max, add water; otherwise update max.

**Dry Run:** `height = [0, 2, 0, 3, 1, 0, 1, 3, 2, 1]`
```
l=0(0) r=9(1) 0<1  leftMax=0  height=0, update leftMax=0  l=1
l=1(2) r=9(1) 2>1  rightMax=1 height=1, rightMax >= 1? 1>=1 yes, update rightMax=1  r=8
l=1(2) r=8(2) 2=2  (else) rightMax=1 height=2, 2>=1 yes, update rightMax=2  r=7
l=1(2) r=7(3) 2<3  leftMax=0 height=2, 2>=0 yes, update leftMax=2  l=2
l=2(0) r=7(3) 0<3  leftMax=2 height=0, 0<2 → water += 2-0 = 2  l=3
l=3(3) r=7(3) 3=3  rightMax=2 height=3, 3>=2 yes, update rightMax=3  r=6
l=3(3) r=6(1) 3>1  rightMax=3 height=1, 1<3 → water += 3-1 = 4  r=5
l=3(3) r=5(0) 3>0  rightMax=3 height=0, 0<3 → water += 3-0 = 7  r=4
l=3(3) r=4(1) 3>1  rightMax=3 height=1, 1<3 → water += 3-1 = 9  r=3
(l>=r, stop)
Result: 9
```
