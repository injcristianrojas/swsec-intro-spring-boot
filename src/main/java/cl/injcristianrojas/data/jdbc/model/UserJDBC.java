package cl.injcristianrojas.data.jdbc.model;

public class UserJDBC {

    private final Long id;
    private final String username;
    private final String password;
    private final boolean enabled;

    public UserJDBC(Long id, String username, String password, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public UserJDBC() {
        super();
        this.id = null;
        this.username = null;
        this.password = null;
        this.enabled = false;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
