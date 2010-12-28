<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="person.model"/><s:text name="title.info"/></td>
        <s:hidden key="person.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="person.firstName"/></td>
        <td class="page-form-value"><s:textfield key="person.firstName"/></td>
        <td/>
        <td/>
	</tr>
	<tr>
        <td class="page-form-label"><s:text name="person.lastName"/></td>
        <td class="page-form-value"><s:textfield key="person.lastName"/></td>
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