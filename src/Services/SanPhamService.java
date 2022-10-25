/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.SanPham;
import Repositories.ISanPhamRepository;
import Repositories.SanPhamRepository;
import ViewsModels.QLSanPham;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SanPhamService implements ISanPhamService {

    private ISanPhamRepository iSanPhamRepository;

    public SanPhamService() {
        iSanPhamRepository = new SanPhamRepository();
    }

    @Override
    public List<QLSanPham> getSanPhams() {
        List<SanPham> sanPham = new ArrayList<>();
        sanPham = iSanPhamRepository.findAll();
        List<QLSanPham> qLSanPhams = new ArrayList<>();
        for (SanPham sp : sanPham) {
            qLSanPhams.add(new QLSanPham(sp.getId(), sp.getMa(), sp.getTen()));
        }
        return qLSanPhams;
    }

    @Override
    public String createNewSanPham(QLSanPham sanPham) {
        if (iSanPhamRepository.save(new SanPham(sanPham.getId(), sanPham.getMa(), sanPham.getTen())) == true) {
            return "Thêm mới thành công";
        } else {
            return "Thêm mới thất bại";
        }
    }

    @Override
    public String deleteSanPham(String id) {
        if (iSanPhamRepository.delete(id) == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String updateSanPham(String ten, String id) {
        if (iSanPhamRepository.update(ten, id) == true) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public QLSanPham laySanPhamTheoId(String id) {
        SanPham sp = iSanPhamRepository.laySanPhamTheoId(id);
        return new QLSanPham(sp.getId(), sp.getMa(), sp.getTen());
    }

    @Override
    public QLSanPham laySanPhamTheoTen(String ten) {
        SanPham sp = iSanPhamRepository.laySanPhamTheoTen(ten);
        return new QLSanPham(sp.getId(), sp.getMa(), sp.getTen());
    }

}
