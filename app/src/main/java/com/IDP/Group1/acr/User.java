package com.IDP.Group1.acr;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.List;

public class User implements java.io.Serializable {

	String name, email, machineID, contactInfo;
	List<String> notification;
	ArrayList <Entry> batteryData;
	ArrayList <BarEntry> dustData;
	ArrayList <BarEntry> cleanRateData;
	ArrayList <BarEntry> powerOnTimeData;
	int battery;
	List<SheduleClass> shedules;
	int [][]map = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
			{1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
			{1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};

	public ArrayList<BarEntry> getCleanRateData() {
		return cleanRateData;
	}

	public void setCleanRateData(ArrayList<BarEntry> cleanRateData) {
		this.cleanRateData = cleanRateData;
	}

	public ArrayList<BarEntry> getPowerOnTimeData() {
		return powerOnTimeData;
	}

	public void setPowerOnTimeData(ArrayList<BarEntry> powerOnTimeData) {
		this.powerOnTimeData = powerOnTimeData;
	}


	User() {
		name = email = machineID = "";
		isPowerOn = isSleep = isRinging = false;
		battery = 0;
		notification = new ArrayList<String>();
		shedules = new ArrayList<>();
//		dummyData();
//		writeData();

		name = "Rahul Mohoto";
		email = "rahulmohoto@gmail.com";
		machineID = "1234";
		contactInfo = "01793886634";

		battery = 100;

		dustData = new ArrayList<>();
		dustData.add(new BarEntry(0, 173.0f));
		dustData.add(new BarEntry(1, 163.0f));
		dustData.add(new BarEntry(2, 203.0f));
		dustData.add(new BarEntry(3, 30.0f));
		dustData.add(new BarEntry(4, 90.0f));
		dustData.add(new BarEntry(5, 101.0f));
		dustData.add(new BarEntry(6, 113.0f));
		dustData.add(new BarEntry(7, 129.0f));
		dustData.add(new BarEntry(8, 188.0f));
		dustData.add(new BarEntry(9, 155.0f));
		dustData.add(new BarEntry(10, 137.0f));

		cleanRateData = new ArrayList<>();
		cleanRateData.add(new BarEntry(0, 5));
		cleanRateData.add(new BarEntry(1, 4));
		cleanRateData.add(new BarEntry(2, 1));
		cleanRateData.add(new BarEntry(3, 3));
		cleanRateData.add(new BarEntry(4, 7));
		cleanRateData.add(new BarEntry(5, 4));
		cleanRateData.add(new BarEntry(6, 10));

		powerOnTimeData = new ArrayList<>();
		powerOnTimeData.add(new BarEntry(0, 100));
		powerOnTimeData.add(new BarEntry(1, 253));
		powerOnTimeData.add(new BarEntry(2, 423));
		powerOnTimeData.add(new BarEntry(3, 233));
		powerOnTimeData.add(new BarEntry(4, 145));
		powerOnTimeData.add(new BarEntry(5, 278));
		powerOnTimeData.add(new BarEntry(6, 56));

		batteryData = new ArrayList<>();
		batteryData.add(new Entry(0, 100f));
		batteryData.add(new Entry(1, 90f));
		batteryData.add(new Entry(2, 80f));
		batteryData.add(new Entry(3, 60f));
		batteryData.add(new Entry(5, 30f));
		batteryData.add(new Entry(11, 10f));
		batteryData.add(new Entry(12, 5f));
		batteryData.add(new Entry(14, 1f));


		notification.add("welcome to our ACR service");
		notification.add("please scan your room");
		notification.add("you battery is fully charged");
		notification.add("add a schedule for cleaning you room daily");

		int[] a = new int[7];

		a[0] = a[1] = a[2] = 1;
		a[3] = a[4] = a[5] = a[6] = 0;

		shedules.add(new SheduleClass(1, 10, 23 ,8, 2019, true));
		shedules.add(new SheduleClass(2, 20, 13 ,7, 2018, false));
		shedules.add(new SheduleClass(3, 30, a, true));
	}


	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	boolean isPowerOn, isSleep, isRinging;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMachineID() {
		return machineID;
	}

	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public List<String> getNotification() {
		return notification;
	}

	public void setNotification(List<String> notification) {
		this.notification = notification;
	}

	public boolean isPowerOn() {
		return isPowerOn;
	}

	public void setPowerOn(boolean powerOn) {
		isPowerOn = powerOn;
	}

	public boolean isSleep() {
		return isSleep;
	}

	public void setSleep(boolean sleep) {
		isSleep = sleep;
	}

	public boolean isRinging() {
		return isRinging;
	}

	public void setRinging(boolean ringing) {
		isRinging = ringing;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public List<SheduleClass> getShedules() {
		return shedules;
	}

	public void setShedules(List<SheduleClass> shedules) {
		this.shedules = shedules;
	}

	public ArrayList<BarEntry> getDustData() {
		return dustData;
	}

	public void setDustData(ArrayList<BarEntry> dustData) {
		this.dustData = dustData;
	}

	public ArrayList<Entry> getBatteryData() {
		return batteryData;
	}

	public void setBatteryData(ArrayList<Entry> batteryData) {
		this.batteryData = batteryData;
	}

	private void dummyData() {
		name = "Rahul Mohoto";
		email = "rahulmohoto@gmail.com";
		machineID = "1234";
		contactInfo = "01793886634";

		battery = 100;

		dustData.add(new BarEntry(0, 173.0f));
		dustData.add(new BarEntry(1, 163.0f));
		dustData.add(new BarEntry(2, 203.0f));
		dustData.add(new BarEntry(3, 30.0f));
		dustData.add(new BarEntry(4, 90.0f));
		dustData.add(new BarEntry(5, 101.0f));
		dustData.add(new BarEntry(6, 113.0f));
		dustData.add(new BarEntry(7, 129.0f));
		dustData.add(new BarEntry(8, 188.0f));
		dustData.add(new BarEntry(9, 155.0f));
		dustData.add(new BarEntry(10, 137.0f));

		batteryData.add(new Entry(0, 100f));
		batteryData.add(new Entry(1, 90f));
		batteryData.add(new Entry(2, 80f));
		batteryData.add(new Entry(3, 60f));
		batteryData.add(new Entry(5, 30f));
		batteryData.add(new Entry(11, 10f));
		batteryData.add(new Entry(12, 5f));
		batteryData.add(new Entry(14, 1f));


		notification.add("welcome to our ACR service");
		notification.add("please scan your room");
		notification.add("you battery is fully charged");
		notification.add("add a schedule for cleaning you room daily");

		int[] a = new int[7];

		a[0] = a[1] = a[2] = 1;
		a[3] = a[4] = a[5] = a[6] = 0;

		shedules.add(new SheduleClass(1, 10, 23 ,8, 2019, true));
		shedules.add(new SheduleClass(2, 20, 13 ,7, 2018, false));
		shedules.add(new SheduleClass(3, 30, a, true));
	}

	boolean isChar(char c) {
		return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
	}
	public void writeData(User user) {
		String p = FirebaseAuth.getInstance().getCurrentUser().getEmail();

		p = p.replace('.', '_');
		p = p.replace('@', '_');

		DatabaseReference dr = FirebaseDatabase.getInstance().getReference(p);
		dr.setValue(user);
	}
//
//	public User readData() {
//		User ret = null;
//
//		try {
//			FileInputStream fileIn = new FileInputStream("/tmp/user.ser");
//			ObjectInputStream in = new ObjectInputStream(fileIn);
//			ret = (User) in.readObject();
//			in.close();
//			fileIn.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		return ret;
//	}
}
