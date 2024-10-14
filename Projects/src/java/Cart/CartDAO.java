package Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utils.DBUtils;

public class CartDAO {
    private Connection connection;
    private PreparedStatement ps;
    ResultSet rs;
    
    public boolean addToCart(String cartId, String idProducts, float price, float totalPrice, int quantity, String image, String userID, String invId) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tblCart (cartId, idProducts, price, totalPrice, quantity, image, userId, invId) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        boolean response = false;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, cartId);
            ps.setString(2, idProducts.trim());
            ps.setFloat(3, price);
            ps.setFloat(4, totalPrice);
            ps.setInt(5, quantity);
            ps.setString(6, image);
            ps.setString(7, userID);
            ps.setString(8, invId);
            response = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            // Handle exception
        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
        return response;
    }

    public boolean removeFromCart(String cartId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM tblCart WHERE cartId = ?;";
        boolean response = false;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, cartId);
            response = ps.executeUpdate() > 0;
        } catch (Exception ex) {
            // Handle exception
        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
        return response;
    }
    
    public List<CartDTO> getCart(String userID) throws SQLException, ClassNotFoundException {
        List<CartDTO> cartList = new ArrayList<>();
        String sql = "SELECT * FROM tblCart WHERE userId = ?;";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CartDTO cart = new CartDTO(
                    rs.getString("cartId"), 
                    rs.getString("idProducts"), 
                    rs.getString("invId"), 
                    rs.getString("userID"), 
                    rs.getString("image"), 
                    rs.getFloat("price"), 
                    rs.getFloat("totalPrice"), 
                    rs.getInt("quantity")
                );
                cartList.add(cart);
            }
        } catch (Exception ex) {
            // Handle exception
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
        return cartList;
    }

    public boolean updateCart(CartDTO cart) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tblCart SET totalPrice = ?, quantity = ? WHERE cartId = ?;";
        boolean response = false;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setFloat(1, cart.getPrice() * cart.getQuantity());
            ps.setInt(2, cart.getQuantity());
            ps.setString(3, cart.getCartId());
            response = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            // Handle exception
        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
        return response;
    }

    public CartDTO getCartByProductsId(String idProducts, String userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM tblCart WHERE idProducts = ? and userId =?;";
        CartDTO cart = null;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, idProducts);
            ps.setString(2, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                cart = new CartDTO(
                    rs.getString("cartId"), 
                    idProducts, 
                    rs.getString("invId"), 
                    userId, 
                    rs.getString("image"), 
                    rs.getFloat("price"), 
                    rs.getFloat("totalPrice"), 
                    rs.getInt("quantity")
                );
            }
        } catch (SQLException ex) {
            // Handle exception
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
        return cart;
    }

    public CartDTO getCartById(String cartId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM tblCart WHERE cartId = ?;";
        CartDTO cart = null;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, cartId);
            rs = ps.executeQuery();
            if (rs.next()) {
                cart = new CartDTO(
                    cartId, 
                    rs.getString("idProducts"), 
                    rs.getString("invId"), 
                    rs.getString("userID"), 
                    rs.getString("image"), 
                    rs.getFloat("price"), 
                    rs.getFloat("totalPrice"), 
                    rs.getInt("quantity")
                );
            }
        } catch (SQLException ex) {
            // Handle exception
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
        return cart;
    }

    public void updateInvId(CartDTO cart) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tblCart SET invId = ? WHERE cartId = ?";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, cart.getInvId());
            ps.setString(2, cart.getCartId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            // Handle exception
        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
    }
}
