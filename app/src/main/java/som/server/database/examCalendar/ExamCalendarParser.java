package som.server.database.examCalendar;

import som.server.database.SqlParser;
import som.server.database.utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ExamCalendarParser implements SqlParser<ExamCalendar, Integer> {

    @Override
    public String createGetQuery(Integer key) {
        return String.format("SELECT * FROM Terminarz_Egzaminow WHERE ID_Terminu=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Terminarz_Egzaminow;";
    }

    @Override
    public String createSaveQuery(ExamCalendar examCalendar) {
        return "INSERT INTO Terminarz_Egzaminow VALUES (null, " +
                Utils.toSqlQuery(examCalendar.room()) + ", " +
                Utils.toSqlQuery(examCalendar.exam()) + ", " +
                Utils.toSqlQuery(examCalendar.jury()) + ", " +
                Utils.toSqlQuery(examCalendar.date()) + ");";
    }

    @Override
    public String createUpdateQuery(ExamCalendar examCalendar, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(ExamCalendar examCalendar) {
        return null;
    }

    @Override
    public Optional<ExamCalendar> makeFrom(ResultSet result) {
        try {
            return Optional.of(new ExamCalendar(
                    result.getInt("ID_Terminu"),
                    result.getInt("Sala"),
                    result.getInt("Egzamin"),
                    result.getInt("Komisja"),
                    result.getTimestamp("Termin")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
