package edu.neu.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.neu.store.controller.exception.FileEmptyException;
import edu.neu.store.entity.User;
import edu.neu.store.service.IUserService;
import edu.neu.store.util.ResponseResult;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	/**
	 * 上傳文件夾的名稱
	 */
	private static final String UPLOAD_DIR_NAME = "upload";
	/**
	 * 上傳文件的最大大小
	 */
	private static final long FILE_MAX_SIZE = 5 * 1024 * 1024;
	/**
	 * 允許上傳的文件類型
	 */
	private static final List<String> FILE_CONTENT_TYPES = new ArrayList<>();

	/**
	 * 初始化允許上傳的文件類型的集合
	 */
	static {
		FILE_CONTENT_TYPES.add("image/jpeg");
		FILE_CONTENT_TYPES.add("image/png");
	}

	@Autowired
	private IUserService userService;
	
	@GetMapping("logout.do")
	public ResponseResult<Void> handleLouout(HttpSession session) {
		session.invalidate();
		return new ResponseResult<Void>(SUCCESS);
	}

	@GetMapping("checkLogin.do")
	public ResponseResult<String> handleCheckLogin(HttpSession session) {

		Integer uid = getUidFromSession(session);
		String username = (String) session.getAttribute("username");
		// 返回
		ResponseResult<String> rr = new ResponseResult<>();
		rr.setState(SUCCESS);
		rr.setData(username);
		return rr;
	}

	@PostMapping("/reg.do")
	public ResponseResult<Void> handleReg(User user) {
		System.out.println("reg.do");
		userService.reg(user);
		return new ResponseResult<Void>(SUCCESS);
	}

	@PostMapping("/login.do")
	public ResponseResult<User> handleLogin(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		// 執行登錄
		User user = userService.login(username, password);
		// 將相關信息存入到Session
		session.setAttribute("uid", user.getId());
		session.setAttribute("username", user.getUsername());
		// 返回
		return new ResponseResult<>(SUCCESS, user);
	}

	@RequestMapping("/password.do")
	public ResponseResult<Void> changePassword(@RequestParam("old_password") String oldPassword,
			@RequestParam("new_password") String newPassword, HttpSession session) {
		// 獲取當前登錄的用戶的id
		Integer uid = getUidFromSession(session);
		// 執行修改密碼
		userService.changePassword(uid, oldPassword, newPassword);
		// 返回
		return new ResponseResult<>(SUCCESS);
	}

	@RequestMapping("/info.do")
	public ResponseResult<User> getInfo(HttpSession session) {
		// 獲取當前登錄的用戶的id
		Integer id = getUidFromSession(session);
		// 執行查詢，獲取用戶數據
		User user = userService.getById(id);
		// 返回
		return new ResponseResult<User>(SUCCESS, user);
	}

	@PostMapping("/change_info.do")
	public ResponseResult<Void> changeInfo(User user, HttpSession session) {
		// 獲取當前登錄的用戶的id
		Integer id = getUidFromSession(session);
		// 將id封裝到參數user中，因為user是用戶提交的數據，並不包含id
		user.setId(id);
		// 執行修改
		userService.changeInfo(user);
		// 返回
		return new ResponseResult<>(SUCCESS);
	}

	@PostMapping("/upload.do")
	public ResponseResult<String> handleUpload(HttpSession session, @RequestParam("file") MultipartFile file) {
		// 檢查是否存在上傳文件 > file.isEmpty()
		if (file.isEmpty()) {
			// 拋出異常：文件不允許為空
			throw new FileEmptyException("上傳失敗！沒有選擇上傳的文件，或選中的文件為空！");
		}

		// TODO 檢查文件大小 > file.getSize()
		if (file.getSize() > FILE_MAX_SIZE) {
			// 拋出異常：文件大小超出限制
		}

		// TODO 檢查文件類型 > file.getContentType()
		if (!FILE_CONTENT_TYPES.contains(file.getContentType())) {
			// 拋出異常：文件類型限制
		}

		// 確定上傳文件夾 > session.getServletContext.getRealPath(UPLOAD_DIR_NAME) > exists() >
		// mkdirs()
		String parentPath = session.getServletContext().getRealPath(UPLOAD_DIR_NAME);
		// parentPath: /Users/andrew/eclipse-workspace/store/src/main/webapp/upload

		File parent = new File(parentPath);
		if (!parent.exists()) {
			parent.mkdirs();
		}

		// 確定文件名 > getOriginalFileName()
		String originalFileName = file.getOriginalFilename();
		int beginIndex = originalFileName.lastIndexOf(".");
		String suffix = originalFileName.substring(beginIndex);
		String fileName = System.currentTimeMillis() + "" + (new Random().nextInt(90000000) + 10000000) + suffix;

		// 確定文件
		File dest = new File(parent, fileName);

		// 執行保存文件
		try {
			file.transferTo(dest);
			System.err.println("上傳完成！");
		} catch (IllegalStateException e) {
			// 拋出異常：上傳失敗
		} catch (IOException e) {
			// 拋出異常：上傳失敗
		}

		// 獲取當前用戶的id
		Integer uid = getUidFromSession(session);
		// 更新頭像數據
		String avatar = "/" + UPLOAD_DIR_NAME + "/" + fileName;
		userService.changeAvatar(uid, avatar);

		// 返回
		ResponseResult<String> rr = new ResponseResult<>();
		rr.setState(SUCCESS);
		rr.setData(avatar);
		return rr;
	}

}
