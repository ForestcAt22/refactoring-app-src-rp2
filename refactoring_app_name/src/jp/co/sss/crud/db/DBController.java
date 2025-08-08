package jp.co.sss.crud.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.sss.crud.util.ConstantSQL;
import jp.co.sss.crud.util.Constants;

/**
 * DB操作処理用のクラス
 *
 * @author System Shared
 */
public class DBController {

	/** インスタンス化を禁止 */
	private DBController() {
	}

	/**
	 * 全ての社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 */
	public static void findAll() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_SELECT_ALL_EMPLOYEES);

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			//resultSetの結果Setがない場合はfalse
			if (!resultSet.isBeforeFirst()) {
				System.out.println(Constants.NO_RESULTS);
				return;
			}

			// レコードを出力
			System.out.println(Constants.EMPLOYEE_LIST);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id") + "\t");
				System.out.print(resultSet.getString("emp_name") + "\t");

				int gender = Integer.parseInt(resultSet.getString("gender"));
				if (gender == Constants.GENDER_NOT_SPECIFIED) {
					System.out.print("回答なし" + "\t");
				} else if (gender == Constants.GENDER_MALE) {
					System.out.print("男性" + "\t");

				} else if (gender == Constants.GENDER_FEMALE) {
					System.out.print("女性" + "\t");

				} else if (gender == Constants.GENDER_OTHER) {
					System.out.print("その他" + "\t");

				}

				System.out.print(resultSet.getString("birthday") + "\t");
				System.out.println(resultSet.getString("dept_name"));
			}

			System.out.println("");
		} finally {
			// ResultSetをクローズ
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員名に該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findEmployeesByName() throws ClassNotFoundException, SQLException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 検索ワード
		String keyword = br.readLine();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_ALL_EMPLOYEE_BASE);
			sql.append(ConstantSQL.SQL_SELECT_BY_EMP_NAME);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setString(1, "%" + keyword + "%");

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				System.out.println(Constants.NO_RESULTS);
				return;
			}

			System.out.println(Constants.EMPLOYEE_LIST);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id"));
				System.out.print("\t");

				System.out.print(resultSet.getString("emp_name"));
				System.out.print("\t");

				String genderValue = resultSet.getString("gender");
				int gender = Integer.parseInt(genderValue);
				if (gender == Constants.GENDER_NOT_SPECIFIED) {
					System.out.print(Constants.GENDER_STR_NOT_SPECIFIED);
				} else if (gender == Constants.GENDER_MALE) {
					System.out.print(Constants.GENDER_STR_MALE);

				} else if (gender == Constants.GENDER_FEMALE) {
					System.out.print(Constants.GENDER_STR_FEMALE);

				} else if (gender == Constants.GENDER_OTHER) {
					System.out.print(Constants.GENDER_STR_OTHER);

				}

				System.out.print("\t");
				System.out.print(resultSet.getString("birthday"));
				System.out.print("\t");

				System.out.println(resultSet.getString("dept_name"));

			}

		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 部署IDに該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findEmployeesByDepartmentId(String deptId)
			throws ClassNotFoundException, SQLException, IOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_ALL_EMPLOYEE_BASE);
			sql.append(ConstantSQL.SQL_SELECT_BY_DEPT_ID);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setString(1, deptId);

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println(Constants.NO_RESULTS);
				return;
			}

			System.out.println(Constants.EMPLOYEE_LIST);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id"));
				System.out.print("\t");

				System.out.print(resultSet.getString("emp_name"));
				System.out.print("\t");

				String genderString = resultSet.getString("gender");
				int gender = Integer.parseInt(genderString);
				if (gender == Constants.GENDER_NOT_SPECIFIED) {
					System.out.print(Constants.GENDER_STR_NOT_SPECIFIED);
				} else if (gender == Constants.GENDER_MALE) {
					System.out.print(Constants.GENDER_STR_MALE);

				} else if (gender == Constants.GENDER_FEMALE) {
					System.out.print(Constants.GENDER_STR_FEMALE);

				} else if (gender == Constants.GENDER_OTHER) {
					System.out.print(Constants.GENDER_STR_OTHER);

				}

				System.out.print("\t");
				System.out.print(resultSet.getString("birthday"));
				System.out.print("\t");

				String deptIdString = resultSet.getString("dept_id");
				int deptIdValue = Integer.parseInt(deptIdString);
				if (deptIdValue == 1) {
					System.out.print("営業部");
				} else if (deptIdValue == 2) {
					System.out.print("経理部");
				} else if (gender == 3) {
					System.out.print("総務部");

				}

			}

		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件登録
	 * 
	 * @param empName 社員名
	 * @param gender 性別
	 * @param birthday 生年月日
	 * @param deptId 部署ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 */
	public static void insert(String empName, String gender, String birthday, String deptId)
			throws ClassNotFoundException, SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT);

			// 入力値をバインド
			preparedStatement.setString(1, empName);
			preparedStatement.setString(2, gender);
			preparedStatement.setString(3, birthday);
			preparedStatement.setString(4, deptId);

			// SQL文を実行
			preparedStatement.executeUpdate();

			// 登録完了メッセージを出力
			System.out.println(Constants.REGISTAR_SUCCESS);
		} finally {
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件更新
	 * 
	 * @param empId 社員ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 */
	public static void update(String empId)
			throws ClassNotFoundException, SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE);

			System.out.print(Constants.IMPUT_EMPLOYEE_NAME);
			String emp_name = br.readLine();
			// 性別を入力
			System.out.print(Constants.IMPUT_GENDER);
			String gender = br.readLine();
			// 誕生日を入力
			System.out.print(Constants.IMPUT_BIRTHDAY);
			String birthday = br.readLine();

			// 部署IDを入力
			System.out.print(Constants.IMPUT_DEPARTMENT_ID);
			String deptId = br.readLine();

			// 入力値をバインド
			preparedStatement.setString(1, emp_name);
			preparedStatement.setString(2, gender);
			preparedStatement.setString(3, birthday);
			preparedStatement.setString(4, deptId);
			preparedStatement.setString(5, empId);

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

		} finally {
			// クローズ処理
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件削除
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void delete() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();
			String empId = br.readLine();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_DELETE);

			// 社員名をバインド
			preparedStatement.setString(1, empId);

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

			System.out.println(Constants.DELETE_SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();

		}

		finally {
			// Statementをクローズ
			try {
				DBManager.close(preparedStatement);
				DBManager.close(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// DBとの接続を切断
		}
	}
}
