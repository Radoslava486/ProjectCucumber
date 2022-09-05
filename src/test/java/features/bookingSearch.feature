Feature: Booking Search
  Scenario Outline: Rating validation
    Given Hotel name for search is "<hotelName>"
    When I navigate to booking.com
    And I enter search name
    And I press search hotel button
    Then First result contains Hotel name in title
    And Hotel has rating "<rating>"
    Examples:
      | hotelName                     |  rating  |
      | Sunrise Arabian Beach Resort  |   9.1    |
      | Poggio Hotel                  |   8.4    |