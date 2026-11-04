# How to compute time and space complexity

A step-by-step method that works for any problem, so complexity is **counted**, not guessed from what the code looks like or from memory of similar problems.

For a one-page summary of results, see [complexity.md](complexity.md).

---

## The core idea

> **Total time = the sum, over everything the program does, of how often it happens × what it costs.**

Every method below is a way of organising that count for a particular shape of code. The skill is choosing the right way to organise it.

---

## Step 0: set up before counting

1. **Name the input sizes.** Every quantity that can grow: `n`, `m`, `W`, `sum`, `k`, `amount`, `V`, `E`. Missing one is the most common error (e.g. the depth of Coin Change II depends on `amount`, not just `n`).
2. **Choose the worst-case input.** Reason with the input that makes the most work happen:
   - every `if` that guards a loop or a recursive call is true,
   - e.g. all characters equal, an already-increasing array, every item fits.
3. **Choose a unit of work.** One loop iteration, one function call, or one comparison. Constant-time statements (arithmetic, array access, `charAt`, comparisons) all count as O(1).

---

## Step 1: pick the method

Look at the **shape of the code**, then use the matching method:

| Code shape | Method | Why this method |
|---|---|---|
| Loops only (no recursion) | **A. Loop counting** | The work is exactly the number of times the innermost body runs, which loops let you count directly |
| Recursion, every call has the **same** number of children and they shrink by the **same** amount | **B. Branches^depth** | All levels of the call tree look alike, so `b` children per call over `d` levels gives the node count directly |
| Recursion where the number or size of children **varies** (recursion inside a loop, splits) | **C. Recurrence relation** | Calls lower in the tree do less, so `bᵈ` overestimates badly; only an exact equation counts the tree correctly |
| Recursion that **halves** the input (binary search, merge sort) | **C. Recurrence + master theorem** | The tree's depth is `log n`, and the work per level follows a standard pattern |
| Memoization or tabulation | **D. States × work per state** | Each state is computed once, so total = how many states × what one state costs |
| Pointers that only move forward, a stack/queue where each element is pushed and popped once | **E. Amortised counting** | One step may be expensive, but the total over the whole run is bounded by how many times each element can move |
| Graph traversal (BFS, DFS) | **F. Vertices + edges** | Each vertex is processed once and each edge is looked at once (twice if undirected) |

A program can need more than one method, e.g. a recursive function (method C) that calls a helper containing a loop (method A).

---

## Method A: loop counting

**Rule:** for each loop, ask *"how many times does it run, given the values of the loops around it?"*

| Pattern | Count | Result |
|---|---|---|
| `for i < n` | n | O(n) |
| `for i < n { for j < m }` | n · m | O(n·m) |
| `for i < n { for j < i }` | 0 + 1 + … + (n−1) = n(n−1)/2 | O(n²) |
| `for i < n { for j = i; j < n }` | n + (n−1) + … + 1 = n(n+1)/2 | O(n²) |
| `for (i = 1; i < n; i *= 2)` | log₂ n | O(log n) |
| `for i < n { for (j = 1; j < n; j *= 2) }` | n · log n | O(n log n) |
| two loops **one after the other** | n + m | O(n + m) |

**Rules:**
- **Nested** (one inside the other) → **multiply**.
- **Sequential** (one after the other) → **add**, then keep only the largest term: O(n²) + O(n) = O(n²).
- **Dependent** inner loop (its range uses the outer variable) → **add up** the inner count over every outer value. Don't just multiply the maximums, although that does give a valid upper bound.
- **Count hidden loops.** A method call inside a loop costs whatever that method costs. Not O(1):
  - your own helpers (e.g. a palindrome check over a range is O(length)),
  - `substring`, `indexOf`, `contains`, `String +` in a loop, `Arrays.fill`, `Arrays.copyOf`, `List.contains`, `list.remove(0)`,
  - sorting: O(n log n).

**Useful sums:**

