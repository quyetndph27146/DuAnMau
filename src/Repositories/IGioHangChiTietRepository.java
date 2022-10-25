/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.GioHangChiTiet;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IGioHangChiTietRepository {
    List<GioHangChiTiet> getALllGioHangChitiets();
    
    boolean saveGioHangCT(GioHangChiTiet ghct);
    
    boolean updateGioHangCT(GioHangChiTiet ghct);
    
    boolean deleteGioHangCT(String idGH,String idChiTietSP);
}
