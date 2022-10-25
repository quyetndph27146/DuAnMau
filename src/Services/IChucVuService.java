/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.ChucVu;
import ViewsModels.QLChucVu;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChucVuService {

    List<QLChucVu> getChucVus();

    String createNewChucVu(QLChucVu qLChucVu);

    String deleteChucVu(String id);

    String updateChucVu(String ten, String id);

    QLChucVu layQLChucVuTheoId(String id);

    QLChucVu layQLChucVuTheoTen(String ten);
}
