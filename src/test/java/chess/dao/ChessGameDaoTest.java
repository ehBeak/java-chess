package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Rank;
import chess.domain.attribute.Square;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    @BeforeEach
    void initChessBoardBeforeTest() {
        chessGameDao.deleteAllPieces();
        chessGameDao.initChessboard();
    }

    @AfterEach
    void initChessBoardAfterAllTest() {
        chessGameDao.deleteAllPieces();
        chessGameDao.initChessboard();
    }

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
    public void findAllPieces() {
        final var chessGameDao = new ChessGameDao();
        List<Piece> allPieces = chessGameDao.findAllPieces();
        for (Piece piece : allPieces) {
            System.out.println(piece.getPieceType());
        }
    }

    @Test
    public void updatePieceMovement() {
        final var chessGameDao = new ChessGameDao();
        chessGameDao.updatePieceMovement(Square.of(File.B, Rank.TWO), Square.of(File.B, Rank.THREE));
    }

    @Test
    public void deletePieceOf() {
        final var chessGameDao = new ChessGameDao();
        chessGameDao.deletePieceOf(Square.of(File.B, Rank.TWO));
        assertFalse(chessGameDao.existPieceIn(Square.of(File.B, Rank.TWO)));
    }

    @Test
    public void existPieceIn() {
        final var chessGameDao = new ChessGameDao();
        assertAll(
                () -> assertTrue(chessGameDao.existPieceIn(Square.of(File.B, Rank.TWO))),
                () -> assertFalse(chessGameDao.existPieceIn(Square.of(File.C, Rank.FOUR)))
        );
    }
}

