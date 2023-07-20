package cl.injcristianrojas;

import static cl.injcristianrojas.security.jwt.Constants.TOKEN_PREFIX;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiTests {
	
	@Autowired
	private MockMvc mvc;

	private String jwtToken;
	
	@Before
	public void setUp() throws Exception {
		MvcResult result = this.mvc.perform(post("/api/v1/login").param("Content-Type", "application/json").content("{ \"username\": \"jperez\", \"password\": \"123\"}")).andReturn();
		jwtToken = result.getResponse().getHeader("Authorization").replace(TOKEN_PREFIX, "");
	}
	
	@Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        this.mvc.perform(get("/api/v2/users")).andExpect(status().isForbidden());
    }

	@Test
	public void testUsers() throws Exception {
		this.mvc.perform(get("/api/v2/users").header("Authorization", TOKEN_PREFIX + jwtToken))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].username", is("jperez")));
				
	}
	
	@Test
	public void testPosts() throws Exception {
		this.mvc.perform(get("/api/v2/posts").header("Authorization", TOKEN_PREFIX + jwtToken))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].message", is("Holi")));
				
	}
}
