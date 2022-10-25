/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.KhachHang;
import Repositories.IKhachHangRepository;
import Repositories.KhachHangRepository;
import ViewsModels.QLKhachHang;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhachHangService implements IKhachHangService {

    private IKhachHangRepository iKhachHangRepository;
    private List<QLKhachHang> listQLKhachHangs;

    public KhachHangService() {
        iKhachHangRepository = new KhachHangRepository();
    }

    @Override
    public List<QLKhachHang> getAllKhachHang() {
        listQLKhachHangs = new ArrayList<>();
        List<KhachHang> listCuaHangs = new ArrayList<>();
        listCuaHangs = iKhachHangRepository.findAll();
        for (KhachHang kh : listCuaHangs) {
            listQLKhachHangs.add(new QLKhachHang(kh.getId(), kh.getMa(), kh.getTen(), kh.getTenDem(), kh.getHo(), kh.getNgaySinh(), kh.getSdt(), kh.getDiaChi(), kh.getThanhPho(), kh.getQuocGia(), kh.getMatKhau()));
        }
        return listQLKhachHangs;
    }

    @Override
    public String createNewKhachHang(QLKhachHang kh) {
        boolean check;
        check = iKhachHangRepository.saveKhachHang(new KhachHang(kh.getId(), kh.getMa(), kh.getTen(), kh.getTenDem(), kh.getHo(), kh.getNgaySinh(), kh.getSdt(), kh.getDiaChi(), kh.getThanhPho(), kh.getQuocGia(), kh.getMatKhau()));
        if (check == true) {
            return "Thêm mới thành công";
        } else {
            return "Thêm mới thất bại";
        }
    }

    @Override
    public String deleteKhachHang(String id) {
        boolean check;
        check = iKhachHangRepository.deleteKhachHang(id);
        if (check == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String updateKhachHang(QLKhachHang kh, String id) {
        boolean check;
        check = iKhachHangRepository.updateKhachHang(new KhachHang(kh.getId(), kh.getMa(), kh.getTen(), kh.getTenDem(), kh.getHo(),
                 kh.getNgaySinh(), kh.getSdt(), kh.getDiaChi(), kh.getThanhPho(), kh.getQuocGia(), kh.getMatKhau()), id);
        if (check == true) {
            return "Sửathành công";
        } else {
            return "Sửa thất bại";
        }
    }

}
