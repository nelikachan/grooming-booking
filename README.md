# Grooming booking system

A web-based appointment booking system for a grooming salon, built with a modern full-stack approach. The application enables customers to conveniently schedule, manage, and cancel appointments, while ensuring data consistency and a smooth booking experience.


## Live demo

This project uses private environment variables (database and email), so it may not run locally without additional setup.

You can view the working version here:

https://grooming-booking.onrender.com/

### Features:
- Online booking
- Select date and time
- Email confirmation
- Cancel booking


## Tech stack

**Backend**

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Mail

**Database**

* PostgreSQL

**Frontend**

*  HTML + CSS + JavaScript 


## Key features

### Appointment booking

* Selection of grooming service (e.g. haircut, bath, ear cleaning)
* Selection of available date and time
* Submission of customer contact details
* Persistent storage of appointments in PostgreSQL


### Availability management

* Dynamic generation of available time slots
* Automatic filtering of already booked hours
* Prevention of double bookings at both application and database level


### Email notifications

* Automatic email confirmation after booking
* Email includes:

    * date and time
    * selected service
    * salon information



### Appointment cancellation

* Each booking generates a unique cancellation token
* Token is sent to the user via email
* User can cancel the appointment using a secure link
* Token-based approach prevents unauthorized access



### Improved identifiers

* System uses **UUID instead of numeric IDs**




### Frontend interface

* Landing page for the grooming salon
* "Book Appointment" button redirecting to reservation form


## Architecture overview

The application follows a layered architecture:

```text
Controller → Service → Repository → Database
```

* **Controller** – handles HTTP requests
* **Service** – contains business logic
* **Repository** – data access layer (Spring Data JPA)
* **Entity** – domain model mapped to database

## Data integrity & reliability

* Validation of input data
* Prevention of double bookings
* Token-based cancellation mechanism

## Example API endpoints

```text
POST   /appointments                → create appointment
GET    /appointments/available      → available time slots
DELETE /appointments/{token}        → cancel appointment via token
```


## Project highlights

* Realistic domain modeling (customers, dogs, services, appointments)
* User-friendly cancellation flow
* Email integration for improved user communication
* Scalable backend design aligned with Spring best practices
* Separation of concerns and code structure


## Possible future improvements

* Authentication and user accounts
* Admin panel for salon management
* Calendar view for appointments
* SMS notifications
* Payment integration



My project demonstrates a complete appointment booking workflow, from reservation to cancellation, with a focus on usability, data integrity, and clean backend architecture. The inclusion of token-based cancellation and UUID identifiers adds a practical layer of security while keeping the system simple for end users.
