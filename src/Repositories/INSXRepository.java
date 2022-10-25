/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.NSX;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface INSXRepository {

    List<NSX> findAll();

    boolean save(NSX nsx);

    boolean delete(String id);

    boolean update(String ten, String id);

    NSX layNSXTheoId(String id);

    NSX layNSXTheoTen(String ten);
}
