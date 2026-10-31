# Matrix Chain Multiplication

Blind 180 DP · Interval (partition) · Hard

## Problem

```text
Given a chain of matrices A1, A2, A3,.....An, you have to figure out the most efficient way to multiply these
matrices. In other words, determine where to place parentheses to minimize the number of multiplications.
Given an array nums of size n. Dimension of matrix Ai ( 0 < i < n ) is nums[i - 1] x nums[i]. Find a minimum number of
multiplications needed to multiply the chain.

Example 1:
Input : nums = [10, 15, 20, 25]
Output : 8000
Explanation : There are two ways to multiply the chain - A1*(A2*A3) or (A1*A2)*A3.
If we multiply in order- A1*(A2*A3), then number of multiplications required are 11250.
If we multiply in order- (A1*A2)*A3, then number of multiplications required are 8000.
Thus minimum number of multiplications required is 8000.

Example 2:
Input : nums = [4, 2, 3]
Output : 24
Explanation : There is only one way to multiply the chain - A1*A2.
Thus minimum number of multiplications required is 24.

Constraints:
2 <= n <= 100
1 <= nums[i] <= 100
```

`nums` of size n describes **n − 1 matrices**; matrix Aᵢ has dimensions `nums[i-1] × nums[i]`.

- Multiplying a `p × q` matrix by a `q × r` matrix costs **`p · q · r`** scalar multiplications and produces a `p × r` matrix.
- The **order** of the matrices is fixed, but the **grouping** (where the parentheses go) can be chosen.
- Every grouping gives the same final matrix, but the total cost differs → find the **minimum total cost**.

Example: `nums = [10, 15, 20, 25]` → A1 = 10×15, A2 = 15×20, A3 = 20×25

| Grouping | Steps | Total |
|---|---|---|
| (A1·A2)·A3 | 10·15·20 = 3000, then 10·20·25 = 5000 | **8000** |
| A1·(A2·A3) | 15·20·25 = 7500, then 10·15·25 = 3750 | 11250 |

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_RecursiveNaive.java`](A_RecursiveNaive.java) | Recursive (naive) |
| [`B_Memoization.java`](B_Memoization.java) | Memoization |
| [`C_Tabulation.java`](C_Tabulation.java) | Tabulation |

---

## Key insight

Think about the **last** multiplication, not the first.

Whatever grouping is chosen, the very last step multiplies a **left group** and a **right group**:

```
nums[left] × nums[k] × nums[right]
```

- `k` is the **split point**, strictly between the two ends.
- The left group is the chain `nums[left..k]`, the right group is the chain `nums[k..right]`.
- Each group is itself a smaller matrix-chain problem → the **same function on a smaller range**.

---

## State

```
f(left, right) = minimum cost to multiply the chain described by nums[left..right]
                 (i.e. matrices left+1 … right)
```

Number of matrices in a section: `right − left`.

---

## Recurrence

```
f(left, right) = min over k in (left+1 … right-1) of
                 f(left, k) + f(k, right) + nums[left] * nums[k] * nums[right]
