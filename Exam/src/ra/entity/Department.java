package ra.entity;

import java.util.Scanner;

public class Department implements IEmployeeManagement {
    private String departmentId;
    private String departmentName;
    private String description;
    private boolean departmentStatus;

    public Department() {
    }

    public Department(String departmentId, String departmentName, String description, boolean departmentStatus) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.description = description;
        this.departmentStatus = departmentStatus;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(boolean departmentStatus) {
        this.departmentStatus = departmentStatus;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Nhập vào mã phòng ban");
        this.departmentId = scanner.nextLine();
        System.out.println("Nhập vào tên phòng ban");
        this.departmentName = scanner.nextLine();
        System.out.println("Nhập vào mô tả phòng ban");
        this.description = scanner.nextLine();
        System.out.println("Nhập vào trạng thái phòng ban");
        this.departmentStatus = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("Department ID: %s - Department Name: %s - Description: %s - Department Status: %s\n", this.departmentId, this.departmentName, this.description, this.departmentStatus ? "Hoạt động" : "Đã xóa");
    }
}
