/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.GioHangChiTiet;
import Repositories.GioHangChiTietRepository;
import Repositories.IGioHangChiTietRepository;
import ViewsModels.QLGioHangChiTiet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class GioHangChiTietService implements IGioHangChiTietService {

    IGioHangChiTietRepository iGioHangChiTietRepository;

    public GioHangChiTietService() {
        iGioHangChiTietRepository = new GioHangChiTietRepository();
    }

    @Override
    public List<QLGioHangChiTiet> getAllGioHangChiTiet() {
        List<GioHangChiTiet> list = iGioHangChiTietRepository.getALllGioHangChitiets();
        List<QLGioHangChiTiet> listGioHangChiTiets = new ArrayList<>();
        for (GioHangChiTiet ghct : list) {
            listGioHangChiTiets.add(new QLGioHangChiTiet(ghct.getGioHang(), ghct.getChiTietSP(), ghct.getSoLuong(), ghct.getDonGia(), ghct.getDonGiaKhiGiam()));
        }
        return listGioHangChiTiets;
    }

    @Override
    public String saveGioHangChiTiet(QLGioHangChiTiet ghct) {
        boolean check;
        check = iGioHangChiTietRepository.saveGioHangCT(new GioHangChiTiet(ghct.getGioHang(), ghct.getChiTietSP(), ghct.getSoLuong(), ghct.getDonGia(), ghct.getDonGiaKhiGiam()));
        if (check == true) {
            return "Thêm mới thành công";
        } else {
            return "Thêm mới thất bại";
        }
    }

    @Override
    public String updateGioHangChiTiet(QLGioHangChiTiet ghct) {
        boolean check;
        check = iGioHangChiTietRepository.updateGioHangCT(new GioHangChiTiet(ghct.getGioHang(), ghct.getChiTietSP(), ghct.getSoLuong(), ghct.getDonGia(), ghct.getDonGiaKhiGiam()));
        if (check == true) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String deleteGioHangChiTiet(String id, String idChiTietSP) {
        boolean check;
        check = iGioHangChiTietRepository.deleteGioHangCT(id,idChiTietSP);
        if (check == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

}
