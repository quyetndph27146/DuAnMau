/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.SanPham;
import ViewsModels.QLSanPham;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISanPhamService {

    List<QLSanPham> getSanPhams();

    String createNewSanPham(QLSanPham sanPham);

    String deleteSanPham(String id);

    String updateSanPham(String ten, String id);

    QLSanPham laySanPhamTheoId(String id);

    QLSanPham laySanPhamTheoTen(String ten);
}
