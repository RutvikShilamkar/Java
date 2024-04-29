package employees;

public class ContractEmployee extends Employee{
	
	private double wagePerHour;
	
	public ContractEmployee(String employeeName, double wagePerHour) {
		super(employeeName);
		this.wagePerHour = wagePerHour;
	}
	
	@Override
	public void calculateSalary(float hoursWorked) {
		double salary = 0;
		if(hoursWorked >= 190)
			salary = this.wagePerHour * hoursWorked;
		else 
			salary = this.wagePerHour * hoursWorked - (this.wagePerHour/2) * (190 - hoursWorked);
		super.setSalary(Math.round(salary));
	}
	
	public double getWagePerHour() {
		return this.wagePerHour;
	}
	
	public void setWagePerHour(double wagePerHour) {
		this.wagePerHour = wagePerHour;
	}
	
	@Override
	public String toString() {
		return "Employee Id: "+getEmployeeId()+", Employee Name: "+getEmployeeName()+", Wage Per Hour: "+getWagePerHour();
	}
}
