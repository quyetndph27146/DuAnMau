/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ViewsModels.QLGioHangChiTiet;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IGioHangChiTietService {
    List<QLGioHangChiTiet> getAllGioHangChiTiet();

    String saveGioHangChiTiet(QLGioHangChiTiet ghct);

    String updateGioHangChiTiet(QLGioHangChiTiet ghct);

    String deleteGioHangChiTiet(String id, String idChiTietSP);
}
