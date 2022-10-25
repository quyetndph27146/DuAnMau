/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.HoaDonChiTiet;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHoaDonChiTietRepository {

    List<HoaDonChiTiet> getAllHoaDonChiTiet();

    boolean saveHoaDonChiTiet(HoaDonChiTiet hdct);

    boolean updateHoaDonChiTiet(HoaDonChiTiet hdct);

    boolean deleteHoaDonChiTiet(String id,String idChiTietSP);
}
