package entity;

public class BookMark {
int serviceId;
int userId;
public BookMark(int sid, int uid){
	this.serviceId = sid;
	this.userId = uid;
}
public int getServiceId() {
	return serviceId;
}
public void setServiceId(int serviceId) {
	this.serviceId = serviceId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}

}
