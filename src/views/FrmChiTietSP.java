/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import DomainModels.DongSP;
import DomainModels.MauSac;
import DomainModels.NSX;
import DomainModels.SanPham;
import Services.ChiTietSPService;
import Services.DongSPService;
import Services.IChiTietSPService;
import Services.IDongSPService;
import Services.IMauSacService;
import Services.INSXService;
import Services.ISanPhamService;
import Services.MauSacService;
import Services.NSXService;
import Services.SanPhamService;
import ViewsModels.QLChiTietSP;
import ViewsModels.QLDongSP;
import ViewsModels.QLMauSac;
import ViewsModels.QLNSX;
import ViewsModels.QLSanPham;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class FrmChiTietSP extends javax.swing.JFrame {

    /**
     * Creates new form FrmChiTietSP
     */
    ISanPhamService iSanPhamService;
    INSXService iNSXService;
    IMauSacService iMauSacService;
    IDongSPService iDongSPService;
    IChiTietSPService iChiTietSPService;
    DefaultTableModel model;
    DefaultComboBoxModel cbx;
    private int totalpages;
    private int totalChiTietSP;
    private int cureentPage;
    private final int pageSize;

    public FrmChiTietSP() {
        initComponents();
        setLocationRelativeTo(this);
        model = (DefaultTableModel) tblChiTietSP.getModel();
        iSanPhamService = new SanPhamService();
        iNSXService = new NSXService();
        iMauSacService = new MauSacService();
        iDongSPService = new DongSPService();
        iChiTietSPService = new ChiTietSPService();
        cureentPage = 1;
        pageSize = 5;
        getAllSanPham();
        getAllNSX();
        getAllMauSac();
        getAllDongSP();
        loadTable(getCurentPage());
    }

    private void getAllSanPham() {
        cbx = (DefaultComboBoxModel) cbxSP.getModel();
        cbx.removeAllElements();
        List<QLSanPham> list = iSanPhamService.getSanPhams();
        for (QLSanPham sp : list) {
            cbx.addElement(sp.getTen());
        }
    }

    private void getAllNSX() {
        cbx = (DefaultComboBoxModel) cbxNSX.getModel();
        cbx.removeAllElements();
        List<QLNSX> list = iNSXService.getNSXs();
        for (QLNSX nsx : list) {
            cbx.addElement(nsx.getTen());
        }
    }

    private void getAllMauSac() {
        cbx = (DefaultComboBoxModel) cbxMauSac.getModel();
        cbx.removeAllElements();
        List<QLMauSac> list = iMauSacService.getMauSacs();
        for (QLMauSac ms : list) {
            cbx.addElement(ms.getTen());
        }
    }

    private void getAllDongSP() {
        cbx = (DefaultComboBoxModel) cbxDongSP.getModel();
        cbx.removeAllElements();
        List<QLDongSP> list = iDongSPService.getDongSP();
        for (QLDongSP dongSP : list) {
            cbx.addElement(dongSP.getTen());
        }
    }

    private void loadTable(List<QLChiTietSP> list) {
        model.setRowCount(0);
        for (QLChiTietSP sP : list) {
            model.addRow(new Object[]{sP.getId(), sP.getSanPham().getTen(), sP.getNsx().getTen(), sP.getMauSac().getTen(), sP.getDongSP().getTen(),
                sP.getNamBH(), sP.getMoTa(), sP.getSoLuongTon(), sP.getGiaNhap(), sP.getGiaBan()});
        }
    }

    private QLChiTietSP validateChiTietSP() {
        String id = "";
        String tenSP = (String) cbxSP.getSelectedItem();
        QLSanPham qlSanPham = iSanPhamService.laySanPhamTheoTen(tenSP);
        SanPham sanPham = new SanPham(qlSanPham.getId(), qlSanPham.getMa(), qlSanPham.getTen());
        String tenNSX = (String) cbxNSX.getSelectedItem();
        QLNSX qlnsx = iNSXService.layNSXTheoTen(tenNSX);
        NSX nsx = new NSX(qlnsx.getId(), qlnsx.getMa(), qlnsx.getTen());
        String tenMauSac = (String) cbxMauSac.getSelectedItem();
        QLMauSac qLMauSac = iMauSacService.layQLMauSacTheoTen(tenMauSac);
        MauSac ms = new MauSac(qLMauSac.getId(), qLMauSac.getMa(), qLMauSac.getTen());
        String tenDongSP = (String) cbxDongSP.getSelectedItem();
        QLDongSP qLDongSP = iDongSPService.layDongSPTheoTen(tenDongSP);
        DongSP dongSP = new DongSP(qLDongSP.getId(), qLDongSP.getMa(), qLDongSP.getTen());
        String namBHString = txtNamBH.getText();
        int namBH = 0;
        if (namBHString.isBlank()) {
            JOptionPane.showMessageDialog(this, "Không được để trỗng năm BH");
            return null;
        } else {
            try {
                namBH = Integer.valueOf(namBHString);
                if (namBH <= 0) {
                    JOptionPane.showMessageDialog(this, "NamBH không được <= 0");
                    return null;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "NamBH Kiểu dữ liệu int,bạn nhập sai kiểu dữ liệu");
                return null;
            }
        }
        String moTa = txtMoTa.getText();
        if (moTa.isBlank()) {
            JOptionPane.showMessageDialog(this, "Mô tả không được để trỗng");
            return null;
        }
        String soLuongTon = txtSoLuongTon.getText();
        int slTon = 0;
        if (soLuongTon.isBlank()) {
            JOptionPane.showMessageDialog(this, "Không được để trỗng Số lượng tồn");
            return null;
        } else {
            try {
                slTon = Integer.valueOf(soLuongTon);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "soLuongTon Kiểu dữ liệu int,bạn nhập sai kiểu dữ liệu");
                return null;
            }
        }
        String giaNhapString = txtGiaNhap.getText();
        float giaNhap = 0;
        if (giaNhapString.isBlank()) {
            JOptionPane.showMessageDialog(this, "Không được để trỗng Giá nhập");
            return null;
        } else {
            try {
                giaNhap = Float.valueOf(giaNhapString);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "giaNhap Kiểu dữ liệu số,bạn nhập sai kiểu dữ liệu");
                return null;
            }
        }
        String giaBanString = txtGiaBan.getText();
        float giaBan = 0;
        if (giaBanString.isBlank()) {
            JOptionPane.showMessageDialog(this, "Không được để trỗng Giá Bán");
            return null;
        } else {
            try {
                giaBan = Float.valueOf(giaBanString);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "giaBan Kiểu dữ liệu số,bạn nhập sai kiểu dữ liệu");
                return null;
            }
        }
        return new QLChiTietSP(id, sanPham, nsx, ms, dongSP, namBH, moTa, slTon, giaNhap, giaBan);
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
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxMauSac = new javax.swing.JComboBox<>();
        cbxSP = new javax.swing.JComboBox<>();
        cbxNSX = new javax.swing.JComboBox<>();
        cbxDongSP = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSoLuongTon = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtMoTa = new javax.swing.JTextField();
        txtNamBH = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietSP = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClearForm = new javax.swing.JButton();
        btnTruoc = new javax.swing.JButton();
        btnSau = new javax.swing.JButton();
        jlbCurrentpage = new javax.swing.JLabel();
        jlbTotalChiTietSP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID:");

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("SP:");

        jLabel3.setText("NSX:");

        jLabel4.setText("Màu sắc:");

        jLabel5.setText("Dòng SP:");

        cbxMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxNSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxDongSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Năm BH:");

        jLabel7.setText("Mô tả:");

        jLabel8.setText("Số lượng tồn:");

        jLabel9.setText("Giá nhập:");

        jLabel10.setText("Giá bán:");

        tblChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "SP", "NSX", "Màu sắc", "Dòng SP", "Năm BH", "Mô tả", "Số lượng tồn", "Giá nhập", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTietSP);

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

        btnClearForm.setText("Clear Form");
        btnClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFormActionPerformed(evt);
            }
        });

        btnTruoc.setText("<");
        btnTruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTruocActionPerformed(evt);
            }
        });

        btnSau.setText(">");
        btnSau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSauActionPerformed(evt);
            }
        });

        jlbCurrentpage.setText("1/1");

        jlbTotalChiTietSP.setText("Total  ChitietSP:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxSP, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(84, 84, 84)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbxNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbxMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(60, 60, 60)
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClearForm))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(btnTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jlbCurrentpage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnSau, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jlbTotalChiTietSP)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cbxNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbxDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnClearForm))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTruoc)
                    .addComponent(btnSau)
                    .addComponent(jlbCurrentpage)
                    .addComponent(jlbTotalChiTietSP))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        QLChiTietSP qLChiTietSP = validateChiTietSP();
        if (qLChiTietSP != null) {
            JOptionPane.showMessageDialog(this, iChiTietSPService.createChiTietSP(qLChiTietSP));
            loadTable(getCurentPage());
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int selectRow = tblChiTietSP.getSelectedRow();
        if (selectRow < 0) {
            JOptionPane.showMessageDialog(this, "Bạn Cần chọn 1 dòng trên bảng");
            return;
        }
        String id = tblChiTietSP.getValueAt(selectRow, 0).toString();
        QLChiTietSP qlctsp = validateChiTietSP();
        if (qlctsp != null) {
            JOptionPane.showMessageDialog(this, iChiTietSPService.updateChiTietSP(qlctsp, id));
            loadTable(getCurentPage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblChiTietSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSPMouseClicked
        // TODO add your handling code here:
        int selectRow = tblChiTietSP.getSelectedRow();
        txtId.setText(tblChiTietSP.getValueAt(selectRow, 0).toString());
        cbxSP.setSelectedItem(tblChiTietSP.getValueAt(selectRow, 1).toString());
        cbxNSX.setSelectedItem(tblChiTietSP.getValueAt(selectRow, 2).toString());
        cbxMauSac.setSelectedItem(tblChiTietSP.getValueAt(selectRow, 3).toString());
        cbxDongSP.setSelectedItem(tblChiTietSP.getValueAt(selectRow, 4).toString());
        txtNamBH.setText(tblChiTietSP.getValueAt(selectRow, 5).toString());
        txtMoTa.setText(tblChiTietSP.getValueAt(selectRow, 6).toString());
        txtSoLuongTon.setText(tblChiTietSP.getValueAt(selectRow, 7).toString());
        txtGiaNhap.setText(tblChiTietSP.getValueAt(selectRow, 8).toString());
        txtGiaBan.setText(tblChiTietSP.getValueAt(selectRow, 9).toString());
    }//GEN-LAST:event_tblChiTietSPMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int selectRow = tblChiTietSP.getSelectedRow();
        if (selectRow < 0) {
            JOptionPane.showMessageDialog(this, "Bạn Cần chọn 1 dòng trên bảng");
            return;
        }
        String id = tblChiTietSP.getValueAt(selectRow, 0).toString();
        int result = JOptionPane.showConfirmDialog(this, "Xóa Nhân viên", "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, iChiTietSPService.deleteChiTietSP(id));
            loadTable(getCurentPage());
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFormActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnClearFormActionPerformed

    private void btnTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTruocActionPerformed
        // TODO add your handling code here:
        cureentPage--;
        if (cureentPage == 0) {
            cureentPage = totalpages;
        }
        loadTable(getCurentPage());
    }//GEN-LAST:event_btnTruocActionPerformed

    private void btnSauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSauActionPerformed
        // TODO add your handling code here:
        cureentPage++;
        if (cureentPage > totalpages) {
            cureentPage = 1;
        }
        loadTable(getCurentPage());
    }//GEN-LAST:event_btnSauActionPerformed

    private void clearForm() {
        txtId.setText("");
        cbxSP.setSelectedIndex(0);
        cbxNSX.setSelectedIndex(0);
        cbxMauSac.setSelectedIndex(0);
        cbxDongSP.setSelectedIndex(0);
        txtNamBH.setText("");
        txtMoTa.setText("");
        txtSoLuongTon.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
    }

    private List<QLChiTietSP> getCurentPage() {
        totalChiTietSP = iChiTietSPService.getAllChiTietSPs().size();
        totalpages = (totalChiTietSP - 1) / pageSize + 1;
        jlbTotalChiTietSP.setText("total chiTietSP:" + totalChiTietSP);
        jlbCurrentpage.setText(cureentPage + "/" + totalpages);
        int start = (cureentPage - 1) * pageSize;
        int end = cureentPage * pageSize;
        if (totalChiTietSP < end) {
            end = totalChiTietSP;
        }
        return iChiTietSPService.getAllChiTietSPs().subList(start, end);
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmChiTietSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmChiTietSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmChiTietSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmChiTietSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmChiTietSP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearForm;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSau;
    private javax.swing.JButton btnTruoc;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbxDongSP;
    private javax.swing.JComboBox<String> cbxMauSac;
    private javax.swing.JComboBox<String> cbxNSX;
    private javax.swing.JComboBox<String> cbxSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbCurrentpage;
    private javax.swing.JLabel jlbTotalChiTietSP;
    private javax.swing.JTable tblChiTietSP;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtNamBH;
    private javax.swing.JTextField txtSoLuongTon;
    // End of variables declaration//GEN-END:variables
}
