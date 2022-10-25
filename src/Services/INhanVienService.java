/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ViewsModels.QLNhanVien;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface INhanVienService {

    List<QLNhanVien> getAllLNhanViens();

    String createNewNhanVien(QLNhanVien nv);

    String deleteNhanVien(String id);

    String updateNhanVien(QLNhanVien nv, String id);
}
