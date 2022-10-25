/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.ChucVu;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChucVuRepository {

    List<ChucVu> findAll();

    boolean save(ChucVu chucVu);

    boolean delete(String id);

    boolean update(String ten, String id);

    ChucVu layChucVuTheoId(String id);

    ChucVu layChucVuTheoTen(String ten);
}
