package com.x.okr.assemble.control.servlet.workimport;

import com.x.base.core.exception.PromptException;

class UserOrganizationQueryException extends PromptException {

	private static final long serialVersionUID = 1859164370743532895L;

	UserOrganizationQueryException( Throwable e, String userName ) {
		super("系统通过操作用户查询用户身份和组织信息时发生异常!Person:'" + userName +"'.", e );
	}
}
