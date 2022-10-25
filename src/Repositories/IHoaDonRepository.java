/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.HoaDon;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHoaDonRepository {

    List<HoaDon> getAllHoasDons();

    boolean saveHoaDon(HoaDon hd);

    boolean updateHoaDon(HoaDon hd);

    boolean deleteHoaDon(String id);

    HoaDon getHoaDonTheoId(String id);

    HoaDon getHoaDonTheoMa(String ma);

}
