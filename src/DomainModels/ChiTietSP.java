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
public class ChiTietSP {

    private String id;
    private SanPham sanPham;
    private NSX nsx;
    private MauSac mauSac;
    private DongSP dongSP;
    private int namBH;
    private String moTa;
    private int soLuongTon;
    private float giaNhap;
    private float giaBan;

    public ChiTietSP() {
    }

    public ChiTietSP(String id, SanPham sanPham, NSX nsx, MauSac mauSac, DongSP dongSP, int namBH, String moTa, int soLuongTon, float giaNhap, float giaBan) {
        this.id = id;
        this.sanPham = sanPham;
        this.nsx = nsx;
        this.mauSac = mauSac;
        this.dongSP = dongSP;
        this.namBH = namBH;
        this.moTa = moTa;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public NSX getNsx() {
        return nsx;
    }

    public void setNsx(NSX nsx) {
        this.nsx = nsx;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public DongSP getDongSP() {
        return dongSP;
    }

    public void setDongSP(DongSP dongSP) {
        this.dongSP = dongSP;
    }

    public int getNamBH() {
        return namBH;
    }

    public void setNamBH(int namBH) {
        this.namBH = namBH;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

}
