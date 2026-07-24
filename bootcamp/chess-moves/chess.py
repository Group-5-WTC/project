def parse_fen(fen):
    fen_pieces, to_move, castling_rights, ep, hm, fm = fen.split(" ")
    pieces = [[]]
    for char in fen_pieces:
        if char.isdigit():
            pieces[-1].extend(["."] * int(char))
        elif char == "/":
            pieces.append([])
        else:
            pieces[-1].append(char)

    return pieces

def display(game_board):
    rank = 0
    for row in game_board:
        print(rank, end="  ")
        for cell in row:
            print(cell, end=" ")
        rank += 1
        print()

#Nahoom, Sandile
def parse_board(board):
    fen_string = ""
    for row in board:
        for cell in row: 
            fen_string += cell
        fen_string += "/"
    return fen_string

# Kamogelo, Nokuthula
def generate_moves(board):
    #Move all white pawns
    moves = []
    for i in range(0,8):
        piece_to_move = board[6][i]
        board[6][i] = board[5][i]
        board[5][i] = piece_to_move
        display(board)
        print()
        moves.append(parse_board(board))
        
    #Move all black pawns
    for i in range(0,8):
        piece_to_move = board[1][i]
        board[1][i] = board[2][i]
        board[2][i] = piece_to_move
        display(board)
        print()
        moves.append(parse_board(board))


def apply_move(board, move):
    raise NotImplementedError("This function is not implemented yet.")

game_board = parse_fen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")
print("Starting position")
display(game_board)
