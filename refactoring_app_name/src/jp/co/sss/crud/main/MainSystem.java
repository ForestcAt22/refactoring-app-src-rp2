package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.util.Constants;

/**
 * 社員情報管理システム開始クラス 社員情報管理システムはこのクラスから始まる。<br/>
 * メニュー画面を表示する。
 *
 * @author System Shared
 *
 */
public class MainSystem {
	/**
	 * 社員管理システムを起動
	 *
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int menuNo = 0;

		do {
			// メニューの表示
			System.out.println(Constants.MENU_TITLE);
			System.out.println(Constants.MENU_ALL_FIND);
			System.out.println(Constants.MENU_FIND_BY_EMP);
			System.out.println(Constants.MENU_FIND_BY_DEPTID);
			System.out.println(Constants.MENU_REGISTAR);
			System.out.println(Constants.MENU_UPDATE);
			System.out.println(Constants.MENU_DELETE);
			System.out.println(Constants.MENU_EXIT);
			System.out.print(Constants.IMPUT_PROMPT);

			// メニュー番号の入力
			String menuInput = reader.readLine();
			menuNo = Integer.parseInt(menuInput);

			// 機能の呼出
			switch (menuNo) {
			case 1:
				// 全件表示機能の呼出
				DBController.findAll();
				break;

			case 2:
				// 社員名検索
				System.out.print(Constants.IMPUT_EMPLOYEE_SEARCH);

				// 検索機能の呼出
				DBController.findEmployeesByName();
				break;

			case 3:
				// 検索する部署IDを入力
				System.out.print(Constants.IMPUT_DEPARTMENT_ID_SEARCH);
				String deptId = reader.readLine();

				// 検索機能の呼出
				DBController.findEmployeesByDepartmentId(deptId);
				break;

			case 4:
				// 登録する値を入力
				System.out.print(Constants.IMPUT_EMPLOYEE_NAME);
				String empName = reader.readLine();
				System.out.print(Constants.IMPUT_GENDER);
				String gender = reader.readLine();
				System.out.print(Constants.IMPUT_BIRTHDAY);
				String birthday = reader.readLine();
				System.out.print(Constants.IMPUT_DEPARTMENT_ID);
				String registDeptId = reader.readLine();

				// 登録機能の呼出
				DBController.insert(empName, gender, birthday, registDeptId);
				break;

			case 5:
				// 更新する社員IDを入力
				System.out.print(Constants.IMPUT_UPDATE_EMPLOYEE_ID);

				// 更新する値を入力する
				String updateEmpId = reader.readLine();
				Integer.parseInt(updateEmpId);

				// 更新機能の呼出
				DBController.update(updateEmpId);
				System.out.println(Constants.UPDATE_SUCCESS);

				break;

			case 6:
				// 削除する社員IDを入力
				System.out.print(Constants.IMPUT_DELETE_EMPLOYEE_ID);

				// 削除機能の呼出
				DBController.delete();
				break;

			}
		} while (menuNo != 7);
		System.out.println(Constants.EXIT);
	}
}
