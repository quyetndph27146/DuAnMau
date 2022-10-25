/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.ChiTietSP;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChitietSPRepository {

    List<ChiTietSP> getAllChiTietSPs();

    boolean saveChiTietSP(ChiTietSP chiTietSP);

    boolean updateChiTietSP(ChiTietSP chiTietSP, String id);

    boolean deleteChiTietSP(String id);

    ChiTietSP getChiTietSPtheoId(String id);

    ChiTietSP getChiTietSPTheoTen(String ten);

    List<String> getAllTen();
    
    String getTen(String id);

}
