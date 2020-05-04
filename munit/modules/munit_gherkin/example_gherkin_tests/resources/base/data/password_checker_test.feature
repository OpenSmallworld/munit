Feature: A password checker

Performs a few simple tests on a password (ensuring it
is long enough, includes numbers etc.)


Scenario Outline: Passwords which are short fail

Given a password checker
When I test password '<password>'
Then the password check will <result>

Examples:
| password | result | comment    |
| shorty   | fail   | too short! |
| long_one | passes | good size! |