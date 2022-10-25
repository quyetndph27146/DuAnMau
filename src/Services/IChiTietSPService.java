/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Repositories.IChitietSPRepository;
import ViewsModels.QLChiTietSP;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChiTietSPService {

    List<QLChiTietSP> getAllChiTietSPs();

    String createChiTietSP(QLChiTietSP chiTietSP);

    String updateChiTietSP(QLChiTietSP qLChiTietSP, String id);

    String deleteChiTietSP(String id);
}
