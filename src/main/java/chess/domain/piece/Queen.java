package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Queen extends MultiShift {
    public Queen(final Color color, final Square square) {
        super(color, PieceType.QUEEN, square);
    }

    public Set<Square> findLegalMoves(Set<Piece> existPieces) {
        return Stream.of(candidateUpSquares(existPieces),
                        candidateDownSquares(existPieces),
                        candidateLeftSquares(existPieces),
                        candidateRightSquares(existPieces),
                        candidateLeftUpSquares(existPieces),
                        candidateLeftDownSquares(existPieces),
                        candidateRightDownSquares(existPieces),
                        candidateRightUpSquares(existPieces))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
