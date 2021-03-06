package com.x.organization.assemble.control.jaxrs.departmentduty;

import java.util.List;

import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.exception.ExceptionWhen;
import com.x.base.core.utils.SortTools;
import com.x.organization.assemble.control.Business;
import com.x.organization.assemble.control.wrapout.WrapOutDepartmentDuty;
import com.x.organization.core.entity.DepartmentDuty;
import com.x.organization.core.entity.Identity;

public class ActionListWithIdentity extends ActionBase {

	protected List<WrapOutDepartmentDuty> execute(Business business, String identityId) throws Exception {
		EntityManagerContainer emc = business.entityManagerContainer();
		/* 检查Identity是否存在 */
		Identity identity = emc.find(identityId, Identity.class, ExceptionWhen.not_found);
		List<String> ids = business.departmentDuty().listWithIdentity(identity.getId());
		List<WrapOutDepartmentDuty> wraps = outCopier.copy(emc.list(DepartmentDuty.class, ids));
		SortTools.asc(wraps, false, "name");
		return wraps;
	}

}
