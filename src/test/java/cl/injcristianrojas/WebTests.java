package cl.injcristianrojas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebTests {

	@Autowired
	private MockMvc mvc;
	private RequestBuilder requestBuilder;

	@Test
	public void testWithoutLogin() throws Exception {
		this.mvc.perform(get("/")).andExpect(content().string(not(containsString("lhamilton"))));
	}

	@Test
	@WithMockUser(username="lhamilton", password="123")
	public void testLogin() throws Exception {
		this.mvc.perform(get("/")).andExpect(content().string(containsString("lhamilton")));
	}
}
