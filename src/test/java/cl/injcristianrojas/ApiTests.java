package cl.injcristianrojas;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiTests {
	@Autowired
	private MockMvc mvc;

	@Test
	public void testUsers() throws Exception {
		this.mvc.perform(get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].username", is("jperez")));
				
	}
	
	@Test
	public void testPosts() throws Exception {
		this.mvc.perform(get("/api/posts"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].message", is("Bienvenidos a Fans de las Aves Chilenas. Soy el administrador.")));
				
	}
}
