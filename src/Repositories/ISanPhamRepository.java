/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.SanPham;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISanPhamRepository {

    List<SanPham> findAll();

    boolean save(SanPham sanPham);

    boolean delete(String id);

    boolean update(String ten, String id);

    SanPham laySanPhamTheoId(String id);

    SanPham laySanPhamTheoTen(String ten);
}
