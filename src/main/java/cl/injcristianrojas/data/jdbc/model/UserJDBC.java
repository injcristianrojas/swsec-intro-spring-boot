package cl.injcristianrojas.data.jdbc.model;

public class UserJDBC {

    private final Long id;
    private final String username;
    private final boolean enabled;

    public UserJDBC(Long id, String username, boolean enabled) {
        this.id = id;
        this.username = username;
        this.enabled = enabled;
    }

    public UserJDBC() {
        super();
        this.id = null;
        this.username = null;
        this.enabled = false;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
