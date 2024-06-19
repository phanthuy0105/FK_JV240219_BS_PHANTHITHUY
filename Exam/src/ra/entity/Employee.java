package ra.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Employee implements IEmployeeManagement{
    private int employeeId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phone;
    private String address;
    private double salary;
    private Date createdAt;
    private Date updateAt;
    private boolean employeeStatus;
    private String departmentId;

    public Employee() {
    }

    public Employee(int employeeId, String firstName, String lastName, Date dateOfBirth, String phone, String address, double salary, Date createdAt, Date updateAt, boolean employeeStatus, String departmentId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.employeeStatus = employeeStatus;
        this.departmentId = departmentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public boolean isEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(boolean employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Nhập vào tên và đệm nhân viên");
        this.firstName = scanner.nextLine();
        System.out.println("Nhập vào họ nhân viên");
        this.firstName = scanner.nextLine();
        this.dateOfBirth = inputDateOfBirth(scanner);
        System.out.println("Nhập vào số điện thoại nhân viên");
        this.phone = scanner.nextLine();
        System.out.println("Nhập vào địa chỉ nhân viên");
        this.address = scanner.nextLine();
        System.out.println("Nhập vào lương cơ bản nhân viên");
        this.salary = Double.parseDouble(scanner.nextLine());
        this.createdAt = inputCreated(scanner);
        this.updateAt = inputUpdate(scanner);
        System.out.println("Nhập vào trạng thái nhân viên");
        this.employeeStatus = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("Nhập vào mã phòng ban nhân viên");
        this.departmentId = scanner.nextLine();
    }

    public Date inputDateOfBirth(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Nhập vào ngày sinh nhân viên:");
        do {
            String createdStr = scanner.nextLine();
            try {
                Date created = sdf.parse(createdStr);
                return created;
            } catch (Exception ex) {
                System.err.println("Ngày ngày sinh có định dạng yyyy-MM-dd, vui lòng nhập lại");
            }
        } while (true);
    }

    public Date inputCreated(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Nhập vào ngày tạo:");
        do {
            String createdStr = scanner.nextLine();
            try {
                Date created = sdf.parse(createdStr);
                return created;
            } catch (Exception ex) {
                System.err.println("Ngày tạo có định dạng yyyy-MM-dd, vui lòng nhập lại");
            }
        } while (true);
    }

    public Date inputUpdate(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Nhập vào ngày cập nhật:");
        do {
            String createdStr = scanner.nextLine();
            try {
                Date created = sdf.parse(createdStr);
                return created;
            } catch (Exception ex) {
                System.err.println("Ngày cập nhật có định dạng yyyy-MM-dd, vui lòng nhập lại");
            }
        } while (true);
    }

    @Override
    public void displayData() {
        System.out.printf("Employee ID: %d - First Name: %s - Last Name: %s - Date Of Birth: %s - Phone: %s - Address: %s\n", this.employeeId, this.firstName, this.lastName, this.dateOfBirth, this.phone, this.address);
        System.out.printf("Salary: %.2f - Create At: %s - Update At: %s - Employee Status: %s - Department ID: %s\n", this.salary, this.createdAt, this.updateAt, this.employeeStatus ? "Hoạt động" : "Đã xóa", this.departmentId);
    }
}
