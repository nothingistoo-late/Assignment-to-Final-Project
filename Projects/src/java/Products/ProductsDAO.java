package Products;

import Products.ProductsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utils.DBUtils;

public class ProductsDAO {
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public List<ProductsDTO> getAllByIdAndName(String name) {
        List<ProductsDTO> productsList = new ArrayList<ProductsDTO>();
        ProductsDTO products;
        String sql = "SELECT * FROM tblProducts WHERE name LIKE ?;";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, '%' + name + '%');

            rs = ps.executeQuery();
            while (rs.next()) {
                products = new ProductsDTO(
                        rs.getString("idProducts"), 
                        rs.getString("name"), 
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getString("image"),
                        rs.getFloat("price"), 
                        rs.getInt("status")
                );
                if(products.getStatus() == 1){
                    productsList.add(products);
                }
            }
        } catch (Exception ex) {
            // Handle exception
        }

        return productsList;
    }

    public List<ProductsDTO> getAllByPriceRange(float minPrice, float maxPrice) throws SQLException {
        List<ProductsDTO> productsList = new ArrayList<ProductsDTO>();
        ProductsDTO products;
        String sql = "SELECT * FROM tblProducts WHERE price BETWEEN ? AND ?;";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setFloat(1, minPrice);
            ps.setFloat(2, maxPrice);

            rs = ps.executeQuery();
            while (rs.next()) {
                products = new ProductsDTO(
                        rs.getString("idProducts"), 
                        rs.getString("name"), 
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getString("image"),
                        rs.getFloat("price"), 
                        rs.getInt("status")
                );
                if(products.getStatus() == 1){
                    productsList.add(products);
                }
            }
        } catch (Exception ex) {
            // Handle exception
        } finally {
            if(ps != null) ps.close();
            if(connection != null) connection.close();
        }

        return productsList;
    }

    public List<ProductsDTO> getAllByType(String type) {
        List<ProductsDTO> productsList = new ArrayList<ProductsDTO>();
        ProductsDTO products;
        String sql = "SELECT * FROM tblProducts WHERE type LIKE ?;";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, '%' + type + '%');

            rs = ps.executeQuery();
            while (rs.next()) {
                products = new ProductsDTO(
                        rs.getString("idProducts"), 
                        rs.getString("name"), 
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getString("image"),
                        rs.getFloat("price"), 
                        rs.getInt("status")
                );
                if(products.getStatus() == 1){
                    productsList.add(products);
                }
            }
        } catch (Exception ex) {
            // Handle exception
        }

        return productsList;
    }

    public List<ProductsDTO> getAllProducts() throws SQLException {
        List<ProductsDTO> productsList = new ArrayList<ProductsDTO>();
        ProductsDTO products;
        String sql = "SELECT * FROM tblProducts;";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                products = new ProductsDTO(
                        rs.getString("idProducts"), 
                        rs.getString("name"), 
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getString("image"),
                        rs.getFloat("price"), 
                        rs.getInt("status")
                );
                if(products.getStatus() == 1){
                    productsList.add(products);
                }
            }
        } catch (Exception ex) {
            // Handle exception
        } finally {
            if(ps != null) ps.close();
            if(connection != null) connection.close();
        }

        return productsList;
    }

    public boolean createProducts(ProductsDTO product) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tblProducts (idProducts, name, description, type, image, price, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean response = false;

        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                ps = connection.prepareStatement(sql);
                ps.setString(1, product.getIdProducts().trim());
                ps.setString(2, product.getName());
                ps.setString(3, product.getDescription());
                ps.setString(4, product.getType());
                ps.setString(5, product.getImage());
                ps.setFloat(6, product.getPrice());
                ps.setInt(7, product.getStatus());
                response = ps.executeUpdate() > 0;
            }
        } finally {
            if(ps != null) ps.close();
            if(connection != null) connection.close();
        }
        return response;
    }

    public boolean deleteProducts(String idProducts) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM tblProducts WHERE idProducts = ?";
        boolean response = false;

        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                ps = connection.prepareStatement(sql);
                ps.setString(1, idProducts.trim());
                response = ps.executeUpdate() > 0;
            }
        } finally {
            if(ps != null) ps.close();
            if(connection != null) connection.close();
        }
        return response;
    }

    public boolean updateProducts(ProductsDTO product) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE tblProducts SET name = ?, description = ?, type = ?, image = ?, price = ?, status = ? WHERE idProducts = ?";
        boolean response = false;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DBUtils.getConnection();
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
                ps.setString(3, product.getType());
                ps.setString(4, product.getImage());
                ps.setFloat(5, product.getPrice());
                ps.setInt(6, product.getStatus());
                ps.setString(7, product.getIdProducts().trim());
                response = ps.executeUpdate() > 0;
            }
        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
        return response;
    }
}
