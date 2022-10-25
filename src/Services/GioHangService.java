/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModels.GioHang;
import Repositories.GioHangRepository;
import Repositories.IGioHangRepository;
import ViewsModels.QLGioHang;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class GioHangService implements IGioHangService {
    
    IGioHangRepository iGioHangRepository;
    List<QLGioHang> listGioHangs;

    public GioHangService() {
        iGioHangRepository = new GioHangRepository();
        listGioHangs = new ArrayList<>();
    }
    
    @Override
    public List<QLGioHang> getAllGioHangs() {
        listGioHangs = new ArrayList<>();
        List<GioHang> list = iGioHangRepository.getAllGioHangs();
        for(GioHang gh:list){
            listGioHangs.add(new QLGioHang(gh.getId(),gh.getKhachHang(), gh.getNhanVien(), gh.getMa(),gh.getNgayTao(),gh.getNgayThanhToan()
                    ,gh.getTenNguoiNhan(),gh.getDiaChi(),gh.getSdt(), gh.getTinhTrang()));
        }
        return listGioHangs;
    }

    @Override
    public String saveGioHang(QLGioHang gh) {
        boolean check = iGioHangRepository.saveGioHang(new GioHang(gh.getId(), gh.getKhachHang(), gh.getNhanVien(), gh.getMa(), gh.getNgayTao(),gh.getNgayThanhToan(),gh.getTenNguoiNhan(),gh.getDiaChi(), gh.getSdt(),gh.getTinhTrang()));
        if(check == true){
            return "Thêm mới thành công";
        }else{
            return "Thêm mới thất bại";
        }
    }

    @Override
    public String updateGioHang(QLGioHang gh) {
        boolean check = iGioHangRepository.updateGioHang(new GioHang(gh.getId(), gh.getKhachHang(), gh.getNhanVien(), gh.getMa(), gh.getNgayTao(),gh.getNgayThanhToan(),gh.getTenNguoiNhan(),gh.getDiaChi(), gh.getSdt(),gh.getTinhTrang()));
        if(check == true){
            return "Sửa thành công";
        }else{
            return "Sửa thất bại";
        }
    }

    @Override
    public String deleteGioHang(String id) {
        boolean check = iGioHangRepository.deleteGioHang(id);
        if(check == true){
            return "Xóa thành công";
        }else{
            return "Xóa thất bại";
        }
    }

}
