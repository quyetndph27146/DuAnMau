/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.NhanVien;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface INhanVienRepository {

    List<NhanVien> findAll();

    boolean saveNhanVien(NhanVien nv);

    boolean deleteNhanVien(String id);

    boolean updateNhanVien(NhanVien nv, String id);

    NhanVien layNhanVienTheoId(String id);

    NhanVien layNhanVienTheoName(String ten);
}
