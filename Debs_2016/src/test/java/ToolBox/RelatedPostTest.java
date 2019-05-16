package ToolBox;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import entity.Comment;
import tools.Algo.ToolBox;

public class RelatedPostTest {

	ArrayList<Comment> comments;
	@Before
	public void setUp() throws Exception {
		 comments = new ArrayList<Comment>();
		
	}

	
	@Test
	public void testRelatedCommented() {
		
		try {
			comments.add(new Comment(getTimeStamp("2010-03-22T00:01:01.943+0000"), 100, 25, "c1", "DatYannou", -1, 1));
			comments.add(new Comment(getTimeStamp("2010-03-22T00:01:01.943+0000"), 101, 25, "c2", "DatYannou", -1, 1));
			comments.add(new Comment(getTimeStamp("2010-03-22T00:01:01.943+0000"), 102, 25, "c3", "DatYannou", 100, -1));
			comments.add(new Comment(getTimeStamp("2010-03-22T00:01:01.943+0000"), 103, 25, "c4", "DatYannou", 100, -1));
			comments.add(new Comment(getTimeStamp("2010-03-22T00:01:01.943+0000"), 104, 25, "c5", "DatYannou", -1, 2));
			comments.add(new Comment(getTimeStamp("2010-03-22T00:01:01.943+0000"), 105, 25, "c6", "DatYannou", 102, -1));
			ToolBox.findRelatedPosts(comments) ;

			 assertEquals(1, comments.get(0).getRelatedPost());
			 assertEquals(1, comments.get(1).getRelatedPost());
			 assertEquals(1, comments.get(2).getRelatedPost());
			 assertEquals(1, comments.get(3).getRelatedPost());
			 assertEquals(2, comments.get(4).getRelatedPost());
			 assertEquals(1, comments.get(5).getRelatedPost());
			 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	//	Comment(Calendar timestamp, int commentId, int userId, String content, String userName, int commentReplied,int postCommented) 
		
		 
		
	
	}
	
	
	
	public static Calendar getTimeStamp(String S) throws ParseException {
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			cal.setTime(sdf.parse(S));

			// Date dateParsed = new
			// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(S);
			return cal;

		} catch (ParseException e) {

			throw new ParseException(S, 0);
		}

	}

}
