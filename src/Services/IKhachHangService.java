/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ViewsModels.QLKhachHang;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IKhachHangService {

    List<QLKhachHang> getAllKhachHang();

    String createNewKhachHang(QLKhachHang kh);

    String deleteKhachHang(String id);

    String updateKhachHang(QLKhachHang kh, String id);
}
