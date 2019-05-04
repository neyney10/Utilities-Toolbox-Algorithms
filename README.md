
# Utilities, Toolbox and algorithms!
This repository contains simple and short utilites and algorithms, such as Dynamic programming, linear programming and differet kind of sorts!.
- Using java-10/11.
- Java JUnit v5.

## Dynamic Programming
To be honest, at the moment the amount of work I've put into this section is low, therefore there aren't many utilities there, and there are only those who are widely known, I hope that this section will be great someday.

### LCS - Longest Common Sequence of strings.
LCS is a common problem solved by the dynamic programming approach, it gives the length of the Longest common sequence between two strings, this has many uses, one example is the "diff" program used by Git to see the file sections that has changed.
For More info see: [Link to Wikipedia](https://en.wikipedia.org/wiki/Longest_common_subsequence_problem)  
#### Features
- Code 
	- Returns Integer as the length of the LCS.				:heavy_check_mark:
	- Array indexing the positions of all common characters. 	:x:
	- Amount of adjacent substrings of the common sequence.  	:x:
- Back Tracking.											:x:
- Tests (Junit v5 for Java).				:heavy_check_mark:

### MED- Minimum edit distance of strings
MED is a common problem as well which solved by dynamic programming approach, it gives the minimum amount of changes on strings such that the two strings will be identical, changes that can be done are: removing a character, replacing a character and adding a character, this is useful in cases we wanna see how similar two strings are.
#### Features
- Code 
	- Returns Integer as the min size of changes to be done.				:heavy_check_mark:
	- Array indexing the positions of all changed characters. 	:x:
	- List of changes to be done.  	:x:
- Back Tracking.											:x:
- Tests (Junit v5 for Java).				:heavy_check_mark:


## Linear programming
This is new section as well, as I'm currently studying it, however I hope I'll be able to add Matrices algorithms to the section such as Matrix multiplication and Gaussian Elimination, Finding inverse and much more. the section currently have a simple Simplex method algorithm to solve optimization problems of linear equations (matrix), currently works on simple scenarios and I'm hoping to further improve the implementation.

### Simplex
( Currently W.I.P. ) solving matrix optimization problems.
#### Features
- Solve Simplex matrices.
	- Maximum optimization problem :heavy_check_mark:
	- Minimum optimization problem :heavy_check_mark: (need some code cleanup)
- a friendly interface to add constraints and objective functions, construct a linear problem in general instead of a matrix :x:
- Returns the solution vector, the values of the optimization variables x1,x2....xn. :x:
- Stops on in-feasibility and on completion. :heavy_check_mark:
- Throws exception on in-feasibility. :x:
- Tests (Junit v5 for Java).				:heavy_check_mark:

### Gauss Elimination - Matrix solver
( Currently W.I.P. ) solving matrices using the Gauss Elimination algorithm.

#### Features
- Solve homogenous matrix. [Single solution only for now] :heavy_check_mark:
- Solve non-homogenous matrix (with vector b, Ax=b).  [Single solution only for now] :heavy_check_mark:
- Solve NxN matrix. [Single solution only for now] :heavy_check_mark:
- Solve MxN matrix. [Single solution only for now] :heavy_check_mark:
- Solve for Inf+ solutions. :x:
- Solve matrix with parameters. :x:
- Tests (Junit v5 for Java).				:heavy_check_mark:

## Array Sorts
Who doesn't like sorting algorithms? well, we are in luck as this section contains handful of algorithms with different implementations and even a performance test!
 - Note: Currently contains only the "utilities.java" files which buckled up all the algorithms and its kinda messy, the file is pretty old and I'm hoping in the near future I'll be able to further improve it and even publish a v3 performance test of sorting algorithms.
 
### Algorithms
#### Linear Search
SImple linear search.
- Worst running-time complexity: O(n).

#### Binary search
Search faster an element in an array.
- Worst running-time complexity: O(Log(n)).
- The array must be sorted!

#### Bubble sort
- Worst running-time complexity: O(n^2).
- Stable.

#### Selection sort
- Worst running-time complexity: O(n^2).
- Stable.

#### Insertion sort
This sort is pretty fast for small arrays, in fact, most advanced algorithms use this as an inner algorithm to sort small sub arrays .
- Worst running-time complexity: O(n^2).
- Stable.

#### Merge sort
- Worst running-time complexity: O(n*Log(n)).
- Stable
- Works pretty damn well on sorting non-primitive types such as Objects!

#### Quick sort (Hoare + Lomotu versions available)
- Worst running-time complexity: O(n^2) although the average time is O(n*log(n)) and it can be further improved by randomly choosing a pivot element as trying to eliminate some Edge-case scenarios that bring it to higher complexity.
- Not Stable!

#### Bucket sort

#### Radix Sort

#### Counting Sort
- Worst running-time complexity: O(n+c) where c is the maximum value of element in the array.
- requires O(c) auxiliary space.
- works only on Non-negative integers!
#### Shell Sort
Based on insertion sort.
- Worst running-time complexity: O(n^(1.5).

### Some additional functions
#### CheckSorted
As the name describes, the function checks if the array is sorted, helpful to test if the algorithm works.
#### Performance Tests
- Performance V1 - pretty old and gives less data.
- Performance V2.
- Performance V3 - W.I.P.

#### Mirror Array
Mirrors the array, switching up the position of the elements in reversed order.
#### createRandomIntegerArray
Creates a new integer array using some options as arguments such as minimum and maximum values (an interval), size of array



