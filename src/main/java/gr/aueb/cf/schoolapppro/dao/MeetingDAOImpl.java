package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.service.dbutil.DBUtil;
import gr.aueb.cf.schoolapppro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapppro.model.Meeting;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MeetingDAOImpl implements IMeetingDAO{
    @Override
    public Meeting insertMeeting(Meeting meeting) throws MeetingDAOException {
        String sql = "INSERT INTO MEETINGS (TEACHER_ID, STUDENT_ID, MEETING_ROOM, MEETING_DATE) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, meeting.getTeacherId());
            ps.setInt(2, meeting.getStudentId());
            ps.setString(3, meeting.getMeetingRoom());
            ps.setDate(4, (meeting.getMeetingDate()));

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new MeetingDAOException("Error inserting meeting");
        }

        return meeting;
    }

    @Override
    public Meeting updateMeeting(Meeting meeting) throws MeetingDAOException {

        String deleteSql = "DELETE FROM MEETINGS WHERE TEACHER_ID = ? AND STUDENT_ID = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement deletePs = connection.prepareStatement(deleteSql)) {

            deletePs.setInt(1, meeting.getTeacherId());
            deletePs.setInt(2, meeting.getStudentId());

            DBUtil.beginTransaction();
            deletePs.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new MeetingDAOException("Error updating meeting");
        }

        String insertSql = "INSERT INTO MEETINGS (TEACHER_ID, STUDENT_ID, MEETING_ROOM, MEETING_DATE) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement insertPs = connection.prepareStatement(insertSql)) {

            insertPs.setInt(1, meeting.getTeacherId());
            insertPs.setInt(2, meeting.getStudentId());
            insertPs.setString(3, meeting.getMeetingRoom());
            insertPs.setDate(4, (meeting.getMeetingDate()));

            DBUtil.beginTransaction();
            insertPs.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new MeetingDAOException("Error updating meeting");
        }

        return meeting;
    }


    @Override
    public void deleteMeetingBy(int teacherId, int studentId) throws MeetingDAOException {
        String sql = "DELETE FROM MEETINGS WHERE TEACHER_ID = ? AND STUDENT_ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, teacherId);
            ps.setInt(2, studentId);

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new MeetingDAOException("Error deleting meeting by teacherId and studentId");
        }
    }

    @Override
    public List<Meeting> getMeetingByDate(Date meetingDate) throws MeetingDAOException {
        String sql = "SELECT * FROM MEETINGS WHERE MEETING_DATE = ?";
        List<Meeting> meetings = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setObject(1, meetingDate);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int teacherId = rs.getInt("TEACHER_ID");
                    int studentId = rs.getInt("STUDENT_ID");
                    String meetingRoom = rs.getString("MEETING_ROOM");
                    Date date = rs.getDate("MEETING_DATE");
                    Meeting meeting = new Meeting(teacherId, studentId, meetingRoom, date);
                    meetings.add(meeting);
                }
            }

        } catch (SQLException e) {
            throw new MeetingDAOException("Error retrieving meetings by date");
        }

        return meetings;
    }


    @Override
    public List<Meeting> getMeetingByRoom(String meetingRoom) throws MeetingDAOException {
        String sql = "SELECT * FROM MEETINGS WHERE MEETING_ROOM = ?";
        List<Meeting> meetings = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, meetingRoom);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int teacherId = rs.getInt("TEACHER_ID");
                    int studentId = rs.getInt("STUDENT_ID");
                    Date date = rs.getDate("MEETING_DATE");
                    Meeting meeting = new Meeting(teacherId, studentId, meetingRoom, date);
                    meetings.add(meeting);
                }
            }

        } catch (SQLException e) {
            throw new MeetingDAOException("Error retrieving meetings by room");
        }

        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsByTeacherId(int teacherId) throws MeetingDAOException {
        String sql = "SELECT * FROM MEETINGS WHERE TEACHER_ID = ?";
        List<Meeting> meetings = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, teacherId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int studentId = rs.getInt("STUDENT_ID");
                    String meetingRoom = rs.getString("MEETING_ROOM");
                    Date date = rs.getDate("MEETING_DATE");
                    Meeting meeting = new Meeting(teacherId, studentId, meetingRoom, date);
                    meetings.add(meeting);
                }
            }

        } catch (SQLException e) {
            throw new MeetingDAOException("Error retrieving meetings by teacherId");
        }

        return meetings;
    }


    @Override
    public List<Meeting> getMeetingsByStudentId(int studentId) throws MeetingDAOException {
        String sql = "SELECT * FROM MEETINGS WHERE STUDENT_ID = ?";
        List<Meeting> meetings = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int teacherId = rs.getInt("TEACHER_ID");
                    String meetingRoom = rs.getString("MEETING_ROOM");
                    Date date = rs.getDate("MEETING_DATE");
                    Meeting meeting = new Meeting(teacherId, studentId, meetingRoom, date);
                    meetings.add(meeting);
                }
            }

        } catch (SQLException e) {
            throw new MeetingDAOException("Error retrieving meetings by studentId");
        }

        return meetings;
    }

}
