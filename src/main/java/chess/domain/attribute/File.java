package chess.domain.attribute;

import static chess.domain.piece.PieceType.PAWN;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private static final int FILE_MIN = 1;
    private static final int FILE_MAX = 8;

    private final int column;

    File(final int column) {
        this.column = column;
    }

    public static File of(final char column) {
        return of(String.valueOf(column));
    }

    public static File of(final int column) {
        return findByColumn(column, file -> file.column == column);
    }

    public static File of(final String column) {
        return findByColumn(column, file -> column.equalsIgnoreCase(file.name()));
    }

    private static <T> File findByColumn(final T column, final Predicate<File> predicate) {
        return Arrays.stream(values())
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "파일은 %d~%d 사이로 입력해주세요: %s".formatted(FILE_MIN, FILE_MAX, column)));
    }

    public static File startKingFile() {
        return E;
    }

    public static File startQueenFile() {
        return D;
    }

    public static File startBishopFileOf(final int index) {
        validateIndexExceptPawn(index);
        if (index == 0) {
            return C;
        }
        return F;
    }

    public static File startKnightFileOf(final int index) {
        validateIndexExceptPawn(index);
        if (index == 0) {
            return B;
        }
        return G;
    }

    public static File startRookFileOf(final int index) {
        validateIndexExceptPawn(index);
        if (index == 0) {
            return A;
        }
        return H;
    }

    private static void validateIndexExceptPawn(final int index) {
        List<Integer> validIndices = List.of(0, 1);
        if (!validIndices.contains(index)) {
            throw new IllegalArgumentException("이미 존재하는 기물의 개수는 0 또는 1개입니다: %d".formatted(index));
        }
    }

    public static File startPawnFileOf(final int index) {
        validatePawnIndex(index);
        return values()[index];
    }

    private static void validatePawnIndex(final int index) {
        List<Integer> validIndices = IntStream.range(0, PAWN.getCount())
                .boxed()
                .toList();
        if (!validIndices.contains(index)) {
            throw new IllegalArgumentException("폰의 개수는 0~8개입니다: %d".formatted(index));
        }
    }

    public File left() {
        int leftOrdinal = this.ordinal() - 1;
        File[] files = File.values();
        try {
            return files[leftOrdinal];
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalStateException("해당 칸의 왼쪽 칸이 존재하지 않습니다.");
        }
    }

    public File right() {
        int rightOrdinal = this.ordinal() + 1;
        File[] files = File.values();
        try {
            return files[rightOrdinal];
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalStateException("해당 칸의 오른쪽 칸이 존재하지 않습니다.");
        }
    }

    public boolean canMoveLeft() {
        try {
            left();
        } catch (IllegalStateException exception) {
            return false;
        }
        return true;
    }
    public boolean canMoveRight() {
        try {
            right();
        } catch (IllegalStateException exception) {
            return false;
        }
        return true;
    }

    public static boolean isInRange(final int column) {
        return FILE_MIN <= column && column <= FILE_MAX;
    }

    public int getColumn() {
        return column;
    }
}
