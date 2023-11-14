package gr.aueb.cf.schoolapppro.validator;

import gr.aueb.cf.schoolapppro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapppro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapppro.dto.TeacherInsertDTO;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class StudentValidator {

    public static Map<String, String> valid(StudentInsertDTO dto) {
        Calendar today = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(dto.getBirthDate());
        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        Map<String, String> errors = new HashMap<>();
        if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32) {
            errors.put("firstname", "length");
        }
        if (dto.getLastname().length() < 3 || dto.getLastname().length() > 32) {
            errors.put("lastname", "length");
        }

        if (age < 18) {

            errors.put("age", "is not valid");

        }
        return errors;
    }
    public static Map<String, String> valid(StudentUpdateDTO dto) {
        Calendar today = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(dto.getBirthdate());
        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        Map<String, String> errors = new HashMap<>();
        if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32) {
            errors.put("firstname", "length");
        }
        if (dto.getLastname().length() < 3 || dto.getLastname().length() > 32) {
            errors.put("lastname", "length");
        }

        if (age < 18) {

            errors.put("age", "is not valid");

        }
        return errors;
    }
}
