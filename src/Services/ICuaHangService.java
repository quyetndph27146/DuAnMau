/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.CuaHang;
import ViewsModels.QLCuaHang;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ICuaHangService {

    List<QLCuaHang> getAllCuaHang();

    String createNewCuaHang(QLCuaHang cuaHang);

    String deleteCuaHang(String id);

    String updateCuaHang(QLCuaHang cuaHang, String id);

    QLCuaHang layCuaHangTheoID(String id);

    QLCuaHang layCuaHangTheoTen(String ten);
}
