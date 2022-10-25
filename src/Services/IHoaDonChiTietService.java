/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ViewsModels.QLHoaDonChiTiet;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHoaDonChiTietService {

    List<QLHoaDonChiTiet> getAllHoaDonChiTiet();

    String saveHoaDonChiTiet(QLHoaDonChiTiet hdct);

    String updateHoaDonChiTiet(QLHoaDonChiTiet hdct);

    String deleteHoaDonChiTiet(String id, String idChiTietSP);
}
