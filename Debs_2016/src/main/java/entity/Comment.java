package entity;

import java.util.Calendar;

public class Comment {

	private Calendar timestamp;
	private int commentId;
	private int userId;
	private String content;
	private String userName;
	private int commentReplied; 
	private int postCommented;
	private int relatedPost;

	
	
	public Comment(Calendar timestamp, int commentId, int userId, String content, String userName, int commentReplied,
			int postCommented) {
		super();
		this.timestamp = timestamp;
		this.commentId = commentId;
		this.userId = userId;
		this.content = content;
		this.userName = userName;
		this.commentReplied = commentReplied;
		this.postCommented = postCommented;
		this.relatedPost = -1;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentId;
		result = prime * result + commentReplied;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + postCommented;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (commentId != other.commentId)
			return false;
		if (commentReplied != other.commentReplied)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (postCommented != other.postCommented)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}



	public Calendar getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}



	public int getCommentId() {
		return commentId;
	}



	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public int getCommentReplied() {
		return commentReplied;
	}



	public void setCommentReplied(int commentReplied) {
		this.commentReplied = commentReplied;
	}



	public int getPostCommented() {
		return postCommented;
	}



	public void setPostCommented(int postCommented) {
		this.postCommented = postCommented;
	}



	public int getRelatedPost() {
		return relatedPost;
	}



	public void setRelatedPost(int relatedPost) {
		this.relatedPost = relatedPost;
	}
	
	
	
	
	
	
	
	
}
