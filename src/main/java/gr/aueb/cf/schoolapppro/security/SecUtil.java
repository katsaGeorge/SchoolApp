package gr.aueb.cf.schoolapppro.security;

    import org.mindrot.jbcrypt.BCrypt;

    public class SecUtil {

        private SecUtil(){}

        public static boolean checkPassword(String inputPassword,String storedHashPassword){
            return BCrypt.checkpw(inputPassword,storedHashPassword);
        }

        public static String hashpassword(String inputPassword){
            int workload = 12;
            String salt = BCrypt.gensalt(workload);
            return BCrypt.hashpw(inputPassword,salt);
        }

    }