```
1 + 2 + … + n               = n(n+1)/2        → O(n²)
1 + 2 + 4 + … + 2ⁿ⁻¹         = 2ⁿ − 1          → O(2ⁿ)
1 + r + r² + … + rⁿ⁻¹        = (rⁿ − 1)/(r − 1) → O(rⁿ) for any r > 1
n + n/2 + n/4 + … + 1       ≤ 2n              → O(n)
log 1 + log 2 + … + log n   = log(n!)         → O(n log n)
```

A **geometric** sum (each term a constant multiple of the previous) is always dominated by its largest term.

**Example (Longest Common Substring brute force):** two loops over every start pair (`n · m`), and an inner `while` that extends a match by at most `min(n, m)` steps → **O(n · m · min(n, m))**. *Method A because there is no recursion; the `while` is counted by its maximum run length.*

---

## Method B: branches^depth (quick bound)

For recursion where every call makes the **same** number of child calls `b`, and every path is at most `d` calls long:

```
number of calls ≤ 1 + b + b² + … + bᵈ  →  O(bᵈ)
time ≤ (number of calls) × (work per call)
```

**How to find `b` and `d`:**
- **b (branches):** how many child calls one call makes **while running**, not how many call statements appear in the code. A recursive call inside a loop that runs `k` times is `k` branches.
- **d (depth):** the length of the longest path from the first call to a base case. For each branch, ask *what shrinks, and by how much*. Every step must shrink something; the depth is how many steps it takes to reach the base case.

**When it is exact, and when it is only a bound:**
- **Exact** when every call really has `b` children and paths reach the full depth: 0/1 Knapsack (pick / not pick), O(2ⁿ).
- **Loose upper bound** when some branches end sooner (Fibonacci-style: `n−1` and `n−2`): the true count is ~1.618ⁿ, but O(2ⁿ) is still accepted.
- **Badly overestimated** when the number of children shrinks as you go deeper (recursion in a loop over the remaining elements): `bᵈ` might say `nⁿ` when the truth is `2ⁿ`. **Use method C instead.**

| Problem | b | d | Why that depth | Result |
|---|---|---|---|---|
| 0/1 Knapsack | 2 | n | every call removes one item | O(2ⁿ) |
| Coin Change II | 2 | n + amount | skip removes a coin, pick removes ≥ 1 from the amount, never both | O(2^(n+amount)) |
| LCS / Unique Paths | 2 | n + m | every call shrinks `n + m` by at least 1 | O(2^(n+m)) |
| Edit Distance | 3 | n + m | same | O(3^(n+m)) |
| Stock IV | 2 | n | every call moves to the next day; `k` only cuts branches off | O(2ⁿ) |

---

## Method C: recurrence relation (exact count)

Use this when the calls aren't all alike. It has two parts: **write** the recurrence, then **solve** it.

### C1. Writing the recurrence

1. **Define T in words.** `T(x)` = total work (or total number of calls) of **one** call on a subproblem of size `x`, **including everything its children do**. Choose `x` as the thing that shrinks: the remaining length `n − start`, the interval length `right − left`, the index `i`, and so on.
2. **Cost of this call alone, `w(x)`.** Everything the function body does except the recursive calls: loops, helpers, comparisons. If you're counting calls, `w = 1`.
3. **List the children.** Go through the code; for every recursive call that can run on the worst-case input (including calls inside loops), write the size of its argument.
4. **Write the equation.** `T(x) = w(x) + T(child₁) + T(child₂) + …` A loop of calls becomes a sum: `T(x) = w(x) + Σ T(child size)`.
5. **Base case.** `T(smallest size) = cost of the base case` (usually 1).

### C2. Solving the recurrence

Try these in order:

1. **Table of small values (always works).** Compute `T(0)`, `T(1)`, …, `T(5)` by hand and look at how the values grow:

   | Growth from one value to the next | Complexity |
   |---|---|
   | + constant | O(n) |
   | + something proportional to n | O(n²) |
   | × 2 | O(2ⁿ) |
   | × 3 | O(3ⁿ) |
   | × ~1.618 (Fibonacci) | O(1.618ⁿ), usually written O(2ⁿ) |

