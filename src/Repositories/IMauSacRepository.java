/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.MauSac;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IMauSacRepository {

    List<MauSac> findAll();

    boolean save(MauSac mauSac);

    boolean delete(String id);

    boolean update(String ten, String id);

    MauSac layMauSacTheoId(String id);

    MauSac layMauSacTheoTen(String ten);
}
