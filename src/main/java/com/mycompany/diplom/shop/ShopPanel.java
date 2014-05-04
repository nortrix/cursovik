/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.shop;

import com.mycompany.diplom.profile.authorizationpanel.AuthorizationProfileDate;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author valik
 */
public class ShopPanel extends JPanel {

//    private JTable table;
    private ArrayList<Integer> selectedRows = new ArrayList<Integer>();

    public void addSelectedRow(Integer selectedRow) {
        selectedRows.add(selectedRow);
    }

    private ShopPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Магазин");
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //label.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(label);

        this.add(Box.createVerticalStrut(10));


        TableModel mtm = new TableModel();
        JTable table = new JTable(mtm);
        table.setPreferredScrollableViewportSize(new Dimension(700, 300));
        //растягивает таблицу
        table.setFillsViewportHeight(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(700, 400));
        tableScrollPane.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(tableScrollPane);
        this.validate();

        List<Article> shopComponents = ArticleDao.getInstance().allShopComponents();


        for (Article item : shopComponents) {
            Object[] row = new Object[5];
            row[0] = new Integer(item.getId());
            row[1] = item.getName();
            row[2] = item.getDescription();
            row[3] = new Integer(item.getCost());
            row[4] = Boolean.FALSE;
            mtm.addData(row);
        }
        this.add(Box.createVerticalStrut(15));

        JButton button = new JButton("Приобрести");
        button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(button);
        button.addActionListener(new ActionButton());

    }

    private class ActionButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            AuthorizationProfileDate profDate = new AuthorizationProfileDate();

            if (profDate.getId() == null) {
                JOptionPane.showMessageDialog(null, "Прежде чем делать покупки войдите под своим профилем!");
            } else {
                for (int i = 0; i < selectedRows.size(); i++) {
                    System.out.println("profId = " + profDate.getId() + "; Покупка = " + selectedRows.get(i));
                }

            }

        }
    }
    private static ShopPanel self = new ShopPanel();

    public static ShopPanel getInstance() {
        return self;
    }
}
