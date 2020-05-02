package edu.neu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.neu.store.controller.exception.FileEmptyException;
import edu.neu.store.controller.exception.FileSizeOutOfLimitException;
import edu.neu.store.controller.exception.FileTypeNotSupportException;
import edu.neu.store.controller.exception.FileUploadException;
import edu.neu.store.controller.exception.NotLoginException;
import edu.neu.store.controller.exception.RequestException;
import edu.neu.store.service.exception.AccessDeniedException;
import edu.neu.store.service.exception.AddressNotFoundException;
import edu.neu.store.service.exception.DeleteException;
import edu.neu.store.service.exception.DuplicateKeyException;
import edu.neu.store.service.exception.InsertException;
import edu.neu.store.service.exception.PasswordNotMatchException;
import edu.neu.store.service.exception.ServiceException;
import edu.neu.store.service.exception.UpdateException;
import edu.neu.store.service.exception.UserNotFoundException;
import edu.neu.store.util.ResponseResult;

/**
 * 當前項目中所有控制器類的基類
 */
public abstract class BaseController {

	/**
	 * 正確響應時的代號
	 */
	public static final Integer SUCCESS = 200;

	@ExceptionHandler({ ServiceException.class, RequestException.class })
	@ResponseBody
	public ResponseResult<Void> handleException(Exception e) {
		Integer state = null;

		if (e instanceof DuplicateKeyException) {
			// 400-違反了Unique約束的異常
			state = 400;
		} else if (e instanceof UserNotFoundException) {
			// 401-用戶數據不存在
			state = 401;
		} else if (e instanceof PasswordNotMatchException) {
			// 402-密碼錯誤
			state = 402;
		} else if (e instanceof AddressNotFoundException) {
			// 403-收貨地址數據不存在
			state = 403;
		} else if (e instanceof AccessDeniedException) {
			// 404-訪問被拒絕的異常
			state = 404;
		} else if(e instanceof NotLoginException)  {
			// 405-尚未登入
			state = 405;
		}else if (e instanceof InsertException) {
			// 500-插入數據異常
			state = 500;
		} else if (e instanceof UpdateException) {
			// 501-更新數據異常
			state = 501;
		} else if (e instanceof DeleteException) {
			// 502-刪除數據異常
			state = 502;
		} else if (e instanceof FileEmptyException) {
			// 600-上傳的文件為空的異常
			state = 600;
		} else if (e instanceof FileSizeOutOfLimitException) {
			// 601-上傳的文件超出了限制的異常
			state = 601;
		} else if (e instanceof FileTypeNotSupportException) {
			// 602-上傳的文件類型不支持的異常
			state = 602;
		} else if (e instanceof FileUploadException) {
			// 610-文件上傳異常
			state = 610;
		}

		return new ResponseResult<>(state, e);
	}

	/**
	 * 從Session中獲取uid
	 * 
	 * @param session HttpSession對象
	 * @return 當前登錄的用戶的id
	 */
	protected Integer getUidFromSession(HttpSession session) {
		Integer uid = null;
		if(session.getAttribute("uid") == null) {
			throw new NotLoginException("尚未登入");
		}else {
			uid = Integer.valueOf(session.getAttribute("uid").toString());
		}
		return uid;
	}

}
