/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.DongSP;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IDongSPRepository {

    List<DongSP> findAll();

    boolean save(DongSP dongSP);

    boolean delete(String id);

    boolean update(String ten, String id);

    DongSP layDongSPTheoId(String id);

    DongSP layDongSPTheoTen(String ten);
}
