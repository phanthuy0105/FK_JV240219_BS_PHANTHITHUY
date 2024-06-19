package ra.business;

import ra.entity.Department;
import ra.entity.Employee;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBusiness {
    public static List<Employee> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employee> listEmployee = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Find_all_employee()}");
            ResultSet rs = callSt.executeQuery();
            listEmployee = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setDateOfBirth(rs.getDate("date_of_birth"));
                employee.setPhone(rs.getString("phone"));
                employee.setAddress(rs.getString("address"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setCreatedAt(rs.getDate("created_at"));
                employee.setUpdateAt(rs.getDate("update_at"));
                employee.setEmployeeStatus(rs.getBoolean("is_deleted"));
                employee.setDepartmentId(rs.getString("department_id"));
                listEmployee.add(employee);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listEmployee;
    }

    public static Employee getEmployeeById(int employeeId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Employee employee = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Find_employee_by_id(?)}");
            callSt.setInt(1, employeeId);
            ResultSet rs = callSt.executeQuery();
            employee = new Employee();
                if (rs.next()) {
                    employee.setEmployeeId(rs.getInt("employee_id"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setDateOfBirth(rs.getDate("date_of_birth"));
                    employee.setPhone(rs.getString("phone"));
                    employee.setAddress(rs.getString("address"));
                    employee.setSalary(rs.getDouble("salary"));
                    employee.setCreatedAt(rs.getDate("created_at"));
                    employee.setUpdateAt(rs.getDate("update_at"));
                    employee.setEmployeeStatus(rs.getBoolean("is_deleted"));
                    employee.setDepartmentId(rs.getString("department_id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employee;
    }

    public static boolean isExistEmployee(int employeeId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call is_exist_employee(?,?)}");
            callSt.setInt(1, employeeId);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            boolean result = callSt.getBoolean(2);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

//    public static boolean create(Employee employee) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        boolean result = false;
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call Create_department(?,?,?,?)}");
//            callSt.setString(1, employee.getFirstName());
//            callSt.setString(2, employee.getLastName());
//            callSt.setDate(3, employee.getDateOfBirth());
//            callSt.setString(4,employee.getPhone());
//            //Thực hiện procedure
//            callSt.executeUpdate();
//            result = true;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return result;
//    }
//
//    public static boolean updateDepartment(Employee employee) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        boolean result = false;
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call Update_department(?,?,?,?)}");
//            callSt.setString(1, department.getDepartmentId());
//            callSt.setString(2, department.getDepartmentName());
//            callSt.setString(3, department.getDescription());
//            callSt.setBoolean(4, department.isDepartmentStatus());
//            callSt.executeUpdate();
//            result = true;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return result;
//    }
//
//    public static boolean delete(String departmentId) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        boolean result = false;
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call Delete_department(?)}");
//            callSt.setString(1, departmentId);
//            callSt.executeUpdate();
//            result = true;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return result;
//    }
}
