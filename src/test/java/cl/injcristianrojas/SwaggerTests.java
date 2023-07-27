package cl.injcristianrojas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class SwaggerTests {

  @Autowired
	private MockMvc mvc;

  @Test
	public void testHomePage() throws Exception {
		this.mvc.perform(get("/swagger-ui/index.html")).andExpect(status().is2xxSuccessful());
    // Extect to see "Aves API"
	}
}
