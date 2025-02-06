package ChungComiServer.dot.core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Month {
    JAN(1), FEB(2), MAR(3), APR(4), MAY(5), JUN(6),
    JUL(7), AUG(8), SEP(9), OCT(10), NOV(11), DEC(12);

    private final int monthNumber;

    Month(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    @JsonValue
    public int getMonthNumber() {
        return monthNumber;
    }

    @JsonCreator
    public static Month fromString(String value) {
        try {
            int num = Integer.parseInt(value);
            for (Month m : Month.values()) {
                if (m.monthNumber == num) return m;
            }
        } catch (NumberFormatException ignored) {}
        return Month.valueOf(value.toUpperCase()); // 문자열도 지원
    }
}
