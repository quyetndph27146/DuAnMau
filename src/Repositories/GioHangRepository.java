/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.GioHang;
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
public class GioHangRepository implements IGioHangRepository {

    final String INSERT_SQL = "INSERT INTO [dbo].[GioHang] ([Id],[IdKH],[IdNV],[Ma],[NgayTao],[NgayThanhToan],[TenNguoiNhan],[DiaChi],[Sdt],[TinhTrang])VALUES(NEWID(),?,?,?,?,?,?,?,?, ?)";
    final String UPDATE_SQL = "UPDATE [dbo].[GioHang] SET [NgayTao] = ?,[NgayThanhToan] = ?,[TenNguoiNhan] = ?,[DiaChi] = ?,[Sdt] = ?,[TinhTrang] = ? WHERE [Id] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[GioHang] WHERE [Id] = ?";
    final String SELECT_BY_SQL_ID = "SELECT * FROM [dbo].[GioHang] WHERE [Id] = ?";
    final String SELECT_BY_SQL_Ma = "SELECT * FROM [dbo].[GioHang] WHERE [Ma] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[GioHang]";

    IKhachHangRepository iKhachHangRepository;
    INhanVienRepository iNhanVienRepository;
    List<GioHang> listGioHangs;

    public GioHangRepository() {
        iKhachHangRepository = new KhachHangRepository();

        iNhanVienRepository = new NhanVienRepository();

        listGioHangs = new ArrayList<>();
    }

    @Override
    public List<GioHang> getAllGioHangs() {
        return getdsGioHangs(SELECT_ALL_SQL);
    }

    private List<GioHang> getdsGioHangs(String sql, Object... args) {
        listGioHangs = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                listGioHangs.add(mapingGioHang(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("2");
        }
        return listGioHangs;
    }

    private GioHang mapingGioHang(ResultSet rs) {
        try {
            if (rs != null) {
                String id = rs.getString("Id");
                String idKH = rs.getString("IdKH");
                KhachHang kh = iKhachHangRepository.getKhachHangTheoId(idKH);
                String idNV = rs.getString("IdNV");
                NhanVien nv = iNhanVienRepository.layNhanVienTheoId(idNV);
                String ma = rs.getString("Ma");
                Date ngayTao = rs.getDate("NgayTao");
                Date ngayThanhToan = rs.getDate("NgayThanhToan");
                String tenNguoiNhan = rs.getString("TenNguoiNhan");
                String diaChi = rs.getString("DiaChi");
                String sdt = rs.getString("Sdt");
                int tinhTrang = rs.getInt("TinhTrang");

                return new GioHang(id, kh, nv, ma, ngayTao, ngayThanhToan, tenNguoiNhan, diaChi, sdt, tinhTrang);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("1");
        }
        return null;
    }

    @Override
    public boolean saveGioHang(GioHang gh) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(INSERT_SQL, gh.getKhachHang().getId(), gh.getNhanVien().getId(), gh.getMa(),
                gh.getNgayTao(), gh.getNgayThanhToan(), gh.getTenNguoiNhan(), gh.getDiaChi(), gh.getSdt(), gh.getTinhTrang());
        return check > 0;
    }

    @Override
    public boolean updateGioHang(GioHang gh) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(UPDATE_SQL, gh.getNgayTao(), gh.getNgayThanhToan(), gh.getTenNguoiNhan(), gh.getDiaChi(), gh.getSdt(), gh.getTinhTrang(), gh.getId());
        return check > 0;
    }

    @Override
    public boolean deleteGioHang(String id) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(DELETE_SQL, id);
        return check > 0;
    }

    @Override
    public GioHang getGioHangTheoId(String id) {
        return getGioHang(SELECT_BY_SQL_ID,id);
    }

    @Override
    public GioHang getGioHangTheoMa(String ma) {
        return getGioHang(SELECT_BY_SQL_Ma, ma);
    }
    
    private GioHang getGioHang(String sql,Object...args){
        GioHang gh = null;
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {                
                gh = mapingGioHang(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("3");
        }
        return gh;
    }

}
