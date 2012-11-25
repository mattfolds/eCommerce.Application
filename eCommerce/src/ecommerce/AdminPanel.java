/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class AdminPanel extends javax.swing.JPanel{

    /**
     * Creates new form AdminPanel
     */
    private EComData adminData;
    
    public AdminPanel() {
        initComponents();
        
        adminData = ECommerce.myData;
        
        userRBtn.setActionCommand("user");
        userRBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                importUserData();
            }
        });
        
        itemRBtn.setActionCommand("item");
        itemRBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                importItemData();
            }            
        });
        
        reviewRBtn.setActionCommand("review");
        reviewRBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                importReviewData();
            }            
        });
        
        newRowBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addRow();
            }
        });
        
        submitBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                submitChanges();
            }
        });
        
        importUserData();
           
    }
    
    public void addRow()
    {
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        Object[] rowData = new Object[] {false, null, null, null, null, null};
        model.addRow(rowData);
    }
    
    public void submitChanges()
    {
        ButtonModel btnModel = buttonGroup1.getSelection();
        
        String selected = btnModel.getActionCommand();
        
        if (selected.equals("user"))
        {
            updateUserData();
        }
        else if (selected.equals("item"))
        {
            updateItemData();
        }
        else if (selected.equals("review"))
        {
            updateReviewData();
        }
    }
    
    public void importUserData()
    {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    
                },
                new String [] {
                    "Delete",
                    "E-Mail",
                    "Password",
                    "Password Hint",
                    "First Name",
                    "Last Name",
                    "Admin"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Boolean.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.Boolean.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }
            });
        
        Boolean delete = false;
        Boolean admin = false;
        
        LinkedList<User> userList = adminData.getUserData();
        ListIterator<User> iterator = userList.listIterator();
        
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        Object[] rowData = new Object[7];
        
        while(iterator.hasNext())
        {
            admin = false;
            
            User myUser = iterator.next();
            
            rowData[0] = delete;
            rowData[1] = myUser.getEmail();
            rowData[2] = myUser.getPassword();
            rowData[3] = myUser.getPasswordHint();
            rowData[4] = myUser.getFName();
            rowData[5] = myUser.getLName();
            
            String isAdmin = myUser.getAdmin();
            
            if(isAdmin.equals("true"))
            {
                admin = true;
            }
            
            rowData[6] = admin;
            
            model.addRow(rowData);
            
        }
    }
    
    public void importItemData()
    {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    
                },
                new String [] {
                    "Delete",
                    "Name",
                    "Description",
                    "Photo Path",
                    "Price"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Boolean.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }
            });
        
        Boolean delete = false;
        
        LinkedList<Item> itemList = adminData.getItemData();
        ListIterator<Item> iterator = itemList.listIterator();
        
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        Object[] rowData = new Object[5];
        
        while(iterator.hasNext())
        {
            Item myItem = iterator.next();
            
            rowData[0] = delete;
            rowData[1] = myItem.getName();
            rowData[2] = myItem.getDescription();
            rowData[3] = myItem.getPhotoPath();
            rowData[4] = myItem.getPrice();
         
            model.addRow(rowData);
            
        }
    }
    
    public void importReviewData()
    {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                   
                },
                new String [] {
                    "Delete",
                    "ReviewID",
                    "Name",
                    "Review Text",
                    "Rating"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Boolean.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }
            });
        Boolean delete = false;
        
        LinkedList<Review> reviewList = adminData.getReviewData();
        ListIterator<Review> iterator = reviewList.listIterator();
        
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        Object[] rowData = new Object[5];
        
        while(iterator.hasNext())
        {
            Review myReview = iterator.next();
            
            rowData[0] = delete;
            rowData[1] = myReview.getReviewID();
            rowData[2] = myReview.getName();
            rowData[3] = myReview.getReviewText();
            rowData[4] = myReview.getRating();
         
            model.addRow(rowData);
            
        }
    }
    
    public void updateUserData()
    {
        int rowCount = jTable1.getRowCount();
        
        Boolean delete;
        Boolean admin;
        
        User myUser = new User();
        
        for(int i=0; i < rowCount; i++ )
        {
            delete = (Boolean)jTable1.getModel().getValueAt(i, 0);
            myUser.setEmail((String)jTable1.getModel().getValueAt(i, 1));
            myUser.setPassword((String)jTable1.getModel().getValueAt(i, 2));
            myUser.setPasswordHint((String)jTable1.getModel().getValueAt(i, 3));
            myUser.setFName((String)jTable1.getModel().getValueAt(i, 4));
            myUser.setLName((String)jTable1.getModel().getValueAt(i, 5));
            
            if (((Boolean)jTable1.getModel().getValueAt(i, 6)) == null)
            {
                admin = false;
            }
            else
            {
                admin = ((Boolean)jTable1.getModel().getValueAt(i, 6));
            }
            
            if (admin)
            {
                myUser.setAdmin("true");
            }
            else
            {
                myUser.setAdmin("false");
            }
            
            if(delete)
            {
                try
                {
                    adminData.deleteRecord(myUser, "user");
                    continue;
                }
                catch(EComException e)
                {
                    
                }
            }
            
            User existing = adminData.queryUserData(myUser.getEmail());
            
            if(existing == null)
            {
                try
                {
                    adminData.writeRecord(myUser, "user");
                    continue;
                }
                catch(EComException e)
                {
                    
                }
            }
            
            if(!existing.equals(myUser))
            {
                try
                {
                    adminData.updateRecord(myUser, "user");
                    continue;
                }
                catch(EComException e)
                {
                    
                }
            }
        }
        
    }
    
    public void updateItemData()
    {
        int rowCount = jTable1.getRowCount();
        
        Boolean delete;
        
        Item myItem = new Item();
        
        for(int i=0; i < rowCount; i++ )
        {
            delete = (Boolean)jTable1.getModel().getValueAt(i, 0);
            myItem.setName((String)jTable1.getModel().getValueAt(i, 1));
            myItem.setDescription((String)jTable1.getModel().getValueAt(i, 2));
            myItem.setPhotoPath((String)jTable1.getModel().getValueAt(i, 3));
            myItem.setPrice((String)jTable1.getModel().getValueAt(i, 4));
            
            if(delete)
            {
                try
                {
                    adminData.deleteRecord(myItem, "item");
                    continue;
                }
                catch(EComException e)
                {
                    
                }
            }
            
            Item existing = adminData.queryItemData(myItem.getName());
            
            if(existing == null)
            {
                try
                {
                    adminData.writeRecord(myItem, "item");
                    continue;
                }
                catch(EComException e)
                {
                    
                }
            }
            
            if(!existing.equals(myItem))
            {
                try
                {
                    adminData.updateRecord(myItem, "item");
                    continue;
                }
                catch(EComException e)
                {
                    
                }
            }
        }
        
    }
    
    public void updateReviewData()
    {
        int rowCount = jTable1.getRowCount();
        
        Boolean delete;
        
        Review myReview = new Review();
        
        for(int i=0; i < rowCount; i++ )
        {
            delete = (Boolean)jTable1.getModel().getValueAt(i, 0);
            myReview.setReviewID((String)jTable1.getModel().getValueAt(i, 1));
            myReview.setName((String)jTable1.getModel().getValueAt(i, 2));
            myReview.setReviewText((String)jTable1.getModel().getValueAt(i, 3));
            myReview.setRating((String)jTable1.getModel().getValueAt(i, 4));
            
            if(delete)
            {
                try
                {
                    adminData.deleteRecord(myReview, "review");
                    continue;
                }
                catch(EComException e)
                {
                    
                }
            }
            
            Review existing = adminData.queryReviewID(myReview.getReviewID());
            
            if(existing == null)
            {
                try
                {
                    adminData.writeRecord(myReview, "review");
                    continue;
                }
                catch(EComException e)
                {
                    
                }
            }
            
            if(!existing.equals(myReview))
            {
                try
                {
                    adminData.updateRecord(myReview, "review");
                    continue;
                }
                catch(EComException e)
                {
                    
                }
            }
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        userRBtn = new javax.swing.JRadioButton();
        itemRBtn = new javax.swing.JRadioButton();
        reviewRBtn = new javax.swing.JRadioButton();
        submitBtn = new javax.swing.JButton();
        titleLbl = new javax.swing.JLabel();
        newRowBtn = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);
        jTable1.setDefaultRenderer(String.class, new MultiLineTableCellRenderer());

        buttonGroup1.add(userRBtn);
        userRBtn.setSelected(true);
        userRBtn.setText("Users");

        buttonGroup1.add(itemRBtn);
        itemRBtn.setText("Items");

        buttonGroup1.add(reviewRBtn);
        reviewRBtn.setText("Reviews");

        submitBtn.setText("Submit");
        submitBtn.setToolTipText("");

        titleLbl.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        titleLbl.setText("Admin Console");

        newRowBtn.setText("New Row");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(submitBtn)
                        .addGap(18, 18, 18)
                        .addComponent(newRowBtn)
                        .addGap(18, 18, 18)
                        .addComponent(userRBtn)
                        .addGap(18, 18, 18)
                        .addComponent(itemRBtn)
                        .addGap(18, 18, 18)
                        .addComponent(reviewRBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(titleLbl)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userRBtn)
                    .addComponent(itemRBtn)
                    .addComponent(reviewRBtn)
                    .addComponent(submitBtn)
                    .addComponent(newRowBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton itemRBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton newRowBtn;
    private javax.swing.JRadioButton reviewRBtn;
    private javax.swing.JButton submitBtn;
    private javax.swing.JLabel titleLbl;
    private javax.swing.JRadioButton userRBtn;
    // End of variables declaration//GEN-END:variables

private class MultiLineTableCellRenderer extends JTextArea
    implements TableCellRenderer {
    private java.util.List<java.util.List<Integer>> rowColHeight = new ArrayList<java.util.List<Integer>>();
   
    public MultiLineTableCellRenderer()
    {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }
   
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected, boolean hasFocus,
        int row, int column)
    {
        
        if (isSelected)
        {
             //setForeground(table.getSelectionForeground());
                setBackground(new Color(189,215,255));
              //setBackground(table.getSelectionBackground());
                setForeground(Color.black);
        }
        else
        {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        setFont(table.getFont());
        if (hasFocus)
        {
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
            if (table.isCellEditable(row, column))
            {
                setForeground(UIManager.getColor("Table.focusCellForeground"));
                setBackground(UIManager.getColor("Table.focusCellBackground"));
            }
        }
        else
        {
            setBorder(new javax.swing.border.EmptyBorder(1, 2, 2, 1));
        }
        if (value != null)
        {
            setText(value.toString());
        }
        else
        {
            setText("");
        }
        
        adjustRowHeight(table, row, column);
        return this;
    }
   
    /**
     * Calculate the new preferred height for a given row, and sets the height on the table.
     */
    private void adjustRowHeight(JTable table, int row, int column)
    {
            //The trick to get this to work properly is to set the width of the column to the
            //textarea. The reason for this is that getPreferredSize(), without a width tries
            //to place all the text in one line. By setting the size with the with of the column,
            //getPreferredSize() returnes the proper height which the row should have in
            //order to make room for the text.
            int cWidth = table.getTableHeader().getColumnModel().getColumn(column).getWidth();
            setSize(new Dimension(cWidth, 1000));
            int prefH = getPreferredSize().height;
            while (rowColHeight.size() <= row)
            {
                rowColHeight.add(new ArrayList<Integer>(column));
            }
            java.util.List<Integer> colHeights = rowColHeight.get(row);
            while (colHeights.size() <= column)
            {
                colHeights.add(0);
            }
            colHeights.set(column, prefH);
            int maxH = prefH;
            for (Integer colHeight : colHeights)
            {
                if (colHeight > maxH)
                {
                        maxH = colHeight;
                }
            }
            if (table.getRowHeight(row) != maxH)
            {
                table.setRowHeight(row, maxH);
            }
    }
 }

}