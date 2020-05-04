Feature: Geometry Interaction Methods

The below tests exercise the methods 'contains?' and 'touches?'
on area geometries. The tests draw triangular areas and then test
their interactions.


Scenario: Triangle Within Triangle

Given a shape called shape1 with coordinates (1000,1000)
And (3000,4000)
And (5000,1000)
And (1000,1000)
And a shape called shape2 with coordinates (2000,2000)
And (3000,3000)
And (4000,2000)
And (2000,2000)
When I test that shape1 contains shape2
Then I get a positive result
When I test that shape2 contains shape1
Then I get a negative result
When I test that shape1 touches shape2
Then I get a negative result


Scenario: Triangle touches Triangle

Given a shape called shape1 with coordinates (4000,2000)
And (5000,3000)
And (5000,2000)
And (4000,2000)
And a shape called shape2 with coordinates (2000,2000)
And (3000,3000)
And (4000,2000)
And (2000,2000)
When I test that shape1 touches shape2
Then I get a positive result
