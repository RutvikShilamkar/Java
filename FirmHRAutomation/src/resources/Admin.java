package resources;

import Exceptions.InvalidAssetsException;
import employees.ContractEmployee;
import employees.Employee;
import employees.PermanentEmployee;

public class Admin {
	public void generateSalarySlip(Employee[] employees, float[] salaryFactor) {
		for(int i = 0; i < employees.length; i++) {
			if(employees[i] instanceof PermanentEmployee) {				
			 	((PermanentEmployee) employees[i]).calculateSalary(salaryFactor[i]);
			}
			if(employees[i] instanceof ContractEmployee) {				
			 	((ContractEmployee) employees[i]).calculateSalary(salaryFactor[i]);
			}
		}
	}
	
	public int generateAssetsReport(Employee[] employees, String lastDate) {
		Asset[] assets = null;
		int totalAssets = 0;
		for(Employee employee : employees) {
			if(employee instanceof PermanentEmployee) {
				try {
					assets = ((PermanentEmployee) employee).getAssetsByDate(lastDate);
					for(Asset asset : assets) {
						if(asset != null) {
							totalAssets++;
						}
					}
				} catch (InvalidAssetsException e) {
					return -1;
				}
			}
		}
		return totalAssets;
	}
	
	public String[] generateAssetsReport(Employee[] employees, char assetCategory) {
		String[] assetIds = new String[employees.length * 3];
		int index = 0;
		for(Employee employee : employees) {
			if( employee instanceof PermanentEmployee) {				
				Asset[] assets= ((PermanentEmployee) employee).getAssets();
				for(Asset asset: assets) {
					if(Character.toLowerCase(assetCategory) == Character.toLowerCase(asset.getAssetId().charAt(0))) {
						assetIds[index++] = asset.getAssetId();
					}
				}
			}
		}
		
		
		
		return assetIds;
	}
}
