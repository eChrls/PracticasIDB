package com.web.api.model;

import lombok.Data;

@Data // para que se generen los getters, setters, toString ...
public class CommentCallDto {
    private Long idUser;
    private String email;
    private String phone;
    private String subject;
    private String comment;
}
