/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.ChiTietSP;
import DomainModels.DongSP;
import DomainModels.MauSac;
import DomainModels.NSX;
import DomainModels.SanPham;
import Utilities.DBConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ChiTietSPRepository implements IChitietSPRepository {

    final String INSERT_SQL = "INSERT INTO [dbo].[ChiTietSP] ([Id],[IdSP],[IdNsx],[IdMauSac],[IdDongSP],[NamBH],[MoTa],[SoLuongTon],[GiaNhap],[GiaBan])VALUES(NEWID(),?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE [dbo].[ChiTietSP] SET [NamBH] = ?,[MoTa] = ?,[SoLuongTon] = ?,[GiaNhap] = ?,[GiaBan] = ?   WHERE [Id] = ?";
    final String DELETE_SQL = "DELETE FROM [dbo].[ChiTietSP] WHERE [Id] = ?";
    final String SELECT_BY_SQL_ID = "SELECT * FROM [dbo].[ChiTietSP] WHERE [Id] = ?";
    final String SELECT_BY_SQL_NAME = "SELECT [ChiTietSP].[Id],[IdSP],[IdNsx],[IdMauSac],[IdDongSP],[NamBH],[MoTa],[SoLuongTon],[GiaNhap],[GiaBan] FROM [dbo].[ChiTietSP] join [dbo].[SanPham] on [ChiTietSP].[IdSP] = [SanPham].[Id] WHERE [Ten] = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [dbo].[ChiTietSP]";
    final String SELECT_ALL_CHITIETSP_SP = "SELECT [Ten] FROM [dbo].[ChiTietSP] join [dbo].[SanPham] on [ChiTietSP].[IdSP] = [SanPham].[Id]";
    final String SELECT_Ten = "SELECT [Ten] FROM [dbo].[ChiTietSP] join [dbo].[SanPham] on [ChiTietSP].[IdSP] = [SanPham].[Id] where [ChiTietSP].[Id] = ?";

    ISanPhamRepository iSanPhamRepository;
    INSXRepository iNSXRepository;
    IMauSacRepository iMauSacRepository;
    IDongSPRepository iDongSPRepository;
    List<ChiTietSP> listChiTietSPs;

    public ChiTietSPRepository() {
        iSanPhamRepository = new SanPhamRepository();
        iNSXRepository = new NSXRepository();
        iMauSacRepository = new MauSacRepository();
        iDongSPRepository = new DongSPRepository();
        listChiTietSPs = new ArrayList<>();
    }

    @Override
    public List<ChiTietSP> getAllChiTietSPs() {
        return getChiTietSPs(SELECT_ALL_SQL);
    }

    private List<ChiTietSP> getChiTietSPs(String sql, Object... args) {
        listChiTietSPs = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                listChiTietSPs.add(mapingChiTietSP(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listChiTietSPs;
    }

    private ChiTietSP mapingChiTietSP(ResultSet rs) {
        try {
            if (rs != null) {
                String id = rs.getString("id");
                String idSP = rs.getString("IdSP");
                SanPham sanPham = iSanPhamRepository.laySanPhamTheoId(idSP);
                String idNSX = rs.getString("IdNsx");
                NSX nsx = iNSXRepository.layNSXTheoId(idNSX);
                String idMauSac = rs.getString("IdMauSac");
                MauSac mauSac = iMauSacRepository.layMauSacTheoId(idMauSac);
                String idDongSP = rs.getString("IdDongSP");
                DongSP dongSP = iDongSPRepository.layDongSPTheoId(idDongSP);
                int namBH = rs.getInt("NamBH");
                String moTa = rs.getNString("MoTa");
                int soLuongton = rs.getInt("SoLuongTon");
                float giaNhap = rs.getFloat("GiaNhap");
                float giaBan = rs.getFloat("GiaBan");

                return new ChiTietSP(id, sanPham, nsx, mauSac, dongSP, namBH, moTa, soLuongton, giaNhap, giaBan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveChiTietSP(ChiTietSP chiTietSP) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(INSERT_SQL, chiTietSP.getSanPham().getId(), chiTietSP.getNsx().getId(), chiTietSP.getMauSac().getId(),
                chiTietSP.getDongSP().getId(), chiTietSP.getNamBH(), chiTietSP.getMoTa(), chiTietSP.getSoLuongTon(), chiTietSP.getGiaNhap(),
                chiTietSP.getGiaBan());
        return check > 0;
    }

    @Override
    public boolean updateChiTietSP(ChiTietSP chiTietSP, String id) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(UPDATE_SQL, chiTietSP.getNamBH(), chiTietSP.getMoTa(), chiTietSP.getSoLuongTon(), chiTietSP.getGiaNhap(), chiTietSP.getGiaBan(), id);
        return check > 0;
    }

    @Override
    public boolean deleteChiTietSP(String id) {
        int check = 0;
        check = DBConnection.ExcuteQuyetnd(DELETE_SQL, id);
        return check > 0;
    }

    @Override
    public ChiTietSP getChiTietSPtheoId(String id) {
        return getChiTietSP(SELECT_BY_SQL_ID, id);
    }

    @Override
    public ChiTietSP getChiTietSPTheoTen(String ten) {
        return getChiTietSP(SELECT_BY_SQL_NAME, ten);
    }

    private ChiTietSP getChiTietSP(String sql, Object... args) {
        ChiTietSP chiTietSP = null;
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                chiTietSP = mapingChiTietSP(rs);
            }
        } catch (Exception e) {
            System.out.println("1");
        }
        return chiTietSP;
    }

    @Override
    public List<String> getAllTen() {
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(SELECT_ALL_CHITIETSP_SP);
            while (rs.next()) {
                String ten = rs.getNString("Ten");
                list.add(ten);
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public String getTen(String id) {
        String ten = null;
        try {
            ResultSet rs = DBConnection.getDataFromQuery(SELECT_Ten, id);
            while (rs.next()) {
                ten = rs.getNString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ten;
    }

}
