package som.server.database.student;

import som.server.database.SqlParser;
import som.server.database.utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class StudentParser implements SqlParser<Student, Integer> {

    @Override
    public String createGetQuery(Integer key) {
        return String.format("SELECT * FROM Uczen WHERE PESEL=\"%s\";", key);
    }

    @Override
    public String createGetAllQuery() {
        return "SELECT * FROM Uczen;";
    }

    @Override
    public String createSaveQuery(Student student) {
        return "INSERT INTO Uczen VALUES (" +
                Utils.toSqlQuery(student.pesel()) + ", " +
                Utils.toSqlQuery(student.name()) + ", " +
                Utils.toSqlQuery(student.surname()) + ", " +
                Utils.toSqlQuery(student.requireAdjustments()) + ", " +
                Utils.toSqlQuery(student.studentCode()) + ", " +
                Utils.toSqlQuery(student.phoneNumber()) + ", " +
                Utils.toSqlQuery(student.email()) + ");";
    }

    @Override
    public String createUpdateQuery(Student student, String[] params) {
        return null;
    }

    @Override
    public String createDeleteQuery(Student student) {
        return null;
    }

    @Override
    public Optional<Student> makeFrom(ResultSet result) {
        try {
            return Optional.of(new Student(
                    result.getString("PESEL"),
                    result.getString("Imie"),
                    result.getString("Nazwisko"),
                    result.getByte("Dostosowania"),
                    result.getString("Kod_Ucznia"),
                    result.getString("Nr_Telefonu"),
                    result.getString("Email")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
