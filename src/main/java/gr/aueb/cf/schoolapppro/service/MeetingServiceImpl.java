package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.IMeetingDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapppro.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolapppro.model.Meeting;
import org.eclipse.aether.util.graph.visitor.PreorderNodeListGenerator;

import java.sql.Date;

public class MeetingServiceImpl implements IMeetingService{

    private final IMeetingDAO dao;

    public MeetingServiceImpl(IMeetingDAO dao){this.dao = dao;}

    @Override
    public Meeting insertMeeting(MeetingInsertDTO insertDTO) throws MeetingDAOException {
        if (insertDTO == null) return null;

        Meeting meeting;
        try {
            meeting = map(insertDTO);
            dao.insertMeeting(meeting);

        }catch (MeetingDAOException e){
            e.printStackTrace();
            throw e;
        }
        return meeting;
    }


    Meeting map(MeetingInsertDTO dto){
        return new Meeting(dto.getTeachersId(), dto.getStudentsId(), dto.getMetRoom(), (Date) dto.getMetDate());
    }
}
