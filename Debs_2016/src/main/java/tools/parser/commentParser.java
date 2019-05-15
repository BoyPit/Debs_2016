package tools.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import entity.Comment;
import entity.Post;
import tools.Algo.ToolBox;

public class commentParser {

	SimpleDateFormat format;

	public String parseData(String content, ArrayList<Integer> score, ArrayList<Comment> result) {

		format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		ArrayList<Integer> top3index;
		String sortie = "";
		String[] line;
		Comment comment;
		Date timestamp;
		String[] raw = content.split("\r\n");
		for (int i = 0; i < raw.length; i++) {

			try {

				line = raw[i].split("\\|");

				comment = new Comment(getTimeStamp(line[0]), getPostId(line[1]), getUserId(line[2]), getContent(line[3]),line[4],Integer.parseInt(line[5]),Integer.parseInt(line[6]));
				
				if(comment.getPostCommented() != -1)
				{
					comment.setRelatedPost(comment.getPostCommented());
					
				}
				 
				result.add(comment);
				score.add(10);
				
				
				
				for (int j = 0; j < result.size(); j++)
					score.set(j, 10);

				top3index = ToolBox.decreaseScore(result, score);
			//	sortie = sortie + ToolBox.outPut(result, score, top3index, format, post.getTimestamp());
				if (i != raw.length - 1)
					sortie = sortie + "\r\n";

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		ToolBox.findRelatedPosts(result);
		return sortie;

	}

	public Calendar getTimeStamp(String S) throws ParseException {
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
