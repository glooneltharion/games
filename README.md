# Game Application with Test Focus

## Overview

The Game Application is a Java-based platform for managing player records, game details, and game-related interactions. The application includes tests for various components, ensuring robustness and reliability. It employs Spring Boot, JPA (Java Persistence API), and Mockito for testing services and controllers.

## Test-Driven Development

The application integrates several test classes to ensure code functionality and behavior:

### PlayerRepositoryTest (`com.glooneltharion.games.repository.PlayerRepositoryTest`)

- Utilizes `@DataJpaTest` for integration testing with an H2 database.
- Includes tests for retrieving player details by ID from the repository.

### PlayerServiceImplTest (`com.glooneltharion.games.service.PlayerServiceImplTest`)

- Tests the `PlayerService` implementation using Mockito for mocking repository dependencies.
- Covers methods for saving players, handling exceptions, and testing player scores.

### PlayerControllerTest (`com.glooneltharion.games.controller.PlayerControllerTest`)

- Focuses on testing HTTP requests for player-related functionalities.
- Verifies controller endpoints for playing games, saving player data, and handling exceptions.

### GameControllerTest (`com.glooneltharion.games.controller.GameControllerTest`)

- Tests the Game Controller endpoints for retrieving game details and handling errors.
- Mocks dependencies to verify game-related functionalities.

### ApiControllerTest (`com.glooneltharion.games.controller.ApiControllerTest`)

- Tests API controller endpoints for retrieving player data and adding new players.
- Uses Mockito for mocking service dependencies.

### ApiControllerIntegrationTest (`com.glooneltharion.games.controller.ApiControllerIntegrationTest`)

- Integration tests for API controllers to validate player-related functionalities and HTTP responses.
- Simulates HTTP requests to endpoints and verifies expected outcomes.

## Project Structure

The project follows a structure with various packages:

- `com.glooneltharion.games.model`: Contains entity classes for Game and Player.
- `com.glooneltharion.games.repository`: Includes repository interfaces for Game and Player entities.
- `com.glooneltharion.games.service`: Implements service classes for managing game and player operations.
- `com.glooneltharion.games.controller`: Contains controller classes for handling HTTP requests and responses.

## How to Run Tests

1. Ensure Java and Gradle are installed.
2. Clone the project repository.
3. Configure test databases in `application.properties`.
4. Run test classes using IDE or Gradle command line.

