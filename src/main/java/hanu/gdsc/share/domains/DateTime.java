package hanu.gdsc.share.domains;

import hanu.gdsc.share.error.BusinessLogicError;

import java.time.ZonedDateTime;
import java.util.Objects;

public class DateTime {
    private ZonedDateTime value;

    public DateTime(ZonedDateTime value) {
        this.value = value;
    }

    public DateTime(String value) {
        try {
            this.value = ZonedDateTime.parse(value);
        } catch (Exception e) {
            throw new BusinessLogicError("Thời gian không hợp lệ: '" + value + "'", "NOT_VALID_DATE");
        }
    }

    public ZonedDateTime toZonedDateTime() {
        return value;
    }

    public boolean isBefore(DateTime that) {
        return this.value.isBefore(that.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateTime dateTime = (DateTime) o;
        return Objects.equals(value, dateTime.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static DateTime now() {
        return new DateTime(ZonedDateTime.now());
    }

    public DateTime plusMinutes(int i) {
        return new DateTime(value.plusMinutes(15));
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
