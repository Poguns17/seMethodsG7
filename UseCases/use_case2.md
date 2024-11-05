# Use Case: All Countries in a Continent Organized by Population (Largest to Smallest)

- **Actor**: Report Viewer, Data Analyst
- **Description**: Displays a report listing all countries in a specific continent, sorted by population from largest to smallest.

## Preconditions
- User is logged in and selects a specific continent.

## Postconditions
- A report of countries in the selected continent is generated and displayed.

## Flow of Events
1. **User Action**: User selects the “Continent Countries by Population” report.
2. **User Action**: User chooses a specific continent from a list.
3. **System Action**: System retrieves data for countries within the selected continent.
4. **System Action**: System sorts the countries by population in descending order.
5. **System Action**: System displays the sorted list of countries for the continent.

## Alternative Flows
- **No Countries Found**: If no data is found for the selected continent, the system displays a message indicating no results.
