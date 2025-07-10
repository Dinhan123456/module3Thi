package org.example.thimodule3.dao;

import org.example.thimodule3.model.RentalRoom;
import org.example.thimodule3.dao.DBConnection;

import java.sql.*;
import java.util.*;

public class RentalRoomDAO {
    private static final String FIND_ALL = "SELECT r.*, p.name AS payment_name FROM rental_room r JOIN payment_method p ON r.payment_method_id = p.id";
    private static final String SAVE_SQL = "INSERT INTO rental_room (tenant_name, phone_number, start_date, payment_method_id, note) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_SQL = "DELETE FROM rental_room WHERE id = ?";

    public List<RentalRoom> findAll() throws SQLException {
        List<RentalRoom> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(FIND_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RentalRoom r = new RentalRoom();
                r.setId(rs.getInt("id"));
                r.setTenantName(rs.getString("tenant_name"));
                r.setPhoneNumber(rs.getString("phone_number"));
                r.setStartDate(rs.getString("start_date"));
                r.setPaymentMethodId(rs.getInt("payment_method_id"));
                r.setPaymentMethodName(rs.getString("payment_name"));
                r.setNote(rs.getString("note"));
                list.add(r);
            }
        }
        return list;
    }

    public void save(RentalRoom r) throws SQLException {
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(SAVE_SQL)) {
            ps.setString(1, r.getTenantName());
            ps.setString(2, r.getPhoneNumber());
            ps.setString(3, r.getStartDate());
            ps.setInt(4, r.getPaymentMethodId());
            ps.setString(5, r.getNote());
            ps.executeUpdate();
        }
    }

    public List<RentalRoom> getRoomsByIds(List<Integer> ids) throws SQLException {
        if (ids.isEmpty()) return Collections.emptyList();
        String placeholders = String.join(",", Collections.nCopies(ids.size(), "?"));
        String sql = "SELECT r.*, p.name AS payment_name FROM rental_room r JOIN payment_method p ON r.payment_method_id = p.id WHERE r.id IN (" + placeholders + ")";
        List<RentalRoom> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            for (int i = 0; i < ids.size(); i++) {
                ps.setInt(i + 1, ids.get(i));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RentalRoom r = new RentalRoom();
                r.setId(rs.getInt("id"));
                r.setTenantName(rs.getString("tenant_name"));
                r.setPhoneNumber(rs.getString("phone_number"));
                r.setStartDate(rs.getString("start_date"));
                r.setPaymentMethodId(rs.getInt("payment_method_id"));
                r.setPaymentMethodName(rs.getString("payment_name"));
                r.setNote(rs.getString("note"));
                list.add(r);
            }
        }
        return list;
    }

    public List<RentalRoom> search(String keyword) throws SQLException {
        List<RentalRoom> list = new ArrayList<>();
        String sql = "SELECT r.*, p.name AS payment_name FROM rental_room r JOIN payment_method p ON r.payment_method_id = p.id WHERE tenant_name LIKE ?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RentalRoom r = new RentalRoom();
                r.setId(rs.getInt("id"));
                r.setTenantName(rs.getString("tenant_name"));
                r.setPhoneNumber(rs.getString("phone_number"));
                r.setStartDate(rs.getString("start_date"));
                r.setPaymentMethodId(rs.getInt("payment_method_id"));
                r.setPaymentMethodName(rs.getString("payment_name"));
                r.setNote(rs.getString("note"));
                list.add(r);
            }
        }
        return list;
    }

    public void deleteByIds(List<Integer> ids) throws SQLException {
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(DELETE_SQL)) {
            for (int id : ids) {
                ps.setInt(1, id);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
