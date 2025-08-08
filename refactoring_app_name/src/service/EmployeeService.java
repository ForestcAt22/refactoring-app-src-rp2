package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.dto.Employee;

public class EmployeeService {

	private DBController employeeDAO;

	public EmployeeService(DBController employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public List<Employee> findEmployeesByDepartmentId(String deptId)
			throws ClassNotFoundException, SQLException, IOException {
		return DBController.findEmployeesByDepartmentId(deptId);
	}

}
