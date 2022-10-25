/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.ChiTietSP;
import DomainModels.GioHang;
import DomainModels.GioHangChiTiet;
import Utilities.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class GioHangChiTietRepository implements IGioHangChiTietRepository {

    final String INSERT_SQL = "INSERT INTO [dbo].[GioHangChiTiet] ([IdGioHang],[IdChiTietSP],[SoLuong],[DonGia],[DonGiaKhiGiam])VALUES(?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[GioHangChiTiet] SET [SoLuong] = ?,[DonGia] = ?,[DonGiaKhiGiam = ? WHERE [IdGioHang] = ? AND [idChiTietSP] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[GioHangChiTiet] WHERE [IdGioHang] = ? AND [idChiTietSP] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[GioHangChiTiet]";
    IGioHangRepository iGioHangRepository;
    IChitietSPRepository iChitietSPRepository;

    public GioHangChiTietRepository() {
        iChitietSPRepository = new ChiTietSPRepository();
        iGioHangRepository = new GioHangRepository();
    }

    @Override
    public List<GioHangChiTiet> getALllGioHangChitiets() {
        return getlistGioHangChiTiet(SELECT_ALL_SQL);
    }

    private List<GioHangChiTiet> getlistGioHangChiTiet(String sql, Object... args) {
        List<GioHangChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                list.add(mapingGioHangChiTiet(rs));
            }
        } catch (Exception e) {
        }
        return list;
    }

    private GioHangChiTiet mapingGioHangChiTiet(ResultSet rs) {
        try {
            if (rs != null) {
                String idGH = rs.getString("IdGioHang");
                GioHang gh = iGioHangRepository.getGioHangTheoId(idGH);
                String idChiTietSP = rs.getString("IdChiTietSP");
                ChiTietSP chiTietSP = iChitietSPRepository.getChiTietSPtheoId(idChiTietSP);
                int soLuong = rs.getInt("SoLuong");
                float donGia = rs.getFloat("DonGia");
                float giamGia = rs.getFloat("DonGiaKhiGiam");
                return new GioHangChiTiet(gh, chiTietSP, soLuong, donGia, giamGia);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean saveGioHangCT(GioHangChiTiet ghct) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(INSERT_SQL, ghct.getGioHang().getId(), ghct.getChiTietSP().getId(), ghct.getSoLuong(), ghct.getDonGia(), ghct.getDonGiaKhiGiam());
        return check > 0;
    }

    @Override
    public boolean updateGioHangCT(GioHangChiTiet ghct) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(UPDATE_SQL, ghct.getSoLuong(), ghct.getDonGia(), ghct.getDonGiaKhiGiam(), ghct.getGioHang().getId(), ghct.getChiTietSP().getId());
        return check > 0;
    }

    @Override
    public boolean deleteGioHangCT(String idGH, String idChiTietSP) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(DELETE_SQL,idGH, idChiTietSP);
        return check > 0;
    }

}
