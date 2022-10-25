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
public class GioHangChiTiet {

    private GioHang gioHang;
    private ChiTietSP chiTietSP;
    private int soLuong;
    private float donGia;
    private float donGiaKhiGiam;

    public GioHangChiTiet() {
    }

    public GioHangChiTiet(GioHang gioHang, ChiTietSP chiTietSP, int soLuong, float donGia, float donGiaKhiGiam) {
        this.gioHang = gioHang;
        this.chiTietSP = chiTietSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.donGiaKhiGiam = donGiaKhiGiam;
    }

    public GioHang getGioHang() {
        return gioHang;
    }

    public void setGioHang(GioHang gioHang) {
        this.gioHang = gioHang;
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

    public float getDonGiaKhiGiam() {
        return donGiaKhiGiam;
    }

    public void setDonGiaKhiGiam(float donGiaKhiGiam) {
        this.donGiaKhiGiam = donGiaKhiGiam;
    }

    
}
