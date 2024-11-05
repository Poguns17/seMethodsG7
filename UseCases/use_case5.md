# Use Case: Top N Populated Countries in a Continent

- **Actor**: Report Viewer, Data Analyst
- **Description**: Displays a report listing the top N most populated countries within a specific continent.

## Preconditions
- The user is logged in, selects a continent, and specifies a value for N.

## Postconditions
- A report is generated showing the top N populated countries for the selected continent.

## Flow of Events
1. **User Action**: User selects the “Top N Countries by Population (Continent)” report.
2. **User Action**: User chooses a specific continent and enters a number (N).
3. **System Action**: System retrieves data for countries in the selected continent.
4. **System Action**: System sorts countries by population in descending order.
5. **System Action**: System displays the top N countries based on user input.

## Alternative Flows
- **No Data Available**: If no data is available for the specified continent, a “No data found” message is displayed.
