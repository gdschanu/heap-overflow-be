package hanu.gdsc.share.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hanu.gdsc.share.error.BusinessLogicError;
import hanu.gdsc.share.json.DateTimeDeserializer;
import hanu.gdsc.share.json.DateTimeSerializer;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@JsonSerialize(using = DateTimeSerializer.class)
@JsonDeserialize(using = DateTimeDeserializer.class)
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

    public static DateTime fromMillis(long value) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = Instant.ofEpochMilli(value);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        return new DateTime(zonedDateTime);
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
        return new DateTime(value.plusMinutes(i));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public long toMillis() {
        long e = value.toInstant().toEpochMilli();
        Instant i = Instant.ofEpochMilli(e);
        return i.toEpochMilli();
    }
}
