<div align="center">
  <h1>🚆 Sydney Train Disruption Alerts 🚆</h1>
  <p>Real-time train disruption alerts for Sydney commuters</p>
</div>

## About
Sydney Train Disruption Alerts is a real-time REST API that monitors 
and alerts users to disruptions across the Sydney train network. 
Built for daily commuters who rely on Sydney Trains, the system 
automatically polls the Transport for NSW GTFS Realtime feed every 5 minutes, 
detecting new disruptions and delivering instant email notifications to subscribed users. 
API features JWT authentication, a full testing suite and is containerised with Docker.
### Built With
![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
## Features
- **Real-time Data Ingestion** — Automated polling of the Transport for NSW GTFS Realtime API every 5 minutes using Spring's `@Scheduled` annotation, with duplicate prevention
- **Alert Management** — Full create, read, update, delete REST API for managing train disruption alerts stored in a PostgreSQL database
- **Secure Authentication** — JWT-based authentication with password encoding, protecting write and delete endpoints while keeping read endpoints publicly accessible
- **User Registration** — User registration and login system
- **Comprehensive Testing** — Unit and integration tests using JUnit 5 and Mockito, covering service and controller layers with MockMvc
- **Containerisation** — Fully containerised with Docker and Docker Compose for consistent deployment across any environment
## Getting Started
### Prerequisites
- Docker and Docker Compose

### Installation
1. Clone the repository
```bash
   git clone https://github.com/jaxondinh/sydney-transport-tracker.git
```
2. Create a `.env` file in the project root using `.env.example` as a template and fill in the values required
3. Run the application
```bash
   docker-compose up --build
```
4. The API will be available at `http://localhost:8080`

## API Endpoints
### Alerts
| Method | Endpoint | Auth Required | Description |
|--------|----------|---------------|-------------|
| GET | /alerts | | |
| GET | /alerts/{id} | | |
| POST | /alerts | | |
| DELETE | /alerts/{id} | | |
| DELETE | /alerts/all | | |

### Auth
| Method | Endpoint | Auth Required | Description |
|--------|----------|---------------|-------------|
| POST | /auth/login | | |
| POST | /auth/register | | |