/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.CuaHang;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ICuaHangRepository {

    List<CuaHang> findAll();

    boolean saveCuaHang(CuaHang cuaHang);

    boolean deleteCuaHang(String id);

    boolean updateCuaHang(CuaHang cuaHang, String id);

    CuaHang layCuaHangTheoID(String id);

    CuaHang layCuaHangTheoTen(String ten);
}
