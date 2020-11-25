# Banking API

test test

<h3>Product Description</h3>
<hr>
This is a backend API server build mainly using Java servlet which privdes an interaction interface between the user and database storing the bank information.

<h3>Database Schema</h3>
<hr>
The bank database consist of 3 tables with one-to-many relationship between them as show below

!["DB Schema"](/Demo/schema.png)

- One branch has many users
- One user has many accounts

```
Note: to setup PostgreSQL database; first create database with name 'Bank_P0' then run the script 'schema.sql' in the root folder.
```

<h3>API Description</h3>
<hr>

<h4><strong>Login User</strong></h4>
Authenticate the user with email/password credentials and receive JWT token back if the user authenticated <br>

```
Note: the user has to provide his/her token everytime interacts with the server or he/she will get 'please login message'
```

POST: <strong>http://localhost:8080/Bank_P0/login</strong>
<br>
Post Body: x-www-form-urlencoded <br>

<table>
<tr>
<th>Key</th>
<th>Value</th>
</tr>
<tr>
<td>username</td>
<td>test@gmail.com</td>
</tr>
<tr>
<td>password</td>
<td>123</td>
</tr>
</table>

```
Result Body:
Welcome test@gmail.com >> admin
JWT Token:
eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxNCIsInJvbGUiOiJhZG1pbiIsImVtYWlsIjoidGVzdEBnbWFpbC5jb20iLCJleHAiOjE2MDYwMTIxNDQsImlhdCI6MTYwNTk5Nzc0NH0.04aOvPsUukU2Zbwq7yfGLyFlXFnvxqcB22SyTrA4C7g
```

#

<h4><strong>Change Password</strong></h4>
The user can change his/her password after signning in <br>
POST: <strong>http://localhost:8080/Bank_P0/change_pass</strong>
<br>
Post Body: x-www-form-urlencoded <br>
<table>
<tr>
<th>Key</th>
<th>Value</th>
</tr>
<tr>
<td>currPassword</td>
<td>Current password</td>
</tr>
<tr>
<td>newPassword</td>
<td>New password</td>
</tr>
</table>

```
Result Body:
Caller test@gmail.com >> admin
password has been changed successflly
```

#

<h4><strong>Add User</strong></h4>
The user with admin role can create new user after signing in<br>
POST: <strong>http://localhost:8080/Bank_P0/add_user</strong>
<br>
Post Body: x-www-form-urlencoded <br>
<table>
<tr>
<th>Key</th>
<th>Value</th>
</tr>
<tr>
<td>branchid</td>
<td>the value of the branch ID</td>
</tr>
<tr>
<td>firstname</td>
<td>new user's first name</td>
</tr>
<tr>
<td>lastname</td>
<td>new user's last name</td>
</tr>
<tr>
<td>email</td>
<td>new user's email</td>
</tr>
<tr>
<td>pass</td>
<td>new user's password</td>
</tr>
<tr>
<td>dob</td>
<td>new user's date of birth</td>
</tr>
<tr>
<td>role</td>
<td>new user's role [admin/user]</td>
</tr>
</table>

```
Result Body:
Caller test@gmail.com >> admin
user has been added successfully
```

#

<h4><strong>Change User Profile Picture</strong></h4>
User can change his/her profile picture<br>
POST: <strong>http://localhost:8080/Bank_P0/update_image</strong>
<br>
Post Body: enctype="multipart/form-data" <br>
<table>
<tr>
<th>Key</th>
<th>Value</th>
</tr>
<tr>
<td>image</td>
<td>select an image on your file system</td>
</tr>
<tr>
<td>userid</td>
<td>The id of the user you would like to change his/her profile picture</td>
</tr>
</table>

```
Result Body:
image has been uploaded successfully
```

#

<h4><strong>Display User profile Picture</strong></h4>
User can fetch his/her profile picture<br>
GET: <strong>http://localhost:8080/Bank_P0/profile_image?[userID]</strong>

```
Result Body:
<image>
```

#