```

- Different values of `k` are **alternatives** (you pick one) → combine with **`min`**
- Left part, right part and the last step **all happen** → combine with **`+`**

## Base case

```
left + 1 == right  →  0     (only one matrix, nothing to multiply)
```

No separate 3-element base case is needed; the general step handles it.

## Answer

```
f(0, n-1)
```

The main function just calls the helper. No loop there.

---

## Tabulation fill order

`dp[left][right]` reads:
- `dp[left][k]` → same row, **smaller** `right` → `right` must go **upwards**
- `dp[k][right]` → rows **below** (`k > left`) → `left` must go **downwards**

So: `left` from n−1 **down** to 0, `right` from `left + 2` **up** to n−1.

---

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive | O(3ⁿ) | O(n) stack |
| Memoization | O(n³) | O(n²) table + O(n) stack |
| Tabulation | O(n³) | O(n²) |

No space optimization: a cell reads its **whole column below it**, not just the next row, so old rows can't be discarded.

---

## Why the naive TC is O(3ⁿ) — step by step

### Step 1: Define what we're counting

Let **`m`** = the number of matrices in a section = `right − left`.

Let **`T(m)`** = the total number of `helper` calls made when you call `helper` on a section of `m` matrices (counting the call itself).

### Step 2: Write down what one call does

A call on a section of `m` matrices:
- counts as **1 call** itself,
- loops over every split `k`. A split leaves **`j` matrices on the left** and **`m − j` on the right**, for `j = 1, 2, …, m−1`,
- for each split, makes **one left call** (size `j`) and **one right call** (size `m − j`).

So:

```
T(m) = 1 + [T(1) + T(m−1)] + [T(2) + T(m−2)] + … + [T(m−1) + T(1)]
       ↑         ↑                 ↑                       ↑
     itself   split j=1         split j=2             split j=m−1
```

Base case: a single matrix makes no further calls → **T(1) = 1**.

### Step 3: Notice every size appears twice

Look at the left sizes and right sizes across all the splits:

| Split | Left size | Right size |
|---|---|---|
| j = 1 | 1 | m−1 |
| j = 2 | 2 | m−2 |
| … | … | … |
| j = m−1 | m−1 | 1 |

The left column is `1, 2, …, m−1`. The right column is the **same numbers in reverse**.
So every size from 1 to m−1 appears **once as a left part and once as a right part**:

```
T(m) = 1 + 2 · [ T(1) + T(2) + … + T(m−1) ]
```

### Step 4: Check it on a small case by hand

For m = 3 (e.g. `nums = [10, 15, 20, 25]`), the call tree is:

```
helper(0,3)                              m=3  → 1 call
├── split k=1
│   ├── helper(0,1)                      m=1  → 1 call
│   └── helper(1,3)                      m=2  → 1 call
│       ├── helper(1,2)                  m=1  → 1 call
│       └── helper(2,3)                  m=1  → 1 call
└── split k=2
    ├── helper(0,2)                      m=2  → 1 call
    │   ├── helper(0,1)                  m=1  → 1 call
    │   └── helper(1,2)                  m=1  → 1 call
    └── helper(2,3)                      m=1  → 1 call
```

Total: **9 calls**. Notice `helper(0,1)`, `helper(1,2)` and `helper(2,3)` are each computed **twice** — that's the repeated work memoization removes.

### Step 5: Compute the first few values

| m | Formula | T(m) |
|---|---|---|
| 1 | base case | **1** |
| 2 | 1 + 2·(1) | **3** |
| 3 | 1 + 2·(1 + 3) | **9** ✅ matches the tree |
| 4 | 1 + 2·(1 + 3 + 9) | **27** |
| 5 | 1 + 2·(1 + 3 + 9 + 27) | **81** |

Pattern: 1, 3, 9, 27, 81 → **T(m) = 3^(m−1)**. Each extra matrix triples the work.

### Step 6: Prove the tripling (subtract consecutive formulas)

Write the formula for `m` and for `m − 1` one under the other:

```
T(m)   = 1 + 2·[ T(1) + … + T(m−2) + T(m−1) ]
T(m−1) = 1 + 2·[ T(1) + … + T(m−2) ]
```

The second line is the first line **without the last term**. Subtract:

```
T(m) − T(m−1) = 2 · T(m−1)
T(m)          = 3 · T(m−1)
```

Starting from T(1) = 1 → T(m) = 3^(m−1).

### Step 7: Convert to n

The whole problem has `m = n − 1` matrices, so the top-level call costs:

```
T(n−1) = 3^(n−2)  →  O(3ⁿ)
```

### Why 3 and not 2?

Compare with the 1D LIS recursion, where `T(i) = 1 + T(0) + … + T(i−1)` → each size called **once** → `T(i) = 2·T(i−1)` → **O(2ⁿ)**.

Here every smaller size is called **twice** (once as a left part, once as a right part) → the `2·` in front of the sum → `T(m) = 3·T(m−1)` → **O(3ⁿ)**.

General trick: for a recurrence of the form `T(m) = 1 + c·[T(1) + … + T(m−1)]`, subtracting consecutive terms gives `T(m) = (c + 1)·T(m−1)` → **O((c + 1)ᵐ)**.

### Side note: Catalan numbers

You may see this problem linked to **Catalan numbers** (~4ⁿ / n^1.5). That counts the number of **different groupings** (5 for 4 matrices, 4862 for 10 matrices) — a different quantity from the number of **recursive calls**. Either way: exponential.

---

## Why memoized TC is O(n³)

```
total work = (number of distinct states) × (work per state)
```

**Number of states:** only pairs with `left < right` are used — the cells above the diagonal.

```
n = 4:        right = 0  1  2  3
  left = 0          ·  ✓  ✓  ✓    ← 3
  left = 1          ·  ·  ✓  ✓    ← 2
  left = 2          ·  ·  ·  ✓    ← 1
  left = 3          ·  ·  ·  ·    ← 0
