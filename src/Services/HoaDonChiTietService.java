/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.HoaDonChiTiet;
import Repositories.HoaDonChiTietRepository;
import Repositories.IHoaDonChiTietRepository;
import ViewsModels.QLHoaDonChiTiet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietService implements IHoaDonChiTietService {

    IHoaDonChiTietRepository iHoaDonChiTietRepository;

    public HoaDonChiTietService() {
        iHoaDonChiTietRepository = new HoaDonChiTietRepository();
    }

    @Override
    public List<QLHoaDonChiTiet> getAllHoaDonChiTiet() {
        List<HoaDonChiTiet> list = iHoaDonChiTietRepository.getAllHoaDonChiTiet();
        List<QLHoaDonChiTiet> listHoaDonChiTiets = new ArrayList<>();
        for (HoaDonChiTiet hdct : list) {
            listHoaDonChiTiets.add(new QLHoaDonChiTiet(hdct.getHoaDon(), hdct.getChiTietSP(), hdct.getSoLuong(), hdct.getDonGia()));
        }
        return listHoaDonChiTiets;
    }

    @Override
    public String saveHoaDonChiTiet(QLHoaDonChiTiet hdct) {
        boolean check;
        check = iHoaDonChiTietRepository.saveHoaDonChiTiet(new HoaDonChiTiet(hdct.getHoaDon(), hdct.getChiTietSP(), hdct.getSoLuong(), hdct.getDonGia()));
        if (check == true) {
            return "Thêm mới thành công";
        } else {
            return "Thêm mới thất bại";
        }
    }

    @Override
    public String updateHoaDonChiTiet(QLHoaDonChiTiet hdct) {
        boolean check;
        check = iHoaDonChiTietRepository.updateHoaDonChiTiet(new HoaDonChiTiet(hdct.getHoaDon(), hdct.getChiTietSP(), hdct.getSoLuong(), hdct.getDonGia()));
        if (check == true) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String deleteHoaDonChiTiet(String id,String idChiTietSP) {
        boolean check;
        check = iHoaDonChiTietRepository.deleteHoaDonChiTiet(id,idChiTietSP);
        if (check == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

}
