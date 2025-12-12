# CSArrayList Report

## Complexity Summary
- add(E): Amortized O(1)
- add(int, E): O(n)
- get(int): O(1)
- remove(int): O(n)
- indexOf(Object): O(n)

## Microbenchmark Results

| N | CSArrayList append | ArrayList append | CSArrayList get | ArrayList get |
|---|--------------------|------------------|-----------------|---------------|
| 100k | 3                  | 5                | 3               | 2             |
| 500k | 2                  | 2                | 1               | 2             |
| 1M | 40                 | 43               | 1               | 1             |

## Observations
- Append performance was similar for both lists.
- Random access time remained constant for both implementations.

## Design Decisions & Pitfalls
- Used a doubling strategy for resizing.
- Implemented a fail-fast iterator using a modification counter.
- Fixed a NullPointerException in indexOf by handling null explicitly.