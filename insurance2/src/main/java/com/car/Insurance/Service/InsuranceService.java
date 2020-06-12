package com.car.Insurance.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.car.Insurance.model.Insurance;
import com.car.Insurance.model.InsuranceDetail;

@Service
public class InsuranceService {

	//give the admin information here--------
	public Map<String, List<Insurance>> getDetails() {

		Map<String, List<Insurance>> go = new HashMap<>();
		ArrayList<Insurance> hm = new ArrayList<Insurance>();

		hm.add(new Insurance("audi", "diesel", 416000,1.97f, 20, 100d, 50d, 1110d));
		hm.add(new Insurance("audi", "gas", 438000,1.37f, 20, 100d, 50d, 1110d));
		hm.add(new Insurance("bmw", "diesel",372000, 1.67f, 20, 100d, 50d, 1110d));
		hm.add(new Insurance("bmw", "petrol",232000, 1.37f, 20, 100d, 50d, 1110d));
		hm.add(new Insurance("benz", "diesel",  449000,1.97f, 20, 100d, 50d, 1110d));
		hm.add(new Insurance("benz", "gas",368000 ,1.57f, 20, 100d, 50d, 1110d));

		ArrayList<Insurance> hu = new ArrayList<Insurance>();

		hu.add(new Insurance("audi", "diesel",416000, 2.97f, 20, 100d, 50d, 1110d));
		hu.add(new Insurance("audi", "gas",438000 ,2.37f, 20, 100d, 50d, 1110d));
		hu.add(new Insurance("bmw", "diesel", 372000,2.67f, 20, 100d, 50d, 1110d));
		hu.add(new Insurance("bmw", "petrol", 232000,2.37f, 20, 100d, 50d, 1110d));
		hu.add(new Insurance("benz", "diesel", 449000, 2.97f, 20, 100d, 50d, 1110d));
		hu.add(new Insurance("benz", "gas",368000, 2.57f, 20, 100d, 50d, 1110d));

		ArrayList<Insurance> hd = new ArrayList<Insurance>();

		hd.add(new Insurance("audi", "diesel", 416000,3.97f, 20, 100d, 50d, 1110d));
		hd.add(new Insurance("audi", "gas",438000, 3.37f, 20, 100d, 50d, 1110d));
		hd.add(new Insurance("bmw", "diesel",372000, 3.67f, 20, 100d, 50d, 1110d));
		hd.add(new Insurance("bmw", "petrol",232000, 3.37f, 20, 100d, 50d, 1110d));
		hd.add(new Insurance("benz", "diesel",  449000,3.97f, 20, 100d, 50d, 1110d));
		hd.add(new Insurance("benz", "gas", 368000,3.57f, 20, 100d, 50d, 1110d));

		go.put("TATA AIG", hm);
		go.put("HDFC ERGO", hu);
		go.put("UNITED INDIA", hd);
		return go;

	}
	
	//set the depreciation percentage for each companies here-------------
	public Map<String, Integer> setDepreciation() {

		Map<String, Integer> go = new HashMap<>();
		go.put("TATA AIG", 5);
		go.put("HDFC ERGO", 6);
		go.put("UNITED INDIA", 3);
		return go;
	}

	

