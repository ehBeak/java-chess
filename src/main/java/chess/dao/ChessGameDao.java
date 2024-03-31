package chess.dao;

import chess.dao.mapper.PieceMapper;
import chess.domain.attribute.Square;
import chess.domain.chessboard.Chessboard;
import chess.domain.piece.Piece;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ChessGameDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // findPieceAll
    // initAllPiece
    // addPiece
    // deletePiece
    // updatePiece
    // deleteAll

    //(String type, String team, String file, String rank)
    public void addPiece(final Piece piece) {
        final var query = "INSERT INTO pieces VALUES(?, ?, ?, ?)";
        String type = piece.getPieceType().toString();
        String color = piece.getColor().toString();
        Square square = piece.currentSquare();
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, color);
            preparedStatement.setString(3, square.getFile().toString());
            preparedStatement.setString(4, square.getRank().getValue());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAllPieces() {
        final var query = "DELETE FROM pieces";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initChessboard() {
        Chessboard chessBoard = Chessboard.createChessBoard();
        Map<Square, Piece> chessboard = chessBoard.getChessboard();
        for (Piece piece : chessboard.values()) {
            addPiece(piece);
        }
    }

    public List<Piece> findAllPieces() {
        final var query = "SELECT * FROM pieces";
        List<Piece> pieces = new ArrayList<>();
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Piece piece = PieceMapper.mapToDomain(new PieceDao(resultSet.getString("piece_type"),
                        resultSet.getString("piece_team"),
                        resultSet.getString("piece_file"),
                        resultSet.getString("piece_rank")));
                pieces.add(piece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return pieces;
    }
}

