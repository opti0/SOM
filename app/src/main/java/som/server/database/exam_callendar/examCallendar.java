package som.server.database.exam_callendar;

public record examCallendar(int dateId,
                                int room,
                                int exam,
                                int jury,
                                LocalDateTime date) {
    
}
