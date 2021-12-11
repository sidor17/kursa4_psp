package sample.classes;

public class User{

    public static User currentUser;
    public int id;
    public String login;
    public String password;
    public boolean role;

    public User(int id, String login, String password, String role)
    {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = !role.equals("false");
    }

    public User(int id, String role)
    {
        this.id = id;
        this.role = !role.equals("user");
    }

    public int getId()
    {
        return this.id;
    }

    public String getLogin()
    {
        return this.login;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean getRole() { return this.role;   }

}
