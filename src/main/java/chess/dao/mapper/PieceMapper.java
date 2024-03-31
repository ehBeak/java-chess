package chess.dao.mapper;

import chess.dao.PieceDao;
import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Rank;
import chess.domain.attribute.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;

public class PieceMapper {

    public static Piece mapToDomain(PieceDao pieceDao) {
        Color color = Color.of(pieceDao.team());
        String pieceType = pieceDao.type();
        Square square = Square.of(File.of(pieceDao.file()), Rank.of(pieceDao.rank()));
        return createPiece(color, pieceType, square);
    }

    public static PieceDao mapToDao(Piece piece) {
        String type = piece.getPieceType().toString();
        String color = piece.getColor().toString();
        Square square = piece.currentSquare();
        String file = square.getFile().toString();
        String rank = square.getRank().toString();
        return new PieceDao(type, color, file, rank);
    }

    private static Piece createPiece(Color color, String pieceType, Square square) {
        if (pieceType.equals("KING")) {
            return new King(color, square);
        }
        if (pieceType.equals("QUEEN")) {
            return new Queen(color, square);
        }
        if (pieceType.equals("ROOK")) {
            return new Rook(color, square);
        }
        if (pieceType.equals("BISHOP")) {
            return new Bishop(color, square);
        }
        if (pieceType.equals("KNIGHT")) {
            return new King(color, square);
        }
        if (pieceType.equals("PAWN")) {
            if (color.equals(Color.WHITE)) {
                return new WhitePawn(square);
            }
            if (color.equals(Color.BLACK)) {
                return new BlackPawn(square);
            }
        }
        throw new IllegalArgumentException("기물의 타입이 존재하지 않습니다.");
    }
}
