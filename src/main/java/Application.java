import chess.ChessGame;
import chess.dao.ChessGameDao;
import chess.view.InputView;
import chess.view.ResultView;

public class Application {
    public static void main(String[] args) {
        final ChessGame chessGame = new ChessGame(new InputView(), new ResultView());
        chessGame.run();
    }
}
