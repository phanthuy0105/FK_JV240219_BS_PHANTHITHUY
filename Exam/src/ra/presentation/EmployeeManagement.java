package ra.presentation;

import ra.business.DepartmentBusiness;
import ra.business.EmployeeBusiness;
import ra.entity.Department;
import ra.entity.Employee;

import java.util.List;
import java.util.Scanner;

public class EmployeeManagement {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println("*************EMPLOYEE-MANAGEMENT***********");
                System.out.println("1. Quản lý phòng ban");
                System.out.println("2. Quản lý nhân viên");
                System.out.println("3. Thoát");
                System.out.print("Lựa chọn của bạn: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        EmployeeManagement.displayDepartmentMenu(scanner);
                        break;
                    case 2:
                        EmployeeManagement.displayEmployeeMenu(scanner);
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.err.println("Vui lòng chọn từ 1-3");
                }
            } while (true);
        }

        public static void displayDepartmentMenu(Scanner scanner) {
            boolean isExist = true;
            do {
                System.out.println("**********EPAPARTRTMENT-T-MENU**********");
                System.out.println("1. Danh sách phòng ban");
                System.out.println("2. Tạo mới phòng ban");
                System.out.println("3. Cập nhật phòng ban");
                System.out.println("4. Xóa phòng ban");
                System.out.println("5. Thống kê số lượng nhân viên theo mã phòng ban");
                System.out.println("6. Thoát");
                System.out.print("Lựa chọn của bạn: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        EmployeeManagement.displayListDepartmentMenu();
                        break;
                    case 2:
                        EmployeeManagement.createDepartment(scanner);
                        break;
                    case 3:
                        EmployeeManagement.updateDepartment(scanner);
                        break;
                    case 4:
                        EmployeeManagement.deleteDepartment(scanner);
                        break;
                    case 5:
                        break;
                    case 6:
                        isExist = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-6");
                }
            } while (isExist);
        }

        public static void displayListDepartmentMenu() {
            List<Department> listDepartment = DepartmentBusiness.findAll();
            listDepartment.stream().forEach(Department::displayData);
        }

        public static void createDepartment(Scanner scanner) {
            Department departmentNew = new Department();
            departmentNew.inputData(scanner);
            boolean result = DepartmentBusiness.create(departmentNew);
            if (result) {
                System.out.println("Thêm mới phòng ban thành công");
            } else {
                System.err.println("Có lỗi trong quá trình thêm mới phòng ban");
            }
        }

        public static void updateDepartment(Scanner scanner) {
            System.out.println("Nhập vào mã phòng ban cần cập nhật:");
            String departmentId = scanner.nextLine();
            if (DepartmentBusiness.isExistDepartment(departmentId)) {
                Department departmentUpdate = DepartmentBusiness.getDepartmentById(departmentId);
                boolean isExist = true;
                do {
                    System.out.println("Chọn thông tin cần cập nhật:");
                    System.out.println("1. Cập nhật mã phòng ban");
                    System.out.println("2. Cập nhật tên phòng ban");
                    System.out.println("3. Cập nhật mô tả phòng ban");
                    System.out.println("4. Cập nhật trạng thái phòng ban");
                    System.out.println("5. Thoát");
                    System.out.println("Lựa chọn của bạn:");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("Nhập vào mã phòng ban cần cập nhật:");
                            departmentUpdate.setDepartmentId(scanner.nextLine());
                            break;
                        case 2:
                            System.out.println("Nhập vào tên phòng ban cần cập nhật:");
                            departmentUpdate.setDepartmentName(scanner.nextLine());
                            break;
                        case 3:
                            System.out.println("Nhập vào mô tả phòng ban cần cập nhật:");
                            departmentUpdate.setDescription(scanner.nextLine());
                            break;
                        case 4:
                            System.out.println("Nhập vào trạng thái phòng ban cần cập nhật:");
                            departmentUpdate.setDepartmentStatus(Boolean.parseBoolean(scanner.nextLine()));
                            break;
                        default:
                            isExist = false;

                    }
                } while (isExist);
                boolean result = DepartmentBusiness.updateDepartment(departmentUpdate);
                if (result){
                    System.out.println("Cập nhật phòng ban thành công");
                }else{
                    System.out.println("Có lỗi trong quá trình cập nhật phòng ban");
                }
            } else {
                System.err.println("Mã phòng ban không tồn tại");
            }
        }

        public static void deleteDepartment(Scanner scanner) {
            System.out.println("Nhập vào mã phòng ban cần xóa:");
            String departmentId = scanner.nextLine();
            boolean isExist = DepartmentBusiness.isExistDepartment(departmentId);
            if (isExist){
                boolean result = DepartmentBusiness.delete(departmentId);
                if (result){
                    System.out.println("Xóa phòng ban thành công");
                }else{
                    System.err.println("Có lỗi trong quá trình xóa phòng ban");
                }
            }else{
                System.err.println("Mã phòng ban không tồn tại");
            }
        }

        public static void displayEmployeeMenu(Scanner scanner) {
            boolean isExist = true;
            do {
                System.out.println("**********BOOK-MENU***********");
                System.out.println("1. Danh sách nhân viên");
                System.out.println("2. Tạo mới nhân viên");
                System.out.println("3. Cập nhật nhân viên");
                System.out.println("4. Xóa nhân viên");
                System.out.println("5. Hiển thị danh sách nhân viên theo tuổi giảm dần");
                System.out.println("6. Tìm kiếm nhân viên theo học hoặc tên");
                System.out.println("7. Thống kê số lượng nhân viên theo độ tuổi");
                System.out.println("8. Thoát");
                System.out.print("Lựa chọn của bạn: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        EmployeeManagement.displayEmployeeMenu();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        isExist = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-8");
                }
            } while (isExist);
        }

        public static void displayEmployeeMenu() {
            List<Employee> listEmployee = EmployeeBusiness.findAll();
            listEmployee.stream().forEach(Employee::displayData);
        }
}
