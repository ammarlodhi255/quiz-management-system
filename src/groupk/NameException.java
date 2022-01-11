package groupk;

class NameException extends Exception{
	public String toString(){
		return "Invalid Name";
	}

	public static void checkName(String name) throws NameException {
		for(int i=0;i<name.length();i++)
		{
			if((name.charAt(i)<'A' || name.charAt(i)>'Z') && (name.charAt(i)<'a' || name.charAt(i)>'z') && name.charAt(i)!=' ')
			{
				throw new NameException();
			}

		}
	}
}
