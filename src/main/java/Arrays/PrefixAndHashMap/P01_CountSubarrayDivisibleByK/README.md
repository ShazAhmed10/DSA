# Count Subarrays with Sum Divisible by K

[LeetCode 974](https://leetcode.com/problems/subarray-sums-divisible-by-k/) · Arrays · Prefix sum + HashMap

## Problem

```text
Q. Given an array arr[] and an integer k, count the number of subarrays whose sum is divisible by k.

Example 1:-
arr = [4, 5, 0, -2, -3, 1]
k = 5
Output: 7
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`Solution.java`](Solution.java) | Prefix sum + HashMap of remainders |

## Key insight

With prefix sums `a = pf[i]`, `b = pf[j]` (`i < j`), the subarray between them has sum `b − a`, which is divisible by `k` exactly when `a % k == b % k`. So for each prefix, add the number of earlier prefixes with the same remainder.

- Seed the map with remainder 0 → 1 (the empty prefix).
- Normalise negative remainders: `((pf % k) + k) % k`.

## Complexity

| Approach | TC | SC |
|---|---|---|
| Prefix sum + HashMap | O(n) | O(min(n, k)) |

## Mistakes I made

None recorded.
