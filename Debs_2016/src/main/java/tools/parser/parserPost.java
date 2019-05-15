package tools.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import entity.Post;
import tools.Algo.ToolBox;

public class parserPost implements ParserInterface {

	
	
	
	public ArrayList<Post> parseData(String content, ArrayList<Integer> score ) {

		ArrayList<Post> result = new ArrayList<Post>(15);
		String[] line;
		Post post;
		Date timestamp;
		for (String raw : content.split("\r\n")) {

			try {
				
				line = raw.split("\\|");
				
				post = new Post(getTimeStamp(line[0]), getPostId(line[1]), getUserId(line[2]), getContent(line[3]),getUserName(line[4]));
				
				result.add(post);
				score.add(10);
				for( int i = 0; i<result.size(); i++)
					score.set(i, 10);
				//score = new ArrayList<Integer>(Collections.nCopies(result.size(),10));
				
				ToolBox.decreaseScore(result,score);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;

	}

	
	
	
	
	
	
	
	
	public Calendar getTimeStamp(String S) throws ParseException {
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			cal.setTime(sdf.parse(S));
					
			//Date dateParsed = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(S);
			return cal;

		} catch (ParseException e) {

			throw new ParseException(S, 0);
		}

	}

	public int getPostId(String S) {
		return Integer.parseInt(S);
	}

	public int getUserId(String S) {
		return Integer.parseInt(S);
	}

	public String getUserName(String S) {
		return S;
	}

	public String getContent(String S) {
		return S;
	}

}
