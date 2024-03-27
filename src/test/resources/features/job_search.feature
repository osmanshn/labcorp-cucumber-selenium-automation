@ui_testing
Feature: LabCorp Job Search

  Scenario Outline: Search for Job and Validate Details
    Given I am on the LabCorp website
    When I navigate to the Careers page
    And I search for a "<job_title>" job
    And I select the first job listing "<job_title>"
    Then I should see the Job Title is "<job_title>"
    And I should see the Job Location is "<job_location>"
    And I should see the Job Id is "<job_id>"
    And I should see second sentence of first paragraph under description is "<description_text>"
    And I should see third requirement is "<third_requirement>"
    And I should see fourth responsibility is "<fourth responsibility>"
    When I click on the Back to search results button
    Then I should see the search results page
    Examples:
      | job_title                    | description_text                                                                                                                                                                                        | third_requirement                                                                                                                        | fourth responsibility                                                                                                                                                 | expected_automation_tool | job_location                                       | job_id   |
      | Senior Java Engineer         | The Application Architect role will be part of newly formed development teams within Labcorp and contribute to creating and implementing enterprise standards for a web service platform (RESTful APIs) | 7+ years of experience in each of the following skills: Java, Spring Core, Spring Integration, Spring Boot, Spring MVC, Spring Security. | Work with developers and integration team to brainstorm and research new design patterns in creating reusable solutions that can be utilized across the organization. | Selenium                 | Durham, North Carolina, United States of America   | 2357467  |
