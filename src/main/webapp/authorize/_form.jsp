<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="authorize.model"/><s:text name="title.info"/></td>
        <s:hidden key="authorize.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="authorize.controller"/></td>
        <td class="page-form-value"><s:textfield key="authorize.controller"/></td>
        <td/>
        <td/>
	</tr>
	<tr>
        <td class="page-form-label"><s:text name="authorize.action"/></td>
        <td class="page-form-value"><s:textfield key="authorize.action"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="authorize.permission"/></td>
        <td class="page-form-value"><s:textfield key="authorize.permission"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
    </tr>
</table> 