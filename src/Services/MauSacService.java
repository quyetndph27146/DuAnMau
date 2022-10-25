/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.MauSac;
import Repositories.IMauSacRepository;
import Repositories.MauSacRepository;
import ViewsModels.QLMauSac;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MauSacService implements IMauSacService {

    IMauSacRepository iMauSacRepository;
    List<QLMauSac> listQLMauSacs;

    public MauSacService() {
        iMauSacRepository = new MauSacRepository();
        listQLMauSacs = new ArrayList<>();
    }

    @Override
    public List<QLMauSac> getMauSacs() {
        listQLMauSacs = new ArrayList<>();
        List<MauSac> list = iMauSacRepository.findAll();
        for (MauSac ms : list) {
            listQLMauSacs.add(new QLMauSac(ms.getId(), ms.getMa(), ms.getTen()));
        }
        return listQLMauSacs;
    }

    @Override
    public String createNewMauSac(QLMauSac mauSac) {
        boolean check;
        check = iMauSacRepository.save(new MauSac(mauSac.getId(), mauSac.getMa(), mauSac.getTen()));
        if (check == true) {
            return "Thêm mới thành công";
        } else {
            return "Thêm mới thất bại";
        }
    }

    @Override
    public String deleteMauSac(String id) {
        boolean check;
        check = iMauSacRepository.delete(id);
        if (check == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String updateMauSac(String ten, String id) {
        boolean check;
        check = iMauSacRepository.update(ten, id);
        if (check == true) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public QLMauSac layQLMauSacTheoId(String id) {
        MauSac mauSac = iMauSacRepository.layMauSacTheoId(id);
        return new QLMauSac(mauSac.getId(), mauSac.getMa(), mauSac.getTen());
    }

    @Override
    public QLMauSac layQLMauSacTheoTen(String ten) {
        MauSac mauSac = iMauSacRepository.layMauSacTheoTen(ten);
        return new QLMauSac(mauSac.getId(), mauSac.getMa(), mauSac.getTen());
    }

}
