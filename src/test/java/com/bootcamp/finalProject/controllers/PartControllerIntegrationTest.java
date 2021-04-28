package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.services.IPartService;
import com.bootcamp.finalProject.services.IWarehouseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = PartController.class)
public class PartControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IPartService partService;

    @MockBean
    private IWarehouseService warehouseService;

    @BeforeEach
    void initSetUp() {

    }

/*
    @Test
    public void whenValidInput_return200() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("queryType", "C");
        params.put("order", "1");

        mockMvc.perform(get("list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Given
        HttpUriRequest request = new HttpGet( "https://api.github.com/users/eugenp" );

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        // Then
        GitHubUser resource = RetrieveUtil.retrieveResourceFromResponse(
                response, GitHubUser.class);
        assertThat( "eugenp", Matchers.is( resource.getLogin() ) );

    }
*/
}

