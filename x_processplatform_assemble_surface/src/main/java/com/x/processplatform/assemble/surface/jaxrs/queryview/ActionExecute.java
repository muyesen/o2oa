package com.x.processplatform.assemble.surface.jaxrs.queryview;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.x.base.core.bean.NameIdPair;
import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.container.factory.EntityManagerContainerFactory;
import com.x.base.core.exception.ExceptionWhen;
import com.x.base.core.gson.XGsonBuilder;
import com.x.base.core.http.ActionResult;
import com.x.base.core.http.EffectivePerson;
import com.x.base.core.logger.Logger;
import com.x.base.core.logger.LoggerFactory;
import com.x.base.core.utils.ListTools;
import com.x.processplatform.assemble.surface.Business;
import com.x.processplatform.assemble.surface.wrapin.element.WrapInQueryExecute;
import com.x.processplatform.core.entity.element.Application;
import com.x.processplatform.core.entity.element.QueryView;
import com.x.processplatform.core.entity.query.DateRangeEntry;
import com.x.processplatform.core.entity.query.FilterEntry;
import com.x.processplatform.core.entity.query.Query;
import com.x.processplatform.core.entity.query.SelectEntry;
import com.x.processplatform.core.entity.query.WhereEntry;

class ActionExecute extends ActionBase {

	private static Logger logger = LoggerFactory.getLogger(ActionExecute.class);

	private static Type filterEntryCollectionType = new TypeToken<List<FilterEntry>>() {
	}.getType();

	private static Type stringCollectionType = new TypeToken<List<String>>() {
	}.getType();

	private Gson gson = XGsonBuilder.instance();

