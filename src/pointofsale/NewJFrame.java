package pointofsale;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JList;

public class NewJFrame extends javax.swing.JFrame {
    
    private List<Products>products;
    private DefaultListModel listModel;
    
    public NewJFrame() {
        try {
            initComponents();
            products = new ArrayList<>();
            //buttonsProducts = new ArrayList<>();
            listModel = new DefaultListModel();
            SoldList.setModel(listModel);
            loadProducts();
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        sellButton = new javax.swing.JButton();
        totalPrice = new javax.swing.JLabel();
        amountTotal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProductPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        SoldList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sellButton.setText("Vender");
        sellButton.setActionCommand("Vender");
        sellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellButtonActionPerformed(evt);
            }
        });

        totalPrice.setText("00.00");

        amountTotal.setText("Total: $");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(amountTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sellButton)
                        .addGap(0, 35, Short.MAX_VALUE))
                    .addComponent(totalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amountTotal)
                    .addComponent(totalPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sellButton)
                .addContainerGap())
        );

        ProductPanel.setLayout(new java.awt.GridLayout(0, 3));
        jScrollPane1.setViewportView(ProductPanel);

        SoldList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(SoldList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellButtonActionPerformed
        JOptionPane.showMessageDialog(this, "Precio total de la venta: " + totalPrice.getText() + "\n\nNo olvides agradecer al cliente su visita", "Venta Realizada", JOptionPane.INFORMATION_MESSAGE);
        totalPrice.setText("00.00");
        DefaultListModel listModel = (DefaultListModel) SoldList.getModel();
        listModel.removeAllElements();
    }//GEN-LAST:event_sellButtonActionPerformed

    public void loadProducts() throws FileNotFoundException, IOException{
        
        String cadena, archivo ="src/Products/Flavors.txt";
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        String[] num;
        while((cadena = b.readLine())!=null) {
            Products product = new Products();
            num = cadena.split(",");
            product.setName(num[0]);
            product.setPrice(Float.parseFloat(num[1]));
            JButton button = new JButton(num[0]);
            try{
                ImageIcon img = new ImageIcon("src/Images/" + num[0] + ".jpg");
                Image image = img.getImage();
                Image newimg = image.getScaledInstance(160,160,Image.SCALE_DEFAULT);
                img = new ImageIcon(newimg);
                button.setIcon(img);
            } catch (Exception ex){
                System.out.println("src/Images/" + num[0] + ".jpg");
            }
            
            ProductPanel.add(button);
            products.add(product);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    float currentTotal = Float.parseFloat(totalPrice.getText());
                    for(Products price : products){
                        if(price.getName() == button.getText()){
                            currentTotal += price.getPrice();
                            totalPrice.setText(Float.toString(currentTotal));
                            listModel.addElement(price.getName() + " $" + price.getPrice());
                        }
                    }
                }
            });
        }
        b.close();
        ProductPanel.updateUI();
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ProductPanel;
    private javax.swing.JList<String> SoldList;
    private javax.swing.JLabel amountTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton sellButton;
    private javax.swing.JLabel totalPrice;
    // End of variables declaration//GEN-END:variables
}
