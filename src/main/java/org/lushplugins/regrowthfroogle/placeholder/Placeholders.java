package org.lushplugins.regrowthfroogle.placeholder;

import org.lushplugins.lushlib.utils.TimeFormatter;
import org.lushplugins.placeholderhandler.annotation.Placeholder;
import org.lushplugins.placeholderhandler.annotation.SubPlaceholder;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@SuppressWarnings("unused")
@Placeholder("froogle")
public class Placeholders {
    private static final DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder()
        .appendValue(ChronoField.DAY_OF_MONTH, 2)
        .appendLiteral('/')
        .appendValue(ChronoField.MONTH_OF_YEAR, 2)
        .appendLiteral('/')
        .appendValue(ChronoField.YEAR, 4)
        .toFormatter();

    private LocalDateTime next = LocalDate.now()
        .withDayOfMonth(4)
        .atTime(0, 41, 20);

    @SubPlaceholder("countdown")
    public String countdown() {
        LocalDateTime now = LocalDateTime.now();
        if (!now.isBefore(this.next)) {
            this.next = this.next.plusMonths(1);
        } else if (now.toLocalDate().isEqual(this.next.toLocalDate())) {
            Duration duration = Duration.between(now, this.next);
            return TimeFormatter.formatDuration(Duration.between(now, this.next),  TimeFormatter.FormatType.SHORT_FORM);
        }

        return this.next.toLocalDate().format(DATE_FORMAT);
    }
}