2. **Subtract consecutive formulas** (for "sum of all smaller T" recurrences). Write the equation for `x` and for `x − 1` underneath; almost everything cancels.
   ```
   T(x)   = 1 + T(x−1) + T(x−2) + … + T(0)
   T(x−1) = 1 +          T(x−2) + … + T(0)
   ─────────────────────────────────────────
   T(x) − T(x−1) = T(x−1)   →   T(x) = 2·T(x−1)   →   O(2ˣ)
   ```
3. **Unrolling.** Substitute the equation into itself a few times until the pattern is clear:
   ```
   T(n) = T(n−1) + n = T(n−2) + (n−1) + n = … = 1 + 2 + … + n  →  O(n²)
   ```
4. **Recursion tree.** Draw levels; total = Σ over levels of (number of calls on that level × work per call on that level). Best for halving recurrences.
5. **Master theorem** (for `T(n) = a·T(n/b) + O(n^d)`): compare `a` with `bᵈ`.
   - `a < bᵈ` → O(n^d) (the top level dominates)
   - `a = bᵈ` → O(n^d · log n) (every level costs the same)
   - `a > bᵈ` → O(n^(log_b a)) (the leaves dominate)

### Common recurrences

| Recurrence | Result | Where it shows up |
|---|---|---|
| `T(n) = T(n−1) + O(1)` | O(n) | linear recursion, factorial |
| `T(n) = T(n−1) + O(n)` | O(n²) | recursive selection sort |
| `T(n) = 2T(n−1) + O(1)` | O(2ⁿ) | pick / not pick |
| `T(n) = T(n−1) + T(n−2) + O(1)` | O(1.618ⁿ) ⊂ O(2ⁿ) | Fibonacci, Frog Jump |
| `T(n) = 1 + Σ_{k<n} T(k)` | O(2ⁿ) | LIS ("ends at i"), recursion over every next choice |
| `T(m) = 1 + 2·Σ_{k<m} T(k)` | O(3ⁿ) | Matrix Chain (split points, two sides each) |
| `T(n) = 1 + c·Σ_{k<n} T(k)` | O((c+1)ⁿ) | general form of the two above |
| `T(n) = T(n/2) + O(1)` | O(log n) | binary search |
| `T(n) = 2T(n/2) + O(n)` | O(n log n) | merge sort |
| `T(n) = 2T(n/2) + O(1)` | O(n) | tree traversal |
| `T(n) = T(n/2) + O(n)` | O(n) | quickselect (average) |

### C3. Add the work per call

If you counted **calls**, multiply by the cost of one call to get time:

```
time = Σ over all calls of w(call)   ≤   (number of calls) × (largest w)
```

The right-hand side is a valid upper bound and is what interviews usually expect. It can be loose when deep calls work on much smaller inputs.

**Example (LIS, "ends at i"):** `T(i) = 1 + T(0) + … + T(i−1)` → table 1, 2, 4, 8 → **O(2ⁿ)** calls; each call loops over `i` earlier elements, so time ≤ O(n · 2ⁿ), commonly stated as O(2ⁿ). *Method C because each call has a different number of children (i of them), so `bᵈ` would wildly overestimate.*

**Example (Matrix Chain):** `T(m) = 1 + 2·Σ_{k<m} T(k)` → 1, 3, 9, 27 → **O(3ⁿ)**. *Method C because every split creates a left and a right child of different sizes.*

---

## Method D: states × work per state (memoization / tabulation)

```
time = (number of distinct states) × (work done inside one state)
```

