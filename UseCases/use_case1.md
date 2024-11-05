# Use Case: All Countries in the World Organized by Population (Largest to Smallest)

- **Actor**: Report Viewer, Data Analyst
- **Description**: Displays a report listing all countries in the world, sorted by population from largest to smallest.

## Preconditions
- The user is logged into the system and has access to the Population Reporting feature.

## Postconditions
- A report is generated showing countries ordered by population.

## Flow of Events
1. **User Action**: User selects the “World Countries by Population” report.
2. **System Action**: System retrieves country data from the database.
3. **System Action**: System sorts countries by population in descending order.
4. **System Action**: System displays the sorted list of countries.

## Alternative Flows
- **Database Error**: If data cannot be retrieved, the system shows an error message and logs the issue.
