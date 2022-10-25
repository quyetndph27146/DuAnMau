/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ViewsModels.QLNSX;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface INSXService {

    List<QLNSX> getNSXs();

    String createNewNSX(QLNSX nsx);

    String deleteNSX(String id);

    String updateNSX(String ten, String id);

    QLNSX layNSXTheoId(String id);

    QLNSX layNSXTheoTen(String ten);
}
