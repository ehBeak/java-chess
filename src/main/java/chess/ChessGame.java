package chess;

import chess.domain.attribute.Color;
import chess.domain.chessboard.Chessboard;
import chess.view.InputView;
import chess.view.ResultView;
import chess.view.command.Command;
import chess.view.command.MoveCommand;
import chess.view.dto.ChessboardDto;

public class ChessGame {

    private final InputView inputView;
    private final ResultView resultView;

    public ChessGame(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        resultView.printGameStartMessage();
        String command = inputView.askCommand();
        if (Command.isStart(command)) {
            play();
        }
        resultView.printGameEnd();
    }

    private void play() {
        Chessboard chessboard = Chessboard.createChessBoard();
        resultView.printBoard(new ChessboardDto(chessboard));
        playByCommand(chessboard);
    }

    private void playByCommand(final Chessboard chessboard) {
        String command = inputView.askCommand();
        playChessGame(chessboard, command);
    }

    private void playChessGame(final Chessboard chessboard, String command) {
        if (!Command.isMove(command)) {
            return;
        }
        MoveCommand moveCommand = new MoveCommand(command);
        chessboard.move(moveCommand.source(), moveCommand.target());
        resultView.printBoard(new ChessboardDto(chessboard));
        if (chessboard.catchKing()) {
            endGame(chessboard);
            return;
        }
        playChessGame(chessboard, inputView.askCommand());
    }

    private void endGame(Chessboard chessboard) {
        resultView.printGameEndMessage();
        String endCommand = inputView.askCommand();
        if (Command.isStatus(endCommand)) {
            resultView.printStatus(Color.WHITE, chessboard.totalScoreOf(Color.WHITE));
            resultView.printStatus(Color.BLACK, chessboard.totalScoreOf(Color.BLACK));

        }
        if (Command.isEnd(endCommand)) {
            return;
        }
    }
}
