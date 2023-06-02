package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DB_Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utilities.DB_Utils.getColumnData;
import static utilities.DB_Utils.getStatement;

public class DB_stepDefinition {

    Connection connection;
    Statement statement;
    ResultSet resultset;

    List<Object> staffID = new ArrayList<>();
    List<Object> adresList = new ArrayList<>();

    @Given("Connect to database.")
    public void connect_to_database() {
        DB_Utils.createConnection();
    }

    @Then("Close database.")
    public void close_database() {
        DB_Utils.closeConnection();
    }

    @Given("A query prepared with the given data {string} and Column name {string} is performed.")
    public void a_query_prepared_with_the_given_data_and_column_name_is_performed(String query, String ColumnName) {
        adresList = getColumnData(query, ColumnName);
        System.out.println(adresList);
    }

    @Then("Verify that the result is {string}.")
    public void verify_that_the_result_is(String expData) {
        Assert.assertEquals(expData, adresList.get(0));
    }

    @Then("Verify that the result size is {int}.")
    public void verify_that_the_result_size_is(int expData) {
        System.out.println(adresList.size());
        Assert.assertEquals(expData, adresList.size());
    }

    int beforeMidday = 0;
    int afterMidday = 0;

    @Then("Saves the result size before the midday.")
    public void saves_the_result_size_before_the_midday() {
        beforeMidday = adresList.size();
    }

    @Then("Saves the result size after the midday.")
    public void saves_the_result_size_after_the_midday() {
        afterMidday = adresList.size();
    }

    @Then("Verify that before the midday appointment is less than after the midday appointment.")
    public void verify_that_before_the_midday_appointment_is_less_than_after_the_midday_appointment() {
        Assert.assertTrue(beforeMidday < afterMidday);
    }

    int startTime = 0;
    int endTime = 0;

    @Then("Save the start time result.")
    public void save_the_start_time_result() {
        String subStart = adresList.toString().substring(1, 3);
        //    System.out.println(subStart);
        startTime = Integer.parseInt(subStart);
        //    System.out.println(startTime);
    }

    @Then("Save the end time result.")
    public void save_the_end_time_result() {

        String subEnd = adresList.toString().substring(1, 3);
        //    System.out.println(subEnd);
        endTime = Integer.parseInt(subEnd);
        //    System.out.println(endTime);
    }

    @Then("Verify that the doctor worked for {int} hours on tuesday.")
    public void verify_that_the_doctor_worked_for_hours_on_tuesday(Integer int1) {
        int expHour = 2;
        Assert.assertEquals(endTime - startTime, expHour);
    }

    @Then("Verify that the date is {string}.")
    public void verify_that_the_date_is(String expData) {
        Assert.assertEquals(expData, adresList.get(0).toString());
    }


    @Given("A query prepared with the given data {string} is performed.")
    public void a_query_prepared_with_the_given_data_is_performed(String query) throws SQLException {
        DB_Utils.update(query);

    }

    @Then("Verify that the result is {int}.")
    public void verifyThatTheResultIs(int expData) {
        Assert.assertEquals(expData, adresList.get(0));
    }

    int beforeDel = 0;
    int afterDel = 0;

    @Then("Save the result size before the deleting.")
    public void save_the_result_size_before_the_deleting() {
        beforeDel = adresList.size();
        System.out.println(beforeDel);
    }

    @Then("Save the result size after the deleting.")
    public void save_the_result_size_after_the_deleting() {
        afterDel = adresList.size();
        System.out.println(afterDel);
    }

    @Then("verify that the record is deleted.")
    public void verifyThatTheRecordIsDeleted() {
        Assert.assertTrue(beforeDel - 1 == afterDel);
    }


    @Then("Verify that the result is {float}, {float}, {float}, {float}, {float}")
    public void verifyThatTheResultIs(float arg0, float arg1, float arg3, float arg4, float arg5) {

    }

    @Then("Verify that the result {int} doesn't exist.")
    public void verifyThatTheResultDoesnTExist(int expData) {
        Assert.assertFalse(adresList.contains(expData));
    }

    @Then("Verify that the result {int} exists.")
    public void verifyThatTheResultExists(int expData) {
        Assert.assertTrue(adresList.contains(expData));
    }

    List<Object> manufacture_year = new ArrayList<>();
    List<Object> created_at = new ArrayList<>();

    List<Object> result = new ArrayList<>();

    @Then("Save the id list related to manufacture year.")
    public void save_the_id_list_related_to_manufacture_year() {
        manufacture_year = adresList;
    }

    @Then("Save the id list related to created at.")
    public void save_the_id_list_related_to_created_at() {
        created_at = adresList;
    }

    @Then("Verifies that a vehicle has same index in each list.")
    public void verifiesThatAVehicleHasSameIndexInEachList() {

        for (int i = 0; i < manufacture_year.size(); i++) {


            for (int j = 0; j < created_at.size(); j++) {

                if (manufacture_year.get(i).equals( created_at.get(j))) {
                    result.add(i);
                }

            }

        }

        Assert.assertTrue(result.size()>0);

    }

    @Then("Verify that the Language is {string}.")
    public void verifyThatTheLanguageIs(String expData) {
        Assert.assertEquals(expData, adresList.get(0));
    }


    @Then("Verify that the result {string} exists.")
    public void verifyThatTheResultExists(String expData) {
        Assert.assertTrue(adresList.contains(expData));
    }

    @And("A query prepared with the given data {string} is performed for row.")
    public void aQueryPreparedWithTheGivenDataIsPerformedForRow(String query) {
        adresList=DB_Utils.getRowList(query);
        System.out.println(adresList);
    }

    @Then("A query is prepared with the given data and the query is performed")
    public void aQueryIsPreparedWithTheGivenDataAndTheQueryIsPerformed() throws SQLException {
        String query="SELECT * FROM department WHERE id='16'";

        resultset= getStatement().executeQuery(query);
        System.out.println(resultset);

    }

    @Then("Verifies that the department_name value of the returned Result set data is {string} and the created_at value is {string}")
    public void verifiesThatTheDepartment_nameValueOfTheReturnedResultSetDataIsAndTheCreated_atValueIs(String department_name, String created_at) throws SQLException {
        resultset.first();

        Assert.assertEquals(department_name,resultset.getString("department_name"));
        Assert.assertEquals(created_at,resultset.getString("created_at"));

    }

}
