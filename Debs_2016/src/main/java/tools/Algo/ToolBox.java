package tools.Algo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import entity.Comment;
import entity.Post;

public class ToolBox {
	

	
	public static ArrayList<Integer> decreaseScore(List<Post> input, List<Integer> score)
	{
		List<Integer> scoreCopy;
		ArrayList<Integer> top3 = new ArrayList<Integer>(3);
		Calendar last = input.get(input.size()-1).getTimestamp();
		for(int i =0;  i< input.size()-1;i++)
		{
			long timePassed = last.getTimeInMillis()-input.get(i).getTimestamp().getTimeInMillis();

			int decrease = (int)(timePassed/86400000);
			
			if(decrease >= score.get(i) ) 
			{
				score.remove(i);
				input.remove(i);
				i--;
			}
			else {
				
				score.set(i,score.get(i)-decrease);

			}
		}
		
		 scoreCopy = new ArrayList<Integer>(score);

		int index;
		int maxScore;
		for(int i =0; i<3;i++)
		{
			maxScore = Collections.max(scoreCopy);
			if(maxScore <= 0)
			{
				scoreCopy.clear();
				return top3;
			}
			else {
				index = scoreCopy.indexOf(maxScore);
				scoreCopy.set(index,0);
				top3.add(index);
			}
		}
		
		scoreCopy.clear();
		
		return top3;
		
	}
	
	public static String outPut(List<Post> input, List<Integer> score, List<Integer> top3Index, SimpleDateFormat format, Calendar currentDate)
	{
		String result = format.format(currentDate.getTime());
		
		Post postToPrint;
		for(int i =0; i<3; i++)
		{
			
			if(i >= top3Index.size())
			{
				result = result+",-,-,-,-";
				
			}else {
				
				postToPrint  = input.get(top3Index.get(i));
				result = result +","+ Integer.toString(postToPrint.getPostId())+","+postToPrint.getUserName()+","+Integer.toString(score.get(top3Index.get(i)))+","+0;
			}

		}
		
		
		return result;
	}
	
	public static void findRelatedPosts(List<Comment> comments) {
		Comment Ctemp;
		for(Comment c : comments) {
			if(c.getRelatedPost() == -1) {
				Ctemp = c;
				while(Ctemp.getPostCommented() == -1) {
					for(Comment search : comments) {
						if(search.getCommentId() == Ctemp.getCommentReplied()) {
							Ctemp = search;
							break;
						}
					}
				}
				c.setRelatedPost(Ctemp.getPostCommented());
			}
		}
	}
	
	public static ArrayList<Integer> CountScoreComment(ArrayList<Comment> comments)
	{
		
		
		return null;
	}
	

}
