package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapppro.model.Meeting;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface IMeetingDAO {
    Meeting insertMeeting(Meeting meeting) throws MeetingDAOException;
    Meeting updateMeeting(Meeting meeting) throws MeetingDAOException;
    void deleteMeetingBy(int teacherId, int studentId) throws MeetingDAOException;
    List<Meeting> getMeetingByDate(Date meetingDate) throws MeetingDAOException;
    List<Meeting> getMeetingByRoom(String meetingRoom) throws MeetingDAOException;
    List<Meeting> getMeetingsByTeacherId(int teacherId) throws MeetingDAOException;
    List<Meeting> getMeetingsByStudentId(int studentId) throws MeetingDAOException;
}
