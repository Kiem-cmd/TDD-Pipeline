package org.example;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String address;
    private String phone;
//    private UserRole role;

    public User(){

    }
    public User(int id, String name) {
        this.id = id;
        this.username = name;
    }


    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

//    public UserRole getRole() { return role; }
//    public void setRole(UserRole role) { this.role = role; }

}
