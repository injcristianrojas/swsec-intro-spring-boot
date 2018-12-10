package cl.injcristianrojas;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebTests {
	@Autowired
	private MockMvc mvc;

	@Test
	public void testHomePage() throws Exception {
		this.mvc.perform(get("/login")).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void testLogin() throws Exception {
		RequestBuilder requestBuilder = post("/login")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            .param("username", "jperez")
	            .param("password", "123")
	            .with(csrf());
		this.mvc.perform(requestBuilder)
	    		.andExpect(redirectedUrl("/"))
	            .andExpect(status().isFound());
	}
}
