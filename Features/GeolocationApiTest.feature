Feature: Geolocation API test feature file

  Scenario: Test geolocation API with valid request body
    Given I have geolocation API with baseUrl "https://www.googleapis.com"
    And API key is included in queryParam and contentType is "application/json"
    When I post request to geolocation API with valid request body
    Then I get successful response from API
