package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapppro.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolapppro.model.Meeting;

public interface IMeetingService {

    Meeting insertMeeting(MeetingInsertDTO insertDTO) throws MeetingDAOException;

}
