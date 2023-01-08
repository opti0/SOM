package som.server.database.teacher;

public record Teacher (int teacherId,
                        String name,
                        String surname,
                        byte fromOtherSchool,
                        String phoneNumber) {
    
}
