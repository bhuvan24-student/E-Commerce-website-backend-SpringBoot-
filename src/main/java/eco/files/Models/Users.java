package eco.files.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Users {
    @Id
    private int id;
    private String username;
    private String password;

    public String userName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }


    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

