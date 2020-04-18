package sprint1_files;

public interface UserInterface {
	public response register(String email, String user, String pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException;
	public response login(String username, String pass);

}
