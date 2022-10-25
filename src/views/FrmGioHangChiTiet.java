/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import DomainModels.ChiTietSP;
import DomainModels.GioHang;
import Repositories.ChiTietSPRepository;
import Repositories.GioHangRepository;
import Repositories.IChitietSPRepository;
import Repositories.IGioHangRepository;
import Services.GioHangChiTietService;
import Services.IGioHangChiTietService;
import ViewsModels.QLGioHangChiTiet;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class FrmGioHangChiTiet extends javax.swing.JFrame {

    /**
     * Creates new form FrmGioHangChiTiet
     */
    IGioHangRepository iGioHangRepository;
    IChitietSPRepository iChitietSPRepository;
    IGioHangChiTietService iGioHangChiTietService;
    DefaultComboBoxModel<String> cbxBoxModel;
    DefaultTableModel model;
    private int totalPage;
    private int totalGHCT;
    private final int pageSize;
    private int currentPage;

    public FrmGioHangChiTiet() {
        initComponents();
        setLocationRelativeTo(this);
        iChitietSPRepository = new ChiTietSPRepository();
        iGioHangRepository = new GioHangRepository();
        iGioHangChiTietService = new GioHangChiTietService();
        model = (DefaultTableModel) tblGHCT.getModel();
        getAllGH();
        getAllChiTietSP();
        currentPage = 1;
        pageSize = 5;
        loadTable(iGioHangChiTietService.getAllGioHangChiTiet());
    }

    private void getAllGH() {
        List<GioHang> list = iGioHangRepository.getAllGioHangs();
        cbxBoxModel = (DefaultComboBoxModel<String>) cbxMaGH.getModel();
        cbxBoxModel.removeAllElements();
        for (GioHang hd : list) {
            cbxBoxModel.addElement(hd.getMa());
        }
    }

    private void getAllChiTietSP() {
        List<String> list = iChitietSPRepository.getAllTen();
        cbxBoxModel = (DefaultComboBoxModel<String>) cbxTenSP.getModel();
        cbxBoxModel.removeAllElements();
        for (String s : list) {
            cbxBoxModel.addElement(s);
        }
    }

    private void loadTable(List<QLGioHangChiTiet> list) {
        model.setRowCount(0);
        for (QLGioHangChiTiet ghct : list) {
            model.addRow(new Object[]{ghct.getGioHang().getMa(), iChitietSPRepository.getTen(ghct.getChiTietSP().getId()),
                ghct.getSoLuong(), ghct.getDonGia(), ghct.getDonGiaKhiGiam(), ghct.getThanhTien()});
        }
    }

    private QLGioHangChiTiet validateGioHangChiTiet() {
        String maGH = (String) cbxMaGH.getSelectedItem();
        GioHang gh = iGioHangRepository.getGioHangTheoMa(maGH);
        String tenSP = (String) cbxTenSP.getSelectedItem();
        ChiTietSP chiTietSP = iChitietSPRepository.getChiTietSPTheoTen(tenSP);
        String soLuongString = txtSL.getText();
        if (soLuongString.isBlank()) {
            JOptionPane.showMessageDialog(this, "không đưuọc để trống số lượng");
            return null;
        }
        int soLuong = 0;
        try {
            soLuong = Integer.valueOf(soLuongString);
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "lỗi dữ liệu,soLuong > 0");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là kiểu số nguyên");
            return null;
        }
        String donGiaString = txtDonGia.getText();
        if (donGiaString.isBlank()) {
            JOptionPane.showMessageDialog(this, "không đưuọc để trống đơn giá");
            return null;
        }
        float donGia = 0;
        try {
            donGia = Float.valueOf(donGiaString);
            if (donGia <= 0) {
                JOptionPane.showMessageDialog(this, "lỗi dữ liệu,donGia > 0");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là kiểu số ");
            return null;
        }
        String giamGiaString = txtGiamGia.getText();
        if (giamGiaString.isBlank()) {
            JOptionPane.showMessageDialog(this, "không đưuọc để trống Giảm giá");
            return null;
        }
        float giamGia = 0;
        try {
            giamGia = Float.valueOf(giamGiaString);
            if (donGia <= 0) {
                JOptionPane.showMessageDialog(this, "lỗi dữ liệu,giamGia > 0");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giảm giá phải là kiểu số ");
            return null;
        }
        return new QLGioHangChiTiet(gh, chiTietSP, soLuong, donGia, giamGia);
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
        cbxMaGH = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbxTenSP = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGHCT = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnTruoc = new javax.swing.JButton();
        jlbCurrentPage = new javax.swing.JLabel();
        btnSau = new javax.swing.JButton();
        jlbTotalGHCT = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Mã GH:");

        cbxMaGH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Tên SP:");

        cbxTenSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Số lượng:");

        jLabel4.setText("Đơn giá:");

        jLabel5.setText("Giảm giá:");

        tblGHCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã GH", "Tên SP", "Số lượng", "Đơn giá", "Giảm giá", "Thành tiền"
            }
        ));
        tblGHCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGHCTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGHCT);

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

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnTruoc.setText("<");
        btnTruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTruocActionPerformed(evt);
            }
        });

        jlbCurrentPage.setText("1/1");

        btnSau.setText(">");
        btnSau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSauActionPerformed(evt);
            }
        });

        jlbTotalGHCT.setText("Total GHCT:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbxMaGH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSL)
                                    .addComponent(txtDonGia)
                                    .addComponent(txtGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jlbCurrentPage)
                        .addGap(37, 37, 37)
                        .addComponent(btnSau, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jlbTotalGHCT, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxMaGH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbxTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnClear))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTruoc)
                    .addComponent(jlbCurrentPage)
                    .addComponent(btnSau)
                    .addComponent(jlbTotalGHCT))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        QLGioHangChiTiet gioHangChiTiet = validateGioHangChiTiet();
        if (gioHangChiTiet != null) {
            List<QLGioHangChiTiet> list = iGioHangChiTietService.getAllGioHangChiTiet();
            if(list.size() != 0){
                for(QLGioHangChiTiet ghChiTiet:list){
                    if(gioHangChiTiet.getGioHang().getId().equalsIgnoreCase(ghChiTiet.getGioHang().getId()) && gioHangChiTiet.getChiTietSP().getId().equalsIgnoreCase(ghChiTiet.getChiTietSP().getId())){
                        JOptionPane.showMessageDialog(this,"Mã GH và ID CTSP đã tồn tại");
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, iGioHangChiTietService.saveGioHangChiTiet(gioHangChiTiet));
            loadTable(getCurrentPage());
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int selectRow = tblGHCT.getSelectedRow();
        if (selectRow < 0) {
            JOptionPane.showMessageDialog(this, "Chon 1 dong ten table");
            return;
        }
        QLGioHangChiTiet qlghct = validateGioHangChiTiet();
        if (qlghct != null) {
            JOptionPane.showMessageDialog(this, iGioHangChiTietService.updateGioHangChiTiet(qlghct));
            loadTable(getCurrentPage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int selectRow = tblGHCT.getSelectedRow();
        if (selectRow < 0) {
            JOptionPane.showMessageDialog(this, "Chon 1 dong ten table");
            return;
        }
        int kq = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (kq == JOptionPane.YES_OPTION) {
            String maGH = tblGHCT.getValueAt(selectRow, 0).toString();
            GioHang gh = iGioHangRepository.getGioHangTheoMa(maGH);
            String tenSP = tblGHCT.getValueAt(selectRow, 1).toString();
            ChiTietSP chiTietSP = iChitietSPRepository.getChiTietSPTheoTen(tenSP);
            JOptionPane.showMessageDialog(this, iGioHangChiTietService.deleteGioHangChiTiet(gh.getId(), chiTietSP.getId()));
            loadTable(getCurrentPage());
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblGHCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGHCTMouseClicked
        // TODO add your handling code here:
        int selectRow = tblGHCT.getSelectedRow();
        cbxMaGH.setSelectedItem(tblGHCT.getValueAt(selectRow, 0));
        cbxTenSP.setSelectedItem(tblGHCT.getValueAt(selectRow, 1));
        txtSL.setText(tblGHCT.getValueAt(selectRow, 2).toString());
        txtDonGia.setText(tblGHCT.getValueAt(selectRow, 3).toString());
        txtGiamGia.setText(tblGHCT.getValueAt(selectRow, 4).toString());
    }//GEN-LAST:event_tblGHCTMouseClicked

    private void btnTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTruocActionPerformed
        // TODO add your handling code here:
        currentPage--;
        if (currentPage <= 0) {
            currentPage = totalPage;
        }
        loadTable(getCurrentPage());
    }//GEN-LAST:event_btnTruocActionPerformed

    private void btnSauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSauActionPerformed
        // TODO add your handling code here:
        currentPage++;
        if (currentPage > totalPage) {
            currentPage = 1;
        }
        loadTable(getCurrentPage());
    }//GEN-LAST:event_btnSauActionPerformed

    private List<QLGioHangChiTiet> getCurrentPage() {
        totalGHCT = iGioHangChiTietService.getAllGioHangChiTiet().size();
        totalPage = (totalGHCT - 1) / pageSize + 1;
        jlbCurrentPage.setText(currentPage + "/" + totalPage);
        jlbTotalGHCT.setText("total GioHang:" + totalGHCT);
        int start = (currentPage - 1) * pageSize;
        int end = currentPage * pageSize;
        if (end > totalGHCT) {
            end = totalGHCT;
        }
        return iGioHangChiTietService.getAllGioHangChiTiet().subList(start, end);
    }

    private void clear() {
        cbxMaGH.setSelectedItem(0);
        cbxTenSP.setSelectedItem(0);
        txtDonGia.setText("");
        txtSL.setText("");
        txtGiamGia.setText("");
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
            java.util.logging.Logger.getLogger(FrmGioHangChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGioHangChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGioHangChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGioHangChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGioHangChiTiet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSau;
    private javax.swing.JButton btnTruoc;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbxMaGH;
    private javax.swing.JComboBox<String> cbxTenSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbCurrentPage;
    private javax.swing.JLabel jlbTotalGHCT;
    private javax.swing.JTable tblGHCT;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtSL;
    // End of variables declaration//GEN-END:variables
}
