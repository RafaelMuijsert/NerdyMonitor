package Utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeUtils {

    public static long getTimeDifferenceMinutes(LocalDateTime newDate, LocalDateTime oldDate) {
        Duration duration = Duration.between(oldDate, newDate);

        // Calculate the duration between the two LocalDateTime objects
        long timeDifferenceMinutes = duration.toMinutes();

        // Turn negative into positive.
        if(timeDifferenceMinutes < 0){
            timeDifferenceMinutes *= -1;
        }

        return timeDifferenceMinutes;
    }
}
