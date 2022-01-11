package groupk;

public class InvalidPasswordException extends Exception { 
  
    int passwordConditionViolated = 0; 
  
    public InvalidPasswordException(int conditionViolated) 
    { 
        super("Invalid Password: "); 
        passwordConditionViolated = conditionViolated; 
    } 
    
    public static void isValid(String password) 
        throws InvalidPasswordException 
    { 
  
   
        if (!((password.length() >= 8) 
              && (password.length() <= 15))) { 
            throw new InvalidPasswordException(1); 
        } 
  
        
        if (password.contains(" ")) { 
            throw new InvalidPasswordException(2); 
        } 
        if (true) { 
            int count = 0; 
  
           
            for (int i = 0; i <= 9; i++) { 
  
              
                String str1 = Integer.toString(i); 
  
                if (password.contains(str1)) { 
                    count = 1; 
                } 
            } 
            if (count == 0) { 
                throw new InvalidPasswordException(3); 
            } 
        } 
  
       
        if (!(password.contains("@") || password.contains("#") 
              || password.contains("!") || password.contains("~") 
              || password.contains("$") || password.contains("%") 
              || password.contains("^") || password.contains("&") 
              || password.contains("*") || password.contains("(") 
              || password.contains(")") || password.contains("-") 
              || password.contains("+") || password.contains("/") 
              || password.contains(":") || password.contains(".") 
              || password.contains(", ") || password.contains("<") 
              || password.contains(">") || password.contains("?") 
              || password.contains("|"))) { 
            throw new InvalidPasswordException(4); 
        } 
  
        if (true) { 
            int count = 0; 
  
            
            for (int i = 65; i <= 90; i++) { 
  
               
                char c = (char)i; 
  
                String str1 = Character.toString(c); 
                if (password.contains(str1)) { 
                    count = 1; 
                } 
            } 
            if (count == 0) { 
                throw new InvalidPasswordException(5); 
            } 
        } 
  
        if (true) { 
            int count = 0; 
            for (int i = 90; i <= 122; i++) {
                char c = (char)i; 
                String str1 = Character.toString(c); 
  
                if (password.contains(str1)) { 
                    count = 1; 
                } 
            } 
            if (count == 0) { 
                throw new InvalidPasswordException(6); 
            } 
        } 

    }
  
    public String toString() 
    { 
        
        switch (passwordConditionViolated) { 
  
        
        case 1: 
            return ("Password length should be"
                    + " between 8 to 15 characters"); 
  
        
        case 2: 
            return ("Password should not"
                    + " contain any space"); 
  
     
        case 3: 
            return ("Password should contain"
                    + " at least one digit(0-9)"); 
  
     
        case 4: 
            return ("Password should contain at "
                    + "least one special character"); 
  
       
        case 5: 
            return ("Password should contain at"
                    + " least one uppercase letter(A-Z)"); 
  
       
        case 6: 
            return ("Password should contain at"
                    + " least one lowercase letter(a-z)"); 
        } 
  
        return (""); 
    } 
}