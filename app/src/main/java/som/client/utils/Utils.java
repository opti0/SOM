package som.client.utils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Utils {
    static public String toUtf8(String string) {
        return new String(Objects.requireNonNullElse(string, "NULL").getBytes(), StandardCharsets.UTF_8);
    }
}
