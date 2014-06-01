/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.authorizationprofileinfo.authorizationprofileinfotable;

import com.mycompany.diplom.profile.authorizationpanel.AuthorizationProfileDate;
import com.mycompany.diplom.profile.authorizationpanel.authorizationprofileinfo.AuthorizationProfilePurchaseDateDao;

/**
 *
 * @author valik
 */
public class AvailablePurchaseTable extends AbstractProfileTable {

  public AvailablePurchaseTable(){
    super();

    AuthorizationProfileDate profDate = new AuthorizationProfileDate();
    this.getPurchases().addAll(AuthorizationProfilePurchaseDateDao.getInstance().findAuthorizationProfilePurchase(profDate.getId()));
    this.updateView();
  }
  @Override
  public String getLabel()
  {
    return "Приобретенное";
  }

}