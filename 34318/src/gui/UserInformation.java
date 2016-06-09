package gui;

public class UserInformation  {
	private String username;
	
	public UserInformation(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return username;
	}
	
	public void fun(){
		System.out.println("fun");
	}


	
}
