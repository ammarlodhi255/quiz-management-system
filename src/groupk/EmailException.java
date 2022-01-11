package groupk;

public class EmailException extends Exception{
	String email;
        
        EmailException() {}
        
	EmailException(String e) {
            email = e;
	}

	public static void checkMail(String email) throws EmailException {
		for(int i=0; i< email.length();i++)
		{
                    if(!(email.contains(".com") && email.contains("@"))) {
			throw new EmailException();
                    }

		}
	}
        
        public String toString(){
		return "Invalid Email";
	}
}