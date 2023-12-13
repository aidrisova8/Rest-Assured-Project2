package api2.session23;

import api2.session22.EmployeePojoClass;

import java.util.List;

public class NestedJsonPojoClass {
    private String companyName;
    private String street;
    private String city;
    private int  pinCode;
    private List<String> bankAccount;

    private List<EmployeePojoClass> employeeList;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public List<String> getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(List<String> bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<EmployeePojoClass> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeePojoClass> employeeList) {
        this.employeeList = employeeList;
    }
}
