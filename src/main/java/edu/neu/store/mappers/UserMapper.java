package edu.neu.store.mappers;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import edu.neu.store.entity.User;

/**
 * Process persistence of user data
 * 
 * @author andrew
 *
 */
public interface UserMapper {
	/**
	 * insert user data
	 * 
	 * @param user
	 * @return influence rows
	 */
	Integer addnew(User user);

	/**
	 * Update Password
	 * 
	 * @param uid          user ID
	 * @param password     new password
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return 受影響的行數
	 */
	Integer updatePassword(
			@Param("uid") Integer uid, 
			@Param("password") String password,
			@Param("modifiedUser") String modifiedUser, 
			@Param("modifiedTime") Date modifiedTime
			);
	
	/**
	 * 修改用戶資料（不含用戶名、密碼與頭像）
	 * @param user 用戶資料
	 * @return 受影響的行數
	 */
	Integer updateInfo(User user);
	
	/**
	 * 更新用戶頭像
	 * @param uid 用戶id
	 * @param avatar 頭像的路徑
	 * @param modifiedUser 修改執行人
	 * @param modifiedTime 修改時間
	 * @return 受影響的行數
	 */
	Integer updateAvatar(
		@Param("uid") Integer uid, 
		@Param("avatar") String avatar, 
		@Param("modifiedUser") String modifiedUser, 
		@Param("modifiedTime") Date modifiedTime
	);
	
	/**
	 * find user data by username
	 * 
	 * @param username
	 * @return matched user data, if don't have mathch username, return null
	 */
	User findByUsername(String username);
	
	/**
	 * 根據id查詢用戶數據
	 * @param id 用戶id
	 * @return 匹配的用戶數據，如果沒有匹配的數據，則返回null
	 */
	User findById(Integer id);

}
