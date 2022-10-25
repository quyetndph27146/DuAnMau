/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.CuaHang;
import Utilities.DBConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CuaHangRepository implements ICuaHangRepository {

    final String INSERT_SQL = "INSERT INTO [dbo].[CuaHang] ([Id],[Ma],[Ten],[DiaChi],[ThanhPho],[QuocGia])VALUES(NEWID(),?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[CuaHang] SET [Ten] = ?,[DiaChi] = ?,[ThanhPho] = ?,[QuocGia] = ? WHERE [Id] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[CuaHang] WHERE [Id] = ?";
    final String SELECT_BY_SQL_ID = "SELECT * FROM [dbo].[CuaHang] WHERE [Id] = ?";
    final String SELECT_BY_SQL_Name = "SELECT * FROM [dbo].[CuaHang] WHERE [Ten] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[CuaHang]";
    private List<CuaHang> listCuaHangs;

    @Override
    public List<CuaHang> findAll() {
        return getSelectAllCuaHang(SELECT_ALL_SQL);
    }

    private List<CuaHang> getSelectAllCuaHang(String sql, Object... args) {
        listCuaHangs = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                listCuaHangs.add(mapingCuaHang(rs));
            }
            return listCuaHangs;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public CuaHang mapingCuaHang(ResultSet rs) {
        try {
            if (rs != null) {
                String id = String.valueOf(rs.getString("Id"));
                String ma = rs.getString("Ma");
                String ten = rs.getString("Ten");
                String diaChi = rs.getString("DiaChi");
                String thanhPho = rs.getString("ThanhPho");
                String quocGia = rs.getString("QuocGia");
                return new CuaHang(id, ma, ten, diaChi, thanhPho, quocGia);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean saveCuaHang(CuaHang cuaHang) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(INSERT_SQL, cuaHang.getMa(), cuaHang.getTen(), cuaHang.getDiaChi(), cuaHang.getThanhPho(), cuaHang.getQuocGia());
        return check > 0;
    }

    @Override
    public boolean deleteCuaHang(String id) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(DELETE_SQL, id);
        return check > 0;
    }

    @Override
    public boolean updateCuaHang(CuaHang cuaHang, String id) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(UPDATE_SQL, cuaHang.getTen(), cuaHang.getDiaChi(), cuaHang.getThanhPho(), cuaHang.getQuocGia(), id);
        return check > 0;
    }

    @Override
    public CuaHang layCuaHangTheoID(String id) {
        return getCuaHang(SELECT_BY_SQL_ID, id);
    }

    @Override
    public CuaHang layCuaHangTheoTen(String ten) {
        return getCuaHang(SELECT_BY_SQL_Name, ten);
    }

    private CuaHang getCuaHang(String sql, Object... args) {
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                return mapingCuaHang(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
