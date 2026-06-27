package eco.Controllers.Services;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productdb")

//products should be in this format only
public class Products {
    @Id
    private int id;
    private String name;
    private double price;
    private String discription;
    private String category;
    private Date date;
    private String image_name;
    private String image_type;
    @Lob
    //byte[] stores the image data
    private byte[] image;
    private boolean available;
    private int quantity;
}
