package threading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.BlockingQueue;

import entity.Comment;
import entity.Evenement;
import entity.Post;

public class Producer implements Runnable {

	private File post;
	private File comment;
	private BlockingQueue<Evenement> timeLine;

	public Producer(BlockingQueue<Evenement> input, String post, String comment) {
		timeLine = input;
		this.post = getFileFromResources(post);
		this.comment = getFileFromResources(comment);

	}

	@Override
	public void run() {

		try (BufferedReader brPost = new BufferedReader(new FileReader(post))) {

			try (BufferedReader brComment = new BufferedReader(new FileReader(comment))) {
				String linePost = null;
				int posPost = 0;
				int posComment = 0;
				String lineComment = null;
				Post postCompare = null;
				Comment commentCompare;
				while (((linePost = brPost.readLine()) != null)) {
					try {
						lineComment = brComment.readLine();

						if (lineComment != null && linePost != null && !lineComment.isEmpty() && !linePost.isEmpty()) {
							commentCompare = commentParser(lineComment);
							postCompare = postParser(linePost);

							while (commentCompare.getTimestamp().compareTo(postCompare.getTimestamp()) == 1) {

								
								timeLine.add(postCompare);
								System.out.println(
										"Post line add : " + postCompare.getTimestamp().getTime().toGMTString());
								posPost++;
								if (((linePost = brPost.readLine()) != null)) {
									postCompare = postParser(linePost);
								}

							}
							while (commentCompare.getTimestamp().compareTo(postCompare.getTimestamp()) == -1) {
								timeLine.add(commentCompare);
								System.out.println(
										"Comment line add : " + commentCompare.getTimestamp().getTime().toGMTString());
								posComment++;
								if ((lineComment = brComment.readLine()) != null) {
									commentCompare = commentParser(lineComment);
								}
							}

						} else if (lineComment != null && !lineComment.isEmpty() && linePost == null) {
							commentCompare = commentParser(lineComment);
							timeLine.add(commentCompare);
							System.out.println("Comment line add sngle : "
									+ commentCompare.getTimestamp().getTime().toGMTString());
							posComment++;
						} else if (linePost != null && !linePost.isEmpty() && lineComment == null) {
							postCompare = postParser(linePost);
							timeLine.add(postCompare);
							System.out.println(
									"Post line add single : " + postCompare.getTimestamp().getTime().toGMTString());
							posPost++;
						}

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public File getFileFromResources(String fileName) {

		ClassLoader classLoader = getClass().getClassLoader();

		java.net.URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			return new File(resource.getFile());
		}
	}

	public Post postParser(String Line) throws ParseException {
		String[] line = Line.split("\\|");
		Post post;
		post = new Post(getTimeStamp(line[0]), getPostId(line[1]), getUserId(line[2]), getContent(line[3]),
				getUserName(line[4]));

		return post;
	}

	public Comment commentParser(String Line) throws NumberFormatException, ParseException {
		String[] line = Line.split("\\|");
		Comment comment;
		if (line[5].isEmpty()) {
			line[5] = "-1";
		}
		if (line.length < 7) {
			line = Arrays.copyOf(line, line.length + 1);
			line[6] = "-1";

		}
		comment = new Comment(getTimeStamp(line[0]), getPostId(line[1]), getUserId(line[2]), getContent(line[3]),
				line[4], Integer.parseInt(line[5]), Integer.parseInt(line[6]));

		return comment;
	}

	public Calendar getTimeStamp(String S) throws ParseException {
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			cal.setTime(sdf.parse(S));
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
