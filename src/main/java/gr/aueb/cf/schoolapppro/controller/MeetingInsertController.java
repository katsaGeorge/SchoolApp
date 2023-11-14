package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.IMeetingDAO;
import gr.aueb.cf.schoolapppro.dao.MeetingDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapppro.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolapppro.service.MeetingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/schoolapppro/meetinginsert")
public class MeetingInsertController extends HttpServlet {

    private final IMeetingDAO dao = new MeetingDAOImpl();
    private final MeetingServiceImpl service = new MeetingServiceImpl(dao);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Integer teacherId = Integer.parseInt(request.getParameter("teacherid"));
            Integer studentId = Integer.parseInt(request.getParameter("studentid"));
            Date meetdate = dateFormat.parse(request.getParameter("date"));
            String metroom = request.getParameter("meetingroom");

            MeetingInsertDTO insertDTO = new MeetingInsertDTO(teacherId,studentId,meetdate,metroom);

            service.insertMeeting(insertDTO);
            request.setAttribute("inserted",true);
            request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);

        }catch (ParseException | MeetingDAOException e){
            e.printStackTrace();
        }


    }
}
