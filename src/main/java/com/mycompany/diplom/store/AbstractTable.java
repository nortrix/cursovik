package com.mycompany.diplom.store;

import com.mycompany.diplom.shop.Article;
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
 * @since May 4, 2014
 * @author dmorari
 */
public abstract class AbstractTable extends JPanel
{

  public AbstractTable()
  {
    this.tableModel = new UnmodificableTableModel();
    this.table = new JTable(tableModel);

    JLabel label = new JLabel(getLabel());
    label.setFont(new Font("Serif", Font.BOLD, 15));
    BorderLayout borderLayout = new BorderLayout();
    this.setLayout(borderLayout);

    this.setBorder(BorderFactory.createTitledBorder(getLabel()));
    this.add(table, BorderLayout.CENTER);

  }
  protected JTable table;
  protected UnmodificableTableModel tableModel;
  private List<Article> articles = new ArrayList<Article>();
  private AbstractTable oppositeTable;

  public void setOppositeTable(AbstractTable oppositeTable)
  {
    this.oppositeTable = oppositeTable;
  }

  public void move(Article article, AbstractTable destination) {
    destination.addArticle(article);
    removeArticle(article);

    updateView();
    destination.updateView();
  }

  public void move(int index, AbstractTable destination){
    if (index < 0 || index >= articles.size()) {
      return;
    } 

    move(articles.get(index), destination);
  }

  public void move(int index) {
    move(index, oppositeTable);
  }
  public JTable getTable()
  {
    return table;
  }

  public List<Article> getArticles()
  {
    return articles;
  }

  public void setArticles(List<Article> articles)
  {
    this.articles = articles;
  }

  public void addArticle(Article article)
  {
    this.articles.add(article);
  }

  public void removeArticle(Article article)
  {
    this.articles.remove(article);
  }

  public void updateView()
  {
    this.tableModel.setRowCount(0);

    for (Article article : articles)
    {
      this.tableModel.addRow(new Object[]
      {
        article.getName(), article.getDescription(), article.getCost()
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

  public class UnmodificableTableModel extends DefaultTableModel
  {

    public UnmodificableTableModel()
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
