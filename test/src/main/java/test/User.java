package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User{
	
	public static void main(String args[]) {
		
		MailService mailService = new MailService();
		String email="";
		String pass="";
		Scanner sc = new Scanner(System.in);

		System.out.println("Register yourself -");

		System.out.println("Enter your email: ");
		email = sc.nextLine();
		if(!mailService.isValidEmail(email))
		{
			System.out.println("Invalid Email");
			System.exit(0);
		}

		System.out.println("Enter password: ");
		pass = sc.nextLine();

		if(!mailService.registerUser(email, pass))
			System.exit(0);
		
		System.out.println("\nForgot Password -");

		System.out.println("Enter your email: ");
		email = sc.nextLine();

		if (mailService.userExists(email)) {
            String s = mailService.sendEmail(email, "This is your password", "user123");
            System.out.println(s);
			System.out.println("Enter your new password: ");
			pass = sc.nextLine();

			if(!mailService.newPassword(email, pass))
				System.exit(0);

		} else {
			System.out.println("\nInvalid Email");
		}

		System.out.println("\nLog Into Your Account\n");

		System.out.println("Enter your email: ");
		email = sc.nextLine();

		System.out.println("Enter your password: ");
		pass = sc.nextLine();
		
		int x = mailService.login(email, pass);

		if (x==0) {
			System.out.println("\nLogin Successfull!!");
		} else if (x==1){
			System.out.println("\nWrong email");
		}
		else if (x==2){
			System.out.println("\nWrong password");
		}

		sc.close();
	}
}
