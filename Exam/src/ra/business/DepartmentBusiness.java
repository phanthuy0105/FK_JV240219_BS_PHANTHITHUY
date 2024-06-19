package ra.business;

import ra.entity.Department;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class DepartmentBusiness {
    public static List<Department> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Department> listDepartment = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Find_all_department()}");
            ResultSet rs = callSt.executeQuery();
            listDepartment = new ArrayList<>();
            while (rs.next()) {
                Department department = new Department();
                department.setDepartmentId(rs.getString("department_id"));
                department.setDepartmentName(rs.getString("department_name"));
                department.setDescription(rs.getString("description"));
                department.setDepartmentStatus(rs.getBoolean("is_deleted"));
                listDepartment.add(department);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listDepartment;
    }

    public static Department getDepartmentById(String departmentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Department department = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Find_department_by_id(?)}");
            callSt.setString(1, departmentId);
            ResultSet rs = callSt.executeQuery();
            department = new Department();
            if (rs.next()) {
                department.setDepartmentId(rs.getString("department_id"));
                department.setDepartmentName(rs.getString("department_name"));
                department.setDescription(rs.getString("description"));
                department.setDepartmentStatus(rs.getBoolean("is_deleted"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return department;
    }

    public static boolean isExistDepartment(String departmentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call is_exist_department(?,?)}");
            callSt.setString(1, departmentId);
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

    public static boolean create(Department department) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Create_department(?,?,?,?)}");
            callSt.setString(1, department.getDepartmentId());
            callSt.setString(2, department.getDepartmentName());
            callSt.setString(3, department.getDescription());
            callSt.setBoolean(4, department.isDepartmentStatus());
            //Thực hiện procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateDepartment(Department department) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Update_department(?,?,?,?)}");
            callSt.setString(1, department.getDepartmentId());
            callSt.setString(2, department.getDepartmentName());
            callSt.setString(3, department.getDescription());
            callSt.setBoolean(4, department.isDepartmentStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean delete(String departmentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call Delete_department(?)}");
            callSt.setString(1, departmentId);
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
}
