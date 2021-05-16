# Software architecture

## Structure

The rpgbattlemap.ui package contains all the user interface element creation and event handlers of the application. The rpgbattlemap.domain package contains all the code related to application logic.

## User interface

User interface two different views

-	Grid creation
-	Grid

These have been implemented as scene objects and are set to a stage object created in application start. The interface creation logic is implemented in [rpgbattlemap.RPGBattlemapUI](https://github.com/annareej/rpg-battlemap/blob/738a6ea53141f0fe6bd0f41f3e5cf1a93ee32872/RPGBattlemap/src/main/java/rpgbattlemap/ui/RPGBattlemapUI.java) class.

The graphic events associated with token are found in class [rpgbattlemap.UITokenControls](https://github.com/annareej/rpg-battlemap/blob/d8102ea51d339ca9b6e8beee07487239c32243c7/RPGBattlemap/src/main/java/rpgbattlemap/ui/UITokenControls.java).

## Application logic

Application has three classes: Grid, Square and Token. 

![Class diagram](/documentation/classdiagram.png)

Grid has given number of squares in it. Token has a square marking its position in grid. These classes have methods for passing to the UI like; getting specific square with coordinates from mouse, move token position left, right, up, and down etc. 

## Main functionalities

These sequence diagrams describe few main functionalities of the application.

### Creating a grid

When user writes width and height of the grid in input fields and clicks createGrid-button:

![Create grid](/documentation/createGridSeq.PNG)

The event handler of the button click will call createGridScene-method that will create new Grid object that will then create a two-dimensional array of Square objects. Then a UI scene is created with same parameters.

### Creating a token

User has chosen size and colour of a token and clicked createToken-button. createTokenMode is set true and token is created by clicking a position in grid:

![Create token](/documentation/createTokenSeq.PNG)

Mouse event handler on clicking grid, when tokenCreationMode is set true, will get Square object from mouse position and will create a new token with chosen size, colour and the Square as the position of the token. 

### Move token left

User has selected a token and pressed left arrow button:

![Move token](/documentation/moveTokenLeft.PNG)

Event handler for key presses will trigger. The selected token will call moveLeft-method of Token class. It will get a square in left side of the current position from grid. This kind of event has been implemented for up, down, right, and left arrow keys. 
