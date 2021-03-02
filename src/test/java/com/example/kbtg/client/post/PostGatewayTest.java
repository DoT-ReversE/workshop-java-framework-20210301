package com.example.kbtg.client.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostGatewayTest {

    @Autowired
    private PostGateway postGateway;

    @MockBean
    private RestTemplate restTemplate;

    @Value("${post.api.url}")
    private String postApiUrl;

    @Test
    public void success_with_id_1() {
        PostResponse expected = new PostResponse();
        expected.setUserId(1);
        expected.setId(1);
        expected.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        expected.setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

        Optional<PostResponse> actual = postGateway.getPostById(1);
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    public void should_return_empty_when_exception_is_occured() {
        String url = String.format("%s/posts/%d", postApiUrl, 1);
        when(restTemplate.getForObject(url, PostResponse.class)).thenThrow(new RestClientException(""));
        Optional<PostResponse> actual = postGateway.getPostById(1);
        assertTrue(!actual.isPresent());
    }
}