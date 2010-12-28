<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="referance.model"/></td>
        <s:hidden name="referance.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.name"/></td>
        <td class="page-form-value">
        	<s:select name="referance.name" list="listReferanceName" listKey="key" listValue="value" value="referance.name" />
        </td>
        <td class="page-form-label"><s:text name="referance.code"/></td>
        <td class="page-form-value"><s:textfield name="referance.code"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.lang"/></td>
        <td class="page-form-value">
        	<s:select name="referance.lang" list="listReferanceLang" listKey="key" listValue="value" value="referance.lang" />
        </td>
        <td class="page-form-label"><s:text name="referance.catagory"/></td>
        <td class="page-form-value">
        	<s:select name="referance.catagory" list="listReferanceCatagory" listKey="key" listValue="value" value="referance.catagory" />
        </td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.text"/></td>
        <td class="page-form-value" colspan="3"><s:textarea name="referance.text"  rows="4" cols="50"></s:textarea></td>
    </tr>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
    </tr>
</table> 