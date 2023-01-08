package som.server.database.exam_callendar;

import java.time.LocalDateTime;

public record ExamCallendar(int dateId,
                            int room,
                            int exam,
                            int jury,
                            LocalDateTime date) {
}
