package com.example.kbtg.client.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostResponse {
    private int id;
    private int userId;
    private String title;
    private String body;
}
