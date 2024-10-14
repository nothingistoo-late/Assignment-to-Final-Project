package Products;

public class ProductsDTO {
    private String idProducts;
    private String name;
    private String description;
    private String type;
    private String image;
    private float price;
    private Integer status;

    public ProductsDTO() {
    }

    public ProductsDTO(String idProducts, String name, String description, String type, String image, float price, Integer status) {
        this.idProducts = idProducts;
        this.name = name;
        this.description = description;
        this.type = type;
        this.image = image;
        this.price = price;
        this.status = status;
    }

    public String getIdProducts() {
        return idProducts;
    }

    public void setIdProducts(String idProducts) {
        this.idProducts = idProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
