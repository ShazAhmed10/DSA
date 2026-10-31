# Maximum Sum of Non-Adjacent Elements

Blind 180 DP · State Transition · Medium (same as [LeetCode 198 House Robber](https://leetcode.com/problems/house-robber/))

## Problem

```text
Given an integer array nums of size n. Return the maximum sum possible using the elements of nums such that no two
elements taken are adjacent in nums.

Example 1:
Input: nums = [1, 2, 4]
Output: 5
Explanation:
[1, 2, 4], the underlined elements are taken to get the maximum sum.

Example 2:
Input: nums = [2, 1, 4, 9]
Output: 11
Explanation:
[2, 1, 4, 9], the underlined elements are taken to get the maximum sum.
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`A_Memoization.java`](A_Memoization.java) | Memoization |
| [`B_Tabulation.java`](B_Tabulation.java) | Tabulation |
| [`C_Tabulation_SpaceOptimisation.java`](C_Tabulation_SpaceOptimisation.java) | Space-optimised: variables |

## Key insight

At each index: **pick** it (then the previous element can't be used → jump 2 back) or **skip** it (move 1 back). Take the max.

## State, recurrence, base case

- **State:** `f(i)` = maximum sum using elements `0..i` with no two adjacent
- **Recurrence:** `f(i) = max(nums[i] + f(i − 2), f(i − 1))`
- **Base cases:** `f(0) = nums[0]`; `f(i) = 0` for `i < 0`
- **Answer:** `f(n − 1)`
- **Space optimisation:** only the previous two values are needed → two variables

## Complexity

| Approach | TC | SC |
|---|---|---|
| Naive recursion | O(2ⁿ) | O(n) stack |
| Memoization | O(n) | O(n) table + O(n) stack |
| Tabulation | O(n) | O(n) |
| Space-optimised | O(n) | O(1) |

## Mistakes I made

None recorded.

## Related

- [Frog Jump](../../../../A2Z/OneDimension_DP/A_Medium/P01_FrogJump/) — same two-variable space optimisation
