package com.cmu.tiegen.server;

import java.util.ArrayList;

import com.cmu.tiegen.entity.BookMark;
import com.cmu.tiegen.entity.Service;
import com.cmu.tiegen.entity.User;

public interface AddBookMark {
	public void addBookMark(BookMark bookmark) throws Exception;
	public ArrayList<Service> loadBookMark(User user) throws Exception;
	public void deleteBookMark(BookMark bookmark) throws Exception;
}
