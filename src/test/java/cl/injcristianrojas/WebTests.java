package cl.injcristianrojas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testWithoutLogin() throws Exception {
		this.mvc.perform(get("/")).andExpect(content().string(not(containsString("lhamilton"))));
	}

	@Test
	@WithMockUser(username="lhamilton", password="123")
	public void testLogin() throws Exception {
		this.mvc.perform(get("/")).andExpect(content().string(containsString("lhamilton")));
	}

	@Test
	@WithMockUser(username="lhamilton", password="123")
	public void testMessageListView() throws Exception {
		this.mvc.perform(get("/posts")).andExpect(content().string(containsString("Bienvenidos a Fans de las Aves Chilenas")));
	}

	@Test
	@WithMockUser(username="lhamilton", password="123")
	public void testSQLi() throws Exception {
		this.mvc.perform(get("/users/type/2 or '1'='1'")).andExpect(content().string(containsString("admin")));
	}

	@Test
	@WithMockUser(username="lhamilton", password="123")
	public void testXSS() throws Exception {
		this.mvc.perform(post("/greeting").param("person", "<script>alert('XSS')</script>")).andExpect(content().string(containsString("<script>")));
	}
}
