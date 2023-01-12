Feature: Validation Pet APIs

  Scenario:Add new pet
    Given Add Pet Payload
    When user calls AddPet API with post http request
    Then the API call is success with status code 200
    And verify that the pet name is correctly added


  Scenario: Get the added pet
    When user calls getPet API with get http request
    Then the API call is success with status code 200
    And Verify that the user can get the added pet using the provided id


  Scenario: Update the name and status of the added pet using form data
    When user Calls UpdatePet API with post http request with "Nutella_updated" and "Sold"
    Then the API call is success with status code 200
    And the returned message displays code 200 and the pet id

  Scenario: Get the updated pet
    When user calls getPet API with get http request
    Then the API call is success with status code 200
    And the name is updated with "Nutella_updated" and status with "Sold" correctly

  Scenario: Delete the added pet
    When user Calls DeletePet API with delete http request
    Then the API call is success with status code 200
    And the returned message displays code 200 and the pet id

  Scenario: Delete the deleted pet
    When user Calls DeletePet API with delete http request
    Then the API call fails with status code 404



