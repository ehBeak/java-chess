package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Rank;
import chess.domain.attribute.Square;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public abstract class Piece {

    protected final Color color;
    protected final PieceType pieceType;
    private Square square;

    protected Piece(final Color color, final PieceType pieceType, Square square) {
        this.color = color;
        this.pieceType = pieceType;
        this.square = square;
    }

    abstract public Set<Square> findLegalMoves(Set<Piece> entirePieces);

    protected abstract Set<Movement> movements();

    public boolean isTypeOf(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public boolean isNotTypeOf(PieceType pieceType) {
        return this.pieceType != pieceType;
    }

    public void moveTo(Square square) {
        this.square = square;
    }

    public boolean isAllyOf(Piece other) {
        return color == other.color;
    }

    public boolean isEnemyOf(Piece other) {
        return color != other.color;
    }

    public boolean isAllyOf(Color color) {
        return this.color == color;
    }

    public Square currentSquare() {
        return square;
    }

    public double getScore() {
        return this.pieceType.getScore();
    }

    public File getLocatedFile() {
        return square.getFile();
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Piece piece = (Piece) object;
        return getColor() == piece.getColor()
                && getPieceType() == piece.getPieceType()
                && Objects.equals(square, piece.square);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getPieceType(), square);
    }

    public boolean locateSameFile(File file) {
        return square.isSameFile(file);
    }

    public boolean locateSameRank(Rank rank) {
        return square.isSameRank(rank);
    }
}
