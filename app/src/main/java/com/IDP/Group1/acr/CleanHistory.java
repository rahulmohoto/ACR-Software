package com.IDP.Group1.acr;

public class CleanHistory {
	private int hour, minute, date, month, year, dust;

	public CleanHistory(int hour, int minute, int date, int month, int year, int dust) {
		this.hour = hour;
		this.minute = minute;
		this.date = date;
		this.month = month;
		this.year = year;
		this.dust = dust;
	}

	public CleanHistory() {
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDust() {
		return dust;
	}

	public void setDust(int dust) {
		this.dust = dust;
	}
}
