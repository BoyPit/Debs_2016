package threading;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import entity.Comment;
import entity.Evenement;
import entity.Post;
import tools.Algo.ToolBox;

public class Consumer implements Runnable {
	
	BlockingQueue<Evenement> queue;
	List<Post> posts;
	List<Comment> comments;
	List<Post> top3;
	List<Integer> top3IDs;
	List<Post> lastTop3;
	Calendar currentDate;
	
	public Consumer(BlockingQueue<Evenement> queue, List<Post> posts, List<Comment> comments) {
		super();
		this.queue = queue;
		this.posts = posts;
		this.comments = comments;
		this.lastTop3 = new ArrayList<Post>(3);
	}

	@Override
	public void run() {
		try {
			Evenement event = queue.take();
			if(event instanceof Comment) {
				comments.add((Comment) event);
				currentDate = comments.get(comments.size()-1).getTimestamp();
			}else if(event instanceof Post){
				posts.add((Post) event);
				currentDate = posts.get(posts.size()-1).getTimestamp();
			}else {
				System.out.println("\nInput object is neither a Post or a Comment.\n");
			}
				
		} catch (Exception e) {
			
		}

		top3IDs = ToolBox.countScore(posts, comments, currentDate);
		for(Post p: posts) {
			for(int i = 0; i < 3; i++) {
				if(p.getPostId() == top3IDs.get(i)) {
					top3.set(i, p);
				}
			}
		}
		
		if(!lastTop3.get(0).equals(top3.get(0)) || !lastTop3.get(1).equals(top3.get(1)) || !lastTop3.get(2).equals(top3.get(2))) {
			// Display the new top 3
		}
		
		lastTop3 = new ArrayList<Post>(top3);
	}

}
