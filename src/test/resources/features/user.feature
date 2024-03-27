@api_testing
Feature: User API Testing

  Scenario Outline: Create user and verify details
    Given I send a POST request to create a user with the following details "<id>" "<username>" "<first_name>" "<last_name>" "<email>" "<password>" "<phone>" "<user_status>"
    Then the user should be created successfully

    Examples: User with details
      | id    | username    | first_name | last_name | email        | password | phone   | user_status |
      | 43433 | 323243431qw | RRRR       | LLL       | we@gmail.com | 23dwewe  | 2324433 |0            |


  Scenario Outline: Update User Details
    Given I send a POST request to create a user with the following details "<id>" "<username>" "<first_name>" "<last_name>" "<email>" "<password>" "<phone>" "<user_status>"
    When I update the user "<new_first_name>"
    Then the API should respond with a "<status_code>"

    Examples: User with details
      | id    | username   | first_name | last_name | email        | password | phone   | user_status | status_code | new_first_name |
      | 44553 | 32258345qw | Osman      | LLL       | we@gmail.com | 23dwewe  | 2324433 | 0           | 200         | Allison        |


  Scenario Outline: Retrieve User with Non-existing Username
    When I attempt to retrieve user details with the non-existing username "<non_existing_user>"
    Then the API should respond with a "<status_code>"

    Examples:
      | non_existing_user | status_code |
      | 33434999323qw     | 404         |


  Scenario Outline: Delete User
    Given I send a POST request to create a user with the following details "<id>" "<username>" "<first_name>" "<last_name>" "<email>" "<password>" "<phone>" "<user_status>"
    When I attempt to delete the user with the username "<username>"
    Then the API should respond with a "<status_code>"

    Examples: User with details
      | id    | username   | first_name | last_name | email        | password | phone   | user_status | status_code |  |
      | 44553 | 32258345qw | Osman      | LLL       | we@gmail.com | 23dwewe  | 2324433 | 0           | 200         |  |

