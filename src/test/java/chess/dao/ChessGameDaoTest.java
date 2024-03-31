package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Rank;
import chess.domain.attribute.Square;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    @Test
    public void connection() {
        try (final var connection = chessGameDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addPiece() {
        final var chessGameDao = new ChessGameDao();
        chessGameDao.addPiece(new King(Color.WHITE, Square.of(File.F, Rank.TWO)));
    }

    @Test
    public void deleteAllPieces() {
        final var chessGameDao = new ChessGameDao();
        chessGameDao.deleteAllPieces();
    }

    @Test
    public void initChessBoard() {
        final var chessGameDao = new ChessGameDao();
        chessGameDao.initChessboard();
    }

    @Test
    public void findAllPieces() {
        final var chessGameDao = new ChessGameDao();
        List<Piece> allPieces = chessGameDao.findAllPieces();
        for (Piece piece : allPieces) {
            System.out.println(piece.getPieceType());
        }
    }
}

