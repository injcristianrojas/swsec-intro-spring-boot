package cl.injcristianrojas.data.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String rolename;

	public String getName() {
		return rolename;
	}

	public void setName(String name) {
		this.rolename = name;
	}
}
