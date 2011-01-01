<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="login.model"/><s:text name="title.info"/></td>
        <s:hidden key="login.id"/>
        <s:token/>
    </tr>
    <tr>
		<td class="page-form-label"><s:text name="login.person"/></td>
		<td class="page-form-value">
        	<s:select name="login.personId" list="listMasterPerson" listKey="key" listValue="value" value="login.personId" />
        </td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="login.loginName"/></td>
        <td class="page-form-value"><s:textfield key="login.loginName"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="login.password"/></td>
        <td class="page-form-value"><s:password key="login.hashedPassword"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
    	<td class="page-form-label"><s:text name="login.email"/></td>
    	<td class="page-form-value"><s:textfield key="login.email"/></td>
    	<td></td>
        <td></td>
    </tr>
    <tr>
		<td class="page-form-label"><s:text name="login.enabled"/></td>
		<td class="page-form-value">
        	<s:select name="login.enabled" list="listReferanceBoolean" listKey="key" listValue="value" value="login.enabled" />
        </td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%"></td>
        <td width="30%"></td>
    </tr>
</table> 