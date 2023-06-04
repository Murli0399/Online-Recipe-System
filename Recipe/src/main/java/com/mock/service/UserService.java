package com.mock.service;

import com.mock.dto.LogInDto;
import com.mock.entities.CurrentUserSession;
import com.mock.entities.User;
import com.mock.exception.CurrentUserSessionException;
import com.mock.exception.UserException;

public interface UserService {
	public User addUser(User user) throws UserException;
	public CurrentUserSession logIn(LogInDto lid) throws CurrentUserSessionException;
	public boolean logOut(String uuid) throws CurrentUserSessionException;
}
