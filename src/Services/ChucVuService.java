/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.ChucVu;
import Repositories.ChucVuRepository;
import Repositories.IChucVuRepository;
import ViewsModels.QLChucVu;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ChucVuService implements IChucVuService {

    private final IChucVuRepository iChucVuRepository;

    public ChucVuService() {
        iChucVuRepository = new ChucVuRepository();
    }

    @Override
    public List<QLChucVu> getChucVus() {
        List<ChucVu> chucVu = new ArrayList<>();
        chucVu = iChucVuRepository.findAll();
        List<QLChucVu> qLChucVus = new ArrayList<>();
        for (ChucVu cv : chucVu) {
            qLChucVus.add(new QLChucVu(cv.getId(), cv.getMa(), cv.getTen()));
        }
        return qLChucVus;
    }

    @Override
    public String createNewChucVu(QLChucVu qLChucVu) {
        if (iChucVuRepository.save(new ChucVu(qLChucVu.getId(), qLChucVu.getMa(), qLChucVu.getTen())) == true) {
            return "Thêm mới thành công";
        } else {
            return "Thêm mới thất bại";
        }
    }

    @Override
    public String deleteChucVu(String id) {
        if (iChucVuRepository.delete(id) == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String updateChucVu(String ten, String id) {
        if (iChucVuRepository.update(ten, id) == true) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public QLChucVu layQLChucVuTheoId(String id) {
        ChucVu cv = iChucVuRepository.layChucVuTheoId(id);
        return new QLChucVu(cv.getId(), cv.getMa(), cv.getTen());
    }

    @Override
    public QLChucVu layQLChucVuTheoTen(String ten) {
        ChucVu cv = iChucVuRepository.layChucVuTheoTen(ten);
        return new QLChucVu(cv.getId(), cv.getMa(), cv.getTen());
    }

}
