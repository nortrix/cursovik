package com.mycompany.diplom.subject;

/**
 *
 * @since Jan 29, 2014
 * @author dmorari
 */

//____________Класс содержащий методы get'оры и set'оры для полей из таблицы SUBJECTS 

public class Subject {
  private Long id;
  private String name;
  private String description; 
  private String icon;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getIcon()
  {
    return icon;
  }

  public void setIcon(String icon)
  {
    this.icon = icon;
  }

}
