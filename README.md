#My Health App

## description: 

The app displays some healthy recipes utilising MealDb API(https://www.themealdb.com/api.php) to fetch meal recipes. It also shows Some Diet Tips you can follow. 

<img  src="/Home_with_Drawer.png" width="200"/> &nbsp;&nbsp;<img  src="/RecipeCategories.png" width="200"/>  <img  src="/Meals.png" width="200"/> &nbsp;&nbsp; <img  src="/Recipe.png" width="200"/> 

## Features
* Home Screen with Drawer Layout showing menus for Recipes, Diet Tips, Calorie Counter
* Recipe Screen showing list of meal categories, clicking on each will display list of meals for the selected category.
* Clicking on Each Meal, it displays the recipe for the meal along with a youtube link for the recipe
* Diet tips screen displays some tips in a card format, you can swipe the cards to read the next tip.

## Architecture
* Built with Modern Android Development practices.
* Utilized Repository pattern for data.
* Includes valid Unit tests for ViewModel and Repository.

## Built With 🛠
- Kotlin - First class and official programming language for Android development.
- Coroutines - For asynchronous and more..
- Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps.
    - LiveData - Data objects that notify views when the underlying data changes.
    - ViewModel - Stores UI-related data that isn't destroyed on UI changes.
    - ViewBinding - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- Dependency Injection
    - Hilt - Easier way to incorporate Dagger DI into Android apps.
- Material Components for Android - Modular and customizable Material Design UI components for Android.

## Improvements:
- Add more Unit/UI Tests
- UI improvements
- More features like products link for overweight people, motivational quotes

## 👨 Developed By
*Fida Mohammed*
