# INF1009P7-T3
Please check all codes before any commits


Notes: classes like entity or scenes, should have protected methods only and be packaged in their own sections (scene & sceneManager). This is so other managers can't edit entities not controlled by them

Code Structure:

  GameMaster
    - while loop to simply run the whole application
    - should only run simlife and stop after user stops to play
    
  SimulationLifecycleManager
    - comes together and creates all the game logic
    - error handling, exceptions and error messages
    
  Player 
  - stores player information, check if player wants to continue
  - player entity can can information from player

  SceneManager
  - creates all the scenes, update and deletes them

  Scene
  - Stores texture and sound
  - has 1 entitymanager
  - displays all the entities and texture using iIO

  EntityManager
  - stores list of entities
  - entity creation, update, and deletion
  - calls ConflictMgr to handle collision

  Entity
  - stores entity values eg. health, position, texture

  AIManager
  - handles all AI entities
  - calls iAIMovement to generate a movement or action

  iAIMovement
  - interface for random generation

  iIO
  - listens and output user inputs
  - sends objects to be displayed onto screen
  - system sound
  
  iColllide
  - checks for collision among entities

  
  CollisionManager
  - runs through collision logic and (?updates entity?) or send list to entityMgr for updating



  
