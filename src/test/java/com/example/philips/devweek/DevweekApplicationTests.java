package com.example.philips.devweek;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DevweekApplicationTests {

	private final String URL = "/api/regioes";

	@Autowired
	MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;  // mapeamento do objeto para json
	@Mock
	private Regiao controller;

	@Test
	public void getCotrollerTest() throws Exception {
		mockMvc.perform(get(URL))
					.andExpect(status().isOk()
					);
	}

	@Test
	public void getByIdControllerTest() throws Exception {
		mockMvc.perform(get(URL+"/1"))
				.andExpect(status().isOk()
				);
	}
	@Test
	public void addNewRegiaoTest() throws Exception {
		Long id = Long.valueOf(6);
		Regiao newRegiao = new Regiao(id,"Noroeste", 57);

		mockMvc.perform(post(URL+"/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(newRegiao)))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteByIdRedgiaoTest() throws Exception {
		//delete("/api/foo/{id}", "11")
		mockMvc.perform(delete(URL+"/delete/{id}","6")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
