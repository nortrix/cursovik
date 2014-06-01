package com.mycompany.diplom.store;

import com.mycompany.diplom.components.CurrencyPanel;
import com.mycompany.diplom.profile.authorizationpanel.AuthorizationProfileDate;
import com.mycompany.diplom.shop.Article;
import com.mycompany.diplom.shop.Purchases;
import com.mycompany.diplom.shop.PurchasesDao;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @since May 4, 2014
 * @author dmorari
 */
public class StorePanel extends JPanel {
        private AbstractTable availableArticles;
        private AbstractTable bookedArticles;
  
        public StorePanel(){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
          
          this.availableArticles = new AvailableArticlesTable();
          this.bookedArticles = new BookedArticlesTable();         

          availableArticles.setOppositeTable(bookedArticles);
          bookedArticles.setOppositeTable(availableArticles);

          JButton confirmButton = new JButton("Купить!");
          confirmButton.setMaximumSize(new Dimension(100, 50));
          confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
          
          JScrollPane tableScrollPane1 = new JScrollPane(availableArticles);
          tableScrollPane1.setPreferredSize(new Dimension(700, 250));
          tableScrollPane1.setAlignmentX(Component.CENTER_ALIGNMENT);
          
          JScrollPane tableScrollPane2 = new JScrollPane(bookedArticles);
          tableScrollPane2.setPreferredSize(new Dimension(700, 150));
          tableScrollPane2.setAlignmentX(Component.CENTER_ALIGNMENT);

          this.add(tableScrollPane1, BorderLayout.PAGE_START);
          this.add(Box.createVerticalStrut(15));
          this.add(tableScrollPane2, BorderLayout.CENTER);
          this.add(confirmButton, BorderLayout.PAGE_END);

          availableArticles.getTable().addMouseListener(createMouseLister(availableArticles));
          bookedArticles.getTable().addMouseListener(createMouseLister(bookedArticles));
         
          confirmButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                for (Article article : bookedArticles.getArticles()) {
                      System.out.println(article.getId() + ") " + article.getName());
                }
                
                AuthorizationProfileDate profDate = new AuthorizationProfileDate();
                if (profDate.getName() != null && profDate.getBalance() != null) {
                    for (Article article : bookedArticles.getArticles()) { 
                        profDate.setBalance(profDate.getBalance() - article.getCost());
                        CurrencyPanel.getInstance().setBalanceInCurrencyLabel(profDate.getBalance());  
                        
                        List<Purchases> list = PurchasesDao.getInstance().allPurchases();
                        PurchasesDao.getInstance().insertPurchases((list.size() + 1), article.getId(), profDate.getId());
                    }
                    JOptionPane.showMessageDialog(null, "Спасибо за покупки!");
                }
            }
          });
        }


        private MouseListener createMouseLister(final AbstractTable table) {
          return new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e)
            {
              if (e.getClickCount() == 2) {
                int row = table.getTable().getSelectedRow();
                table.move(row);
              }
            }
                
          };
        }
}
