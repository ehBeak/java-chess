package chess.dao.convertor;

import chess.dao.PieceDao;
import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Rank;
import chess.domain.attribute.Square;
import chess.domain.piece.Piece;

public class PiecesConvertor {

    public static Piece mapToDomain(PieceDao pieceDao) {
        Color color = Color.of(pieceDao.team());
        String pieceType = pieceDao.type();
        Square square = Square.of(File.of(pieceDao.file()), Rank.of(pieceDao.rank()));
        return PieceConvertor.convertTo(color, pieceType, square);
    }
}
