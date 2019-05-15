package output.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import entity.Post;
import tools.parser.parserPost;

public class outPutTest {
	
	
	parserPost parser;
	SimpleDateFormat format;
	@Before
	public void setUp() throws Exception {
		parser = new parserPost();
		format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	@Test
	public void testOutPutPost1() {
		String sortie ="";
		String expected = "2010-02-01T05:12:32.921+0000,1039993,Lei Liu,10,0,-,-,-,-,-,-,-,-\r\n" + 
				"2010-02-02T19:53:43.226+0000,299101,Michael Wang,10,0,1039993,Lei Liu,9,0,-,-,-,-\r\n" + 
				"2010-02-09T04:05:10.421+0000,529360,Wei Zhu,10,0,299101,Michael Wang,4,0,1039993,Lei Liu,3,0";
		
		String input = "2010-02-01T05:12:32.921+0000|1039993|3981||Lei Liu\r\n"
				+ "2010-02-02T19:53:43.226+0000|299101|4661|photo299101.jpg|Michael Wang\r\n"
				+ "2010-02-09T04:05:10.421+0000|529360|2608||Wei Zhu";
		
		
		ArrayList<Integer> Score = new ArrayList<Integer>();
		ArrayList<Post> resultat = new ArrayList<Post>(15);
		sortie = parser.parseData(input,Score, resultat);
		System.out.println(sortie);
		
		assertEquals(expected, sortie);
		
		
	}

}
