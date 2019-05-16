package tools.Algo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.BlockingQueue;

import entity.Comment;
import entity.Evenement;
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
	
	public static List<Integer> countScore(List<Post> posts, List<Comment> comments, Calendar currentDate){
		
		List<Integer> top3 = new ArrayList<Integer>(3);
		List<Integer> commentScores = new ArrayList<Integer>();
		List<Integer> scoreCopy;
		
		commentScores.clear();
		for(int i = 0; i < comments.size(); i++) {
			commentScores.add(10);
		}
		
		for(int i = 0; i < posts.size(); i++) {
			posts.get(i).setScore(10);
		}
		
		// ### Count the score of every Post
		long timePassed;
		int decrease;
		// Counts what's worth each comment
		for(int j = 0; j < comments.size() - 1; j++) {
			timePassed = currentDate.getTimeInMillis()-comments.get(j).getTimestamp().getTimeInMillis();
			decrease = (int)(timePassed/86400000);
			if(decrease > 10) {
				decrease = 10;
			}
			commentScores.set(j, commentScores.get(j) - decrease);
		}
		// Counts the total score of each post
		for(int i = 0; i < posts.size() - 1; i++)
		{
			timePassed = currentDate.getTimeInMillis()-posts.get(i).getTimestamp().getTimeInMillis();
			decrease = (int)(timePassed/86400000);
			if(decrease > 10) {
				decrease = 10;
			}
			posts.get(i).setScore(posts.get(i).getScore() - decrease);
			for(int c = 0; c < comments.size(); c++) {
				if(comments.get(c).getRelatedPost() == posts.get(i).getPostId()) {
					posts.get(i).setScore(posts.get(i).getScore() + commentScores.get(c));
				}
			}
			if(posts.get(i).getScore() <= 0) {	// Checks if a post is dead (score down to 0)
				posts.remove(i);
				for(int c = 0; c < comments.size(); c++) { // Removes from data every comment linked to the dying post
					if(comments.get(c).getRelatedPost() == posts.get(i).getPostId()) {
						comments.remove(c);
					}
				}
				i--;
			}
		}
		
		// ### Get the top 3 of best Posts (IDs)
		scoreCopy = new ArrayList<Integer>(posts.size());
		for(int i = 0; i < posts.size(); i++) {
			scoreCopy.set(i, posts.get(i).getScore()); 
		}
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
	
	public static void findRelatedPosts(BlockingQueue<Evenement> events) {
		Comment Ctemp, c, search;
		boolean gotItAlready = false;
		for(Evenement e : events) {
			if(e instanceof Comment) {
				c = (Comment)e;
				if(c.getRelatedPost() == -1) {
					Ctemp = c;
					whileloop:
					while(Ctemp.getPostCommented() == -1) {
						forloop:
						for(Evenement searchE : events) {
							if(searchE instanceof Comment) {
								search = (Comment)searchE;
								if(search.getCommentId() == Ctemp.getCommentReplied()) {
									Ctemp = search;
									if(Ctemp.getRelatedPost() != -1) {
										c.setRelatedPost(Ctemp.getRelatedPost());
										gotItAlready = true;
										break whileloop;
									}
									break forloop;
								}
						}
					}
					if(!gotItAlready) {
						c.setRelatedPost(Ctemp.getPostCommented());
					}
					gotItAlready = false;
				}
			}
		}
	}
	
	public static ArrayList<Integer> CountScoreComment(ArrayList<Comment> comments)
	{
		
		
		return null;
	}
	

}
