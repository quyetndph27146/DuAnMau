/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.DongSP;
import Repositories.DongSPRepository;
import Repositories.IDongSPRepository;
import ViewsModels.QLDongSP;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class DongSPService implements IDongSPService {

    private IDongSPRepository iDongSPRepository;

    public DongSPService() {
        iDongSPRepository = new DongSPRepository();
    }

    @Override
    public List<QLDongSP> getDongSP() {
        List<DongSP> dongSP = new ArrayList<>();
        dongSP = iDongSPRepository.findAll();
        List<QLDongSP> qLDongSPs = new ArrayList<>();
        for (DongSP dsp : dongSP) {
            qLDongSPs.add(new QLDongSP(dsp.getId(), dsp.getMa(), dsp.getTen()));
        }
        return qLDongSPs;
    }

    @Override
    public String createNewDongSP(QLDongSP qLDongSP) {
        if (iDongSPRepository.save(new DongSP(qLDongSP.getId(), qLDongSP.getMa(), qLDongSP.getTen())) == true) {
            return "Thêm mới thành công";
        } else {
            return "Thêm mới thất bại";
        }
    }

    @Override
    public String deleteDongSP(String id) {
        if (iDongSPRepository.delete(id) == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String updateDongSP(String ten, String id) {
        if (iDongSPRepository.update(ten, id) == true) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public QLDongSP layDongSPTheoId(String id) {
        DongSP dongSP = iDongSPRepository.layDongSPTheoId(id);
        return new QLDongSP(dongSP.getId(), dongSP.getMa(), dongSP.getTen());
    }

    @Override
    public QLDongSP layDongSPTheoTen(String ten) {
        DongSP dongSP = iDongSPRepository.layDongSPTheoTen(ten);
        return new QLDongSP(dongSP.getId(), dongSP.getMa(), dongSP.getTen());
    }

}
