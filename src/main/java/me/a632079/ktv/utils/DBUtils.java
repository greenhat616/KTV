package me.a632079.ktv.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

/**
 * @className: DBUtils
 * @description: JBDC 工具类封装
 * @version: v1.0.0
 * @date: 2022/5/13 13:38
 * @author: haoduor
 */

public class DBUtils {

	private Connection conn;
	private Properties properties;

	static {
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public DBUtils() {
		init();
	}

	public void init() {
		try {
			// conn = DriverManager.getConnection(url, username, password);
			conn = DriverManager.getConnection("jdbc:mysql://192.168.177.130:3306/gugu", "haoduor", "qwe123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet doSelect(String sql) {
		Statement statement = null;
		try {
			statement = conn.createStatement();
			return statement.executeQuery(sql);
		} catch (SQLException throwables) {
			return null;
		}
	}

	public ResultSet doSelect(String sql, Object[] para) {
		try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			for (int i = 0; i < para.length; i++) {
				preparedStatement.setObject(i + 1, para);
			}

			return preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public int doQuery(String sql) {
		try (Statement statement = conn.createStatement()) {
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public int doQuery(String sql, Object[] para) {
		try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			for (int i = 0; i < para.length; i++) {
				preparedStatement.setObject(i + 1, para);
			}

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static final Map<Class, Method> valueOf = new HashMap<>();

	public void cacheRef() {
		try {
			Method intMethod = ResultSet.class.getMethod("getInt", String.class);
			Method longMethod = ResultSet.class.getMethod("getLong", String.class);
			Method floatMethod = ResultSet.class.getMethod("getFloat", String.class);
			Method doubleMethod = ResultSet.class.getMethod("getDouble", String.class);
			Method stringMethod = ResultSet.class.getMethod("getString", String.class);
			Method booleanMethod = ResultSet.class.getMethod("getBoolean", int.class);

			valueOf.put(Integer.class, intMethod);
			valueOf.put(Long.class, longMethod);
			valueOf.put(String.class, stringMethod);
			valueOf.put(Boolean.class, booleanMethod);
			valueOf.put(Double.class, doubleMethod);
			valueOf.put(Float.class, floatMethod);

			valueOf.put(Integer.TYPE, intMethod);
			valueOf.put(Long.TYPE, longMethod);
			valueOf.put(Boolean.TYPE, booleanMethod);
			valueOf.put(Double.TYPE, doubleMethod);
			valueOf.put(Float.TYPE, floatMethod);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public <T> List<T> convertResult(ResultSet resultSet, T tmplate) {
		Field[] fields = tmplate.getClass().getDeclaredFields();
		List<T> list = new LinkedList<>();

		Map<String, Class> templateMap = new HashMap<>();
		Map<String, Field> fieldMap = new HashMap<>();
		for (Field f : fields) {
			f.setAccessible(true);
			Class<?> type = f.getType();

			String name = f.getName();
			templateMap.put(name, type);
			fieldMap.put(name, f);
		}
		try {
			while (resultSet.next()) {
				T o = (T) tmplate.getClass().newInstance();

				for (String k : templateMap.keySet()) {
					Method method = valueOf.get(templateMap.get(k));
					try {
						Object tmp = method.invoke(resultSet, k);
						Field field = fieldMap.get(k);
						field.setAccessible(true);
						field.set(o, tmp);
					} catch (IllegalAccessException | InvocationTargetException e) {
						templateMap.remove(k);
					}
				}

				list.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Map<String, Object>> convertMap(ResultSet resultSet) {
		List<Map<String, Object>> list = new LinkedList<>();
		List<String> colNames = new LinkedList<>();
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();
			for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
				colNames.add(metaData.getColumnName(i).toLowerCase());
			}
		} catch (SQLException e) {
			return null;
		}

		try {
			while (resultSet.next()) {
				Map<String, Object> map = new HashMap<>();

				for (String colName : colNames) {
					map.put(colName, resultSet.getObject(colName));
				}

				list.add(map);
			}
		} catch (SQLException throwables) {
			return null;
		}

		return list;
	}

	public void close() {
	}
}
