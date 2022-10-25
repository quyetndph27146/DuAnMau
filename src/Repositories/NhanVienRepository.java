/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.ChucVu;
import DomainModels.CuaHang;
import DomainModels.NhanVien;
import Utilities.DBConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class NhanVienRepository implements INhanVienRepository {

    final String INSERT_SQL = "INSERT INTO [dbo].[NhanVien] ([Id],[Ma],[Ten],[TenDem],[Ho],[GioiTinh],[NgaySinh],[DiaChi],[Sdt],[MatKhau],[IdCH],[IdCV],[IdGuiBC],[TrangThai])VALUES(NEWID(),?,?,?,?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[NhanVien] SET [Ten] = ?,[TenDem] = ?,[Ho] = ?,[GioiTinh] = ?,[NgaySinh] = ?,[DiaChi] = ?,[Sdt] = ?,[MatKhau] = ?,[IdCH] = ?,[IdCV] = ?,[IdGuiBC] = ?,[TrangThai] = ?  WHERE [Id] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[NhanVien] WHERE [Id] = ?";
    final String SELECT_BY_SQL_ID = "SELECT * FROM [dbo].[NhanVien] WHERE [Id] = ?";
    final String SELECT_BY_SQL_NAME = "SELECT * FROM [dbo].[NhanVien] WHERE [Ten] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[NhanVien]";
    private List<NhanVien> listNhanViens;
    private IChucVuRepository iChucVuRepository;
    private ICuaHangRepository iCuaHangRepository;

    public NhanVienRepository() {
        iChucVuRepository = new ChucVuRepository();
        iCuaHangRepository = new CuaHangRepository();
    }

    @Override
    public List<NhanVien> findAll() {
        return getAllNhanViens(SELECT_ALL_SQL);
    }

    private List<NhanVien> getAllNhanViens(String sql, Object... args) {
        listNhanViens = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                listNhanViens.add(mapingNhanVien(rs));
            }
            return listNhanViens;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private NhanVien mapingNhanVien(ResultSet rs) {
        try {
            if (rs != null) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getNString("Ten");
                String tenDem = rs.getNString("TenDem");
                String ho = rs.getNString("Ho");
                String gt = rs.getNString("GioiTinh");
                String nSinh = rs.getString("NgaySinh");
                String diaChi = rs.getNString("DiaChi");
                String sdt = rs.getString("Sdt");
                String mK = rs.getString("MatKhau");
                String idCH = rs.getString("IdCH");
                CuaHang cuaHang = iCuaHangRepository.layCuaHangTheoID(idCH);
                String idCV = rs.getString("IdCV");
                ChucVu chucVu = iChucVuRepository.layChucVuTheoId(idCV);
                String idGuiBC = rs.getString("IdGuiBC");
                NhanVien nhanVien;
                if (idGuiBC != null) {
                    nhanVien = layNhanVienTheoId(idGuiBC);
                } else {
                    nhanVien = null;
                }
                int trangThai = rs.getInt("TrangThai");
                return new NhanVien(id, ma, ten, tenDem, ho, gt, nSinh, diaChi, sdt, mK, cuaHang, chucVu, nhanVien, trangThai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveNhanVien(NhanVien nv) {
        String idGuiBC;
        if (nv.getNhanVien() == null) {
            idGuiBC = null;
        } else {
            idGuiBC = nv.getNhanVien().getId();
        }
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(INSERT_SQL, nv.getMa(), nv.getTen(), nv.getTenDem(), nv.getHo(), nv.getGioiTinh(), nv.getNgaySinh(), nv.getDiaChi(), nv.getSdt(),
                nv.getMatKhau(), nv.getCuaHang().getId(), nv.getChucVu().getId(), idGuiBC, nv.getTrangThai());
        return check > 0;
    }

    @Override
    public boolean deleteNhanVien(String id) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(DELETE_SQL, id);
        return check > 0;
    }

    @Override
    public boolean updateNhanVien(NhanVien nv, String id) {
        String idGuiBC;
        if (nv.getNhanVien() == null) {
            idGuiBC = null;
        } else {
            idGuiBC = nv.getNhanVien().getId();
        }
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(UPDATE_SQL, nv.getTen(), nv.getTenDem(), nv.getHo(), nv.getGioiTinh(), nv.getNgaySinh(), nv.getDiaChi(), nv.getSdt(),
                nv.getMatKhau(), nv.getCuaHang().getId(), nv.getChucVu().getId(), idGuiBC, nv.getTrangThai(), id);
        return check > 0;
    }

    @Override
    public NhanVien layNhanVienTheoId(String id) {
        return layNhanVien(SELECT_BY_SQL_ID, id);
    }

    @Override
    public NhanVien layNhanVienTheoName(String ten) {
        return layNhanVien(SELECT_BY_SQL_NAME, ten);
    }

    private NhanVien layNhanVien(String sql, Object... args) {
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                return mapingNhanVien(rs);
            }
        } catch (Exception e) {
        }
        return null;
    }
}
