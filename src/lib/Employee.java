package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	
	
	private int yearJoined;
	private int monthJoined;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee( int yearJoined, int monthJoined, boolean isForeigner) {
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.isForeigner = isForeigner;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			monthlySalary = 3000000;
			
		}else if (grade == 2) {
			monthlySalary = 5000000;
		
		}else if (grade == 3) {
			monthlySalary = 7000000;
		
		}else {
			System.err.println("Input grade tidak valid!");
			monthlySalary = 0;
			return;
		}
		if(isForeigner){
			monthlySalary = (int) (monthlySalary * 1.5);
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse( String spouseIdNumber) {
		this.spouseIdNumber = spouseIdNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		calculateMonthsWorked();

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}

		//Memisah logika getAnnualIncomeTax dengan menambah fungsi baru untuk menghitung berapa lama pegawai bekerja dalam setahun dengan tipe private karena fungsi hanya digunakan secara lokal untuk menghitung berapa lama pegawai bekerja dalam setahun.
	private void calculateMonthsWorked() {
        LocalDate currentDate = LocalDate.now();

        if (currentDate.getYear() == yearJoined) {
            monthWorkingInYear = currentDate.getMonthValue() - monthJoined;
        } else {
            monthWorkingInYear = 12; 
        }
    }
}
