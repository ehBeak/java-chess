package chess;

import chess.domain.attribute.Color;
import chess.domain.chessboard.Chessboard;
import chess.view.InputView;
import chess.view.ResultView;
import chess.view.command.Command;
import chess.view.command.MoveCommand;
import chess.view.dto.ChessboardDto;
import chess.view.dto.GameResultDto;

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

    private void playByCommand(final Chessboard chessboard) {//todo 상태패턴
        if (chessboard.catchKing()) {
            endGame(chessboard);
            return;
        }
        String command = inputView.askCommand();
        if (Command.isMove(command)) {
            moveChessPiece(chessboard, command);
            playByCommand(chessboard);
        }
        if (Command.isStatus(command)) {
            boardStatus(chessboard);
        }
        if (Command.isEnd(command)) {
            endGame(chessboard);
        }
        throw new IllegalArgumentException("잘못된 명령어 입니다.");
    }

    private void moveChessPiece(final Chessboard chessboard, String command) {
        MoveCommand moveCommand = new MoveCommand(command);
        chessboard.move(moveCommand.source(), moveCommand.target());
        resultView.printBoard(new ChessboardDto(chessboard));
    }

    private void boardStatus(Chessboard chessboard) {
        resultView.printWinner(GameResultDto.of(chessboard.findWinner()));
        resultView.printStatus(Color.WHITE, chessboard.totalScoreOf(Color.WHITE));
        resultView.printStatus(Color.BLACK, chessboard.totalScoreOf(Color.BLACK));
    }

    private void endGame(Chessboard chessboard) {
        resultView.printWinner(GameResultDto.of(chessboard.findWinner()));
    }
}
