# SnakeGame

SnakeGame is a modern twist on the classic Snake game implemented in Java. The game challenges players to navigate a snake to eat different types of fruit and grow longer while avoiding collisions with the walls or the snake's own body. The game incorporates additional features like increasing speed and multiple fruit types, adhering to SOLID principles to ensure a clean and maintainable codebase.

![SnakeGame](https://github.com/user-attachments/assets/4a411bc7-2e40-4f10-9672-12c8d323f89b)

## Goal of the Project

The goal of the SnakeGame project is to provide an entertaining and engaging game experience while demonstrating clean code practices. The game is designed to be simple yet challenging, encouraging players to beat their high scores and adapt to the increasing difficulty. It also serves as a learning resource for applying SOLID principles in Java game development.

## User Experience (UX)

### Strategy

- **App Objective**: SnakeGame aims to provide a fun and nostalgic gaming experience, suitable for players of all ages, with an increasing level of challenge over time. It features simple controls and addictive gameplay.
  
- **User Needs**: The game focuses on delivering smooth gameplay, clear graphics, and responsive controls.

Opportunities/Problems table used to determine the strategic priorities UX efforts should address (in this order):

| Opportunity/Problem                       | Importance | Viability/Feasibility |
|-------------------------------------------|------------|-----------------------|
| A. Have a functional game                 | 5          | 5                     |
| B. Have an ergonomic interface            | 5          | 5                     |
| C. Display player name and score          | 5          | 5                     |
| D. Include increasing speed and new fruit | 4          | 5                     |

### Scope

#### Functional Specifications

SnakeGame is inspired by classic Snake games and focuses on essential gameplay features, including score tracking, increasing speed, and multiple fruit types with different effects.

**Feature Set:**

- Control the snake using arrow keys.
- Eat apples to grow the snake.
- Eat special fruits to grow more significantly.
- Speed increases every few seconds to increase the challenge.
- Avoid collisions with walls and the snake's body.
- Display player's name and score at the end of the game.

### Structure

#### Interaction Design

**Consistency & Predictability:**

- A common interface that provides a smooth gameplay experience.
- Clear visual cues for player interactions.
- Separate areas for game board and score display.

**Feedback:**

- Visual feedback for user interactions and game events.
- Interactive elements that respond to player input.

### Surface

**App's Colors:**

- **Background**: Light gray and green tones for the game board.
- **Snake**: Bright and easily distinguishable colors.
- **Apples**: Red for visibility.
- **Special Fruit**: Unique colors to distinguish from regular apples.

**Fonts:**

- Simple, clear fonts for text and score display.

## Existing Features

- Smooth and responsive controls.
- Score tracking throughout the game.
- Increasing speed to add challenge.
- Player name input at the start.
- Display of player's name and score at the end.
- Additional fruit types that have unique effects on the snake.

## Features to Add

| Label                         | Description                                            |
|-------------------------------|--------------------------------------------------------|
| Multiplayer support           | Allow multiple players to compete simultaneously.      |
| High score leaderboard        | Display a leaderboard of top scores.                   |
| Different game themes         | Introduce new skins and themes for the game.           |

## Technologies Used

- **Java**: Core programming language used for development.
- **Swing**: Java library used for building the graphical user interface.
- **Eclipse IDE**: Integrated Development Environment used for coding and testing.

## Testing

Extensive manual testing has been conducted to ensure the game's stability and performance on different Windows platforms.

**Tests Conducted:**

| Test No. | Test Name                                      | Result |
|----------|------------------------------------------------|--------|
| 1        | Responsive controls                            | PASS   |
| 2        | Correct collision detection                    | PASS   |
| 3        | Accurate score tracking                        | PASS   |
| 4        | Name and score display at game end             | PASS   |
| 5        | Speed increases over time                      | PASS   |
| 6        | Special fruit effects                          | PASS   |
| 7        | Compatibility with Windows 8, 8.1, 10, and 11  | PASS   |

## Deployment

### Creation of the JAR

To create a JAR file using Eclipse:

1. Right-click on the project > Click on Export > JAR file.
2. Select the resources to include in the JAR.
3. Choose desired options > Enter the destination path for the JAR > Click Next.
4. Select the class containing the `main` method.
5. Click Finish to create the JAR file.

## Credits

- **Images**: Icons and images sourced from Google and open resources.

## Acknowledgments

Special thanks to friends and family for their support and feedback during the development and testing of this project.
