<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="content.model"/><s:text name="title.info"/></td>
        <s:hidden key="content.id"/>
        <s:token/>
    </tr>
    <tr>
   		<td class="page-form-label"><s:text name="content.fileName"/></td>
        <td class="page-form-value"><s:textfield key="content.fileName"/></td>
    	<td class="page-form-label"><s:text name="content.contentType"/></td>
        <td class="page-form-value"><s:textfield key="content.contentType"/></td>
    </tr>
    <tr>
   		<td class="page-form-label"><s:text name="content.refName"/></td>
        <td class="page-form-value"><s:textfield key="content.refName"/></td>
    	<td class="page-form-label"><s:text name="content.refId"/></td>
        <td class="page-form-value"><s:textfield key="content.refId"/></td>
    </tr>
    <tr>
   		<td class="page-form-label"><s:text name="content.catagory"/></td>
        <td class="page-form-value"><s:textfield key="content.catagory"/></td>
    	<td class="page-form-label"><s:text name="content.lang"/></td>
        <td class="page-form-value">
        	<s:select name="content.lang" list="listReferanceLang" listKey="key" listValue="value" value="content.lang" />
        </td>
    </tr>
    <tr>
		<td class="page-form-label"><s:text name="content.file"/></td>
        <td class="page-form-value"><input type="file" name="blob"/></td>
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