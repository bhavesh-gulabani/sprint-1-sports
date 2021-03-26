package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bean.User;
import com.cg.dao.IUserRepository;
import com.cg.exception.ResourceNotFoundException;
import com.cg.exception.UserAlreadyExistsException;
import com.cg.exception.WrongCredentialsException;
import com.cg.service.UserServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserTest 
{
	List<User> userList;
	User u1 = new User("user1", "zxcv", "customer");
	User u2 = new User("user2", "1234", "customer");
	User u3 = new User("user3", "5678", "admin");
	User u4 = new User("user3", "asdf", "admin");
	
	@Mock
	IUserRepository userRepoMock;
	
	@InjectMocks
	UserServiceImpl userService;
	
	
	@BeforeEach
	void setUp() throws Exception
	{
		userList = new ArrayList<>();
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);	
	}

	//SignIn NULL
	@Test
	void signInNull() throws ResourceNotFoundException, WrongCredentialsException
	{
		when(userRepoMock.findAll()).thenReturn(userList);
		User user = null;
		assertEquals(null, userService.signIn(user));
	}
	
	@Test
	void signInPos() throws ResourceNotFoundException, WrongCredentialsException
	{
		when(userRepoMock.findAll()).thenReturn(userList);
		assertEquals(u2, userService.signIn(u2));
	}
	
	@Test
	void signInNeg() throws ResourceNotFoundException, WrongCredentialsException
	{
		when(userRepoMock.findAll()).thenReturn(userList);
		assertThrows(WrongCredentialsException.class, () -> userService.signIn(u4)); 
	}
	
	@Test
	void signOutPos()
	{
		when(userRepoMock.existsById(u3.getId())).thenReturn(true);
		assertEquals(u3, userService.signOut(u3));
	}
	
	@Test
	void signOutNeg() {
		User user = new User("user567", "5678", "admin");
		when(userRepoMock.existsById(u3.getId())).thenReturn(true);
		assertEquals(user, userService.signOut(user));
	}	

	@Test
	public void addUserTest() throws UserAlreadyExistsException, ResourceNotFoundException {
		User u2 = new User("user123", "1234", "customer");
		when(userRepoMock.save(u2)).thenReturn(u2);
		assertEquals(u2, userService.addUser(u2));
	}
	
	@Test
	public void changePasswordTest() throws ResourceNotFoundException
	{
		when(userRepoMock.findById(u3.getId())).thenReturn(Optional.of(u3));
		when(userRepoMock.save(u4)).thenReturn(u3);
		assertEquals(u3, userService.changePassword(u2.getId(), u4));
	}

	@Test
	public void getAllUsersTest() throws ResourceNotFoundException {
		when(userRepoMock.findAll()).thenReturn(userList);
		assertEquals(3, userService.getAllUsers().size());
	}
	
	@Test
	public void getUserTest() throws ResourceNotFoundException {
		when(userRepoMock.findById(u1.getId())).thenReturn(Optional.of(u1));
		assertEquals(u1, userService.getUserById(u1.getId()));
	}
	
	@Test
	public void removeUserTest() throws ResourceNotFoundException {
		when(userRepoMock.findById(u1.getId())).thenReturn(Optional.of(u1));
		assertEquals(u1, userService.removeUser(u1.getId()));
	}	
}
