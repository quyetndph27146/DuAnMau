/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.NhanVien;
import Repositories.INhanVienRepository;
import Repositories.NhanVienRepository;
import ViewsModels.QLNhanVien;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienService implements INhanVienService {

    private INhanVienRepository iNhanVienRepository;
    private List<QLNhanVien> listQLNhanViens;

    public NhanVienService() {
        iNhanVienRepository = new NhanVienRepository();
        listQLNhanViens = new ArrayList<>();
    }

    @Override
    public List<QLNhanVien> getAllLNhanViens() {
        List<NhanVien> listNhanViens = iNhanVienRepository.findAll();
        listQLNhanViens = new ArrayList<>();
        for (NhanVien nv : listNhanViens) {
            listQLNhanViens.add(new QLNhanVien(nv.getId(), nv.getMa(), nv.getTen(), nv.getTenDem(), nv.getHo(), nv.getGioiTinh(), nv.getNgaySinh(), nv.getDiaChi(),
                     nv.getSdt(), nv.getMatKhau(), nv.getCuaHang(), nv.getChucVu(), nv.getNhanVien(), nv.getTrangThai()));
        }
        return listQLNhanViens;
    }

    @Override
    public String createNewNhanVien(QLNhanVien nv) {
        NhanVien nhanVien;
        if (nv.getNhanVien() == null) {
            nhanVien = null;
        } else {
            nhanVien = nv.getNhanVien();
        }
        boolean check;
        check = iNhanVienRepository.saveNhanVien(new NhanVien(nv.getId(), nv.getMa(), nv.getTen(), nv.getTenDem(), nv.getHo(), nv.getGioiTinh(),
                nv.getNgaySinh(), nv.getDiaChi(), nv.getSdt(), nv.getMatKhau(), nv.getCuaHang(), nv.getChucVu(), nhanVien, nv.getTrangThai()));
        if (check == true) {
            return "Thêm mới thành công";
        } else {
            return "Thêm mới thất bại";
        }
    }

    @Override
    public String deleteNhanVien(String id) {
        boolean check;
        check = iNhanVienRepository.deleteNhanVien(id);
        if (check == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String updateNhanVien(QLNhanVien nv, String id) {
        NhanVien nhanVien;
        if (nv.getNhanVien() == null) {
            nhanVien = null;
        } else {
            nhanVien = nv.getNhanVien();
        }
        boolean check;
        check = iNhanVienRepository.updateNhanVien(new NhanVien(nv.getId(), nv.getMa(), nv.getTen(), nv.getTenDem(), nv.getHo(), nv.getGioiTinh(),
                nv.getNgaySinh(), nv.getDiaChi(), nv.getSdt(), nv.getMatKhau(), nv.getCuaHang(), nv.getChucVu(), nhanVien, nv.getTrangThai()), id);
        if (check == true) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

}
