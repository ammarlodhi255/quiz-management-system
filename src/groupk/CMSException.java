package groupk;

public class CMSException extends Exception{
	public String toString(){
		return "Invalid CMD ID";
	}

	public static void checkCMS(String C) throws CMSException {
		if(C.charAt(2)!='-' || C.charAt(5)!='-' || C.length() > 13)
		{
			throw new CMSException();
		}
	}
}
