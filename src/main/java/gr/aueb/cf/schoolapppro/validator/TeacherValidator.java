package gr.aueb.cf.schoolapppro.validator;

import gr.aueb.cf.schoolapppro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapppro.dto.TeacherUpdateDto;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidator {
    public static Map<String,String> valid (TeacherInsertDTO dto){
        Map<String,String> errors = new HashMap<>();
        if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32 ){
            errors.put("firstname", "length");
        }
        if (dto.getLastname().length() < 3 || dto.getLastname().length() > 32 ){
            errors.put("lastname", "length");
        }

        return errors;
    }

    public static Map<String,String> valid (TeacherUpdateDto dto){
        Map<String,String> errors = new HashMap<>();
        if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32 ){
            errors.put("firstname", "length");
        }
        if (dto.getLastname().length() < 3 || dto.getLastname().length() > 32 ){
            errors.put("lastname", "length");
        }

        return errors;
    }
}
