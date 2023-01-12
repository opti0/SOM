package som.server.database.utils;

public class Utils {
    public static String toSqlQuery(Object object) {
        return object == null ? "NULL" : String.format("\"%s\"", object);
    }

    public static String toSqlQuery(String object) {
        if (object == null || object.isEmpty()) {
            return "NULL";
        }
        return String.format("\"%s\"", object);
    }
}
