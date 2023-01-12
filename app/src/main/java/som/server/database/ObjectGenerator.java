package som.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ObjectGenerator<T> {
    private final ResultSet result;
    private final SqlParser<T, ?> parser;
    private boolean isAlwaysInvalid;

    public ObjectGenerator(ResultSet result, SqlParser<T, ?> parser) {
        this.result = result;
        this.parser = parser;

        try {
            isAlwaysInvalid = !result.isBeforeFirst();
        } catch (SQLException e) {
            isAlwaysInvalid = true;
        }
    }

    public boolean hasNext() {
        try {
            return !(result.isLast() || result.isAfterLast() || isAlwaysInvalid);
        } catch (SQLException e) {
            return false;
        }
    }

    public Optional<T> getNext() {
        if (isAlwaysInvalid) {
            return Optional.empty();
        }
        try {
            result.next();
            return parser.makeFrom(result);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }


}
