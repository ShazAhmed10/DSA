# Minimum Knight Moves to Reach a Target

GfG · Graphs · BFS

## Problem

```text
Q. Given a square chessboard of n x n size, the position of the knight and the position of a target are given and
we need to find out the minimum steps a knight will take to reach the target position.

Example 1:-
knight position : (1,3)
target position : (5,0)
output : 3
(1,3) to (5,0)
steps : (1,3) -> (3,4) -> (4,2) -> (5,0)
```

## Solutions

Each file has a `main` that runs the examples and prints actual vs expected.

| File | Approach |
|---|---|
| [`Solution.java`](Solution.java) | BFS |

## Key insight

Every square is a node and each of the 8 knight moves is an edge with equal weight, so the **shortest number of moves is a BFS distance**. A `visited` board stops squares from being processed twice. (Plain DP doesn't fit naturally because knight moves form cycles — there's no order in which every square's answer depends only on already-solved squares.)

## Complexity

| Approach | TC | SC |
|---|---|---|
| BFS | O(n²) | O(n²) |

## Mistakes I made

None recorded.
