/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomainModels;

/**
 *
 * @author Admin
 */
public class HoaDonChiTiet {

    private HoaDon hoaDon;
    private ChiTietSP chiTietSP;
    private int soLuong;
    private float donGia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(HoaDon hoaDon, ChiTietSP chiTietSP, int soLuong, float donGia) {
        this.hoaDon = hoaDon;
        this.chiTietSP = chiTietSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ChiTietSP getChiTietSP() {
        return chiTietSP;
    }

    public void setChiTietSP(ChiTietSP chiTietSP) {
        this.chiTietSP = chiTietSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    
}
