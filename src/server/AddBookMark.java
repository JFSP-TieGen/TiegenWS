package server;

import java.util.ArrayList;

import entity.BookMark;
import entity.Service;
import entity.User;

public interface AddBookMark {
	public void addBookMark(BookMark bookmark) throws Exception;
	public ArrayList<Service> loadBookMark(User user) throws Exception;
	public void deleteBookMark(BookMark bookmark) throws Exception;
}
