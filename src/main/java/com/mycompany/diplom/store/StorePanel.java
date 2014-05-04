//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package com.mycompany.diplom.store;

import com.mycompany.diplom.shop.Article;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @since May 4, 2014
 * @author dmorari
 */
public class StorePanel extends JPanel {
        private AbstractTable availableArticles;
        private AbstractTable bookedArticles;
  
        public StorePanel(){
          BorderLayout borderLayout = new BorderLayout(5, 20);
          this.setLayout(borderLayout);
          
          this.availableArticles = new AvailableArticlesTable();
          this.bookedArticles = new BookedArticlesTable();
         

          availableArticles.setOppositeTable(bookedArticles);
          bookedArticles.setOppositeTable(availableArticles);

          JButton confirmButton = new JButton("Buy!");

          this.add(availableArticles, BorderLayout.PAGE_START);
          this.add(bookedArticles, BorderLayout.CENTER);
          this.add(confirmButton, BorderLayout.PAGE_END);


          availableArticles.getTable().addMouseListener(createMouseLister(availableArticles));
          bookedArticles.getTable().addMouseListener(createMouseLister(bookedArticles));
         
          confirmButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
              for (Article article : bookedArticles.getArticles()) {
                System.out.println(article.getName());
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
