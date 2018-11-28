package cl.injcristianrojas;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import cl.injcristianrojas.webapp.HomeController;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ApplicationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHomePage() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void testLogin() throws Exception {
		RequestBuilder requestBuilder = post("/login")
	            .param("username", "jperez")
	            .param("password", "123")
	            .with(csrf());
	    mockMvc.perform(requestBuilder)
	    		.andExpect(redirectedUrl("/"))
	            .andExpect(status().isOk())
	            .andExpect(cookie().exists("JSESSIONID"));
	}

}
