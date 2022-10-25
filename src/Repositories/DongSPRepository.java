/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.DongSP;
import Utilities.DBConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DongSPRepository implements IDongSPRepository {

    final String INSERT_SQL = "INSERT INTO [dbo].[DongSP] ([Id],[Ma],[Ten])VALUES(NEWID(),?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[DongSP] SET [Ten] = ? WHERE [Id] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[DongSP] WHERE [Id] = ?";
    final String SELECT_BY_SQL_ID = "SELECT * FROM [dbo].[DongSP] WHERE [Id] = ?";
    final String SELECT_BY_SQL_NAME = "SELECT * FROM [dbo].[DongSP] WHERE [Ten] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[DongSP]";

    List<DongSP> listDongSP;

    public DongSPRepository() {
    }

    @Override
    public List<DongSP> findAll() {
        return getSelectSql(SELECT_ALL_SQL);
    }

    private List<DongSP> getSelectSql(String sql, Object... args) {
        listDongSP = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                listDongSP.add(maping(rs));
            }
            return listDongSP;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public DongSP maping(ResultSet rs) {
        try {
            if (rs != null) {
                String id = String.valueOf(rs.getString("Id"));
                String ma = rs.getString("Ma");
                String ten = rs.getNString("Ten");
                return new DongSP(id, ma, ten);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean save(DongSP dongSP) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(INSERT_SQL, dongSP.getMa(), dongSP.getTen());
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
    public DongSP layDongSPTheoId(String id) {
        return getDongSP(SELECT_BY_SQL_ID, id);
    }

    @Override
    public DongSP layDongSPTheoTen(String ten) {
        return getDongSP(SELECT_BY_SQL_NAME, ten);
    }

    private DongSP getDongSP(String sql, Object... args) {
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
