
public class testString {
	
	public static void changeString(String s)
	{
		s = s+"test";
	}

	public static void main(String[] args) {
		
		String s = "test";
		changeString(s);
		System.out.println(s);
		
	}
}
