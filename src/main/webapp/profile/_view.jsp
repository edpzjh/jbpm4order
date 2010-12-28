<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="profile.model"/><s:text name="title.info"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="profile.language"/></td>
        <td class="page-form-value"><s:property value="profile.languageName"/></td>
        <td class="page-form-label"><s:text name="profile.country"/></td>
        <td class="page-form-value"><s:property value="profile.countryName"/></td>
    </tr>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
    </tr>
</table>