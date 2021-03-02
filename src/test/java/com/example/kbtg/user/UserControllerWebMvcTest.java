package com.example.kbtg.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerWebMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void failure_user_not_found() throws Exception {
        // Arrange
        when(userService.getInfo(15))
                .thenThrow(new UserNotFoundException("User not found id=15"));

        // Act
        MvcResult mvcResult = mvc.perform(get("/user/15")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        // String => JSON Message
        String jsonResult = mvcResult.getResponse().getContentAsString();
        // Convert JSON to Object
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse response = mapper.readValue(jsonResult, ErrorResponse.class);

        ErrorResponse expected = new ErrorResponse(1234, "User not found id=15");
        assertEquals(expected, response);
    }

    @Test
    public void success_get_user_id_1() throws Exception {
        when(userService.getInfo(1))
                .thenReturn(new UserResponse(1, "somkiat", 30));

        MvcResult mvcResult = mvc.perform(get("/user/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String jsonResult = mvcResult.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        UserResponse response = mapper.readValue(jsonResult, UserResponse.class);

        UserResponse expected = new UserResponse(1, "somkiat", 30);
        assertEquals(expected, response);
    }

}