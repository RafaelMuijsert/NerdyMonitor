package Utils;

import org.apache.commons.text.StringEscapeUtils;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class StringUtils {
    private static final Pattern SPACES_OR_EMPTY = Pattern.compile(" *");

    /**
     * Convert array into a single String where each item is separated by the given separator
     * @param separator
     * @param data
     * @return
     */
    public static String implode(String separator, String[] data) {
        StringJoiner sb = new StringJoiner(separator);

        for (String token : data) {
            if (!SPACES_OR_EMPTY.matcher(token).matches()) {
                sb.add(sanitize(token));
            }
        }

        return sb.toString();
    }

    /**
     * @todo Find alternative
     * @param content
     * @return
     */
    public static String sanitize (String content) {
        content.replace("'", "''");
        return (StringEscapeUtils.escapeJava(content));
    }
}