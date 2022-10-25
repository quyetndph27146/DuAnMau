/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.MauSac;
import Utilities.DBConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class MauSacRepository implements IMauSacRepository {

    final String INSERT_SQL = "INSERT INTO [dbo].[MauSac] ([Id],[Ma],[Ten])VALUES(NEWID(),?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[MauSac] SET [Ten] = ? WHERE [Id] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[MauSac] WHERE [Id] = ?";
    final String SELECT_BY_SQL_ID = "SELECT * FROM [dbo].[MauSac] WHERE [Id] = ?";
    final String SELECT_BY_SQL_Name = "SELECT * FROM [dbo].[MauSac] WHERE [Ten] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[MauSac]";

    List<MauSac> listMauSac;

    @Override
    public List<MauSac> findAll() {
        return getSelectSql(SELECT_ALL_SQL);
    }

    private List<MauSac> getSelectSql(String sql, Object... args) {
        listMauSac = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                listMauSac.add(maping(rs));
            }
            return listMauSac;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public MauSac maping(ResultSet rs) {
        try {
            if (rs != null) {
                String id = String.valueOf(rs.getString("Id"));
                String ma = rs.getString("Ma");
                String ten = rs.getNString("Ten");
                return new MauSac(id, ma, ten);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean save(MauSac mauSac) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(INSERT_SQL, mauSac.getMa(), mauSac.getTen());
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
    public MauSac layMauSacTheoId(String id) {
        return getMauSac(SELECT_BY_SQL_ID, id);
    }

    @Override
    public MauSac layMauSacTheoTen(String ten) {
        return getMauSac(SELECT_BY_SQL_Name, ten);
    }

    private MauSac getMauSac(String sql, Object... args) {
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
