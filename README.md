Hotel Management System(AirBnb) This is a complete, back-end Hotel Management System built with Spring Boot. It provides a RESTful API for both guests to search, book, and pay for hotel rooms, and for hotel managers to manage their properties, inventory, and pricing.

The system features role-based authentication, dynamic pricing based on multiple factors (occupancy, urgency, etc.), and full payment integration with Stripe.

Core Features

Authentication & Authorization Role-Based Access Control: The system defines two distinct roles: GUEST and HOTEL_MANAGER.
JWT Authentication: User authentication is handled using JSON Web Tokens (JWT). The AuthService provides sign-up and login endpoints, generating an access token and a refresh token (stored in an HttpOnly cookie).

Secure Endpoints: All /admin/** routes are protected for HOTEL_MANAGERs, while /bookings/** and /users/** routes require any authenticated user.

Guest Portal (/hotels, /bookings, /users) Search Hotels: Guests can search for available hotels by city, check-in/check-out dates, and number of rooms.
View Hotel Info: Get detailed information for a specific hotel, including its list of rooms.

User Profile: Authenticated users can view and update their profile (name, DoB, gender) and see a list of all their bookings.

Admin Portal (/admin/**) Hotel Management: Hotel managers can perform full CRUD operations on their hotels. Hotels are created as inactive and must be activated via an endpoint to begin populating inventory.
Room Management: Managers can add, update, and delete rooms for their specific hotels.

Inventory Control:

View all inventory for a room, ordered by date.

Update inventory in bulk for a specific date range, allowing changes to the surgeFactor and closed (availability) status.

Reporting: Get a list of all bookings for a hotel and generate a financial report with booking counts, total revenue, and average revenue for a given date range.

Advanced Features Dynamic Pricing Engine The system uses the Strategy Pattern to calculate room prices dynamically at the time of booking. The PricingService chains multiple pricing strategies together:

BasePricingStrategy: Starts with the room's default basePrice.

SurgePricingStrategy: Applies a manager-defined surgeFactor from the inventory.

OccupancyPricingStrategy: Applies a 1.2x multiplier if the room's occupancy is over 80%.

UrgencyPricingStrategy: Applies a 1.15x multiplier if the booking date is within the next 7 days.

HolidayPricingStrategy: Applies a 1.25x multiplier if the date is a holiday (logic is stubbed as isTodayHoliday = true).

Inventory & Search Optimization Inventory Generation: When a hotel is activated or a new room is added, the system automatically generates inventory records for that room for one year in advance.

Scheduled Price Updates: An hourly scheduled task (@EnableScheduling) runs to update inventory prices and populate the HotelMinPrice table. This table stores the lowest price for each hotel for each day, drastically speeding up hotel search queries.

Booking & Payment Flow The booking process is a stateful workflow managed by BookingStatus:

RESERVED: POST /bookings/init

A guest initiates a booking. The system finds and locks the available inventory using @Lock(LockModeType.PESSIMISTIC_WRITE) to prevent race conditions.

The reservedCount in the inventory is incremented, and a Booking record is created.

GUESTS_ADDED: POST /bookings/{bookingId}/addGuests

The user adds guest information (name, age, gender) to the booking.

PAYMENT_PENDING: POST /bookings/{bookingId}/payments

The user requests to pay. The system generates a Stripe Checkout Session and returns the session URL to the client.

CONFIRMED: POST /webhooks/payment (from Stripe)

Stripe sends a checkout.session.completed event to the webhook.

The BookingService captures this, finds the booking by its paymentSessionId, and updates the status to CONFIRMED.

It then atomically decrements reservedCount and increments bookedCount in the inventory.

CANCELLED: POST /bookings/{bookingId}/cancel

A user cancels a CONFIRMED booking. The system sets the status to CANCELLED, decrements the bookedCount in inventory, and issues a refund via the Stripe API.

Tech Stack Framework: Spring Boot 3.5.7

Language: Java 17

Database: PostgreSQL

Data Access: Spring Data JPA

Security: Spring Security, JWT

Payments: Stripe API

Build: Apache Maven

API Docs: SpringDoc OpenAPI

Utilities: Lombok, ModelMapper

Setup & Installation Prerequisites Java 17 (or newer)

Apache Maven

PostgreSQL database

A Stripe account (with API keys)

Clone the Repository Bash
git clone https://github.com/rohitrathore45/hotel_management_system.git cd hotel_management_system 2. Configure the Database Open your PostgreSQL admin tool (e.g., psql or PGAdmin).

Create a new database for the project.

SQL

CREATE DATABASE hotel_system; 3. Configure Application Properties The project uses application.properties for configuration. This file is included in the .gitignore, so you will need to create it.

Create a file at src/main/resources/application.properties and add the following properties:

Properties

Spring Datasource (Update with your Postgres details)
spring.datasource.url=jdbc:postgresql://localhost:5432/hotel_system spring.datasource.username=postgres spring.datasource.password=your_postgres_password

Spring JPA
spring.jpa.hibernate.ddl-auto=update spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

JWT Secret
jwt.secretKey=your-very-strong-and-long-32-byte-secret-key-here

Stripe Keys (Get these from your Stripe Dashboard)
stripe.secret.key=sk_test_... stripe.webhook.secret=whsec_...

Frontend URL (Used for Stripe success/cancel redirects)
frontend.url=http://localhost:3000 4. Run the Application You can run the application using the built-in Maven wrapper.

On macOS/Linux:

Bash

./mvnw spring-boot:run On Windows:

Bash

./mvnw.cmd spring-boot:run The application will start on http://localhost:8080.
