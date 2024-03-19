package test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailService{
	
	HashMap<String, String> users = new HashMap<String, String>();
	
	
	public int isValidLength(String pass) {
		if(pass.length()<6) {
			return 1;
		}
		else if(pass.length()>12) {
			return 2;
		}
		return 0;
	}
	
	public boolean isValidEmail(String email) {
		String regex="^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+$";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(email);
		return m.matches();
	}
	
	public boolean userExists(String email) {
		if (users.containsKey(email)) {
			return true;
		}
		return false;
	}
	
	public boolean registerUser(String email, String pwd) {
		boolean flag=false;
		int x=isValidLength(pwd);
		if(x==1) {
			System.out.println("Password should be at least 6 characters");
		}
		else if(x==2) {
			System.out.println("Password should be less than 12 characters");
		}
		else if(x==0) {
			users.put(email, pwd);
			System.out.println("Successfully registered!!");
			flag=true;
		}
		return flag;
	}
	
	public boolean newPassword(String email, String pwd) {
		boolean flag=false;
		int x=isValidLength(pwd);
		if(x==1) {
			System.out.println("Password should be at least 6 characters");
		}
		else if(x==2) {
			System.out.println("Password should be less than 12 characters");
		}
		else if(x==0) {
			users.replace(email, pwd);
			flag=true;
		}
		return flag;
		
	}
	
	public int login(String email, String pwd) {
		if (!users.containsKey(email)) {
			return 1;
		}
		else if (!(users.containsKey(email) && users.get(email).equals(pwd))) {
			return 2;
		}
		return 0;
	}
	
	public String sendEmail(String to, String subject, String body) {
		return "Mail sent to - " + to;
	}
}
