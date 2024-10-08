import subprocess

def solve(filename):
    return subprocess.run(['./sudoku.sh', filename], stdout=subprocess.PIPE).stdout.decode()

def test_sudoku_small():
    with open("_puzzles/small-solved.txt") as f:
        solution = f.read()
    assert solve("_puzzles/small.txt").strip() == solution.strip()