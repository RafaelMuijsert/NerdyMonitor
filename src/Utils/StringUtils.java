package Utils;

import org.apache.commons.text.StringEscapeUtils;

import java.io.File;
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
     * Sanitize String against SQL injection
     * @param content
     * @return
     */
    public static String sanitize (String content) {
        content.replace("'", "''");
        return (StringEscapeUtils.escapeJava(content));
    }

    public static String removeExtention(String filePath) {
        File f = new File(filePath);

        // if it's a directory, don't remove the extention
        if (f.isDirectory()) return filePath;

        String name = f.getName();

        // Now we know it's a file - don't need to do any special hidden
        // checking or contains() checking because of:
        final int lastPeriodPos = name.lastIndexOf('.');
        if (lastPeriodPos <= 0)
        {
            // No period after first character - return name as it was passed in
            return filePath;
        }
        else
        {
            // Remove the last period and everything after it
            File renamed = new File(f.getParent(), name.substring(0, lastPeriodPos));
            return renamed.getPath();
        }
    }}
