package gr.aueb.cf.schoolapppro.dto;

import gr.aueb.cf.schoolapppro.model.Meeting;

import java.util.Date;

public class MeetingInsertDTO {

   private int teachersId;
   private int studentsId;
   private Date metDate;
   private String metRoom;

   public MeetingInsertDTO(){}

    public MeetingInsertDTO(int teachersId, int studentsId, Date metDate, String metRoom) {
        this.teachersId = teachersId;
        this.studentsId = studentsId;
        this.metDate = metDate;
        this.metRoom = metRoom;
    }

    public int getTeachersId() {
        return teachersId;
    }

    public void setTeachersId(int teachersId) {
        this.teachersId = teachersId;
    }

    public int getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(int studentsId) {
        this.studentsId = studentsId;
    }

    public Date getMetDate() {
        return metDate;
    }

    public void setMetDate(Date metDate) {
        this.metDate = metDate;
    }

    public String getMetRoom() {
        return metRoom;
    }

    public void setMetRoom(String metRoom) {
        this.metRoom = metRoom;
    }
}
