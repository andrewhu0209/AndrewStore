package edu.neu.store.service;

import edu.neu.store.entity.User;
import edu.neu.store.service.exception.DuplicateKeyException;
import edu.neu.store.service.exception.InsertException;
import edu.neu.store.service.exception.PasswordNotMatchException;
import edu.neu.store.service.exception.UpdateException;
import edu.neu.store.service.exception.UserNotFoundException;

/**
 * 處理用戶數據的業務層接口
 */
public interface IUserService {

	/**
	 * User Registration
	 * 
	 * @param user 用戶的註冊信息
	 * @return 成功註冊的用戶數據
	 * @throws DuplicateKeyException
	 * @throws InsertException
	 */
	User reg(User user) throws DuplicateKeyException, InsertException;

	/**
	 * User Login
	 * 
	 * @param username
	 * @param password
	 * @return success login user data
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 */
	User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException;

	/**
	 * Edit Password
	 * 
	 * @param uid         用户id
	 * @param oldPassword 原密码
	 * @param newPassword 新密码
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 * @throws UpdateException
	 */
	void changePassword(Integer uid, String oldPassword, String newPassword)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException;

	/**
	 * 修改用戶個人資料
	 * 
	 * @param user 用戶數據
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeInfo(User user) throws UserNotFoundException, UpdateException;

	/**
	 * 修改頭像
	 * 
	 * @param uid    用戶id
	 * @param avatar 頭像路徑
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeAvatar(Integer uid, String avatar) throws UserNotFoundException, UpdateException;

	/**
	 * 根據id獲取用戶數據
	 * 
	 * @param id 用戶id
	 * @return 匹配的用戶數據，如果沒有匹配的數據，則返回null
	 */
	User getById(Integer id);
}
