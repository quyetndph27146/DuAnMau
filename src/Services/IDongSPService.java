/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ViewsModels.QLDongSP;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IDongSPService {

    List<QLDongSP> getDongSP();

    String createNewDongSP(QLDongSP qLDongSP);

    String deleteDongSP(String id);

    String updateDongSP(String ten, String id);

    QLDongSP layDongSPTheoId(String id);

    QLDongSP layDongSPTheoTen(String ten);
}
