package Projeto;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date {

  public int day;

  public int mounth;

  public int year;

  public int hour;

  public int min;

  public Date(int day, int mounth, int year, int hour, int min) {
    this.day = day;
    this.mounth = mounth;
    this.year = year;
    this.hour = hour;
    this.min = min;
  }

  public Date(){
    GregorianCalendar gregorianCalendar= new GregorianCalendar();
    this.day =gregorianCalendar.get(Calendar.DAY_OF_MONTH);
    this.mounth =gregorianCalendar.get(Calendar.MONTH);
    this.year =gregorianCalendar.get(Calendar.YEAR);
  }


  public boolean beforeDate(Date d) {
    return compareTo(d) == -1;// ? true:false;
  }

  public boolean afterDate(Date d) {

    return compareTo(d) == 1;
  }

  public boolean isLeapYear() {
    return this.year % 4 == 0 && (this.year % 400 == 0||this.year % 100 != 0);
  }

  public void incrementDate() {
// FAZER
  }

  public int differenceYears(Date d) {

    return Math.abs(this.year - d.year);
  }

  public int differenceMonths(Date d) {
    return 0;
  }

  public int compareTo(Date d) {
    if (this.day==d.day && this.mounth==d.mounth && this.year==d.year)
      return 0;
    if(this.year==d.year){
      if (this.mounth==d.mounth)
        return this.day<d.day ? -1:1;
      else
        return this.mounth<d.mounth ?-1:1;
    }
    return this.year<d.year ? -1:1;
  }

  public int daysMonth() {
// IF month <1 && >12 erro e return -1
    switch (this.mounth){
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12: return 31;

      case 2: return isLeapYear() ? 29:28;
      default: return 30;
    }
  }
  public static int daysMonth(int month, int year, int hour, int min){
    Date d=new Date(1,month,year, hour, min);
    return d.daysMonth();
  }

  @Override
  public String toString() {
    return day+"/"+mounth+"/"+year;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getMonth() {
    return mounth;
  }

  public void setMonth(int month) {
    this.mounth = month;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getMin() {
    return min;
  }

  public void setMin(int min) {
    this.min = min;
  }

  // ################################
  public static int daysCrawlerRecursive(Date begin, Date end){
    int diffDays=0;
    Date auxBegin= new Date(begin.day,begin.mounth,begin.year, begin.hour, begin.min);
    diffDays= daysCrawlerRecursiveAux(auxBegin,end);
    return diffDays;
  }

  public static int daysCrawlerRecursiveAux(Date begin, Date end){
    if(begin.beforeDate(end)){
      begin.incrementDate();
      return 1+Date.daysCrawlerRecursive(begin,end);
    }
    return 0;
  }
}