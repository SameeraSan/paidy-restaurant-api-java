package com.paidy.restaurant.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paidy.restaurant.config.SystemConfig;
import com.paidy.restaurant.model.Items;
import com.paidy.restaurant.model.Order;
import com.paidy.restaurant.util.UtilMethods;

public class DatabaseProcessing {
	
	public static List<Items> getTableItems(int tableNo) throws Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		List<Items> list = new ArrayList<>(); 
	
		try {
			con = DatabasePool.getConnection();

			if (con != null) {

			
				String query = "SELECT o.id AS order_id, "
						+ " o.table_id AS table_id, "
						+ " o.item_id AS item_id, "
						+ " o.status AS status, "
						+ " o.cook_time AS cook_time, "
						+ " o.quantity AS quantity, "
						+ " i.name AS item_name "
						+ " FROM ORDERS o JOIN ITEMS i ON o.item_id = i.id "
						+ " WHERE o.table_id = ? "
						+ " AND o.status IN (?,?)";
				
				ps = con.prepareStatement(query);
				ps.setInt(1, tableNo);
				ps.setInt(2, SystemConfig.ST_RECEIVED);
				ps.setInt(3, SystemConfig.ST_PREPARING);
				
				rs = ps.executeQuery();

				while (rs.next()) {

					list.add(new Items(rs.getInt("item_id"), rs.getString("item_name"), rs.getInt("status"), rs.getInt("cook_time"), rs.getInt("quantity")));
				}				

			}

		} catch (Exception e) {

			throw e;

		} finally {

			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			DatabasePool.releaseConnectionToPool(con);
		}

		return list;
	}

	
	public static Items getTableSpecifiedItems(int tableNo, int itemNo) throws Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
	
		try {
			con = DatabasePool.getConnection();

			if (con != null) {

			
				String query = "SELECT o.id AS order_id, "
						+ " o.table_id AS table_id, "
						+ " o.item_id AS item_id, "
						+ " o.status AS status, "
						+ " o.cook_time AS cook_time, "
						+ " o.quantity AS quantity, "
						+ " i.name AS item_name "
						+ " FROM ORDERS o JOIN ITEMS i ON o.item_id = i.id "
						+ " WHERE o.table_id = ? "
						+ " AND o.item_id = ? "
						+ " AND o.status NOT IN (?,?)";
				
				ps = con.prepareStatement(query);
				ps.setInt(1, tableNo);
				ps.setInt(2, itemNo);
				ps.setInt(3, SystemConfig.ST_DELIVERED);
				ps.setInt(4, SystemConfig.ST_CANCELLED);
				
				rs = ps.executeQuery();

				if (rs.next()) {

					return new Items(rs.getInt("item_id"), rs.getString("item_name"), rs.getInt("status"), rs.getInt("cook_time"), rs.getInt("quantity"));
				}				

			}

		} catch (Exception e) {

			throw e;

		} finally {

			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			DatabasePool.releaseConnectionToPool(con);
		}

		return null;
	}
	
	public static void insertOrder(Order order) throws Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
	
		try {
			con = DatabasePool.getConnection();

			if (con != null) {

			
				String query = "INSERT INTO orders (order_id, table_id, item_id, status, cook_time, quantity, notes) "
						+" VALUES(?,?,?,?,?,?,?)";
				
				ps = con.prepareStatement(query);
				
				for(Items i: order.getItems()) {
					ps.setString(1, order.getOrderId());
					ps.setInt(2, order.getTableId());
					ps.setInt(3, i.getItemNo());
					ps.setInt(4, SystemConfig.ST_RECEIVED);
					ps.setInt(5, UtilMethods.generateRandomTime());
					ps.setInt(6, i.getQuantity());
					ps.setString(7, order.getNotes());
					ps.addBatch();
				}

				ps.executeBatch();
				

			}

		} catch (Exception e) {

			throw e;

		} finally {

			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			DatabasePool.releaseConnectionToPool(con);
		}

	}
	
	public static int deleteOrder(int tableNo, int itemNo) throws Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		int affectedRows = 0;
	
		try {
			con = DatabasePool.getConnection();
			if (con != null) {

			
				String query = "DELETE FROM orders WHERE table_id = ? AND item_id = ? AND status = ? ";
				
				ps = con.prepareStatement(query);
				ps.setInt(1, tableNo);
				ps.setInt(2, itemNo);
				ps.setInt(3, SystemConfig.ST_RECEIVED);
				
				affectedRows = ps.executeUpdate();
							
			}

		} catch (Exception e) {

			throw e;

		} finally {

			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			DatabasePool.releaseConnectionToPool(con);
		}

		return affectedRows;
	}
	

}
