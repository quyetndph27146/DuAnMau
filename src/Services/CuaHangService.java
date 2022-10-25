/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.CuaHang;
import Repositories.CuaHangRepository;
import Repositories.ICuaHangRepository;
import ViewsModels.QLCuaHang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class CuaHangService implements ICuaHangService {

    private ICuaHangRepository iCuaHangRepository;
    private List<QLCuaHang> listQLCuaHangs;

    public CuaHangService() {
        iCuaHangRepository = new CuaHangRepository();
    }

    @Override
    public List<QLCuaHang> getAllCuaHang() {
        listQLCuaHangs = new ArrayList<>();
        List<CuaHang> listCuaHangs = new ArrayList<>();
        listCuaHangs = iCuaHangRepository.findAll();
        for (CuaHang ch : listCuaHangs) {
            listQLCuaHangs.add(new QLCuaHang(ch.getId(), ch.getMa(), ch.getTen(), ch.getDiaChi(), ch.getThanhPho(), ch.getQuocGia()));
        }
        return listQLCuaHangs;
    }

    @Override
    public String createNewCuaHang(QLCuaHang cuaHang) {
        if (iCuaHangRepository.saveCuaHang(new CuaHang(cuaHang.getId(), cuaHang.getMa(), cuaHang.getTen(), cuaHang.getDiaChi(), cuaHang.getThanhPho(), cuaHang.getQuocGia())) == true) {
            return "Thêm mới thành công";
        } else {
            return "Thêm mới thất bại";
        }
    }

    @Override
    public String deleteCuaHang(String id) {
        if (iCuaHangRepository.deleteCuaHang(id) == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String updateCuaHang(QLCuaHang cuaHang, String id) {
        if (iCuaHangRepository.updateCuaHang(new CuaHang(cuaHang.getId(), cuaHang.getMa(), cuaHang.getTen(), cuaHang.getDiaChi(), cuaHang.getThanhPho(), cuaHang.getQuocGia()), id) == true) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public QLCuaHang layCuaHangTheoID(String id) {
        CuaHang ch = iCuaHangRepository.layCuaHangTheoID(id);
        return new QLCuaHang(ch.getId(), ch.getMa(), ch.getTen(), ch.getDiaChi(), ch.getThanhPho(), ch.getQuocGia());
    }

    @Override
    public QLCuaHang layCuaHangTheoTen(String ten) {
        CuaHang ch = iCuaHangRepository.layCuaHangTheoTen(ten);
        return new QLCuaHang(ch.getId(), ch.getMa(), ch.getTen(), ch.getDiaChi(), ch.getThanhPho(), ch.getQuocGia());
    }

}
