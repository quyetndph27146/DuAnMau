/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.ChiTietSP;
import Repositories.ChiTietSPRepository;
import Repositories.IChitietSPRepository;
import ViewsModels.QLChiTietSP;
import ViewsModels.QLDongSP;
import ViewsModels.QLMauSac;
import ViewsModels.QLNSX;
import ViewsModels.QLSanPham;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ChiTietSPService implements IChiTietSPService {

    IChitietSPRepository iChitietSPRepository;
    List<QLChiTietSP> listChiTietSPs;

    public ChiTietSPService() {
        iChitietSPRepository = new ChiTietSPRepository();
        listChiTietSPs = new ArrayList<>();
    }

    @Override
    public List<QLChiTietSP> getAllChiTietSPs() {
        listChiTietSPs = new ArrayList<>();
        List<ChiTietSP> list = new ArrayList<>();
        list = iChitietSPRepository.getAllChiTietSPs();
        for (ChiTietSP chiTietSP : list) {
            listChiTietSPs.add(new QLChiTietSP(chiTietSP.getId(), chiTietSP.getSanPham(), chiTietSP.getNsx(), chiTietSP.getMauSac(), chiTietSP.getDongSP(),
                     chiTietSP.getNamBH(), chiTietSP.getMoTa(), chiTietSP.getSoLuongTon(), chiTietSP.getGiaNhap(), chiTietSP.getGiaBan()));
        }
        return listChiTietSPs;
    }

    @Override
    public String createChiTietSP(QLChiTietSP chiTietSP) {
        boolean ok = iChitietSPRepository.saveChiTietSP(new ChiTietSP(chiTietSP.getId(), chiTietSP.getSanPham(), chiTietSP.getNsx(), chiTietSP.getMauSac(),
                 chiTietSP.getDongSP(), chiTietSP.getNamBH(), chiTietSP.getMoTa(), chiTietSP.getSoLuongTon(), chiTietSP.getGiaNhap(), chiTietSP.getGiaBan()));
        if (ok == true) {
            return "Thêm mới thành công";
        } else {
            return "Thêm mới thất bại";
        }
    }

    @Override
    public String updateChiTietSP(QLChiTietSP qLChiTietSP, String id) {
        boolean ok = iChitietSPRepository.updateChiTietSP(new ChiTietSP(qLChiTietSP.getId(), qLChiTietSP.getSanPham(), qLChiTietSP.getNsx(), qLChiTietSP.getMauSac(),
                 qLChiTietSP.getDongSP(), qLChiTietSP.getNamBH(), qLChiTietSP.getMoTa(), qLChiTietSP.getSoLuongTon(), qLChiTietSP.getGiaNhap(), qLChiTietSP.getGiaBan()), id);
        if (ok == true) {
            return "Sửa thành công";
        } else {
            return "Sửa mới thất bại";
        }
    }

    @Override
    public String deleteChiTietSP(String id) {
        boolean ok = iChitietSPRepository.deleteChiTietSP(id);
        if (ok == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

}
