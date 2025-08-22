# Uniform Exception Handling Documentation

## Overview
All exceptions in the AuthService now return consistent error responses to the frontend with the following format:

```json
{
  "success": false,
  "message": "Human-readable error message",
  "errorCode": "SPECIFIC_ERROR_CODE",
  "timestamp": "2025-08-22T10:30:45",
  "fieldErrors": {
    "field1": "Field-specific error message",
    "field2": "Another field error"
  }
}
```

## Exception Types and Responses

### 1. Authentication & Authorization Errors

#### USER_NOT_FOUND (404)
```json
{
  "success": false,
  "message": "User not found",
  "errorCode": "USER_NOT_FOUND",
  "timestamp": "2025-08-22T10:30:45"
}
```

#### INVALID_CREDENTIALS (401)
```json
{
  "success": false,
  "message": "Incorrect password",
  "errorCode": "INVALID_CREDENTIALS",
  "timestamp": "2025-08-22T10:30:45"
}
```

#### USER_ALREADY_EXISTS (409)
```json
{
  "success": false,
  "message": "Username or email already exists",
  "errorCode": "USER_ALREADY_EXISTS",
  "timestamp": "2025-08-22T10:30:45"
}
```

### 2. Validation Errors

#### PASSWORD_MISMATCH (400)
```json
{
  "success": false,
  "message": "Passwords do not match",
  "errorCode": "PASSWORD_MISMATCH",
  "timestamp": "2025-08-22T10:30:45"
}
```

#### VALIDATION_ERROR (400)
```json
{
  "success": false,
  "message": "Validation failed",
  "errorCode": "VALIDATION_ERROR",
  "timestamp": "2025-08-22T10:30:45",
  "fieldErrors": {
    "username": "Username is required",
    "email": "Please provide a valid email address",
    "password": "Password must contain at least one uppercase letter"
  }
}
```

### 3. Request Format Errors

#### MALFORMED_REQUEST (400)
```json
{
  "success": false,
  "message": "Invalid request format",
  "errorCode": "MALFORMED_REQUEST",
  "timestamp": "2025-08-22T10:30:45"
}
```

#### METHOD_NOT_SUPPORTED (405)
```json
{
  "success": false,
  "message": "HTTP method not supported for this endpoint",
  "errorCode": "METHOD_NOT_SUPPORTED",
  "timestamp": "2025-08-22T10:30:45"
}
```

#### MISSING_PARAMETER (400)
```json
{
  "success": false,
  "message": "Required parameter 'loginInput' is missing",
  "errorCode": "MISSING_PARAMETER",
  "timestamp": "2025-08-22T10:30:45"
}
```

#### TYPE_MISMATCH (400)
```json
{
  "success": false,
  "message": "Invalid parameter type for 'id'",
  "errorCode": "TYPE_MISMATCH",
  "timestamp": "2025-08-22T10:30:45"
}
```

### 4. System Errors

#### ILLEGAL_STATE (500)
```json
{
  "success": false,
  "message": "Private key file not found",
  "errorCode": "ILLEGAL_STATE",
  "timestamp": "2025-08-22T10:30:45"
}
```

#### INTERNAL_SERVER_ERROR (500)
```json
{
  "success": false,
  "message": "An unexpected error occurred",
  "errorCode": "INTERNAL_SERVER_ERROR",
  "timestamp": "2025-08-22T10:30:45"
}
```

## Frontend Integration

### Error Handling in Frontend
```javascript
// Example React/Angular error handling
const handleApiError = (error) => {
  if (error.response?.data) {
    const { success, message, errorCode, fieldErrors } = error.response.data;
    
    switch (errorCode) {
      case 'USER_NOT_FOUND':
      case 'INVALID_CREDENTIALS':
        showLoginError(message);
        break;
        
      case 'VALIDATION_ERROR':
        showFieldErrors(fieldErrors);
        break;
        
      case 'USER_ALREADY_EXISTS':
        showRegistrationError(message);
        break;
        
      default:
        showGenericError(message);
    }
  }
};
```

### Success Response Format
```json
{
  "success": true,
  "message": "User registered successfully",
  "data": {
    "userId": "123e4567-e89b-12d3-a456-426614174000",
    "username": "john_doe",
    "email": "john@example.com",
    "role": "ROLE_CUSTOMER"
  },
  "timestamp": "2025-08-22T10:30:45"
}
```

## Benefits

✅ **Consistency**: All errors follow the same structure
✅ **Clarity**: Specific error codes for different scenarios  
✅ **Debugging**: Timestamps and detailed messages
✅ **Frontend-Friendly**: Easy to parse and handle
✅ **Internationalization**: Error codes can be mapped to different languages
✅ **Monitoring**: Structured format for logging and monitoring

## Error Code Categories

- **AUTH_**: Authentication/Authorization errors
- **VALIDATION_**: Input validation errors
- **REQUEST_**: HTTP request format errors
- **SYSTEM_**: Internal system errors
- **USER_**: User management errors
