from magic_square import generate_magic_square
from itertools import chain


def assert_valid_magic_square(grid, size):
    sum_rows = [sum(row) for row in grid]
    sum_columns = [sum(column) for column in zip(*grid)]
    sum_diagonals = [
        sum(row[i] for i, row in enumerate(grid)),
        sum(row[-i - 1] for i, row in enumerate(grid)),
    ]

    assert len({*sum_rows, *sum_columns, *sum_diagonals}) == 1

    digits = set(range(1, size * size + 1))
    assert digits == set(chain.from_iterable(grid))


def test_generate_magic_square_3():
    grid = generate_magic_square(3)
    assert_valid_magic_square(grid, 3)