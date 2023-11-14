package gr.aueb.cf.schoolapppro.model;


import java.sql.Date;
import java.time.LocalDate;

public class Meeting {
    private int teacherId;
    private int studentId;
    private String meetingRoom;
    private Date meetingDate;

    public Meeting () {}

    public Meeting(int teacherId, int studentId, String meetingRoom, Date meetingDate) {
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.meetingRoom = meetingRoom;
        this.meetingDate = meetingDate;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }
}
