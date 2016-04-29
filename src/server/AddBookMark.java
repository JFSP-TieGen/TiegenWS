package server;

import java.util.ArrayList;

import entity.BookMark;
import entity.Service;
import entity.User;

public interface AddBookMark {
	public void addBookMark(BookMark bookmark);
	public ArrayList<Service> loadBookMark(User user);
	public void deleteBookMark(BookMark bookmark);
}
