package tcore.dto;

public class User {
    private String email;

    private boolean active;

    public User(String email, boolean active) {
        this.email = email;
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }
}
