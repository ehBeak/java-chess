package chess.domain.chessboard;

import static chess.domain.attribute.File.*;
import static chess.domain.attribute.Rank.*;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.attribute.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessboardTest {

    @DisplayName("체스판에 King이 존재하는지 확인한다.")
    @Test
    void existKing() {
        Chessboard chessBoard = Chessboard.createChessBoard();
        chessBoard.move(Square.of(E, TWO), Square.of(E, THREE));
        chessBoard.move(Square.of(F, SEVEN), Square.of(F, SIX));
        chessBoard.move(Square.of(D, ONE), Square.of(H, FIVE));
        chessBoard.move(Square.of(H, FIVE), Square.of(E, EIGHT));

        assertFalse(chessBoard.isEnd());
    }
}
