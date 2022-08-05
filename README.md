### How to run the project

#### Clone the project.
#### Go to folder ./Docker/Demo and give the command "docker compose up" in a cmd tool.

#### After the installation, the project will be live on: localhost:3000
#### Only holds the two example from the task are saved in the database: 
#### accountnumber/pin : 123456789/1234 && 987654321/4321
#### After logging in, there are two menu options: Withdraw money and check the atm current note storage.
#### On the Withdraw money page it is possible to choose from 7 predefined amounts or type in a custom amount. 
#### If the withdrawal was successful. the updated balance is shown and and the exact banknotes that the ATM gives to the user. 
#### (This is calculated to be the least pieces of banknotes, for example 200€ -> 4 * 50€)
#### In case of an error, the error is shown to the user with some hints on what went wrong.
