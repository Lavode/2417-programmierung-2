Service-Locator:
	Changes:
	Add Interfaces:	IGame
					ICommandParser
					IUserInteraction
	Add Classes:	TestGame
					TestUserInteraction
					DefaultServiceLocator
					TestServiceLocator
					ServiceLocator
	The ServiceLocator Classes are just Copies of your idea on your SerciveLocator Classes.
	The Service Locator is used in ParserV1.class, ParserV2.class to create a new IGame,
	and in UserInteraction.class to set up an ICommandParser.
	TestGame is the IGame that can be used for testing. it's the same as Game.class but doesn't 
	create any output. Therefore i had to introduce a new class TestUserInteraction to simulate 
	UserInteraction. It doesn't need any Input (which is as well nice for testing)
	
dependency-injection:
	//GLHF Michi
	
silent-tests:
	Since all our tests were silent beforehand there is nothing to do here.
	