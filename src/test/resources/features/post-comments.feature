@Regression
@Comments
@Posts
Feature: Comments are displayed for every post
  As a typicode user
  I want to be able to see the comments associated with every post
  So that I can learn about different views on each post


  Scenario Outline: Email addresses in the comment section are in proper format
    Given the username "<USER>" corresponds to an existing user id

    And the user has created at least 1 post

    When all the comments associated with the user's posts are fetch from the API

    Then all the email addresses in the comments have proper format

    Examples:
      | USER            |
      | Delphine        |
      | Bret            |
#      | NonExistingUser |
#  un-comment the row above to deliberately fail the test