1. **Count the states.** Multiply the ranges of the parameters that change: `n · W`, `n · m`, `n · 2 · (k+1)`. If only part of the table is ever used (e.g. `left < right`), you may count just that part: n(n−1)/2, still O(n²).
2. **Work per state.** Count the loop and helper work inside **one** call or cell. **Treat each recursive call as O(1)**, because with memoization it is a lookup; its real work is already counted under its own state. (This is the opposite of method C, where a child's whole subtree is counted.)
3. **Multiply.**

| Problem | States | Work per state | Time |
|---|---|---|---|
| 0/1 Knapsack | n · W | O(1) — two choices | O(n · W) |
| LCS / Edit Distance | n · m | O(1) | O(n · m) |
| LIS ("ends at i") | n | O(n) — loop over earlier j | O(n²) |
| Matrix Chain | ~n²/2 | O(n) — loop over split points | O(n³) |

**If the main function loops over many starting points** (LIS, Last Stone Weight II scan), share **one** table across all of them; then each state is still computed once in total.

**Tabulation** has the same states and work, so the same time. Count the nested loops directly (method A) as a check: they should match states × work.

---

## Method E: amortised counting

When one step can be expensive but the **total** is limited, count the total movement instead of the worst single step.

- **Two pointers / sliding window:** each pointer only moves forward, at most `n` times in total → O(n), even though there's a `while` inside a `for`.
- **Monotonic stack:** each element is pushed once and popped at most once → O(n) total.
- **Dynamic array (`ArrayList.add`):** occasional O(n) resize, but O(1) per add on average.

**Ask:** "How many times, in the whole run, can each element be touched?" If the answer is a constant, the total is O(n).

---

## Method F: graphs

- **BFS / DFS with a visited array:** each vertex is processed once, and each edge is checked once (directed) or twice (undirected) → **O(V + E)**.
- **Grid as a graph:** V = rows · cols, E ≈ 4V (or 8V for knight moves) → **O(rows · cols)**.
- **Dijkstra with a heap:** O((V + E) log V).

---

## Space complexity

Count everything that exists **at the same time**, at its largest:

| Source | Cost |
|---|---|
| dp table | product of its dimensions |
| recursion stack | maximum depth (from method B/C) × space per frame |
| extra structures | maps, sets, queues, visited arrays, built strings |
| space-optimised dp | the rows or variables actually kept |

Input arrays are usually not counted. O(n) + O(n) is O(n), but writing the stack term separately shows why tabulation is safer than memoization for deep recursion (`StackOverflowError`).

---

## Step 2: check the answer

1. **Plug in small numbers.** Count real iterations or calls for n = 1, 2, 3, 4 and compare with your formula.
2. **Check against the constraints.** Roughly 10⁸ simple operations per second; a typical limit is 1–2 seconds.

   | Max n | Feasible complexity |
   |---|---|
   | ≤ 10–12 | O(n!), O(n · 2ⁿ) |
   | ≤ 20–25 | O(2ⁿ) |
   | ≤ 100–500 | O(n³) |
   | ≤ 1,000–5,000 | O(n²) |
   | ≤ 10⁵–10⁶ | O(n log n), O(n) |
   | larger | O(log n), O(1) |

   Read the constraints **before** designing: they tell you which complexity the intended solution has.

---

## Common traps

| Trap | What to do instead |
|---|---|
| Counting call statements as branches | Count how many child calls actually run (calls inside loops count once per iteration) |
| Treating child calls as O(1) in the **naive** version | Only memoization makes them O(1); naive children cost their whole subtree |
| Forgetting helper costs inside loops | Add each helper's own complexity (palindrome check, substring, sort) |
| Depth from the wrong parameter | List what each branch shrinks (Coin Change II: n **or** amount) |
| Using `bᵈ` when children shrink | Write a recurrence (method C) |
| Multiplying sequential calls | Sequential → add; geometric sums are dominated by the largest term |
| Counting states as the naive time | States belong to method D; naive time comes from the call tree |
| Leaving a loop variable in the answer (`O(n · (end − start))`) | Replace it with its worst case (`n`) |

---

## Template: complexity section for a problem README

For each approach, state the result, **the method used and why**, and the derivation.

```markdown
## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive | O(…) | O(…) stack |
| Memoization | O(…) | O(…) + O(…) stack |
| Tabulation | O(…) | O(…) |
| Space-optimised | O(…) | O(…) |

### How I got these

- **Naive — method: <A / B / C>** because <why this method fits the code's shape>.
  <branches, depth and what shrinks — or the recurrence, its first few values and the pattern>.
  Work per call: <loops + helpers> → total <…>.
- **Memoization / tabulation — method: states × work per state.**
  States: <parameters and ranges>. Work per state: <loop/helper cost; child calls are O(1) lookups>.
- **Space:** <table> + <stack depth and why>.
```
