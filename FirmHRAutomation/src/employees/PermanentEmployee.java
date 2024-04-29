package employees;


import Exceptions.InvalidAssetsException;
import Exceptions.InvalidExperienceException;
import resources.Asset;
import resources.Resources;

public class PermanentEmployee extends Employee {

	private double basicPay;
	private String[] salaryComponents;
	private float experience;
	private Asset[] assets;
	
	public PermanentEmployee(String employeeName, double basicPay, String[] salaryComponents, Asset[] assets) {
		super(employeeName);
		this.basicPay = basicPay;
		this.salaryComponents = salaryComponents;
		this.assets = assets;
	}
	
	public double calculateBonus(float experience) throws InvalidExperienceException {
		if(experience < 2.5) {
			throw new InvalidExperienceException("A minimum of 2.5 years is required for bonus!");
		}
		else if(experience >= 2.5 && experience < 4) {
			return 2550;
		}
		else if(experience >= 4 && experience < 8) {
			return 5000;
		}
		else if(experience >= 8 && experience < 12) {
			return 8750;
		}
		else {
			return 13000;
		}
	}
	
	@Override
	public void calculateSalary(float experience) {
		this.experience = experience;
		
		double daComponent = Double.parseDouble(this.salaryComponents[0].substring(3));
		double hraComponent = Double.parseDouble(this.salaryComponents[1].substring(4));
		double bonus;
		try {
			bonus = this.calculateBonus(experience);
		} catch (InvalidExperienceException e) {
			bonus = 0;
		}
		
		double salary = this.basicPay + ((daComponent/100) + (hraComponent/100)) * this.basicPay + bonus;
		super.setSalary(salary);
	}
	
	public Asset[] getAssetsByDate(String lastDate) throws InvalidAssetsException {
		Asset[] assets = new Asset[this.assets.length];
		boolean assetFound = false;
		int index = 0;
		for(Asset asset : this.assets) {
			String[] assetDate = asset.getAssetExpiry().split("-");
			String[] endDate = lastDate.split("-");
			if(Integer.parseInt(assetDate[0]) < Integer.parseInt(endDate[0])) {
				assets[index++] = asset;
				assetFound = true;
			}
			else if(Integer.parseInt(assetDate[0]) == Integer.parseInt(endDate[0]) 
					&& Resources.getMonth(assetDate[1]) < Resources.getMonth(endDate[1])) {
				assets[index++] = asset;
				assetFound = true;
			}
			else if(Integer.parseInt(assetDate[0]) == Integer.parseInt(endDate[0]) 
					&& Resources.getMonth(assetDate[1]) == Resources.getMonth(endDate[1])
					&& Integer.parseInt(assetDate[2]) <= Integer.parseInt(endDate[2])) {
				assets[index++] = asset;
				assetFound = true;
			}
		}
		if(!assetFound) {
			throw new InvalidAssetsException("No assets found for the given criteria!");
		}
		return assets;
	}

	public double getBasicPay() {
		return basicPay;
	}

	public void setBasicPay(double basicPay) {
		this.basicPay = basicPay;
	}

	public String[] getSalaryComponents() {
		return salaryComponents;
	}

	public void setSalaryComponents(String[] salaryComponents) {
		this.salaryComponents = salaryComponents;
	}

	public float getExperience() {
		return experience;
	}

	public void setExperience(float experience) {
		this.experience = experience;
	}

	public Asset[] getAssets() {
		return assets;
	}

	public void setAssets(Asset[] assets) {
		this.assets = assets;
	}
	
	@Override
	public String toString() {
		return "Employee Id: "+getEmployeeId()+", Employee Name: "+getEmployeeName()+", Basic Pay: "+getBasicPay()+", Salary Components: "+getSalaryComponents()+", Assets: "+getAssets();
	}
}
