/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.ChiTietSP;
import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import Utilities.DBConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietRepository implements IHoaDonChiTietRepository {

    final String INSERT_SQL = "INSERT INTO [dbo].[HoaDonChiTiet] ([IdHoaDon],[IdChiTietSP],[SoLuong],[DonGia])VALUES(?,?,?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[HoaDonChiTiet] SET [SoLuong] = ?,[DonGia] = ? WHERE [IdHoaDon] = ? AND [idChiTietSP] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[HoaDonChiTiet] WHERE [IdHoaDon] = ? AND [idChiTietSP] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[HoaDonChiTiet]";

    IHoaDonRepository iHoaDonRepository;
    IChitietSPRepository iChitietSPRepository;

    public HoaDonChiTietRepository() {
        iHoaDonRepository = new HoaDonRepository();
        iChitietSPRepository = new ChiTietSPRepository();
    }

    @Override
    public List<HoaDonChiTiet> getAllHoaDonChiTiet() {
        return getlistHoaDonChiTiet(SELECT_ALL_SQL);
    }

    private List<HoaDonChiTiet> getlistHoaDonChiTiet(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                list.add(mapingHoaDonChiTiet(rs));
            }
        } catch (Exception e) {
        }
        return list;
    }

    private HoaDonChiTiet mapingHoaDonChiTiet(ResultSet rs) {
        try {
            if (rs != null) {
                String idHoaDon = rs.getString("IdHoaDon");
                HoaDon hoaDon = iHoaDonRepository.getHoaDonTheoId(idHoaDon);
                String idChiTietSP = rs.getString("IdChiTietSP");
                ChiTietSP chiTietSP = iChitietSPRepository.getChiTietSPtheoId(idChiTietSP);
                int soLuong = rs.getInt("SoLuong");
                float donGia = rs.getFloat("DonGia");
                return new HoaDonChiTiet(hoaDon, chiTietSP, soLuong, donGia);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean saveHoaDonChiTiet(HoaDonChiTiet hdct) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(INSERT_SQL, hdct.getHoaDon().getId(), hdct.getChiTietSP().getId(), hdct.getSoLuong(), hdct.getDonGia());
        return check > 0;
    }

    @Override
    public boolean updateHoaDonChiTiet(HoaDonChiTiet hdct) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(UPDATE_SQL, hdct.getSoLuong(), hdct.getDonGia(), hdct.getHoaDon().getId(),hdct.getChiTietSP().getId());
        return check > 0;
    }

    @Override
    public boolean deleteHoaDonChiTiet(String id, String idChiTietSP) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(DELETE_SQL, id, idChiTietSP);
        return check > 0;
    }

}
