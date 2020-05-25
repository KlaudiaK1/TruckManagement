# TruckManagement
A system to manage customs clearance queue for trucks.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Using the program](#using-the-program)

## General info
Trucks with different weights arrive at regular intervals for customs clearance, which consists of two stages.
Firstly, the driver drives to the gate where the documents are checked. Then the vehicle is placed in one of two queues to the gates where the transported goods are checked. The time to check the goods in the gate is proportional to the weight of the vehicle.
Queues between the first and second stage of check-in can hold a maximum of 5 vehicles.
The aim of this project is to optimalize average waiting time for trucks.

## Technologies
Project is created with Java.

## Setup
To run this project:
* Download project to a chosen location on your computer.
* Open a command prompt window and go to the directory where you saved the project.
* Type ```$ javac TruckManagement.java``` and press enter to compile your code.
* Type ```$ java TruckManagement``` to run program.

## Using the program
Now you are able to see the result printed on the window.

You are asked to enter truck's weight. It must be a positive integer.

Firstly, the truck is added to main queue, then it is placed in one of two queues to the gates depending on waiting time.

If you want to check waiting time for an exact truck, you should enter '0'. Then you will be able to type truck id and the waiting time will be shown.

If you enter something that isn't a positive integer, the program ends.
