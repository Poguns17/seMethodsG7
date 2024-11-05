# Use Case: Top N Populated Countries in the World

- **Actor**: Report Viewer, Data Analyst
- **Description**: Displays a report listing the top N most populated countries in the world, where N is provided by the user.

## Preconditions
- User is logged in and specifies a value for N.

## Postconditions
- A report with the top N populated countries is generated.

## Flow of Events
1. **User Action**: User selects the “Top N Countries by Population (World)” report.
2. **User Action**: User enters a number (N) to specify how many countries should be displayed.
3. **System Action**: System retrieves population data for all countries.
4. **System Action**: System sorts the countries by population in descending order.
5. **System Action**: System displays the top N countries based on user input.

## Alternative Flows
- **Invalid Input for N**: If N is invalid (e.g., non-numeric or too large), the system prompts the user to enter a valid number.
