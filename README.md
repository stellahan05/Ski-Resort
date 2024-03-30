# Ski Resort Management System

## **What will the application do?**
- **Trail Management:**
  - Add new trails with varying difficulty levels.
  - Monitor the status (open/closed) of each trail.
  - Change the status of each trail.
  - Display a list of all open trails.

## **Who will use it?**

Ski resort administrators responsible for overseeing the operations and guest experience of the resort.

## **Why is this project of interest to you?**

As someone who loves skiing and snowboarding, I make use of my favourite ski resorts' applications each winter, so this
project provides a practical application of Java in the context of a real-world scenario that perfectly aligns with my 
interests.

## **User Stories**
- As a user, I want to be able to add a trail to the resort and specify the name and difficulty level.
- As a user, I want to be able to view a list of all the trails in the resort.
- As a user, I want to be able to mark a trail as open/closed from the list of all trails.
- As a user, I want to be able to select a trail from the list and view it in detail.
- As a user, I want to be able to write a review for a trail.
- As a user, I want to be able to remove a trail from the resort.
- As a user, I want to be able to save my ski resort app with the list of trails to file (if I so choose)
- As a user, I want to be able to be able to load my ski resort app from file (if I so choose)

## **Instructions for Grader**
- You can add a trail to the list of trails by entering the name and difficulty of the new trail in the respective
input fields, then by clicking on the "Add Trail" button. The new trail will be added to the list
displayed in the GUI.
- You can remove a trail from the list by selecting the trail in the displayed list, then by clicking on the 
"Remove Trail" button. The selected trail will be removed from the list.
- You can filter the list of displayed trails by clicking the "Filter by Difficulty" dropdown menu then by selecting
the desired difficulty level from "All", "Easy", "Intermediate", and "Advanced". The list of displayed trails will show
only the trails with the selected difficulty level.
- You can locate my visual component on the left of each trail name in the displayed list as a coloured shape based on 
the trail's difficulty.
- You can save the state of my application by clicking on the "Save" button. The current list of trails will be saved
to skiResort.json in the data package.
- You can reload the state of my application by clicking on the "Load" button. The previously saved json file will be
loaded into the GUI.

## **Event Log sample**
Event log:
Sat Mar 30 15:35:37 PDT 2024
Collins trail added.
Sat Mar 30 15:35:41 PDT 2024
Horizon trail added.
Sat Mar 30 15:35:46 PDT 2024
Status changed for Collins trail.
Sat Mar 30 15:35:54 PDT 2024
Review added for Horizon trail.


### Future Ideas

#### Ski Resort Guest Assistance Application

*Enhancing your Ski Resort Experience*

### **What will the application do?**
- **Equipment Rental:**
  - Allow guests to rent ski/snowboarding equipment based on their preferences and skill levels.
  - Provide information on available equipment and rental prices.
- **Ticket Booking:**
  - Allow guests to purchase lift tickets for a specific number of days.
- **Weather Updates:**
  - Display current weather conditions on the mountain.
  - Provide forecasts to help guests plan their activities.
- **Trail Navigation/Lift Status:**
  - Display a trail map with live updates on trail conditions.
  - Inform users of which lifts and trails are currently open.
  - Suggest trails based on the guest's skiing/riding proficiency.
- **Guest Profile:**
  - Allow guests to create profiles with their skiing/riding preferences.
  - Store and retrieve guest information for personalized experiences.

### **Who will use it?**

Ski resort guests looking to plan and optimize their experience on the mountains.


### **User Stories**
- As a user, I want to be able to purchase lift tickets and add them to my ticket purchase history.
- As a user, I want to be able to view a list of the available equipment along with rental prices.
- As a user, I want to be able to select equipment of my choice and rent them out.
- As a user, I want to be select a difficulty and list all the trails of that difficulty.
- As a user, I want to be able to select a trail and rate it on a scale of 1 to 5 stars.