	// to get the difference in month
	public int getmonth(String year) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date parse = sdf.parse(year);
		Calendar c = Calendar.getInstance();
		c.setTime(parse);
		LocalDate userday = LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
		LocalDate today = LocalDate.now();
		Period diff = Period.between(userday, today);
		int b = diff.getMonths();
		return b;
	}

	// to get the difference in years
	public int getyear(String year) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date parse = sdf.parse(year);
		Calendar c = Calendar.getInstance();
		c.setTime(parse);
		LocalDate userday = LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
		LocalDate today = LocalDate.now();
		Period diff = Period.between(userday, today);
		int a = diff.getYears();
		return a;
	}

	// to get the depreciation amount
	public List<Long> getAllDepreciationAmount(int month, int nyear, String name, String fuel) {
		long dep1=0;
		long dep2=0;
		long dep3=0;
		int dep=0;
		List<Long> depreciation = new ArrayList<Long>();
		List<String> all = getCompanyNames();
		Map<String, Integer> go=setDepreciation();
		int ss = all.size();
		for (int i = 0; i < ss; i++) {
			String one = all.get(i);

			if (one.equalsIgnoreCase("TATA AIG") ) {
				dep=(int)go.get("TATA AIG");
				dep1=getDepreciation(month, nyear,dep);
			} 
			if (one.equalsIgnoreCase("HDFC ERGO") ) {
				dep=(int)go.get("HDFC ERGO");
				dep2=getDepreciation(month, nyear,dep);
			} 
			if (one.equalsIgnoreCase("UNITED INDIA") ) {
				dep=(int)go.get("UNITED INDIA");
				dep3=getDepreciation(month, nyear,dep);
			}
		}
		depreciation.add(dep1);
		depreciation.add(dep2);
		depreciation.add(dep3);
		return depreciation;
	}
	
	//to calculate the depreciation amount based on month & year
	public long getDepreciation(int month, int nyear,long dep) {
			long amount=0;
				if ( month < 6 && nyear < 1) {
					amount = dep*1;
				} else if (month > 6 && nyear < 1) {
					amount = dep*2;
				} else if ( nyear >= 1 && nyear < 2) {
					amount = dep*3;
				} else if (nyear >= 2 && nyear < 3) {
					amount =dep*4;
				} else if ( nyear >= 3 && nyear < 4) {
					amount = dep*5;
				} else if (nyear >= 4 && nyear < 5) {
					amount = dep*6;
				}		
			
			return amount;

			
		}
		
	

	// to get the IDV(Insured Declared Value)
	public List<Long> getIDV(String name, String fuel, int month, int year) {
		long depreciation = 0;
		Map<String, List<Insurance>> hm = getDetails();
		List<Long> IDV = new ArrayList<Long>();
		List<Long> depreciation_percentage = getAllDepreciationAmount(month, year, name, fuel);
		int size = depreciation_percentage.size();
		for (Entry<String, List<Insurance>> entry : hm.entrySet()) {
			for (Insurance d : entry.getValue()) {
				boolean n = d.getModelNAme().equalsIgnoreCase(name) && d.getFueltype().equalsIgnoreCase(fuel);
				long insured = d.getShowroom_price();
				if (n) {
					for (int i = 0; i < size; i++) {
						long value = depreciation_percentage.get(i);
						depreciation = (insured / 100) * value;
						long IDVvalue = insured - depreciation;
						IDV.add(IDVvalue);
					}
				}
			}
		}
		
		return IDV;
	}
	// to get the name of the company
	public List<String> getCompanyNames() {
		Map<String, List<Insurance>> hm = getDetails();
		List<String> all = new ArrayList<String>();
		for (Entry<String, List<Insurance>> entry : hm.entrySet()) {
			//System.out.println(entry.getKey());
			all.add(entry.getKey());
		}
		return all;
	}

	// to get the premium amount
	public List<Long> getPremium(String name, String fuel, int month, int no_year) {
		long a = 0;
		Map<String, List<Insurance>> hm = getDetails();
		List<Long> all = new ArrayList<Long>();
		for (Entry<String, List<Insurance>> entry : hm.entrySet()) {
			int i = 0;
			for (Insurance d : entry.getValue()) {
				boolean n = d.getModelNAme().equalsIgnoreCase(name) && d.getFueltype().equalsIgnoreCase(fuel);
				if (n) {
					List<Long> IDV = getIDV(name, fuel, month, no_year);

					long damage = (long) ((IDV.get(i) / 100) * d.getOwn_damage());
					long claim = (damage / 100) * 20;
					long total_own = damage - claim;
					long net = (long) (total_own + d.getThird_party_cover() + d.getCover() + d.getLegal_liability());
					long service = (net / 100) * 14;
					a = net + service;
					all.add(a);
				}
			}
			i++;
		}
		return all;
	}
	public List<InsuranceDetail> getPlans(String name,String fuel,int month,int year){
		List<InsuranceDetail> insuranceDetails=new ArrayList<>();
		List<String> companyNames=getCompanyNames();
		List<Long> premiums=getPremium(name, fuel, month, year);
		List<Long> idvs=getIDV(name, fuel, month, year);
		for(int i=0;i<companyNames.size();i++)
		{
			insuranceDetails.add(new InsuranceDetail(companyNames.get(i), premiums.get(i), idvs.get(i)));
		}
		return insuranceDetails;
	}
}
