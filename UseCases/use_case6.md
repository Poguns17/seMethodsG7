# Use Case: Top N Populated Countries in a Region

- **Actor**: Report Viewer, Data Analyst
- **Description**: Displays a report listing the top N most populated countries in a specific region.

## Preconditions
- User is logged in, selects a region, and specifies a value for N.

## Postconditions
- A report is generated with the top N populated countries in the selected region.

## Flow of Events
1. **User Action**: User selects the “Top N Countries by Population (Region)” report.
2. **User Action**: User chooses a specific region and enters a number (N).
3. **System Action**: System retrieves data for countries in the specified region.
4. **System Action**: System sorts countries by population in descending order.
5. **System Action**: System displays the top N countries based on user input.

## Alternative Flows
- **Region Not Found**: If the region has no data, a “No data found” message is shown.
