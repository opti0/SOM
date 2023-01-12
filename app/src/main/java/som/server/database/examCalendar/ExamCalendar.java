package som.server.database.examCalendar;

import java.sql.Timestamp;

public record ExamCalendar(int dateId,
                           int room,
                           int exam,
                           int jury,
                           Timestamp date) {
    
}
