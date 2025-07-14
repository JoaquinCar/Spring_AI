# Spring AI Application

This application uses Spring AI to analyze software engineers' skills and provide recommendations for improvement.

## Setup

### API Key Configuration

1. The application requires an OpenAI API key to function properly.
2. Open the `.env` file in the root directory of the project.
3. Replace `your_openai_api_key_here` with your actual OpenAI API key:

```
OPENAI_API_KEY=your_actual_api_key_here
```

4. Save the file.

### Running the Application

#### Using Docker

1. Make sure you have Docker and Docker Compose installed.
2. Run the following command in the root directory of the project:

```bash
docker-compose up
```

This will start the application and the PostgreSQL database.

#### Using Maven

1. Make sure you have Maven and Java 21 installed.
2. Run the following command in the root directory of the project:

```bash
./mvnw spring-boot:run
```

## API Endpoints

### Search Engineers with AI Analysis

```
GET /software-engineers/search-with-analysis-param?name=<engineer_name>
```

This endpoint searches for engineers by name and provides an AI analysis of their skills.

Example:
```
http://localhost:8080/software-engineers/search-with-analysis-param?name=carlos
```

### Other Endpoints

- `GET /software-engineers/all` - Get all engineers
- `GET /software-engineers/engineer/{id}` - Get engineer by ID
- `GET /software-engineers/search/{name}` - Search engineers by name
- `GET /software-engineers/skill/{skillName}` - Search engineers by skill
- `GET /software-engineers/analyze/{name}` - Alternative endpoint for AI analysis

## Security Note

The `.env` file contains sensitive information and is excluded from version control. Never commit your actual API keys to the repository.