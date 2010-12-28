<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="referance.model"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.name"/></td>
        <td class="page-form-value"><s:property value="referance.nameName"/></td>
        <td class="page-form-label"><s:text name="referance.code"/></td>
        <td class="page-form-value"><s:property value="referance.code"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.lang"/></td>
        <td class="page-form-value"><s:property value="referance.langName"/></td>
        <td class="page-form-label"><s:text name="referance.catagory"/></td>
        <td class="page-form-value"><s:property value="referance.catagoryName"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.text"/></td>
        <td class="page-form-value" colspan="3"><page:textarea value="referance.text"/></td>
    </tr>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
    </tr>
</table>