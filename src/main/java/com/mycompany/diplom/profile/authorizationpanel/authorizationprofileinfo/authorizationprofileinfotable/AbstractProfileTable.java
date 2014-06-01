/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.authorizationprofileinfo.authorizationprofileinfotable;

import com.mycompany.diplom.profile.authorizationpanel.authorizationprofileinfo.AuthorizationProfilePurchaseDate;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author valik
 */
public abstract class AbstractProfileTable extends JPanel
{

  public AbstractProfileTable()
  {
    this.tableModel = new AbstractProfileTable.UnmodificableProfileTableModel();
    this.table = new JTable(tableModel);

    JLabel label = new JLabel(getLabel());
    label.setFont(new Font("Serif", Font.BOLD, 15));
    BorderLayout borderLayout = new BorderLayout();
    this.setLayout(borderLayout);

    this.setBorder(BorderFactory.createTitledBorder(getLabel()));
    this.add(table, BorderLayout.CENTER);

  }
  protected JTable table;
  protected AbstractProfileTable.UnmodificableProfileTableModel tableModel;
  private List<AuthorizationProfilePurchaseDate> purchaseList = new ArrayList<AuthorizationProfilePurchaseDate>();
  private AbstractProfileTable oppositeTable;

  public void setOppositeTable(AbstractProfileTable oppositeTable)
  {
    this.oppositeTable = oppositeTable;
  }

  public void move(AuthorizationProfilePurchaseDate purchase, AbstractProfileTable destination) {
    destination.addArticle(purchase);
    removeArticle(purchase);

    updateView();
    destination.updateView();
  }

  public void move(int index, AbstractProfileTable destination){
    if (index < 0 || index >= purchaseList.size()) {
      return;
    } 

    move(purchaseList.get(index), destination);
  }

  public void move(int index) {
    move(index, oppositeTable);
  }
  public JTable getTable()
  {
    return table;
  }

  public List<AuthorizationProfilePurchaseDate> getPurchases()
  {
    return purchaseList;
  }

  public void setArticles(List<AuthorizationProfilePurchaseDate> purchaseList)
  {
    this.purchaseList = purchaseList;
  }

  public void addArticle(AuthorizationProfilePurchaseDate purchase)
  {
    this.purchaseList.add(purchase);
  }

  public void removeArticle(AuthorizationProfilePurchaseDate purchase)
  {
    this.purchaseList.remove(purchase);
  }

  public void updateView()
  {
    this.tableModel.setRowCount(0);

    for (AuthorizationProfilePurchaseDate purchase : purchaseList)
    {
      this.tableModel.addRow(new Object[]
      {
        purchase.getName(), purchase.getDescription(), purchase.getCost()
      });
    }
    
    this.tableModel.fireTableDataChanged();
  }

  public abstract String getLabel();

  protected String[] getColumnNames()
  {
    return new String[]
    {
      "Name", "Description", "Cost"
    };
  }

  public class UnmodificableProfileTableModel extends DefaultTableModel
  {

    public UnmodificableProfileTableModel()
    {
      super(getColumnNames(), 0);
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
      return false;
    }
  }
}
