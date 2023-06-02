package stepDefinitions;

import hooks.API_Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class API_stepDefinition {
    public static String fullPath;
    JSONObject reqBodyJson;
    Response response;
    int successStatusCode = 200;
    public static String idBloodBankCreatedId;
    public static String idVisitorCreatedId;
    public static String idVisitorPurposeId;

    @Given("Set {string} parameters")
    public void set_parameters(String rawPaths) {

        String[] paths = rawPaths.split("/");
        StringBuilder tempPath = new StringBuilder("/{");
        for (int i = 0; i < paths.length; i++) {
            String key = "pp" + i; // pp0 pp1 pp2
            String value = paths[i].trim();
            System.out.println("value = " + value);
            API_Hooks.spec.pathParam(key, value);
            tempPath.append(key + "}/{");
        }

        // System.out.println("tempPath = " + tempPath);

        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));
        System.out.println("tempPath = " + tempPath);
        fullPath = tempPath.toString();
        System.out.println("fullPath = " + fullPath);
    }


    @Then("Verifies that status code is {int}")
    public void verifiesThatStatusCodeIs(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("Verifies that the message information is {string}")
    public void verifiesThatTheMessageInformationIs(String message) {
        JsonPath resJP = response.jsonPath();
        assertEquals(message, resJP.getString("message"));
    }


    @Then("Verifies that record has {string} includes {string}")
    public void verifiesThatRecordHasIncludes(String id, String data) {
        JsonPath resJP = response.jsonPath();
        System.out.println(resJP.getList("lists"));
        List<Object> list = resJP.getList("lists");
        Object[] arrList = new Object[list.size()];
        arrList = list.toArray(arrList);
        System.out.println(Arrays.toString(arrList));

        int index = 0;
        for (int a = 0; a < arrList.length; a++) {
            if (arrList[a].toString().contains(id)) {
                System.out.println("index no : " + a);
                index = a;
                break;
            }
        }
        System.out.println(arrList[index].toString());
        String actualData = arrList[index].toString();
        actualData = actualData.replaceAll(",", "");
        actualData = actualData.replaceAll(" ", "");
        actualData = actualData.replace("{", "");
        actualData = actualData.replace("}", "");
        System.out.println(actualData);
        String expectedData = data;
        expectedData = expectedData.replaceAll(",", "");
        expectedData = expectedData.replaceAll(" ", "");
        System.out.println(expectedData);
        Assert.assertEquals(expectedData, actualData);

    }


    @Then("Saves the returned response as a result of Get Request sent for List")
    public void savesTheReturnedResponseAsAResultOfGetRequestSentForVisitorPurposeList() {
        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when()
                .get(fullPath);
    }

    @Then("Saves the returned response as a result of Get Request sent for Visitor Purpose List with invalid authorization information")
    public void savesTheReturnedResponseAsAResultOfGetRequestSentForVisitorPurposeListWithInvalidAuthorizationInformation() {
        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + "aaabbbcccc")
                .contentType(ContentType.JSON)
                .when()
                .get(fullPath);
        response.prettyPrint();


    }

    @Then("Saves the returned response as a result of Get By Id Request sent for visitors List")
    public void savesTheReturnedResponseAsAResultOfGetByIdRequestSentForList() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", 1);
        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .get(fullPath);
        response.prettyPrint();
    }

    @Then("Saves the returned response as a result of Get By Id Request sent for visitor purpose List")
    public void savesTheReturnedResponseAsAResultOfGetByIdRequestSentForVisitorPurposeList() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", 4);
        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .get(fullPath);
        response.prettyPrint();
    }

    @Then("Verifies that record includes {string}")
    public void verifiesThatRecordIncludes(String arg0) {
        JsonPath resJP = response.jsonPath();

        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);
        String expectedData = arg0;
        String[] expectedArr = expectedData.split(", ");
        for (String each : expectedArr
        ) {
            Assert.assertTrue(actualData.contains(each));
        }

    }

    @Then("Send a invalid authorization and a valid data verify status code {int} and message info in the response body is {string}")
    public void sendAInvalidAuthorizationAndAValidDataVerifyStatusCodeAndMessageInfoInTheResponseBodyIsFailed(int arg0, String message) {
        reqBodyJson = new JSONObject();
        reqBodyJson.put("exp_category", "stationary");
        reqBodyJson.put("description", "Low Pulse");
        reqBodyJson.put("is_active", "Loww");
        reqBodyJson.put("is_deleted", "no no");

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBodyJson.toString())
                .post(fullPath);

        response.then().assertThat().statusCode(arg0).body("message", Matchers.equalTo(message));
    }

    @Then("Send a valid authorization and a invalid data verify status code {int} and message info in the response body is {string}")
    public void sendAValidAuthorizationAndAInvalidDataVerifyStatusCodeAndMessageInfoInTheResponseBodyIsFailed(int arg0, String message) {
        reqBodyJson = new JSONObject();
        reqBodyJson.put("exp_category", "stationary");
        reqBodyJson.put("description", "Low Pulse");
        reqBodyJson.put("is_active", "Loww");
        reqBodyJson.put("is_deleted", "no no");

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBodyJson.toString())
                .post(fullPath);

        response.then().assertThat().statusCode(arg0).body("message", Matchers.equalTo(message));
    }

    @Then("APi user extract the ExpenseHead datas")
    public void apiUserExtractTheExpenseHeadDatas() {
        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when()
                .get(fullPath);

    }

    @And("Api user should verify if Added Data is exist")
    public void apiUserShouldVerifyIfAddedDataIsExist() {
        String eklenenVeri = "Low Pulse";
        JsonPath resJP = response.jsonPath();
        System.out.println(resJP.getList("lists"));
        List<Object> list1 = resJP.getList("lists");
        Object[] arrList = new Object[list1.size()];
        arrList = list1.toArray(arrList);
        System.out.println(Arrays.toString(arrList));
        //List<JsonObject> JsList=
        int index = 0;
        for (int a = 0; a < arrList.length; a++) {
            if (arrList[a].toString().contains(eklenenVeri)) {
                System.out.println("index no : " + a);
                index = a;
                Assert.assertTrue(arrList.length > index);
                break;
            }
        }
    }

    @Then("Api user expenseHead, creates a record that includes with description data")
    public void apiUserExpenseHeadCreatesARecordThatIncludesWithDescriptionData() {
              /*
        {
            "exp_category": "stationary",
            "description": "stationary expense",
            "is_active": "yes",
            "is_deleted": "no"
}
         */
        reqBodyJson = new JSONObject();
        reqBodyJson.put("exp_category", "stationary");
        reqBodyJson.put("description", "Low Pulse");
        reqBodyJson.put("is_active", "Loww");
        reqBodyJson.put("is_deleted", "no no");

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBodyJson.toString())
                .post(fullPath);
    }

    @Then("Verifies that deletedId information is the same as the id information in the request body")
    public void verifiesThatDeletedIdInformationIsTheSameAsTheIdInformationInTheRequestBody() {
        JsonPath resJp = response.jsonPath();
        Assert.assertEquals(idVisitorCreatedId, resJp.get("deletedId").toString());


    }


    @Then("Saves the returned response as a result of Delete Request for Visitors by invalid data")
    public void savesTheReturnedResponseAsAResultOfDeleteRequestForVisitorsByInvalidData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("iiiiii", idVisitorCreatedId);

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + "xxxxxx")
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .delete(fullPath);
    }

    @Then("Verifies the added visitor record is deleted")
    public void verifiesTheAddedVisitorRecordIsDeleted() {
        JsonPath resJp = response.jsonPath();
        List<String> idList = resJp.getList("lists.id");
        Assert.assertFalse(idList.contains(idVisitorCreatedId));
    }

    @Then("Saves the returned response as a result of Post Request sent for Blood Group List with correct data")
    public void savesTheReturnedResponseAsAResultOfPostRequestSentForBloodGroupListWithCorrectData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("name", "Kubra");
        reqBody.put("is_blood_group", "1");
        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .get(fullPath);


        JsonPath resJs = response.jsonPath();
        idBloodBankCreatedId = resJs.get("addId").toString();

    }

    @Then("Verifies that the data is created")
    public void verifiesThatTheDataIsCreated() {
        JsonPath resJp = response.jsonPath();
        List<String> idList = resJp.getList("lists.id");
        Assert.assertTrue(idList.contains(idBloodBankCreatedId));


    }

    @Then("Saves the returned response as a result of Post Request sent for Blood Group List with invalid data")
    public void savesTheReturnedResponseAsAResultOfPostRequestSentForBloodGroupListWithInvalidData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("inci", "Kubra");
        reqBody.put("403", "1");
        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .post(fullPath);
    }

    @Then("Then Saves the returned response as a result of Post Request sent for Visitor List with valid data")
    public void thenSavesTheReturnedResponseAsAResultOfPostRequestSentForVisitorListWithValidData() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("purpose", "Inquiry");
        reqBody.put("name", "jonh Doe");
        reqBody.put("email", "eeeeee");
        reqBody.put("contact", "9638521770");
        reqBody.put("id_proof", "0125856");
        reqBody.put("visit_to", "opd_patient");
        reqBody.put("ipd_opd_staff_id", "2");
        reqBody.put("related_to", "Oliver Thomas");
        reqBody.put("no_of_pepple", "1");
        reqBody.put("date", "2023-10-05");
        reqBody.put("in_time", "09:30 PM");
        reqBody.put("out_time", "10:30 PM");
        reqBody.put("note", "");

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .post(fullPath);
        response.prettyPrint();
        JsonPath resJs = response.jsonPath();
        idVisitorCreatedId = resJs.get("addId").toString();
    }

    @Then("Saves the returned response as a result of Delete Request for Visitors by id")
    public void savesTheReturnedResponseAsAResultOfDeleteRequestForVisitorsById() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("id", idVisitorCreatedId);

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .delete(fullPath);

    }

    @Then("Saves the returned response as a result of Post Request sent for Visitor Purpose List with valid data")
    public void savesTheReturnedResponseAsAResultOfPostRequestSentForVisitorPurposeListWithValidData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("visitors_purpose", "deneme purpose");
        reqBody.put("description", "deneme description");

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .post(fullPath);

        JsonPath resJs = response.jsonPath();
        idVisitorPurposeId = resJs.get("addId").toString();
    }

    @Then("Saves the returned response as a result of Delete Request for Visitors Purpose by id")
    public void savesTheReturnedResponseAsAResultOfDeleteRequestForVisitorsPurposeById() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", idVisitorPurposeId);

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .delete(fullPath);

    }

    @Then("In visitors purpose list, Verifies that deletedId information is the same as the id information in the request body")
    public void inVisitorsPurposeListVerifiesThatDeletedIdInformationIsTheSameAsTheIdInformationInTheRequestBody() {
        JsonPath resJp = response.jsonPath();
        Assert.assertEquals(idVisitorPurposeId, resJp.get("DeletedId").toString());

    }

    @Then("Saves the returned response as a result of Visitors Purpose Delete Request for Visitors by invalid data")
    public void savesTheReturnedResponseAsAResultOfVisitorsPurposeDeleteRequestForVisitorsByInvalidData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("iiiiii", idVisitorPurposeId);

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + "xxxxxx")
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .delete(fullPath);
    }

    @Then("Verifies the added visitor purpose record is deleted")
    public void verifiesTheAddedVisitorPurposeRecordIsDeleted() {
        JsonPath resJp = response.jsonPath();
        List<String> idList = resJp.getList("lists.id");
        Assert.assertFalse(idList.contains(idVisitorPurposeId));

    }

    @Then("Saves the returned response as a result of Get Request sent for Visitor List with invalid authorization information")
    public void savesTheReturnedResponseAsAResultOfGetRequestSentForVisitorListWithInvalidAuthorizationInformation() {
        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + "aaaaa")
                .contentType(ContentType.JSON)
                .when()
                .get(fullPath);
        response.prettyPrint();

    }
    @Then("Saves the returned response as a result of Patch Request for BloodGroup by valid data")
    public void savesTheReturnedResponseAsAResultOfPatchRequestForBloodGroupByValidData() {

        JSONObject reqBody = new JSONObject();

        reqBody.put("id", "94");
        reqBody.put("name", "iva");
        reqBody.put("is_blood_group", "1");

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .patch(fullPath);
        response.prettyPrint();
    }


    @Then("Saves the returned response as a result of Post Request sent for BloodGroup List with valid data")
    public void savesTheReturnedResponseAsAResultOfPostRequestSentForBloodGroupListWithValidData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id","43");
        reqBody.put("source","null");
        reqBody.put("purpose","Inquiry");
        reqBody.put("name", "Bayram  son eklenen 1");
        reqBody.put("email", "null");
        reqBody.put("contact", "9638521770");
        reqBody.put("id_proof", "0125856");
        reqBody.put("visit_to","opd_patient");
        reqBody.put("ipd_opd_staff_id","2");
        reqBody.put("related_to", "bayram ERGUVEN (4) (OPDN2)");
        reqBody.put("no_of_pepple", "1");
        reqBody.put("date", "2023-10-05");
        reqBody.put("in_time", "09:30 PM");
        reqBody.put("out_time", "10:30 PM");
        reqBody.put("note", "");
        reqBody.put("image", "");
        reqBody.put("created_at", "2023-05-18 10:26:05");


        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .post(fullPath);
        response.prettyPrint();
        JsonPath resJs =response.jsonPath();
        idBloodBankCreatedId=resJs.get("addId").toString();

    }

    @Then("Saves the returned response as a result of Delete Request for BloodGroup by id")
    public void savesTheReturnedResponseAsAResultOfDeleteRequestForBloodGroupById() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id",idBloodBankCreatedId);

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .delete(fullPath);

    }

    @Then("In BloodGroup, Verifies that deletedId information is the same as the id information in the request body")
    public void ınBloodGroupVerifiesThatDeletedIdInformationIsTheSameAsTheIdInformationInTheRequestBody() {
        JsonPath resJp  =response.jsonPath();
        Assert.assertEquals(idBloodBankCreatedId,resJp.get("DeletedId").toString());

    }
    @Then("Verifies that updatedId information is the same as the id information in the request body")
    public void verifiesThatUpdatedIdInformationIsTheSameAsTheIdInformationInTheRequestBody () {
        JsonPath resJp = response.jsonPath();
        Assert.assertEquals("94", resJp.get("updatedId").toString());
    }


    @Then("Saves the returned response as a result of Patch Request for BloodGroup by invalid data")
    public void savesTheReturnedResponseAsAResultOfPatchRequestForBloodGroupByInvalidData() {
        JSONObject reqBody = new JSONObject();

        reqBody.put("xyz", "94");
        reqBody.put("name", "iva");
        reqBody.put("qqq", "1");

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + "lalalala")
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .patch(fullPath);
    }

    @Then("Verifies the updated blood group record is saved")
    public void verifiesTheUpdatedBloodGroupRecordIsSaved() {
        JsonPath resJp = response.jsonPath();
        List<Object> list = resJp.getList("lists");
        Object[] arrList = new Object[list.size()];
        arrList = list.toArray(arrList);
        int count = 0;
        for (Object each : arrList
        ) {
            if (each.toString().contains("94")) {
                if (each.toString().contains("iva"))
                    count++;

            }
        }
        Assert.assertEquals(count,1);
    }
    @Then("Api user expenseHead, update a record that includes with description data")
    public void apiUserExpenseHeadUpdateARecordThatIncludesWithDescriptionData() {
        reqBodyJson = new JSONObject();
        /*
            "id": 22,
            "exp_category": "stationary 1",
            "description": "stationary expense",
            "is_active": "yes",
            "is_deleted": "no"
         */
        reqBodyJson.put("exp_category", "stationary");
        reqBodyJson.put("id", 22);
        reqBodyJson.put("description", "stationary expense");
        reqBodyJson.put("is_active", "yes");
        reqBodyJson.put("is_deleted", "no");

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBodyJson.toString())
                .patch(fullPath);
    }
    public static String idFindingCategoryById="";
    @Then("Then Saves the returned response as a result of Post Request sent for Finding Category List with valid data")
    public void thenSavesTheReturnedResponseAsAResultOfPostRequestSentForFindingCategoryListWithValidData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("category", "head Diseases");
        reqBody.put("created_at", "2024-10-25 02:19:41");

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .post(fullPath);

        JsonPath resJs = response.jsonPath();
        idFindingCategoryById = resJs.get("addId").toString();
    }


    @Then("Saves the returned response as a result of Delete Request for Finding Category by id")
    public void savesTheReturnedResponseAsAResultOfDeleteRequestForFindingCategoryById() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", idFindingCategoryById);

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .delete(fullPath);


    }


    @Then("Saves the returned response as a result of BloodGroup Delete Request for BloodGroup by invalid data")
    public void savesTheReturnedResponseAsAResultOfBloodGroupDeleteRequestForBloodGroupByInvalidData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("iiiiii", idBloodBankCreatedId);

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + "aaaa")
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .delete(fullPath);
    }
    @Then("Verifies that deleted FindingCategoryId information is the same as the id information in the request body")
    public void verifiesThatDeletedFindingCategoryIdInformationIsTheSameAsTheIdInformationInTheRequestBody() {

        JsonPath resJp  =response.jsonPath();
        Assert.assertEquals(idFindingCategoryById,resJp.get("deletedId").toString());
    }

    @Then("Saves the returned response as a result of Finding Category Request for Visitors by invalid data")
    public void savesTheReturnedResponseAsAResultOfFindingCategoryRequestForVisitorsByInvalidData() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "hahah");


        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + "hahahah")
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .post(fullPath);

    }

    @Then("Verifies the added Finding Category record is deleted")
    public void verifiesTheAddedFindingCategoryRecordIsDeleted() {
        JsonPath resJp =response.jsonPath();
        List<String> idList=resJp.getList("lists.id");
        Assert.assertFalse(idList.contains(idFindingCategoryById));


    }
    public static String addFindingsId;
    @Then("Saves the returned response as a result of Post Request sent for Finding List with valid data")
    public void savesTheReturnedResponseAsAResultOfPostRequestSentForFindingListWithValidData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("name", "mouth sore");
        reqBody.put("description","mouth sore is");
        reqBody.put("finding_category_id", 2);

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .post(fullPath);

        JsonPath resJs =response.jsonPath();
        addFindingsId=resJs.get("addId").toString();
    }
    @Then("Saves the returned response as a result of Delete Request for Finding List by id")
    public void savesTheReturnedResponseAsAResultOfDeleteRequestForFindingListById() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id",addFindingsId);

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .delete(fullPath);
    }
    @Then("In Finding list, Verifies that deletedId information is the same as the id information in the request body")
    public void inFindingListVerifiesThatDeletedIdInformationIsTheSameAsTheIdInformationInTheRequestBody() {
        JsonPath resJp  =response.jsonPath();
        Assert.assertEquals(addFindingsId,resJp.get("deletedId").toString());
    }
    @Then("Saves the returned response as a result of Finding Delete Request for Visitors by invalid data")
    public void savesTheReturnedResponseAsAResultOfFindingDeleteRequestForVisitorsByInvalidData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("iiiiii",5);

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + "xxxxxx")
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .delete(fullPath);
    }
    @Then("Verifies the added finding record is deleted")
    public void verifiesTheAddedFindingRecordIsDeleted() {
        JsonPath resJp =response.jsonPath();
        List<String> idList=resJp.getList("lists.id");
        Assert.assertFalse(idList.contains(addFindingsId));
    }

    @Then("Saves the returned response as a result of Get Blood Group By Id Request sent for List")
    public void savesTheReturnedResponseAsAResultOfGetBloodGroupByIdRequestSentForList() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "99");
        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .get(fullPath);
    }

    @Then("Saves the returned response as a result of Get Blood Group By Id Request sent for List with invalid data")
    public void savesTheReturnedResponseAsAResultOfGetBloodGroupByIdRequestSentForListWithInvalidData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("BloodGroup", "99");
        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + "xxxxx")
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .get(fullPath);
    }

    @Then("Saves the returned response as a result of Post Request sent for Visitors Purpose Add List with correct data")
    public void savesTheReturnedResponseAsAResultOfPostRequestSentForVisitorsPurposeAddListWithCorrectData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("visitors_purpose", "deneme purpose");
        reqBody.put("description", "deneme description");
        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .post(fullPath);


        JsonPath resJs = response.jsonPath();
        idVisitorPurposeId = resJs.get("addId").toString();
    }

    @Then("Saves the returned response as a result of Post Request sent for Visitors Purpose Add List with invalid data")
    public void savesTheReturnedResponseAsAResultOfPostRequestSentForVisitorsPurposeAddListWithInvalidData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("visitors purpose", "Rana");
        reqBody.put("4030", "11");
        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + "xxxxx")
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .post(fullPath);
    }

    @Then("Verifies that data has been created")
    public void verifiesThatDataHasBeenCreated() {
        JsonPath resJp = response.jsonPath();
        List<String> idList = resJp.getList("lists.id");
        Assert.assertTrue(idList.contains(idVisitorPurposeId));

    }

    public static String ExpenseHeadId;
    @Then("Saves the returned response as a result of Post Request sent for ExpenseHead List with valid data.")
    public void savesTheReturnedResponseAsAResultOfPostRequestSentForExpenseHeadListWithValidData() {
        JSONObject reqBody = new JSONObject();
        reqBody.put( "exp_category", "deneme purpose");
        reqBody.put("description", "deneme description");
        reqBody.put("is_active", "deneme purpose");
        reqBody.put("is_deleted", "deneme description");

        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .post(fullPath);


        JsonPath resJs = response.jsonPath();
        ExpenseHeadId = resJs.get("addId").toString();
    }

    @Then("Saves the returned response as a result of Delete Request for ExpenseHead List by id")
    public void savesTheReturnedResponseAsAResultOfDeleteRequestForExpenseHeadListById() {

        JSONObject reqBody = new JSONObject();
        reqBody.put( "id", ExpenseHeadId);


        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.token)
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .delete(fullPath);


    }

    @Then("In ExpenseHead list, Verifies that deletedId information is the same as the id information in the request body.")
    public void inExpenseHeadListVerifiesThatDeletedIdInformationIsTheSameAsTheIdInformationInTheRequestBody() {

        JsonPath resJs = response.jsonPath();
        assertEquals(resJs.get("deletedId").toString(), ExpenseHeadId);
    }

    @Then("Saves the returned response as a result of ExpenseHead Delete Request by invalid data.")
    public void savesTheReturnedResponseAsAResultOfExpenseHeadDeleteRequestByInvalidData() {

        JSONObject reqBody = new JSONObject();
        reqBody.put( "ide", 112);


        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + "deneme")
                .contentType(ContentType.JSON)
                .when().body(reqBody.toString())
                .delete(fullPath);
    }

    @Then("Verifies the added ExpenseHead record is deleted.")
    public void verifiesTheAddedExpenseHeadRecordIsDeleted() {
        JsonPath resJp = response.jsonPath();
        List<String> idList = resJp.getList("lists.id");
        Assert.assertFalse(idList.contains(ExpenseHeadId));
    }
}





