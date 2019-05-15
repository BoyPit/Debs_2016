/**
 * 
 */
package tools.parser;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import entity.Post;

/**
 * @author utilisateur1
 *
 */
public class ParserPostTest {

	parserPost parser;
	SimpleDateFormat format;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		parser = new parserPost();
		format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	/**
	 * Test method for {@link tools.parser.parserPost#parseData(java.lang.String)}.
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testParseOneLineData() throws ParseException {

		
		ArrayList<Integer> Score = new ArrayList<Integer>();
		String input = "2010-02-01T05:12:32.921+0000|1039993|3981||Lei Liu\r\n";
		String sortie ="";
		ArrayList<Post> resultat = new ArrayList<Post>(10);
		sortie = parser.parseData(input, Score,resultat);

		assertEquals("2010-02-01T05:12:32.921+0000", format.format(resultat.get(0).getTimestamp().getTime()));
		assertEquals(1039993, resultat.get(0).getPostId());
		assertEquals(3981, resultat.get(0).getUserId());
		assertEquals("", resultat.get(0).getContent());
		assertEquals("Lei Liu", resultat.get(0).getUserName());

	}

	/**
	 * Test method for {@link tools.parser.parserPost#parseData(java.lang.String)}.
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testParseLinesData() throws ParseException {
		ArrayList<Integer> Score = new ArrayList<Integer>();

		String input = "2010-02-01T05:12:32.921+0000|1039993|3981||Lei Liu\r\n"
				+ "2010-02-02T19:53:43.226+0000|299101|4661|photo299101.jpg|Michael Wang\r\n"
				+ "2010-02-09T04:05:10.421+0000|529360|2608||Wei Zhu";
		String sortie ="";
		ArrayList<Post> resultat = new ArrayList<Post>(10);
		sortie = parser.parseData(input, Score,resultat);

		
		
		assertEquals("2010-02-01T05:12:32.921+0000", format.format(resultat.get(0).getTimestamp().getTime()));
		assertEquals(1039993, resultat.get(0).getPostId());
		assertEquals(3981, resultat.get(0).getUserId());
		assertEquals("", resultat.get(0).getContent());
		assertEquals("Lei Liu", resultat.get(0).getUserName());

		assertEquals("2010-02-02T19:53:43.226+0000", format.format(resultat.get(1).getTimestamp().getTime()));
		assertEquals(299101, resultat.get(1).getPostId());
		assertEquals(4661, resultat.get(1).getUserId());
		assertEquals("photo299101.jpg", resultat.get(1).getContent());
		assertEquals("Michael Wang", resultat.get(1).getUserName());

		assertEquals("2010-02-09T04:05:10.421+0000", format.format(resultat.get(2).getTimestamp().getTime()));
		assertEquals(529360, resultat.get(2).getPostId());
		assertEquals(2608, resultat.get(2).getUserId());
		assertEquals("", resultat.get(2).getContent());
		assertEquals("Wei Zhu", resultat.get(2).getUserName());
		
		
		
		
		System.out.println(Score.toString());
		
		

	}
	
	
	@Test
	public void testScoreTest()
	{
		ArrayList<Integer> Score = new ArrayList<Integer>();

		String input = "2010-03-21T00:01:01.943+0000|1|47|A|Tissa Perera\r\n" + 
				"2010-03-22T00:01:01.943+0000|2|47|B|Tissa Perera\r\n" + 
				"2010-03-23T00:01:01.943+0000|3|47|C|Tissa Perera\r\n" + 
				"2010-03-24T00:01:01.943+0000|4|47|D|Tissa Perera\r\n" + 
				"2010-05-25T00:01:01.943+0000|5|47|F|Tissa Perera";
		
		String sortie ="";
		ArrayList<Post> resultat = new ArrayList<Post>(10);
		sortie = parser.parseData(input, Score,resultat);		
	
		System.out.println(Score.toString());
	}
	

	/**
	 * Test method for
	 * {@link tools.parser.parserPost#getTimeStamp(java.lang.String)}.
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testGetTimeStamp() throws ParseException {

		String S = "2010-02-01T05:12:32.923+0000";
		Calendar d = parser.getTimeStamp(S);
		assertEquals("2010-02-01T05:12:32.923+0000", format.format(d.getTime()));


	}

	/**
	 * Test method for {@link tools.parser.parserPost#getPostId(java.lang.String)}.
	 */
	@Test
	public void testGetPostId() {

		String id = "12653";
		assertEquals(12653, parser.getPostId(id));
	}

	/**
	 * Test method for {@link tools.parser.parserPost#getUserId(java.lang.String)}.
	 */
	@Test
	public void testGetUserId() {
		String id = "12653";
		assertEquals(12653, parser.getPostId(id));
	}

}
