package io.mountblue.learn.pojo;

public class Request {
	private int id;
	private String name;
	private String email;
	private String message;
	private boolean isArchived;
	public Request() {
		super();
	}
	public Request(int id, String name, String email, String message,boolean isArchived) {
		super();
		this.id = id;
		this.name=name;
		this.email = email;
		this.message = message;
		this.isArchived=isArchived;
	}
	
	public Request(String name, String email, String message) {
		super();
		this.name = name;
		this.email = email;
		this.message = message;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isArchived() {
		return isArchived;
	}
	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
