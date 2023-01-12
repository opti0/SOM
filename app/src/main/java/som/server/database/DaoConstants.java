package som.server.database;

import som.server.database.exam.Exam;
import som.server.database.exam.ExamParser;
import som.server.database.examCalendar.ExamCalendar;
import som.server.database.examCalendar.ExamCalendarParser;
import som.server.database.examSubject.ExamSubject;
import som.server.database.examSubject.ExamSubjectParser;
import som.server.database.jury.Jury;
import som.server.database.jury.JuryParser;
import som.server.database.juryMembers.JuryMembers;
import som.server.database.juryMembers.JuryMembersParser;
import som.server.database.level.Level;
import som.server.database.level.LevelParser;
import som.server.database.licenses.Licenses;
import som.server.database.licenses.LicensesParser;
import som.server.database.qualifications.Qualifications;
import som.server.database.qualifications.QualificationsParser;
import som.server.database.room.Room;
import som.server.database.room.RoomParser;
import som.server.database.schoolSubject.SchoolSubject;
import som.server.database.schoolSubject.SchoolSubjectParser;
import som.server.database.student.Student;
import som.server.database.student.StudentParser;
import som.server.database.studentsExam.StudentsExam;
import som.server.database.studentsExam.StudentsExamParser;
import som.server.database.subjectKnowledge.SubjectKnowledge;
import som.server.database.subjectKnowledge.SubjectKnowledgeParser;
import som.server.database.teacher.Teacher;
import som.server.database.teacher.TeacherParser;

import java.time.temporal.Temporal;

public class DaoConstants {
    public static final Dao<Exam, Integer> EXAM = new Dao<>(new ExamParser());
    public static final Dao<ExamCalendar, Integer> EXAM_CALENDAR = new Dao<>(new ExamCalendarParser());
    public static final Dao<ExamSubject, Integer> EXAM_SUBJECT = new Dao<>(new ExamSubjectParser());
    public static final Dao<Jury, Integer> JURY = new Dao<>(new JuryParser());
    public static final Dao<JuryMembers, Integer> JURY_MEMBERS = new Dao<>(new JuryMembersParser());
    public static final Dao<Level, Integer> LEVEL = new Dao<>(new LevelParser());
    public static final Dao<Licenses, Integer> LICENSES = new Dao<>(new LicensesParser());
    public static final Dao<Qualifications, Integer> QUALIFICATIONS = new Dao<>(new QualificationsParser());
    public static final Dao<Room, Integer> ROOM = new Dao<>(new RoomParser());
    public static final Dao<SchoolSubject, Integer> SCHOOL_SUBJECT = new Dao<>(new SchoolSubjectParser());
    public static final Dao<Student, Integer> STUDENT = new Dao<>(new StudentParser());
    public static final Dao<StudentsExam, Integer> STUDENTS_EXAM = new Dao<>(new StudentsExamParser());
    public static final Dao<SubjectKnowledge, Integer> SUBJECT_KNOWLEDGE = new Dao<>(new SubjectKnowledgeParser());
    public static final Dao<Teacher, Integer> TEACHER = new Dao<>(new TeacherParser());

}
