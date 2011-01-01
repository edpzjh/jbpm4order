<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="mailTemplate.model"/><s:text name="title.info"/></td>
        <s:hidden key="mailTemplate.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="mailTemplate.name"/></td>
        <td class="page-form-value"><s:textfield key="mailTemplate.name"/></td>
        <td></td>
        <td></td>
	</tr>
	<tr>
        <td class="page-form-label"><s:text name="mailTemplate.lang"/></td>
        <td class="page-form-value"><s:select name="mailTemplate.lang" list="listReferanceLang" listKey="key" listValue="value" value="mailTemplate.lang" /></td>
        <td></td>
        <td></td>
	</tr>
	<tr>
        <td class="page-form-label"><s:text name="mailTemplate.subject"/></td>
        <td class="page-form-value" colspan="3"><s:textfield key="mailTemplate.subject" cssStyle="width:400px;"/></td>
	</tr>
	<tr>
        <td class="page-form-label"><s:text name="mailTemplate.body"/></td>
        <td class="page-form-value" colspan="3"><s:textarea name="mailTemplate.bodyName"  rows="8" cols="60"></s:textarea></td>
	</tr>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%"></td>
        <td width="30%"></td>
    </tr>
</table> 