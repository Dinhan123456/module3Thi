package org.example.thimodule3.dao;

import org.example.thimodule3.dao.DBConnection;

import java.sql.*;
import java.util.*;

public class PaymentMethodDAO {
    public List<Map.Entry<Integer, String>> findAll() throws SQLException {
        List<Map.Entry<Integer, String>> list = new ArrayList<>();
        String sql = "SELECT * FROM payment_method";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new AbstractMap.SimpleEntry<>(rs.getInt("id"), rs.getString("name")));
            }
        }
        return list;
    }
}