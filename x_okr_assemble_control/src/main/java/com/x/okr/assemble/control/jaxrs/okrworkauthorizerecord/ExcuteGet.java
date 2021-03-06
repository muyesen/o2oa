package com.x.okr.assemble.control.jaxrs.okrworkauthorizerecord;

import javax.servlet.http.HttpServletRequest;

import com.x.base.core.http.ActionResult;
import com.x.base.core.http.EffectivePerson;
import com.x.base.core.logger.Logger;
import com.x.base.core.logger.LoggerFactory;
import com.x.okr.assemble.control.jaxrs.okrworkauthorizerecord.exception.AuthorizeRecordIdEmptyException;
import com.x.okr.assemble.control.jaxrs.okrworkauthorizerecord.exception.AuthorizeRecordNotExistsException;
import com.x.okr.assemble.control.jaxrs.okrworkauthorizerecord.exception.AuthorizeRecordQueryByIdException;
import com.x.okr.entity.OkrWorkAuthorizeRecord;

public class ExcuteGet extends ExcuteBase {

	private Logger logger = LoggerFactory.getLogger( ExcuteGet.class );
	
	protected ActionResult<WrapOutOkrWorkAuthorizeRecord> execute( HttpServletRequest request,EffectivePerson effectivePerson, String id ) throws Exception {
		ActionResult<WrapOutOkrWorkAuthorizeRecord> result = new ActionResult<>();
		WrapOutOkrWorkAuthorizeRecord wrap = null;
		OkrWorkAuthorizeRecord okrWorkAuthorizeRecord = null;
		if( id == null || id.isEmpty() ){
			Exception exception = new AuthorizeRecordIdEmptyException();
			result.error( exception );
			//logger.error( e, effectivePerson, request, null);
		}else{
			try {
				okrWorkAuthorizeRecord = okrWorkAuthorizeRecordService.get( id );
				if( okrWorkAuthorizeRecord != null ){
					wrap = wrapout_copier.copy( okrWorkAuthorizeRecord );
					result.setData(wrap);
				}else{
					Exception exception = new AuthorizeRecordNotExistsException( id );
					result.error( exception );
					//logger.error( e, effectivePerson, request, null);
				}
			} catch (Exception e) {
				Exception exception = new AuthorizeRecordQueryByIdException( e, id );
				result.error( exception );
				logger.error( e, effectivePerson, request, null);
			}
		}
		return result;
	}
	
}