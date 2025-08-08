package jp.co.sss.crud.util;

public final class Constants {

	private Constants() {
	} //インスタンス化禁止

	//メニュー関連の定数
	public static final String MENU_TITLE = "=== 社員管理システム ===";
	public static final String MENU_ALL_FIND = "1.全件表示";
	public static final String MENU_FIND_BY_EMP = "2.社員名検索";
	public static final String MENU_FIND_BY_DEPTID = "3.部署ID検索";
	public static final String MENU_REGISTAR = "4.新規登録";
	public static final String MENU_UPDATE = "5.更新";
	public static final String MENU_DELETE = "6.削除";
	public static final String MENU_EXIT = "7.終了";
	public static final String IMPUT_PROMPT = "メニュー番号を入力してください";
	public static final String EXIT = "システムを終了します";

	//DBController関連の定数
	public static final String NO_RESULTS = "該当者はいませんでした";
	public static final String EMPLOYEE_LIST = "社員ID\t社員名\t性別\t生年月日\t部署名";
	public static final String REGISTAR_SUCCESS = "社員情報を登録しました";
	public static final String IMPUT_EMPLOYEE_NAME = "社員名";
	public static final String IMPUT_GENDER = "性別(0:回答しない, 1:男性, 2:女性, 9:その他):";
	public static final String IMPUT_BIRTHDAY = "生年月日(西暦/月/日)：";
	public static final String IMPUT_DEPARTMENT_ID = "部署ID(1：営業部、2：経理部、3：総務部)：";
	public static final String UPDATE_EMPLOYEE_ID = "更新する社員の社員IDを入力してください：";
	public static final String DELETE_EMPLOYEE_ID = "削除する社員の社員IDを入力してください：";
	public static final String UPDATE_SUCCESS = "社員情報を更新しました";
	public static final String DELETE_SUCCESS = "社員情報を削除しました";

	//MainSystem関連の定数
	public static final String IMPUT_EMPLOYEE_SEARCH = "社員名";
	public static final String IMPUT_DEPARTMENT_ID_SEARCH = "部署ID(1:営業部、2:経理部、3:総務部)を入力してください:";
	public static final String IMPUT_UPDATE_EMPLOYEE_ID = "更新する社員の社員IDを入力してください：";
	public static final String IMPUT_DELETE_EMPLOYEE_ID = "削除する社員の社員IDを入力してください：";

	//性別の定数
	public static final int GENDER_NOT_SPECIFIED = 0;
	public static final int GENDER_MALE = 1;
	public static final int GENDER_FEMALE = 2;
	public static final int GENDER_OTHER = 9;

	//性別の文字列定数
	public static final String GENDER_STR_NOT_SPECIFIED = "回答なし";
	public static final String GENDER_STR_MALE = "男性";
	public static final String GENDER_STR_FEMALE = "女性";
	public static final String GENDER_STR_OTHER = "その他";

	//部署の文字列定数
	public static final String DEPARTMENT_STR_SALES = "営業部";
	public static final String DEPARTMENT_STR_ACCOUNTING = "経理部";
	public static final String DEPARTMENT_STR_GENERAL = "総務部";

}
