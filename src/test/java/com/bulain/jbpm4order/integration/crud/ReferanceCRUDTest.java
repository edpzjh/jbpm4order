package com.bulain.jbpm4order.integration.crud;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sourceforge.jwebunit.junit.WebTestCase;

public class ReferanceCRUDTest extends WebTestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(ReferanceCRUDTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        setBaseUrl("http://localhost:8080/jbpm4order");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCrud() {
        // get new
        beginAt("/referance/new");

        assertTextFieldEquals("referanceBean.name", "");
        assertTextFieldEquals("referanceBean.code", "");
        assertTextFieldEquals("referanceBean.textEN", "");
        assertTextFieldEquals("referanceBean.textCN", "");
        assertTextFieldEquals("referanceBean.catagory", "");
        assertSubmitButtonPresent();
        assertLinkPresentWithExactText("Back");

        setTextField("referanceBean.name", "name");
        setTextField("referanceBean.code", "code");
        setTextField("referanceBean.textEN", "textEN");
        setTextField("referanceBean.textCN", "textCN");
        setTextField("referanceBean.catagory", "catagory");

        // post create
        submit();

        assertTablePresent("list");
        assertTableRowCountEquals("list", 3);
        DateFormat df = new SimpleDateFormat("M/dd/yy");
        String date = df.format(new Date());
        String[][] tables = new String[][]{
                {"Name", "Code", "Text", "Lang", "Catagory", "Updated By", "Updated At", "Action"},
                {"name", "code", "textEN", "English", "catagory", "createdBy", date, "Show |Edit |Destroy"},
                {"name", "code", "textCN", "Chinese", "catagory", "createdBy", date, "Show |Edit |Destroy"}};
        assertTableEquals("list", tables);

        // get show
        clickLinkWithExactText("Show");

        // get list
        clickLinkWithExactText("Back");
        assertTablePresent("list");

        // get edit
        clickLinkWithExactText("Edit", 0);
        assertTextFieldEquals("referance.name", "name");
        assertTextFieldEquals("referance.code", "code");
        assertTextFieldEquals("referance.text", "textEN");
        assertTextFieldEquals("referance.lang", "English");
        assertTextFieldEquals("referance.catagory", "catagory");

        setTextField("referance.name", "name-updated");
        setTextField("referance.code", "code-updated");
        setTextField("referance.text", "textEN-updated");
        setTextField("referance.lang", "English-updated");
        setTextField("referance.catagory", "catagory-updated");

        // post update
        submit();
        assertTablePresent("list");
        tables = new String[][]{
                {"Name", "Code", "Text", "Lang", "Catagory", "Updated By", "Updated At", "Action"},
                {"name-updated", "code-updated", "textEN-updated", "English-updated", "catagory-updated", "updatedBy",
                        date, "Show |Edit |Destroy"},
                {"name", "code", "textCN", "Chinese", "catagory", "createdBy", date, "Show |Edit |Destroy"}};
        assertTableEquals("list", tables);

        // get edit
        clickLinkWithExactText("Edit", 0);
        assertTextFieldEquals("referance.name", "name-updated");
        assertTextFieldEquals("referance.code", "code-updated");
        assertTextFieldEquals("referance.text", "textEN-updated");
        assertTextFieldEquals("referance.lang", "English-updated");
        assertTextFieldEquals("referance.catagory", "catagory-updated");

        // get list
        clickLinkWithExactText("Back");
        assertTablePresent("list");

        setTextField("search.name", "name-updated");
        setTextField("search.code", "code-updated");

        // post list
        submit();
        assertTablePresent("list");

        // post destroy
        clickLinkWithExactText("Destroy");
        assertTablePresent("list");
        tables = new String[][]{{"Name", "Code", "Text", "Lang", "Catagory", "Updated By", "Updated At", "Action"}};
        assertTableEquals("list", tables);

        // post destroy
        setTextField("search.name", "");
        setTextField("search.code", "");
        submit();
        assertTablePresent("list");
        clickLinkWithExactText("Destroy");
        assertTablePresent("list");
        tables = new String[][]{{"Name", "Code", "Text", "Lang", "Catagory", "Updated By", "Updated At", "Action"}};
        assertTableEquals("list", tables);
    }
}
