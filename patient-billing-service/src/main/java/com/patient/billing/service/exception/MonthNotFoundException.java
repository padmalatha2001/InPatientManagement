package com.patient.billing.service.exception;

public class MonthNotFoundException extends Exception
{
  public MonthNotFoundException()
  {
	  super();
  }
  public MonthNotFoundException(String message)
  {
	  super(message);
  }

}