```

Count = (n−1) + (n−2) + … + 1 = **n(n−1)/2** = n²/2 − n/2 → **O(n²)** (drop the ½ and the smaller n term).

**Work per state:** the loop over `k` runs `right − left − 1` times. Each iteration is **O(1)** because the two recursive calls are memoized lookups (their own loops are counted under *their* states). So work per state = **O(right − left) ≤ O(n)**.

**Total:** O(n²) × O(n) = **O(n³)**.

---

## Mistakes I made

1. **Recursing on "which pair to multiply first".** The array changes shape after every step, so the state would be the whole remaining array (can't be memoized), and the same grouping gets counted multiple times via different orders. → Split on the **last** multiplication instead.
2. **Thinking the left and right parts need separate functions.** Both are "a section with two ends" → the same function, called with different arguments.
3. **Base case at 3 elements.** A part can have only 2 elements (one matrix), e.g. the right part `[25, 30]` → its cost is 0. Use `left + 1 == right → 0`.
4. **Using `+=` over the split options.** Different splits are alternatives → `min`, not sum.
5. **Using `min(leftCost, rightCost)`.** Both parts must be built → `leftCost + rightCost`.
6. **Forgetting the last-step cost** `nums[left] * nums[k] * nums[right]`. Every call adds its own; it doesn't come from a base case.
7. **Duplicating the helper's loop in the main function.** It was identical to `helper(0, n-1)`, and it returned `Integer.MAX_VALUE` for n = 2 (one matrix).
8. **Including the ends as split points** (`k = left` or `k = right`). Those aren't real multiplications; `k` must be strictly inside the range.
9. **(Tabulation) Declaring the running minimum outside the cell loops.** It carried values from one cell to the next. Reset it for every `(left, right)`.
10. **(Tabulation) Leaving single-matrix cells at `MAX_VALUE`.** They must be 0; otherwise adding two `MAX_VALUE`s overflows to a negative number.
11. **(Complexity) Assuming naive TC is O(2ⁿ).** Each call loops over up to m−1 splits with two calls each → O(3ⁿ) (see derivation above).
12. **(Complexity) Counting the recursive calls' loops inside the parent's work** when analysing memoization. With memoization, each child call is an O(1) lookup from the parent's point of view.

---

## Related

**Split-point interval DP** — state `(left, right)`, try every split `k` inside, combine both sides plus a "joining" cost:

- Burst Balloons
- Minimum Cost to Cut a Stick
- Palindrome Partitioning II

Usually the only new work is figuring out what the joining cost is.
- [Longest Palindromic Subsequence](../../../C_Sequence/A_Medium/P03_LongestPalindromicSubsequence/) — interval DP without split points
- Pattern: [Complexity](../../../../../../../../patterns/complexity.md)
