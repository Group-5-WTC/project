def generate_magic_square(size):
    if size == 3:
        return [
            [8, 1, 6],
            [3, 5, 7],
            [4, 9, 2],
        ]

    raise NotImplementedError("Only 3x3 magic squares are supported")