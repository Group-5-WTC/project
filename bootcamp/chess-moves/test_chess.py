import chess


def test_opening_moves():
    board = chess.parse_fen(
        "rnbqkb1r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKB1R w KQkq - 0 1"
    )

    moves = chess.generate_moves(board)
    assert len(moves) == 20, f"Expected 20 moves, but got {len(moves)}"
