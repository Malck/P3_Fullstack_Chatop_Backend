package com.chatop.api.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {

    private long id;

    private String name;

    private int surface;

    private int price;

    private String picture;

    private String description;

    private long ownerId;

    private Date createdAt;

    private Date updatedAt;
    
}
