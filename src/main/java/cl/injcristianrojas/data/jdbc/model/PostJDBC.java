package cl.injcristianrojas.data.jdbc.model;

public class PostJDBC {

    private final Long id;
    private final String message;

    public PostJDBC(long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
