package gr.aueb.cf.schoolapppro.validator;

import gr.aueb.cf.schoolapppro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapppro.dto.UserLoginDTO;
import gr.aueb.cf.schoolapppro.dto.UserRegisterDTO;

import java.util.HashMap;
import java.util.Map;

public class UserValidator {

    public UserValidator(){}

    public static Map<String,String> validate(UserRegisterDTO dto){
            Map<String,String> errors = new HashMap<>();

            if (dto.getUsername().length() < 3 || dto.getUsername().length() > 32 ){
            errors.put("username", "length");
            }
            if (dto.getUsername().matches("^.*\\s+.*$")){
                errors.put("username","whitespaces");
            }
            if(dto.getPassword().length() < 3 || dto.getPassword().length() > 32 ){
                errors.put("password", "length");
            }
            if(!(dto.getPassword().equals(dto.getConfPass()))){
                errors.put("password","notmatch");
            }

            return errors;
    }
    public static Map<String,String> validatelog(UserLoginDTO dto){
        Map<String,String> errors = new HashMap<>();

        if (dto.getUsername().length() < 3 || dto.getUsername().length() > 32 ){
            errors.put("username", "length");
        }
        if (dto.getUsername().matches("^.*\\s+.*$")){
            errors.put("username","whitespaces");
        }
        if(dto.getPassword().length() < 3 || dto.getPassword().length() > 32 ){
            errors.put("password", "length");
        }
        return errors;
    }


}
