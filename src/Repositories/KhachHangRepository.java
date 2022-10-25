/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.KhachHang;
import Utilities.DBConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class KhachHangRepository implements IKhachHangRepository {

    final String INSERT_SQL = "INSERT INTO [dbo].[KhachHang] ([Id],[Ma],[Ten],[TenDem],[Ho],[NgaySinh],[Sdt],[DiaChi],[ThanhPho],[QuocGia],[MatKhau])VALUES(NEWID(),?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[KhachHang] SET [Ten] = ?,[TenDem] = ?,[Ho] = ?,[NgaySinh] = ?,[Sdt] = ?,[DiaChi] = ?,[ThanhPho] = ?,[QuocGia] = ?,[MatKhau] = ? WHERE [Id] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[KhachHang] WHERE [Id] = ?";
    final String SELECT_BY_SQL_ID = "SELECT * FROM [dbo].[KhachHang] WHERE [Id] = ?";
    final String SELECT_BY_SQL_NAME = "SELECT * FROM [dbo].[KhachHang] WHERE [Ten] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[KhachHang]";
    private List<KhachHang> listKhachHangs;

    public KhachHangRepository() {
    }

    @Override
    public List<KhachHang> findAll() {
        return getSelectAllKhachHang(SELECT_ALL_SQL);
    }

    private List<KhachHang> getSelectAllKhachHang(String sql, Object... args) {
        listKhachHangs = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                listKhachHangs.add(mapingKhachHang(rs));
            }
            return listKhachHangs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public KhachHang mapingKhachHang(ResultSet rs) {
        try {
            if (rs != null) {
                String id = String.valueOf(rs.getString("Id"));
                String ma = rs.getString("Ma");
                String ten = rs.getString("Ten");
                String tenDem = rs.getString("TenDem");
                String ho = rs.getString("Ho");
                String ngaySinh = rs.getString("NgaySinh");
                String sdt = rs.getString("Sdt");
                String matKhau = rs.getString("MatKhau");
                String diaChi = rs.getString("DiaChi");
                String thanhPho = rs.getString("ThanhPho");
                String quocGia = rs.getString("QuocGia");
                return new KhachHang(id, ma, ten, tenDem, ho, ngaySinh, sdt, diaChi, thanhPho, quocGia, matKhau);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean saveKhachHang(KhachHang kh) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(INSERT_SQL, kh.getMa(), kh.getTen(), kh.getTenDem(), kh.getHo(), kh.getNgaySinh(), kh.getSdt(),
                 kh.getDiaChi(), kh.getThanhPho(), kh.getQuocGia(), kh.getMatKhau());
        return check > 0;
    }

    @Override
    public boolean deleteKhachHang(String id) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(DELETE_SQL, id);
        return check > 0;
    }

    @Override
    public boolean updateKhachHang(KhachHang kh, String id) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(UPDATE_SQL, kh.getTen(), kh.getTenDem(), kh.getHo(), kh.getNgaySinh(), kh.getSdt(), kh.getDiaChi(),
                 kh.getThanhPho(), kh.getQuocGia(), kh.getMatKhau(), id);
        return check > 0;
    }

    @Override
    public KhachHang getKhachHangTheoId(String id) {
        return getKhachHang(SELECT_BY_SQL_ID, id);
    }

    @Override
    public KhachHang getKhachHangTheoName(String ten) {
        return getKhachHang(SELECT_BY_SQL_NAME, ten);
    }

    private KhachHang getKhachHang(String sql, Object... args) {
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                return mapingKhachHang(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
