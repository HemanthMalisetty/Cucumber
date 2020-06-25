#Please Do not change Feature Templet
Feature: To test the application to calculate college fee for different type of student
#Please Do not change Scenario Outline Templet
 Scenario Outline: To calculate college fee for different type of student

#Please Do not change Given Templet
    Given Browser is launched and user is on application page
    When User Enters student name, "<Student Type>", "<Students per room>"
    When User clicks on the calculate fee button
    Then "<College Fee>", "<Hostel Fee>", "<Additional Fee>", "<Total Fee>" should be correctly displayed

    Examples:
      | Student Type | Students per room | College Fee | Hostel Fee | Additional Fee | Total Fee   |
      | Day Scholar  |                 0 | Rs.120000.0 |          0 |              0 | Rs.120000.0 |
      | Hosteller    |                 1 | Rs.120000.0 | Rs.75750.0 | Rs.30300.0     | Rs.226050.0 |
      | Hosteller    |                 2 | Rs.120000.0 | Rs.75750.0 | Rs.15150.0     | Rs.210900.0 |
      | Hosteller    |                 3 | Rs.120000.0 | Rs.75750.0 |              0 | Rs.195750.0 |

