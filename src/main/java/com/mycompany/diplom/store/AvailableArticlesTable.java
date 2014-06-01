package com.mycompany.diplom.store;

import com.mycompany.diplom.shop.ArticleDao;

/**
 *
 * @since May 4, 2014
 * @author dmorari
 */
public class AvailableArticlesTable extends AbstractTable {

  public AvailableArticlesTable(){
    super();

    this.getArticles().addAll(ArticleDao.getInstance().findAll());
    this.updateView();
  }
  @Override
  public String getLabel()
  {
    return "Магазин";
  }

}
