package com.urwoo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable{

    private static final long serialVersionUID = -137980054743783037L;
    private Long id;
    private String name;
}
