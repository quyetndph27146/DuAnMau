/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.HoaDon;
import DomainModels.KhachHang;
import DomainModels.NhanVien;
import Utilities.DBConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class HoaDonRepository implements IHoaDonRepository {

    final String INSERT_SQL = "INSERT INTO [dbo].[HoaDon] ([Id],[IdKH],[IdNV],[Ma],[NgayTao],[NgayThanhToan],[NgayShip],[NgayNhan],[TinhTrang],[TenNguoiNhan],[DiaChi],[Sdt])VALUES(NEWID(),?,?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[HoaDon] SET [IdKH] = ?,[IdNV] = ?,[NgayTao] = ?,[NgayThanhToan] = ?,[NgayShip] = ?,[NgayNhan] = ?,[TinhTrang] = ?,[TenNguoiNhan] = ?,[DiaChi] = ?,[Sdt] = ? WHERE [Id] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[HoaDon] WHERE [Id] = ?";
    final String SELECT_BY_SQL_ID = "SELECT * FROM [dbo].[HoaDon] WHERE [Id] = ?";
    final String SELECT_BY_SQL_Ma = "SELECT * FROM [dbo].[HoaDon] WHERE [Ma] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[HoaDon]";
    DBConnection dBConnection;

    IKhachHangRepository khachHangRepository;
    INhanVienRepository nhanVienRepository;

    public HoaDonRepository() {
        dBConnection = new DBConnection();
        khachHangRepository = new KhachHangRepository();
        nhanVienRepository = new NhanVienRepository();
    }

    @Override
    public List<HoaDon> getAllHoasDons() {
        return getlListHoaDons(SELECT_ALL_SQL);
    }

    private List<HoaDon> getlListHoaDons(String sql, Object... args) {
        List<HoaDon> listHoaDons = new ArrayList<>();
        try {
            ResultSet rs = dBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                HoaDon hoaDon = mapingHoaDon(rs);
                listHoaDons.add(hoaDon);
            }
        } catch (Exception e) {
        }
        return listHoaDons;
    }

    private HoaDon mapingHoaDon(ResultSet rs) {
        try {
            if (rs != null) {
                String id = rs.getString("Id");
                String idKH = rs.getString("IdKH");
                KhachHang khachHang = khachHangRepository.getKhachHangTheoId(idKH);
                String idNV = rs.getString("IdNV");
                NhanVien nhanVien = nhanVienRepository.layNhanVienTheoId(idNV);
                String ma = rs.getString("Ma");
                Date ngayTao = rs.getDate("NgayTao");
                Date ngayThanhToan = rs.getDate("NgayThanhToan");
                Date ngayShip = rs.getDate("NgayShip");
                Date ngayNhan = rs.getDate("NgayNhan");
                int tinhTrang = rs.getInt("TinhTrang");
                String tenNguoiNhan = rs.getNString("TenNguoiNhan");
                String diaChi = rs.getNString("DiaChi");
                String sdt = rs.getString("Sdt");
                return new HoaDon(id, khachHang, nhanVien, ma, ngayTao, ngayThanhToan, ngayShip, ngayNhan, tinhTrang, tenNguoiNhan, diaChi, sdt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveHoaDon(HoaDon hd) {
        int check = 0;
        check = dBConnection.ExcuteQuyetnd(INSERT_SQL, hd.getKhachHang().getId(), hd.getNhanVien().getId(), hd.getMa(), hd.getNgayTao(), hd.getNgayThanhToan(),
                hd.getNgayShip(), hd.getNgayNhan(), hd.getTinhTrang(), hd.getTenNguoiNhan(), hd.getDiaChi(), hd.getSdt());
        return check > 0;
    }

    @Override
    public boolean updateHoaDon(HoaDon hd) {
        int check = 0;
        check = dBConnection.ExcuteQuyetnd(UPDATE_SQL, hd.getKhachHang().getId(), hd.getNhanVien().getId(), hd.getNgayTao(), hd.getNgayThanhToan(),
                hd.getNgayShip(), hd.getNgayNhan(), hd.getTinhTrang(), hd.getTenNguoiNhan(), hd.getDiaChi(), hd.getSdt(), hd.getId());
        return check > 0;
    }

    @Override
    public boolean deleteHoaDon(String id) {
        int check = 0;
        check = dBConnection.ExcuteQuyetnd(DELETE_SQL, id);
        return check > 0;
    }

    @Override
    public HoaDon getHoaDonTheoId(String id) {
        return getHoaDon(SELECT_BY_SQL_ID, id);
    }

    @Override
    public HoaDon getHoaDonTheoMa(String ma) {
        return getHoaDon(SELECT_BY_SQL_Ma, ma);
    }

    private HoaDon getHoaDon(String sql, Object... args) {
        try {
            ResultSet rs = dBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                HoaDon hoaDon = mapingHoaDon(rs);
                return hoaDon;
            }
        } catch (Exception e) {
        }
        return null;
    }

}
