package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;
import java.util.Set;

public class Queen extends SlidingPiece {
    public Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public Set<Square> movableSquaresFrom(final Square source) {
        return movableSquaresOf(Direction.all(), source);
    }
}
