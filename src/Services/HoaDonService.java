/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.HoaDon;
import Repositories.HoaDonRepository;
import Repositories.IHoaDonRepository;
import ViewsModels.QLHoaDon;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class HoaDonService implements IHoaDonService {

    IHoaDonRepository iHoaDonRepository;

    public HoaDonService() {
        iHoaDonRepository = new HoaDonRepository();
    }

    @Override
    public List<QLHoaDon> getallHoaDons() {
        List<QLHoaDon> listHoaDons = new ArrayList<>();
        List<HoaDon> list = iHoaDonRepository.getAllHoasDons();
        for (HoaDon hd : list) {
            listHoaDons.add(new QLHoaDon(hd.getId(), hd.getKhachHang(), hd.getNhanVien(), hd.getMa(), hd.getNgayTao(), hd.getNgayThanhToan(),
                    hd.getNgayShip(), hd.getNgayNhan(), hd.getTinhTrang(), hd.getTenNguoiNhan(), hd.getDiaChi(), hd.getSdt()));
        }
        return listHoaDons;
    }

    @Override
    public String saveHoaDon(QLHoaDon hoaDon) {
        if (iHoaDonRepository.saveHoaDon(new HoaDon(hoaDon.getId(), hoaDon.getKhachHang(), hoaDon.getNhanVien(), hoaDon.getMa(), hoaDon.getNgayTao(),
                hoaDon.getNgayThanhToan(), hoaDon.getNgayShip(), hoaDon.getNgayNhan(), hoaDon.getTinhTrang(), hoaDon.getTenNguoiNhan(),
                hoaDon.getDiaChi(), hoaDon.getSdt())) == true) {
            return "Thêm mới thành công";
        } else {
            return "Thêm mới thất bại";
        }
    }

    @Override
    public String updateHoaDon(QLHoaDon hoaDon) {
        if (iHoaDonRepository.updateHoaDon(new HoaDon(hoaDon.getId(), hoaDon.getKhachHang(), hoaDon.getNhanVien(), hoaDon.getMa(), hoaDon.getNgayTao(),
                hoaDon.getNgayThanhToan(), hoaDon.getNgayShip(), hoaDon.getNgayNhan(), hoaDon.getTinhTrang(), hoaDon.getTenNguoiNhan(),
                hoaDon.getDiaChi(), hoaDon.getSdt())) == true) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String deleteHoadon(String id) {
        if (iHoaDonRepository.deleteHoaDon(id) == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

}
