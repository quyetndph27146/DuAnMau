/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.NSX;
import Repositories.INSXRepository;
import Repositories.NSXRepository;
import ViewsModels.QLNSX;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NSXService implements INSXService {

    INSXRepository iNSXRepository;
    List<QLNSX> qlnsxs;

    public NSXService() {
        iNSXRepository = new NSXRepository();
        qlnsxs = new ArrayList<>();
    }

    @Override
    public List<QLNSX> getNSXs() {
        List<NSX> nsx = new ArrayList<>();
        nsx = iNSXRepository.findAll();
        qlnsxs = new ArrayList<>();
        for (NSX n : nsx) {
            qlnsxs.add(new QLNSX(n.getId(), n.getMa(), n.getTen()));
        }
        return qlnsxs;
    }

    @Override
    public String createNewNSX(QLNSX nsx) {
        if (iNSXRepository.save(new NSX(nsx.getId(), nsx.getMa(), nsx.getTen())) == true) {
            return "Thêm mới thành công";
        } else {
            return "Thêm mới thất bại";
        }
    }

    @Override
    public String deleteNSX(String id) {
        if (iNSXRepository.delete(id) == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String updateNSX(String ten, String id) {
        if (iNSXRepository.update(ten, id) == true) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public QLNSX layNSXTheoId(String id) {
        NSX nsx = iNSXRepository.layNSXTheoId(id);
        return new QLNSX(nsx.getId(), nsx.getMa(), nsx.getTen());
    }

    @Override
    public QLNSX layNSXTheoTen(String ten) {
        NSX nsx = iNSXRepository.layNSXTheoTen(ten);
        return new QLNSX(nsx.getId(), nsx.getMa(), nsx.getTen());
    }

}