<h4><strong>Add Account</strong></h4>
The user with admin role can create new account after signing in<br>
POST: <strong>http://localhost:8080/Bank_P0/add_account</strong>
<br>
Post Body: x-www-form-urlencoded <br>
<table>
<tr>
<th>Key</th>
<th>Value</th>
</tr>
<tr>
<td>userid</td>
<td>the value of the user ID</td>
</tr>
<tr>
<td>status</td>
<td>the status of the account [open/pending/closed]</td>
</tr>
<tr>
<td>balance</td>
<td>account balance</td>
</tr>
</table>

```
Result Body:
Caller test@gmail.com >> admin
Account has been added successfully
```

#

<h4><strong>Add Branch</strong></h4>
The user with admin role can create new branch after signing in<br>
POST: <strong>http://localhost:8080/Bank_P0/add_branch</strong>
<br>
Post Body: x-www-form-urlencoded <br>
<table>
<tr>
<th>Key</th>
<th>Value</th>
</tr>
<tr>
<td>branchName</td>
<td>the name of the new branch</td>
</tr>
<tr>
<td>branchCity</td>
<td>the branch city</td>
</tr>
</table>

```
Result Body:
Caller test@gmail.com >> admin
branch has been added successfully
```

#

<h4><strong>Delete User</strong></h4>
The user with admin role can delete user by user ID after signing in<br>
GET: <strong>http://localhost:8080/Bank_P0/delete_user?[userID]</strong>

```
Result Body:
Caller test@gmail.com >> admin
User has been deleted
```

#

<h4><strong>Delete Account</strong></h4>
The user with admin role can delete account by account ID after signing in<br>
GET: <strong>http://localhost:8080/Bank_P0/delete_account?[accountID]</strong>

```
Result Body:
Caller test@gmail.com >> admin
Account has been deleted successflly
```

#

<h4><strong>View Accounts</strong></h4>
The user can list all the bank accounts after signing in<br>
GET: <strong>http://localhost:8080/Bank_P0/get_accounts</strong>

```json
Result Body:
Caller test@gmail.com >> admin
[
    {
        "userID": 11,
        "balance": 150.5,
        "status": "open",
        "openDate": {
            "year": 2020,
            "month": "NOVEMBER",
            "dayOfMonth": 19,
            "dayOfWeek": "THURSDAY",
            "era": "CE",
            "dayOfYear": 324,
            "leapYear": true,
            "monthValue": 11,
            "chronology": {
                "calendarType": "iso8601",
                "id": "ISO"
            }
        }
    }
]
```

#

<h4><strong>View Accounts Per User</strong></h4>
The user can list all the bank accounts for each user after signing in<br>
GET: <strong>http://localhost:8080/Bank_P0/user_accounts?[userID]</strong>

```json
Result Body:
Caller test@gmail.com >> admin
[
    {
        "userID": 11,
        "balance": 150.5,
        "status": "open",
        "openDate": {
            "year": 2020,
            "month": "NOVEMBER",
            "dayOfMonth": 19,
            "dayOfWeek": "THURSDAY",
            "era": "CE",
            "dayOfYear": 324,
            "leapYear": true,
            "monthValue": 11,
            "chronology": {
                "calendarType": "iso8601",
                "id": "ISO"
            }
        }
    }
]
```

#

<h4><strong>View Branches</strong></h4>
The user can list all the bank branches after signing in<br>
GET: <strong>http://localhost:8080/Bank_P0/get_branches</strong>

```json
Result Body:
Caller test@gmail.com >> admin
[
    {
        "branchName": "test",
        "branchCity": "test"
    },
    {
        "branchName": "test1",
        "branchCity": "test1"
    },
    {
        "branchName": "WestValley",
        "branchCity": "Phoenix"
    }
]
```

#

<h4><strong>View Users Per Branch</strong></h4>
The user can list all users in the bank branch after signing in<br>
GET: <strong>http://localhost:8080/Bank_P0/branch_users?[branchID]</strong>

