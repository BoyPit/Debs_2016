package tools.Algo;

import java.util.Calendar;
import java.util.List;

import entity.Post;

public class ToolBox {
	
	public static void decreaseScore(List<Post> input, List<Integer> score)
	{
		Calendar last = input.get(input.size()-1).getTimestamp();
		for(int i =0;  i< input.size()-1;i++)
		{
			long timePassed = last.getTimeInMillis()-input.get(i).getTimestamp().getTimeInMillis();

			int decrease = (int)(timePassed/86400000);
			System.out.println(decrease);
			
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
		
	}
	

}
