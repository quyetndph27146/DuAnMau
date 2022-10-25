/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.SanPham;
import Utilities.DBConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class SanPhamRepository implements ISanPhamRepository {

    final String INSERT_SQL = "INSERT INTO [dbo].[SanPham] ([Id],[Ma],[Ten])VALUES(NEWID(),?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[SanPham] SET [Ten] = ? WHERE [Id] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[SanPham] WHERE [Id] = ?";
    final String SELECT_BY_SQL_ID = "SELECT * FROM [dbo].[SanPham] WHERE [Id] = ?";
    final String SELECT_BY_SQL_NAME = "SELECT * FROM [dbo].[SanPham] WHERE [Ten] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[SanPham]";

    List<SanPham> listSanPham;

    @Override
    public List<SanPham> findAll() {
        return getSelectSql(SELECT_ALL_SQL);
    }

    private List<SanPham> getSelectSql(String sql, Object... args) {
        listSanPham = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                listSanPham.add(maping(rs));
            }
            return listSanPham;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public SanPham maping(ResultSet rs) {
        try {
            if (rs != null) {
                String id = String.valueOf(rs.getString("Id"));
                String ma = rs.getString("Ma");
                String ten = rs.getNString("Ten");
                return new SanPham(id, ma, ten);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean save(SanPham sanPham) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(INSERT_SQL, sanPham.getMa(), sanPham.getTen());
        return check > 0;
    }

    @Override
    public boolean delete(String id) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(DELETE_SQL, id);
        return check > 0;
    }

    @Override
    public boolean update(String ten, String id) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(UPDATE_SQL, ten, id);
        return check > 0;
    }

    private SanPham getsanPham(String sql, Object... args) {
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                return maping(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SanPham laySanPhamTheoId(String id) {
        return getsanPham(SELECT_BY_SQL_ID, id);
    }

    @Override
    public SanPham laySanPhamTheoTen(String ten) {
        return getsanPham(SELECT_BY_SQL_NAME, ten);
    }
}
