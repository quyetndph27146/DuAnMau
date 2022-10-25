/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ViewsModels.QLMauSac;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IMauSacService {

    List<QLMauSac> getMauSacs();

    String createNewMauSac(QLMauSac mauSac);

    String deleteMauSac(String id);

    String updateMauSac(String ten, String id);

    QLMauSac layQLMauSacTheoId(String id);

    QLMauSac layQLMauSacTheoTen(String ten);
}
