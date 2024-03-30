package chess.view;

import static chess.view.command.Command.END;

import chess.domain.attribute.Color;
import chess.domain.chessboard.GameResult;
import chess.view.command.Command;
import chess.view.dto.ChessboardDto;
import chess.view.dto.GameResultDto;
import java.util.List;

public class ResultView {

    public void printGameStartMessage() {
        System.out.printf("체스 게임을 시작합니다.%n"
                + "> 게임 시작 : start%n"
                + "> 게임 종료 : end%n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n");
    }

    public void printGameEnd() {
        System.out.printf(END.getMessage());
    }

    public void printBoard(final ChessboardDto chessboardDto) {
        List<List<String>> chessboard = chessboardDto.getChessboard();
        chessboard.forEach(squares -> System.out.println(String.join("", squares)));
        System.out.println();
    }

    public void printStatus(Color color, double score) {
        System.out.println("%s 점수 : %s".formatted(color, score));
    }

    public void printGameEndMessage() {
        System.out.println("게임이 종료되었습니다.");
        System.out.println();
    }

    public void printWinner(GameResultDto gameResultDto) {
        System.out.println(gameResultDto.output());
        System.out.println();
    }
}
