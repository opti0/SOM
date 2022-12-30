package som.server.database;

import java.sql.ResultSet;
import java.util.Optional;

public interface SqlParser<T, K> {
    String createGetQuery(K key);
    String createGetAllQuery();
    String createSaveQuery(T t);
    String createUpdateQuery(T t, String[] params);
    String createDeleteQuery(T t);

    Optional<T> makeFrom(ResultSet result);
}
