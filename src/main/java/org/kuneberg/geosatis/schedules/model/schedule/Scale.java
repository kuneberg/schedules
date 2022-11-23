package org.kuneberg.geosatis.schedules.model.schedule;

import java.time.temporal.ChronoField;

public enum Scale {
    HOUR_MINUTE(ChronoField.MINUTE_OF_HOUR),
    DAY_MILLIS(ChronoField.MILLI_OF_DAY),
    DAY_HOUR(ChronoField.HOUR_OF_DAY),
    WEEK_DAY(ChronoField.DAY_OF_WEEK),
    MONTH_WEEK(ChronoField.ALIGNED_WEEK_OF_MONTH),
    YEAR_DAY(ChronoField.DAY_OF_YEAR),
    YEAR_WEEK(ChronoField.ALIGNED_WEEK_OF_YEAR),
    MONTH_DAY(ChronoField.DAY_OF_MONTH),
    MONTH(ChronoField.MONTH_OF_YEAR),
    YEAR(ChronoField.YEAR);

    private final ChronoField scaleField;

    Scale(ChronoField scaleField) {
        this.scaleField = scaleField;
    }

    public ChronoField getScaleField() {
        return scaleField;
    }
}
