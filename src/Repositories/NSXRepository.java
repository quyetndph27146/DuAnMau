/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.NSX;
import Utilities.DBConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NSXRepository implements INSXRepository {

    final String INSERT_SQL = "INSERT INTO [dbo].[NSX] ([Id],[Ma],[Ten])VALUES(NEWID(),?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[NSX] SET [Ten] = ? WHERE [Id] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[NSX] WHERE [Id] = ?";
    final String SELECT_BY_SQL_ID = "SELECT * FROM [dbo].[NSX] WHERE [Id] = ?";
    final String SELECT_BY_SQL_NAME = "SELECT * FROM [dbo].[NSX] WHERE [Ten] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[NSX]";

    List<NSX> listNSX;

    public NSXRepository() {
        listNSX = new ArrayList<>();
    }

    @Override
    public List<NSX> findAll() {
        return getSelectSql(SELECT_ALL_SQL);
    }

    private List<NSX> getSelectSql(String sql, Object... args) {
        listNSX = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                listNSX.add(maping(rs));
            }
            return listNSX;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public NSX maping(ResultSet rs) {
        try {
            if (rs != null) {
                String id = String.valueOf(rs.getString("Id"));
                String ma = rs.getString("Ma");
                String ten = rs.getNString("Ten");
                return new NSX(id, ma, ten);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean save(NSX nsx) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(INSERT_SQL, nsx.getMa(), nsx.getTen());
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

    @Override
    public NSX layNSXTheoId(String id) {
        return getsanPham(SELECT_BY_SQL_ID, id);
    }

    @Override
    public NSX layNSXTheoTen(String ten) {
        return getsanPham(SELECT_BY_SQL_NAME, ten);
    }

    private NSX getsanPham(String sql, Object... args) {
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
}
