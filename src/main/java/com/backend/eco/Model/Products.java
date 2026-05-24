package com.backend.eco.Model;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    @Id
   private Integer id;
    private String name;
     private String description;
      private String brand;
       private double price;
        private String category;
         private Date date;
         @Column(name="image_name")
         private String image_name;
         @Column(name="image_type")
         private String image_type;
         @Lob
         @JsonIgnore
         @Column(name = "image_date")
         private byte[] image_date;
         private boolean available;
         private int quantity;

}
