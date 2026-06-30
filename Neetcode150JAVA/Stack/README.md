# Stack Problems

## Concept

A **stack** is a linear data structure that follows **LIFO**: Last In, First Out.

Think of it like a pile of plates:
- The last plate placed on top is the first plate removed.
- You can only directly access the top item.

**Common stack operations:**
- `push(x)`: add `x` to the top
- `pop()`: remove and return the top item
- `peek()`: view the top item without removing it
- `isEmpty()`: check whether the stack has no items

**When to use a stack:**
- Matching pairs, like brackets or parentheses
- Processing expressions
- Tracking previous smaller/greater elements
- Handling monotonic increasing/decreasing patterns
- Simulating nested or reversible behavior

**Time complexity:** Most stack operations are `O(1)`.

---

## Problems

### 1. Valid Parentheses (`ValidParentheses.java`)

**LeetCode 20:** [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

**Problem:** Given a string containing `()`, `{}`, and `[]`, check whether every opening bracket is closed by the correct type of closing bracket in the correct order.

**Idea:** Use a stack to store opening brackets. When a closing bracket appears, it must match the most recent opening bracket.

**Approach:**
- Traverse each character.
- If it is an opening bracket, push it into the stack.
- If it is a closing bracket:
  - The stack must not be empty.
  - Pop the top bracket.
  - Check if the popped bracket matches the current closing bracket.
- At the end, the stack must be empty.

**Pseudocode:**
```text
function isValid(s):
    stack = empty stack

    for ch in s:
        if ch is opening bracket:
            push ch into stack
        else:
            if stack is empty:
                return false

            top = pop stack

            if ch does not match top:
                return false

    return stack is empty
```

**Dry Run:** `s = "{[()]}"`
```text
ch = {  -> push {        stack = [{]
ch = [  -> push [        stack = [{, []
ch = (  -> push (        stack = [{, [, (]
ch = )  -> pop ( match   stack = [{, []
ch = ]  -> pop [ match   stack = [{]
ch = }  -> pop { match   stack = []

stack is empty -> true
```

**Complexity:**
- Time: `O(n)`
- Space: `O(n)`

---

### 2. Min Stack (`MinStackDemo.java`)

**LeetCode 155:** [Min Stack](https://leetcode.com/problems/min-stack/)

**Problem:** Design a stack that supports `push`, `pop`, `top`, and `getMin` in constant time.

**Idea:** Use two stacks:
- `stack` stores all values.
- `minStack` stores the minimum values seen so far.

Whenever a new value is less than or equal to the current minimum, push it into `minStack`. When popping, if the popped value is equal to the current minimum, pop from `minStack` too.

**Approach:**
- `push(val)`:
  - Push `val` into the main stack.
  - If `minStack` is empty or `val <= minStack.peek()`, push `val` into `minStack`.
- `pop()`:
  - If top of main stack equals top of `minStack`, pop from `minStack`.
  - Pop from main stack.
- `top()`:
  - Return top of main stack.
- `getMin()`:
  - Return top of `minStack`.

**Pseudocode:**
```text
class MinStack:
    stack = empty stack
    minStack = empty stack

    function push(val):
        stack.push(val)
        if minStack is empty OR val <= minStack.peek():
            minStack.push(val)

    function pop():
        if stack.peek() == minStack.peek():
            minStack.pop()
        stack.pop()

    function top():
        return stack.peek()

    function getMin():
        return minStack.peek()
```

**Dry Run:**
```text
push(-2)
stack    = [-2]
minStack = [-2]

push(0)
stack    = [-2, 0]
minStack = [-2]

push(-3)
stack    = [-2, 0, -3]
minStack = [-2, -3]

getMin() -> -3

pop()
top of stack = -3, top of minStack = -3
pop both
stack    = [-2, 0]
minStack = [-2]

top()    -> 0
getMin() -> -2
```

**Complexity:**
- Time: `O(1)` for each operation
- Space: `O(n)`

---

### 3. Evaluate Reverse Polish Notation (`EvaluateRPN.java`)

**LeetCode 150:** [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/)

**Problem:** Evaluate an arithmetic expression written in Reverse Polish Notation. Operators come after operands.

Example: `["2", "1", "+", "3", "*"]` means `(2 + 1) * 3`.

**Idea:** Use a stack for numbers. When an operator appears, pop the last two numbers, apply the operation, and push the result back.

**Approach:**
- Traverse every token.
- If the token is a number, push it.
- If the token is an operator:
  - Pop second operand `b`.
  - Pop first operand `a`.
  - Compute `a operator b`.
  - Push the result.
- The final answer is the only value left in the stack.

**Pseudocode:**
```text
function evalRPN(tokens):
    stack = empty stack

    for token in tokens:
        if token is a number:
            stack.push(token as integer)
        else:
            b = stack.pop()
            a = stack.pop()

            if token == '+': stack.push(a + b)
            if token == '-': stack.push(a - b)
            if token == '*': stack.push(a * b)
            if token == '/': stack.push(a / b)

    return stack.peek()
```

**Dry Run:** `tokens = ["2", "1", "+", "3", "*"]`
```text
token = 2  -> push 2       stack = [2]
token = 1  -> push 1       stack = [2, 1]
token = +  -> pop 1 and 2  stack = []
              2 + 1 = 3
              push 3       stack = [3]
token = 3  -> push 3       stack = [3, 3]
token = *  -> pop 3 and 3
              3 * 3 = 9
              push 9       stack = [9]

result = 9
```

**Complexity:**
- Time: `O(n)`
- Space: `O(n)`

---

### 4. Car Fleet (`CarFleet.java`)

**LeetCode 853:** [Car Fleet](https://leetcode.com/problems/car-fleet/)

**Problem:** Cars are moving toward a target. A faster car cannot pass a slower car in front of it; if it catches up, they become one fleet. Return the number of fleets that reach the target.

**Idea:** Sort cars by position from closest to target to farthest. For each car, calculate how long it takes to reach the target. A farther car becomes a new fleet only if it takes longer than the fleet in front.

**Approach:**
- Pair each car's `position` with its `speed`.
- Sort cars by position in descending order.
- Track `currentTime`, the arrival time of the fleet in front.
- For each car:
  - Calculate `time = (target - position) / speed`.
  - If `time > currentTime`, this car cannot catch the fleet in front, so it starts a new fleet.
  - Update `currentTime`.

**Pseudocode:**
```text
function carFleet(target, position, speed):
    cars = pair(position[i], speed[i])
    sort cars by position descending

    fleets = 0
    currentTime = 0

    for car in cars:
        time = (target - car.position) / car.speed

        if time > currentTime:
            fleets = fleets + 1
            currentTime = time

    return fleets
```

**Dry Run:** `target = 12`, `position = [10,8,0,5,3]`, `speed = [2,4,1,1,3]`
```text
Cars after sorting by position descending:
(10,2), (8,4), (5,1), (3,3), (0,1)

Car (10,2): time = (12-10)/2 = 1
time 1 > currentTime 0 -> new fleet
fleets = 1, currentTime = 1

Car (8,4): time = (12-8)/4 = 1
time 1 <= currentTime 1 -> joins fleet ahead
fleets = 1

Car (5,1): time = (12-5)/1 = 7
time 7 > currentTime 1 -> new fleet
fleets = 2, currentTime = 7

Car (3,3): time = (12-3)/3 = 3
time 3 <= currentTime 7 -> joins fleet ahead
fleets = 2

Car (0,1): time = (12-0)/1 = 12
time 12 > currentTime 7 -> new fleet
fleets = 3

Result: 3
```

**Complexity:**
- Time: `O(n log n)` because of sorting
- Space: `O(n)` for storing car pairs

---

### 5. Largest Rectangle in Histogram (`LargestRectangleInHistogram.java`)

**LeetCode 84:** [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)

**Problem:** Given bar heights in a histogram, find the largest rectangle area.

**Idea:** Use a monotonic increasing stack of indices. When the current height is smaller than the height at the top index, the popped height can no longer extend to the right. Calculate its rectangle area.

**Approach:**
- Store indices in the stack, not heights.
- Keep heights in increasing order inside the stack.
- Iterate from `0` to `n`.
- At `i == n`, use height `0` to force all remaining bars to pop.
- While current height is smaller than the height at stack top:
  - Pop index from stack.
  - Use popped height as rectangle height.
  - Right boundary is `i`.
  - Left boundary is new stack top, or `-1` if stack is empty.
  - Width is `i - left - 1`.
  - Update maximum area.
- Push current index.

**Pseudocode:**
```text
function largestRectangleArea(heights):
    stack = empty stack of indices
    maxArea = 0
    n = length of heights

    for i from 0 to n:
        if i == n:
            currHeight = 0
        else:
            currHeight = heights[i]

        while stack is not empty AND currHeight < heights[stack.peek()]:
            height = heights[stack.pop()]

            if stack is empty:
                left = -1
            else:
                left = stack.peek()

            width = i - left - 1
            area = height * width
            maxArea = max(maxArea, area)

        stack.push(i)

    return maxArea
```

**Dry Run:** `heights = [2, 1, 5, 6, 2, 3]`
```text
i=0, height=2
push 0
stack = [0]

i=1, height=1
1 < heights[0]=2 -> pop 0
height=2, left=-1, width=1-(-1)-1=1, area=2
maxArea=2
push 1
stack = [1]

i=2, height=5
5 >= heights[1]=1 -> push 2
stack = [1, 2]

i=3, height=6
6 >= heights[2]=5 -> push 3
stack = [1, 2, 3]

i=4, height=2
2 < heights[3]=6 -> pop 3
height=6, left=2, width=4-2-1=1, area=6
maxArea=6

2 < heights[2]=5 -> pop 2
height=5, left=1, width=4-1-1=2, area=10
maxArea=10

push 4
stack = [1, 4]

i=5, height=3
3 >= heights[4]=2 -> push 5
stack = [1, 4, 5]

i=6, height=0
0 < heights[5]=3 -> pop 5
height=3, left=4, width=6-4-1=1, area=3

0 < heights[4]=2 -> pop 4
height=2, left=1, width=6-1-1=4, area=8

0 < heights[1]=1 -> pop 1
height=1, left=-1, width=6-(-1)-1=6, area=6

Result: 10
```

**Complexity:**
- Time: `O(n)`
- Space: `O(n)`

---

## Stack Pattern Summary

| Problem | Main Stack Use | Key Idea |
|---|---|---|
| Valid Parentheses | Store opening brackets | Most recent opening bracket must match next closing bracket |
| Min Stack | Maintain minimum values | Use a second stack for current minimums |
| Evaluate RPN | Store operands | Apply operator to last two operands |
| Car Fleet | Stack-like comparison after sorting | A car forms a new fleet only if its time is greater than the fleet ahead |
| Largest Rectangle | Monotonic increasing stack | Popped bar becomes the limiting height for an area |

