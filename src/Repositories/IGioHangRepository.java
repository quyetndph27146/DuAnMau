/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.GioHang;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IGioHangRepository {

    List<GioHang> getAllGioHangs();

    boolean saveGioHang(GioHang gh);

    boolean updateGioHang(GioHang gh);

    boolean deleteGioHang(String id);
    
    GioHang getGioHangTheoId(String id);
    
    GioHang getGioHangTheoMa(String ma);
}
