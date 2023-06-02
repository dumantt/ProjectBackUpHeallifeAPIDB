@API
Feature:

  Scenario: API [US_02 / TC_01]
    Given Set "api/staffList" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"
    Then Verifies that record has "id=4" includes "id=4, name=Sansa, surname=Gomez, employee_id=9008"
    Then Verifies that record has "id=10" includes "id=10, name=Natasha, surname= Romanoff, employee_id=9010"
    Then Verifies that record has "id=16" includes "id=16, name=April, surname=Clinton, employee_id=9020"

  Scenario: API [US_03 / TC_01]
    Given Set "api/ipdList" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"
    Then Verifies that record has "id=11" includes "id=11, patient_name=Mukul singh,patient_id=16"
    Then Verifies that record has "id=13" includes "id=13, patient_name=Marsh Martin,patient_id=24"

  Scenario:  API [US_04 / TC_01]
    Given Set "api/visitorsPurposeList" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"
    Then Verifies that record has "id=19" includes "id=19, visitors_purpose=feridun bey, description=bayram 123 111, created_at=2023-04-12 08:34:56"
    Then Verifies that record has "id=29" includes "id=29, visitors_purpose=special work, description=special word details, created_at=2023-05-18 17:00:26"

  Scenario: API  [US_04 / TC_02]
    Given Set "api/visitorsPurposeList" parameters
    Then Saves the returned response as a result of Get Request sent for Visitor Purpose List with invalid authorization information
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario: API [US_10 / TC_01]
    Given Set "api/visitorsId" parameters
    Then Saves the returned response as a result of Get By Id Request sent for visitors List
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"
    Then Verifies that record includes "id, source, purpose, name, email, contact, id_proof, visit_to, ipd_opd_staff_id, related_to, no_of_pepple, date, in_time, out_time, note, created_at"

  Scenario: API [US_05 / TC_01]
    Given Set "api/visitorsPurposeId" parameters
    Then Saves the returned response as a result of Get By Id Request sent for visitor purpose List
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"
    Then Verifies that record includes "id, visitors_purpose, description, created_at"

  Scenario: API [US_14 / TC_01]
    Given Set "api/getBloodGroup" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"
    Then Verifies that record has "id=3" includes "id=3,name= AB-, is_blood_group= 1, created_at= 2021-10-25 02:32:41"
    Then Verifies that record has "id=8" includes "id=8,name= O+, is_blood_group= 1, created_at= 2021-10-25 02:33:28"

  Scenario: API  [US_14 / TC_02]
    Given Set "api/getBloodGroup" parameters
    Then Saves the returned response as a result of Get Request sent for Visitor Purpose List with invalid authorization information
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario: API  [US_02 / TC_02]
    Given Set "api/staffList" parameters
    Then Saves the returned response as a result of Get Request sent for Visitor Purpose List with invalid authorization information
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario: API  [US_03 / TC_02]
    Given Set "api/ipdList" parameters
    Then Saves the returned response as a result of Get Request sent for Visitor Purpose List with invalid authorization information
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario: API  [US_05 / TC_02]
    Given Set "api/visitorsPurposeId" parameters
    Then Saves the returned response as a result of Get Request sent for Visitor Purpose List with invalid authorization information
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario: API  [US_10 / TC_02]
    Given Set "api/visitorsId" parameters
    Then Saves the returned response as a result of Get Request sent for Visitor Purpose List with invalid authorization information
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario: API  [US_21 / TC_01]
    Given Set "api/addExpenseHead" parameters
    Then Api user expenseHead, creates a record that includes with description data
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"

  Scenario: API  [US_21 / TC_02]
    Given Set "api/addExpenseHead" parameters
    Then Send a invalid authorization and a valid data verify status code 403 and message info in the response body is "failed"
    Then Send a valid authorization and a invalid data verify status code 403 and message info in the response body is "failed"

  Scenario: API  [US_21 / TC_03]
    Given Set "api/getExpenseHead" parameters
    Then APi user extract the ExpenseHead datas
    And Api user should verify if Added Data is exist

  Scenario:API  [US_16 / TC_01]
    Given Set "api/addBloodGroup" parameters
    Then  Saves the returned response as a result of Post Request sent for Blood Group List with correct data
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"
    Then Set "api/getBloodGroup" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies that the data is created

  Scenario: [US_16 / TC_02]
    Given Set "api/addBloodGroup" parameters
    Then  Saves the returned response as a result of Post Request sent for Blood Group List with invalid data
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario: [US_13 / TC_01]
    Given Set "api/visitorsAdd" parameters
    Then Then Saves the returned response as a result of Post Request sent for Visitor List with valid data
    Then Set "api/visitorsDelete" parameters
    Then Saves the returned response as a result of Delete Request for Visitors by id
    Then Verifies that deletedId information is the same as the id information in the request body
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"

  Scenario: [US_13 / TC_02]
    Given Set "api/visitorsDelete" parameters
    Then Saves the returned response as a result of Delete Request for Visitors by invalid data
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario:  [US_13 / TC_03]
    Given Set "api/visitorsList" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies the added visitor record is deleted

  Scenario:  [US_08 / TC_01]
    Given Set "api/visitorsPurposeAdd" parameters
    Then  Saves the returned response as a result of Post Request sent for Visitor Purpose List with valid data
    Then Set "api/visitorsPurposeDelete" parameters
    Then Saves the returned response as a result of Delete Request for Visitors Purpose by id
    Then  In visitors purpose list, Verifies that deletedId information is the same as the id information in the request body
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"

  Scenario: [US_08 / TC_02]
    Given Set "api/visitorsPurposeDelete" parameters
    Then Saves the returned response as a result of Visitors Purpose Delete Request for Visitors by invalid data
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario:  [US_08 / TC_03]
    Given Set "api/visitorsPurposeList" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies the added visitor purpose record is deleted


  Scenario:  [US_09 / TC_01]
    Given Set "api/visitorsList" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"

  Scenario:  [US_09 / TC_02]
    Given Set "api/visitorsList" parameters
    Then Saves the returned response as a result of Get Request sent for Visitor List with invalid authorization information
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario:  [US_09 / TC_03]
    Given Set "api/visitorsList" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies that status code is 200
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies that record has "id=38" includes "id=38,source=Online,purpose=Improve local visibility for heallifehospital.com,name=Mike Holmes,email=no-replyAnnora@gmail.com,"

  Scenario:  [US_18 / TC_01]
    Given Set "api/deleteBloodGroup" parameters
    Then Saves the returned response as a result of Post Request sent for BloodGroup List with valid data
    Then Set "api/deleteBloodGroup" parameters
    Then Saves the returned response as a result of Delete Request for BloodGroup by id
    Then In BloodGroup, Verifies that deletedId information is the same as the id information in the request body
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"


  Scenario: [US_18 / TC_02]
    Given Set "api/deleteBloodGroup" parameters
    Then Saves the returned response as a result of BloodGroup Delete Request for BloodGroup by invalid data
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario:  [US_18 / TC_03]
    Given Set "api/deleteBloodGroup" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies the added visitor purpose record is deleted

  Scenario: [US_17 / TC_01]
    Given Set "api/updateBloodGroup" parameters
    Then Saves the returned response as a result of Patch Request for BloodGroup by valid data
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"
    Then Verifies that updatedId information is the same as the id information in the request body

  Scenario: [US_17 / TC_02]
    Given Set "api/updateBloodGroup" parameters
    Then Saves the returned response as a result of Patch Request for BloodGroup by invalid data
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario: [US_17 / TC_03]
    Given Set "api/getBloodGroup" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies the updated blood group record is saved


  Scenario: API  [US_22 / TC_01]
    Given Set "api/updateExpenseHead" parameters
    Then Api user expenseHead, update a record that includes with description data
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"

  Scenario: API  [US_22 / TC_03]
    Given Set "api/updateExpenseHead" parameters
    Then Send a invalid authorization and a valid data verify status code 403 and message info in the response body is "failed"
    Then Send a valid authorization and a invalid data verify status code 403 and message info in the response body is "failed"

  Scenario: API  [US_22 / TC_03]
    Given Set "api/getExpenseHead" parameters
    Then APi user extract the ExpenseHead datas
    And Api user should verify if Added Data is exist


  Scenario: [US_33 / TC_01]
    Given Set "api/addFindingCategory" parameters
    Then Then Saves the returned response as a result of Post Request sent for Finding Category List with valid data
    Then Set "api/deleteFindingCategory" parameters
    Then Saves the returned response as a result of Delete Request for Finding Category by id
    Then Verifies that deleted FindingCategoryId information is the same as the id information in the request body
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"

  Scenario: [US_33 / TC_02]
    Given Set "api/deleteFindingCategory" parameters
    Then Saves the returned response as a result of Finding Category Request for Visitors by invalid data
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"


  Scenario:  [US_33 / TC_03]
    Given Set "api/getFindingCategory" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies the added Finding Category record is deleted

  Scenario:  [US_39 / TC_01]
    Given Set "api/addFinding" parameters
    Then  Saves the returned response as a result of Post Request sent for Finding List with valid data
    Then Set "api/deleteFinding" parameters
    Then Saves the returned response as a result of Delete Request for Finding List by id
    Then  In Finding list, Verifies that deletedId information is the same as the id information in the request body
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"

  Scenario: [US_39 / TC_02]
    Given Set "api/deleteFinding" parameters
    Then Saves the returned response as a result of Finding Delete Request for Visitors by invalid data
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario:  [US_39 / TC_03]
    Given Set "api/getFinding" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies the added finding record is deleted

  Scenario:  API [US_15 / TC_01]
    Given Set "api/getBloodGroupById" parameters
    Then Saves the returned response as a result of Get Blood Group By Id Request sent for List
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"
    Then Verifies that record includes "id, name, is_blood_group, created_at"

  Scenario: API  [US_15 / TC_02]
    Given Set "api/getBloodGroupById" parameters
    Then Saves the returned response as a result of Get Blood Group By Id Request sent for List with invalid data
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"


  Scenario:API  [US_06 / TC_01]
    Given Set "api/visitorsPurposeAdd" parameters
    Then  Saves the returned response as a result of Post Request sent for Visitors Purpose Add List with correct data
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"


  Scenario:API  [US_06 / TC_02]
    Given Set "api/visitorsPurposeList" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies that data has been created


  Scenario: [US_06 / TC_03]
    Given Set "api/visitorsPurposeAdd" parameters
    Then Saves the returned response as a result of Post Request sent for Visitors Purpose Add List with invalid data
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario: [US_28 / TC_01]
    Given Set "api/addNotice" parameters
    Then  Saves the returned response as a result of Post Request sent for  Notice List with valid data
    Then Set "api/deleteNotice" parameters
    Then Saves the returned response as a result of Delete Request for Delete Notice by id
    Then  In Notice list, Verifies that deletedId information is the same as the id information in the request body
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"

  Scenario: [US_28 / TC_02]
    Given Set "api/deleteNotice" parameters
    Then  Saves the returned response as a result of Post Request sent for  Notice List with invalid data
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario:  [US_28 / TC_03]
    Given Set "api/getNotice" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies the added Notice  record is deleted








  Scenario:  [US_23 / TC_01]
    Given Set "api/addExpenseHead" parameters
    Then  Saves the returned response as a result of Post Request sent for ExpenseHead List with valid data.
    Then Set "api/deleteExpenseHead" parameters
    Then Saves the returned response as a result of Delete Request for ExpenseHead List by id
    Then  In ExpenseHead list, Verifies that deletedId information is the same as the id information in the request body.
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"

  Scenario: [US_23 / TC_02]
    Given Set "api/deleteExpenseHead" parameters
    Then Saves the returned response as a result of ExpenseHead Delete Request by invalid data.
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"

  Scenario:  [US_23 / TC_03]
    Given Set "api/getExpenseHead" parameters
    Then Saves the returned response as a result of Get Request sent for List
    Then Verifies the added ExpenseHead record is deleted.