	ActionResult<Query> execute(EffectivePerson effectivePerson, String flag, String applicationFlag,
			JsonElement jsonElement) throws Exception {
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			ActionResult<Query> result = new ActionResult<>();
			WrapInQueryExecute wrapIn = this.convertToWrapIn(jsonElement, WrapInQueryExecute.class);
			Business business = new Business(emc);
			Application application = business.application().pick(applicationFlag);
			QueryView queryView = business.queryView().pick(flag, application, ExceptionWhen.not_found);
			if (!business.queryView().allowRead(effectivePerson, queryView, application)) {
				throw new Exception("insufficient permissions.");
			}
			Query query = this.concrete(queryView, wrapIn);
			query.query();
			/* 整理一下输出值 */
			if ((null != query.getGroupEntry()) && query.getGroupEntry().available()) {
				query.setGrid(null);
			}
			if ((null != query.getCalculate()) && (query.getCalculate().available())) {
				query.setGrid(null);
				query.setGroupGrid(null);
			}
			result.setData(query);
			return result;
		}
	}

	private Query concrete(QueryView queryView, WrapInQueryExecute wrapIn) throws Exception {
		Query query = gson.fromJson(queryView.getData(), Query.class);
		if (null != wrapIn) {
			if (null != wrapIn.getDate()) {
				query.setDateRangeEntry(this.readDateRangeEntry(wrapIn.getDate()));
			}
			if ((null != wrapIn.getFilter()) && (!wrapIn.getFilter().isJsonNull())) {
				query.setFilterEntryList(this.readFilterEntryList(wrapIn.getFilter()));
			}
			if ((null != wrapIn.getColumn()) && (!wrapIn.getColumn().isJsonNull())) {
				query.setColumnList(this.readColumnList(wrapIn.getColumn(), query.getSelectEntryList()));
			}
			WhereEntry whereEntry = this.readWhereEntry(wrapIn.getApplication(), wrapIn.getProcess(),
					wrapIn.getCompany(), wrapIn.getDepartment(), wrapIn.getPerson(), wrapIn.getIdentity());
			if (whereEntry.available()) {
				query.setWhereEntry(whereEntry);
			}
		}
		return query;
	}

	private WhereEntry readWhereEntry(JsonElement application, JsonElement process, JsonElement company,
			JsonElement department, JsonElement person, JsonElement identity) throws Exception {
		WhereEntry whereEntry = new WhereEntry();
		if (null != application && (!application.isJsonNull())) {
			whereEntry.setApplicationList(this.readApplication(application));
		}
		if (null != process && (!process.isJsonNull())) {
			whereEntry.setProcessList(this.readProcess(process));
		}
		if (null != company && (!company.isJsonNull())) {
			whereEntry.setCompanyList(this.readCompany(company));
		}
		if (null != department && (!department.isJsonNull())) {
			whereEntry.setDepartmentList(this.readDepartment(department));
		}
		if (null != person && (!person.isJsonNull())) {
			whereEntry.setPersonList(this.readPerson(person));
		}
		if (null != identity && (!identity.isJsonNull())) {
			whereEntry.setIdentityList(this.readIdentity(identity));
		}
		return whereEntry;
	}

	private List<NameIdPair> readApplication(JsonElement o) throws Exception {
		List<NameIdPair> results = new ArrayList<>();
		List<String> flags = new ArrayList<>();
		if (o.isJsonArray()) {
			flags = XGsonBuilder.instance().fromJson(o, stringCollectionType);
		} else if (o.isJsonPrimitive() && o.getAsJsonPrimitive().isString()) {
			flags.add(o.getAsJsonPrimitive().getAsString());
		}
		for (String str : flags) {
			NameIdPair p = new NameIdPair(str, str);
			results.add(p);
		}
		return results;
	}

	private List<NameIdPair> readProcess(JsonElement o) throws Exception {
		List<NameIdPair> results = new ArrayList<>();
		List<String> flags = new ArrayList<>();
		if (o.isJsonArray()) {
			flags = XGsonBuilder.instance().fromJson(o, stringCollectionType);
		} else if (o.isJsonPrimitive() && o.getAsJsonPrimitive().isString()) {
			flags.add(o.getAsJsonPrimitive().getAsString());
		}
		for (String str : flags) {
			NameIdPair p = new NameIdPair(str, str);
			results.add(p);
		}
		return results;
	}

	private List<NameIdPair> readCompany(JsonElement o) throws Exception {
		List<NameIdPair> results = new ArrayList<>();
		List<String> flags = new ArrayList<>();
		if (o.isJsonArray()) {
			flags = XGsonBuilder.instance().fromJson(o, stringCollectionType);
		} else if (o.isJsonPrimitive() && o.getAsJsonPrimitive().isString()) {
			flags.add(o.getAsJsonPrimitive().getAsString());
		}
		for (String str : flags) {
			NameIdPair p = new NameIdPair(str, str);
			results.add(p);
		}
		return results;
	}

	private List<NameIdPair> readDepartment(JsonElement o) throws Exception {
		List<NameIdPair> results = new ArrayList<>();
		List<String> flags = new ArrayList<>();
		if (o.isJsonArray()) {
			flags = XGsonBuilder.instance().fromJson(o, stringCollectionType);
		} else if (o.isJsonPrimitive() && o.getAsJsonPrimitive().isString()) {
			flags.add(o.getAsJsonPrimitive().getAsString());
		}
		for (String str : flags) {
			NameIdPair p = new NameIdPair(str, str);
			results.add(p);
		}
		return results;
	}

	private List<NameIdPair> readIdentity(JsonElement o) throws Exception {
		List<NameIdPair> results = new ArrayList<>();
		List<String> flags = new ArrayList<>();
		if (o.isJsonArray()) {
			flags = XGsonBuilder.instance().fromJson(o, stringCollectionType);
		} else if (o.isJsonPrimitive() && o.getAsJsonPrimitive().isString()) {
			flags.add(o.getAsJsonPrimitive().getAsString());
		}
		for (String str : flags) {
			NameIdPair p = new NameIdPair(str, str);
			results.add(p);
		}
		return results;
	}

	private List<NameIdPair> readPerson(JsonElement o) throws Exception {
		List<NameIdPair> results = new ArrayList<>();
		List<String> flags = new ArrayList<>();
		if (o.isJsonArray()) {
			flags = XGsonBuilder.instance().fromJson(o, stringCollectionType);
		} else if (o.isJsonPrimitive() && o.getAsJsonPrimitive().isString()) {
			flags.add(o.getAsJsonPrimitive().getAsString());
		}
		for (String str : flags) {
			NameIdPair p = new NameIdPair(str, str);
			results.add(p);
		}
		return results;
	}

	private DateRangeEntry readDateRangeEntry(DateRangeEntry o) throws Exception {
		if (!o.available()) {
			throw new Exception("dateRangeEntry not available:" + o);
		}
		return o;
	}

	private List<FilterEntry> readFilterEntryList(JsonElement o) throws Exception {
		List<FilterEntry> list = new ArrayList<>();
		if (o.isJsonArray()) {
			list = XGsonBuilder.instance().fromJson(o, filterEntryCollectionType);
		} else if (o.isJsonObject()) {
			list.add(XGsonBuilder.instance().fromJson(o, FilterEntry.class));
		}
		for (FilterEntry entry : list) {
			if (!entry.available()) {
				throw new Exception("filterEntry not available:" + entry);
			}
		}
		return list;
	}

	private List<String> readColumnList(JsonElement o, List<SelectEntry> selectEntryList) throws Exception {
		List<String> columns = new ArrayList<>();
		if (o.isJsonArray()) {
			/* 多值 */
			for (JsonElement element : o.getAsJsonArray()) {
				String column = this.getColumn(element, selectEntryList);
				if (StringUtils.isNotEmpty(column)) {
					columns.add(column);
				}
			}
		} else if (o.isJsonPrimitive()) {
			/* 单值 */
			String column = this.getColumn(o, selectEntryList);
			if (StringUtils.isNotEmpty(column)) {
				columns.add(column);
			}
		}
		return columns;
	}

	private String getColumn(JsonElement o, List<SelectEntry> list) throws Exception {
		if (null == o || o.isJsonNull() || (!o.isJsonPrimitive()) || ListTools.isEmpty(list)) {
			return null;
		}
		if (o.getAsJsonPrimitive().isNumber()) {
			Integer idx = o.getAsJsonPrimitive().getAsInt();
			if ((idx >= 1) && (idx <= (list.size() + 1))) {
				return list.get(idx - 1).getColumn();
			}
		} else if (o.getAsJsonPrimitive().isString()) {
			String str = o.getAsJsonPrimitive().getAsString();
			for (SelectEntry entry : list) {
				if (StringUtils.equals(entry.getColumn(), str)) {
					return entry.getColumn();
				}
			}
		}
		return null;
	}
}