```json
Result Body:
Caller test@gmail.com >> admin
[
    {
        "userID": 11,
        "branchID": 3,
        "firstName": "newUser2",
        "lastName": "newUserLast2",
        "email": "hishamss90@gmail.com",
        "password": "NA",
        "role": "user",
        "dob": {
            "year": 1990,
            "month": "JUNE",
            "dayOfMonth": 22,
            "dayOfWeek": "FRIDAY",
            "era": "CE",
            "dayOfYear": 173,
            "leapYear": false,
            "chronology": {
                "id": "ISO",
                "calendarType": "iso8601"
            },
            "monthValue": 6
        }
    },
    {
        "userID": 13,
        "branchID": 3,
        "firstName": "newUser2222",
        "lastName": "newUserLast22",
        "email": "hishamss9000@gmail.com",
        "password": "NA",
        "role": "user",
        "dob": {
            "year": 1990,
            "month": "JUNE",
            "dayOfMonth": 22,
            "dayOfWeek": "FRIDAY",
            "era": "CE",
            "dayOfYear": 173,
            "leapYear": false,
            "chronology": {
                "id": "ISO",
                "calendarType": "iso8601"
            },
            "monthValue": 6
        }
    }
]
```

#

<h4><strong>View Users</strong></h4>
The user can list all users in the bank after signing in<br>
GET: <strong>http://localhost:8080/Bank_P0/get_users</strong>

```json
Result Body:
Caller test@gmail.com >> admin
[
    {
        "userID": 11,
        "branchID": 3,
        "firstName": "newUser2",
        "lastName": "newUserLast2",
        "email": "hishamss90@gmail.com",
        "password": "NA",
        "role": "user",
        "dob": {
            "year": 1990,
            "month": "JUNE",
            "dayOfMonth": 22,
            "dayOfWeek": "FRIDAY",
            "era": "CE",
            "dayOfYear": 173,
            "leapYear": false,
            "chronology": {
                "id": "ISO",
                "calendarType": "iso8601"
            },
            "monthValue": 6
        }
    },
    {
        "userID": 13,
        "branchID": 3,
        "firstName": "newUser2222",
        "lastName": "newUserLast22",
        "email": "hishamss9000@gmail.com",
        "password": "NA",
        "role": "user",
        "dob": {
            "year": 1990,
            "month": "JUNE",
            "dayOfMonth": 22,
            "dayOfWeek": "FRIDAY",
            "era": "CE",
            "dayOfYear": 173,
            "leapYear": false,
            "chronology": {
                "id": "ISO",
                "calendarType": "iso8601"
            },
            "monthValue": 6
        }
    },
    {
        "userID": 14,
        "branchID": 6,
        "firstName": "Hisham",
        "lastName": "Saymeh",
        "email": "test@gmail.com",
        "password": "NA",
        "role": "admin",
        "dob": {
            "year": 1990,
            "month": "JUNE",
            "dayOfMonth": 22,
            "dayOfWeek": "FRIDAY",
            "era": "CE",
            "dayOfYear": 173,
            "leapYear": false,
            "chronology": {
                "id": "ISO",
                "calendarType": "iso8601"
            },
            "monthValue": 6
        }
    }
]
```

#

<h3>Demo</h3>
<hr>

!["Demo"](/Demo/API.gif)

<h3>Technology Used</h3>
<hr>
<table>
<tr>
<th>Technology</th>
<th>Link</th>
</tr>
<tr>
<td>Java 8</td>
<td>https://www.oracle.com/java/technologies/java8.html</td>
</tr>
<tr>
<td>Apache Maven</td>
<td>https://maven.apache.org/</td>
</tr>
<tr>
<td>JUnit</td>
<td>https://junit.org/junit4/</td>
</tr>
<tr>
<td>PostgreSQL</td>
<td>https://www.postgresql.org/</td>
</tr>
<tr>
<td>Jackson- Java JSON Library</td>
<td>https://github.com/FasterXML/jackson</td>
</tr>
<tr>
<td>Bcrypt</td>
<td>https://www.mindrot.org/projects/jBCrypt/</td>
</tr>
<tr>
<td>Apache Log4j 2</td>
<td>https://logging.apache.org/log4j/2.x/</td>
</tr>
<tr>
<td>JJWT</td>
<td>https://www.jsonwebtoken.io/</td>
</tr>

</table>

<h3>Author</h3>
<hr>

- <a href="http://portfolio.hishamsaymeh.com/">Portfolio</a>
- <a href="https://www.linkedin.com/in/hisham-saymeh">LinkedIn</a>
- <a href="https://github.com/hishamss">GitHub</a>
