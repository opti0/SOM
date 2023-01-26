package som.client;

import som.client.csv.StudentData;
import som.server.database.Dao;
import som.server.database.DaoConstants;
import som.server.database.student.Student;

import java.util.List;

public class StudentDataSaver {
    public void save(List<StudentData> data) {
        var levels = DaoConstants.LEVEL.getAll();
        var exams = DaoConstants.EXAM.getAll();

    }
}
