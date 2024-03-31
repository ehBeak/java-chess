package chess.domain.attribute;

import java.util.Arrays;

public enum Color {
    WHITE,
    BLACK;

    //todo db convertor 역할을 하는 of라는 함수는 도메인에 작성하는 것이 맞을까?
    public static Color of(String colorName) {
        return Arrays.stream(values())
                .filter(color -> color.name().equals(colorName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("%s색의 기물이 존재하지 않습니다.".formatted(colorName)));
    }
}
