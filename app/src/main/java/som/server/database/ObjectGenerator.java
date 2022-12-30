package som.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ObjectGenerator<T> {
    private final ResultSet result;
    private final SqlParser<T, ?> parser;

    public ObjectGenerator(ResultSet result, SqlParser<T, ?> parser) {
        this.result = result;
        this.parser = parser;
    }

    public boolean hasNext() {
        try {
            return !result.isLast();
        } catch (SQLException e) {
            return false;
        }
    }

    public Optional<T> getNext() {
        try {
            result.next();
        } catch (SQLException e) {
            return Optional.empty();
        }
        return parser.makeFrom(result);
    }


}
