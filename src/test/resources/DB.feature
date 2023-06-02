@jdbc03
Feature:

  Scenario: DB [US_08 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "select * from bed where bed_group_id=4 and is_active='no';" and Column name "name" is performed.
    Then Verify that the result is "105".
    Then Close database.

  Scenario: DB [US_03 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM appointment where patient_id=20;" and Column name "source" is performed.
    Then Verify that the result is "Offline".
    Then Close database.

  Scenario: DB [US_01 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM ambulance_call where patient_id=1 and driver= 'Smith';" and Column name "id" is performed.
    Then Verify that the result size is 2.
    Then Close database.


  Scenario: DB [US_09 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT weight FROM birth_report where weight >=2.5;" and Column name "weight" is performed.
    Then Verify that the result size is 6.
    Then Close database.


  Scenario: DB [US_04 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM appointment where global_shift_id=1;" and Column name "time" is performed.
    Then Saves the result size before the midday.
    And A query prepared with the given data "SELECT * FROM appointment where global_shift_id=3;" and Column name "time" is performed.
    Then Saves the result size after the midday.
    Then Verify that before the midday appointment is less than after the midday appointment.
    Then Close database.

  Scenario: DB [US_19 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM doctor_shift where staff_id=2 and day='tuesday';" and Column name "start_time" is performed.
    Then Save the start time result.
    And A query prepared with the given data "SELECT * FROM doctor_shift where staff_id=2 and day='tuesday';" and Column name "end_time" is performed.
    Then Save the end time result.
    Then Verify that the doctor worked for 2 hours on tuesday.
    Then Close database.


  Scenario: DB [US_30 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "select * from visitors_book where related_to='Maria Fernandis (4) (OPDN24)';" and Column name "name" is performed.
    Then Verify that the result is "Jhon".
    Then Close database.


  Scenario: DB [US_11 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "select * from blood_donor where id=7;" and Column name "donor_name" is performed.
    Then Verify that the result is "Maria".
    And A query prepared with the given data "select * from blood_donor where id=7;" and Column name "gender" is performed.
    Then Verify that the result is "Female".
    And A query prepared with the given data "select * from blood_donor where id=7;" and Column name "father_name" is performed.
    Then Verify that the result is "Jhonson".
    And A query prepared with the given data "select * from blood_donor where id=7;" and Column name "address" is performed.
    Then Verify that the result is "England".
    And A query prepared with the given data "select * from blood_donor where id=7;" and Column name "date_of_birth" is performed.
    Then Verify that the date is "2001-03-02".
    Then Close database.

  Scenario: DB [US_05 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "select * from appointment_payment where payment_type='Offline';" and Column name "id" is performed.
    Then Verify that the result 1 exists.
    Then Verify that the result 2 exists.
    Then Verify that the result 3 exists.
    Then Verify that the result 13 exists.
    Then Verify that the result 14 exists.
    Then Verify that the result 15 exists.
    Then Verify that the result 17 exists.
    Then Verify that the result 18 exists.
    Then Verify that the result 20 exists.
    Then Verify that the result 21 exists.
    Then Verify that the result 25 exists.
    Then Close database.


  Scenario: DB [US_16 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM death_report order by death_date desc;" and Column name "guardian_name" is performed.
    Then Verify that the result is "Kane Stark".
    Then Close database.

  Scenario: DB [US_25 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT patient_name,gender, email FROM patients WHERE patient_name LIKE '%Jain%';" and Column name "gender" is performed.
    Then Verify that the result is "Female".
    And A query prepared with the given data "SELECT patient_name,gender, email FROM patients WHERE patient_name LIKE '%Jain%';" and Column name "email" is performed.
    Then Verify that the result is "reshu@gmail.com".
    Then Close database.

  Scenario: DB [US_14 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT standard_charge FROM charges ORDER BY standard_charge ASC LIMIT 5;" and Column name "standard_charge" is performed.
    Then Verify that the result is 100.0, 100.0, 100.0, 110.0, 120.0
    Then Close database.




  Scenario: DB [US_20 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "insert into heallife_hospitaltraining.events values (35,'denemedeneme',null , 01/01/2021,02/01/2021,'public','#c53da9',0,null,'yes','2023-05-29 10:30:00');" is performed.
    And A query prepared with the given data "SELECT *  FROM heallife_hospitaltraining.events  where id=35;" and Column name "id" is performed.
    Then Verify that the result is 35.
    And A query prepared with the given data "SELECT *  FROM heallife_hospitaltraining.events;" and Column name "id" is performed.
    Then Save the result size before the deleting.
    And A query prepared with the given data "delete  from heallife_hospitaltraining.events  where id=35;" is performed.
    And A query prepared with the given data "SELECT *  FROM heallife_hospitaltraining.events;" and Column name "id" is performed.
    Then Save the result size after the deleting.
    Then verify that the record is deleted.
    Then Close database.


  Scenario: DB [US_02 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.appointment;" and Column name "id" is performed.
    Then Verify that the result 122 doesn't exist.
    And A query prepared with the given data "insert into heallife_hospitaltraining.appointment values ('122', '1', '38', '38', '2022-10-25 16:26:00', NULL, '12', '', '112', '', 'Message', 'approved', 'Offline', '', '', '32', '42', '12', 'yes', '2022-10-25 06:57:09');" is performed.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.appointment;" and Column name "id" is performed.
    Then Verify that the result 122 exists.
    And A query prepared with the given data "delete  from heallife_hospitaltraining.appointment  where id=122;" is performed.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.appointment;" and Column name "id" is performed.
    Then Verify that the result 122 doesn't exist.
    Then Close database.



  Scenario: DB [US_29 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.vehicles order by manufacture_year asc;" and Column name "id" is performed.
    Then Save the id list related to manufacture year.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.vehicles order by created_at asc;" and Column name "id" is performed.
    Then Save the id list related to created at.
    Then Verifies that a vehicle has same index in each list.
    Then Close database.



  Scenario: DB [US_12 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.blood_donor_cycle where institution !='';" and Column name "id" is performed.
    Then Verify that the result 17 exists.
    Then Close database.


  Scenario: DB [US_21 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.lab where lab_name like 'X-RAY%' order by id desc;" and Column name "id" is performed.
    Then Verify that the result is 3.
    Then Close database.



  Scenario: DB [US_13 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT distinct charge_type_id FROM heallife_hospitaltraining.charge_categories where name like 'P%' ;" and Column name "charge_type_id" is performed.
    Then Verify that the result 6 exists.
    Then Verify that the result 7 exists.
    Then Close database.


  Scenario: DB [US_15 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.consultant_register;" and Column name "id" is performed.
    Then Verify that the result 45 doesn't exist.
    And A query prepared with the given data "insert into heallife_hospitaltraining.consultant_register values ('45','9', '2023-05-25 11:30:00', '2023-05-25', 'insert', '5', '2023-05-25 04:50:19');" is performed.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.consultant_register;" and Column name "id" is performed.
    Then Verify that the result 45 exists.
    And A query prepared with the given data "delete  from heallife_hospitaltraining.consultant_register  where id=45;" is performed.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.consultant_register;" and Column name "id" is performed.
    Then Verify that the result 45 doesn't exist.
    Then Close database.


  Scenario: DB [US_22 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.languages where short_code like 'yi';" and Column name "language" is performed.
    Then Verify that the Language is "Yiddish".
    Then Close database.



  Scenario: DB [US_26 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.patients order by age desc;" and Column name "known_allergies" is performed.
    Then Verify that the result is "Fast food".
    Then Close database.


  Scenario: DB [US_10 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.birth_report where father_name='Mahesh';" and Column name "child_name" is performed.
    Then Verify that the result "Rohit" exists.
    Then Verify that the result "Reyana" exists.
    Then Verify that the result "child" exists.
    Then Close database.


  Scenario: DB [US_23 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM medicine_supplier where address = 'Andheri, Mumbai';" is performed for row.
    Then Verify that the result 3 exists.
    Then Verify that the result "7546985214" exists.
    Then Verify that the result "9754632587" exists.
    Then Verify that the result "VKS Pharmacy" exists.
    Then Verify that the result "Vinay Shrivastava" exists.
    Then Close database.

  Scenario: DB [US_24 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.nurse_note;" and Column name "id" is performed.
    Then Verify that the result 40 doesn't exist.
    Then Verify that the result 41 doesn't exist.
    And A query prepared with the given data "insert into heallife_hospitaltraining.nurse_note (id,date,ipd_id,staff_id,note,comment,updated_at)values (40, '2022-10-25 16:26:00', 15, 63, 'çok acil durum', 'doktor nerede?', '2022-10-25 17:26:00'),(41, '2022-05-25 16:26:00', 15, 63, 'çok acil', 'doktor ?', '2023-10-25 17:26:00');" is performed.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.nurse_note;" and Column name "id" is performed.
    Then Verify that the result 40 exists.
    Then Verify that the result 41 exists.
    And A query prepared with the given data "delete  from heallife_hospitaltraining.nurse_note  where id=40;" is performed.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.nurse_note;" and Column name "id" is performed.
    Then Verify that the result 40 doesn't exist.
    And A query prepared with the given data "delete  from heallife_hospitaltraining.nurse_note  where id=41;" is performed.
    And A query prepared with the given data "SELECT * FROM heallife_hospitaltraining.nurse_note;" and Column name "id" is performed.
    Then Verify that the result 41 doesn't exist.
    Then Close database.


  Scenario: DB [US_28 / TC_01]
    Given Connect to database.
    And A query prepared with the given data "select * from vehicles order by manufacture_year asc;" and Column name "driver_name" is performed.
    Then Verify that the result is "bayram erguven".
    And A query prepared with the given data "select * from vehicles order by manufacture_year asc;" and Column name "driver_licence" is performed.
    Then Verify that the result is "b".
    Then Close database.

  Scenario:  DB_US06 TC_01
    Given Connect to database.
    And A query prepared with the given data "select * from appointment_queue where created_at like '%2023-05%';" and Column name "id" is performed.
    Then Verify that the result 13 exists.
    Then Verify that the result 14 exists.
    Then Verify that the result 15 exists.
    Then Verify that the result 16 exists.
    Then Close database.

  Scenario:  DB_US17 TC_01  Matching test of department_name and created_at information of contents in department table via database
    Given Connect to database.
    Then  A query is prepared with the given data and the query is performed
    Then  Verifies that the department_name value of the returned Result set data is "Cardiology" and the created_at value is "2021-10-25 00:54:54"
    And   Close database.

