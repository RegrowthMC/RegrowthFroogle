package org.lushplugins.regrowthfroogle.placeholder;

import org.lushplugins.lushlib.utils.TimeFormatter;
import org.lushplugins.placeholderhandler.annotation.Placeholder;
import org.lushplugins.placeholderhandler.annotation.SubPlaceholder;
import org.lushplugins.regrowthfroogle.RegrowthFroogle;

import java.time.Duration;
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

    @SubPlaceholder("countdown")
    public String countdown() {
        LocalDateTime next = RegrowthFroogle.getInstance().getNextFeedingTime();
        LocalDateTime now = LocalDateTime.now();
        if (now.toLocalDate().isEqual(next.toLocalDate())) {
            Duration duration = Duration.between(now, next);
            return TimeFormatter.formatDuration(Duration.between(now, next),  TimeFormatter.FormatType.SHORT_FORM);
        }

        return next.toLocalDate().format(DATE_FORMAT);
    }
}
