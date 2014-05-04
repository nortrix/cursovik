package com.mycompany.diplom.task;

/**
 *
 * @since Jan 29, 2014
 * @author dmorari
 */
public class Task {
  private Integer id;
  private String shortName;
  private String description;
  private Long subject;
  private String icon;
  private String answer;
  private Integer cost;
  private boolean solved;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getShortName()
  {
    return shortName;
  }

  public void setShortName(String shortName)
  {
    this.shortName = shortName;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public Long getSubject()
  {
    return subject;
  }

  public void setSubject(Long subject)
  {
    this.subject = subject;
  }

  public String getIcon()
  {
    return icon;
  }

  public void setIcon(String icon)
  {
    this.icon = icon;
  }

  public String getAnswer()
  {
    return answer;
  }

  public void setAnswer(String answer)
  {
    this.answer = answer;
  }

  public Integer getCost()
  {
    return cost;
  }

  public void setCost(Integer cost)
  {
    this.cost = cost;
  }

  public boolean isSolved()
  {
    return solved;
  }

  public void setSolved(boolean solved)
  {
    this.solved = solved;
  }
}
