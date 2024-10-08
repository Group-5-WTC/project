# Sudoku

In this project, you will be writing a program that solves Sudoku puzzles. The program should be able to solve any valid Sudoku puzzle, and should be able to determine if a puzzle is invalid.

You will be given Sudoku puzzles in 9x9, 16x16, and 25x25 formats. The puzzles will be stored in text files, with the following format:

```
5 3 0 0 7 0 0 0 0
6 0 0 1 9 5 0 0 0
0 9 8 0 0 0 0 6 0
8 0 0 0 6 0 0 0 3
4 0 0 8 0 3 0 0 1
7 0 0 0 2 0 0 0 6
0 6 0 0 0 0 2 8 0
0 0 0 4 1 9 0 0 5
0 0 0 0 8 0 0 7 9
```

The numbers represent the values in the Sudoku puzzle. A `0` represents an empty cell.

We'll use letters to represent values greater than 9. For example, in a 25x25 puzzle, the values `A` through `P` will be used to represent the values `10` through `25`, respectively. Your program should be case insensitive.

## Usage

Your program should be able to solve puzzles in the following way:

```bash
./sudoku.sh puzzle.txt
```

Your program should output the solved puzzle to standard output. If the puzzle is invalid, your program should output `Invalid puzzle`.

If your program needs to compile or install anything, include a command like:

```bash
./prepare.sh
```

This will be run once at the beginning of a review.