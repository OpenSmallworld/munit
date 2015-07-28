Feature: My test feature
Hello - this is some documentation to be ignored


Scenario: My Test 1

Given my test has been set up
When I try to run the test
Then the test should say it is happy
And I should display coordinate (1234,2345)

Scenario: My Test 2

This is some more documentation. Again, it should be ignored,
until we get a recognised keyword at the start of the line
(like 'Given' coming up soon)

Given my test has been set up
When I try to run the test again
Then the test should say it is unhappy
# This line is a comment. It should be ignored
And I should display coordinate (2345,3456)


