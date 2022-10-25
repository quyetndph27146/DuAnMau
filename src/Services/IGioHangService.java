/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ViewsModels.QLGioHang;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IGioHangService {

    List<QLGioHang> getAllGioHangs();

    String saveGioHang(QLGioHang gh);

    String updateGioHang(QLGioHang gh);

    String deleteGioHang(String id);
}
