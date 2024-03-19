package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MailServiceTest {
	
	public MailService mailService;
	HashMap<String, String> newUsers = new HashMap<String, String>();

	
	@BeforeEach void test() 
    { 
		mailService = mock(MailService.class);
		
    } 
	
	@Test
	void testSendEmail() {
		when(mailService.sendEmail(anyString(),anyString(),anyString())).thenReturn("Mail sent to user@gmail.com");
		String m = mailService.sendEmail("user@gmail.com","This is a subject","This is body");
		verify(mailService, times(1)).sendEmail("user@gmail.com","This is a subject","This is body");
		assertEquals("Mail sent to user@gmail.com",m);
	}
	
	@Test 
	public void testValidEmail() {
//		Test for valid mails
		when(mailService.isValidEmail("test@gmail.com")).thenReturn(true);
		boolean validMail1 = mailService.isValidEmail("test@gmail.com");
		assertTrue(validMail1);
		when(mailService.isValidEmail("testfor2324535@gmail.com")).thenReturn(true);
		boolean validMail2 = mailService.isValidEmail("testfor2324535@gmail.com");
		assertTrue(validMail2);
	}
	
	@Test
	public void testInvalidEmail() {
//		Test for invalid mails
		when(mailService.isValidEmail("test@_com")).thenReturn(false);
		boolean inValidMail1 = mailService.isValidEmail("test@_com");
		assertFalse(inValidMail1,"Invalid Email Type");
		when(mailService.isValidEmail("test..com")).thenReturn(false);
		boolean inValidMail2 = mailService.isValidEmail("test..com");
		assertFalse(inValidMail2,"Invalid Email Type");
	}
	
	@Test
	public void testPasswordLength() {
//		When password is less than 6
		when(mailService.isValidLength("244")).thenReturn(1);
		int demoEmail1 = mailService.isValidLength("244");
		assertEquals(1, demoEmail1);
//		When password is greater greater than 6 and less than 10
		when(mailService.isValidLength("jason234")).thenReturn(0);
		int demoEmail2 = mailService.isValidLength("jason234");
		assertEquals(0, demoEmail2);
//		When password is greater than 10
		when(mailService.isValidLength("jasonmichelle@@32498499300")).thenReturn(2);
		int demoEmail3 = mailService.isValidLength("jasonmichelle@@32498499300");
		assertEquals(2, demoEmail3);
	}

	@Test
	public void testUserRegistered() {
		when(mailService.registerUser("test@gmail.com", "test1234")).thenReturn(true);
		boolean userRegistered = mailService.registerUser("test@gmail.com", "test1234");
		assertTrue(userRegistered);
		when(mailService.registerUser("test@gmail.com", "tes4")).thenReturn(false);
		boolean userNotRegistered = mailService.registerUser("test@gmail.com", "tes4");
		assertFalse(userNotRegistered);
	}
	
	@Test
	public void testLoggedIn() {
		mailService = new MailService();
		newUsers.put("test@gmail.com", "test1234");
		mailService.users = newUsers;
		int result = mailService.login("test@gmail.com", "test1234");
        assertEquals(0, result);
	}
	
	@Test
	public void testNotLoggedIn() {
		mailService = new MailService();
		int x = mailService.login("test@gmail.com", "test1234");
        assertEquals(1, x);
        
        mailService = new MailService();
		newUsers.put("test@gmail.com", "");
		mailService.users = newUsers;
		int y = mailService.login("test@gmail.com", "test1234");
        assertEquals(2, y);
        
	}
	   

}
