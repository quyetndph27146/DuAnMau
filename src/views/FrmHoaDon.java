/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import DomainModels.KhachHang;
import DomainModels.NhanVien;
import Repositories.IKhachHangRepository;
import Repositories.INhanVienRepository;
import Repositories.KhachHangRepository;
import Repositories.NhanVienRepository;
import Services.HoaDonService;
import Services.IHoaDonService;
import ViewsModels.QLHoaDon;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class FrmHoaDon extends javax.swing.JFrame {

    /**
     * Creates new form FrmHoaDon
     */
    DefaultComboBoxModel<String> cbBoxModel;
    DefaultTableModel model;
    private IKhachHangRepository khachHangRepository;
    private INhanVienRepository nhanVienRepository;
    private IHoaDonService iHoaDonService;
    private int totalPage;
    private int curentPage;
    private final int pageSize;
    private int totalHoaDon;

    public FrmHoaDon() {
        initComponents();
        setLocationRelativeTo(this);
        khachHangRepository = new KhachHangRepository();
        nhanVienRepository = new NhanVienRepository();
        getKhachHang();
        getNhanVien();
        cbxTinhTrang.removeAllItems();
        cbxTinhTrang.addItem("đã thanh toán");
        cbxTinhTrang.addItem("đã hủy");
        cbxTinhTrang.addItem("chờ thanh toán");
        model = (DefaultTableModel) tblHoaDon.getModel();
        pageSize = 5;
        curentPage = 1;
        iHoaDonService = new HoaDonService();
        loadTable(iHoaDonService.getallHoaDons());
    }

    private void getKhachHang() {
        cbBoxModel = (DefaultComboBoxModel<String>) cbxKhachHang.getModel();
        cbBoxModel.removeAllElements();
        List<KhachHang> listKhachHangs = khachHangRepository.findAll();
        for (KhachHang kh : listKhachHangs) {
            cbBoxModel.addElement(kh.getTen());
        }
    }

    private void getNhanVien() {
        cbBoxModel = (DefaultComboBoxModel<String>) cbxNhanVien.getModel();
        cbBoxModel.removeAllElements();
        List<NhanVien> listNhanViens = nhanVienRepository.findAll();
        for (NhanVien nv : listNhanViens) {
            cbBoxModel.addElement(nv.getTen());
        }
    }

    private void loadTable(List<QLHoaDon> hoaDon) {
        model.setRowCount(0);
        for (QLHoaDon hd : hoaDon) {
            model.addRow(new Object[]{hd.getId(), hd.getKhachHang().getTen(), hd.getNhanVien().getTen(), hd.getMa(), hd.getNgayTao(), hd.getNgayThanhToan(),
                 hd.getNgayShip(), hd.getNgayNhan(), hd.getTinhTrangString(), hd.getTenNguoiNhan(), hd.getDiaChi(), hd.getSdt()});
        }
    }

    private QLHoaDon validateHoaDon() {
        String id = txtId.getText();
        String kh = (String) cbxKhachHang.getSelectedItem();
        KhachHang khachHang = khachHangRepository.getKhachHangTheoName(kh);
        String nv = (String) cbxNhanVien.getSelectedItem();
        NhanVien nhanVien = nhanVienRepository.layNhanVienTheoName(nv);
        String ma = txtma.getText();
        if (ma.isBlank()) {
            JOptionPane.showMessageDialog(this, "mã không được để trống");
            return null;
        }
        String ngayTao = txtNgayTao.getText();
        if (ngayTao.isBlank()) {
            JOptionPane.showMessageDialog(this, "Ngày tạo không được để trống");
            return null;
        }
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
        Date ngayTaoDate = null;
        try {
            ngayTaoDate = (Date) dtf.parse(ngayTao);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "lỗi định dạng ngày");
            return null;
        }

        String ngayThanhToan = txtNgayThanhToan.getText();
        if (ngayThanhToan.isBlank()) {
            JOptionPane.showMessageDialog(this, "Ngày thanh toán không được để trống");
            return null;
        }
        Date ngayThanhToandaDate = null;
        try {
            ngayThanhToandaDate = (Date) dtf.parse(ngayThanhToan);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "lỗi định dạng ngày");
            return null;
        }

        String ngayShip = txtNgayShip.getText();
        if (ngayShip.isBlank()) {
            JOptionPane.showMessageDialog(this, "Ngày ship không được để trống");
            return null;
        }
        Date ngayShipdDate = null;
        try {
            ngayShipdDate = (Date) dtf.parse(ngayShip);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "lỗi định dạng ngày");
            return null;
        }

        String ngayNhan = txtNgayNhan.getText();
        if (ngayNhan.isBlank()) {
            JOptionPane.showMessageDialog(this, "Ngày nhận không được để trống");
            return null;
        }
        Date ngaynhanDate = null;
        try {
            ngaynhanDate = (Date) dtf.parse(ngayNhan);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "lỗi định dạng ngày");
            return null;
        }

        String tinhtrangString = (String) cbxTinhTrang.getSelectedItem();
        int tinhTrang;
        if (tinhtrangString.equalsIgnoreCase("đã thanh toán")) {
            tinhTrang = 1;
        } else if (tinhtrangString.equalsIgnoreCase("đã hủy")) {
            tinhTrang = 0;
        } else {
            tinhTrang = 2;
        }

        String tenNguoiNhan = txtTenNguoiNhan.getText();
        if (tenNguoiNhan.isBlank()) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên người nhận");
            return null;
        }
        String diaChi = txtDiaChi.getText();
        if (diaChi.isBlank()) {
            JOptionPane.showMessageDialog(this, "Không được để trống địa chỉ");
            return null;
        }
        String sdt = txtSDT.getText();
        if (sdt.isBlank()) {
            JOptionPane.showMessageDialog(this, "Không được để trống sđt");
            return null;
        }

        return new QLHoaDon(id, khachHang, nhanVien, ma, ngayTaoDate, ngaynhanDate, ngayShipdDate, ngaynhanDate, tinhTrang, tenNguoiNhan, diaChi, sdt);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        txtNgayThanhToan = new javax.swing.JTextField();
        txtma = new javax.swing.JTextField();
        txtNgayShip = new javax.swing.JTextField();
        txtNgayNhan = new javax.swing.JTextField();
        txtTenNguoiNhan = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        cbxTinhTrang = new javax.swing.JComboBox<>();
        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        cbxNhanVien = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbxKhachHang = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btntruoc = new javax.swing.JButton();
        btnsau = new javax.swing.JButton();
        jlbcurrenPage = new javax.swing.JLabel();
        jlbtotalHoadon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Mã:");

        jLabel2.setText("Ngày tạo:");

        jLabel3.setText("Ngày thanh toán:");

        jLabel4.setText("Ngày ship:");

        jLabel5.setText("Ngày nhận:");

        jLabel6.setText("Tình Trạng:");

        jLabel7.setText("Tên người nhận:");

        jLabel8.setText("SĐT:");

        jLabel9.setText("Địa chỉ:");

        cbxTinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "TenKH", "TenNV", "Mã", "Ngày tạo", "Ngày thanh toán", "Ngày ship", "Ngày nhận", "Tình trạng", "Tên người nhận", "Địa chỉ", "SĐT"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        jLabel10.setText("Nhân viên:");

        cbxNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Khách hàng:");

        cbxKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Id:");

        txtId.setEditable(false);

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btntruoc.setText("<");
        btntruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntruocActionPerformed(evt);
            }
        });

        btnsau.setText(">");
        btnsau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsauActionPerformed(evt);
            }
        });

        jlbcurrenPage.setText("1/1");

        jlbtotalHoadon.setText("total HoaDon:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(42, 42, 42)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel10))
                            .addGap(38, 38, 38)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtma)
                    .addComponent(txtNgayTao)
                    .addComponent(cbxKhachHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtId)
                    .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(25, 25, 25)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayShip, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btntruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jlbcurrenPage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnsau, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(jlbtotalHoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNgayShip, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNgayNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cbxKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(cbxNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnClear))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntruoc)
                    .addComponent(btnsau)
                    .addComponent(jlbcurrenPage)
                    .addComponent(jlbtotalHoadon))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        QLHoaDon hoaDon = validateHoaDon();
        if (hoaDon != null) {
            JOptionPane.showMessageDialog(this, iHoaDonService.saveHoaDon(hoaDon));
            loadTable(getCurrentPage());
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int selectRow = tblHoaDon.getSelectedRow();
        if (selectRow < 0) {
            JOptionPane.showMessageDialog(this, "chọn 1 dòng trên table");
            return;
        }
        QLHoaDon qlhd = validateHoaDon();
        JOptionPane.showMessageDialog(this, iHoaDonService.updateHoaDon(qlhd));
        loadTable(getCurrentPage());
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int selectRow = tblHoaDon.getSelectedRow();
        txtId.setText(tblHoaDon.getValueAt(selectRow, 0).toString());
        cbxKhachHang.setSelectedItem(tblHoaDon.getValueAt(selectRow, 1));
        cbxNhanVien.setSelectedItem(tblHoaDon.getValueAt(selectRow, 2));
        txtma.setText(tblHoaDon.getValueAt(selectRow, 3).toString());
        txtNgayTao.setText(tblHoaDon.getValueAt(selectRow, 4).toString());
        txtNgayThanhToan.setText(tblHoaDon.getValueAt(selectRow, 5).toString());
        txtNgayShip.setText(tblHoaDon.getValueAt(selectRow, 6).toString());
        txtNgayNhan.setText(tblHoaDon.getValueAt(selectRow, 7).toString());
        cbxTinhTrang.setSelectedItem(tblHoaDon.getValueAt(selectRow, 8));
        txtTenNguoiNhan.setText(tblHoaDon.getValueAt(selectRow, 9).toString());
        txtDiaChi.setText(tblHoaDon.getValueAt(selectRow, 10).toString());
        txtSDT.setText(tblHoaDon.getValueAt(selectRow, 11).toString());
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int selectRow = tblHoaDon.getSelectedRow();
        if (selectRow < 0) {
            JOptionPane.showMessageDialog(this, "chọn 1 dòng trên table");
            return;
        }
        String id = txtId.getText();
        int kq = JOptionPane.showConfirmDialog(this, "bạn có muốn xóa không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (kq == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, iHoaDonService.deleteHoadon(id));
            loadTable(getCurrentPage());
            clearform();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa Thất Bại");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clearform();

    }//GEN-LAST:event_btnClearActionPerformed

    private void btntruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntruocActionPerformed
        // TODO add your handling code here:
        curentPage--;
        if (curentPage == 0) {
            curentPage = totalPage;
        }
        loadTable(getCurrentPage());
    }//GEN-LAST:event_btntruocActionPerformed

    private void btnsauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsauActionPerformed
        // TODO add your handling code here:
        curentPage++;
        if (curentPage > totalPage) {
            curentPage = 1;
        }
        loadTable(getCurrentPage());
    }//GEN-LAST:event_btnsauActionPerformed

    private List<QLHoaDon> getCurrentPage() {
        totalHoaDon = iHoaDonService.getallHoaDons().size();
        totalPage = (totalHoaDon - 1) / pageSize + 1;
        jlbtotalHoadon.setText("Total HoaDon:" + totalHoaDon);
        jlbcurrenPage.setText(curentPage + "/" + totalPage);
        int start = (curentPage - 1) * pageSize;
        int end = curentPage * pageSize;
        if (end > totalHoaDon) {
            end = totalHoaDon;
        }
        return iHoaDonService.getallHoaDons().subList(start, end);
    }

    private void clearform() {
        txtId.setText("");
        cbxKhachHang.setSelectedIndex(0);
        cbxNhanVien.setSelectedIndex(0);
        txtma.setText("");
        txtNgayTao.setText("");
        txtNgayThanhToan.setText("");
        txtNgayShip.setText("");
        txtNgayNhan.setText("");
        cbxTinhTrang.setSelectedIndex(0);
        txtTenNguoiNhan.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnsau;
    private javax.swing.JButton btntruoc;
    private javax.swing.JComboBox<String> cbxKhachHang;
    private javax.swing.JComboBox<String> cbxNhanVien;
    private javax.swing.JComboBox<String> cbxTinhTrang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbcurrenPage;
    private javax.swing.JLabel jlbtotalHoadon;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNgayNhan;
    private javax.swing.JTextField txtNgayShip;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtNgayThanhToan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNguoiNhan;
    private javax.swing.JTextField txtma;
    // End of variables declaration//GEN-END:variables
}
