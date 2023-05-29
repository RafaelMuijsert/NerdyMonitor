package Utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static long getTimeDifference(LocalDateTime newDate, LocalDateTime oldDate) {
        Duration duration = Duration.between(oldDate, newDate);
        // Calculate the duration between the two LocalDateTime objects

        return duration.toMinutes();
    }
}
