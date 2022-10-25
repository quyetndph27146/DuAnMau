/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.KhachHang;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IKhachHangRepository {

    List<KhachHang> findAll();

    boolean saveKhachHang(KhachHang kh);

    boolean deleteKhachHang(String id);

    boolean updateKhachHang(KhachHang kh, String id);

    KhachHang getKhachHangTheoId(String id);

    KhachHang getKhachHangTheoName(String ten);
}
