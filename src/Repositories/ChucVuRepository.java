/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.ChucVu;
import Utilities.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class ChucVuRepository implements IChucVuRepository {

    final String INSERT_SQL = "INSERT INTO [dbo].[ChucVu] ([Id],[Ma],[Ten])VALUES(NEWID(),?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[ChucVu] SET [Ten] = ? WHERE [Id] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[ChucVu] WHERE [Id] = ?";
    final String SELECT_BY_SQL_ID = "SELECT * FROM [dbo].[ChucVu] WHERE [Id] = ?";
    final String SELECT_BY_SQL_Name = "SELECT * FROM [dbo].[ChucVu] WHERE [Ten] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[ChucVu]";

    List<ChucVu> listChucVu;

    public ChucVuRepository() {
    }

    @Override
    public List<ChucVu> findAll() {
        return getSelectSql(SELECT_ALL_SQL);
    }

    private List<ChucVu> getSelectSql(String sql, Object... args) {
        listChucVu = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                listChucVu.add(maping(rs));
            }
            return listChucVu;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public ChucVu maping(ResultSet rs) {
        try {
            if (rs != null) {
                String id = String.valueOf(rs.getString("Id"));
                String ma = rs.getString("Ma");
                String ten = rs.getNString("Ten");
                return new ChucVu(id, ma, ten);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean save(ChucVu chucVu) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(INSERT_SQL, chucVu.getMa(), chucVu.getTen());
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
    public ChucVu layChucVuTheoId(String id) {
        return getChucVu(SELECT_BY_SQL_ID, id);
    }

    @Override
    public ChucVu layChucVuTheoTen(String ten) {
        return getChucVu(SELECT_BY_SQL_Name, ten);
    }

    private ChucVu getChucVu(String sql, Object... args) {
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
