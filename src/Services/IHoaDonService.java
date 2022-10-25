/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ViewsModels.QLHoaDon;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHoaDonService {

    List<QLHoaDon> getallHoaDons();

    String saveHoaDon(QLHoaDon hoaDon);

    String updateHoaDon(QLHoaDon hoaDon);

    String deleteHoadon(String id);
}
