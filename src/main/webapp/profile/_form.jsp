<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="profile.model"/><s:text name="title.info"/></td>
        <s:hidden key="profile.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="profile.language"/></td>
        <td class="page-form-value">
        	<s:select name="profile.language" list="listReferanceLanguage" listKey="code" listValue="text" value="profile.language" />
        </td>
        <td class="page-form-label"><s:text name="profile.country"/></td>
        <td class="page-form-value">
        	<s:select name="profile.country" list="listReferanceCountry" listKey="code" listValue="text" value="profile.country" />
        </td>
    </tr>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
    </tr>
</table> 